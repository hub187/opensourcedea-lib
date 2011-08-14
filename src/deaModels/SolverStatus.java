
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

package deaModels;

import linearSolver.SolverResults;
import dea.DEAPSolution;
import dea.SolverReturnStatus;

public class SolverStatus {
	
	/**
	 * 
	 * @param ReturnSol
	 * @param Sol
	 */
	public static void checkSolverStatus(DEAPSolution ReturnSol,
			SolverResults Sol) {
		switch(Sol.Status) {
			case OPTIMAL_SOLUTION_NOT_FOUND:
				ReturnSol.setStatus(SolverReturnStatus.OPTIMAL_SOLUTION_NOT_FOUND);
				break;
		
			case UNKNOWN_ERROR:
				ReturnSol.setStatus(SolverReturnStatus.UNKNOWN_ERROR);
				break;
			
			case MODEL_CREATION_FAILURE:
				ReturnSol.setStatus(SolverReturnStatus.MODEL_CREATION_FAILURE);
				break;
			
			case OPTIMAL_SOLUTION_FOUND:

				/* The lpsolve class CANNOT return NA (which is only used for initialisation as default value).
				 * If ReturnSol.Status == NA this means the previous optimisation (if any) did not have any problem so it is
				 * save to store a ReturnValue of OptimalSolutionFound.
				 * If the previous return status is NOT NA, then it is either:
				 * => OptimalSolutionFound, in this case we do not need to change it or
				 * => A SolutionReturnStatus indicating a problem, in this case again, we should leave it as it is.*/
				
				if(ReturnSol.getStatus() == SolverReturnStatus.NOT_SOLVED){
					ReturnSol.setStatus(SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
				}
				break;
		}
	}

}
