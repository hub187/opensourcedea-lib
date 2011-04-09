package dea;

public class TestDEAProblemSolution {
	
	
	//Storing the solution
	public double[] Objectives;
	public double[] [] Lambdas;
	public double[] [] Slacks;
	public double[] [] Weights;
	public double[] [] Projections;
	
	public TestDEAProblemSolution(int NbDMUs, int NbVariables) {
					
		/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
		 * The array are initialised here for this reason.*/
		Objectives = new double[NbDMUs];
		Lambdas = new double[NbDMUs] [NbDMUs];
		Slacks = new double[NbDMUs] [NbVariables];
		Weights = new double[NbDMUs] [NbVariables];
		Projections = new double[NbDMUs] [NbVariables];
	}
	
	
//	//Method to access Objective
//	public double[] getObjectives()
//	{
//		return Objectives;
//	}
//	public double getObjective(int DMUNumber)
//	{
//		return Objectives[DMUNumber];
//	}

	
	
}
