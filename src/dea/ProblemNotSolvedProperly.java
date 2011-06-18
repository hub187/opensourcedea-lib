package dea;

public class ProblemNotSolvedProperly extends DEAException {

	private static final long serialVersionUID = 1L;

	public ProblemNotSolvedProperly() {
		super("One of the problem was not solved successfully." +
				"You cannot read a solution from a DEA Problem when a DMU optimisation was not solved optimally.");
	}
	public ProblemNotSolvedProperly(String detailMsg) {
		super(detailMsg);
	}
	
}
