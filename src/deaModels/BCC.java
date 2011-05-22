
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
 * The class implementing the BBC (Input and Output oriented) models.
 * The first column is the theta so the length of the weights array is NbVariable + 1.
 * Because Theta is coded in the first column, the first weight of the weight array is the variable u0
 * (multiplier variable corresponding to the convexity constraint).
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
		int[] SolverEqualityType;
		
		if(deaP.getModelType() == DEAModelType.BCCI ||
				deaP.getModelType() == DEAModelType.BCCO){
			RHS1 = new double [NbVariables + 1]; //RHS Phase I
			SolverEqualityType = new int[NbVariables + 1];
		}
		else /* GRS, IRS, DRS, need one extra row for second convexity constraint*/ {
			RHS1 = new double [NbVariables + 2]; //RHS Phase I
			SolverEqualityType = new int[NbVariables + 2];
		}
		
		for (int j = 0; j <= NbVariables; j++) {
			
			//Build Model for each DMU
			
			//Build the Constraint Matrix
			double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
			//First column (input values for  DMU under observation (i) * -1; 0 for outputs)
			if(j < NbVariables) {
				if(deaP.getModelType() == DEAModelType.BCCI) {
					if (deaP.getVariableType(j) == DEAVariableType.Input) {
						ConstraintRow[0] = TransposedMatrix[j] [i] * -1;
					}
					else  {
						ConstraintRow[0] = 0;
					}
				}
				else if(deaP.getModelType() == DEAModelType.BCCO) {
					if (deaP.getVariableType(j) == DEAVariableType.Output) {
						ConstraintRow[0] = TransposedMatrix[j] [i] * -1;
					}
					else  {
						ConstraintRow[0] = 0;
					}
				}
				else /* GRS, IRS, DRS, need one extra row for second convexity constraint*/ {
					
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
			}
			else {
				//If J = NbVariables, put the convexity constraint
				for(int k = 1; k <= NbDMUs; k++){
					ConstraintRow[k] = 1;
				}
			}
			Constraints.add(ConstraintRow);
			
			
			//Build RHS & SolverEqualityTypes
			if(j < NbVariables) {
				if(deaP.getModelType() == DEAModelType.BCCI) {
					if (deaP.getVariableType(j) == DEAVariableType.Input) {
						RHS1[j] = 0;
						SolverEqualityType[j] = LpSolve.EQ;
					}
					else {
						RHS1[j] = TransposedMatrix[j] [i];
						SolverEqualityType[j] = LpSolve.EQ;
					}
				}
				else {
					if (deaP.getVariableType(j) == DEAVariableType.Output) {
						RHS1[j] = 0;
						SolverEqualityType[j] = LpSolve.EQ;
					}
					else {
						RHS1[j] = TransposedMatrix[j] [i];
						SolverEqualityType[j] = LpSolve.EQ;
					}
				}
			}
			else {
				RHS1[j] = 1;
				SolverEqualityType[j] = LpSolve.EQ;
			}
			
		
		}

		//Build Objective Function (Theta column is assigned the weight 1. All the other columns are left to 0).
		ObjF[0] = 1;
		
		
		//Solve
		SolverResults Sol = new SolverResults();
		
		try {
			if(deaP.getModelType() == DEAModelType.BCCI){
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MIN, SolverEqualityType);
			}
			else {
				Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS1, SolverObjDirection.MAX, SolverEqualityType);
			}
		}
		catch (DEASolverException e) {
			throw e;
		}

		//Collect information from Phase I (Theta)
		if(deaP.getModelType() == DEAModelType.BCCI) {
			ReturnSol.Objectives[i] = Sol.Objective;
		}
		else {
			if(Sol.Objective != 0) {
				ReturnSol.Objectives[i] = 1/ Sol.Objective;
			}
			else {
				ReturnSol.Objectives[i] = 0;
			}
		}
		ReturnSol.Weights[i] = Sol.Weights;

		
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
		double[] ConstraintRow = new double[NbDMUs + NbVariables + 1];
		ConstraintRow[0] = 1;
		Constraints.add(ConstraintRow);
		
		//Changing RHS & SolverEqTypes
		double[] RHS2 = new double[NbVariables + 2];
		System.arraycopy(RHS1, 0, RHS2, 0, RHS1.length);
		RHS2[NbVariables + 1] = Sol.Objective;
		
		SolverEqualityType = new int[NbVariables + 2];
		for(int j = 0; j < NbVariables + 2; j++) {
			SolverEqualityType[j] = LpSolve.EQ;
		}
		
		
		//Change Objective Function
		Arrays.fill(ObjF,0);
		for (int j = NbDMUs + 1; j <= NbDMUs + NbVariables; j++) {
			ObjF[j] = 1;
		}
		
		//Solve the Phase II Problem
		try {
			Sol = Lpsolve.solveLPProblem(Constraints, ObjF, RHS2, SolverObjDirection.MAX, SolverEqualityType);
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		//Collect information from Phase II (Theta)
		System.arraycopy(Sol.VariableResult, 1, ReturnSol.Lambdas[i] /*deaP.getLambdas(i) | deaP._Solution.Lambdas[i]*/, 0, NbDMUs);
		System.arraycopy(Sol.VariableResult, NbDMUs + 1, ReturnSol.Slacks[i] /*deaP.getSlacks(i) | deaP.Solution.Slacks[i]*/, 0, NbVariables);


		for (int j = 0; j < NbVariables; j++) {
			if(deaP.getModelType() == DEAModelType.BCCI) {
				if(deaP.getVariableType(j) == DEAVariableType.Input) {
					//Projections
					ReturnSol.Projections[i] [j] = ReturnSol.Objectives[i] * deaP.getDataMatrix(i, j) - ReturnSol.Slacks[i] [j];
				}
				else {
					//Projections
					//deaP.setProjections(i, j, deaP.getDataMatrix(i, j) + deaP.getSlacks(i, j));
					ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) + ReturnSol.Slacks[i] [j];
				}
			}
			else {
				if(deaP.getVariableType(j) == DEAVariableType.Output) {
					//Projections
					ReturnSol.Projections[i] [j] = ReturnSol.Objectives[i] * deaP.getDataMatrix(i, j) + ReturnSol.Slacks[i] [j];
				}
				else {
					//Projections
					//deaP.setProjections(i, j, deaP.getDataMatrix(i, j) + deaP.getSlacks(i, j));
					ReturnSol.Projections[i] [j] = deaP.getDataMatrix(i, j) - ReturnSol.Slacks[i] [j];
				}
			}
		}

		
		
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
