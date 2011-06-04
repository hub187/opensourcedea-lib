package dea;


/**
 * An enum of the possible Model Types (e.g. CCR, SBM...).
 * <p>
 * The enum lists, for each model, the model characteristics. These are - amongst others:
 * <ul>
 * <li>ModelOrientation (e.g. input/output oriented),</li>
 * <li>Efficiency type (e.g. mix or technical),</li>
 * <li>Return To Scale (CONSTANT, VARIABLE).</li>
 * </ul>
 * </p>
 * @author Hubert Virtos
 *
 */
public enum ModelType {
	CCRI ("The Charnes Cooper and Rhodes Model called CCR." +
			"This model was first introduced in 1978 and assumes CONSTANT RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.CONSTANT),
	CCRO ("The Charnes Cooper and Rhodes Model called CCR." +
			"This model was first introduced in 1978 and assumes CONSTANT RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.CONSTANT),
	BCCI ("The Banker Charnes and Cooper Model called BCC." +
			"This model was first introduced in 1984 to introduce VARIABLE Returns to Scale (the CCR model" +
			"only assumed CONSTANT RTS). The only difference with the CCR model is the convexity constraint" +
			"e*Lambdas = 1 / or uo in the multiplier form.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.VARIABLE),
	BCCO ("The Banker Charnes and Cooper Model called BCC." +
			"This model was first introduced in 1984 to introduce VARIABLE Returns to Scale (the CCR model" +
			"only assumed CONSTANT RTS). The only difference with the CCR model is the convexity constraint" +
			"e*Lambdas = 1 / or uo in the multiplier form.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.VARIABLE),
	GRSI ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint can be relaxed." +
			"The only difference with the BCCI model is the fact e*Lambdas are as follows:" +
			"0 <= e*Lambdas <= Positive_Infinity." +
			"Setting e*Lambdas = 1 is equivalent to the BCC model.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.GENERAL),
	GRSO ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint can be relaxed." +
			"The only difference with the BCCO model is the fact e*Lambdas are as follows:" +
			"0 <= e*Lambdas <= Positive_Infinity." +
			"Setting e*Lambdas = 1 is equivalent to the BCC model.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.GENERAL),
	IRSI ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only" +
			"allow INCREASING Returns to Scale." +
			"The only difference with the BCCI model is the fact e*Lambdas are as follows:" +
			"1 <= e*Lambdas",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.INCREASING),
	IRSO ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only" +
			"allow INCREASING Returns to Scale." +
			"The only difference with the BCCO model is the fact e*Lambdas are as follows:" +
			"1 <= e*Lambdas",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.INCREASING),
	DRSI ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only" +
			"allow DECREASING Returns to Scale." +
			"The only difference with the BCCI model is the fact e*Lambdas are as follows:" +
			"0 <= e*Lambdas <= 1.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.DECREASING),
	DRSO ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only" +
			"allow DECREASING Returns to Scale." +
			"The only difference with the BCCO model is the fact e*Lambdas are as follows:" +
			"0 <= e*Lambdas <= 1.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnToScale.DECREASING),
	SBM ("The Slack Based Model first (SBM) introduced by Tone (1997, 2001). " +
			"The model assumes CONSTANT RTS and is non-oriented." +
			"The model is also both unit invariant in respect to the unit of measurement and" +
			"monotone DECREASING in each input and output slack.",
			ModelOrientation.NON_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.CONSTANT),
	SBMV ("The Non-Oriented version of the SBM model assuming VARIABLE RTS.",
			ModelOrientation.NON_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.VARIABLE),
	SBMGRS ("The Non-Oriented version of the SBM model assuming GENERAL RTS." +
			"This model requires a set of two parameters Lower Limit and Upper Limit.",
			ModelOrientation.NON_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.GENERAL),
	SBMI ("The Input Oriented version of the SBM model assuming CONSTANT RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.CONSTANT),
	SBMIV ("The Input Oriented version of the SBM model assuming VARIABLE RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.VARIABLE),	
	SBMIGRS ("The Input Oriented version of the SBM model assuming GENERAL RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.GENERAL),	
	SBMO ("The Ouput Oriented version of the SBM model assuming CONSTANT RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.CONSTANT),
	SBMOV ("The Ouput Oriented version of the SBM model assuming VARIABLE RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.VARIABLE),
	SBMOGRS ("The Output Oriented version of the SBM model assuming GENERAL RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnToScale.GENERAL);
	
	
	private String description;
	private ModelOrientation orientation;
	private EfficiencyType efficiencyType;
	private ReturnToScale rts;
	
	
	ModelType(String desc, ModelOrientation orient, EfficiencyType effType, ReturnToScale rts) {
		this.description = desc;
		this.orientation = orient;
		this.efficiencyType = effType;
		this.rts = rts;
	}


	public String getDescription()
	{
		return this.description;
	}
	
	public ModelOrientation getOrientation() {
		return this.orientation;
	}
	
	public EfficiencyType getEfficiencyType() {
		return this.efficiencyType;
	}
	
	public ReturnToScale getReturnToScale() {
		return this.rts;
	}
	
	
}

