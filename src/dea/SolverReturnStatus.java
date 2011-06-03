package dea;


/**
 * An enum listing the possible Solver Return Status.
 * </br>
 * @author Hubert Virtos
 *
 */
public enum SolverReturnStatus {
	NA ("Model Initialised but not solved."),
	/* Each solution of an instance of a DEAProblem class is created by default with the 'NA' return status.
	 * After optimisation, each instance of a DEAProblem class MUST have another status corresponding to the
	 * optimisation results.
	 * This is important as the getWhateverPartOfTheSolution methods will in future test whether a the problem has
	 * been solved prior to returning the solution (i.e. check whether InstanceOfDEAProblem.Solution.Status
	 * 	== SolverReturnStatus.NA).*/
	ModelCreationFailure ("One or all the Linear Problems could not be created"),
	OptimalSolutionFound ("One or all the models could be created and optimal solutions were found each time."),
	OptimalSolutionNotfound ("One or all the Models could be created but an optimal solution was not found at least once."),
	UnknownError ("An unknown problem occured which stopped the optimisation process.");
	
	
	private String Description;
	SolverReturnStatus(String Desc) {
		this.Description = Desc;
	}
	
	//Method to access _ModelType
	public String GetStatusDescription()
	{
		return Description;
	}
	
}
