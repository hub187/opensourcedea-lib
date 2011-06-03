package dea;

public class ProblemNotSolvedProperly extends DEAException {

	private static final long serialVersionUID = 1L;

	public ProblemNotSolvedProperly() {
		super("You are trying to read the solution but the problem was not solved successfully.");
	}
	public ProblemNotSolvedProperly(String detailMsg) {
		super(detailMsg);
	}
	
}
