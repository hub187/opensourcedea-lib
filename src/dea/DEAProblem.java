
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
	

	private ModelType deapModelType;
	private String[] deapDMUName;
	private String[] deapVariableName;
	private DEAVariableType [] deapVariableType;
	private double [] [] deapDataMatrix;
	private double deapRTSLowerBound;
	private double deapRTSUpperBound;
	private dea.DEAPSolution deapSolution;
	
	
	/**
	 * 
	 * @param nbDMUs The number of DMUs in the DEA problem to solve.
	 * @param nbVariables The number of variables in the DEA Problem to solve.
	 */
	public DEAProblem(int nbDMUs, int nbVariables) {
		deapSolution = new DEAPSolution(nbDMUs, nbVariables);
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
		return deapModelType;
	}
	
	/**
	 * Sets type of DEA Problem Model (e.g. CCR, SBM...)
	 * @param modelType The DEA Problem type.
	 */
	public void setModelType(ModelType modelType)
	{
		deapModelType = modelType;
	}

	

	/**
	 * Returns the Names of all the DMUs
	 * @return DMUName A String[] of the DMU Names.
	 */
	public String[] getDMUNames()
	{
		return deapDMUName;
	}
	
	/**
	 * Returns the name of a specific DMU.
	 * @param dmuIndex The number of the DMU (position) in the Array.
	 * @return DMUName The DMU name of the specific DMU at the position specified.
	 */
	public String getDMUName(int dmuIndex)
	{
		return deapDMUName[dmuIndex];
	}
	
	/**
	 * Sets DMU Names of all DMUs
	 * @param dmuNames The String[] of the DMU Names.
	 */
	public void setDMUNames(String[] dmuNames)
	{
		deapDMUName = dmuNames;
	}
	
	/**
	 * Returns a DMU Name for the the DMU Number specified.
	 * @param dmuName The Name of the DMU.
	 * @param dmuIndex The DMU Number (position in the array) where to put the name.
	 */
	public void setDMUName(String dmuName, int dmuIndex)
	{
		deapDMUName[dmuIndex] = dmuName;
	}

	
	/**
	 * Returns the Variable Names.
	 * @return A String[] of the VariableName.
	 */
	public String[] getVariableNames()
	{
		return deapVariableName;
	}
	
	/**
	 * Returns the variable name for a specific variable.
	 * @param variableIndex The Variable Number in the variable array.
	 * @return The variable name for a specific variable specified.
	 */
	public String getVariableName(int variableIndex)
	{
		return deapVariableName[variableIndex];
	}
	
	/**
	 * Sets all the Variable names.
	 * @param variableNames A String[] of Variable Names.
	 */
	public void setVariableNames(String[] variableNames)
	{
		deapVariableName = variableNames;
	}
	
	/**
	 * Sets the name of the variable at position VariableNumber in the Variable Names Array.
	 * @param variableName The Name of the Variable.
	 * @param variableIndex The Variable position in the Variable Array.
	 */
	public void setVariableName(String variableName, int variableIndex)
	{
		deapVariableName[variableIndex] = variableName;
	}
	
	
	/**
	 * Returns an array of VariableTypes.
	 * @return An array of variable types.
	 */
	public DEAVariableType[] getVariableTypes()
	{
		return deapVariableType;
	}
	
	/**
	 * Gets the Variable type of the Variable specified.
	 * @param variableIndex The position of the variable in the Variable Array.
	 * @return The Variable Type of the Variable at the position specified.
	 */
	public DEAVariableType getVariableType(int variableIndex)
	{
		return deapVariableType[variableIndex];
	}
	
	/**
	 * Sets the variable type for all the variables.
	 * @param variableTypes An array of DEAVariableType.
	 */
	public void setVariableTypes(DEAVariableType[] variableTypes)
	{
		deapVariableType = variableTypes;
	}
	
	/**
	 * Sets the DEAVariableType for the specified Variable.
	 * @param variableType The DEAVariableType of the specified variable.
	 * @param variableIndex The position of the variable in the Variable Array.
	 */
	public void setVariableType(DEAVariableType variableType, int variableIndex)
	{
		deapVariableType[variableIndex] = variableType;
	}
	
	
	/**
	 * Returns the matrix of values.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all values.
	 */
	public double[] [] getDataMatrix()
	{
		return deapDataMatrix;
	}
	
	/**
	 * Returns a specific variable value  from the Data Matrix for the DMU specified.
	 * @param dmuIndex The number of DMU in the DMU array
	 * @param variableIndex The number of the variable in the Variable Array
	 * @return a double corresponding to the Variable value.
	 */
	public double getDataMatrix(int dmuIndex, int variableIndex)
	{
		return deapDataMatrix[dmuIndex] [variableIndex];
	}
	
	/**
	 * Sets the whole Data Matrix.
	 * @param dataMatrix The double [NbDMUs] [NbVariable] array of the problem values.
	 */
	public void setDataMatrix(double[] [] dataMatrix)
	{
		deapDataMatrix = dataMatrix;
	}
	
	/**
	 * Sets a value for a specific DMU.
	 * @param value The specific value to set.
	 * @param dmuIndex The DMU Number for which to set the value.
	 * @param variableIndex The Variable Number for which to set the value.
	 */
	public void setDataMatrix(double value, int dmuIndex, int variableIndex)
	{
		deapDataMatrix[dmuIndex] [variableIndex] = value;
	}
	
	/**
	 * Sets the Lower Bound limit for General Return To Scale DEA Models.
	 * @param lowerB A double corresponding to the General RTS Lower Bound Limit.
	 */
	public void setRTSLowerBound(double lowerB) throws InvalidPropertyValue {
		if(lowerB < 0 || lowerB > 1) {
			throw new InvalidPropertyValue("Lower Bound must be as follows: 0 <= LowerB <= 1.");
		}
		deapRTSLowerBound = lowerB;
	}
	
	/**
	 * Gets the Lower Bound limit for General Return To Scale DEA Models.
	 * @return RTSLowerBound A double corresponding to the General RTS Lower Bound Limit.
	 */
	public double getRTSLowerBound() {
		return deapRTSLowerBound;
	}
	
	/**
	 * Sets the Upper Bound limit for General Return To Scale DEA Models.
	 * @param upperB A double corresponding to the General RTS Upper Bound Limit.
	 */
	public void setRTSUpperBound(double upperB) throws InvalidPropertyValue {
		if(upperB < 1) {
			throw new InvalidPropertyValue("Upper Bound must be greater or equal to 1.");
		}
		deapRTSUpperBound = upperB;
	}
	
	/**
	 * Gets the Upper Bound limit for General Return To Scale DEA Models.
	 * @return RTSUpperBound A double corresponding to the General RTS Upper Bound Limit.
	 */
	public double getRTSUpperBound() {
		return deapRTSUpperBound;
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
		nbDMUs = deapDataMatrix.length;
		return nbDMUs;
	}
	
	/**
	 * Gets the number of Outputs of the DEA Problem
	 * @return The number of Outputs.
	 */
	public int getNumberOfOutputs() {
		int nbOutputs = 0;
		for(int i = 0; i < deapVariableType.length; i++) {
			if(deapVariableType[i] == DEAVariableType.OUTPUT) {
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
		for(int i = 0; i < deapVariableType.length; i++) {
			if(deapVariableType[i] == DEAVariableType.INPUT) {
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
		nbVariables = deapDataMatrix[0].length;
		return nbVariables;
	}
	
	/**
	 * Gets the Transpose of the Data Matrix
	 * @param returnsNegativeTranspose A boolean. If true, all values of the TransposedMatrix are timed by -1.
	 * @return A double[] [] corresponding to the transpose of the Data Matrix
	 */
	public double [] [] getTranspose(boolean returnsNegativeTranspose) {
		double [] [] transposedMatrix = new double [this.deapDataMatrix[0].length] [this.deapDataMatrix.length];
		
		if(returnsNegativeTranspose == false) {
			for (int i = 0; i < this.deapDataMatrix.length; i++)
	            for (int j = 0; j < this.deapDataMatrix[0].length; j++)
	                transposedMatrix[j][i] = this.deapDataMatrix[i][j];
		}
		else {
			for (int i = 0; i < this.deapDataMatrix.length; i++)
	            for (int j = 0; j < this.deapDataMatrix[0].length; j++)
	                transposedMatrix[j][i] = this.deapDataMatrix[i][j] * -1;
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
			switch (this.deapModelType) {
				case CCRI: this.deapSolution = CCR.solveCCR(this); break;
				
				case CCRO: this.deapSolution = CCR.solveCCR(this); break;
				
				case BCCI: this.deapSolution = BCC.solveBCC(this); break;
				
				case BCCO: this.deapSolution = BCC.solveBCC(this); break;
				
				case GRSI: this.deapSolution = BCC.solveBCC(this); break;
				
				case GRSO: this.deapSolution = BCC.solveBCC(this); break;
				
				case IRSI:
					this.setRTSLowerBound(1);
					this.setRTSUpperBound(1E30); //LpSolve cannot solve with Double.POSITIVE_INFINITY (numerical instability => model is infeasible, last best value of -1e+30)
					this.deapSolution = BCC.solveBCC(this);
					break;
				
				case IRSO:
					this.setRTSLowerBound(1);
					this.setRTSUpperBound(1E30);
					this.deapSolution = BCC.solveBCC(this);
					break;
				
				case DRSI:
					this.setRTSLowerBound(0);
					this.setRTSUpperBound(1);
					this.deapSolution = BCC.solveBCC(this);
					break;
				
				case DRSO:
					this.setRTSLowerBound(0);
					this.setRTSUpperBound(1);
					this.deapSolution = BCC.solveBCC(this);
					break;
				
				case SBM: this.deapSolution = SBM.solveSBM(this); break;
				
				case SBMV: this.deapSolution = SBM.solveSBM(this); break;
				
				case SBMGRS: this.deapSolution = SBM.solveSBM(this); break;
				
				case SBMI: this.deapSolution = SBMI.solveSBMI(this); break;
				
				case SBMIV: this.deapSolution = SBMI.solveSBMI(this); break;				
				
				case SBMIGRS: this.deapSolution = SBMI.solveSBMI(this); break;
				
				case SBMO: this.deapSolution = SBMO.solveSBMO(this); break;
				
				case SBMOV: this.deapSolution = SBMO.solveSBMO(this); break;
				
				case SBMOGRS: this.deapSolution = SBMO.solveSBMO(this); break;
			}
		}
		catch (DEASolverException e) {
			throw e;
		}
		
		
	}


	private void checkDataBeforeSolving() throws MissingData,
			InconsistentNoOfVariables, InconsistentNoOfDMUs {
		if(this.deapDataMatrix == null || this.deapDMUName == null || this.deapModelType == null ||
				this.deapVariableName == null || this.deapVariableType == null) {
			//MissingData e = new MissingData("some text to describe the error");
			throw new MissingData();
		}
		
		int lenX = this.deapDataMatrix[0].length;
		if(lenX != this.deapVariableName.length || lenX != this.deapVariableType.length) {
			throw new InconsistentNoOfVariables();
		}
		
		int lenY = this.deapDataMatrix.length;
		if(lenY != this.deapDMUName.length) {
			throw new InconsistentNoOfDMUs();
		}
		
		if(this.getModelType().getReturnToScale() == ReturnToScale.GENERAL) {
			if(this.deapRTSUpperBound == 0) {
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
		return this.deapSolution.getStatus();
	}
	
	/**
	 * Returns the Objectives of all the DMUs.
	 * @return A double[] corresponding to all the DMUs Objectives.
	 * @throws DEAExceptions 
	 */
	public double[] getObjectives() //throws DEAExceptions
	{
		return deapSolution.getObjectives();
		//return _Solution.Objectives;
	}
	
	/**
	 * Returns the Objective value for the specified DMU.
	 * @param DMUNumber The number of the DMU in the Objective Array.
	 * @return The Objective value for the specified DMU.
	 */
	public double getObjective(int DMUNumber)
	{
		return deapSolution.getObjectives()[DMUNumber];// _Solution.getObjectives()[DMUNumber];
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
	 * @param DMUNumber The number of the DMU.
	 * @return A double[] corresponding to the slacks for the specified DMU.
	 */
	public double[] getSlacks(int DMUNumber)
	{
		return deapSolution.getSlacks(DMUNumber); // .Slacks[DMUNumber];
	}
	
	/**
	 * Returns the slack value for the specified DMU and Variable Number.
	 * @param DMUNumber The number of the DMU
	 * @param VarNumber The Number of the Variable
	 * @return The slack value for the specified DMU and Variable Number
	 */
	public double getSlack(int DMUNumber, int VarNumber)
	{
		return deapSolution.getSlack(DMUNumber, VarNumber); // _Solution.Slacks[DMUNumber] [VarNumber];
	}
	
	/**
	 * Returns the Slacks for all the DMUs.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all the slacks for all the DMUs.
	 */
	public double[] [] getSlacks()
	{
		return deapSolution.getSlacks(); // Slacks;
	}
	
	
	
	/**
	 * Returns the weights of the specified DMU.
	 * @param DMUNumber The Number of the DMU.
	 * @return A double[] corresponding to the weights for the specified DMU.
	 */
	public double[] getWeight(int DMUNumber)
	{
		return deapSolution.getWeights(DMUNumber);
	}
	
	/**
	 * Returns the specific weight value for the specified DMU and Variable number.
	 * @param DMUNumber The number of the DMU
	 * @param VarNumber The number of the Variable
	 * @return A specific weight value.
	 */
	public double getWeight(int DMUNumber, int VarNumber)
	{
		return deapSolution.getWeight(DMUNumber, VarNumber);
	}
	
	/**
	 * Returns the weights for all the DMUs
	 * @return A double[NbDMUs] [NbVariables] corresponding to the weights of all the DMUs
	 */
	public double[] [] getWeight()
	{
		return deapSolution.getWeights();
	}
	
	
	
	/**
	 * Returns the projections for the specified DMU.
	 * @param DMUNumber The number of the DMU
	 * @return The projections for the specific DMU
	 */
	public double[] getProjections(int DMUNumber)
	{
		return deapSolution.getProjections(DMUNumber);
	}
	
	/**
	 * Returns the projections for all the DMU
	 * @return a double[NbDMUs] [NbVariables] of all the projections
	 */
	public double[] [] getProjections()
	{
		return deapSolution.getProjections();
	}
	

	
	
	/* These methods only return all the values unlike the previous one which could be accessed value by value.
	 * This is because it would be inefficient to access the values below individually.*/
	
	/**
	 * Returns the projection percentages
	 * @param DMUNumber The DMU Number
	 * @return A double[] of the projection percentages for the specified DMU
	 */	
	public double[] getProjectionPercentages(int DMUNumber)
	{
		double[] ProjectionPercentages = new double[this.deapSolution.getProjections()[0].length];
		double VariableValue;
		double[] Projections = this.getProjections(DMUNumber);
		//Loop through each variable
		for(int i = 0; i < this.deapSolution.getProjections()[0].length; i++) {
			VariableValue = this.getDataMatrix(DMUNumber, i);
			if(this.getVariableType(i) == DEAVariableType.INPUT) {
				ProjectionPercentages[i] = (VariableValue -  Projections[i]) / VariableValue * -1;
			}
			else {
				ProjectionPercentages[i] = (VariableValue -  Projections[i]) / VariableValue;
			}
		}
		return ProjectionPercentages;
	}
	
	/**
	 * Returns the projection percentages for all DMUs
	 * @return A double[NbDMUs] [NbVariables] of the projection percentages for all the DMUs.
	 */	
	public double[] [] getProjectionPercentages()
	{
		
		double[] [] ProjectionPercentages =
			new double[this.getDMUNames().length] [this.deapSolution.getProjections()[0].length];
		double VariableValue;
		double[] Projections; 
		//Loop through all DMUs
		
		for(int i = 0; i < this.getDMUNames().length; i++) {
			Projections = this.getProjections(i);
			//Loop through each variable
			for(int j = 0; j < this.deapSolution.getProjections()[0].length; j++) {
				VariableValue = this.getDataMatrix(i, j);
				if(this.getVariableType(j) == DEAVariableType.INPUT) {
					ProjectionPercentages[i] [j] = (VariableValue -  Projections[j]) / VariableValue * -1;
				}
				else {
					ProjectionPercentages[i] [j] = (VariableValue -  Projections[j]) / VariableValue;
				}
			}
		}
		return ProjectionPercentages;
	}
	
	
	/**
	 * Returns the DMU ranks (based on Objectives value)
	 * @param HighestIsOne A boolean. If true the highest value has rank '1' (first). Should generally be 'true'.
	 * @param typeOfRanking The type of ranking to use. Should generally be 'STANDARD'.
	 * @param Precision An int value to specify at which decimal place the scores need to be rounded up prior to been ranked.
	 * This is important as most of the scores of the 'efficient' DMUs are not 1 but some value very close to 1 (e.g. 1.00000000000002).
	 * All precisions between 0 and 16 are taken into account, any other int value would leave the scores unchanged.
	 * @return A double[] of the ranks.
	 */
	public int[] getRanks(boolean HighestIsOne, RankingType typeOfRanking, int Precision)
	{
		/*This needs to be calculated here (i.e. if requested by the user) instead of
		 * calculating it post optimisation automatically (which would slow the optimisation process.*/
		int[] ranksArray;
		ranksArray = Rank.getRanks(this.deapSolution.getObjectives(), HighestIsOne, typeOfRanking, Precision);
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


