package dea;

/**
 * An enum listing the possible VariableTypes.
 * </br> 
 * @author Hubert.Virtos
 *
 */
public enum VariableType {
	STANDARD ("The standard variable type."),
	NON_CONTROLLABLE ("The non-controllable variable type (used with the" +
			"non-controllables models)."),
	NON_DISCRETIONARY ("The non-discretionary variable type (used with the" +
			"non-discretionary models).");
	
	private String name;
	private String description;
	
	

	VariableType(String varDescription) {
		this.description = varDescription;
	}
	
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
}
