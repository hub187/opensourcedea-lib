package deaModels;

import dea.*;
import dea.DEASolverException;
import linearSolver.*;

import java.util.ArrayList;





/**
 * The class implementing the SBMO model.
 * <p>
 * Because the objective 'MIN po = 1 / (1 + (1/s) SUM(Sr+/Yro))' is not linear the 
 * objective used in the calculation is 'MAX 1/p0 = (1 + (1/s) SUM(Sr+/Yro))' which is linear.</p>
 * <p>
 * As a consquence the output slack weight is 1 / (s*Yro) and the model objective is deduced from
 * the solver objective value as follows: Model Objective = 1 / (1 + SolverObjectiveValue).
 * </p>
 * @author Hubert Virtos
 *
 */
public class SBMO {
	
	/**
	 * The method solving the CCR Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws DEASolverException 
	 */
	public static DEAPSolution solveSBMO(DEAProblem deaP) throws DEASolverException {
		
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
		
		
		/* As the SBM optimisation needs to be ran for all DMUs, 
		 * the program will loop through all DMUs.*/
		
		for (int i = 0; i < NbDMUs; i++) {
			createAndSolveSBMO(deaP, NbDMUs, NbVariables, Constraints, TransposedMatrix,
					ReturnSol, i);
		}
		
		
		return ReturnSol;
		
	}

	private static void createAndSolveSBMO(DEAProblem deaP, int NbDMUs,
			int NbVariables,  ArrayList<double[]> Constraints, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, int i) throws DEASolverException {
		double[] ObjF = new double [NbDMUs + NbVariables];
		double[] RHS = new double [NbVariables];
		int NbOutputs = deaP.getNumberOfOutputs();
		
		
		//Build the RHS
		for (int j = 0; j < NbVariables; j++) {
				//Build RHS
				RHS[j] = TransposedMatrix[j] [i];
				if(deaP.getVariableType(j) == DEAVariableType.Output){
					ObjF[NbDMUs + j] = 1 / (TransposedMatrix[j] [i] * NbOutputs);
				}
		}
		
		SolverResults Sol = new SolverResults();
		
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MAX);
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		//Store solution
		ReturnSol.Objectives[i] = 1 / (1 + Sol.Objective); //there was a Objective constant of 1.
		for(int j = 0; j < NbVariables; j++) {
			
			ReturnSol.Slacks[i][j] = Sol.VariableResult[NbDMUs + j];
			
			if(deaP.getVariableType(j) == DEAVariableType.Output) {
				ReturnSol.Weights[i][j] = Sol.DualResult[j + 1] * -1;
				ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) + ReturnSol.Slacks[i] [j];
			}
			else {
				ReturnSol.Weights[i][j] = Sol.DualResult[j + 1];
				ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j); // + ReturnSol.Slacks[i] [j];
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