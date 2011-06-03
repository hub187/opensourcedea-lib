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
