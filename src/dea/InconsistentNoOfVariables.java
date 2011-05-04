package dea;

public class InconsistentNoOfVariables extends InconsistentData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InconsistentNoOfVariables() {
		super("The number of variables does not seem to match with the data. Please check the VariableNames, VariableTypes and DataMatrix.");
	}
	
}
