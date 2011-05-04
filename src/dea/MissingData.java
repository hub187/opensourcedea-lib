package dea;

public class MissingData extends DEAException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingData() {
		super("The DEAProblem is missing some data. Please check the DataMatrix, DMUNames, ModelType, VariableNames and VariableTypes.");
	}
	public MissingData(String message) {
		super(message);
	}
}
