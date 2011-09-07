package org.opensourcedea.dea;

public interface IModel {
	public DEAPSolution solve(DEAProblem deaP) throws MissingData;
}
