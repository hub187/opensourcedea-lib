package dea;

/**
 * A DEASolver exception. Used when the Lpsolve crashes. This should not happen has the check in the DEAProblem.solve() method
 * should throw an DEAException before bad data is sent to a model (e.g. throw MissingData DEAexception is there is no data in the
 * DEAProblem).
 * </br>
 * @author Hubert Virtos
 *
 */
public class DEASolverException extends DEAException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DEASolverException() {
		super("The linear solver lpsolve encountered an error. This is likely caused by inconsistencies in the data sent to the Lpsolve.solveLPProblem method.");
	}
}
