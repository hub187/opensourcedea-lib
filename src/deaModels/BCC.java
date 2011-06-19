
/*	<DEASolver (googleproject opensourcedea) is a java DEA solver.>
    Copyright (C) <2010>  <Hubert Paul Bernard VIRTOS>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    
    @author Hubert Paul Bernard VIRTOS
    @version 0.1 2011-02-04
*/

package deaModels;

import dea.*;

import java.util.ArrayList;
import java.util.Arrays;
import linearSolver.*;
import lpsolve.LpSolve;



/**
 * The class implementing the BBC (Input and Output oriented) models as well as the IRS, DRS and GRS models.
 * The first column is the theta so the length of the weights array is NbVariable + 1.
 * The first weight of the weight array is the variable u0 (corresponding to the convexity constraint). 
 * If the model is under IRS, DRS or GRS, there will be TWO weights before the variable weights
 * (corresponding respectively to the Lower Bound and Upper Bound constraints).
 * <\br>
 * @author Hubert Virtos
 *
 */
public  class BCC {

	/**
	 * The method solving the DEA Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveBCC(DEAProblem deaP) throws Exception, DEAException {
		
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
		
		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVariables = deaP.getNumberOfVariables();
			double [] [] transposedMatrix = new double [nbVariables] [nbDMUs];
			transposedMatrix = deaP.getTranspose(true); //Want the negative Transposed
			DEAPSolution returnSol = new DEAPSolution(nbDMUs, nbVariables);
	
			/* As the BBC optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the BBC model is solved in two phases.
			 * The problem will consequently be solved for each DMUs for Phase I and
			 * solved again for each DMUs for Phase II.*/
	
			for (int dmuIndex = 0; dmuIndex < nbDMUs; dmuIndex++) {
				
				  createAndSolveBCC(deaP, nbDMUs, nbVariables, transposedMatrix,
						returnSol, dmuIndex);
			}		
			return returnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createAndSolveBCC(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex) throws Exception {
		
		ArrayList<double[]> constraints = new ArrayList<double []>();
		double[] objF = new double [nbDMUs + nbVariables + 1];
		double[] rhs1;
		double[] rhs2;
		int[] solverEqualityType1;
		int[] solverEqualityType2;
		
		
		  //////////////////////////////
		 //		Solve Phase I		 //
		//////////////////////////////
		
		if(deaP.getModelType() == ModelType.BCC_I ||
				deaP.getModelType() == ModelType.BCC_O){
			rhs1 = new double [nbVariables + 1]; //RHS Phase I
			solverEqualityType1 = new int[nbVariables + 1];
		}
		else /* GRS, IRS, DRS, need one extra row for second convexity constraint*/ {
			rhs1 = new double [nbVariables + 2]; //RHS Phase I
			solverEqualityType1 = new int[nbVariables + 2];
		}
		
		createPhaseOneModel(deaP, nbDMUs, nbVariables, transposedMatrix, dmuIndex,
				constraints, objF, rhs1, solverEqualityType1);
		
		
		//Solve
		SolverResults sol = new SolverResults();
		try {
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				sol = Lpsolve.solveLPProblem(constraints, objF, rhs1, SolverObjDirection.MIN,
						solverEqualityType1);
			}
			else {
				sol = Lpsolve.solveLPProblem(constraints, objF, rhs1, SolverObjDirection.MAX,
						solverEqualityType1);
			}
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: "
					+ dmuIndex.toString());
		}
		catch (MissingData e2) {
			throw e2;
		}
		catch (DEASolverException e3) {
			throw e3;
		}

		
		storePhaseOneInformation(deaP, returnSol, dmuIndex, sol);
		
		
		  /////////////////////////////
		 //		Solve Phase II		//
		/////////////////////////////
		
		/* The only things that are needed for Phase II are to:
		 * - add an extra Constraint Row to Constraints in order to ensure Theta Phase I
		 *   is not changed during Phase II Optimisation.
		 * - add the corresponding Theta to the RHS Array
		 * - change the Objective Function accordingly (all 1 on Slacks, all others coeff = 0).*/
		
		if(deaP.getModelType() == ModelType.BCC_I ||
				deaP.getModelType() == ModelType.BCC_O) {
			/*+1 row for convexity constraints, + 1 row for theta*/
			rhs2 = new double[nbVariables + 2];
			solverEqualityType2 = new int[nbVariables + 2];
		}
		else {
			/*+2 rows for convexity constraints (U & L), + 1 row for theta*/
			rhs2 = new double[nbVariables + 3];
			solverEqualityType2 = new int[nbVariables + 3];
		}
		
		createPhaseTwoModel(deaP, nbDMUs, nbVariables, constraints, objF, rhs1,
				rhs2, solverEqualityType1, solverEqualityType2, sol);
		
		
		//Solve the Phase II Problem
		try {
			sol = Lpsolve.solveLPProblem(constraints, objF, rhs2, SolverObjDirection.MAX,
					solverEqualityType2);
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
					 dmuIndex.toString());
		}
		catch (DEASolverException e2) {
			throw e2;
		}

		
		try {
			storePhaseTwoInformation(deaP, nbDMUs, nbVariables, returnSol, dmuIndex, sol);
		}
		catch (Exception e) {
			throw e;
		}

	}


	
	private static void storePhaseTwoInformation(DEAProblem deaP, int nbDMUs,
			int nbVariables, DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
		
		//Collect information from Phase II (Theta)
		try {
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int lambdaPos = 0; lambdaPos < nbDMUs; lambdaPos++) {
				if(sol.VariableResult[lambdaPos + 1] != 0) {
					refSet.add(new NonZeroLambda(lambdaPos, sol.VariableResult[lambdaPos + 1]));
				}
			}
			returnSol.setReferenceSet(dmuIndex, refSet);
			returnSol.setSlackArrayCopy(dmuIndex, sol.VariableResult, nbDMUs + 1, nbVariables);
	
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if(deaP.getVariableType(varIndex) == VariableType.STANDARD_INPUT) {
							//Projections
							returnSol.setProjection(dmuIndex, varIndex, returnSol.getObjective(dmuIndex)
									* deaP.getDataMatrix(dmuIndex, varIndex)
									- returnSol.getSlack(dmuIndex, varIndex));
						}
						else {
							//Projections
							returnSol.setProjection(dmuIndex, varIndex,
									deaP.getDataMatrix(dmuIndex, varIndex)
									+ returnSol.getSlack(dmuIndex, varIndex));
						}
					}
					else {
						if(deaP.getVariableType(varIndex) == VariableType.STANDARD_OUTPUT) {
							//Projections
							if(returnSol.getObjective(dmuIndex) != 0){
								returnSol.setProjection(dmuIndex, varIndex,
										(1 / returnSol.getObjective(dmuIndex))
										* deaP.getDataMatrix(dmuIndex, varIndex)
										+ returnSol.getSlack(dmuIndex, varIndex));
							}
							else {
								returnSol.setProjection(dmuIndex, varIndex,
										returnSol.getSlack(dmuIndex, varIndex));
							}
						}
						else {
							//Projections
							returnSol.setProjection(dmuIndex, varIndex,
									deaP.getDataMatrix(dmuIndex, varIndex)
									- returnSol.getSlack(dmuIndex, varIndex));
						}
					}
			}
	
			SolverStatus.checkSolverStatus(returnSol, sol);
			}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createPhaseTwoModel(DEAProblem deaP, int NbDMUs,
			int NbVariables, ArrayList<double[]> Constraints, double[] ObjF,
			double[] RHS1, double[] RHS2, int[] SolverEqualityType1,
			int[] SolverEqualityType2, SolverResults Sol) {
		
		double[] ConstraintRow;
		
		//Changing Constraint Matrix
		ConstraintRow = new double[NbDMUs + NbVariables + 1];
		ConstraintRow[0] = 1;
		Constraints.add(ConstraintRow);
				
		
		//Changing RHS & SolverEqTypes
		if(deaP.getModelType() == ModelType.BCC_I ||
				deaP.getModelType() == ModelType.BCC_O) {
			System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
			RHS2[NbVariables + 1] = Sol.Objective;
			System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0,
					SolverEqualityType1.length);
			SolverEqualityType2[NbVariables + 1] = LpSolve.EQ;
		}
		else {
			System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
			RHS2[NbVariables + 2] = Sol.Objective;
			System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0,
					SolverEqualityType1.length);
			SolverEqualityType2[NbVariables + 2] = LpSolve.EQ;
		}
		
	
		//Change Objective Function
		Arrays.fill(ObjF,0);
		for (int j = NbDMUs + 1; j <= NbDMUs + NbVariables; j++) {
			ObjF[j] = 1;
		}
	}


	private static void storePhaseOneInformation(DEAProblem deaP,
			DEAPSolution returnSol, int i, SolverResults sol) throws Exception {
		//Collect information from Phase I (Theta)
		try {
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				returnSol.setObjective(i, sol.Objective);
			}
			else {
				if(sol.Objective != 0) {
					returnSol.setObjective(i, 1 / sol.Objective);
				}
				else {
					returnSol.setObjective(i, 0);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				returnSol.setWeights(i, sol.Weights);
			}
			else {
				returnSol.setWeights(i, new double[sol.Weights.length]);
				for(int k = 0; k < sol.Weights.length; k++) {
					returnSol.setWeight(i, k, sol.Weights[k] * -1);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
		SolverStatus.checkSolverStatus(returnSol, sol);
	}


	private static void createPhaseOneModel(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix, int dmuIndex,
			ArrayList<double[]> constraints, double[] objF, double[] rhs1,
			int[] solverEqualityType1) throws Exception {
		
		double[] constraintRow;
		for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
						
			//Build the Constraint Matrix, row by row
			constraintRow = new double[nbDMUs + nbVariables + 1];
			//First column (input values for  DMU under observation (DMUIndex) * -1; 0 for outputs)
				try {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if (deaP.getVariableType(varIndex) == VariableType.STANDARD_INPUT) {
							constraintRow[0] = transposedMatrix[varIndex] [dmuIndex] * -1;
						}
						else  {
							constraintRow[0] = 0;
						}
					}
					else {
						if (deaP.getVariableType(varIndex) == VariableType.STANDARD_OUTPUT) {
							constraintRow[0] = transposedMatrix[varIndex] [dmuIndex] * -1;
						}
						else  {
							constraintRow[0] = 0;
						}
					}
				}
				catch (Exception e) {
					throw e;
				}

				//Copy rest of the data matrix
				System.arraycopy(transposedMatrix[varIndex], 0, constraintRow, 1, nbDMUs);
				
				//and slacks
				if (deaP.getVariableType(varIndex) == VariableType.STANDARD_INPUT) {
					constraintRow[nbDMUs + 1 + varIndex] = -1;
				}
				else {
					constraintRow[nbDMUs + 1 + varIndex] = 1;
				}
				
				//Add the row to the Constraints ArrayList
				constraints.add(constraintRow);
			
			//Build RHS & SolverEqualityTypes
				try {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if (deaP.getVariableType(varIndex) == VariableType.STANDARD_INPUT) {
							rhs1[varIndex] = 0;
							solverEqualityType1[varIndex] = LpSolve.EQ;
						}
						else {
							rhs1[varIndex] = transposedMatrix[varIndex] [dmuIndex];
							solverEqualityType1[varIndex] = LpSolve.EQ;
						}
					}
					else {
						if (deaP.getVariableType(varIndex) == VariableType.STANDARD_OUTPUT) {
							rhs1[varIndex] = 0;
							solverEqualityType1[varIndex] = LpSolve.EQ;
						}
						else {
							rhs1[varIndex] = transposedMatrix[varIndex] [dmuIndex];
							solverEqualityType1[varIndex] = LpSolve.EQ;
						}
					}
				}
				catch (Exception e) {
					throw e;
				}
			} //finished looping through all variables

		
		//Build the row corresponding to the convexity constraint
		constraintRow = new double[nbDMUs + nbVariables + 1];
		for(int VarIndex = 1; VarIndex <= nbDMUs; VarIndex++){
			constraintRow[VarIndex] = 1;
		}
		if(deaP.getModelType() == ModelType.BCC_I ||
				deaP.getModelType() == ModelType.BCC_O) {
			constraints.add(constraintRow);
			rhs1[nbVariables] = 1;
			solverEqualityType1[nbVariables] = LpSolve.EQ;
		}
		else /*In this case the model is a general, increasing or decreasing model*/ {
			constraints.add(constraintRow);
			rhs1[nbVariables] = deaP.getRTSLowerBound();
			solverEqualityType1[nbVariables] = LpSolve.GE;
			constraints.add(constraintRow);
			rhs1[nbVariables + 1] = deaP.getRTSUpperBound();
			solverEqualityType1[nbVariables + 1] = LpSolve.LE;
		}
		
		//Build Objective Function (Theta column is assigned the weight 1. All the other columns are left to 0).
		objF[0] = 1;
	}

}
