
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

package org.opensourcedea.linearSolver;

import java.util.ArrayList;

import org.opensourcedea.dea.DEASolverException;
import org.opensourcedea.dea.ProblemNotSolvedProperly;
import org.opensourcedea.dea.SolverObjDirection;
import org.opensourcedea.dea.SolverReturnStatus;

import lpsolve.LpSolve;

/**
 * This class deals with the calls to the lpsolve library and is used to solve linear problem.
 * It is essential to use a linear solver in order to solve DEA problems.
 * <p>
 * @author Hubert Virtos
 *
 */
public class Lpsolve {
	

	/*
	 * IMPORTANT NOTE:
	 * 
	 * If you have any problem initiating the LpSolve class, check the installation instructions in linearSolver.lpsolve\READ ME file.
	 */

	
	/**
	 * The solveLPProblem method solve a Linear Problem. This method is called for each DMU when solving a DEA problem.
	 * The method returns a solution object. See the SolverResults class for more information on this object.
	 * 
	 * The solveLPProblem method is called with all the element required to define a Linear Problem, i.e.:
	 * 1) an ArrayList of double[] for the constraints
	 * 2) a double[] for the objective function
	 * 3) a double[] for the Right Hand Side
	 * 4) a optimisation direction (min or max)
	 * 
	 * 
	 * @param Constraints A double[] of the constraint values.
	 * @param ObjF A double[] of the Objective Function values.
	 * @param RHS A double[] of the Right Hand Side values
	 * @param Dir A SolverObjDirection (MIN or MAX)
	 * @return A SolverResult Object with the solution to the linear problem.
	 * @throws Exception 
	 * @throws ProblemNotSolvedProperly
	 */
	public static SolverResults solveLPProblem(ArrayList<double[]> Constraints, double[] ObjF, double[] RHS,
			SolverObjDirection Dir, int[] EqType) throws DEASolverException, ProblemNotSolvedProperly {
	
		//The Solution object which will be returned
		SolverResults Sol = new SolverResults();
		
		try {
			
			int NbColumns = Constraints.get(0).length;
			int NbRows = Constraints.size();
			int ret = 0;			
			
			
			//Create Lpsolve linear problem
			LpSolve LpProb = LpSolve.makeLp(0, NbColumns);			
	        if(LpProb.getLp() == 0) {
	        	//Linear problem could not be constructed.
	        	Sol.Status = SolverReturnStatus.MODEL_CREATION_FAILURE;
	        	ret = 1;
	        }
	        
	        if (ret == 0) {
	        	//Linear problem constructed OK to proceed.
	        	
	        	
	        	//Set AddRowMode to true
	        	LpProb.setAddRowmode(true);
	        		        	
	        	//Create column index Array (used to add constraints)
	        	int[] ColNo = new int[NbColumns];
	        	for (int i = 0; i < NbColumns;) {
	        		ColNo[i++] = i;
	        	}
	        	
	        	//Add constraints
	        	for (int j = 0; j < NbRows; j++) {
	        		LpProb.addConstraintex(NbColumns, Constraints.get(j), ColNo, EqType[j] /*LpSolve.EQ*/, RHS[j]);
	        	}
	        	
	        	//Set Add Row Mode back to false
	        	LpProb.setAddRowmode(false);
	        	
	        	
	        	//Set Objective Function
	        	LpProb.setObjFnex(NbColumns, ObjF, ColNo);
	        	
	        	
	        	
	        	//Set Direction
	        	if (Dir == SolverObjDirection.MAX) {
	        		LpProb.setMaxim();
	        	}
	        	else {
	        		LpProb.setMinim();
	        	}
	        	
	        	LpProb.setVerbose(0);
	        	
	        	//Solve the Linear Problem
	        	ret = LpProb.solve();
	        	if (ret == LpSolve.OPTIMAL) {
	        		ret = 0;
	        	}
	        	else {
	        		ret = 1;
	        		Sol.Status = SolverReturnStatus.OPTIMAL_SOLUTION_NOT_FOUND;
	        		throw new ProblemNotSolvedProperly("The problem could not be solved properly.");
	        	}
	        	
	        	//Store Optimisation values
	        	if (ret == 0) {
		            /* In order to optimise the code, it could be possible to return the solver solution
		             * directly instead of a copy (weight/constraint results, objectives...).
		             * This was not done for clarity purposes*/
	        		
	        		double[] VariableResult = new double[NbColumns];
	        		double[] ConstraintResult = new double[NbRows];
	        		double Objective = 0;
	        		double[] DualResult = new double[NbColumns + NbRows + 1];
	        		double[] Weights = new double[NbRows];
	        		
	        		//Get Primal Solution (variables values)
	        		LpProb.getVariables(VariableResult);
	        		//Get Objective Value
	        		Objective = LpProb.getObjective();
	        		//Get Dual Solution (weights)
	        		LpProb.getConstraints(ConstraintResult);
	        		LpProb.getDualSolution(DualResult);
	        		System.arraycopy(DualResult, NbColumns + 1, Weights, 0, NbRows);
	        		

	        		Sol.ConstraintResult = ConstraintResult;
	        		Sol.DualResult = DualResult;
	        		Sol.Objective = Objective;
	        		Sol.VariableResult = VariableResult;
	        		Sol.Weights = Weights;
	        		Sol.Status = SolverReturnStatus.OPTIMAL_SOLUTION_FOUND;

	        		
	        	}

	        }
	           
		}
		
		catch (ProblemNotSolvedProperly e1) {
			Sol.Status = SolverReturnStatus.OPTIMAL_SOLUTION_NOT_FOUND;
	    	throw e1;
		}

	    catch (Exception e2) {
	    	//e.printStackTrace();
	    	Sol.Status = SolverReturnStatus.UNKNOWN_ERROR;
	    	throw new DEASolverException("An error was thrown by the LpSolve class when attempting to solve the problem." +
	    			"This is likely caused by inconsistent data sent to the solver." +
	    			"The error details are as follows: " + 
	    			e2.toString());
	    	
	    }

	 
	    return Sol;
	}
	
}
