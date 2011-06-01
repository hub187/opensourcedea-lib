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
		this.Objectives = objectives;
	}
	
	public void setObjective(int DMUIndex, double ObjectiveValue) {
		this.Objectives[DMUIndex] = ObjectiveValue;
	}
	
	public double getObjective(int DMUIndex) {
		return this.Objectives[DMUIndex];
	}
	
	public double[] getObjectives() {
		return this.Objectives;
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
	
	public void setSlackArrayCopy(int DMUIndex, double[] ArrayToCopyFrom, int PositionToCopyFrom, 
			int LengthToCopy) {
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
		this.ReferenceSet = referenceSet;
	}
	
	public void setReferenceSet(int DMUIndex, ArrayList<NonZeroLambda> referenceSet) {
		this.ReferenceSet[DMUIndex] = referenceSet;
	}

	public ArrayList<NonZeroLambda>[] getReferenceSet() {
		return this.ReferenceSet;
	}
	
	public ArrayList<NonZeroLambda> getReferenceSet(int DMUIndex) {
		return this.ReferenceSet[DMUIndex];
	}
	
	
	
	
	
	public void setWeights(double[] [] weights) {
		this.Weights = weights;
	}
	
	public void setWeights(int DMUIndex, double[] weights) {
		this.Weights[DMUIndex] = weights;
	}

	public void setWeight(int DMUIndex, int VarIndex, double weight) {
		this.Weights[DMUIndex] [VarIndex] = weight;
	}
	
	public double[] [] getWeights() {
		return Weights;
	}
	
	public double[] getWeights(int DMUIndex) {
		return this.Weights[DMUIndex];
	}
	
	public double getWeight(int DMUIndex, int VarIndex) {
		return this.Weights[DMUIndex][VarIndex];
	}
	
	
	
	
	
	public void setProjections(double[] [] projections) {
		Projections = projections;
	}
	
	public void setProjection(int DMUIndex, int VarIndex, double ProjectionValue) {
		this.Projections[DMUIndex][VarIndex] = ProjectionValue;
	}

	public double[] [] getProjections() {
		return Projections;
	}
	
	public double[] getProjections(int DMUIndex) {
		return this.Projections[DMUIndex];
	}
	
	
	
	
	
	public void setStatus(SolverReturnStatus status) {
		Status = status;
	}
	
	public SolverReturnStatus getStatus() {
		return Status;
	}

	


	
	
	
}

