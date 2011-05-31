
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
	 * The method solving the CCR Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws DEASolverException 
	 */
	public static DEAPSolution solveBCC(DEAProblem deaP) throws DEASolverException {
		
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
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
		

		
		for (int i = 0; i < NbDMUs; i++) {
			
			  createAndSolveBCC(deaP, NbDMUs, NbVariables, TransposedMatrix,
					ReturnSol, i);
			
		}
		
		return ReturnSol;

		
	}


	private static void createAndSolveBCC(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, int i) throws DEASolverException {
		/////////////////////////////
		 //		Solve Phase I		//
		/////////////////////////////
		
		
		
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		double[] ObjF = new double [NbDMUs + NbVariables + 1];
		double[] RHS1;
		double[] RHS2;
		int[] SolverEqualityType1;
		int[] SolverEqualityType2;
		double[] ConstraintRow;
		//DEAModelType mt = deaP.getModelType();
		
		if(deaP.getModelType() == DEAModelType.BCCI ||
				deaP.getModelType() == DEAModelType.BCCO){
			RHS1 = new double [NbVariables + 1]; //RHS Phase I
			SolverEqualityType1 = new int[NbVariables + 1];
		}
		else /* GRS, IRS, DRS, need one extra row for second convexity constraint*/ {
			RHS1 = new double [NbVariables + 2]; //RHS Phase I
			SolverEqualityType1 = new int[NbVariables + 2];
		}
		
		for (int j = 0; j < NbVariables; j++) {
			
			//Build Model for each DMU
			
			//Build the Constraint Matrix
			ConstraintRow = new double[NbDMUs + NbVariables + 1];
			//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
				try {
					if(deaP.getModelOrientation() == DEAModelOrientation.InputOriented) {
						if (deaP.getVariableType(j) == DEAVariableType.Input) {
							ConstraintRow[0] = TransposedMatrix[j] [i] * -1;
						}
						else  {
							ConstraintRow[0] = 0;
						}
					}
					else {
						if (deaP.getVariableType(j) == DEAVariableType.Output) {
							ConstraintRow[0] = TransposedMatrix[j] [i] * -1;
						}
						else  {
							ConstraintRow[0] = 0;
						}
					}
				}
				catch (MissingData e) {
					e.printStackTrace();
				}

				
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
			
			//Build RHS & SolverEqualityTypes
				try {
					if(deaP.getModelOrientation() == DEAModelOrientation.InputOriented) {// deaP.getModelType() == DEAModelType.BCCI) {
						if (deaP.getVariableType(j) == DEAVariableType.Input) {
							RHS1[j] = 0;
							SolverEqualityType1[j] = LpSolve.EQ;
						}
						else {
							RHS1[j] = TransposedMatrix[j] [i];
							SolverEqualityType1[j] = LpSolve.EQ;
						}
					}
					else {
						if (deaP.getVariableType(j) == DEAVariableType.Output) {
							RHS1[j] = 0;
							SolverEqualityType1[j] = LpSolve.EQ;
						}
						else {
							RHS1[j] = TransposedMatrix[j] [i];
							SolverEqualityType1[j] = LpSolve.EQ;
						}
					}
				}
				catch (MissingData e) {
					e.printStackTrace();
				}
			} //finished looping through all variable
		
		
//			else {
//				if(deaP.getModelType() == DEAModelType.BCCI ||
//						deaP.getModelType() == DEAModelType.BCCO) {
//					RHS1[j] = 1;
//					SolverEqualityType[j] = LpSolve.EQ;
//				}
//				else /*In this case the model is a general, increasing or decreasing model*/ {
//					RHS1[j] = deaP.getRTSLowerBound();
//					SolverEqualityType[j] = LpSolve.LE;
//				}
//			}
//		} //finished looping through all variable + 1
		
		ConstraintRow = new double[NbDMUs + NbVariables + 1];
		for(int k = 1; k <= NbDMUs; k++){
			ConstraintRow[k] = 1;
		}
		
		if(deaP.getModelType() == DEAModelType.BCCI ||
				deaP.getModelType() == DEAModelType.BCCO) {
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
		
		
		//Solve
		SolverResults Sol = new SolverResults();
		
		try {
			if(deaP.getModelOrientation() == DEAModelOrientation.InputOriented) { // deaP.getModelType() == DEAModelType.BCCI){
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MIN, SolverEqualityType1);
			}
			else {
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MAX, SolverEqualityType1);
			}
		}
		catch (DEASolverException e) {
			throw e;
		}
		catch (MissingData e) {
			e.printStackTrace();
		}

		//Collect information from Phase I (Theta)
		try {
			if(deaP.getModelOrientation() == DEAModelOrientation.InputOriented) { //deaP.getModelType() == DEAModelType.BCCI) {
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
		} catch (MissingData e1) {
			e1.printStackTrace();
		}
		
		try {
			if(deaP.getModelOrientation() == DEAModelOrientation.InputOriented) {
				ReturnSol.getWeights()[i] = Sol.Weights;
			}
			else {
				ReturnSol.getWeights()[i] = new double[Sol.Weights.length];
				for(int k = 0; k < Sol.Weights.length; k++) {
					ReturnSol.getWeights()[i][k] = Sol.Weights[k] * -1;
				}
			}
		} catch (MissingData e1) {
			e1.printStackTrace();
		}
		
		checkSolverStatus(ReturnSol, Sol);
		
		
		  /////////////////////////////
		 //		Solve Phase II		//
		/////////////////////////////
		/* The only things that are needed for Phase II are to:
		 * - add an extra Constraint Row to Constraints in order to ensure Theta Phase I
		 *   is not changed during Phase II Optimisation.
		 * - add the corresponding Theta to the RHS Array
		 * - change the Objective Function accordingly (all 1 on Slacks, all others coeff = 0).*/
		
		
		//Changing Constraint Matrix
		ConstraintRow = new double[NbDMUs + NbVariables + 1];
		ConstraintRow[0] = 1;
		Constraints.add(ConstraintRow);
				
		
		//Changing RHS & SolverEqTypes
		if(deaP.getModelType() == DEAModelType.BCCI ||
				deaP.getModelType() == DEAModelType.BCCO) {
			RHS2 = new double[NbVariables + 2];
			SolverEqualityType2 = new int[NbVariables + 2];
			System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
			RHS2[NbVariables + 1] = Sol.Objective;
			System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0, SolverEqualityType1.length);
			SolverEqualityType2[NbVariables + 1] = LpSolve.EQ;
		}
		else {
			RHS2 = new double[NbVariables + 3];
			SolverEqualityType2 = new int[NbVariables + 3];
			/*+2 rows for convexity constraints (U & L), + 1 row for theta*/
			System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
			RHS2[NbVariables + 2] = Sol.Objective;
			System.arraycopy(SolverEqualityType1, 0, SolverEqualityType2, 0, SolverEqualityType1.length);
			SolverEqualityType2[NbVariables + 2] = LpSolve.EQ;
		}
		
//		SolverEqualityType = new int[NbVariables + 2];
//		for(int j = 0; j < NbVariables + 2; j++) {
//			SolverEqualityType[j] = LpSolve.EQ;
//		}
		
		
		//Change Objective Function
		Arrays.fill(ObjF,0);
		for (int j = NbDMUs + 1; j <= NbDMUs + NbVariables; j++) {
			ObjF[j] = 1;
		}
		
		//Solve the Phase II Problem
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS2, SolverObjDirection.MAX, SolverEqualityType2);
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		//Collect information from Phase II (Theta)
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		for(int lambdaPos = 0; lambdaPos < NbDMUs; lambdaPos++) {
			if(Sol.VariableResult[lambdaPos + 1] != 0) {
				refSet.add(new NonZeroLambda(lambdaPos, Sol.VariableResult[lambdaPos + 1]));
			}
		}
		ReturnSol.getReferenceSet()[i] = refSet;
		//System.arraycopy(Sol.VariableResult, 1, ReturnSol.Lambdas[i], 0, NbDMUs);
		//ReturnSol.setSlackArrayCopy(Sol.VariableResult, NbDMUs + 1, 0, NbVariables, i);
		System.arraycopy(Sol.VariableResult, NbDMUs + 1, ReturnSol.getSlacks(i) /*deaP.getSlacks(i) | deaP.Solution.Slacks[i]*/, 0, NbVariables);


		for (int j = 0; j < NbVariables; j++) {
			try {
				if(deaP.getModelOrientation() == DEAModelOrientation.InputOriented) { //deaP.getModelType() == DEAModelType.BCCI) {
					if(deaP.getVariableType(j) == DEAVariableType.Input) {
						//Projections
						ReturnSol.getProjections()[i] [j] = ReturnSol.getObjective(i) * deaP.getDataMatrix(i, j) - ReturnSol.getSlack(i, j);
					}
					else {
						//Projections
						//deaP.setProjections(i, j, deaP.getDataMatrix(i, j) + deaP.getSlacks(i, j));
						ReturnSol.getProjections()[i] [j] = deaP.getDataMatrix(i, j) + ReturnSol.getSlack(i, j);
					}
				}
				else {
					if(deaP.getVariableType(j) == DEAVariableType.Output) {
						//Projections
						if(ReturnSol.getObjective(i) != 0){
							ReturnSol.getProjections()[i] [j] = (1 / ReturnSol.getObjective(i)) * deaP.getDataMatrix(i, j) + ReturnSol.getSlack(i, j);
						}
						else {
							ReturnSol.getProjections()[i] [j] = ReturnSol.getSlack(i, j);
						}
					}
					else {
						//Projections
						//deaP.setProjections(i, j, deaP.getDataMatrix(i, j) + deaP.getSlacks(i, j));
						ReturnSol.getProjections()[i] [j] = deaP.getDataMatrix(i, j) - ReturnSol.getSlack(i, j);
					}
				}
			} catch (MissingData e) {
				e.printStackTrace();
			}
		}

		
		
		checkSolverStatus(ReturnSol, Sol);
	}

	
	private static void checkSolverStatus(DEAPSolution ReturnSol,
			SolverResults Sol) {
		switch(Sol.Status) {
			case OptimalSolutionNotfound:
				ReturnSol.setStatus(SolverReturnStatus.OptimalSolutionNotfound);
				break;
		
			case UnknownError:
				ReturnSol.setStatus(SolverReturnStatus.UnknownError);
				break;
			
			case ModelCreationFailure:
				ReturnSol.setStatus(SolverReturnStatus.ModelCreationFailure);
				break;
			
			case OptimalSolutionFound:
				/* The lpsolve class CANNOT return NA (which is only used for initialisation as default value).
				 * If ReturnSol.Status == NA this means the previous optimisation (if any) did not have any problem so it is
				 * save to store a ReturnValue of OptimalSolutionFound*/
				
				if(ReturnSol.getStatus() == SolverReturnStatus.NA){
					ReturnSol.setStatus(SolverReturnStatus.OptimalSolutionFound);
				}
				break;
		}
	}
	

	

}
