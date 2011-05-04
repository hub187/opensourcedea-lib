package dea;

public class InconsistentData extends DEAException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InconsistentData() {
		super("The data is inconsistent.");
	}
	public InconsistentData(String detailMsg) {
		super(detailMsg);
		// TODO Auto-generated constructor stub
	}

}
