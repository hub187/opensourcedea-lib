package dea;

import java.util.Arrays;

public class Variable {
	private String[] variableName;
	private VariableOrientation [] variableOrientation;
	private VariableType[] variableType;
	
	
	public Variable(int varNumber){
		variableName = new String[varNumber];
		variableOrientation = new VariableOrientation[varNumber];
		variableType = new VariableType[varNumber];
		Arrays.fill(variableType, VariableType.STANDARD);
	}
	
	
	public void setVariableNames(String[] varNames) {
		this.variableName = varNames;
	}
	
	public void setVariableName(int varIndex, String varName) throws Exception {
		try {
			this.variableName[varIndex] = varName;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public String[] getVariableNames() {
		return this.variableName;
	}
	
	public String getVariableName(int varIndex) throws Exception {
		try {
			return this.variableName[varIndex];
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
	
	
	public void setVariableOrientations(VariableOrientation[] varOrientations) {
		this.variableOrientation = varOrientations;
	}
	
	public void setVariableOrientation(int varIndex, VariableOrientation varOrientation) throws Exception{
		try {
			this.variableOrientation[varIndex] = varOrientation;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public VariableOrientation[] getVariableOrientations() {
		return this.variableOrientation;
	}
	
	public VariableOrientation getVariableOrientation(int varIndex)  throws Exception{
		try {
			return this.variableOrientation[varIndex];
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
	
	
	public void setVariableTypes(VariableType[] varTypes) {
		this.variableType = varTypes;
	}
	
	public void setVariableType(int varIndex, VariableType varType) throws Exception {
		try {
			this.variableType[varIndex] = varType;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public VariableType[] getVariableTypes() {
		return this.variableType;
	}
	
	public VariableType getVariableType(int varIndex) throws Exception{
		try {
			return this.variableType[varIndex];
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public String getVariableTypeName(int varIndex) throws Exception {
		try {
			return this.getVariableType(varIndex).getName();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public String getVariableTypeDescription(int varIndex) throws Exception{
		try {
			return this.getVariableType(varIndex).getDescription();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
