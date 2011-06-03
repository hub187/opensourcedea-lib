
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
    @version 0.1 2011-02-04
*/


package dea;

//import java.util.Arrays;

import deaModels.*;
//import java.util.ArrayList;



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
	

	private ModelType modelType;
	private String[] dmuName;
	private String[] variableName;
	private VariableType [] variableType;
	private double [] [] dataMatrix;
	private double rtsLowerBound;
	private double rtsUpperBound;
	private dea.DEAPSolution solution;
	
	
	/**
	 * 
	 * @param nbDMUs The number of DMUs in the DEA problem to solve.
	 * @param nbVariables The number of variables in the DEA Problem to solve.
	 */
	public DEAProblem(int nbDMUs, int nbVariables) {
		this.solution = new DEAPSolution(nbDMUs, nbVariables);
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
		return this.modelType;
	}
	
	/**
	 * Sets type of DEA Problem Model (e.g. CCR, SBM...)
	 * @param modelType The DEA Problem type.
	 */
	public void setModelType(ModelType modType)
	{
		this.modelType = modType;
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
	 */
	public String getDMUName(int dmuIndex)
	{
		return this.dmuName[dmuIndex];
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
	 */
	public void setDMUName(String dmuName, int dmuIndex)
	{
		this.dmuName[dmuIndex] = dmuName;
	}

	
	/**
	 * Returns the Variable Names.
	 * @return A String[] of the VariableName.
	 */
	public String[] getVariableNames()
	{
		return this.variableName;
	}
	
	/**
	 * Returns the variable name for a specific variable.
	 * @param varIndex The Variable Number in the variable array.
	 * @return The variable name for a specific variable specified.
	 */
	public String getVariableName(int varIndex)
	{
		return this.variableName[varIndex];
	}
	
	/**
	 * Sets all the Variable names.
	 * @param varNames A String[] of Variable Names.
	 */
	public void setVariableNames(String[] varNames)
	{
		this.variableName = varNames;
	}
	
	/**
	 * Sets the name of the variable at position VariableNumber in the Variable Names Array.
	 * @param varName The Name of the Variable.
	 * @param varIndex The Variable position in the Variable Array.
	 */
	public void setVariableName(String varName, int varIndex)
	{
		this.variableName[varIndex] = varName;
	}
	
	
	/**
	 * Returns an array of VariableTypes.
	 * @return An array of variable types.
	 */
	public VariableType[] getVariableTypes()
	{
		return this.variableType;
	}
	
	/**
	 * Gets the Variable type of the Variable specified.
	 * @param varIndex The position of the variable in the Variable Array.
	 * @return The Variable Type of the Variable at the position specified.
	 */
	public VariableType getVariableType(int varIndex)
	{
		return this.variableType[varIndex];
	}
	
	/**
	 * Sets the variable type for all the variables.
	 * @param varTypes An array of VariableType.
	 */
	public void setVariableTypes(VariableType[] varTypes)
	{
		this.variableType = varTypes;
	}
	
	/**
	 * Sets the VariableType for the specified Variable.
	 * @param variableType The VariableType of the specified variable.
	 * @param varIndex The position of the variable in the Variable Array.
	 */
	public void setVariableType(VariableType varType, int varIndex)
	{
		this.variableType[varIndex] = varType;
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
	 */
	public void setDataMatrix(double value, int dmuIndex, int varIndex)
	{
		this.dataMatrix[dmuIndex] [varIndex] = value;
	}
	
	/**
	 * Sets the Lower Bound limit for General Return To Scale DEA Models.
	 * @param lowerB A double corresponding to the General RTS Lower Bound Limit.
	 */
	public void setRTSLowerBound(double lowerB) throws InvalidPropertyValue {
		if(lowerB < 0 || lowerB > 1) {
			throw new InvalidPropertyValue("Lower Bound must be as follows: 0 <= LowerB <= 1.");
		}
		this.rtsLowerBound = lowerB;
	}
	
	/**
	 * Gets the Lower Bound limit for General Return To Scale DEA Models.
	 * @return RTSLowerBound A double corresponding to the General RTS Lower Bound Limit.
	 */
	public double getRTSLowerBound() {
		return this.rtsLowerBound;
	}
	
	/**
	 * Sets the Upper Bound limit for General Return To Scale DEA Models.
	 * @param upperB A double corresponding to the General RTS Upper Bound Limit.
	 */
	public void setRTSUpperBound(double upperB) throws InvalidPropertyValue {
		if(upperB < 1) {
			throw new InvalidPropertyValue("Upper Bound must be greater or equal to 1.");
		}
		this.rtsUpperBound = upperB;
	}
	
	/**
	 * Gets the Upper Bound limit for General Return To Scale DEA Models.
	 * @return RTSUpperBound A double corresponding to the General RTS Upper Bound Limit.
	 */
	public double getRTSUpperBound() {
		return this.rtsUpperBound;
	}
	
	 //////////////////////////////////////////////////////////////////////////
	//						Mostly Internal Use								//
   //////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets the number of DMUs in the DEA Problem
	 * @return The number of DMUs.
	 */
	public int getNumberOfDMUs() {
		int nbDMUs;
		nbDMUs = this.dataMatrix.length;
		return nbDMUs;
	}
	
	/**
	 * Gets the number of Outputs of the DEA Problem
	 * @return The number of Outputs.
	 */
	public int getNumberOfOutputs() {
		int nbOutputs = 0;
		for(int i = 0; i < this.variableType.length; i++) {
			if(this.variableType[i] == VariableType.OUTPUT) {
				nbOutputs++;
			}
		}
		return nbOutputs;
	}
	
	/**
	 * Gets the number of Inputs of the DEA Problem
	 * @return The number of Inputs.
	 */
	public int getNumberOfInputs() {
		int nbInputs = 0;
		for(int i = 0; i < variableType.length; i++) {
			if(variableType[i] == VariableType.INPUT) {
				nbInputs++;
			}
		}
		return nbInputs;
	}
	
	/**
	 * Gets the number of Variables in the DEA Problem
	 * @return The number of Variables.
	 */
	public int getNumberOfVariables() {
		int nbVariables;
		nbVariables = this.dataMatrix[0].length;
		return nbVariables;
	}
	
	/**
	 * Gets the Transpose of the Data Matrix
	 * @param returnsNegativeTranspose A boolean. If true, all values of the TransposedMatrix are timed by -1.
	 * @return A double[] [] corresponding to the transpose of the Data Matrix
	 */
	public double [] [] getTranspose(boolean returnsNegativeTranspose) {
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
	
	
	/**
	 * This method return the DEA Model Orientation (Input, Output or Non oriented). This property cannot
	 * be set as it is a inherent property of the ModelType of the DEA Problem. In order to set
	 * the ModelOrientation property, it is necessary to set the corresponding ModelType.
	 * @return The DEAModelOrientation of the DEA Problem.
	 * @throws MissingData Thrown if the ModelType is not set.
	 */
	public ModelOrientation getModelOrientation() throws MissingData {
		if(this.getModelType() != null) {
			return this.getModelType().getOrientation() ;
		}
		else {
			throw new MissingData("The DEA Model Type is not set!");
		}
			
	}
	
	/**
	 * This method return the DEA Model Efficiency Type (e.g. MIX or TECH). This property cannot
	 * be set as it is a inherent property of the ModelType of the DEA Problem. In order to set
	 * the ModelEfficiencyType property, it is necessary to set the corresponding ModelType.
	 * @return The DEAEfficiencyType of the DEA Problem.
	 * @throws MissingData Thrown if the ModelType is not set.
	 */
	public EfficiencyType getModelEfficiencyType() throws MissingData {
		if(this.getModelType() != null) {
			return this.getModelType().getEfficiencyType();
		}
		else {
			throw new MissingData("The DEA Model Type is not set!");
		}
	}
	
	/**
	 * This method return the DEA Model RTS Type (e.g. Constant, Variable, Increasing...). This property cannot
	 * be set as it is a inherent property of the ModelType of the DEA Problem. In order to set
	 * the DEAReturnToScale property, it is necessary to set the corresponding ModelType.
	 * @return The DEAReturnToScale of the DEA Problem.
	 * @throws MissingData Thrown if the ModelType is not set. 
	 */
	public ReturnToScale getModelRTS() throws MissingData {
		if(this.getModelType() != null) {
			return this.getModelType().getReturnToScale();
		}
		else {
			throw new MissingData("The DEA Model Type is not set!");
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
	public void solve() throws DEAException, MissingData, InconsistentNoOfDMUs, InconsistentNoOfVariables {
		
		checkDataBeforeSolving();
		
		try {
			switch (this.modelType) {
				case CCRI: this.solution = CCR.solveCCR(this); break;
				
				case CCRO: this.solution = CCR.solveCCR(this); break;
				
				case BCCI: this.solution = BCC.solveBCC(this); break;
				
				case BCCO: this.solution = BCC.solveBCC(this); break;
				
				case GRSI: this.solution = BCC.solveBCC(this); break;
				
				case GRSO: this.solution = BCC.solveBCC(this); break;
				
				case IRSI:
					this.setRTSLowerBound(1);
					this.setRTSUpperBound(1E30); //LpSolve cannot solve with Double.POSITIVE_INFINITY (numerical instability => model is infeasible, last best value of -1e+30)
					this.solution = BCC.solveBCC(this);
					break;
				
				case IRSO:
					this.setRTSLowerBound(1);
					this.setRTSUpperBound(1E30);
					this.solution = BCC.solveBCC(this);
					break;
				
				case DRSI:
					this.setRTSLowerBound(0);
					this.setRTSUpperBound(1);
					this.solution = BCC.solveBCC(this);
					break;
				
				case DRSO:
					this.setRTSLowerBound(0);
					this.setRTSUpperBound(1);
					this.solution = BCC.solveBCC(this);
					break;
				
				case SBM: this.solution = SBM.solveSBM(this); break;
				
				case SBMV: this.solution = SBM.solveSBM(this); break;
				
				case SBMGRS: this.solution = SBM.solveSBM(this); break;
				
				case SBMI: this.solution = SBMI.solveSBMI(this); break;
				
				case SBMIV: this.solution = SBMI.solveSBMI(this); break;				
				
				case SBMIGRS: this.solution = SBMI.solveSBMI(this); break;
				
				case SBMO: this.solution = SBMO.solveSBMO(this); break;
				
				case SBMOV: this.solution = SBMO.solveSBMO(this); break;
				
				case SBMOGRS: this.solution = SBMO.solveSBMO(this); break;
			}
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		
	}


	private void checkDataBeforeSolving() throws MissingData,
			InconsistentNoOfVariables, InconsistentNoOfDMUs {
		if(this.dataMatrix == null || this.dmuName == null || this.modelType == null ||
				this.variableName == null || this.variableType == null) {
			//MissingData e = new MissingData("some text to describe the error");
			throw new MissingData();
		}
		
		int lenX = this.dataMatrix[0].length;
		if(lenX != this.variableName.length || lenX != this.variableType.length) {
			throw new InconsistentNoOfVariables();
		}
		
		int lenY = this.dataMatrix.length;
		if(lenY != this.dmuName.length) {
			throw new InconsistentNoOfDMUs();
		}
		
		if(this.getModelType().getReturnToScale() == ReturnToScale.GENERAL) {
			if(this.rtsUpperBound == 0) {
				throw new MissingData("RTS Bounds not set correctly!");
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
	 * @throws DEAExceptions 
	 */
	public double[] getObjectives()
	{
		return this.solution.getObjectives();
	}
	
	/**
	 * Returns the Objective value for the specified DMU.
	 * @param dmuNumber The number of the DMU in the Objective Array.
	 * @return The Objective value for the specified DMU.
	 */
	public double getObjective(int dmuNumber)
	{
		return this.solution.getObjectives()[dmuNumber];
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
	 */
	public double[] getSlacks(int dmuNumber)
	{
		return this.solution.getSlacks(dmuNumber);
	}
	
	/**
	 * Returns the slack value for the specified DMU and Variable Number.
	 * @param dmuNumber The number of the DMU
	 * @param varNumber The Number of the Variable
	 * @return The slack value for the specified DMU and Variable Number
	 */
	public double getSlack(int dmuNumber, int varNumber)
	{
		return this.solution.getSlack(dmuNumber, varNumber);
	}
	
	/**
	 * Returns the Slacks for all the DMUs.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all the slacks for all the DMUs.
	 */
	public double[] [] getSlacks()
	{
		return this.solution.getSlacks(); // Slacks;
	}
	
	
	
	/**
	 * Returns the weights of the specified DMU.
	 * @param dmuNumber The Number of the DMU.
	 * @return A double[] corresponding to the weights for the specified DMU.
	 */
	public double[] getWeight(int dmuNumber)
	{
		return this.solution.getWeights(dmuNumber);
	}
	
	/**
	 * Returns the specific weight value for the specified DMU and Variable number.
	 * @param dmuNumber The number of the DMU
	 * @param varNumber The number of the Variable
	 * @return A specific weight value.
	 */
	public double getWeight(int dmuNumber, int varNumber)
	{
		return this.solution.getWeight(dmuNumber, varNumber);
	}
	
	/**
	 * Returns the weights for all the DMUs
	 * @return A double[NbDMUs] [NbVariables] corresponding to the weights of all the DMUs
	 */
	public double[] [] getWeight()
	{
		return this.solution.getWeights();
	}
	
	
	
	/**
	 * Returns the projections for the specified DMU.
	 * @param dmuNumber The number of the DMU
	 * @return The projections for the specific DMU
	 */
	public double[] getProjections(int dmuNumber)
	{
		return this.solution.getProjections(dmuNumber);
	}
	
	/**
	 * Returns the projections for all the DMU
	 * @return a double[NbDMUs] [NbVariables] of all the projections
	 */
	public double[] [] getProjections()
	{
		return this.solution.getProjections();
	}
	

	
	
	/* These methods only return all the values unlike the previous one which could be accessed value by value.
	 * This is because it would be inefficient to access the values below individually.*/
	
	/**
	 * Returns the projection percentages
	 * @param dmuNumber The DMU Number
	 * @return A double[] of the projection percentages for the specified DMU
	 */	
	public double[] getProjectionPercentages(int dmuNumber)
	{
		double[] projectionPercentages = new double[this.solution.getProjections()[0].length];
		double varValue;
		double[] projections = this.getProjections(dmuNumber);
		//Loop through each variable
		for(int i = 0; i < this.solution.getProjections()[0].length; i++) {
			varValue = this.getDataMatrix(dmuNumber, i);
			if(this.getVariableType(i) == VariableType.INPUT) {
				projectionPercentages[i] = (varValue -  projections[i]) / varValue * -1;
			}
			else {
				projectionPercentages[i] = (varValue -  projections[i]) / varValue;
			}
		}
		return projectionPercentages;
	}
	
	/**
	 * Returns the projection percentages for all DMUs
	 * @return A double[NbDMUs] [NbVariables] of the projection percentages for all the DMUs.
	 */	
	public double[] [] getProjectionPercentages()
	{
		
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
				if(this.getVariableType(j) == VariableType.INPUT) {
					projectionPercentages[i] [j] = (varValue -  projections[j]) / varValue * -1;
				}
				else {
					projectionPercentages[i] [j] = (varValue -  projections[j]) / varValue;
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
	 */
	public int[] getRanks(boolean highestIsOne, RankingType typeOfRanking, int precision)
	{
		/*This needs to be calculated here (i.e. if requested by the user) instead of
		 * calculating it post optimisation automatically (which would slow the optimisation process.*/
		int[] ranksArray;
		ranksArray = Rank.getRanks(this.solution.getObjectives(), highestIsOne, typeOfRanking, precision);
		return ranksArray;
	
	}
	
	/**
	 * Returns the reference set of a DEAProblem solution.
	 * @return An ArrayList<ArrayList<Integer>> corresponding to each DMU reference set.
	 */
//	public ArrayList<Integer>[] getReferenceSet() {
//
//		@SuppressWarnings("unchecked")
//		ArrayList<Integer>[] ReferenceSets = (ArrayList<Integer>[])new ArrayList[this._DataMatrix.length];
//		
//		//loop through all DMUs
//		for(int i = 0; i < this.getNumberOfDMUs(); i++) {
//			//loop through all lambdas
//			ReferenceSets[i] = findNonZeroLambdas(i);		
//		}
//		
//		return ReferenceSets;
//	}
//	
//	
//	public ArrayList<Integer> getReferenceSet(int DMUIndex) {
//
//		ArrayList<Integer> ReferenceSet = findNonZeroLambdas(DMUIndex);
//		return ReferenceSet;
//	}
//
//
//
//
//	private ArrayList<Integer> findNonZeroLambdas( int i) {
//		ArrayList<Integer> nonZeroLambdaIndexes = new ArrayList<Integer>();
//		for(int j = 0; j < this.getNumberOfDMUs(); j++) {
//			if(this.getLambda(i, j) > 0.00000000001) {
//				/* Might want to add a sensitivity parameter in the getReferenceSet method
//				 * instead of hard-coding it in the > above.*/
//				nonZeroLambdaIndexes.add(j);
//			}
//			
//		}
//		return nonZeroLambdaIndexes;
//	}
	
	
	//Calculate weighted data


}


