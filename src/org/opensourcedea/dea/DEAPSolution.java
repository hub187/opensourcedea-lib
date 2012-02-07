
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
    
*/

package org.opensourcedea.dea;

import java.util.ArrayList;
import java.io.Serializable;


/**
 * This class defines a DEA Problem Solution. All the methods in this class are used 
 * to set or get part of a DEA Problem Solution (DEAPSolution).
 * </br>
 * @author Hubert Virtos
 *
 */
public class DEAPSolution implements Serializable {
	
	/**
	 * The auto-generated serial version UID
	 */
	private static final long serialVersionUID = 2402167210320751288L;
	
	private double[] objectives;
	private double[] [] slacks;
	private ArrayList<NonZeroLambda>[] referenceSets;
	private double[] [] weights;
	private double[] u0Weights;
	private double[] lBConvexityConstraintWeights;
	private double[] uBConvexityConstraintWeights;
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
		setSlacks(new double[nbDMUs] [nbVariables]);
		setReferenceSets((ArrayList<NonZeroLambda>[])new ArrayList[nbDMUs]);
		setWeights(new double[nbDMUs] [nbVariables]);
		setProjections(new double[nbDMUs] [nbVariables]);
		setStatus(SolverReturnStatus.NOT_SOLVED);
		u0Weights = new double[nbDMUs];
		lBConvexityConstraintWeights = new double[nbDMUs];
		uBConvexityConstraintWeights = new double[nbDMUs];
		
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
	 * @param dmuIndex The index of the DMU to copy data to
	 * @param arrayToCopyFrom the array to copy slack values from
	 * @param positionToCopyFrom the position to copy from in this array
	 * @param LengthToCopy the length to copy
	 */
	public void setSlackArrayCopy(int dmuIndex, double[] arrayToCopyFrom, int positionToCopyFrom, 
			int LengthToCopy)  {
		System.arraycopy(arrayToCopyFrom, positionToCopyFrom, this.slacks[dmuIndex], 0, LengthToCopy);
	}
	
	/**
	 * Gets the slack value for a specific DMU and a specific variable.
	 * @param dmuIndex The index of the DMU to get the slack value for.
	 * @param varIndex The index of the variable to get the slack for/
	 * @return The slack value for the specified DMU and variable.
	 */
	public double getSlack(int dmuIndex, int varIndex){
		return this.slacks[dmuIndex][varIndex];
	}
	
	/**
	 * Gets the slack values for the specified DMU.
	 * @param dmuIndex The index of the DMU to get the slacks for.
	 * @return The slack values for the specified DMU.
	 */
	public double[] getSlacks(int dmuIndex) {
		return this.slacks[dmuIndex];
	}
	
	/**
	 * Gets all the slack values for all the DMUs.
	 * @return The slack values for all the DMUs.
	 */
	public double[][] getSlacks() {
		return this.slacks;
	}
	
	
	
	
	/**
	 * Sets the reference set for all DMUs.
	 * @param referenceSets The reference sets for all DMUs.
	 */
	public void setReferenceSets(ArrayList<NonZeroLambda>[] referenceSets) {
		this.referenceSets = referenceSets;
	}
	
	/**
	 * Sets the reference set of a specific DMU.
	 * @param dmuIndex The index of the DMU to set the reference set to.
	 * @param referenceSet The reference set of the DMU.
	 */
	public void setReferenceSet(int dmuIndex, ArrayList<NonZeroLambda> referenceSet) {
		this.referenceSets[dmuIndex] = referenceSet;
	}
	
	/**
	 * Adds a NonZeroLambda to the reference set of a DMU.
	 * @param dmuIndex The index of the DMU to add a NonZeroLambda to.
	 * @param nonZeroLambda The NonZeroLambda to add to the reference set.
	 */
	public void addNonZeroLambdaToReferenceSet(int dmuIndex, NonZeroLambda nonZeroLambda) {
		this.referenceSets[dmuIndex].add(nonZeroLambda);
	}
	
	/**
	 * Gets all the reference sets for all the DMUs.
	 * @return The reference sets of all DMUs.
	 */
	public ArrayList<NonZeroLambda>[] getReferenceSet() {
		return this.referenceSets;
	}
	
	/**
	 * Gets the reference set of a specific DMU.
	 * @param dmuIndex The index of the DMU to get the reference set for.
	 * @return The reference set for the specified DMU.
	 */
	public ArrayList<NonZeroLambda> getReferenceSet(int dmuIndex) {
		return this.referenceSets[dmuIndex];
	}
	

	
	
	
	
	/**
	 * Sets all the weights for all the DMUs.
	 * @param weights The double[] [] corresponding to all the weights for all the DMUs.
	 */
	public void setWeights(double[] [] weights) {
		this.weights = weights;
	}
	
	/**
	 * Sets the weights for a specific DMU.
	 * @param dmuIndex The index of the DMU to set the weights for.
	 * @param weights The array of the weights to assign to the DMU.
	 */
	public void setWeights(int dmuIndex, double[] weights) {
		this.weights[dmuIndex] = weights;
	}
	
	/**
	 * Method to set the weights using the System.arraycopy method.
	 * @param dmuIndex The DMU index to copy value for.
	 * @param arrayToCopyFrom The weight array to copy weights from
	 * @param positionToCopyFrom the position to copy from in arrayToCopyFrom
	 * @param LengthToCopy the length to copy
	 */
	public void setWeightsArrayCopy(int dmuIndex, double[] arrayToCopyFrom, int positionToCopyFrom, 
			int LengthToCopy)  {
		System.arraycopy(arrayToCopyFrom, positionToCopyFrom, this.weights[dmuIndex], 0, LengthToCopy);
	}
	
	/**
	 * Sets the weight for a specific DMU and a specific variable.
	 * @param dmuIndex The index of the DMU to set the weight for.
	 * @param varIndex The index of the variable to set the weight for.
	 * @param weight The weight to set.
	 */
	public void setWeight(int dmuIndex, int varIndex, double weight) {
		this.weights[dmuIndex] [varIndex] = weight;
	}
	
	/**
	 * Gets the all the weights for all the DMUs.
	 * @return All the weights for all the DMUs.
	 */
	public double[] [] getWeights() {
		return weights;
	}
	
	/**
	 * Gets the weight for a specific DMU.
	 * @param dmuIndex The index of the DMU to get the weight of.
	 * @return The weights for the specified DMU.
	 */
	public double[] getWeights(int dmuIndex) {
		return this.weights[dmuIndex];
	}
	
	/**
	 * Gets a specific weight for a specific DMU.
	 * @param dmuIndex The index of the DMU to get the weight of.
	 * @param varIndex The index of the variable to get the weight of. 
	 * @return The weight for the specified variable and DMU.
	 */
	public double getWeight(int dmuIndex, int varIndex) {
		return this.weights[dmuIndex][varIndex];
	}
	
	
	
	
	/**
	 * Sets all the projections for all the DMUs.
	 * @param projectionValues The double[] [] of projections for all DMUs.
	 */
	public void setProjections(double[] [] projectionValues) {
		this.projections = projectionValues;
	}
	
	/**
	 * Sets the projection value for a specific variable and a specific DMU.
	 * @param dmuIndex The index of the DMU to set the projection value for.
	 * @param varIndex The index of the variable to set the projection value for.
	 * @param projectionValue The projection value to set.
	 */
	public void setProjection(int dmuIndex, int varIndex, double projectionValue) {
		this.projections[dmuIndex][varIndex] = projectionValue;
	}
	
	/**
	 * Gets all the projections for all the DMUs.
	 * @return All the projections for all the DMUs.
	 */
	public double[] [] getProjections() {
		return this.projections;
	}
	
	/**
	 * Gets all the projections for a specific DMU.
	 * @param dmuIndex The index of the DMU to get the projection of.
	 * @return The projections for the specified DMU.
	 */
	public double[] getProjections(int dmuIndex) {
		return this.projections[dmuIndex];
	}
	
	
	
	
	/**
	 * Sets the Solution Status.
	 * This status is used to specify whether the problem was solved successfully for all DMUs.
	 * @param statusValue The status value.
	 */
	public void setStatus(SolverReturnStatus statusValue) {
		status = statusValue;
	}
	
	/**
	 * Gets the Solution Status.
	 * This status is used to specify whether the problem was solved successfully for all DMUs.
	 * @return statusValue The status value.
	 */
	public SolverReturnStatus getStatus() {
		return this.status;
	}

	
	
	/**
	 * Sets the u0 weight (corresponding to the convexity constraint on variable models).
	 * @param u0Weight
	 */
	public void setU0Weight(int dmuIndex, double u0Weight) {
		this.u0Weights[dmuIndex] = u0Weight;
	}
	
	
	/**
	 * Gets the u0 weights for all DMUs (corresponding to the convexity constraint on variable models).
	 * @return the u0 weights
	 */
	public double[] getU0Weights() {
		return u0Weights;
	}
	
	/**
	 * Gets the u0 weight of a specific DMU (corresponding to the convexity constraint on variable models).
	 * @param dmuIndex
	 * @return
	 */
	public double getU0Weight(int dmuIndex) {
		return u0Weights[dmuIndex];
	}
	
	

	
	/**
	 * Sets the weight corresponding to the lower bound convexity constraint on
	 * general, increasing and decreasing models.
	 * @param lBConvexityConstraintWeight
	 */
	public void setlBConvexityConstraintWeights(int dmuIndex, double lBConvexityConstraintWeight) {
		this.lBConvexityConstraintWeights[dmuIndex] = lBConvexityConstraintWeight;
	}
	
	
	/**
	 * Gets the weight corresponding to the lower bound convexity constraint on
	 * general, increasing and decreasing models for a specific DMU.
	 * @param dmuIndex
	 * @return
	 */
	public double getlBConvexityConstraintWeight(int dmuIndex) {
		return lBConvexityConstraintWeights[dmuIndex];
	}
	
	/**
	 * Gets the weight corresponding to the lower bound convexity constraints on
	 * general, increasing and decreasing models for all DMUs.
	 * @return the weights corresponding to the Lower Bound Convexity Constraint
	 */
	public double[] getlBConvexityConstraintWeights() {
		return lBConvexityConstraintWeights;
	}

	
	
	
	/**
	 * Sets the weight corresponding to the upper bound convexity constraint on
	 * general, increasing and decreasing models.
	 * @param uBConvexityConstraintWeight
	 */
	public void setuBConvexityConstraintWeight(int dmuIndex, double uBConvexityConstraintWeight) {
		this.uBConvexityConstraintWeights[dmuIndex] = uBConvexityConstraintWeight;
	}

	
	/**
	 * Gets the weight corresponding to the upper bound convexity constraint on
	 * general, increasing and decreasing models for all DMUs.
	 * @return the weights corresponding to the Lower Bound Convexity Constraint
	 */
	public double[] getuBConvexityConstraintWeights() {
		return uBConvexityConstraintWeights;
	}
	
	/**
	 * Gets the weight corresponding to the upper bound convexity constraint on
	 * general, increasing and decreasing models for a specific DMU.
	 * @param dmuIndex the index of the DMU to retrieve the weight of.
	 * @return the weight corresponding to the Lower Bound Convexity Constraint
	 */
	public double getuBConvexityConstraintWeight(int dmuIndex) {
		return uBConvexityConstraintWeights[dmuIndex];
	}

	


	
	
	
}

