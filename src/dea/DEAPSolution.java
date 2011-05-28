package dea;

import java.util.ArrayList;



/**
 * This class defines a DEA Problem Solution.
 * </br>
 * @author Hubert Virtos
 *
 */
public class DEAPSolution {
	
	private double[] Objectives;
	private double[] [] Slacks;
	private ArrayList<NonZeroLambda>[] ReferenceSet;
	private double[] [] Weights;
	private double[] [] Projections;
	private SolverReturnStatus Status;

	
	
	/**
	 * 
	 * @param NbDMUs The number of DMUs in the DEA problem to solve.
	 * @param NbVariables The number of variables in the DEA Problem to solve.
	 */
	
	@SuppressWarnings("unchecked")
	public DEAPSolution (int NbDMUs, int NbVariables) {
		
		/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
		 * The array are initialised here for this reason.*/
		setObjectives(new double[NbDMUs]);
		Slacks = new double[NbDMUs] [NbVariables];
		setReferenceSet((ArrayList<NonZeroLambda>[])new ArrayList[NbDMUs]);
		setWeights(new double[NbDMUs] [NbVariables]);
		setProjections(new double[NbDMUs] [NbVariables]);
		setStatus(SolverReturnStatus.NA);

	}

	
	public void setSlacks(double[] [] slacks) {
		this.Slacks = slacks;
	}

	
	public double[][] getSlacks() {
		return this.Slacks;
	}


	public void setReferenceSet(ArrayList<NonZeroLambda>[] referenceSet) {
		ReferenceSet = referenceSet;
	}


	public ArrayList<NonZeroLambda>[] getReferenceSet() {
		return ReferenceSet;
	}


	public void setWeights(double[] [] weights) {
		Weights = weights;
	}


	public double[] [] getWeights() {
		return Weights;
	}


	public void setProjections(double[] [] projections) {
		Projections = projections;
	}


	public double[] [] getProjections() {
		return Projections;
	}


	public void setStatus(SolverReturnStatus status) {
		Status = status;
	}


	public SolverReturnStatus getStatus() {
		return Status;
	}


	public void setObjectives(double[] objectives) {
		Objectives = objectives;
	}


	public double[] getObjectives() {
		return Objectives;
	}
	
	
	
}

