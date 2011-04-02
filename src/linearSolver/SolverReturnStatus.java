package linearSolver;

public enum SolverReturnStatus {
	NA ("Default Return Status."),
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
