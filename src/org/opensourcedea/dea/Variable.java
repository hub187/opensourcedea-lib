
/*	<DEASolver (googleproject opensourcedea) is a java DEA solver.>
    Copyright (C) <2010>  <Hubert Paul Bernard VIRTOS>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    
    @author Hubert Paul Bernard VIRTOS
    
*/

package org.opensourcedea.dea;

import java.io.Serializable;

/**
 * The Variables of a DEA Problem. This holds all the information relative to the 
 * variables of a DEA Problem.
 * @author Hubert.Virtos
 *
 */
public class Variable implements Serializable {
	/**
	 * The serial version UID (auto generated)
	 */
	private static final long serialVersionUID = 4821189688327962633L;
	
	
	private String[] variableName;
	private VariableOrientation [] variableOrientation;
	private VariableType[] variableType;
	
	
	public Variable(int varNumber){
		variableName = new String[varNumber];
		variableOrientation = new VariableOrientation[varNumber];
		variableType = new VariableType[varNumber];
		/* No real need to fill some values here, if the arrays are not filled by the user,
		 * then using the get methods will give empty arrays.
		 */
		//Arrays.fill(variableType, VariableType.STANDARD);
		//Arrays.fill(variableOrientation, VariableOrientation.INPUT);
	}
	
	
	/**
	 * Sets the Variable Names.
	 * @param varNames The Variable Names.
	 */
	public void setVariableNames(String[] varNames) {
		this.variableName = varNames;
	}
	
	/**
	 * Sets the name of a Variable.
	 * @param varIndex The index of the Variable to set the name for.
	 * @param varName The Variable Name.
	 * @throws IndexOutOfBoundsException
	 */
	public void setVariableName(int varIndex, String varName) throws IndexOutOfBoundsException {
		try {
			this.variableName[varIndex] = varName;
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Gets the names of the variables.
	 * @return The names of the variables.
	 * @IndexOutOfBoundsException
	 */
	public String[] getVariableNames() {
		return this.variableName;
	}
	
	public String getVariableName(int varIndex) throws IndexOutOfBoundsException {
		try {
			return this.variableName[varIndex];
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	
	
	/**
	 * Sets the orientations of the variables (e.g. input oriented).
	 * @param varOrientations The variable orientation.
	 */
	public void setVariableOrientations(VariableOrientation[] varOrientations) {
		this.variableOrientation = varOrientations;
	}
	
	/**
	 * Sets the orientation of a specific variable.
	 * @param varIndex The index of the variable to set the orientation for.
	 * @param varOrientation the orientation of the variable.
	 * @throws IndexOutOfBoundsException
	 */
	public void setVariableOrientation(int varIndex, VariableOrientation varOrientation)
		throws IndexOutOfBoundsException{
		try {
			this.variableOrientation[varIndex] = varOrientation;
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Gets the variables orientation.
	 * @return The orientation for all the variables.
	 */
	public VariableOrientation[] getVariableOrientations() {
		return this.variableOrientation;
	}
	
	/**
	 * Gets the orientation of a specific variable.
	 * @param varIndex The index of the variable to get the orientation of.
	 * @return The orientation of the variable.
	 * @throws IndexOutOfBoundsException
	 */
	public VariableOrientation getVariableOrientation(int varIndex) 
		throws IndexOutOfBoundsException{
		try {
			return this.variableOrientation[varIndex];
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	
	
	/**
	 * Sets the types for all the variables (e.g. standard, non-discretionary...).
	 * @param varTypes The Types of all variables. 
	 */
	public void setVariableTypes(VariableType[] varTypes) {
		this.variableType = varTypes;
	}
	
	/**
	 * Sets the variable type for a specific variable.
	 * @param varIndex The index of the variable to set the variable type to.
	 * @param varType The type of variable.
	 * @throws IndexOutOfBoundsException
	 */
	public void setVariableType(int varIndex, VariableType varType)
		throws IndexOutOfBoundsException {
		try {
			this.variableType[varIndex] = varType;
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Gets the variable types for all the variables.
	 * @return The variable types of all the variables.
	 */
	public VariableType[] getVariableTypes() {
		return this.variableType;
	}
	
	/**
	 * Gets the variable type of a specific variable.
	 * @param varIndex The index of the variable to get the Type of.
	 * @return The variable Variable Type.
	 * @throws IndexOutOfBoundsException
	 */
	public VariableType getVariableType(int varIndex) throws IndexOutOfBoundsException {
		try {
			return this.variableType[varIndex];
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Gets the name of a VariableType for a specific variable. VariableTypes enums have both a 
	 * name and a description fields (e.g. NON_DISCRETIONARY, "A non-discretionary variable").
	 * This method only provides the name part of the enum (e.g. in this case, NON_DISCRETIONARY).
	 * @param varIndex The index of the variable to get the VariableType name of.
	 * @return The VariableType name of the specified variable.
	 * @throws IndexOutOfBoundsException
	 */
	public String getVariableTypeName(int varIndex) throws IndexOutOfBoundsException {
		try {
			return this.getVariableType(varIndex).getName();
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Gets the description of a VariableType for a specific variable. VariableTypes enums have both a 
	 * name and a description fields (e.g. NON_DISCRETIONARY, "A non-discretionary variable").
	 * This method only provides the description part of the enum (e.g. in this case,
	 * "A non-discretionary variable").
	 * @param varIndex The index of the variable to get the VariableType description of.
	 * @return The VariableType description of the specified variable.
	 * @throws IndexOutOfBoundsException
	 */
	public String getVariableTypeDescription(int varIndex) throws IndexOutOfBoundsException{
		try {
			return this.getVariableType(varIndex).getDescription();
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
}
