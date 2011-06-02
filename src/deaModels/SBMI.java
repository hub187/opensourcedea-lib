package deaModels;

import dea.*;
import linearSolver.*;
import lpsolve.LpSolve;
import java.util.ArrayList;





/**
 * The class implementing the SBM Input Oriented model (implementing both CRTS and VRTS).
 * </br>
 * @author Hubert Virtos
 *
 */
public class SBMI {
	
	/**
	 * The method solving the CCR Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws DEASolverException 
	 */
	public static DEAPSolution solveSBMI(DEAProblem deaP) throws DEASolverException {
		
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
		int NbDMUs = deaP.getNumberOfDMUs();
		int NbVariables = deaP.getNumberOfVariables();
		double [] [] TransposedMatrix = new double [NbVariables] [NbDMUs];
		TransposedMatrix = deaP.getTranspose(false);
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		DEAPSolution ReturnSol = new DEAPSolution(deaP.getNumberOfDMUs(), deaP.getNumberOfVariables());
		
		/* Because the Constraint Matrix is identical for all optimisation,
		 * let's build it here and re-use it for each optimisation. */
		createConstraintMatrix(deaP, NbDMUs, NbVariables, TransposedMatrix,
				Constraints);
		
		/* As the SBM optimisation needs to be ran for all DMUs, 
		 * the program will loop through all DMUs.*/
		
		for (int DMUIndex = 0; DMUIndex < NbDMUs; DMUIndex++) {
			createAndSolveSBMI(deaP, NbDMUs, NbVariables, Constraints, TransposedMatrix,
					ReturnSol, DMUIndex);
		}		
		return ReturnSol;
	}
	
	
	private static void createConstraintMatrix(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			ArrayList<double[]> Constraints) {

		for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
			double[] ConstraintRow = new double[NbDMUs + NbVariables];
			
				//Copy rest of the data matrix
				System.arraycopy(TransposedMatrix[VarIndex], 0, ConstraintRow, 0, NbDMUs);
				//and slacks
				if (deaP.getVariableType(VarIndex) == DEAVariableType.Input) {
					ConstraintRow[NbDMUs + VarIndex] = 1;
				}
				else {
					ConstraintRow[NbDMUs + VarIndex] = -1;
				}
				Constraints.add(ConstraintRow);
		}
		
		if(deaP.getModelType() == DEAModelType.SBMIV) {
			//Add one row for the convexity constraints
			double[] ConstraintRow = new double[NbDMUs + NbVariables];
			for(int DMUIndex = 0; DMUIndex < NbDMUs; DMUIndex++) {
				ConstraintRow[DMUIndex] = 1;
			}
			Constraints.add(ConstraintRow);
		}
		
		if(deaP.getModelType() == DEAModelType.SBMIGRS) {
			double[] ConstraintRow = new double[NbDMUs + NbVariables];
			for(int DMUIndex = 0; DMUIndex < NbDMUs; DMUIndex++) {
				ConstraintRow[DMUIndex] = 1;
			}
			Constraints.add(ConstraintRow);
			Constraints.add(ConstraintRow);
		}
	}

	private static void createAndSolveSBMI(DEAProblem deaP, int NbDMUs,
			int NbVariables,  ArrayList<double[]> Constraints, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, int i) throws DEASolverException {
		double[] ObjF = new double [NbDMUs + NbVariables];
		double[] RHS;
		int[] SolverEqualityType;
		if(deaP.getModelType() == DEAModelType.SBMI) {
			RHS = new double[NbVariables];
			SolverEqualityType = new int[NbVariables];
		}
		else if (deaP.getModelType() == DEAModelType.SBMIV) {
			//One extra row for convexity constraint
			RHS = new double[NbVariables + 1];
			SolverEqualityType = new int[NbVariables + 1];
		}
		else { //In this case the model is DEAModelType.SBMIGRS
			//Tow extra rows for GRS constraints
			RHS = new double[NbVariables + 2];
			SolverEqualityType = new int[NbVariables + 2];
		}

		
		createModel(deaP, NbDMUs, NbVariables, TransposedMatrix, i, ObjF, RHS,
				SolverEqualityType);
		
		
		SolverResults Sol = new SolverResults();
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MIN, SolverEqualityType);
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		
		storeSolution(deaP, NbDMUs, NbVariables, ReturnSol, i, Sol);
	}


	private static void storeSolution(DEAProblem deaP, int NbDMUs,
			int NbVariables, DEAPSolution ReturnSol, int i, SolverResults Sol) {
		//Store solution
		ReturnSol.setObjective(i, 1 + Sol.Objective); //there was a Objective constant of 1.
		
		for(int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
			ReturnSol.setSlack(i, VarIndex, Sol.VariableResult[NbDMUs + VarIndex]);
			
			if(deaP.getVariableType(VarIndex) == DEAVariableType.Input) {
				ReturnSol.setWeight(i, VarIndex, Sol.DualResult[VarIndex + 1] * -1);
				ReturnSol.setProjection(i, VarIndex,
						deaP.getDataMatrix(i, VarIndex) - ReturnSol.getSlack(i, VarIndex));
			}
			else {
				ReturnSol.setWeight(i, VarIndex, Sol.DualResult[VarIndex + 1]);
				ReturnSol.setProjection(i, VarIndex,
						deaP.getDataMatrix(i, VarIndex) + ReturnSol.getSlack(i, VarIndex));
			}
		}
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		for(int LambdaIndex = 0; LambdaIndex < NbDMUs; LambdaIndex++) {
			if(Sol.VariableResult[LambdaIndex] != 0) {
				refSet.add(new NonZeroLambda(LambdaIndex, Sol.VariableResult[LambdaIndex]));
			}
		}
		ReturnSol.setReferenceSet(i, refSet);
		
		SolverStatus.checkSolverStatus(ReturnSol, Sol);
	}


	private static void createModel(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix, int i, double[] ObjF,
			double[] RHS, int[] SolverEqualityType) {
		int NbInputs = deaP.getNumberOfInputs();
				
		//Build the RHS
		for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
				//Build RHS
				RHS[VarIndex] = TransposedMatrix[VarIndex] [i];
				if(deaP.getVariableType(VarIndex) == DEAVariableType.Input){
					ObjF[NbDMUs + VarIndex] = -1 / (TransposedMatrix[VarIndex] [i] * NbInputs);
				}
				SolverEqualityType[VarIndex] = LpSolve.EQ;
		}
		
		//Add extra row if Variable RTS
		if(deaP.getModelType() == DEAModelType.SBMIV) {
			RHS[NbVariables] = 1;
			SolverEqualityType[NbVariables] = LpSolve.EQ;
		}
		
		//Add two extra rows if model is SBMIGRS
		if(deaP.getModelType() == DEAModelType.SBMIGRS) {
			
			RHS[NbVariables] = deaP.getRTSLowerBound();
			SolverEqualityType[NbVariables] = LpSolve.GE;
			
			RHS[NbVariables + 1] = deaP.getRTSUpperBound();
			SolverEqualityType[NbVariables+1] = LpSolve.LE;
			
		}
	}
	
}
