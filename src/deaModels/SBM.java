package deaModels;

import dea.*;
import dea.DEASolverException;
import linearSolver.*;

import java.util.ArrayList;





/**
 * The class implementing the SBM model.
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
public class SBM {
	
	/**
	 * The method solving the CCR Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws DEASolverException 
	 */
	public static DEAPSolution solveSBM(DEAProblem deaP) throws DEASolverException {
		
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
		int NbDMUs = deaP.getNumberOfDMUs();
		int NbVariables = deaP.getNumberOfVariables();
		double [] [] TransposedMatrix = new double [NbVariables] [NbDMUs];
		TransposedMatrix = deaP.getTranspose(true);
		DEAPSolution ReturnSol = new DEAPSolution(deaP.getNumberOfDMUs(), deaP.getNumberOfVariables());
		
		
		/* As the SBM optimisation needs to be ran for all DMUs, 
		 * the program will loop through all DMUs.*/
		
		for (int i = 0; i < NbDMUs; i++) {
			
			ArrayList<double[]> Constraints = new ArrayList<double []>();
			double[] ObjF = new double [NbDMUs + NbVariables + 1];
			double[] RHS = new double [NbVariables + 1];
			
			ObjF[0] = 1;
			
			//Build the constraint matrix
			for (int j = 0; j <= NbVariables; j++) {
				double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
				
				if(j < NbVariables) {
					//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
						ConstraintRow[0] = TransposedMatrix[j] [i] * -1;
					//Copy rest of the data matrix
					System.arraycopy(TransposedMatrix[j], 0, ConstraintRow, 1, NbDMUs);
					//and slacks
					if (deaP.getVariableType(j) == DEAVariableType.Input) {
						ConstraintRow[NbDMUs + 1 + j] = -1;
					}
					else {
						ConstraintRow[NbDMUs + 1 + j] = 1;
					}
					Constraints.add(ConstraintRow);
					
					//Build RHS
					RHS[j] = 0;
					
				}
				else {
					//Last row (added constraint)
					ConstraintRow[0] = 1;
					for (int v = 0; v < NbVariables; v++) {
						if(deaP.getVariableType(v) != DEAVariableType.Input) {
							ConstraintRow[NbDMUs + 1 + v] = -1 / (TransposedMatrix[v] [i] * deaP.getNumberOfOutputs());
						}
						else {
							ObjF[NbDMUs + 1 + v] = 1 / (TransposedMatrix[v] [i] * deaP.getNumberOfInputs());
						}
					}
					Constraints.add(ConstraintRow);
					
					//Build last row of RHS
					RHS[j] = 1;
					
				}
				
			} //End loop through all variables (rows of constraint matrix) + 1 row (added constraint)
			
			SolverResults Sol = new SolverResults();
			
			try {
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MIN);
			}
			catch (DEASolverException e) {
				throw e;
			}
			
			//Store solution
			double t = Sol.VariableResult[0];
			
			ReturnSol.Objectives[i] = Sol.Objective;
			for(int j = 0; j < NbVariables; j++) {
				
				ReturnSol.Slacks[i][j] = Sol.VariableResult[NbDMUs + 1 + j] / t;
				
				if(deaP.getVariableType(j) == DEAVariableType.Input) {
					ReturnSol.Weights[i][j] = Sol.DualResult[j + 1];
					ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) - ReturnSol.Slacks[i] [j];
				}
				else {
					ReturnSol.Weights[i][j] = Sol.DualResult[j + 1] * -1;
					ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) + ReturnSol.Slacks[i] [j];
				}
			}
			
			for(int j = 0; j < NbDMUs;j++){
				ReturnSol.Lambdas[i][j] = Sol.VariableResult[j + 1] / t;
			}
			
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
			

			

		} //End loop through all DMUs
		
		
		return ReturnSol;
		
	}
	
	
	
}
