
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

package linearSolver;

import java.util.ArrayList;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import dea.enums.*;


public class Lpsolve {
	
	/* In order for this code to run, you need to install lpsolve on your machine.
	 * As the correct dlls and the lpsolve jar wrapper is already on your machine (if you checked out the source correctly
	 * they should be in src\linearSolver\lpsolve), you simply need to add the lpsolve55j.jar to your buildpath.
	 * On Eclipse, you can do this by
	 * => right clicking on your project,
	 * => selecting Properties (you can also select your project in the package explorer and press Alt + Enter),
	 * => clicking on the libraries tabs,
	 * => clicking on Add External JARs,
	 * => browsing to the lpsolve55j.jar and clicking OK.*/
	
	public static SolverResults solveLPProblem(ArrayList<double[]> Constraints, double[] ObjF, double[] RHS, SolverObjDirection Dir) {
	
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
	        	Sol.Status = SolverReturnStatus.ModelCreationFailure;
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
	        		LpProb.addConstraintex(NbColumns, Constraints.get(j), ColNo, LpSolve.EQ, RHS[j]);
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
	        	
	        	
	        	//Solve the Linear Problem
	        	ret = LpProb.solve();
	        	if (ret == LpSolve.OPTIMAL) {
	        		ret = 0;
	        	}
	        	else {
	        		ret = 1;
	        		Sol.Status = SolverReturnStatus.OptimalSolutionNotfound;
	        	}
	        	
	        	//Store Optimisation values
	        	if (ret == 0) {
		            
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
	        		Sol.Status = SolverReturnStatus.OptimalSolutionFound;

	        		
	        	}

	        }
	           
		}
			
	    catch (LpSolveException e) {
	       e.printStackTrace();
	       Sol.Status = SolverReturnStatus.UnknownError;
	    }
	 
	    return Sol;
	}
	
}
