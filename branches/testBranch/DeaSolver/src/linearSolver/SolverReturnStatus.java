package linearSolver;


/**
 * enum listing the possible Solver Return Status.
 * @author Hubert Virtos
 *
 */
public enum SolverReturnStatus {
	NA ("Default Return Initialisation Status."),
	/* Each solution of an instance of a DEAProblem class is created by default with the 'NA' return status.
	 * After optimisation, each instance of a DEAProblem class MUST have another status corresponding to the optimisation results.
	 * This is important as the getWhateverPartOfTheSolution methods will in future test whether a the problem has been solved prior to
	 * returning the solution (i.e. check whether InstanceOfDEAProblem.Solution.Status == SolverReturnStatus.NA).*/
	ModelCreationFailure ("The Linear Problem could not be created"),
	OptimalSolutionFound ("The model could be created and an optimal solution was found."),
	OptimalSolutionNotfound ("the Model could be created but an optimal solution was not found."),
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
