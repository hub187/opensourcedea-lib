package dea;

public class Variable {
	private String[] variableName;
	private VariableOrientation [] variableOrientation;
	private VariableType[] variableType;
	
	
	
	
	
	public void setVariableNames(String[] varNames) {
		this.variableName = varNames;
	}
	
	public void setVariableName(int varIndex, String varName) {
		this.variableName[varIndex] = varName;
	}
	
	public String[] getVariableNames() {
		return this.variableName;
	}
	
	public String getVariableName(int varIndex) {
		return this.variableName[varIndex];
	}
	
	
	
	
	public void setVariableOrientations(VariableOrientation[] varOrientations) {
		this.variableOrientation = varOrientations;
	}
	
	public void setVariableOrientation(int varIndex, VariableOrientation varOrientation) {
		this.variableOrientation[varIndex] = varOrientation;
	}
	
	public VariableOrientation[] getVariableOrientations() {
		return this.variableOrientation;
	}
	
	public VariableOrientation getVariableOrientation(int varIndex) {
		return this.variableOrientation[varIndex];
	}
	
	
	
	
	public void setVariableTypes(VariableType[] varTypes) {
		this.variableType = varTypes;
	}
	
	public void setVariableType(int varIndex, VariableType varType) {
		this.variableType[varIndex] = varType;
	}
	
	public VariableType[] getVariableTypes() {
		return this.variableType;
	}
	
	public VariableType getVariableType(int varIndex) {
		return this.variableType[varIndex];
	}
}
