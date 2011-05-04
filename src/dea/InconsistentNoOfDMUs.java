package dea;

public class InconsistentNoOfDMUs extends InconsistentData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InconsistentNoOfDMUs() {
		super("The number of DMUs does not seem to match. Please check the DMUNames and DataMatrix.");
	}
	
}
