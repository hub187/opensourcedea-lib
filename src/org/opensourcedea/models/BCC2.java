package org.opensourcedea.models;

import org.opensourcedea.dea.*;

public class BCC2 extends AbstractModel implements IModel {

	@Override
	public DEAPSolution solve(DEAProblem deaP) throws MissingDataException {
		
	
		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVars = deaP.getNumberOfVariables();
			double[][] transPosM = deaP.getTranspose(true);
			DEAPSolution tempSol = new DEAPSolution(nbDMUs, nbVars);
			for(int i = 0; i < nbDMUs; i++) {
				createAndSolve(deaP, nbDMUs, nbVars, transPosM, tempSol, i);
			}
			return tempSol;			
		} catch (MissingDataException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		

		
		
	}

	@Override
	public void createAndSolve(DEAProblem deaP, int nbDMUs, int nbVariables,
			double[][] transposedMatrix, DEAPSolution returnSol,
			Integer dmuIndex) {
		// TODO Auto-generated method stub
		
	}
	
}
