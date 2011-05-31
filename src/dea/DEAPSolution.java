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

	
	
	public void setObjectives(double[] objectives) {
		Objectives = objectives;
	}
	
	public void setObjective(int DMUIndex, double ObjectiveValue) {
		Objectives[DMUIndex] = ObjectiveValue;
	}
	
	public double getObjective(int DMUIndex) {
		return Objectives[DMUIndex];
	}
	
	public double[] getObjectives() {
		return Objectives;
	}
	
	
	
	
	
	public void setSlacks(double[] [] SlackValues) {
		this.Slacks = SlackValues;
	}
	
	public void setSlacks(int DMUIndex, double[] SlackValues) {
		this.Slacks[DMUIndex] = SlackValues;
	}
	
	public void setSlack(int DMUIndex, int VarIndex, double SlackValue) {
		this.Slacks[DMUIndex][VarIndex] = SlackValue;
	}
	
	public void setSlackArrayCopy(double[] ArrayToCopyFrom, int PositionToCopyFrom, 
			int LengthToCopy, int DMUIndex) {
		System.arraycopy(ArrayToCopyFrom, PositionToCopyFrom, this.Slacks[DMUIndex], 0, LengthToCopy);
	}
	
	public double getSlack(int DMUIndex, int VarIndex) {
		return this.Slacks[DMUIndex][VarIndex];
	}
	
	public double[] getSlacks(int DMUIndex) {
		return this.Slacks[DMUIndex];
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

	


	
	
	
}

