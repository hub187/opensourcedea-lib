package dea;

import java.util.ArrayList;



/**
 * This class defines a DEA Problem Solution.
 * </br>
 * @author Hubert Virtos
 *
 */
public class DEAPSolution {
	
	private double[] objectives;
	private double[] [] slacks;
	private ArrayList<NonZeroLambda>[] referenceSet;
	private double[] [] weights;
	private double[] [] projections;
	private SolverReturnStatus status;

	
	
	/**
	 * 
	 * @param nbDMUs The number of DMUs in the DEA problem to solve.
	 * @param nbVariables The number of variables in the DEA Problem to solve.
	 */
	@SuppressWarnings("unchecked")
	public DEAPSolution (int nbDMUs, int nbVariables) {
		
		/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
		 * The array are initialised here for this reason.*/
		setObjectives(new double[nbDMUs]);
		slacks = new double[nbDMUs] [nbVariables];
		setReferenceSet((ArrayList<NonZeroLambda>[])new ArrayList[nbDMUs]);
		setWeights(new double[nbDMUs] [nbVariables]);
		setProjections(new double[nbDMUs] [nbVariables]);
		setStatus(SolverReturnStatus.NOT_SOLVED);

	}

	
	/**
	 * Sets the Objective values for all DMUs.
	 * @param objectives The Objective values (array)
	 */
	public void setObjectives(double[] objectives) {
		this.objectives = objectives;
	}
	
	/**
	 * Sets the Objective Value for the specific DMU.
	 * @param DMUIndex The index of the DMU to set the objective value to.
	 * @param objectiveValue The Objective Value.
	 */
	public void setObjective(int DMUIndex, double objectiveValue) {
		this.objectives[DMUIndex] = objectiveValue;
	}
	
	/**
	 * Gets the Objective value for a specific DMU.
	 * @param dmuIndex The index of the DMU to get the objective value for.
	 * @return The Objective Value for the specified DMU.
	 */
	public double getObjective(int dmuIndex) {
		return this.objectives[dmuIndex];
	}
	
	/**
	 * Gets the Objective values for all DMUs.
	 * @return The Objective Values.
	 */
	public double[] getObjectives() {
		return this.objectives;
	}
	
	
	
	
	/**
	 * Set the Slack Values for all DMUs.
	 * @param slackValues The Slack Values for all DMUs.
	 */
	public void setSlacks(double[] [] slackValues) {
		this.slacks = slackValues;
	}
	
	/**
	 * Set the Slacks for a specific DMU.
	 * @param dmuIndex The index of the DMU to set slacks for.
	 * @param slackValues The Slack Values.
	 */
	public void setSlacks(int dmuIndex, double[] slackValues) {
		this.slacks[dmuIndex] = slackValues;
	}
	
	/**
	 * Set the Slack for a specific DMU and a specific variable.
	 * @param dmuIndex The index of the DMU to set slack for.
	 * @param varIndex The index of the variable to set the slack for.
	 * @param slackValue The actual slack value.
	 */
	public void setSlack(int dmuIndex, int varIndex, double slackValue) {
		this.slacks[dmuIndex][varIndex] = slackValue;
	}
	
	/**
	 * Copies slacks from an array to the Slack Array of the Solution Object.
	 * This enables efficient copying of Solver results into the DEAPSolution.slacks array.
	 * @param dmuIndex The index of the DMU to c
	 * @param arrayToCopyFrom
	 * @param positionToCopyFrom
	 * @param LengthToCopy
	 */
	public void setSlackArrayCopy(int dmuIndex, double[] arrayToCopyFrom, int positionToCopyFrom, 
			int LengthToCopy) {
		System.arraycopy(arrayToCopyFrom, positionToCopyFrom, this.slacks[dmuIndex], 0, LengthToCopy);
	}
	
	public double getSlack(int dmuIndex, int varIndex) {
		return this.slacks[dmuIndex][varIndex];
	}
	
	public double[] getSlacks(int dmuIndex) {
		return this.slacks[dmuIndex];
	}
	
	public double[][] getSlacks() {
		return this.slacks;
	}
	
	
	
	
	
	public void setReferenceSet(ArrayList<NonZeroLambda>[] referenceSet) {
		this.referenceSet = referenceSet;
	}
	
	public void setReferenceSet(int dmuIndex, ArrayList<NonZeroLambda> referenceSet) {
		this.referenceSet[dmuIndex] = referenceSet;
	}
	
	public void setReferenceSet(int dmuIndex, NonZeroLambda nonZeroLambda) {
		this.referenceSet[dmuIndex].add(nonZeroLambda);
	}
	
	public ArrayList<NonZeroLambda>[] getReferenceSet() {
		return this.referenceSet;
	}
	
	public ArrayList<NonZeroLambda> getReferenceSet(int dmuIndex) {
		return this.referenceSet[dmuIndex];
	}
	

	
	
	
	
	
	public void setWeights(double[] [] weights) {
		this.weights = weights;
	}
	
	public void setWeights(int dmuIndex, double[] weights) {
		this.weights[dmuIndex] = weights;
	}

	public void setWeight(int dmuIndex, int varIndex, double weight) {
		this.weights[dmuIndex] [varIndex] = weight;
	}
	
	public double[] [] getWeights() {
		return weights;
	}
	
	public double[] getWeights(int dmuIndex) {
		return this.weights[dmuIndex];
	}
	
	public double getWeight(int dmuIndex, int varIndex) {
		return this.weights[dmuIndex][varIndex];
	}
	
	
	
	
	
	public void setProjections(double[] [] projectionValues) {
		this.projections = projectionValues;
	}
	
	public void setProjection(int dmuIndex, int varIndex, double projectionValue) {
		this.projections[dmuIndex][varIndex] = projectionValue;
	}

	public double[] [] getProjections() {
		return this.projections;
	}
	
	public double[] getProjections(int dmuIndex) {
		return this.projections[dmuIndex];
	}
	
	
	
	
	
	public void setStatus(SolverReturnStatus statusValue) {
		status = statusValue;
	}
	
	public SolverReturnStatus getStatus() {
		return this.status;
	}

	


	
	
	
}

