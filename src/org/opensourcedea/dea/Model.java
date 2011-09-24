package org.opensourcedea.dea;

import org.opensourcedea.exception.DEASolverException;
import org.opensourcedea.exception.MissingDataException;
import org.opensourcedea.exception.ProblemNotSolvedProperlyException;

public class Model {
	
	/**
	 * This is the method that is called to solve a DEA Problem.
	 * This method only loops through the DMUs. Most sub-classes would inherit
	 * this method although some sub-classes might override this solve method. For
	 * example, the SBMI class creates the constraint in the solve method has it
	 * is the same for all the problems regardless of the DMU under examination.
	 * @param deaP an instance of a DEAProblem
	 * @return a DEAPSolution
	 * @throws MissingDataException
	 * @throws DEASolverException
	 * @throws ProblemNotSolvedProperlyException
	 */
	public DEAPSolution solve(DEAProblem deaP) 
	throws MissingDataException, DEASolverException, ProblemNotSolvedProperlyException {
		
		Integer dmuIndex = 0;
		
		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVars = deaP.getNumberOfVariables();
			/* Although the negative transpose often allow the model built
			 * to be closer to the mathematical examples, returning the negative
			 * transpose is not really efficient. It might be worth changing all the
			 * models to use the positive transpose?*/ 
			double[][] transPosM = deaP.getTranspose(true);
			
			DEAPSolution tempSol = new DEAPSolution(nbDMUs, nbVars);
			
			/* As the BBC optimisations need to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the BBC models are solved in two phases.
			 * The problem will consequently be solved for each DMUs for Phase I and
			 * solved again for each DMUs for Phase II.*/
			
			for(int i = 0; i < nbDMUs; i++) {
				dmuIndex = i;
				createAndSolve(deaP, nbDMUs, nbVars, transPosM, tempSol, i);
			}
			return tempSol;			
		}
		
		catch (ProblemNotSolvedProperlyException e1) {
			throw new ProblemNotSolvedProperlyException("The problem could not be solved properly at DMU Index: "
					+ dmuIndex.toString()
					+". The error was: " + e1.getMessage());
		}
		catch (DEASolverException e2) {
			throw new DEASolverException("The problem could not be solved properly at DMU Index: "
					+ dmuIndex.toString()
					+ ". The error was: " + e2.getMessage());
		}
		catch (MissingDataException e3) {
			throw new MissingDataException("Some model data is missing.");
		}	
		
		
	}
	
	
	/** 
	 * this method is really just a signature mask for the createAndSolve methods. It is
	 * added here so that the solve method could call it. It should ALWAYS be overridden
	 * in the sub-classes extending this class. The code in the sub-classes methods overriding
	 * this createAndSolve method will solve a single DEA Problem for a specific DMUs.
	 * @param deaP an instance of a DEAProblem
	 * @param nbDMUs the number of DMUs in deaP
	 * @param nbVariables the number of variables in deaP
	 * @param transposedMatrix the transposed matrix
	 * @param returnSol the returned solution. This is a DEAPSolution in which all the solution is stored.
	 * @param dmuIndex the index of the DMU under examination
	 * @throws ProblemNotSolvedProperlyException
	 * @throws DEASolverException
	 * @throws MissingDataException
	 */
	public void createAndSolve(DEAProblem deaP, int nbDMUs,
			int nbVariables, double[][] transposedMatrix,
			DEAPSolution returnSol, Integer dmuIndex)
	throws ProblemNotSolvedProperlyException, DEASolverException, MissingDataException {
		
		
	}
	
	
}
