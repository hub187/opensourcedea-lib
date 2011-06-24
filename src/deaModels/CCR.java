
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
 * The class implementing the CCR (Input and Output oriented) models.
 *
 *<p>
 *The linear problem of the Model is as follows:
 *<p>
 * <center>
 * <table border = "1">
 * <tr>
 * 		<td>Variable</td>
 * 		<td>Theta</td>
 * 		<td>Lambda 1</td>
 * 		<td>...</td>
 * 		<td>Lambda n</td>
 * 		<td>Slack 1</td>
 * 		<td>...</td>
 * 		<td>Slack p</td>
 * 		<td>DIR</td>
 * 		<td>RHS</td>
 * </tr>
 * <tr>
 * 		<td>Obj Coeff</td>
 * 		<td>1</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td>0</td>
 * 		<td></td>
 * 		<td></td>
 * </tr>
 * <tr>
 * 		<td>Input 1</td>
 * 		<td>-Input 1,1</td>
 * 		<td>Input 1,1</td>
 * 		<td>...</td>
 * 		<td>Input 1, n</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>0</td>
 * 		<td>E</td>
 * 		<td>0</td>
 * </tr>
 * <tr>
 * 		<td>Input i</td>
 * 		<td>-Input i, 1</td>
 * 		<td>Input i, 1</td>
 * 		<td>...</td>
 * 		<td>Input i, n</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>0</td>
 * 		<td>E</td>
 * 		<td>0</td>
 * </tr>
 * <tr>
 * 		<td>Ouput p</td>
 * 		<td>0</td>
 * 		<td>Output p, 1</td>
 * 		<td>...</td>
 * 		<td>Output p, n</td>
 * 		<td>0</td>
 * 		<td>...</td>
 * 		<td>-1</td>
 * 		<td>E</td>
 * 		<td>Output p, 1</td>
 * </tr>
 * </table>
 * </center>
 * <p>
 *  Where the input values of the DMU being optimised are put in the Theta column and timed by -1
 *  (e.g. -Inputi 1 being the ith input of DMU1).
 * </p>
 * @author Hubert Virtos
 *
 */
public  class CCR {

	/**
	 * The method solving the CCR Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveCCR(DEAProblem deaP) throws Exception {
		
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVariables = deaP.getNumberOfVariables();
			double [] [] transposedMatrix = new double [nbVariables] [nbDMUs];
			transposedMatrix = deaP.getTranspose(true); //Want the negative Transposed
			DEAPSolution returnSol = new DEAPSolution(nbDMUs, nbVariables);
			
						
			
			
			/* As the CCR optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the CCR model is solved in two phases.
			 * The problem will consequently be solved for each DMUs for Phase I and
			 * solved again for each DMUs for Phase II.*/
			
	
			
			for (int dmuIndex = 0; dmuIndex < nbDMUs; dmuIndex++) {
				
				  createAndSolveCCR(deaP, nbDMUs, nbVariables, transposedMatrix,
						returnSol, dmuIndex);
			}
			return returnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createAndSolveCCR(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex) throws DEASolverException, Exception {
		/////////////////////////////
		 //		Solve Phase I		//
		/////////////////////////////
		
		//Build model for Phase I

		/*
		 * The model is built with an array as follows:
		 * 
		 * 	Variable:		Theta	  Lambda 1   .....	  Lambda n      Slack 1  ...  Slack p		DIR		RHS
		 *  
		 *  Obj Coeff:		  1		     0       .....       0		      0      ...     0			 
		 *  
		 *  DMU1 (input)   -Input1 1  Input1 1   .....     Input1 n       1      ...     0			 E		 0
		 *  DMUi (input)   -Inputi 1  Inputi 1   .....     Inputi n       0      ...     0			 E		 0
		 *  DMUp (output)     0	      Outputp 1  .....     Outputp n      0      ...    -1			 E	  Output p 1
		 * 
		 * 
		 *  Where the input values of the DMU being optimised are put in the Theta column and timed by -1
		 *  (e.g. -Inputi 1 being the ith input of DMU1).
		 */
		
		
		ArrayList<double[]> constraints = new ArrayList<double []>();
		double[] objF = new double [nbDMUs + nbVariables + 1];
		double[] rhs1 = new double [nbVariables]; //RHS Phase I
		double[] rhs2;// = new double [NbVariables]; //RHS Phase II
		int[] solverEqualityType = new int[nbVariables];

		createPhaseOneModel(deaP, nbDMUs, nbVariables, transposedMatrix, dmuIndex,
				constraints, objF, rhs1, solverEqualityType);
		
		
		//Solve
		SolverResults sol = new SolverResults();
		
		try {
			sol = Lpsolve.solveLPProblem(constraints, objF, rhs1, SolverObjDirection.MIN, solverEqualityType);
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
		
		
		  /////////////////////////////
		 //		Solve Phase II		//
		/////////////////////////////
		/* The only things that are needed for Phase II are to:
		 * - add an extra Constraint Row to Constraints in order to ensure Theta Phase I
		 *   is not changed during Phase II Optimisation.
		 * - add the corresponding Theta to the RHS Array
		 * - change the Objective Function accordingly (all 1 on Slacks, all others coeff = 0).*/
		
		rhs2 = new double[nbVariables + 1];
		solverEqualityType = new int[nbVariables + 1];
		
		createPhaseTwoModel(nbDMUs, nbVariables, constraints, objF, rhs1, rhs2,
				solverEqualityType, sol);
		
		//Solve the Phase II Problem
		try {
			sol = Lpsolve.solveLPProblem(constraints, objF, rhs2, SolverObjDirection.MAX, solverEqualityType);
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
		
		
		try {
			storePhaseTwoInformation(deaP, nbDMUs, nbVariables, returnSol, dmuIndex, sol);
		} catch (Exception e) {
			throw e;
		}
	}


	private static void createPhaseTwoModel(int nbDMUs, int nbVariables,
			ArrayList<double[]> constraints, double[] objF, double[] rhs1,
			double[] rhs2, int[] solverEqualityType, SolverResults sol) {
		//Changing Constraint Matrix
		double[] constraintRow = new double[nbDMUs + nbVariables + 1];
		constraintRow[0] = 1;
		constraints.add(constraintRow);
		
		//Changing RHS & SolverEqTypes
		System.arraycopy(rhs1, 0, rhs2, 0, rhs1.length);
		rhs2[nbVariables] = sol.Objective;
		
		for(int varIndex = 0; varIndex < nbVariables + 1; varIndex++) {
			solverEqualityType[varIndex] = LpSolve.EQ;
		}
		
		
		//Change Objective Function
		Arrays.fill(objF,0);
		for (int j = nbDMUs + 1; j <= nbDMUs + nbVariables; j++) {
			objF[j] = 1;
		}
	}


	private static void storePhaseOneInformation(DEAProblem deaP,
			DEAPSolution returnSol, int i, SolverResults sol) {
		//Collect information from Phase I (Theta)
		if(deaP.getModelType() == ModelType.CCR_I) {
			returnSol.setObjective(i, sol.Objective);
			returnSol.setWeights(i, sol.Weights);
		}
		else {
			if(sol.Objective != 0) {
				returnSol.setObjective(i, 1 / sol.Objective);
				for(int w = 0; w < sol.Weights.length; w++) {
					returnSol.setWeight(i, w, sol.Weights[w] / sol.Objective);
				}
			}
			else {
				returnSol.setObjective(i, 0);
				for(int w = 0; w < sol.Weights.length; w++) {
					returnSol.setWeight(i, w, sol.Weights[w]);
				}
			}
		}
		
		SolverStatus.checkSolverStatus(returnSol, sol);
	}


	private static void storePhaseTwoInformation(DEAProblem deaP, int nbDMUs,
			int nbVariables, DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
		
		try{
			//Collect information from Phase II (Theta)
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			if(deaP.getModelType() == ModelType.CCR_I) {
				for(int lambdaPos = 0; lambdaPos < nbDMUs; lambdaPos++) {
					if(sol.VariableResult[lambdaPos + 1] != 0) {
						refSet.add(new NonZeroLambda(lambdaPos, sol.VariableResult[lambdaPos + 1]));
					}
				}
				returnSol.setReferenceSet(dmuIndex, refSet);
				returnSol.setSlackArrayCopy(dmuIndex, sol.VariableResult, nbDMUs + 1, nbVariables);
			}
			else {
				for(int lambdaIndex = 0; lambdaIndex < nbDMUs; lambdaIndex++) {
					if(sol.VariableResult[lambdaIndex + 1] != 0) {
						refSet.add(new NonZeroLambda(lambdaIndex,
								sol.VariableResult[lambdaIndex + 1] * returnSol.getObjective(dmuIndex)));
					}
				}
				returnSol.setReferenceSet(dmuIndex, refSet);
				for(int varIndex = 0; varIndex < nbVariables; varIndex++) {
					//setSlack(i, s, Sol.VariableResult[NbDMUs + 1 + s] * ReturnSol.getObjective(i));
					returnSol.setSlack(dmuIndex, varIndex, sol.VariableResult[nbDMUs + 1 + varIndex] * returnSol.getObjective(dmuIndex));
				}
			}
			
			if(deaP.getModelType() == ModelType.CCR_I) {
				for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
					if(deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT) {
						//Projections
						returnSol.setProjection(dmuIndex, varIndex,
								returnSol.getObjective(dmuIndex) * deaP.getDataMatrix(dmuIndex, varIndex)
								- returnSol.getSlack(dmuIndex, varIndex));
					}
					else {
						//Projections
						returnSol.setProjection(dmuIndex, varIndex,
								deaP.getDataMatrix(dmuIndex, varIndex) + returnSol.getSlack(dmuIndex, varIndex));
					}
				}
			}
			else {
				for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
					if(deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT) {
						//Projections
						returnSol.setProjection(dmuIndex, varIndex,
								returnSol.getObjective(dmuIndex) * deaP.getDataMatrix(dmuIndex, varIndex)
								+ returnSol.getSlack(dmuIndex, varIndex));
					}
					else {
						//Projections
						returnSol.setProjection(dmuIndex, varIndex,
								deaP.getDataMatrix(dmuIndex, varIndex) - returnSol.getSlack(dmuIndex, varIndex));
					}
				}
			}
			
			SolverStatus.checkSolverStatus(returnSol, sol);
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createPhaseOneModel(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix, int dmuIndex,
			ArrayList<double[]> constraints, double[] objF, double[] rhs1,
			int[] solverEqualityType) throws Exception {
		
		
		try {
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
				
				//Build Model for each DMU
				
				//Build the Constraint Matrix
				double[] constraintRow = new double[nbDMUs + nbVariables + 1];
				//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
				if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT) {
					constraintRow[0] = transposedMatrix[varIndex] [dmuIndex] * -1;
				}
				else {
					constraintRow[0] = 0;
				}
				//Copy rest of the data matrix
				System.arraycopy(transposedMatrix[varIndex], 0, constraintRow, 1, nbDMUs);
				//and slacks
				if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT) {
					constraintRow[nbDMUs + 1 + varIndex] = -1;
				}
				else {
					constraintRow[nbDMUs + 1 + varIndex] = 1;
				}
				constraints.add(constraintRow);
				
				
				//Build RHS & SolverEqualityTypes
				if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT) {
					rhs1[varIndex] = 0;
					solverEqualityType[varIndex] = LpSolve.EQ;
				}
				else {
					rhs1[varIndex] = transposedMatrix[varIndex] [dmuIndex];
					solverEqualityType[varIndex] = LpSolve.EQ;
				}
			
			}
		}
		catch (Exception e) {
			throw e;
		}
		/* Build Objective Function (Theta column is assigned the weight 1.
		 * All the other columns are left to 0).*/
		objF[0] = 1;
	}


}
