package dea;

import java.util.ArrayList;



/**
 * This class defines a DEA Problem Solution.
 * </br>
 * @author Hubert Virtos
 *
 */
public class DEAPSolution {
	
	public double[] Objectives;
	//public double[] [] Lambdas;
	public double[] [] Slacks;
	public ArrayList<NonZeroLambda>[] ReferenceSet;
	public double[] [] Weights;
	public double[] [] Projections;
	public SolverReturnStatus Status;
	
	
	/**
	 * 
	 * @param NbDMUs The number of DMUs in the DEA problem to solve.
	 * @param NbVariables The number of variables in the DEA Problem to solve.
	 */
	
	@SuppressWarnings("unchecked")
	public DEAPSolution (int NbDMUs, int NbVariables) {
		
		/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
		 * The array are initialised here for this reason.*/
		Objectives = new double[NbDMUs];
		//Lambdas = new double[NbDMUs] [NbDMUs];
		Slacks = new double[NbDMUs] [NbVariables];
		ReferenceSet = (ArrayList<NonZeroLambda>[])new ArrayList[NbDMUs];
		Weights = new double[NbDMUs] [NbVariables];
		Projections = new double[NbDMUs] [NbVariables];
		Status = SolverReturnStatus.NA;
		
		
	}
	

	
	

	
	
	
}

