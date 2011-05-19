package dea;

/**
 * A DEAException thrown when number of variables or DMUs do not match between different element of the DEAProblem
 * (e.g. 1050 VariableTypes but only 1040 VariableNames).
 * </br>
 * @author Hubert Virtos
 *
 */
public class InvalidPropertyValue extends DEAException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPropertyValue() {
		super("The value entered is invalid.");
	}
	public InvalidPropertyValue(String detailMsg) {
		super(detailMsg);
		// TODO Auto-generated constructor stub
	}

}
