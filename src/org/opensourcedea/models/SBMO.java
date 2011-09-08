
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

import lpsolve.LpSolve;
import java.util.ArrayList;

import org.opensourcedea.dea.*;
import org.opensourcedea.linearSolver.*;





/**
 * The class implementing the SBM Output Oriented model (implementing both CRTS and VRTS).
 * <p>
 * Because the objective 'MIN po = 1 / (1 + (1/s) SUM(Sr+/Yro))' is not linear the 
 * objective used in the calculation is 'MAX 1/p0 = (1 + (1/s) SUM(Sr+/Yro))' which is linear.</p>
 * <p>
 * As a consequence the output slack weight is 1 / (s*Yro) and the model objective is deduced from
 * the solver objective value as follows: Model Objective = 1 / (1 + SolverObjectiveValue).
 * </p>
 * @author Hubert Virtos
 *
 */
public class SBMO {
	
	/**
	 * The method solving the DEA Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveSBMO(DEAProblem deaP) throws Exception {
		
		try {
		/* Declare & Collect the variables that will often be used in the process (rather
		 * than calling the different methods several times.*/
		int nbDMUs = deaP.getNumberOfDMUs();
		int nbVariables = deaP.getNumberOfVariables();
		double [] [] transposedMatrix = new double [nbVariables] [nbDMUs];
		transposedMatrix = deaP.getTranspose(false);
		ArrayList<double[]> constraints = new ArrayList<double []>();
		DEAPSolution returnSol = new DEAPSolution(nbDMUs, nbVariables);
		
		
		/* Because the Constraint Matrix is identical for all optimisation,
		 * let's build it here and re-use it for each optimisation. */
		createConstraintMatrix(deaP, nbDMUs, nbVariables, transposedMatrix,
				constraints);
		
		/* As the SBM optimisation needs to be ran for all DMUs, 
		 * the program will loop through all DMUs.*/
		
		for (int dmuIndex = 0; dmuIndex < nbDMUs; dmuIndex++) {
			createAndSolveSBMO(deaP, nbDMUs, nbVariables, constraints, transposedMatrix,
					returnSol, dmuIndex);
		}
		return returnSol;
		}
		catch (Exception e) {
			throw e;
		}
	}
	

	private static void createConstraintMatrix(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			ArrayList<double[]> constraints) throws Exception {
		
		try {
			double[] constraintRow;
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
				constraintRow = new double[nbDMUs + nbVariables];
				
					//Copy rest of the data matrix
					System.arraycopy(transposedMatrix[varIndex], 0, constraintRow, 0, nbDMUs);
					//and slacks
					if (deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT) {
						constraintRow[nbDMUs + varIndex] = 1;
					}
					else {
						constraintRow[nbDMUs + varIndex] = -1;
					}
					constraints.add(constraintRow);
			}
			
			if(deaP.getModelType() == ModelType.SBM_O_V) {
				constraintRow = new double[nbDMUs + nbVariables];
				for(int k = 0; k < nbDMUs; k++) {
					constraintRow[k] = 1;
				}
				constraints.add(constraintRow);
			}
			if(deaP.getModelType() == ModelType.SBM_O_GRS) {
				constraintRow = new double[nbDMUs + nbVariables];
				for(int k = 0; k < nbDMUs; k++) {
					constraintRow[k] = 1;
				}
				constraints.add(constraintRow);
				constraints.add(constraintRow);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	private static void createAndSolveSBMO(DEAProblem deaP, int nbDMUs,
			int nbVariables,  ArrayList<double[]> constraints, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex) throws Exception {
		
		try {
			double[] objF = new double [nbDMUs + nbVariables];
			double[] rhs;
			int[] solverEqualityType;
			int nbOutputs = deaP.getNumberOfOutputs();
			
			if(deaP.getModelType() == ModelType.SBM_O) {
				rhs = new double [nbVariables];
				solverEqualityType = new int[nbVariables];
			}
			else if (deaP.getModelType() == ModelType.SBM_O_V) {
				rhs = new double [nbVariables + 1];
				solverEqualityType = new int[nbVariables + 1];
				rhs[nbVariables] = 1;
				solverEqualityType[nbVariables] = LpSolve.EQ;
			}
			else { //deaP.getModelType() == ModelType.SBMOGRS
				rhs = new double [nbVariables + 2];
				solverEqualityType = new int[nbVariables + 2];
				rhs[nbVariables] = deaP.getRTSLowerBound();
				solverEqualityType[nbVariables] = LpSolve.GE;
				rhs[nbVariables + 1] = deaP.getRTSUpperBound();
				solverEqualityType[nbVariables + 1] = LpSolve.LE;
				
			}
			
			
			
			
			//Build the RHS (for the variables, added constraints (convexity, grs... were added before)
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
					//Build RHS
					rhs[varIndex] = transposedMatrix[varIndex] [dmuIndex];
					if(deaP.getVariableOrientation(varIndex) == VariableOrientation.OUTPUT){
						if(transposedMatrix[varIndex] [dmuIndex] * nbOutputs != 0) {
							objF[nbDMUs + varIndex] = 1 / (transposedMatrix[varIndex] [dmuIndex] * nbOutputs);
						}
						else {
							objF[nbDMUs + varIndex] = 1;
						}
					}
					solverEqualityType[varIndex] = LpSolve.EQ;
			}
			
			
			SolverResults sol = new SolverResults();
			
			try {
				sol = Lpsolve.solveLPProblem(constraints, objF, rhs,
						SolverObjDirection.MAX, solverEqualityType);
			}
			catch (ProblemNotSolvedProperlyException e1) {
				throw new ProblemNotSolvedProperlyException("The problem could not be solved properly at DMU Index: "
						+ dmuIndex.toString()
						+". The error was: " + e1.getMessage());
			}
			catch (DEASolverException e2) {
				throw new DEASolverException("The problem could not be solved properly at DMU Index: "
						+ dmuIndex.toString()
						+ ". The error was: " + e2.getMessage());
			}
			
			
			storeSolution(deaP, nbDMUs, nbVariables, returnSol, dmuIndex, sol);
		}
		catch (Exception e) {
			throw e;
		}
	}


	private static void storeSolution(DEAProblem deaP, int nbDMUs,
			int nbVariables, DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
		
		try {
			//Store solution
			returnSol.setObjective(dmuIndex, 1 / (1 + sol.Objective)); //there was a Objective constant of 1.
			for(int varPos = 0; varPos < nbVariables; varPos++) {
				
				returnSol.setSlack(dmuIndex, varPos, sol.VariableResult[nbDMUs + varPos]);
				
				if(deaP.getVariableOrientation(varPos) == VariableOrientation.OUTPUT) {
					returnSol.setWeight(dmuIndex, varPos, sol.DualResult[varPos + 1] * -1);
					returnSol.setProjection(dmuIndex, varPos,
							deaP.getDataMatrix(dmuIndex, varPos) + returnSol.getSlack(dmuIndex, varPos));
				}
				else {
					returnSol.setWeight(dmuIndex, varPos, sol.DualResult[varPos + 1]);
					returnSol.setProjection(dmuIndex, varPos,
							deaP.getDataMatrix(dmuIndex, varPos) - returnSol.getSlack(dmuIndex, varPos));
				}
			}
			
			ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
			for(int lambdaPos = 0; lambdaPos < nbDMUs; lambdaPos++) {
				if(sol.VariableResult[lambdaPos] != 0) {
					refSet.add(new NonZeroLambda(lambdaPos, sol.VariableResult[lambdaPos]));
				}
			}
			returnSol.setReferenceSet(dmuIndex, refSet);
			//System.arraycopy(Sol.VariableResult, 0, ReturnSol.Lambdas[i], 0, NbDMUs - 1);
			
			SolverStatus.checkSolverStatus(returnSol, sol);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
