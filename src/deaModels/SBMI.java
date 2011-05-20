package deaModels;

import dea.*;
import dea.DEASolverException;
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
		for (int j = 0; j < NbVariables; j++) {
			double[] ConstraintRow = new double[NbDMUs + NbVariables];
			
				//Copy rest of the data matrix
				System.arraycopy(TransposedMatrix[j], 0, ConstraintRow, 0, NbDMUs);
				//and slacks
				if (deaP.getVariableType(j) == DEAVariableType.Input) {
					ConstraintRow[NbDMUs + j] = 1;
				}
				else {
					ConstraintRow[NbDMUs + j] = -1;
				}
				Constraints.add(ConstraintRow);
		}
		
		if(deaP.getModelType() == DEAModelType.SBMIV) {
			//Add one row for the convexity constraints
			double[] ConstraintRow = new double[NbDMUs + NbVariables];
			for(int k = 0; k < NbDMUs; k++) {
				ConstraintRow[k] = 1;
			}
			Constraints.add(ConstraintRow);
		}
		
		if(deaP.getModelType() == DEAModelType.SBMIGRS) {
			double[] ConstraintRow = new double[NbDMUs + NbVariables];
			for(int k = 0; k < NbDMUs; k++) {
				ConstraintRow[k] = 1;
			}
			Constraints.add(ConstraintRow);
			Constraints.add(ConstraintRow);
		}
		
		/* As the SBM optimisation needs to be ran for all DMUs, 
		 * the program will loop through all DMUs.*/
		
		for (int i = 0; i < NbDMUs; i++) {
			createAndSolveSBMI(deaP, NbDMUs, NbVariables, Constraints, TransposedMatrix,
					ReturnSol, i);
		}
		
		
		return ReturnSol;
		
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

		int NbInputs = deaP.getNumberOfInputs();
		
		
		//Build the RHS
		for (int j = 0; j < NbVariables; j++) {
				//Build RHS
				RHS[j] = TransposedMatrix[j] [i];
				if(deaP.getVariableType(j) == DEAVariableType.Input){
					ObjF[NbDMUs + j] = -1 / (TransposedMatrix[j] [i] * NbInputs);
				}
				SolverEqualityType[j] = LpSolve.EQ;
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
		
		SolverResults Sol = new SolverResults();
		
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MIN, SolverEqualityType);
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		//Store solution
		ReturnSol.Objectives[i] = 1 + Sol.Objective; //there was a Objective constant of 1.
		for(int j = 0; j < NbVariables; j++) {
			
			ReturnSol.Slacks[i][j] = Sol.VariableResult[NbDMUs + j];
			
			if(deaP.getVariableType(j) == DEAVariableType.Input) {
				ReturnSol.Weights[i][j] = Sol.DualResult[j + 1] * -1;
				ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) - ReturnSol.Slacks[i] [j];
			}
			else {
				ReturnSol.Weights[i][j] = Sol.DualResult[j + 1];
				ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) + ReturnSol.Slacks[i] [j];
			}
		}
		
		System.arraycopy(Sol.VariableResult, 0, ReturnSol.Lambdas[i], 0, NbDMUs - 1);
		
		checkSolverStatus(ReturnSol, Sol);
	}

	private static void checkSolverStatus(DEAPSolution ReturnSol,
			SolverResults Sol) {
		switch(Sol.Status) {
			case OptimalSolutionNotfound:
				ReturnSol.Status = SolverReturnStatus.OptimalSolutionNotfound;
				break;
		
			case UnknownError:
				ReturnSol.Status = SolverReturnStatus.UnknownError;
				break;
			
			case ModelCreationFailure:
				ReturnSol.Status = SolverReturnStatus.ModelCreationFailure;
				break;
			
			case OptimalSolutionFound:
				/* The lpsolve class CANNOT return NA (which is only used for initialisation as default value).
				 * If ReturnSol.Status == NA this means the previous optimisation (if any) did not have any problem so it is
				 * save to store a ReturnValue of OptimalSolutionFound*/
				
				if(ReturnSol.Status == SolverReturnStatus.NA){
					ReturnSol.Status = SolverReturnStatus.OptimalSolutionFound;
				}
				break;
		}
	}
	
	
	
}
