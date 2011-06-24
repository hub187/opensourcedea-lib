
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
//import java.util.Arrays;
//import java.util.Arrays;
import linearSolver.*;
import lpsolve.LpSolve;



/**
 * The class implementing the NC (Input and Output oriented) models as well as the NCIV and NCOV models.
 * These models solve the NC problem in ONE OPTIMISATION STAGE.
 * The first column is the theta so the length of the weights array is NbVariable + 1.
 * For the NCIV and NCOV models, the first weight of the weight array is the variable u0 (corresponding to the convxity constraint).
 * <\br>
 * @author Hubert Virtos
 *
 */
public  class NC_ND {

	/**
	 * The method solving the DEA Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveNC(DEAProblem deaP) throws Exception, DEAException {
		
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
		
		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVariables = deaP.getNumberOfVariables();
			double [] [] transposedMatrix = new double [nbVariables] [nbDMUs];
			transposedMatrix = deaP.getTranspose(false); //Want the Transposed (not negative transpose)
			DEAPSolution returnSol = new DEAPSolution(nbDMUs, nbVariables);
	
			/* As the NC optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the NC model is solved in two phases (1st to min Theta, 2nd to max slacks).
			 * The problem will consequently be solved for each DMUs for Phase I and
			 * solved again for each DMUs for Phase II.*/
	
			for (int dmuIndex = 0; dmuIndex < nbDMUs; dmuIndex++) {
				
				  createAndSolveNC(deaP, nbDMUs, nbVariables, transposedMatrix,
						returnSol, dmuIndex);
			}		
			return returnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createAndSolveNC(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex) throws Exception {
		
		ArrayList<double[]> constraints = new ArrayList<double []>();
		double[] objF = new double [nbDMUs + nbVariables + 1];
		double[] rhs1;
		int[] solverEqualityType1;
//		double[] rhs2;
//		int[] solverEqualityType2;
		
		
		  //////////////////////////////
		 //		Solve Phase I		 //
		//////////////////////////////
		
		if(deaP.getModelRTS() == ReturnToScale.CONSTANT){
			rhs1 = new double [nbVariables];
			solverEqualityType1 = new int[nbVariables];
		}
		else if(deaP.getModelRTS() == ReturnToScale.VARIABLE){ /*Need an extra row for convexity contraint*/
			rhs1 = new double [nbVariables + 1];
			solverEqualityType1 = new int[nbVariables + 1];
		}
		else { /*IRS, DRS, GRS need two extra row for convexity constraints*/
			rhs1 = new double [nbVariables + 2];
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
					+ dmuIndex.toString()
					+". The error was: " + e1.getMessage());
		}
		catch (DEASolverException e2) {
			throw new DEASolverException("The problem could not be solved properly at DMU Index: "
					+ dmuIndex.toString()
					+ ". The error was: " + e2.getMessage());
		}

		
		storePhaseOneInformation(deaP, returnSol, dmuIndex, sol);
		
		
//		  /////////////////////////////
//		 //		Solve Phase II		//
//		/////////////////////////////
//		
//		/* The only things that are needed for Phase II are to:
//		 * - add an extra Constraint Row to Constraints in order to ensure Theta Phase I
//		 *   is not changed during Phase II Optimisation.
//		 * - add the corresponding Theta to the RHS Array
//		 * - change the Objective Function accordingly (all 1 on Slacks, all others coeff = 0).*/
//		
//		rhs2 = new double[rhs1.length + 1];
//		solverEqualityType2 = new int[solverEqualityType1.length + 1];
//
//		
//		createPhaseTwoModel(deaP, nbDMUs, nbVariables, constraints, objF, rhs1,
//				rhs2, solverEqualityType1, solverEqualityType2, sol);
//		
//		
//		//Solve the Phase II Problem
//		try {
//			sol = Lpsolve.solveLPProblem(constraints, objF, rhs2, SolverObjDirection.MAX,
//					solverEqualityType2);
//		}
//		catch (ProblemNotSolvedProperly e1) {
//			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
//					 dmuIndex.toString());
//		}
//		catch (DEASolverException e2) {
//			throw e2;
//		}
//
//		
//		try {
//			storePhaseTwoInformation(deaP, nbDMUs, nbVariables, returnSol, dmuIndex, sol);
//		}
//		catch (Exception e) {
//			throw e;
//		}

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
						if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT &&
								deaP.getVariableType(varIndex) == VariableType.STANDARD) {
							constraintRow[0] = transposedMatrix[varIndex] [dmuIndex] * -1;
						}
						else { //Either a non-controllable input or output or a standard output. In all cases not timed by theta so 0
							constraintRow[0] = 0;
						}
					}
					else { //Model is Output Oriented
						if (deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT &&
								deaP.getVariableType(varIndex) == VariableType.STANDARD) {
							constraintRow[0] = transposedMatrix[varIndex] [dmuIndex] * -1;
						}
						else { //Either a non-controllable input or output or a standard output. In all cases not timed by theta so 0
							constraintRow[0] = 0;
						}
					}
				}
				catch (Exception e) {
					throw e;
				}

				//Copy rest of the data matrix (the row corresponding to varIndex)
				System.arraycopy(transposedMatrix[varIndex], 0, constraintRow, 1, nbDMUs);
				
				//and slacks
				if(deaP.getModelType() == ModelType.NC_I || deaP.getModelType() == ModelType.NC_I_DRS ||
						deaP.getModelType() == ModelType.NC_I_GRS || deaP.getModelType() == ModelType.NC_I_IRS ||
						deaP.getModelType() == ModelType.NC_I_V || deaP.getModelType() == ModelType.NC_O ||
						deaP.getModelType() == ModelType.NC_O_DRS || deaP.getModelType() == ModelType.NC_O_GRS ||
						deaP.getModelType() == ModelType.NC_O_IRS || deaP.getModelType() == ModelType.NC_O_V) {
					if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT &&
							deaP.getVariableType(varIndex) == VariableType.STANDARD) {
						constraintRow[nbDMUs + 1 + varIndex] = 1;
					}
					else if (deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT &&
							deaP.getVariableType(varIndex) == VariableType.STANDARD) {
						constraintRow[nbDMUs + 1 + varIndex] = -1;
					}
				}
				else { //Non discretionary models
					if(deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT &&
							(deaP.getVariableType(varIndex) == VariableType.STANDARD || 
									deaP.getVariableType(varIndex) == VariableType.NON_DISCRETIONARY)) {
						constraintRow[nbDMUs + 1 + varIndex] = 1;
					}
					else if (deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT &&
							(deaP.getVariableType(varIndex) == VariableType.STANDARD || 
									deaP.getVariableType(varIndex) == VariableType.NON_DISCRETIONARY)) {
						constraintRow[nbDMUs + 1 + varIndex] = -1;
					}
				}
				
				//Add the row to the Constraints ArrayList
				constraints.add(constraintRow);
			
			//Build RHS & SolverEqualityTypes
				try {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT &&
								deaP.getVariableType(varIndex) == VariableType.STANDARD) {
							rhs1[varIndex] = 0;
							solverEqualityType1[varIndex] = LpSolve.EQ;
						}
						else {
							rhs1[varIndex] = transposedMatrix[varIndex] [dmuIndex];
							solverEqualityType1[varIndex] = LpSolve.EQ;
						}
					}
					else { //Model is Output Oriented
						if (deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT &&
								deaP.getVariableType(varIndex) == VariableType.STANDARD) {
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

		if(deaP.getModelRTS() != ReturnToScale.CONSTANT) {
			//Build the row corresponding to the convexity constraint
			constraintRow = new double[nbDMUs + nbVariables + 1];
			for(int VarIndex = 1; VarIndex <= nbDMUs; VarIndex++){
				constraintRow[VarIndex] = 1;
			}
			if(deaP.getModelRTS() == ReturnToScale.VARIABLE) {
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
		}
		
		//Build Objective Function (Theta column is assigned the weight 1. All the other columns are left to 0).
		objF[0] = 1;
	}

	
	
	private static void storePhaseOneInformation(DEAProblem deaP,
			DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
		
		//Collect information from Phase I (Theta)
		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVariables = deaP.getNumberOfVariables();
			
			//OBJECTIVES
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				returnSol.setObjective(dmuIndex, sol.Objective);
			}
			else {
				if(sol.Objective != 0) {
					returnSol.setObjective(dmuIndex, 1 / sol.Objective);
				}
				else {
					returnSol.setObjective(dmuIndex, 0);
				}
			}
			
			//WEIGHTS
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				returnSol.setWeights(dmuIndex, sol.Weights);
			}
			else {
				returnSol.setWeights(dmuIndex, new double[sol.Weights.length]);
				for(int k = 0; k < sol.Weights.length; k++) {
					returnSol.setWeight(dmuIndex, k, sol.Weights[k] * -1);
				}
			}
			
			//REFERENCE SET
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int lambdaPos = 0; lambdaPos < nbDMUs; lambdaPos++) {
				if(sol.VariableResult[lambdaPos + 1] != 0) {
					refSet.add(new NonZeroLambda(lambdaPos, sol.VariableResult[lambdaPos + 1]));
				}
			}
			returnSol.setReferenceSet(dmuIndex, refSet);
			
			//SLACKS
			returnSol.setSlackArrayCopy(dmuIndex, sol.VariableResult, nbDMUs + 1, nbVariables);
			
			//PROJECTIONS
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						
						if(deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT &&
								deaP.getVariableType(varIndex) == VariableType.STANDARD) {
							returnSol.setProjection(dmuIndex, varIndex, returnSol.getObjective(dmuIndex)
									* deaP.getDataMatrix(dmuIndex, varIndex)
									- returnSol.getSlack(dmuIndex, varIndex));
						}
						else if(deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT &&
								deaP.getVariableType(varIndex) != VariableType.STANDARD) {
							//Non-discretionary and Non-controllable models. Slacks will be 0 for NC models
							returnSol.setProjection(dmuIndex, varIndex,
									deaP.getDataMatrix(dmuIndex, varIndex)- returnSol.getSlack(dmuIndex, varIndex));
						}
						else { //Output variable
							returnSol.setProjection(dmuIndex, varIndex,
									deaP.getDataMatrix(dmuIndex, varIndex)
									+ returnSol.getSlack(dmuIndex, varIndex));
						}
					}
					else { //Set projections for output oriented model
						
						if(deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT &&
								deaP.getVariableType(varIndex) == VariableType.STANDARD) {
							if(returnSol.getObjective(dmuIndex) != 0){
								returnSol.setProjection(dmuIndex, varIndex,
										(1 / returnSol.getObjective(dmuIndex))
										* deaP.getDataMatrix(dmuIndex, varIndex)
										+ returnSol.getSlack(dmuIndex, varIndex));
							}
							else {
								returnSol.setProjection(dmuIndex, varIndex,
										deaP.getDataMatrix(dmuIndex, varIndex));
							}
						}
						else if(deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT &&
								deaP.getVariableType(varIndex) != VariableType.STANDARD) {
							//NC and ND models. Slacks will be 0 for NC models.
							returnSol.setProjection(dmuIndex, varIndex,
									deaP.getDataMatrix(dmuIndex, varIndex)
									+ returnSol.getSlack(dmuIndex, varIndex));
						}
						else {
							//Input variable
							returnSol.setProjection(dmuIndex, varIndex,
									deaP.getDataMatrix(dmuIndex, varIndex)
									- returnSol.getSlack(dmuIndex, varIndex));
						}
					}
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		SolverStatus.checkSolverStatus(returnSol, sol);
	}
	
	
	
//	private static void storePhaseTwoInformation(DEAProblem deaP, int nbDMUs,
//			int nbVariables, DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
//		
//		//Collect information from Phase II (Theta)
//		try {
//			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
//			for(int lambdaPos = 0; lambdaPos < nbDMUs; lambdaPos++) {
//				if(sol.VariableResult[lambdaPos + 1] != 0) {
//					refSet.add(new NonZeroLambda(lambdaPos, sol.VariableResult[lambdaPos + 1]));
//				}
//			}
//			returnSol.setReferenceSet(dmuIndex, refSet);
//			returnSol.setSlackArrayCopy(dmuIndex, sol.VariableResult, nbDMUs + 1, nbVariables);
//	
//			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
//					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
//						if(deaP.getVariableType(varIndex) == VariableType.STANDARD_INPUT) {
//							//Projections
//							returnSol.setProjection(dmuIndex, varIndex, returnSol.getObjective(dmuIndex)
//									* deaP.getDataMatrix(dmuIndex, varIndex)
//									- returnSol.getSlack(dmuIndex, varIndex));
//						}
//						else {
//							//Projections
//							returnSol.setProjection(dmuIndex, varIndex,
//									deaP.getDataMatrix(dmuIndex, varIndex)
//									+ returnSol.getSlack(dmuIndex, varIndex));
//						}
//					}
//					else {
//						if(deaP.getVariableType(varIndex) == VariableType.STANDARD_OUTPUT) {
//							//Projections
//							if(returnSol.getObjective(dmuIndex) != 0){
//								returnSol.setProjection(dmuIndex, varIndex,
//										(1 / returnSol.getObjective(dmuIndex))
//										* deaP.getDataMatrix(dmuIndex, varIndex)
//										+ returnSol.getSlack(dmuIndex, varIndex));
//							}
//							else {
//								returnSol.setProjection(dmuIndex, varIndex,
//										returnSol.getSlack(dmuIndex, varIndex));
//							}
//						}
//						else {
//							//Projections
//							returnSol.setProjection(dmuIndex, varIndex,
//									deaP.getDataMatrix(dmuIndex, varIndex)
//									- returnSol.getSlack(dmuIndex, varIndex));
//						}
//					}
//			}
//	
//			SolverStatus.checkSolverStatus(returnSol, sol);
//			}
//		catch (Exception e) {
//			throw e;
//		}
//	}
//
//
//	private static void createPhaseTwoModel(DEAProblem deaP, int NbDMUs,
//			int NbVariables, ArrayList<double[]> Constraints, double[] ObjF,
//			double[] RHS1, double[] RHS2, int[] SolverEqualityType1,
//			int[] SolverEqualityType2, SolverResults Sol) throws Exception {
//		
//		double[] ConstraintRow;
//		
//		//Changing Constraint Matrix
//		ConstraintRow = new double[NbDMUs + NbVariables + 1];
//		ConstraintRow[0] = 1;
//		Constraints.add(ConstraintRow);
//				
//		RHS2[RHS2.length - 1] = Sol.Objective;
//		System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0,
//				SolverEqualityType1.length);
//		SolverEqualityType2[SolverEqualityType2.length - 1] = LpSolve.EQ;
//		
//		//Change Objective Function
//		Arrays.fill(ObjF,0);
//		for (int j = NbDMUs + 1; j <= NbDMUs + NbVariables; j++) {
//			try{
//			if(deaP.getVariableType(j - NbDMUs -1) == VariableType.STANDARD_INPUT ||
//					deaP.getVariableType(j - NbDMUs -1) == VariableType.STANDARD_OUTPUT) {
//				ObjF[j] = 1;
//			}
//			}
//			catch (Exception e) {
//				throw e;
//			}
//			
//		}
//	}

}
