package dea;


/**
 * An enum of the possible Model Types (e.g. CCR...).
 * <p>
 * @author Hubert Virtos
 *
 */
public enum DEAModelType {
	CCRI ("The Charnes Cooper and Rhodes Model called CCR. This model was first introduced in 1978 and assumes constant RTS."),
	CCRO ("The Charnes Cooper and Rhodes Model called CCR. This model was first introduced in 1978 and assumes constant RTS."),
	SBM ("The Slack Based Model first (SBM) introduced by Tone (1997, 2001). The model assumes constant RTS and is non-oriented." +
			"The model is also both unit invariant in respect to the unit of measurement and monotone decreasing in each" +
			"input and output slack."),
	SBMI ("The Input Oriented version of the SBM model assuming Constant RTS.");
	
	
	private String Description;
	
	
	DEAModelType(String Desc) {
		this.Description = Desc;
	}
	
	//Method to access _ModelType
	public String GetStatusDescription()
	{
		return Description;
	}
	
}

