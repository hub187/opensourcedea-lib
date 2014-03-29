package org.opensourcedea.dea;

/**
 * A enum listing the ModelVariableTypes. This enum is attached to all model types and help identifying which type of variable the model can use.
 * @author Hubert Virtos
 *
 */
public enum ModelVariablesType {
	STANDARD ("The standard Model Variable Type. These models only have standard variables. " +
			"They do not have non-discretionary or non-controllable variables."),
	NON_DISCRETIONARY ("The non-discretionary Model Variable Type. These models allows both standard variable and " +
			"non-discretionary variable types."),
	NON_CONTROLLABLE ("The non-controllable Model Variable Type. These models allows both standard variable and " +
			"non-controllable variable types."),
	NON_DISCRETIONARY_NON_CONTROLLABLE ("The non-discretionary-non-controllable Model Variable Type. These models allows all variables types. " +
			"(i.e. standard, non-dicretionary and non-controllable variable types.)");
	
	private String name;
	private String description;
	
	

	ModelVariablesType(String modVarTypeDescription) {
		this.description = modVarTypeDescription;
	}
	
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
	
	
}
