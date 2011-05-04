package dea;

public class DEASolverException extends DEAException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DEASolverException() {
		super("The linear solver lpsolve encountered an error. This is likely caused by inconsistencies in the data sent to the Lpsolve.solveLPProblem method.");
	}
}
