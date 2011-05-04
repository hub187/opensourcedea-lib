package dea;

public class InconsistentNoOfDMUs extends InconsistentData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InconsistentNoOfDMUs() {
		super("The number of DMUs does not seem to match with the data. Please check the DMUNames and DataMatrix.");
	}
	
}