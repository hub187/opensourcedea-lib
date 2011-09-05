
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
 * The class implementing the SBM Input Oriented model (implementing both CRTS and VRTS).
 * </br>
 * @author Hubert Virtos
 *
 */
public class SBMI {
	
	/**
	 * The method solving the DEA Problem.
	 * @param deaP An instance of DEAProblem.
	 * @throws Exception 
	 */
	public static DEAPSolution solveSBMI(DEAProblem deaP) throws Exception {
		
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
				createAndSolveSBMI(deaP, nbDMUs, nbVariables, constraints, transposedMatrix,
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
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
				double[] constraintRow = new double[nbDMUs + nbVariables];
				
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
		}
		catch (Exception e) {
			throw e;
		}
		
		if(deaP.getModelType() == ModelType.SBMI_V) {
			//Add one row for the convexity constraints
			double[] constraintRow = new double[nbDMUs + nbVariables];
			for(int dmuIndex = 0; dmuIndex < nbDMUs; dmuIndex++) {
				constraintRow[dmuIndex] = 1;
			}
			constraints.add(constraintRow);
		}
		
		if(deaP.getModelType() == ModelType.SBM_I_GRS) {
			double[] constraintRow = new double[nbDMUs + nbVariables];
			for(int dmuIndex = 0; dmuIndex < nbDMUs; dmuIndex++) {
				constraintRow[dmuIndex] = 1;
			}
			constraints.add(constraintRow);
			constraints.add(constraintRow);
		}
	}

	private static void createAndSolveSBMI(DEAProblem deaP, int nbDMUs,
			int nbVariables,  ArrayList<double[]> constraints, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex) throws Exception {
		
		double[] objF = new double [nbDMUs + nbVariables];
		double[] rhs;
		int[] solverEqualityType;
		if(deaP.getModelType() == ModelType.SBM_I) {
			rhs = new double[nbVariables];
			solverEqualityType = new int[nbVariables];
		}
		else if (deaP.getModelType() == ModelType.SBMI_V) {
			//One extra row for convexity constraint
			rhs = new double[nbVariables + 1];
			solverEqualityType = new int[nbVariables + 1];
		}
		else { //In this case the model is ModelType.SBMIGRS
			//Tow extra rows for GRS constraints
			rhs = new double[nbVariables + 2];
			solverEqualityType = new int[nbVariables + 2];
		}

		
		createModel(deaP, nbDMUs, nbVariables, transposedMatrix, dmuIndex, objF, rhs,
				solverEqualityType);
		
		
		SolverResults Sol = new SolverResults();
		try {
			Sol = Lpsolve.solveLPProblem(constraints, objF, rhs, SolverObjDirection.MIN, solverEqualityType);
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
			storeSolution(deaP, nbDMUs, nbVariables, returnSol, dmuIndex, Sol);
		} catch (Exception e) {
			throw e;
		}
	}


	private static void storeSolution(DEAProblem deaP, int nbDMUs,
			int nbVariables, DEAPSolution returnSol, int dmuIndex, SolverResults sol) throws Exception {
		
		try {
		//Store solution
		returnSol.setObjective(dmuIndex, 1 + sol.Objective); //there was a Objective constant of 1.
		
		for(int varIndex = 0; varIndex < nbVariables; varIndex++) {
			returnSol.setSlack(dmuIndex, varIndex, sol.VariableResult[nbDMUs + varIndex]);
			
			if(deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT) {
				returnSol.setWeight(dmuIndex, varIndex, sol.DualResult[varIndex + 1] * -1);
				returnSol.setProjection(dmuIndex, varIndex,
						deaP.getDataMatrix(dmuIndex, varIndex) - returnSol.getSlack(dmuIndex, varIndex));
			}
			else {
				returnSol.setWeight(dmuIndex, varIndex, sol.DualResult[varIndex + 1]);
				returnSol.setProjection(dmuIndex, varIndex,
						deaP.getDataMatrix(dmuIndex, varIndex) + returnSol.getSlack(dmuIndex, varIndex));
			}
		}
		}
		catch (Exception e) {
			throw e;
		}
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		for(int lambdaIndex = 0; lambdaIndex < nbDMUs; lambdaIndex++) {
			if(sol.VariableResult[lambdaIndex] != 0) {
				refSet.add(new NonZeroLambda(lambdaIndex, sol.VariableResult[lambdaIndex]));
			}
		}
		returnSol.setReferenceSet(dmuIndex, refSet);
		
		SolverStatus.checkSolverStatus(returnSol, sol);
	}


	private static void createModel(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix, int dmuIndex, double[] objF,
			double[] rhs, int[] solverEqualityType) throws Exception {
		
		try {
			int nbInputs = deaP.getNumberOfInputs();
					
			//Build the RHS
			for (int varIndex = 0; varIndex < nbVariables; varIndex++) {
					//Build RHS
					rhs[varIndex] = transposedMatrix[varIndex] [dmuIndex];
					if(deaP.getVariableOrientation(varIndex) == VariableOrientation.INPUT){
						if(transposedMatrix[varIndex] [dmuIndex] * nbInputs != 0) {
							objF[nbDMUs + varIndex] = -1 / (transposedMatrix[varIndex] [dmuIndex] * nbInputs);
						}
						else {
							objF[nbDMUs + varIndex] = -1;
						}
					}
					solverEqualityType[varIndex] = LpSolve.EQ;
			}
			
			//Add extra row if Variable RTS
			if(deaP.getModelType() == ModelType.SBMI_V) {
				rhs[nbVariables] = 1;
				solverEqualityType[nbVariables] = LpSolve.EQ;
			}
			
			//Add two extra rows if model is SBMIGRS
			if(deaP.getModelType() == ModelType.SBM_I_GRS) {
				
				rhs[nbVariables] = deaP.getRTSLowerBound();
				solverEqualityType[nbVariables] = LpSolve.GE;
				
				rhs[nbVariables + 1] = deaP.getRTSUpperBound();
				solverEqualityType[nbVariables+1] = LpSolve.LE;
				
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	
}
