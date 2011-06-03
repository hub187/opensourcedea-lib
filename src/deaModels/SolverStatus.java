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
				 * save to store a ReturnValue of OptimalSolutionFound.
				 * If the previous return status is NOT NA, then it is either:
				 * => OptimalSolutionFound, in this case we do not need to change it or
				 * => A SolutionReturnStatus indicating a problem, in this case again, we should leave it as it is.*/
				
				if(ReturnSol.getStatus() == SolverReturnStatus.NA){
					ReturnSol.setStatus(SolverReturnStatus.OptimalSolutionFound);
				}
				break;
		}
	}

}
