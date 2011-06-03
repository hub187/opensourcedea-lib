package dea;

/**
 * A DEAException extending InconsistentData thrown when number of Variables do not match between
 * different element of the DEAProblem.
 * </br>
 * @author Hubert Virtos
 *
 */
public class InconsistentNoOfVariables extends InconsistentData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InconsistentNoOfVariables() {
		super("The number of variables does not seem to match with the data. Please check the VariableNames, VariableTypes and DataMatrix.");
	}
	
}
