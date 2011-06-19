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
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVariables = deaP.getNumberOfVariables();
			double [] [] transposedMatrix = new double [nbVariables] [nbDMUs];
			transposedMatrix = deaP.getTranspose(true);
			DEAPSolution returnSol = new DEAPSolution(nbDMUs, nbVariables);
			
			
			/* As the SBM optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.*/
			
			for (int DMUIndex = 0; DMUIndex < nbDMUs; DMUIndex++) {
				
				createAndSolveSBM(deaP, nbDMUs, nbVariables, transposedMatrix,
						returnSol, DMUIndex);
			}
			return returnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	
	
	private static void createAndSolveSBM(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex ) throws Exception {
		
		ArrayList<double[]> constraints = new ArrayList<double []>();
		/* There is one extra column for the scalar t used during the 
		 * Fractional to Linear SBM model transformaation.*/
		double[] objF = new double [nbDMUs + nbVariables + 1]; 
		int[] solverEqualityType;
		double[] rhs;
		
		//if model is assuming Variable RTS, we need to add an extra row for the convexity constraint
		if(deaP.getModelType() == ModelType.SBM) {
			rhs = new double [nbVariables + 1];
			solverEqualityType = new int [nbVariables + 1];
		}
		else if (deaP.getModelType() == ModelType.SBM_V) {
			rhs = new double [nbVariables + 2];
			solverEqualityType = new int [nbVariables + 2];
		}
		else {
			rhs = new double [nbVariables + 3];
			solverEqualityType = new int [nbVariables + 3];
		}
		
		
		createModel(deaP, nbDMUs, nbVariables, transposedMatrix, dmuIndex,
				constraints, objF, solverEqualityType, rhs);
		
		
		//SOLVE PROBLEM
		SolverResults sol = new SolverResults();
		
		try {
			sol = Lpsolve.solveLPProblem(constraints, objF, rhs, SolverObjDirection.MIN, solverEqualityType);
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
					 dmuIndex.toString());
		}
		catch (DEASolverException e2) {
			throw e2;
		}

		
		try {
			storeSolution(deaP, nbDMUs, nbVariables, returnSol, dmuIndex, sol);
		} catch (Exception e) {
			throw e;
		}
		
	}




	private static void storeSolution(DEAProblem deaP, int nbDMUs,
			int nbVariables, DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
		
		try {
			//STORE SOLUTION
			double t = sol.VariableResult[0];
			
			returnSol.setObjective(dmuIndex, sol.Objective);
			for(int VarIndex = 0; VarIndex < nbVariables; VarIndex++) {
				
				if(t != 0) {
					returnSol.setSlack(dmuIndex, VarIndex, sol.VariableResult[nbDMUs + 1 + VarIndex] / t);
				}
				else {
					returnSol.setSlack(dmuIndex, VarIndex, sol.VariableResult[nbDMUs + 1 + VarIndex]);
				}
				
				if(deaP.getVariableType(VarIndex) == VariableType.STANDARD_INPUT) {
					returnSol.setWeight(dmuIndex, VarIndex, sol.DualResult[VarIndex + 1]);
					returnSol.setProjection(dmuIndex, VarIndex,
							deaP.getDataMatrix(dmuIndex, VarIndex) - returnSol.getSlack(dmuIndex, VarIndex));
				}
				else {
					returnSol.setWeight(dmuIndex, VarIndex, sol.DualResult[VarIndex + 1] * -1);
					returnSol.setProjection(dmuIndex, VarIndex,
							deaP.getDataMatrix(dmuIndex, VarIndex) + returnSol.getSlack(dmuIndex, VarIndex));
				}
			}
			
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int LambdaIndex = 0; LambdaIndex < nbDMUs;LambdaIndex++){
				if(sol.VariableResult[LambdaIndex + 1] != 0 && t != 0){
					refSet.add(new NonZeroLambda(LambdaIndex, sol.VariableResult[LambdaIndex + 1] / t));
				}
				returnSol.setReferenceSet(dmuIndex, refSet);
			}
			
			SolverStatus.checkSolverStatus(returnSol, sol);
		}
		catch (Exception e) {
			throw e;
		}
	}




	private static void createModel(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix, int dmuIndex,
			ArrayList<double[]> constraints, double[] objF,
			int[] solverEqualityType, double[] rhs) throws Exception {
		
		try {
			objF[0] = 1;
			
			/* BUILD THE CONTRAINST MATRIX (loop through all variables + one row for
			 * extra constraint after Fractional => Linear transformation*/
			for (int varIndex = 0; varIndex <= nbVariables; varIndex++) {
				double[] constraintRow = new double[nbDMUs + nbVariables + 1];
				
				if(varIndex < nbVariables) {
					//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
						constraintRow[0] = transposedMatrix[varIndex] [dmuIndex] * -1;
					//Copy rest of the data matrix
					System.arraycopy(transposedMatrix[varIndex], 0, constraintRow, 1, nbDMUs);
					//and slacks
					if (deaP.getVariableType(varIndex) == VariableType.STANDARD_INPUT) {
						constraintRow[nbDMUs + 1 + varIndex] = -1;
					}
					else {
						constraintRow[nbDMUs + 1 + varIndex] = 1;
					}
					constraints.add(constraintRow);
					
					//Build RHS
					rhs[varIndex] = 0;
					solverEqualityType[varIndex] = LpSolve.EQ;
					
				}
				else {
					/* Last row (added constraint after Fractional => Linear transformation).
					 * Necessary for all SBM models*/
					constraintRow[0] = 1;
					for (int varPos = 0; varPos < nbVariables; varPos++) {
						if(deaP.getVariableType(varPos) != VariableType.STANDARD_INPUT) {
							if(transposedMatrix[varPos] [dmuIndex] * deaP.getNumberOfOutputs() != 0) {
								constraintRow[nbDMUs + 1 + varPos] = -1
								/ (transposedMatrix[varPos] [dmuIndex] * deaP.getNumberOfOutputs());
							}
							else {
								constraintRow[nbDMUs + 1 + varPos] = 0;	
							}
						}
						else {
							if(transposedMatrix[varPos] [dmuIndex] * deaP.getNumberOfInputs() != 0) {
								objF[nbDMUs + 1 + varPos] = 1
								/ (transposedMatrix[varPos] [dmuIndex] * deaP.getNumberOfInputs());
							}
							else {
								objF[nbDMUs + 1 + varPos] = 1;
							}
						}
					}
					constraints.add(constraintRow);
					
					//Build last row of RHS
					rhs[varIndex] = 1;
					solverEqualityType[varIndex] = LpSolve.EQ;
					
				}
				
			} //End loop through all variables (rows of constraint matrix) + 1 row (added constraint)
			
			
			//OPTIONAL ROWS
			if(deaP.getModelType() == ModelType.SBM_V) {
				//Build convexity constraint. This is the ONLY difference with the SBMmodel
				double[] constraintRow = new double[nbDMUs + nbVariables + 1];
				constraintRow[0] = 1;
				for (int dmuPos = 1; dmuPos <= nbDMUs; dmuPos++) {
					constraintRow[dmuPos] = -1;
				}
				constraints.add(constraintRow);
				//RHS already 0.
				solverEqualityType[nbVariables + 1] = LpSolve.EQ;
			}
			
			if(deaP.getModelType() == ModelType.SBM_GRS) {
				double[] tempConstraintRow = new double[nbDMUs + nbVariables + 1];
				for (int dmuPos = 1; dmuPos <= nbDMUs; dmuPos++) {
					tempConstraintRow[dmuPos] = -1;
				}
				//Lower Bounds (General RTS)
				double[] constraintRow = new double[nbDMUs + nbVariables + 1];
				System.arraycopy(tempConstraintRow, 1, constraintRow, 1, nbDMUs);
				constraintRow[0] = deaP.getRTSLowerBound();
				constraints.add(constraintRow);
				rhs[nbVariables + 1] = 0;
				solverEqualityType[nbVariables + 1] = LpSolve.LE;
				
				//Upper Bounds (General RTS)
				constraintRow = new double[nbDMUs + nbVariables + 1];
				System.arraycopy(tempConstraintRow, 1, constraintRow, 1, nbDMUs);
				constraintRow[0] = deaP.getRTSUpperBound();
				constraints.add(constraintRow);
				rhs[nbVariables + 2] = 0;
				solverEqualityType[nbVariables + 2] = LpSolve.GE;
				}
		}
		catch (Exception e) {
			throw e;
		}
	}
}
