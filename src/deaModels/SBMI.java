package deaModels;

import dea.*;
import dea.DEASolverException;
import linearSolver.*;

import java.util.ArrayList;





/**
 * The class implementing the SBMI model.
 * TABLE NOT UP TO DATE NEEDS MODIFICATION CURRENTLY CCR
 *<p>
 *The linear problem of the Model is as follows:
 *<p>
 * <center>
 * <table border = "1">
 * <tr>
 * 		<td>Variable</td>
 * 		<td>t</td>
 * 		<td>Lambda 1</td>
 * 		<td>...</td>
 * 		<td>Lambda n</td>
 * 		<td>Slack 1 (input)</td>
 * 		<td>...</td>
 * 		<td>Slack p (output)</td>
 * 		<td>DIR</td>
 * 		<td>RHS</td>
 * </tr>
 * <tr>
 * 		<td>Obj Coeff</td>
 * 		<td>1</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>-1 / (Input(1,1) * NbOfInputs)</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td></td>
 * 		<td></td>
 * </tr>
 * <tr>
 * 		<td>Input 1</td>
 * 		<td>Input 1,1</td>
 * 		<td>-Input 1,1</td>
 * 		<td>...</td>
 * 		<td>-Input 1, n</td>
 * 		<td>...</td>
 * 		<td>...</td>
 * 		<td>0</td>
 * 		<td>E</td>
 * 		<td>0</td>
 * </tr>
 * <tr>
 * 		<td>Input i</td>
 * 		<td>Input i, 1</td>
 * 		<td>-Input i, 1</td>
 * 		<td>...</td>
 * 		<td>-Input i, n</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>0</td>
 * 		<td>E</td>
 * 		<td>0</td>
 * </tr>
 * <tr>
 * 		<td>Ouput p</td>
 * 		<td>-Output p, 1</td>
 * 		<td>-Output p, 1</td>
 * 		<td>...</td>
 * 		<td>-Output p, n</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>-1</td>
 * 		<td>E</td>
 * 		<td>0</td>
 * </tr>
 * <tr>
 * 		<td>1</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>1 /(Output(p,n) * NbOfOutputs)</td>
 * 		<td>E</td>
 * 		<td>1</td>
 * </tr>
 * </table>
 * </center>
 * <p>
 *  Where the input values of the DMU being optimised are put in the Theta column and timed by -1
 *  (e.g. -Inputi 1 being the ith input of DMU1).
 * <p>
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
		double[] RHS = new double [NbVariables];
		int NbInputs = deaP.getNumberOfInputs();
		
		
		//Build the RHS
		for (int j = 0; j < NbVariables; j++) {
				//Build RHS
				RHS[j] = TransposedMatrix[j] [i];
				if(deaP.getVariableType(j) == DEAVariableType.Input){
					ObjF[NbDMUs + j] = -1 / (TransposedMatrix[j] [i] * NbInputs);
				}
		}
		
		SolverResults Sol = new SolverResults();
		
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MIN);
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
