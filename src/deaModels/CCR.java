
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
			int NbDMUs = deaP.getNumberOfDMUs();
			int NbVariables = deaP.getNumberOfVariables();
			double [] [] TransposedMatrix = new double [NbVariables] [NbDMUs];
			TransposedMatrix = deaP.getTranspose(true); //Want the negative Transposed
			DEAPSolution ReturnSol = new DEAPSolution(deaP.getNumberOfDMUs(), deaP.getNumberOfVariables());
			
						
			
			
			/* As the CCR optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the CCR model is solved in two phases.
			 * The problem will consequently be solved for each DMUs for Phase I and
			 * solved again for each DMUs for Phase II.*/
			
	
			
			for (int DMUIndex = 0; DMUIndex < NbDMUs; DMUIndex++) {
				
				  createAndSolveCCR(deaP, NbDMUs, NbVariables, TransposedMatrix,
						ReturnSol, DMUIndex);
			}
			return ReturnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createAndSolveCCR(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, Integer i) throws DEASolverException, Exception {
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
		
		
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		double[] ObjF = new double [NbDMUs + NbVariables + 1];
		double[] RHS1 = new double [NbVariables]; //RHS Phase I
		double[] RHS2;// = new double [NbVariables]; //RHS Phase II
		int[] SolverEqualityType = new int[NbVariables];

		createPhaseOneModel(deaP, NbDMUs, NbVariables, TransposedMatrix, i,
				Constraints, ObjF, RHS1, SolverEqualityType);
		
		
		//Solve
		SolverResults Sol = new SolverResults();
		
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MIN, SolverEqualityType);
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
					 i.toString());
		}
		catch (DEASolverException e2) {
			throw e2;
		}

		
		storePhaseOneInformation(deaP, ReturnSol, i, Sol);
		
		
		  /////////////////////////////
		 //		Solve Phase II		//
		/////////////////////////////
		/* The only things that are needed for Phase II are to:
		 * - add an extra Constraint Row to Constraints in order to ensure Theta Phase I
		 *   is not changed during Phase II Optimisation.
		 * - add the corresponding Theta to the RHS Array
		 * - change the Objective Function accordingly (all 1 on Slacks, all others coeff = 0).*/
		
		RHS2 = new double[NbVariables + 1];
		SolverEqualityType = new int[NbVariables + 1];
		
		createPhaseTwoModel(NbDMUs, NbVariables, Constraints, ObjF, RHS1, RHS2,
				SolverEqualityType, Sol);
		
		//Solve the Phase II Problem
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS2, SolverObjDirection.MAX, SolverEqualityType);
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
					 i.toString());
		}
		catch (DEASolverException e2) {
			throw e2;
		}
		
		try {
			storePhaseTwoInformation(deaP, NbDMUs, NbVariables, ReturnSol, i, Sol);
		} catch (Exception e) {
			throw e;
		}
	}


	private static void createPhaseTwoModel(int NbDMUs, int NbVariables,
			ArrayList<double[]> Constraints, double[] ObjF, double[] RHS1,
			double[] RHS2, int[] SolverEqualityType, SolverResults Sol) {
		//Changing Constraint Matrix
		double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
		ConstraintRow[0] = 1;
		Constraints.add(ConstraintRow);
		
		//Changing RHS & SolverEqTypes
		System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
		RHS2[NbVariables] = Sol.Objective;
		
		for(int VarIndex = 0; VarIndex < NbVariables + 1; VarIndex++) {
			SolverEqualityType[VarIndex] = LpSolve.EQ;
		}
		
		
		//Change Objective Function
		Arrays.fill(ObjF,0);
		for (int j = NbDMUs + 1; j <= NbDMUs + NbVariables; j++) {
			ObjF[j] = 1;
		}
	}


	private static void storePhaseOneInformation(DEAProblem deaP,
			DEAPSolution ReturnSol, int i, SolverResults Sol) {
		//Collect information from Phase I (Theta)
		if(deaP.getModelType() == ModelType.CCRI) {
			ReturnSol.setObjective(i, Sol.Objective);
			ReturnSol.setWeights(i, Sol.Weights);
		}
		else {
			ReturnSol.setObjective(i, 1 / Sol.Objective);
			for(int w = 0; w < Sol.Weights.length; w++) {
				ReturnSol.setWeight(i, w, Sol.Weights[w] / Sol.Objective);
			}
		}
		
		SolverStatus.checkSolverStatus(ReturnSol, Sol);
	}


	private static void storePhaseTwoInformation(DEAProblem deaP, int NbDMUs,
			int NbVariables, DEAPSolution ReturnSol, int i, SolverResults Sol) throws Exception {
		
		try{
			//Collect information from Phase II (Theta)
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			if(deaP.getModelType() == ModelType.CCRI) { // getModelOrientation() == DEAModelOrientation.InputOriented) {
				for(int lambdaPos = 0; lambdaPos < NbDMUs; lambdaPos++) {
					if(Sol.VariableResult[lambdaPos + 1] != 0) {
						refSet.add(new NonZeroLambda(lambdaPos, Sol.VariableResult[lambdaPos + 1]));
					}
				}
				ReturnSol.setReferenceSet(i, refSet);
				//System.arraycopy(Sol.VariableResult, 1, ReturnSol.Lambdas[i] /*deaP.getLambdas(i) | deaP._Solution.Lambdas[i]*/, 0, NbDMUs);
				//ReturnSol.setSlackArrayCopy(Sol.VariableResult, NbDMUs + 1, 0, NbVariables, i);
				ReturnSol.setSlackArrayCopy(i, Sol.VariableResult, NbDMUs + 1, NbVariables);
			}
			else {
				for(int LambdaIndex = 0; LambdaIndex < NbDMUs; LambdaIndex++) {
					if(Sol.VariableResult[LambdaIndex + 1] != 0) {
						refSet.add(new NonZeroLambda(LambdaIndex, Sol.VariableResult[LambdaIndex + 1] * ReturnSol.getObjective(i)));
					}
					//ReturnSol.Lambdas[i][lambdaPos] = Sol.VariableResult[lambdaPos + 1] * ReturnSol.Objectives[i]; 
				}
				ReturnSol.setReferenceSet(i, refSet);
				for(int s = 0; s < NbVariables; s++) {
					//setSlack(i, s, Sol.VariableResult[NbDMUs + 1 + s] * ReturnSol.getObjective(i));
					ReturnSol.setSlack(i, s, Sol.VariableResult[NbDMUs + 1 + s] * ReturnSol.getObjective(i));
				}
			}
			
			if(deaP.getModelType() == ModelType.CCRI) {
				for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
					if(deaP.getVariableType(VarIndex) == VariableType.INPUT) {
						//Projections
						ReturnSol.setProjection(i, VarIndex,
								ReturnSol.getObjective(i) * deaP.getDataMatrix(i, VarIndex) - ReturnSol.getSlack(i, VarIndex));
					}
					else {
						//Projections
						//deaP.setProjections(i, j, deaP.getDataMatrix(i, j) + deaP.getSlacks(i, j));
						ReturnSol.setProjection(i, VarIndex,
								deaP.getDataMatrix(i, VarIndex) + ReturnSol.getSlack(i, VarIndex));
					}
				}
			}
			else {
				for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
					if(deaP.getVariableType(VarIndex) == VariableType.OUTPUT) {
						//Projections
						ReturnSol.setProjection(i, VarIndex,
								ReturnSol.getObjective(i) * deaP.getDataMatrix(i, VarIndex) + ReturnSol.getSlack(i, VarIndex));
					}
					else {
						//Projections
						//deaP.setProjections(i, j, deaP.getDataMatrix(i, j) + deaP.getSlacks(i, j));
						ReturnSol.setProjection(i, VarIndex,
								deaP.getDataMatrix(i, VarIndex) - ReturnSol.getSlack(i, VarIndex));
					}
				}
			}
			
			SolverStatus.checkSolverStatus(ReturnSol, Sol);
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createPhaseOneModel(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix, int i,
			ArrayList<double[]> Constraints, double[] ObjF, double[] RHS1,
			int[] SolverEqualityType) throws Exception {
		
		
		try {
			for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
				
				//Build Model for each DMU
				
				//Build the Constraint Matrix
				double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
				//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
				if (deaP.getVariableType(VarIndex) == VariableType.INPUT) {
					ConstraintRow[0] = TransposedMatrix[VarIndex] [i] * -1;
				}
				else {
					ConstraintRow[0] = 0;
				}
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
				
				
				//Build RHS & SolverEqualityTypes
				if (deaP.getVariableType(VarIndex) == VariableType.INPUT) {
					RHS1[VarIndex] = 0;
					SolverEqualityType[VarIndex] = LpSolve.EQ;
				}
				else {
					RHS1[VarIndex] = TransposedMatrix[VarIndex] [i];
					SolverEqualityType[VarIndex] = LpSolve.EQ;
				}
			
			}
		}
		catch (Exception e) {
			throw e;
		}
		//Build Objective Function (Theta column is assigned the weight 1. All the other columns are left to 0).
		ObjF[0] = 1;
	}


}
