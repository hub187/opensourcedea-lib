package deaModels;

import dea.*;
import linearSolver.*;
import lpsolve.LpSolve;
import java.util.ArrayList;





/**
 * The class implementing the SBM (CRS, VRS and GRS) Non Oriented model.
 * </br>
 * @author Hubert Virtos
 *
 */
public class SBM {
	
	/**
	 * The method solving the SBM Problems (Constant and Variable RTS).
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveSBM(DEAProblem deaP) throws Exception {
		
		try {
			/* Declare & Collect the variables that will often be used in the process (rather
			 * than calling the different methods several times.*/
			int NbDMUs = deaP.getNumberOfDMUs();
			int NbVariables = deaP.getNumberOfVariables();
			double [] [] TransposedMatrix = new double [NbVariables] [NbDMUs];
			TransposedMatrix = deaP.getTranspose(true);
			DEAPSolution ReturnSol = new DEAPSolution(deaP.getNumberOfDMUs(), deaP.getNumberOfVariables());
			
			
			/* As the SBM optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.*/
			
			for (int DMUIndex = 0; DMUIndex < NbDMUs; DMUIndex++) {
				
				createAndSolveSBM(deaP, NbDMUs, NbVariables, TransposedMatrix,
						ReturnSol, DMUIndex);
			}
			return ReturnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	
	
	private static void createAndSolveSBM(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, Integer i /*DMU Number*/) throws Exception {
		
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		/* There is one extra column for the scalar t used during the 
		 * Fractional to Linear SBM model transformaation.*/
		double[] ObjF = new double [NbDMUs + NbVariables + 1]; 
		int[] SolverEqualityType;
		double[] RHS;
		
		//if model is assuming Variable RTS, we need to add an extra row for the convexity constraint
		if(deaP.getModelType() == ModelType.SBM) {
			RHS = new double [NbVariables + 1];
			SolverEqualityType = new int [NbVariables + 1];
		}
		else if (deaP.getModelType() == ModelType.SBMV) {
			RHS = new double [NbVariables + 2];
			SolverEqualityType = new int [NbVariables + 2];
		}
		else /*if (deaP.getModelType() == ModelType.SBMGRS)*/ {
			RHS = new double [NbVariables + 3];
			SolverEqualityType = new int [NbVariables + 3];
		}
		
		
		createModel(deaP, NbDMUs, NbVariables, TransposedMatrix, i,
				Constraints, ObjF, SolverEqualityType, RHS);
		
		
		//SOLVE PROBLEM
		SolverResults Sol = new SolverResults();
		
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MIN, SolverEqualityType);
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
					 i.toString());
		}
		catch (DEASolverException e2) {
			throw e2;
		}

		
		try {
			storeSolution(deaP, NbDMUs, NbVariables, ReturnSol, i, Sol);
		} catch (Exception e) {
			throw e;
		}
		
	}




	private static void storeSolution(DEAProblem deaP, int NbDMUs,
			int NbVariables, DEAPSolution ReturnSol, int i, SolverResults Sol) throws Exception {
		
		try {
			//STORE SOLUTION
			double t = Sol.VariableResult[0];
			
			ReturnSol.setObjective(i, Sol.Objective);
			for(int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
				
				ReturnSol.setSlack(i, VarIndex, Sol.VariableResult[NbDMUs + 1 + VarIndex] / t);
				
				if(deaP.getVariableType(VarIndex) == VariableType.INPUT) {
					ReturnSol.setWeight(i, VarIndex, Sol.DualResult[VarIndex + 1]);
					ReturnSol.setProjection(i, VarIndex,
							deaP.getDataMatrix(i, VarIndex) - ReturnSol.getSlack(i, VarIndex));//
				}
				else {
					ReturnSol.setWeight(i, VarIndex, Sol.DualResult[VarIndex + 1] * -1);
					ReturnSol.setProjection(i, VarIndex,
							deaP.getDataMatrix(i, VarIndex) + ReturnSol.getSlack(i, VarIndex));
				}
			}
			
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int LambdaIndex = 0; LambdaIndex < NbDMUs;LambdaIndex++){
				if(Sol.VariableResult[LambdaIndex + 1] != 0){
					refSet.add(new NonZeroLambda(LambdaIndex, Sol.VariableResult[LambdaIndex + 1] / t));
				}
				ReturnSol.setReferenceSet(i, refSet);
				//ReturnSol.Lambdas[i][j] = Sol.VariableResult[j + 1] / t;
			}
			
			SolverStatus.checkSolverStatus(ReturnSol, Sol);
		}
		catch (Exception e) {
			throw e;
		}
	}




	private static void createModel(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix, int i,
			ArrayList<double[]> Constraints, double[] ObjF,
			int[] SolverEqualityType, double[] RHS) throws Exception {
		
		try {
			ObjF[0] = 1;
			
			/* BUILD THE CONTRAINST MATRIX (loop through all variables + one row for extra constraint after
			 * Fractional => Linear transformation*/
			for (int VarIndex = 0; VarIndex <= NbVariables; VarIndex++) {
				double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
				
				if(VarIndex < NbVariables) {
					//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
						ConstraintRow[0] = TransposedMatrix[VarIndex] [i] * -1;
					//Copy rest of the data matrix
					System.arraycopy(TransposedMatrix[VarIndex], 0, ConstraintRow, 1, NbDMUs);
					//and slacks
					if (deaP.getVariableType(VarIndex) == VariableType.INPUT) {
						ConstraintRow[NbDMUs + 1 + VarIndex] = -1;
					}
					else {
						ConstraintRow[NbDMUs + 1 + VarIndex] = 1;
					}
					Constraints.add(ConstraintRow);
					
					//Build RHS
					RHS[VarIndex] = 0;
					SolverEqualityType[VarIndex] = LpSolve.EQ;
					
				}
				else {
					/* Last row (added constraint after Fractional => Linear transformation).
					 * Necessary for all SBM models*/
					ConstraintRow[0] = 1;
					for (int SlackIndex = 0; SlackIndex < NbVariables; SlackIndex++) {
						if(deaP.getVariableType(SlackIndex) != VariableType.INPUT) {
							ConstraintRow[NbDMUs + 1 + SlackIndex] = -1 / (TransposedMatrix[SlackIndex] [i] * deaP.getNumberOfOutputs());
						}
						else {
							ObjF[NbDMUs + 1 + SlackIndex] = 1 / (TransposedMatrix[SlackIndex] [i] * deaP.getNumberOfInputs());
						}
					}
					Constraints.add(ConstraintRow);
					
					//Build last row of RHS
					RHS[VarIndex] = 1;
					SolverEqualityType[VarIndex] = LpSolve.EQ;
					
				}
				
			} //End loop through all variables (rows of constraint matrix) + 1 row (added constraint)
			
			
			//OPTIONAL ROWS
			if(deaP.getModelType() == ModelType.SBMV) {
				//Build convexity constraint. This is the ONLY difference with the SBMmodel
				double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
				ConstraintRow[0] = 1;
				for (int DMUIndex = 1; DMUIndex <= NbDMUs; DMUIndex++) {
					ConstraintRow[DMUIndex] = -1;
				}
				Constraints.add(ConstraintRow);
				//RHS already 0.
				SolverEqualityType[NbVariables + 1] = LpSolve.EQ;
			}
			
			if(deaP.getModelType() == ModelType.SBMGRS) {
				double[] TempConstraintRow = new double[NbDMUs + NbVariables + 1];
				for (int DMUIndex = 1; DMUIndex <= NbDMUs; DMUIndex++) {
					TempConstraintRow[DMUIndex] = -1;
				}
				//Lower Bounds (General RTS)
				double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
				System.arraycopy(TempConstraintRow, 1, ConstraintRow, 1, NbDMUs);
				ConstraintRow[0] = deaP.getRTSLowerBound();
				Constraints.add(ConstraintRow);
				RHS[NbVariables + 1] = 0;
				SolverEqualityType[NbVariables + 1] = LpSolve.LE;
				
				//Upper Bounds (General RTS)
				ConstraintRow = new double[NbDMUs + NbVariables + 1];
				System.arraycopy(TempConstraintRow, 1, ConstraintRow, 1, NbDMUs);
				ConstraintRow[0] = deaP.getRTSUpperBound();
				Constraints.add(ConstraintRow);
				RHS[NbVariables + 2] = 0;
				SolverEqualityType[NbVariables + 2] = LpSolve.GE;
				}
		}
		catch (Exception e) {
			throw e;
		}
	}
}
