package deaModels;

import dea.*;
import linearSolver.*;
import lpsolve.LpSolve;
import java.util.ArrayList;





/**
 * The class implementing the SBM Output Oriented model (implementing both CRTS and VRTS).
 * <p>
 * Because the objective 'MIN po = 1 / (1 + (1/s) SUM(Sr+/Yro))' is not linear the 
 * objective used in the calculation is 'MAX 1/p0 = (1 + (1/s) SUM(Sr+/Yro))' which is linear.</p>
 * <p>
 * As a consequence the output slack weight is 1 / (s*Yro) and the model objective is deduced from
 * the solver objective value as follows: Model Objective = 1 / (1 + SolverObjectiveValue).
 * </p>
 * @author Hubert Virtos
 *
 */
public class SBMO {
	
	/**
	 * The method solving the CCR Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveSBMO(DEAProblem deaP) throws Exception {
		
		try {
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
		
		for (int i = 0; i < NbDMUs; i++) {
			createAndSolveSBMO(deaP, NbDMUs, NbVariables, Constraints, TransposedMatrix,
					ReturnSol, i);
		}
		return ReturnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}
	

	private static void createConstraintMatrix(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			ArrayList<double[]> Constraints) throws Exception {
		
		try {
			double[] ConstraintRow;
			for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
				ConstraintRow = new double[NbDMUs + NbVariables];
				
					//Copy rest of the data matrix
					System.arraycopy(TransposedMatrix[VarIndex], 0, ConstraintRow, 0, NbDMUs);
					//and slacks
					if (deaP.getVariableType(VarIndex) == VariableType.INPUT) {
						ConstraintRow[NbDMUs + VarIndex] = 1;
					}
					else {
						ConstraintRow[NbDMUs + VarIndex] = -1;
					}
					Constraints.add(ConstraintRow);
			}
			
			if(deaP.getModelType() == ModelType.SBMOV) {
				ConstraintRow = new double[NbDMUs + NbVariables];
				for(int k = 0; k < NbDMUs; k++) {
					ConstraintRow[k] = 1;
				}
				Constraints.add(ConstraintRow);
			}
			if(deaP.getModelType() == ModelType.SBMOGRS) {
				ConstraintRow = new double[NbDMUs + NbVariables];
				for(int k = 0; k < NbDMUs; k++) {
					ConstraintRow[k] = 1;
				}
				Constraints.add(ConstraintRow);
				Constraints.add(ConstraintRow);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	private static void createAndSolveSBMO(DEAProblem deaP, int NbDMUs,
			int NbVariables,  ArrayList<double[]> Constraints, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, Integer i) throws Exception {
		
		try {
			double[] ObjF = new double [NbDMUs + NbVariables];
			double[] RHS;
			int[] SolverEqualityType;
			int NbOutputs = deaP.getNumberOfOutputs();
			
			if(deaP.getModelType() == ModelType.SBMO) {
				RHS = new double [NbVariables];
				SolverEqualityType = new int[NbVariables];
			}
			else if (deaP.getModelType() == ModelType.SBMOV) {
				RHS = new double [NbVariables + 1];
				SolverEqualityType = new int[NbVariables + 1];
				RHS[NbVariables] = 1;
				SolverEqualityType[NbVariables] = LpSolve.EQ;
			}
			else { //deaP.getModelType() == ModelType.SBMOGRS
				RHS = new double [NbVariables + 2];
				SolverEqualityType = new int[NbVariables + 2];
				RHS[NbVariables] = deaP.getRTSLowerBound();
				SolverEqualityType[NbVariables] = LpSolve.GE;
				RHS[NbVariables + 1] = deaP.getRTSUpperBound();
				SolverEqualityType[NbVariables + 1] = LpSolve.LE;
				
			}
			
			
			
			
			//Build the RHS (for the variables, added constraints (convexity, grs... were added before)
			for (int j = 0; j < NbVariables; j++) {
					//Build RHS
					RHS[j] = TransposedMatrix[j] [i];
					if(deaP.getVariableType(j) == VariableType.OUTPUT){
						ObjF[NbDMUs + j] = 1 / (TransposedMatrix[j] [i] * NbOutputs);
					}
					SolverEqualityType[j] = LpSolve.EQ;
			}
			
			
			SolverResults Sol = new SolverResults();
			
			try {
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MAX, SolverEqualityType);
			}
			catch (ProblemNotSolvedProperly e1) {
				throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
						 i.toString());
			}
			catch (DEASolverException e2) {
				throw e2;
			}
			
			
			storeSolution(deaP, NbDMUs, NbVariables, ReturnSol, i, Sol);
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void storeSolution(DEAProblem deaP, int NbDMUs,
			int NbVariables, DEAPSolution ReturnSol, int i, SolverResults Sol) throws Exception {
		
		try {
			//Store solution
			ReturnSol.setObjective(i, 1 / (1 + Sol.Objective)); //there was a Objective constant of 1.
			for(int varPos = 0; varPos < NbVariables; varPos++) {
				
				ReturnSol.setSlack(i, varPos, Sol.VariableResult[NbDMUs + varPos]);
				
				if(deaP.getVariableType(varPos) == VariableType.OUTPUT) {
					ReturnSol.setWeight(i, varPos, Sol.DualResult[varPos + 1] * -1);
					ReturnSol.setProjection(i, varPos,
							deaP.getDataMatrix(i, varPos) + ReturnSol.getSlack(i, varPos));//
				}
				else {
					ReturnSol.setWeight(i, varPos, Sol.DualResult[varPos + 1]);
					ReturnSol.setProjection(i, varPos,
							deaP.getDataMatrix(i, varPos) - ReturnSol.getSlack(i, varPos)); // + ReturnSol.Slacks[i] [j];
				}
			}
			
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int lambdaPos = 0; lambdaPos < NbDMUs; lambdaPos++) {
				if(Sol.VariableResult[lambdaPos] != 0) {
					refSet.add(new NonZeroLambda(lambdaPos, Sol.VariableResult[lambdaPos]));
				}
			}
			ReturnSol.setReferenceSet(i, refSet);
			//System.arraycopy(Sol.VariableResult, 0, ReturnSol.Lambdas[i], 0, NbDMUs - 1);
			
			SolverStatus.checkSolverStatus(ReturnSol, Sol);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
