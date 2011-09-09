
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

//import java.util.Arrays;

import java.util.ArrayList;

import org.opensourcedea.models.*;




/**
 *
 * <p>
 * Instances of this class are DEA Problems. A DEA Problem Object can hold all the information necessary to define a DEA Problem.
 * <p>
 * This class needs be instantiated in order to:
 * <ul>
 * <li>Define the DEA Problem (e.g. Model type, Variable Names, Variable types, Model orientation, Data matrix)</li>
 * <li>Solve the DEA Problem</li>
 * <li>Retrieve the DEA Problem solution</li>
 * </ul>
 * <p>
 * @author Hubert Virtos
 * */
public class DEAProblem {
	
	 //////////////////////////////////////////////////////////////////////////
	//				Properties of the DEAProblem Object						//
   //////////////////////////////////////////////////////////////////////////
	
	private String[] dmuName;
	private double [] [] dataMatrix;
	private ModelDetails modelDetails;
	private Variable variable;
	private org.opensourcedea.dea.DEAPSolution solution;
	private Model model;
	
	
	/**
	 * 
	 * @param nbDMUs The number of DMUs in the DEA problem to solve.
	 * @param nbVariables The number of variables in the DEA Problem to solve.
	 */
	public DEAProblem(int nbDMUs, int nbVariables) {
		this.solution = new DEAPSolution(nbDMUs, nbVariables);
		this.variable = new Variable(nbVariables);
		this.modelDetails = new ModelDetails();
	}
	


	
	
	 //////////////////////////////////////////////////////////////////////////
	//								Get / Set Problem						//
   //////////////////////////////////////////////////////////////////////////
	
	

	
	/**
	 * Returns Model Type of the DEA Problem (e.g. CCR, SBM...)
	 * @return ModelType The Model Type of the DEA Problem
	 */
	public ModelType getModelType()
	{
		return this.modelDetails.getModelType();
	}
	
	/**
	 * Sets type of DEA Problem Model (e.g. CCR, SBM...)
	 * @param modType The DEA Problem type.
	 */
	public void setModelType(ModelType modType)
	{
		/* The line below should be replaced by a switch statement when
		 * the refactoring is finished.*/
		this.modelDetails.setModelType(modType);
		
		try {
			if(this.getModelType() == ModelType.IRS_I ||
					this.getModelType() == ModelType.IRS_O){
				this.setRTSLowerBound(1);
				this.setRTSUpperBound(1E30); //LpSolve cannot solve with Double.POSITIVE_INFINITY (numerical instability => model is infeasible, last best value of -1e+30)
			}
			else if (this.getModelType() == ModelType.DRS_I ||
					this.getModelType() == ModelType.DRS_O){
				this.setRTSLowerBound(0);
				this.setRTSUpperBound(1);
			}
		}
		catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}

		
		if(this.getModelType() == ModelType.BCC_I ||
				this.getModelType() == ModelType.BCC_O ||
				this.getModelType() == ModelType.GRS_I ||
				this.getModelType() == ModelType.GRS_O ||
				this.getModelType() == ModelType.IRS_I ||
				this.getModelType() == ModelType.IRS_O ||
				this.getModelType() == ModelType.DRS_I ||
				this.getModelType() == ModelType.DRS_O) {
			model = new BCC2();
		}
	}

	

	/**
	 * Returns the Names of all the DMUs
	 * @return DMUName A String[] of the DMU Names.
	 */
	public String[] getDMUNames()
	{
		return this.dmuName;
	}
	
	/**
	 * Returns the name of a specific DMU.
	 * @param dmuIndex The number of the DMU (position) in the Array.
	 * @return DMUName The DMU name of the specific DMU at the position specified.
	 * @throws IndexOutOfBoundsException 
	 */
	public String getDMUName(int dmuIndex) throws IndexOutOfBoundsException
	{
		try {
			return this.dmuName[dmuIndex];
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Sets DMU Names of all DMUs
	 * @param dmuNames The String[] of the DMU Names.
	 */
	public void setDMUNames(String[] dmuNames)
	{
		this.dmuName = dmuNames;
	}
	
	/**
	 * Returns a DMU Name for the the DMU Number specified.
	 * @param dmuName The Name of the DMU.
	 * @param dmuIndex The DMU Number (position in the array) where to put the name.
	 * @throws IndexOutOfBoundsException 
	 */
	public void setDMUName(String dmuName, int dmuIndex) throws IndexOutOfBoundsException
	{
		try {
			this.dmuName[dmuIndex] = dmuName;
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}

	
	/**
	 * Returns the Variable Names.
	 * @return A String[] of the VariableName.
	 */
	public String[] getVariableNames()
	{
		return this.variable.getVariableNames();
	}
	
	/**
	 * Returns the variable name for a specific variable.
	 * @param varIndex The Variable Number in the variable array.
	 * @return The variable name for a specific variable specified.
	 * @throws IndexOutOfBoundsException 
	 */
	public String getVariableName(int varIndex) throws IndexOutOfBoundsException
	{
		try {
			return this.variable.getVariableName(varIndex);
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Sets all the Variable names.
	 * @param varNames A String[] of Variable Names.
	 */
	public void setVariableNames(String[] varNames)
	{
		this.variable.setVariableNames(varNames);
	}
	
	/**
	 * Sets the name of the variable at position VariableNumber in the Variable Names Array.
	 * @param varName The Name of the Variable.
	 * @param varIndex The Variable position in the Variable Array.
	 * @throws IndexOutOfBoundsException 
	 */
	public void setVariableName(String varName, int varIndex) throws IndexOutOfBoundsException
	{
		try {
			this.variable.setVariableName(varIndex, varName);
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	
	/**
	 * Returns an array of VariableTypes.
	 * @return An array of variable types.
	 */
	public VariableType[] getVariableTypes()
	{
		return this.variable.getVariableTypes();
	}
	
	/**
	 * Gets the Variable type of the Variable specified.
	 * @param varIndex The position of the variable in the Variable Array.
	 * @return The Variable Type of the Variable at the position specified.
	 * @throws IndexOutOfBoundsException 
	 */
	public VariableType getVariableType(int varIndex) throws IndexOutOfBoundsException
	{
		try {
			return this.variable.getVariableType(varIndex);
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Sets the variable type for all the variables.
	 * @param varTypes An array of VariableType.
	 */
	public void setVariableTypes(VariableType[] varTypes)
	{
		this.variable.setVariableTypes(varTypes);
	}
	
	/**
	 * Sets the VariableType for the specified Variable.
	 * @param varIndex The position of the variable in the Variable Array.
	 * @param varType The VariableType of the specified variable.
	 * @throws IndexOutOfBoundsException 
	 */
	public void setVariableType(int varIndex, VariableType varType) throws IndexOutOfBoundsException
	{
		try {
			this.variable.setVariableType(varIndex, varType);
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	
	
	
	
	
	
	/**
	 * Sets the model Variable Orientations
	 * @param varOrientations the VariableOrientation[] array.
	 */
	public void setVariableOrientations(VariableOrientation[] varOrientations) {
		this.variable.setVariableOrientations(varOrientations);
	}
	
	/**
	 * Sets the model variable orientation for the specific variable at varIndex.
	 * @param varIndex the variable index
	 * @param varOrientation the variable orientation type
	 */
	public void setVariableOrientation(int varIndex, VariableOrientation varOrientation) {
		this.variable.setVariableOrientation(varIndex, varOrientation);
	}
	
	/**
	 * Returns the model variable orientation.
	 * @return the variable orientations as (VariableOrientation[]).
	 */
	public VariableOrientation[] getVariableOrientation() {
		return this.variable.getVariableOrientations();
	}
	
	/**
	 * 
	 * @param varIndex the variable index
	 * @return the variable orientation.
	 */
	public VariableOrientation getVariableOrientation(int varIndex) {
		return this.variable.getVariableOrientation(varIndex);
	}
	
	
	
	
	
	
	
	/**
	 * Returns the matrix of values.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all values.
	 */
	public double[] [] getDataMatrix()
	{
		return this.dataMatrix;
	}
	
	/**
	 * Returns a specific variable value  from the Data Matrix for the DMU specified.
	 * @param dmuIndex The number of DMU in the DMU array
	 * @param varIndex The number of the variable in the Variable Array
	 * @return a double corresponding to the Variable value.
	 */
	public double getDataMatrix(int dmuIndex, int varIndex)
	{
		return dataMatrix[dmuIndex] [varIndex];
	}
	
	/**
	 * Sets the whole Data Matrix.
	 * @param dMatrix The double [NbDMUs] [NbVariable] array of the problem values.
	 */
	public void setDataMatrix(double[] [] dMatrix)
	{
		this.dataMatrix = dMatrix;
	}
	
	/**
	 * Sets a value for a specific DMU.
	 * @param value The specific value to set.
	 * @param dmuIndex The DMU Number for which to set the value.
	 * @param varIndex The Variable Number for which to set the value.
	 * @throws IndexOutOfBoundsException 
	 */
	public void setDataMatrix(double value, int dmuIndex, int varIndex) throws IndexOutOfBoundsException
	{
		try {
			this.dataMatrix[dmuIndex] [varIndex] = value;
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	/**
	 * Sets the Lower Bound limit for General Return To Scale DEA Models.
	 * @param lowerB A double corresponding to the General RTS Lower Bound Limit.
	 */
	public void setRTSLowerBound(double lowerB) throws InvalidPropertyValueException {
		if(lowerB < 0 || lowerB > 1) {
			throw new InvalidPropertyValueException("Lower Bound must be as follows: 0 <= LowerB <= 1.");
		}
		this.modelDetails.setRTSLowerBound(lowerB);
	}
	
	/**
	 * Gets the Lower Bound limit for General Return To Scale DEA Models.
	 * @return RTSLowerBound A double corresponding to the General RTS Lower Bound Limit.
	 */
	public double getRTSLowerBound() {
		return this.modelDetails.getRTSLowerBound();
	}
	
	/**
	 * Sets the Upper Bound limit for General Return To Scale DEA Models.
	 * @param upperB A double corresponding to the General RTS Upper Bound Limit.
	 */
	public void setRTSUpperBound(double upperB) throws InvalidPropertyValueException {
		if(upperB < 1) {
			throw new InvalidPropertyValueException("Upper Bound must be greater or equal to 1.");
		}
		this.modelDetails.setRTSUpperBound(upperB);
	}
	
	/**
	 * Gets the Upper Bound limit for General Return To Scale DEA Models.
	 * @return RTSUpperBound A double corresponding to the General RTS Upper Bound Limit.
	 */
	public double getRTSUpperBound() {
		return this.modelDetails.getRTSUpperBound();
	}
	
	 //////////////////////////////////////////////////////////////////////////
	//						Mostly Internal Use								//
   //////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets the number of DMUs in the DEA Problem
	 * @return The number of DMUs.
	 * @throws MissingDataException 
	 */
	public int getNumberOfDMUs() throws MissingDataException {
		int nbDMUs;
		try {
			nbDMUs = this.dataMatrix.length;
			return nbDMUs;
		}
		catch (Exception e) {
			throw new MissingDataException("The Data Matrix is null!");
		}
		
	}
	
	/**
	 * Gets the number of Outputs of the DEA Problem
	 * @return The number of Outputs.
	 * @throws MissingDataException 
	 */
	public int getNumberOfOutputs() throws MissingDataException {
		int nbOutputs = 0;
		try {
			for(int i = 0; i < this.variable.getVariableOrientations().length; i++) {
				if(this.variable.getVariableOrientation(i) == VariableOrientation.OUTPUT) {
					nbOutputs++;
				}
			}
			return nbOutputs;
		}
		catch (Exception e) {
			throw new MissingDataException("The VariableType array is null!");
		}
	}
	
	/**
	 * Gets the number of Inputs of the DEA Problem
	 * @return The number of Inputs.
	 * @throws MissingDataException 
	 */
	public int getNumberOfInputs() throws MissingDataException {
		int nbInputs = 0;
		try {
			for(int i = 0; i < this.variable.getVariableOrientations().length; i++) {
				if(this.variable.getVariableOrientation(i) == VariableOrientation.INPUT) {
					nbInputs++;
				}
			}
			return nbInputs;
		}
		catch (Exception e) {
			throw new MissingDataException("The VariableType array is null!");
		}
	}
	
	/**
	 * Gets the number of Variables in the DEA Problem
	 * @return The number of Variables.
	 * @throws MissingDataException 
	 */
	public int getNumberOfVariables() throws MissingDataException {
		int nbVariables;
		try {
			nbVariables = this.dataMatrix[0].length;
			return nbVariables;
		}
		catch (Exception e) {
			throw new MissingDataException("The Data Matrix is null!");
		}
	}
	
	/**
	 * Gets the Transpose of the Data Matrix
	 * @param returnsNegativeTranspose A boolean. If true, all values of the TransposedMatrix are timed by -1.
	 * @return A double[] [] corresponding to the transpose of the Data Matrix
	 * @throws MissingDataException 
	 */
	public double [] [] getTranspose(boolean returnsNegativeTranspose) throws MissingDataException {
		try {
			double [] [] transposedMatrix = new double [this.dataMatrix[0].length] [this.dataMatrix.length];
			
			if(returnsNegativeTranspose == false) {
				for (int i = 0; i < this.dataMatrix.length; i++)
		            for (int j = 0; j < this.dataMatrix[0].length; j++)
		                transposedMatrix[j][i] = this.dataMatrix[i][j];
			}
			else {
				for (int i = 0; i < this.dataMatrix.length; i++)
		            for (int j = 0; j < this.dataMatrix[0].length; j++)
		                transposedMatrix[j][i] = this.dataMatrix[i][j] * -1;
			}
					
			return transposedMatrix;
		}
		catch (Exception e) {
			throw new MissingDataException("The Data Matrix is null!");
		}
	}
	
	
	/**
	 * This method return the DEA Model Orientation (Input, Output or Non oriented). This property cannot
	 * be set as it is a inherent property of the ModelType of the DEA Problem. In order to set
	 * the ModelOrientation property, it is necessary to set the corresponding ModelType.
	 * @return The DEAModelOrientation of the DEA Problem.
	 * @throws MissingDataException Thrown if the ModelType is not set.
	 */
	public ModelOrientation getModelOrientation() throws MissingDataException {
		if(this.getModelType() != null) {
			return this.getModelType().getOrientation() ;
		}
		else {
			throw new MissingDataException("The DEA Model Type is not set!");
		}
			
	}
	
	/**
	 * This method return the DEA Model Efficiency Type (e.g. MIX or TECH). This property cannot
	 * be set as it is a inherent property of the ModelType of the DEA Problem. In order to set
	 * the ModelEfficiencyType property, it is necessary to set the corresponding ModelType.
	 * @return The DEAEfficiencyType of the DEA Problem.
	 * @throws MissingDataException Thrown if the ModelType is not set.
	 */
	public EfficiencyType getModelEfficiencyType() throws MissingDataException {
		if(this.getModelType() != null) {
			return this.getModelType().getEfficiencyType();
		}
		else {
			throw new MissingDataException("The DEA Model Type is not set!");
		}
	}
	
	/**
	 * This method return the DEA Model RTS Type (e.g. Constant, Variable, Increasing...). This property cannot
	 * be set as it is a inherent property of the ModelType of the DEA Problem. In order to set
	 * the DEAReturnToScale property, it is necessary to set the corresponding ModelType.
	 * @return The DEAReturnToScale of the DEA Problem.
	 * @throws MissingDataException Thrown if the ModelType is not set. 
	 */
	public ReturnToScale getModelRTS() throws MissingDataException {
		if(this.getModelType() != null) {
			return this.getModelType().getReturnToScale();
		}
		else {
			throw new MissingDataException("The DEA Model Type is not set!");
		}
	}
	
	 //////////////////////////////////////////////////////////////////////////
	//								Solve Problem							//
   //////////////////////////////////////////////////////////////////////////
	
	/**
	 * Solves the specific DEA Problem.
	 * <p> The Model needs to have all the required parameters in order for this to work.
	 * <p> There is no check at present, if the parameters are incorrect, the library will simply crash.
	 * <p> The switch in the solve() method call a separate method for each model. Each of these method 
	 * returns a DEAPSolution object which is passed to this._Solution (and can then be accessed using the getSolutionItems
	 * methods).
	 */
	public void solve() throws MissingDataException, InconsistentNoOfDMUsException, InconsistentNoOfVariablesException, DEAException {
		
		checkDataBeforeSolving();
		
		
			/*DEBUG ONLY (NECESSARY FOR ALL THE TESTS TO WORK DURING THE REFACTORING)
			To be replaced by 'model.solve(this);' only*/
			if(this.getModelType() == ModelType.BCC_I ||
					this.getModelType() == ModelType.BCC_O ||
					this.getModelType() == ModelType.GRS_I ||
					this.getModelType() == ModelType.GRS_O ||
					this.getModelType() == ModelType.IRS_I ||
					this.getModelType() == ModelType.IRS_O ||
					this.getModelType() == ModelType.DRS_I ||
					this.getModelType() == ModelType.DRS_O){
				this.solution = model.solve(this, true);
				return;
			}
			
			/* Will get rid of this try catch as well as whole switch statement
			 * after the model.solve method works for all the models.*/
			try {
			switch (this.modelDetails.getModelType()) {
				case CCR_I: this.solution = CCR.solveCCR(this); break;
				
				case CCR_O: this.solution = CCR.solveCCR(this); break;
				
				case BCC_I: this.solution = BCC.solveBCC(this); break;
				
				case BCC_O: this.solution = BCC.solveBCC(this); break;
				
				case GRS_I: this.solution = BCC.solveBCC(this); break;
				
				case GRS_O: this.solution = BCC.solveBCC(this); break;
				
				case IRS_I: case IRS_O:
					this.setRTSLowerBound(1);
					this.setRTSUpperBound(1E30); //LpSolve cannot solve with Double.POSITIVE_INFINITY (numerical instability => model is infeasible, last best value of -1e+30)
					this.solution = BCC.solveBCC(this);
					break;
				
				case DRS_I: case DRS_O:
					this.setRTSLowerBound(0);
					this.setRTSUpperBound(1);
					this.solution = BCC.solveBCC(this);
					break;
				
				case NC_I_IRS: case NC_O_IRS: case ND_I_IRS: case ND_O_IRS:
					this.setRTSLowerBound(1);
					this.setRTSUpperBound(1E30);
					this.solution = NC_ND.solveNC(this);
					break;
				
				case NC_I_DRS: case NC_O_DRS: case ND_I_DRS: case ND_O_DRS:
					this.setRTSLowerBound(0);
					this.setRTSUpperBound(1);
					this.solution = NC_ND.solveNC(this);
					break;
				
				case SBM: this.solution = SBM.solveSBM(this); break;
				
				case SBM_V: this.solution = SBM.solveSBM(this); break;
				
				case SBM_GRS: this.solution = SBM.solveSBM(this); break;
				
				case SBM_I: this.solution = SBMI.solveSBMI(this); break;
				
				case SBMI_V: this.solution = SBMI.solveSBMI(this); break;				
				
				case SBM_I_GRS: this.solution = SBMI.solveSBMI(this); break;
				
				case SBM_O: this.solution = SBMO.solveSBMO(this); break;
				
				case SBM_O_V: this.solution = SBMO.solveSBMO(this); break;
				
				case SBM_O_GRS: this.solution = SBMO.solveSBMO(this); break;
				
				case NC_I: this.solution = NC_ND.solveNC(this); break;
				
				case NC_O: this.solution = NC_ND.solveNC(this); break;
				
				case NC_I_V: this.solution = NC_ND.solveNC(this); break;
				
				case NC_O_V: this.solution = NC_ND.solveNC(this); break;
				
				case NC_I_GRS: this.solution = NC_ND.solveNC(this); break;
				
				case NC_O_GRS: this.solution = NC_ND.solveNC(this); break;
				
				case ND_I: this.solution = NC_ND.solveNC(this); break;
				
				case ND_I_V: this.solution = NC_ND.solveNC(this); break;
				
				case ND_I_GRS: this.solution = NC_ND.solveNC(this); break;
				
				case ND_O: this.solution = NC_ND.solveNC(this); break;
				
				case ND_O_V: this.solution = NC_ND.solveNC(this); break;
				
				case ND_O_GRS: this.solution = NC_ND.solveNC(this); break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}


	private void checkDataBeforeSolving() throws MissingDataException,
			InconsistentNoOfVariablesException, InconsistentNoOfDMUsException {
		if(this.dataMatrix == null || this.dmuName == null || this.modelDetails.getModelType() == null ||
				this.variable.getVariableNames() == null || this.variable.getVariableOrientations() == null) {
			//MissingData e = new MissingData("some text to describe the error");
			throw new MissingDataException();
		}
		
		int lenX = this.dataMatrix[0].length;
		if(lenX != this.variable.getVariableNames().length || lenX != this.variable.getVariableOrientations().length) {
			throw new InconsistentNoOfVariablesException();
		}
		
		int lenY = this.dataMatrix.length;
		if(lenY != this.dmuName.length) {
			throw new InconsistentNoOfDMUsException();
		}
		
		if(this.getModelType().getReturnToScale() == ReturnToScale.GENERAL) {
			if(this.modelDetails.getRTSUpperBound() == 0 ||
					this.modelDetails.getRTSUpperBound() < this.modelDetails.getRTSLowerBound()) {
				throw new MissingDataException("RTS Bounds not set correctly!");
			}
		}
	}
	
	
	 //////////////////////////////////////////////////////////////////////////////
	//						Get Problem Solution								//
 //////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the DEAProblem optimisation SolverReturnStatus. If only one optimisation for a single DMU did not return a status
	 * 'an optimal solution was found', the overall optimisation status will not return the status 'OptimalSolutionFound' but 
	 * instead the corresponding error status (e.g. OptimalSolutionNotfound).
	 * @return a SolverReturnStatus
	 */
	public SolverReturnStatus getOptimisationStatus() {
		return this.solution.getStatus();
	}
	
	/**
	 * Returns the Objectives of all the DMUs.
	 * @return A double[] corresponding to all the DMUs Objectives.
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public double[] getObjectives() throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		return this.solution.getObjectives();
	}
	
	/**
	 * Returns the Objective value for the specified DMU.
	 * @param dmuNumber The number of the DMU in the Objective Array.
	 * @return The Objective value for the specified DMU.
	 * @throws ProblemNotSolvedProperlyException
	 */
	public double getObjective(int dmuNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		
		return this.solution.getObjective(dmuNumber);

	}
		


	
//	/**
//	 * Returns the Lambda of the specified DMU and Lambda.
//	 * @param DMUNumber The DMU Number
//	 * @param Lambda The Lambda Number
//	 * @return The value of the lambda for the specified DMU and Lambda
//	 */
//	public double getLambda(int DMUNumber, int Lambda)
//	{
//		return _Solution.Lambdas[DMUNumber] [Lambda];
//	}
	
	
	
	/**
	 * Returns the Slacks for the specified DMU.
	 * @param dmuNumber The number of the DMU.
	 * @return A double[] corresponding to the slacks for the specified DMU.
	 * @throws ProblemNotSolvedProperlyException
	 */
	public double[] getSlacks(int dmuNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		
		return this.solution.getSlacks(dmuNumber);

	}
	
	/**
	 * Returns the slack value for the specified DMU and Variable Number.
	 * @param dmuNumber The number of the DMU
	 * @param varNumber The Number of the Variable
	 * @return The slack value for the specified DMU and Variable Number
	 * @throws ProblemNotSolvedProperlyException
	 */
	public double getSlack(int dmuNumber, int varNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}

		return this.solution.getSlack(dmuNumber, varNumber);
	}
	
	/**
	 * Returns the Slacks for all the DMUs.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all the slacks for all the DMUs.
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public double[] [] getSlacks() throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		return this.solution.getSlacks();
	}
	
	
	
	/**
	 * Returns the weights of the specified DMU.
	 * @param dmuNumber The Number of the DMU.
	 * @return A double[] corresponding to the weights for the specified DMU.
	 * @throws ProblemNotSolvedProperlyException
	 */
	public double[] getWeight(int dmuNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
	
		return this.solution.getWeights(dmuNumber);

	}
	
	/**
	 * Returns the specific weight value for the specified DMU and Variable number.
	 * @param dmuNumber The number of the DMU
	 * @param varNumber The number of the Variable
	 * @return A specific weight value.
	 * @throws ProblemNotSolvedProperlyException
	 */
	public double getWeight(int dmuNumber, int varNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}

		return this.solution.getWeight(dmuNumber, varNumber);

	}
	
	/**
	 * Returns the weights for all the DMUs
	 * @return A double[NbDMUs] [NbVariables] corresponding to the weights of all the DMUs
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public double[] [] getWeight() throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		return this.solution.getWeights();
	}
	
	
	
	/**
	 * Returns the projections for the specified DMU.
	 * @param dmuNumber The number of the DMU
	 * @return The projections for the specific DMU
	 * @throws ProblemNotSolvedProperlyException
	 */
	public double[] getProjections(int dmuNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}

		return this.solution.getProjections(dmuNumber);

	}
	
	/**
	 * Returns the projections for all the DMU
	 * @return a double[NbDMUs] [NbVariables] of all the projections
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public double[] [] getProjections() throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		return this.solution.getProjections();
	}
	

	
	
	/* These methods only return all the values unlike the previous one which could be accessed value by value.
	 * This is because it would be inefficient to access the values below individually.*/
	
	/**
	 * Returns the projection percentages.  Input projections are negative, output projections are
	 * positive.
	 * @param dmuNumber The DMU Number
	 * @return A double[] of the projection percentages for the specified DMU
	 * @throws ProblemNotSolvedProperlyException
	 */	
	public double[] getProjectionPercentages(int dmuNumber) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		
		double[] projectionPercentages = new double[this.solution.getProjections()[0].length];
		double varValue;
		double[] projections = this.getProjections(dmuNumber);
		//Loop through each variable
		for(int i = 0; i < this.solution.getProjections()[0].length; i++) {
			varValue = this.getDataMatrix(dmuNumber, i);
			if(this.getVariableOrientation(i) == VariableOrientation.INPUT) {
				projectionPercentages[i] = (varValue -  projections[i]) / varValue * -1;
			}
			else {
				projectionPercentages[i] = (projections[i] - varValue) / varValue;
			}
		}
		return projectionPercentages;

		
	}
	
	/**
	 * Returns the projection percentages for all DMUs. Input projections are negative, output projections are
	 * positive.
	 * @return A double[NbDMUs] [NbVariables] of the projection percentages for all the DMUs.
	 * @throws ProblemNotSolvedProperlyException
	 */	
	public double[] [] getProjectionPercentages() throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}

		double[] [] projectionPercentages =
			new double[this.getDMUNames().length] [this.solution.getProjections()[0].length];
		double varValue;
		double[] projections; 
		//Loop through all DMUs
		
		for(int i = 0; i < this.getDMUNames().length; i++) {
			projections = this.getProjections(i);
			//Loop through each variable
			for(int j = 0; j < this.solution.getProjections()[0].length; j++) {
				varValue = this.getDataMatrix(i, j);
				if(this.getVariableOrientation(j) == VariableOrientation.INPUT) {
					projectionPercentages[i] [j] = (varValue -  projections[j]) / varValue * -1;
				}
				else {
					projectionPercentages[i] [j] = (projections[j] - varValue) / varValue;
				}
			}
		}
		return projectionPercentages;

	}
	
	
	/**
	 * Returns the DMU ranks (based on Objectives value)
	 * @param highestIsOne A boolean. If true the highest value has rank '1' (first). Should generally be 'true'.
	 * @param typeOfRanking The type of ranking to use. Should generally be 'STANDARD'.
	 * @param precision An int value to specify at which decimal place the scores need to be rounded up prior to been ranked.
	 * This is important as most of the scores of the 'efficient' DMUs are not 1 but some value very close to 1 (e.g. 1.00000000000002).
	 * All precisions between 0 and 16 are taken into account, any other int value would leave the scores unchanged.
	 * @return A double[] of the ranks.
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public int[] getRanks(boolean highestIsOne, RankingType typeOfRanking, int precision) throws ProblemNotSolvedProperlyException
	{
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		/*This needs to be calculated here (i.e. if requested by the user) instead of
		 * calculating it post optimisation automatically (which would slow the optimisation process.*/
		int[] ranksArray;
		ranksArray = Rank.getRanks(this.solution.getObjectives(), highestIsOne, typeOfRanking, precision);
		return ranksArray;
	
	}
	
	
	/**
	 * Returns the solution Reference Sets (for all DMUs).
	 * @return An ArrayList<NonZeroLambda>[].
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public ArrayList<NonZeroLambda>[] getReferenceSet() throws ProblemNotSolvedProperlyException {
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		return this.solution.getReferenceSet();
	}
	
	/**
	 * Returns the Reference Set corresponding to dmuIndex.
	 * @param dmuIndex
	 * @return An ArrayList<NonZeroLambda>.
	 * @throws ProblemNotSolvedProperlyException 
	 */
	public ArrayList<NonZeroLambda> getReferenceSet(int dmuIndex) throws ProblemNotSolvedProperlyException {
		if(this.getOptimisationStatus() != SolverReturnStatus.OPTIMAL_SOLUTION_FOUND) {
			throw new ProblemNotSolvedProperlyException();
		}
		return this.solution.getReferenceSet(dmuIndex);
	}
	



}


