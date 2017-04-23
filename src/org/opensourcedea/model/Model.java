package org.opensourcedea.model;

import org.opensourcedea.dea.DEAPSolution;
import org.opensourcedea.dea.DEAProblem;
import org.opensourcedea.dea.SolverReturnStatus;
import org.opensourcedea.exception.DEASolverException;
import org.opensourcedea.exception.MissingDataException;
import org.opensourcedea.exception.ProblemNotSolvedProperlyException;
import org.opensourcedea.linearSolver.SolverResults;

public class Model {

	/**
	 * This is the method that is called to solve a DEA Problem.
	 * This method only loops through the DMUs. Most sub-classes would inherit
	 * this method (as they extend the Model class) although some sub-classes might override this solve method. For
	 * example, the SBMI creates the constraint in the solve method as it
	 * is the same for all the problems regardless of the DMU under examination (it thus more computer efficient to override this solve method).
	 * @param deaP an instance of a DEAProblem
	 * @throws MissingDataException
	 * @throws DEASolverException
	 * @throws ProblemNotSolvedProperlyException
	 */
	public void solveAll(DEAProblem deaP) 
			throws MissingDataException, DEASolverException, ProblemNotSolvedProperlyException {

		Integer dmuIndex = 0;

		try {
			int nbDMUs = deaP.getNumberOfDMUs();

			/* Although the negative transpose often allow the model built
			 * to be closer to the mathematical examples, returning the negative
			 * transpose is not really efficient. It might be worth changing all the
			 * models to use the positive transpose?*/ 
			double[][] transPosM = deaP.getTranspose(true);


			/* As the optimisations need to be ran for all DMUs, 
			 * the program will loop through all DMUs.
			 * Also, the BBC models are solved in two phases.
			 */

			for(int i = 0; i < nbDMUs; i++) {
				solveOne(deaP, transPosM, i);
			}		
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
	 * This method solves a DEA Problem for a given DMU.
	 * @param deaP
	 * @param negativeTransposedM
	 * @param dmuIndex
	 * @throws ProblemNotSolvedProperlyException
	 * @throws DEASolverException
	 * @throws MissingDataException
	 */
	public void solveOne(DEAProblem deaP, double[][] negativeTransposedM, int dmuIndex)
			throws ProblemNotSolvedProperlyException, DEASolverException, MissingDataException {
		
		Integer dmuInd = dmuIndex;

		try {
			int nbDMUs = deaP.getNumberOfDMUs();
			int nbVars = deaP.getNumberOfVariables();

			createAndSolve(deaP, nbDMUs, nbVars, negativeTransposedM, deaP.getSolution(), dmuIndex);		
		}

		catch (ProblemNotSolvedProperlyException e1) {
			throw new ProblemNotSolvedProperlyException("The problem could not be solved properly at DMU Index: "
					+ dmuInd.toString()
					+". The error was: " + e1.getMessage());
		}
		catch (DEASolverException e2) {
			throw new DEASolverException("The problem could not be solved properly at DMU Index: "
					+ dmuInd.toString()
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
		throw new DEASolverException("The class of the problem you are trying to solve does not override" +
				" the createAndSolve method. You need to review the source code or contact the person who gave you the code.");

	}
	
	
	public void checkSolverStatus(SolverResults sol, Integer dmuIndex, String dmuName) {
		if(sol.Status == SolverReturnStatus.SOLUTION_FOUND_LOW_ACCURACY) {
			System.out.println("Low solver accuracy for DMU: " + dmuName + " (DMU Index: " + dmuIndex + ").");
		}
	}


}
