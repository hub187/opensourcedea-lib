package dea;


/**
 * This class defines a DEA Problem Solution.
 * <p>
 * @author Hubert Virtos
 *
 */
public class DEAPSolution {
	
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

