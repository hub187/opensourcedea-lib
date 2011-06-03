
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
 * Because Theta is coded in the first column, the first weight of the weight array is the variable u0. 
 * (multiplier variable corresponding to the convexity constraint). If the model is under IRS, DRS or GRS, there will be
 * TWO weights before the variable weights (corresponding respectively to the Lower Bound and Upper Bound constraints).
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
			int NbDMUs = deaP.getNumberOfDMUs();
			int NbVariables = deaP.getNumberOfVariables();
			double [] [] TransposedMatrix = new double [NbVariables] [NbDMUs];
			TransposedMatrix = deaP.getTranspose(true); //Want the negative Transposed
			DEAPSolution ReturnSol = new DEAPSolution(deaP.getNumberOfDMUs(), deaP.getNumberOfVariables());
	
			/* As the BBC optimisation needs to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the BBC model is solved in two phases.
			 * The problem will consequently be solved for each DMUs for Phase I and
			 * solved again for each DMUs for Phase II.*/
	
			for (int DMUIndex = 0; DMUIndex < NbDMUs; DMUIndex++) {
				
				  createAndSolveBCC(deaP, NbDMUs, NbVariables, TransposedMatrix,
						ReturnSol, DMUIndex);
			}		
			return ReturnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void createAndSolveBCC(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, Integer i) throws Exception {
		
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		double[] ObjF = new double [NbDMUs + NbVariables + 1];
		double[] RHS1;
		double[] RHS2;
		int[] SolverEqualityType1;
		int[] SolverEqualityType2;
		
		
		  //////////////////////////////
		 //		Solve Phase I		 //
		//////////////////////////////
		
		if(deaP.getModelType() == ModelType.BCCI ||
				deaP.getModelType() == ModelType.BCCO){
			RHS1 = new double [NbVariables + 1]; //RHS Phase I
			SolverEqualityType1 = new int[NbVariables + 1];
		}
		else /* GRS, IRS, DRS, need one extra row for second convexity constraint*/ {
			RHS1 = new double [NbVariables + 2]; //RHS Phase I
			SolverEqualityType1 = new int[NbVariables + 2];
		}
		
		createPhaseOneModel(deaP, NbDMUs, NbVariables, TransposedMatrix, i,
				Constraints, ObjF, RHS1, SolverEqualityType1);
		
		
		//Solve
		SolverResults Sol = new SolverResults();
		try {
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MIN, SolverEqualityType1);
			}
			else {
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MAX, SolverEqualityType1);
			}
		}
		catch (ProblemNotSolvedProperly e1) {
			throw new ProblemNotSolvedProperly("The problem could not be solved properly at DMU Index: " +
					 i.toString());
		}
		catch (MissingData e2) {
			throw e2;
		}
		catch (DEASolverException e3) {
			throw e3;
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
		
		if(deaP.getModelType() == ModelType.BCCI ||
				deaP.getModelType() == ModelType.BCCO) {
			/*+1 row for convexity constraints, + 1 row for theta*/
			RHS2 = new double[NbVariables + 2];
			SolverEqualityType2 = new int[NbVariables + 2];
		}
		else {
			/*+2 rows for convexity constraints (U & L), + 1 row for theta*/
			RHS2 = new double[NbVariables + 3];
			SolverEqualityType2 = new int[NbVariables + 3];
		}
		
		createPhaseTwoModel(deaP, NbDMUs, NbVariables, Constraints, ObjF, RHS1,
				RHS2, SolverEqualityType1, SolverEqualityType2, Sol);
		
		
		//Solve the Phase II Problem
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS2, SolverObjDirection.MAX, SolverEqualityType2);
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
		}
		catch (Exception e) {
			throw e;
		}

	}


	
	private static void storePhaseTwoInformation(DEAProblem deaP, int NbDMUs,
			int NbVariables, DEAPSolution ReturnSol, int i, SolverResults Sol) throws Exception {
		
		//Collect information from Phase II (Theta)
		try {
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int lambdaPos = 0; lambdaPos < NbDMUs; lambdaPos++) {
				if(Sol.VariableResult[lambdaPos + 1] != 0) {
					refSet.add(new NonZeroLambda(lambdaPos, Sol.VariableResult[lambdaPos + 1]));
				}
			}
			ReturnSol.setReferenceSet(i, refSet);
			ReturnSol.setSlackArrayCopy(i, Sol.VariableResult, NbDMUs + 1, NbVariables);
	
			for (int j = 0; j < NbVariables; j++) {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if(deaP.getVariableType(j) == VariableType.INPUT) {
							//Projections
							ReturnSol.setProjection(i, j, ReturnSol.getObjective(i) * deaP.getDataMatrix(i, j) - ReturnSol.getSlack(i, j));
						}
						else {
							//Projections
							ReturnSol.setProjection(i, j, deaP.getDataMatrix(i, j) + ReturnSol.getSlack(i, j));
						}
					}
					else {
						if(deaP.getVariableType(j) == VariableType.OUTPUT) {
							//Projections
							if(ReturnSol.getObjective(i) != 0){
								ReturnSol.setProjection(i, j, (1 / ReturnSol.getObjective(i)) * deaP.getDataMatrix(i, j) + ReturnSol.getSlack(i, j));
							}
							else {
								ReturnSol.setProjection(i, j, ReturnSol.getSlack(i, j));
							}
						}
						else {
							//Projections
							ReturnSol.setProjection(i, j, deaP.getDataMatrix(i, j) - ReturnSol.getSlack(i, j));
						}
					}
			}
	
			SolverStatus.checkSolverStatus(ReturnSol, Sol);
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
		if(deaP.getModelType() == ModelType.BCCI ||
				deaP.getModelType() == ModelType.BCCO) {
			System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
			RHS2[NbVariables + 1] = Sol.Objective;
			System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0, SolverEqualityType1.length);
			SolverEqualityType2[NbVariables + 1] = LpSolve.EQ;
		}
		else {
			System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
			RHS2[NbVariables + 2] = Sol.Objective;
			System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0, SolverEqualityType1.length);
			SolverEqualityType2[NbVariables + 2] = LpSolve.EQ;
		}
		
	
		//Change Objective Function
		Arrays.fill(ObjF,0);
		for (int j = NbDMUs + 1; j <= NbDMUs + NbVariables; j++) {
			ObjF[j] = 1;
		}
	}


	private static void storePhaseOneInformation(DEAProblem deaP,
			DEAPSolution ReturnSol, int i, SolverResults Sol) throws Exception {
		//Collect information from Phase I (Theta)
		try {
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				ReturnSol.setObjective(i, Sol.Objective);
			}
			else {
				if(Sol.Objective != 0) {
					ReturnSol.setObjective(i, 1/ Sol.Objective);
				}
				else {
					ReturnSol.setObjective(i, 0);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
				ReturnSol.setWeights(i, Sol.Weights);
			}
			else {
				ReturnSol.setWeights(i, new double[Sol.Weights.length]);
				for(int k = 0; k < Sol.Weights.length; k++) {
					ReturnSol.setWeight(i, k, Sol.Weights[k] * -1);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
		SolverStatus.checkSolverStatus(ReturnSol, Sol);
	}


	private static void createPhaseOneModel(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix, int i,
			ArrayList<double[]> Constraints, double[] ObjF, double[] RHS1,
			int[] SolverEqualityType1) throws Exception {
		
		double[] ConstraintRow;
		for (int VarIndex = 0; VarIndex < NbVariables; VarIndex++) {
						
			//Build the Constraint Matrix, row by row
			ConstraintRow = new double[NbDMUs + NbVariables + 1];
			//First column (input values for  DMU under observation (DMUIndex) * -1; 0 for outputs)
				try {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if (deaP.getVariableType(VarIndex) == VariableType.INPUT) {
							ConstraintRow[0] = TransposedMatrix[VarIndex] [i] * -1;
						}
						else  {
							ConstraintRow[0] = 0;
						}
					}
					else {
						if (deaP.getVariableType(VarIndex) == VariableType.OUTPUT) {
							ConstraintRow[0] = TransposedMatrix[VarIndex] [i] * -1;
						}
						else  {
							ConstraintRow[0] = 0;
						}
					}
				}
				catch (Exception e) {
					throw e;
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
				
				//Add the row to the Constraints ArrayList
				Constraints.add(ConstraintRow);
			
			//Build RHS & SolverEqualityTypes
				try {
					if(deaP.getModelOrientation() == ModelOrientation.INPUT_ORIENTED) {
						if (deaP.getVariableType(VarIndex) == VariableType.INPUT) {
							RHS1[VarIndex] = 0;
							SolverEqualityType1[VarIndex] = LpSolve.EQ;
						}
						else {
							RHS1[VarIndex] = TransposedMatrix[VarIndex] [i];
							SolverEqualityType1[VarIndex] = LpSolve.EQ;
						}
					}
					else {
						if (deaP.getVariableType(VarIndex) == VariableType.OUTPUT) {
							RHS1[VarIndex] = 0;
							SolverEqualityType1[VarIndex] = LpSolve.EQ;
						}
						else {
							RHS1[VarIndex] = TransposedMatrix[VarIndex] [i];
							SolverEqualityType1[VarIndex] = LpSolve.EQ;
						}
					}
				}
				catch (Exception e) {
					throw e;
				}
			} //finished looping through all variables

		
		//Build the row corresponding to the convexity constraint
		ConstraintRow = new double[NbDMUs + NbVariables + 1];
		for(int VarIndex = 1; VarIndex <= NbDMUs; VarIndex++){
			ConstraintRow[VarIndex] = 1;
		}
		if(deaP.getModelType() == ModelType.BCCI ||
				deaP.getModelType() == ModelType.BCCO) {
			Constraints.add(ConstraintRow);
			RHS1[NbVariables] = 1;
			SolverEqualityType1[NbVariables] = LpSolve.EQ;
		}
		else /*In this case the model is a general, increasing or decreasing model*/ {
			Constraints.add(ConstraintRow);
			RHS1[NbVariables] = deaP.getRTSLowerBound();
			SolverEqualityType1[NbVariables] = LpSolve.GE;
			Constraints.add(ConstraintRow);
			RHS1[NbVariables + 1] = deaP.getRTSUpperBound();
			SolverEqualityType1[NbVariables + 1] = LpSolve.LE;
		}
		
		//Build Objective Function (Theta column is assigned the weight 1. All the other columns are left to 0).
		ObjF[0] = 1;
	}

}
