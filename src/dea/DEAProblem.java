
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
import utils.Rank;
import deaModels.*;


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
	

	private DEAModelType _ModelType;
	private String[] _DMUName;
	private DEAModelOrientation _ModelOrientation;
	private String[] _VariableName;
	private DEAVariableType [] _VariableType;
	private double [] [] _DataMatrix;
	
	//Solution
	public DEAPSolution Solution;
	
	
	/**
	 * 
	 * @param NbDMUs The number of DMUs in the DEA problem to solve.
	 * @param NbVariables The number of variables in the DEA Problem to solve.
	 */
	public DEAProblem(int NbDMUs, int NbVariables) {
		Solution = new DEAPSolution(NbDMUs, NbVariables);
	}
	
	/**
	 * This class defines a DEA Problem Solution.
	 * <p>
	 * @author Hubert Virtos
	 *
	 */
	public static class DEAPSolution {
		
		public double[] Objectives;
		public double[] [] Lambdas;
		public double[] [] Slacks;
		public double[] [] Weights;
		public double[] [] Projections;
		
		/**
		 * 
		 * @param NbDMUs The number of DMUs in the DEA problem to solve.
		 * @param NbVariables The number of variables in the DEA Problem to solve.
		 */
		public DEAPSolution (int NbDMUs, int NbVariables) {
			
			/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
			 * The array are initialised here for this reason.*/
			Objectives = new double[NbDMUs];
			Lambdas = new double[NbDMUs] [NbDMUs];
			Slacks = new double[NbDMUs] [NbVariables];
			Weights = new double[NbDMUs] [NbVariables];
			Projections = new double[NbDMUs] [NbVariables];
		}
		
	}
	
	
	 //////////////////////////////////////////////////////////////////////////
	//								Set Problem								//
   //////////////////////////////////////////////////////////////////////////
	
	

	
	/**
	 * Returns Model Type of the DEA Problem (e.g. CCR, SBM...)
	 * @return DEAModelType The Model Type of the DEA Problem
	 */
	public DEAModelType getModelType()
	{
		return _ModelType;
	}
	
	/**
	 * Sets type of DEA Problem Model (e.g. CCR, SBM...)
	 * @param ModelType The DEA Problem type.
	 */
	public void setModelType(DEAModelType ModelType)
	{
		_ModelType = ModelType;
	}


	/**
	 * Returns Model Orientation of the DEA Problem.
	 * @return Model Orientation (i.e. InputOriented or OutputOriented)
	 */
	public DEAModelOrientation getModelOrientation()
	{
		return _ModelOrientation;
	}
	
	/**
	 * Sets the Model Orientation of the DEA problem (e.g. InputOriented)
	 * @param ModelOrientation The Model Orientation of the DEA Problem.
	 */
	public void setModelOrientation(DEAModelOrientation ModelOrientation)
	{
		_ModelOrientation = ModelOrientation;
	}
	

	/**
	 * Returns the Names of all the DMUs
	 * @return DMUName A String[] of the DMU Names.
	 */
	public String[] getDMUNames()
	{
		return _DMUName;
	}
	
	/**
	 * Returns the name of a specific DMU.
	 * @param DMUNumber The number of the DMU (position) in the Array.
	 * @return DMUName The DMU name of the specific DMU at the position specified.
	 */
	public String getDMUName(int DMUNumber)
	{
		return _DMUName[DMUNumber];
	}
	
	/**
	 * Sets DMU Names of all DMUs
	 * @param DMUName The String[] of the DMU Names.
	 */
	public void setDMUNames(String[] DMUName)
	{
		_DMUName = DMUName;
	}
	
	/**
	 * Returns a DMU Name for the the DMU Number specified.
	 * @param DMUName The Name of the DMU.
	 * @param DMUNumber The DMU Number (position in the array) where to put the name.
	 */
	public void setDMUName(String DMUName, int DMUNumber)
	{
		_DMUName[DMUNumber] = DMUName;
	}

	
	/**
	 * Returns the Variable Names.
	 * @return A String[] of the VariableName.
	 */
	public String[] getVariableNames()
	{
		return _VariableName;
	}
	
	/**
	 * Returns the variable name for a specific variable.
	 * @param VariableNumber The Variable Number in the variable array.
	 * @return The variable name for a specific variable specified.
	 */
	public String getVariableName(int VariableNumber)
	{
		return _VariableName[VariableNumber];
	}
	
	/**
	 * Sets all the Variable names.
	 * @param VariableName A String[] of Variable Names.
	 */
	public void setVariableNames(String[] VariableName)
	{
		_VariableName = VariableName;
	}
	
	/**
	 * Sets the name of the variable at position VariableNumber in the Variable Names Array.
	 * @param VariableName The Name of the Variable.
	 * @param VariableNumber The Variable position in the Variable Array.
	 */
	public void setVariableName(String VariableName, int VariableNumber)
	{
		_VariableName[VariableNumber] = VariableName;
	}
	
	
	/**
	 * Returns an array of VariableTypes.
	 * @return An array of variable types.
	 */
	public DEAVariableType[] getVariableTypes()
	{
		return _VariableType;
	}
	
	/**
	 * Gets the Variable type of the Variable specified.
	 * @param VariableNumber The position of the variable in the Variable Array.
	 * @return The Variable Type of the Variable at the position specified.
	 */
	public DEAVariableType getVariableType(int VariableNumber)
	{
		return _VariableType[VariableNumber];
	}
	
	/**
	 * Sets the variable type for all the variables.
	 * @param VariableType An array of DEAVariableType.
	 */
	public void setVariableTypes(DEAVariableType[] VariableType)
	{
		_VariableType = VariableType;
	}
	
	/**
	 * Sets the DEAVariableType for the specified Variable.
	 * @param VariableType The DEAVariableType of the specified variable.
	 * @param VariableNumber The position of the variable in the Variable Array.
	 */
	public void setVariableType(DEAVariableType VariableType, int VariableNumber)
	{
		_VariableType[VariableNumber] = VariableType;
	}
	
	
	/**
	 * Returns the matrix of values.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all values.
	 */
	public double[] [] getDataMatrix()
	{
		return _DataMatrix;
	}
	
	/**
	 * Returns a specific variable value  from the Data Matrix for the DMU specified.
	 * @param DMUNumber The number of DMU in the DMU array
	 * @param VariableNumber The number of the variable in the Variable Array
	 * @return a double corresponding to the Variable value.
	 */
	public double getDataMatrix(int DMUNumber, int VariableNumber)
	{
		return _DataMatrix[DMUNumber] [VariableNumber];
	}
	
	/**
	 * Sets the whole Data Matrix.
	 * @param DataMatrix The double [NbDMUs] [NbVariable] array of the problem values.
	 */
	public void setDataMatrix(double[] [] DataMatrix)
	{
		_DataMatrix = DataMatrix;
	}
	
	/**
	 * Sets a value for a specific DMU.
	 * @param Value The specific value to set.
	 * @param DMUNumber The DMU Number for which to set the value.
	 * @param VariableNumber The Variable Number for which to set the value.
	 */
	public void setDataMatrix(double Value, int DMUNumber, int VariableNumber)
	{
		_DataMatrix[DMUNumber] [VariableNumber] = Value;
	}
	
	
	 //////////////////////////////////////////////////////////////////////////
	//						Mostly Internal Use								//
   //////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets the number of DMUs in the DEA Problem
	 * @return The number of DMUs.
	 */
	public int getNumberOfDMUs() {
		int NbDMUs;
		NbDMUs = _DataMatrix.length;
		return NbDMUs;
	}
	
	/**
	 * Gets the number of Variables in the DEA Problem
	 * @return The number of Variables.
	 */
	public int getNumberOfVariables() {
		int NbVariables;
		NbVariables = _DataMatrix[0].length;
		return NbVariables;
	}
	
	/**
	 * Gets the Transpose of the Data Matrix
	 * @return A double[] [] corresponding to the transpose of the Data Matrix
	 */
	public double [] [] getTranspose() {
		double [] [] TransposedMatrix = new double [this._DataMatrix[0].length] [this._DataMatrix.length];
		
		for (int i = 0; i < this._DataMatrix.length; i++)
            for (int j = 0; j < this._DataMatrix[0].length; j++)
                TransposedMatrix[j][i] = this._DataMatrix[i][j];
		return TransposedMatrix;
	}
	
	
	 //////////////////////////////////////////////////////////////////////////
	//								Solve Problem							//
   //////////////////////////////////////////////////////////////////////////
	
	/**
	 * Solves the specific DEA Problem.
	 * <p> The Model needs to have all the required parameters in order for this to work.
	 * <p> There is no check at present, if the parameters are incorrect, the library will simply crash.
	 */
	public void solve() {
		//Need call a method here to check whether the DEAProblem is correct or not (before calling any DEA model Class
		switch (this._ModelType) {
			case CCR: CCR.solveCCR(this);
			
		}
		
	
		
	}
	
	
	
	 //////////////////////////////////////////////////////////////////////////////
	//						Get Problem Solution								//
   //////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Returns the Objectives of all the DMUs.
	 * @return A double[] corresponding to all the DMUs Objectives.
	 */
	public double[] getObjectives()
	{
		return Solution.Objectives;
	}
	
	/**
	 * Returns the Objective value for the specified DMU.
	 * @param DMUNumber The number of the DMU in the Objective Array.
	 * @return The Objective value for the specified DMU.
	 */
	public double getObjective(int DMUNumber)
	{
		return Solution.Objectives[DMUNumber];
	}

	
	/**
	 * Returns the lambdas of the specified DMU.
	 * @param DMUNumber The number of the DMU in the Lambda Array.
	 * @return A double[] corresponding to the Lambdas for the specified DMU.
	 */
	public double[] getLambdas(int DMUNumber)
	{
		return Solution.Lambdas[DMUNumber];
	}
	
	/**
	 * Returns the lambdas for all DMUs.
	 * @return A double[DMUNumber] [NumberOfDMUs] corresponding to the lambdas for all the DMUs.
	 */
	public double[] [] getLambdas()
	{
		return Solution.Lambdas;
	}
	
	/**
	 * Returns the Lambda of the specified DMU and Lambda.
	 * @param DMUNumber The DMU Number
	 * @param Lambda The Lambda Number
	 * @return The value of the lambda for the specified DMU and Lambda
	 */
	public double getLambdas(int DMUNumber, int Lambda)
	{
		return Solution.Lambdas[DMUNumber] [Lambda];
	}
	
	/**
	 * Returns the Slacks for the specified DMU.
	 * @param DMUNumber The number of the DMU.
	 * @return A double[] corresponding to the slacks for the specified DMU.
	 */
	public double[] getSlacks(int DMUNumber)
	{
		return Solution.Slacks[DMUNumber];
	}
	
	/**
	 * Returns the slack value for the specified DMU and Variable Number.
	 * @param DMUNumber The number of the DMU
	 * @param VarNumber The Number of the Variable
	 * @return The slack value for the specified DMU and Variable Number
	 */
	public double getSlacks(int DMUNumber, int VarNumber)
	{
		return Solution.Slacks[DMUNumber] [VarNumber];
	}
	
	/**
	 * Returns the Slacks for all the DMUs.
	 * @return A double[NbDMUs] [NbVariables] corresponding to all the slacks for all the DMUs.
	 */
	public double[] [] getSlacks()
	{
		return Solution.Slacks;
	}
	
	/**
	 * Returns the weights of the specified DMU.
	 * @param DMUNumber The Number of the DMU.
	 * @return A double[] corresponding to the weights for the specified DMU.
	 */
	public double[] getWeight(int DMUNumber)
	{
		return Solution.Weights[DMUNumber];
	}
	
	/**
	 * Returns the specific weight value for the specified DMU and Variable number.
	 * @param DMUNumber The number of the DMU
	 * @param VarNumber The number of the Variable
	 * @return A specific weight value.
	 */
	public double getWeight(int DMUNumber, int VarNumber)
	{
		return Solution.Weights[DMUNumber] [VarNumber];
	}
	
	/**
	 * Returns the weights for all the DMUs
	 * @return A double[NbDMUs] [NbVariables] corresponding to the weights of all the DMUs
	 */
	public double[] [] getWeight()
	{
		return Solution.Weights;
	}
	
	/**
	 * Returns the projections for the specified DMU.
	 * @param DMUNumber The number of the DMU
	 * @return The projections for the specific DMU
	 */
	public double[] getProjections(int DMUNumber)
	{
		return Solution.Projections[DMUNumber];
	}
	
	/**
	 * Returns the projections for all the DMU
	 * @return a double[NbDMUs] [NbVariables] of all the projections
	 */
	public double[] [] getProjections()
	{
		return Solution.Projections;
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
		double[] ProjectionPercentages = new double[this.Solution.Projections[0].length];
		double VariableValue;
		double[] Projections = this.getProjections(DMUNumber);
		//Loop through each variable
		for(int i = 0; i < this.Solution.Projections[0].length; i++) {
			VariableValue = this.getDataMatrix(DMUNumber, i);
			if(this.getVariableType(i) == DEAVariableType.Input) {
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
			new double[this.getDMUNames().length] [this.Solution.Projections[0].length];
		double VariableValue;
		double[] Projections; 
		//Loop through all DMUs
		
		for(int i = 0; i < this.getDMUNames().length; i++) {
			Projections = this.getProjections(i);
			//Loop through each variable
			for(int j = 0; j < this.Solution.Projections[0].length; j++) {
				VariableValue = this.getDataMatrix(i, j);
				if(this.getVariableType(j) == DEAVariableType.Input) {
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
	 * @param LowestIsZero A boolean. If true the lowest value has rank '0'.
	 * @return A double[] of the ranks.
	 */
	public int[] GetDMURanks(boolean LowestIsZero)
	{
		/*This needs to be calculated here (i.e. if requested by the user) instead of
		 * calculating it post optimisation automatically (which would slow the optimisation process.*/
		int[] ranksArray;
		ranksArray = Rank.GetRanks(this.Solution.Objectives, LowestIsZero);
		return ranksArray;

		
		
		
		
//		char[][][] data=new char[numFiles][][],data1=new char[numFiles][][];
//		for (int i = 0; i < numFiles; i++) {
//		data[i]=split(files[i]); //line 1-Reads file into char array
//		data1[i]=bubbleSort(data[i]);//line 2
		
		
	}
	
	//Calculate weighted data


}
