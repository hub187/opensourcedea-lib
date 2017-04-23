
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

import org.opensourcedea.dea.SolverObjDirection;
import org.opensourcedea.dea.SolverReturnStatus;
import org.opensourcedea.exception.DEASolverException;
import org.opensourcedea.exception.ProblemNotSolvedProperlyException;

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
	 * @throws ProblemNotSolvedProperlyException
	 */
	public static SolverResults solveLPProblem(ArrayList<double[]> Constraints, double[] ObjF, double[] RHS,
			SolverObjDirection Dir, int[] EqType) throws DEASolverException, ProblemNotSolvedProperlyException {
	
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
	        	
	        	LpProb.setVerbose(1);
	        	
	        	//Solve the Linear Problem
	        	ret = LpProb.solve();
	        	
	        	
	        	//DEALING WITH NUMERICAL FAILURE (5)
	        	if (ret == 5) {
	        		System.out.println("NUMERICAL ISSUES WITH PROBLEM - PRE-SOLVING TO TRY RE-SOLVING THE PROBLEM");
	        		// There was a problem let's try solving it by pre-solving it
	        		LpProb.resetBasis();
	        		LpSolve LpProb2 = LpProb.copyLp();
	        		LpProb2.setPresolve(LpSolve.PRESOLVE_BOUNDS | LpSolve.PRESOLVE_COLS, LpProb2.getPresolveloops());
	        		
	        		ret = LpProb2.solve();
	        		
	        		//If this does not work, try more pre-solving option
	        		if (ret != LpSolve.OPTIMAL) {
	        		LpProb.resetBasis();
	        		LpProb2 = LpProb.copyLp();
	        		LpProb2.setPresolve(LpSolve.PRESOLVE_BOUNDS | LpSolve.PRESOLVE_COLS |
	            			LpSolve.PRESOLVE_ROWS |LpSolve.PRESOLVE_ROWDOMINATE | LpSolve.PRESOLVE_COLDOMINATE, LpProb2.getPresolveloops());
	        		}
	        		
	        		ret = LpProb2.solve();
	        		
	        		LpProb = LpProb2;
	        	}
	        	
	        	
	        	//DEALING WITH ACCURACY FAILURE (25) NOTE THAT setBreakAcccuracy IS NOT AVAILABLE IN JAVA WRAPPER".
	        	//See LpSolveMailing reply on this post:
	        	//http://lp-solve.2324885.n4.nabble.com/lpsolve-error-set-break-numeric-accuracy-Unimplemented-td10318.html
	        	if (ret == 25) {
	        		System.out.println("ACCURACY ISSUES WITH PROBLEM - SET SCALING TO TRY RE-SOLVING THE PROBLEM");
	        		// There was a problem let's try solving it by pre-solving it
	        		LpProb.resetBasis();
	        		LpSolve LpProb2 = LpProb.copyLp();
	        		LpProb2.resetBasis();

	        		LpProb2.setScaling(LpSolve.SCALE_GEOMETRIC | LpSolve.SCALE_INTEGERS);
	        		
	        		LpProb2.setPresolve(LpSolve.PRESOLVE_BOUNDS , LpProb2.getPresolveloops());
	        		
	        		ret = LpProb2.solve();
	        		
	        		LpProb = LpProb2;
	        	}
	        	
	        	if (ret == LpSolve.OPTIMAL) {
	        		ret = LpSolve.OPTIMAL;
	        	}
	        	else if(ret == 25) {
	        		System.out.println("CANNOT RESOLVE WITH GOOD ACCURACY");
	        	}
	        	else {
	        		
	        		if (ret != LpSolve.OPTIMAL) {
	        			String errorTxt = "";
	        			switch (ret) {
	        				case 1 : errorTxt = "Sub-optimal solution found";
	        				case 2: errorTxt = "Problem is infeasible";
	        				case 3: errorTxt = "Problem is inbounded";
	        				case 4: errorTxt = "Problem degenerated";
	        				case 5: errorTxt = "Numerical failure when trying to solve";
	        				case 6: errorTxt = "User interuption";
	        				case 7: errorTxt = "A timeout occured";
	        				case 9: errorTxt = "Problem can be solved via presolve";
	        				case 25: errorTxt = "Accuracy error encountered";
	        				System.out.println(errorTxt);
	        			}

	        		Sol.Status = SolverReturnStatus.OPTIMAL_SOLUTION_NOT_FOUND;
	        		throw new ProblemNotSolvedProperlyException("The problem could not be solved properly." + " " + errorTxt);
	        		}
	        	}
	        	
	        	//Store Optimisation values (EVEN WHEN ACCURACY IS LOWER THAN DESIRED)
	        	if (ret == LpSolve.OPTIMAL || ret == 25) {
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
	        		
	        		if(ret == LpSolve.OPTIMAL) {
	        			Sol.Status = SolverReturnStatus.OPTIMAL_SOLUTION_FOUND;
	        		}
	        		else {
	        			Sol.Status = SolverReturnStatus.SOLUTION_FOUND_LOW_ACCURACY;
	        		}

	        		
	        	}

	        }
	           
		}
		
		catch (ProblemNotSolvedProperlyException e1) {
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
