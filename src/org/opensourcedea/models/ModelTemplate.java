
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
    
*/

package org.opensourcedea.models;

//import lpsolve.LpSolve;
import java.util.ArrayList;

import org.opensourcedea.dea.*;
import org.opensourcedea.linearSolver.*;





/**
 * The class implementing the SBM (CRS, VRS and GRS) Non Oriented model.
 * </br>
 * @author Hubert Virtos
 *
 */
public class ModelTemplate {
	
	/**
	 * The method solving the SBM Problems (Constant and Variable RTS).
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveDEAProblem(DEAProblem deaP) throws Exception {
		
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
				
				createAndSolveDEAProblem(deaP, NbDMUs, NbVariables, TransposedMatrix,
						ReturnSol, DMUIndex);
			}
			return ReturnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}


	
	
	private static void createAndSolveDEAProblem(DEAProblem deaP, int NbDMUs,
			int NbVariables, double[][] TransposedMatrix,
			DEAPSolution ReturnSol, Integer i /*DMU Number*/) throws Exception {
		
		
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		/* There is one extra column for the scalar t used during the 
		 * Fractional to Linear SBM model transformaation.*/
		
		double[] ObjF = new double [NbDMUs + NbVariables]; 
		double[] RHS = new double [NbVariables];
		int[] SolverEqualityType = new int [NbVariables];
		
		
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
			
			//WRITE CODE TO STORE SOLUTION HERE
			
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
		
		/* WRITE THE CODE FOR:
		 * 	- THE CONSTRAINSTS MATRIX,
		 * 	- THE RHS VECTOR,
		 * 	- THE OBJECTIVE FUNCTION,
		 * 	- THE SOLVER EQUALITY TYPE VECTOR
		 * HERE.
		 * */
	}
}
