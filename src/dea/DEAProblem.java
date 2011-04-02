package dea;

//import java.util.Arrays;
import dea.enums.*;
import dea.models.CCR;
import dea.Rank;


public class DEAProblem {
	
	 //////////////////////////////////////////////////////////////////////////
	//				Properties of the DEAProblem Object						//
   //////////////////////////////////////////////////////////////////////////
	
	//Building the model
	private DEAModelType _ModelType;
	private String[] _DMUName;
	private DEAModelOrientation _ModelOrientation;
	private String[] _VariableName;
	private DEAVariableType [] _VariableType;
	private double [] [] _DataMatrix;
	
	//Solution
	public DEAPSolution Solution;
	
	
	
	public DEAProblem(int NbDMUs, int NbVariables) {
		Solution = new DEAPSolution(NbDMUs, NbVariables);
	}
	
	public class DEAPSolution {
		
		//Storing the solution
		public  double[] Objectives;
		public double[] [] Lambdas;
		public double[] [] Slacks;
		public double[] [] Weights;
		public double[] [] Projections;
		
		public DEAPSolution (int NbDMUs, int NbVariables) {
			
			/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
			 * The array are initialised here for this reason.*/
			Objectives = new double[NbDMUs];
			Lambdas = new double[NbDMUs] [NbDMUs];
			Slacks = new double[NbDMUs] [NbVariables];
			Weights = new double[NbDMUs] [NbVariables];
			Projections = new double[NbDMUs] [NbVariables];
		}
		
//		public double[] Objectives {
//			set {_Objectives = value}
//		}

	}
	
	
	 //////////////////////////////////////////////////////////////////////////
	//								Set Problem								//
   //////////////////////////////////////////////////////////////////////////
	
	
	/* These Get Set methods are currently not useful at present
	 * but code will be added in those methods at a later stage.*/
	
	//Methods to access/set _ModelType
	public DEAModelType getModelType()
	{
		return _ModelType;
	}
	public void setModelType(DEAModelType ModelType)
	{
		_ModelType = ModelType;
	}


	//Methods to access/set _ModelOrientation
	public DEAModelOrientation getModelOrientation()
	{
		return _ModelOrientation;
	}
	public void setModelOrientation(DEAModelOrientation ModelOrientation)
	{
		_ModelOrientation = ModelOrientation;
	}
	
	//Methods to access/set _DMUNames
	public String[] getDMUNames()
	{
		return _DMUName;
	}
	public String getDMUName(int Pos)
	{
		return _DMUName[Pos];
	}
	public void setDMUNames(String[] DMUName)
	{
		_DMUName = DMUName;
	}
	public void setDMUName(String DMUName, int Pos)
	{
		_DMUName[Pos] = DMUName;
	}

	
	//Methods to access/set _VariableNames
	public String[] getVariableNames()
	{
		return _VariableName;
	}
	public String getVariableName(int Pos)
	{
		return _VariableName[Pos];
	}
	public void setVariableNames(String[] VariableName)
	{
		_VariableName = VariableName;
	}
	public void setVariableName(String VariableName, int Pos)
	{
		_VariableName[Pos] = VariableName;
	}
	
	//Methods to access/set _VariableType
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
