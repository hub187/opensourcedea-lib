package org.opensourcedea.dea;

import java.util.ArrayList;

public class DEAPSolutionVariableRTS extends DEAPSolution {

	double[] u0Weights;
	
	@SuppressWarnings("unchecked")
	public DEAPSolutionVariableRTS(int nbDMUs, int nbVariables) {
		super(nbDMUs, nbVariables);
		
		/*The solution Attributes will be put one by one at each solver optimisation (for each DMU).
		 * The array are initialised here for this reason.*/
		setObjectives(new double[nbDMUs]);
		setSlacks(new double[nbDMUs] [nbVariables]);
		setReferenceSets((ArrayList<NonZeroLambda>[])new ArrayList[nbDMUs]);
		setWeights(new double[nbDMUs] [nbVariables]);
		setU0Weights(new double[nbDMUs]);
		setProjections(new double[nbDMUs] [nbVariables]);
		setStatus(SolverReturnStatus.NOT_SOLVED);
	}
	

	
	/**
	 * Sets all the U0 weights for all the DMUs.
	 * @param U0Wghts The double[] corresponding to all the weights for all the DMUs.
	 */
	public void setU0Weights(double[] U0Wghts) {
		this.u0Weights = U0Wghts;
	}
	
	/**
	 * Sets the U0 weight for a specific DMU.
	 * @param dmuIndex The index of the DMU to set the weights for.
	 * @param U0Wght The double corresponding to the U0 weights to assign to the DMU.
	 */
	public void setWeights(int dmuIndex, double U0Wght) {
		this.u0Weights[dmuIndex] = U0Wght;
	}

	
	/**
	 * Gets the all the U0 weights for all the DMUs.
	 * @return All the weights for all the DMUs.
	 */
	public double[] getU0Weights() {
		return u0Weights;
	}
	
	/**
	 * Gets the U0 weight for a specific DMU.
	 * @param dmuIndex The index of the DMU to get the U0 weight of.
	 * @return The U0 weight for the specified DMU.
	 */
	public double getU0Weights(int dmuIndex) {
		return this.u0Weights[dmuIndex];
	}
	
}
