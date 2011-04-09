
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
import dea.enums.*;
import dea.models.CCR;
import dea.Rank;


/**
 *@author Hubert Virtos
 * <p>
 * Instances of this class are DEA Problems. A DEA Problem Object can hold all the information necessary to define a DEA Problem.
 * <p>
 * This class needs be instantiated in order to:
 * <ul>
 * <li>Define the DEA Problem (e.g. Model type, Variable Names, Variable types, Model orientation, Data matrix)</li>
 * <li>Solve the DEA Problem</li>
 * <li>Retrieve the DEA Problem solution</li>
 * </ul>
 * 
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
	

	public static class DEAPSolution {
		
		//Storing the solution
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
	 * Gets Model Orientation of the DEA Problem.
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
	 * Gets Names of the DEA Problem DMUs
	 * @return DMUName A String[] of the DMU Names.
	 */
	public String[] getDMUNames()
	{
		return _DMUName;
	}
	
	/**
	 * Gets name of a specific DMU (specified by position in the DMUName Array).
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
	 * Sets a DMU Name for the the DMU Number specified.
	 * @param DMUName The Name of the DMU.
	 * @param DMUNumber The DMU Number (position in the array) where to put the name.
	 */
	public void setDMUName(String DMUName, int DMUNumber)
	{
		_DMUName[DMUNumber] = DMUName;
	}

	
	/**
	 * Gets the String[] of the Variable Names.
	 * @return VariableName A String[] of the variable names.
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
	 * @see DEAVariableType
	 */
	public DEAVariableType[] getVariableTypes()
	{
		return _VariableType;
	}
	public DEAVariableType getVariableType(int Pos)
	{
		return _VariableType[Pos];
	}
	public void setVariableTypes(DEAVariableType[] VariableType)
	{
		_VariableType = VariableType;
	}
	public void setVariableType(DEAVariableType VariableType, int Pos)
	{
		_VariableType[Pos] = VariableType;
	}
	
	//Methods to access/set _DataMatrix
	public double[] [] getDataMatrix()
	{
		return _DataMatrix;
	}
	public double getDataMatrix(int i, int j)
	{
		return _DataMatrix[i] [j];
	}
	public void setDataMatrix(double[] [] DataMatrix)
	{
		_DataMatrix = DataMatrix;
	}
	public void setDataMatrix(double Value, int i, int j)
	{
		_DataMatrix[i] [j] = Value;
	}
	
	
	 //////////////////////////////////////////////////////////////////////////
	//						Mostly Internal Use								//
   //////////////////////////////////////////////////////////////////////////
	
	//Method to get Number of DMUs
	public int getNumberOfDMUs() {
		int NbDMUs;
		NbDMUs = _DataMatrix.length;
		return NbDMUs;
	}
	
	//Method to get number of Variables
	public int getNumberOfVariables() {
		int NbVariables;
		NbVariables = _DataMatrix[0].length;
		return NbVariables;
	}
	
	//Method to get the transpose of the DataMatrix
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
	
	//Method to solve the DEA problem
	public void solve() {
		//Need call a method here to check whether the DEAProblem is correct or not (before calling any DEA model Class
		switch (this._ModelType) {
			case CCR: CCR.solveCCR(this);
			
		}
		
	
		
	}
	
	
	
	 //////////////////////////////////////////////////////////////////////////////
	//						Get Problem Solution								//
   //////////////////////////////////////////////////////////////////////////////
	
	
	//Method to access Objective
	public double[] getObjectives()
	{
		return Solution.Objectives;
	}
	public double getObjective(int DMUNumber)
	{
		return Solution.Objectives[DMUNumber];
	}

	//Method to access Lambdas for a specific DMU
	public double[] getLambdas(int DMUNumber)
	{
		return Solution.Lambdas[DMUNumber];
	}
	//Method to access All Lambdas
	public double[] [] getLambdas()
	{
		return Solution.Lambdas;
	}
	//Method to access a specific Lambda for a specific DMU
	public double getLambdas(int DMUNumber, int Lambda)
	{
		return Solution.Lambdas[DMUNumber] [Lambda];
	}
	
	//Method to access Slacks for a specific DMU
	public double[] getSlacks(int DMUNumber)
	{
		return Solution.Slacks[DMUNumber];
	}
	//Method to access a specific Slack value for a specific DMU
	public double getSlacks(int DMUNumber, int VarNumber)
	{
		return Solution.Slacks[DMUNumber] [VarNumber];
	}
	//Method to access All Slacks
	public double[] [] getSlacks()
	{
		return Solution.Slacks;
	}
	
	//Method to access Weights for a specific DMU
	public double[] getWeight(int DMUNumber)
	{
		return Solution.Weights[DMUNumber];
	}
	//Method to access a specific Weight for a specific DMU
	public double getWeight(int DMUNumber, int VarNumber)
	{
		return Solution.Weights[DMUNumber] [VarNumber];
	}
	//Method to access All Weights
	public double[] [] getWeight()
	{
		return Solution.Weights;
	}
	
	//Method to access Projections for a specific DMU
	public double[] getProjections(int DMUNumber)
	{
		return Solution.Projections[DMUNumber];
	}
	//Method to access All Projections
	public double[] [] getProjections()
	{
		return Solution.Projections;
	}
	
	
	/* These methods can only be accessed all at once unlike the previous one which could be accessed value by value.
	 * This is because it would be inefficient to access the values below individually.*/
	
	//Method to access ProjectionsPercentages for a specific DMU
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
	//Method to access All ProjectionsPercentages
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
	
	
	//Method to access Ranks
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
