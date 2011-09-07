package org.opensourcedea.dea;

public abstract class AbstractModel implements IModel {
	public abstract void createAndSolve(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex);
}
