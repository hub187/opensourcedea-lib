package dea;


/**
 * An enum listing the possible Solver Return Status.
 * </br>
 * @author Hubert Virtos
 *
 */
public enum SolverReturnStatus {
	NOT_SOLVED ("Model Initialised but not solved."),
	/* Each solution of an instance of a DEAProblem class is created by default with the 'NA' return status.
	 * After optimisation, each instance of a DEAProblem class MUST have another status corresponding to the
	 * optimisation results.
	 * This is important as the getWhateverPartOfTheSolution methods will in future test whether a the problem has
	 * been solved prior to returning the solution (i.e. check whether InstanceOfDEAProblem.Solution.Status
	 * 	== SolverReturnStatus.NA).*/
	MODEL_CREATION_FAILURE ("One or all the Linear Problems could not be created"),
	OPTIMAL_SOLUTION_FOUND ("One or all the models could be created and optimal solutions were found each time."),
	OPTIMAL_SOLUTION_NOT_FOUND ("One or all the Models could be created but an optimal solution was not found at least once."),
	UNKNOWN_ERROR ("An unknown problem occured which stopped the optimisation process.");
	
	
	private String description;
	SolverReturnStatus(String desc) {
		this.description = desc;
	}
	
	//Method to access _ModelType
	public String getStatusDescription()
	{
		return description;
	}
	
}
