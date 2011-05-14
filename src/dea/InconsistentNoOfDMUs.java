package dea;

/**
 * A DEAException extending InconsistentData thrown when number of DMUs do not match between different element of the DEAProblem.
 * </br>
 * @author Hubert Virtos
 *
 */
public class InconsistentNoOfDMUs extends InconsistentData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InconsistentNoOfDMUs() {
		super("The number of DMUs does not seem to match with the data. Please check the DMUNames and DataMatrix.");
	}
	
}
