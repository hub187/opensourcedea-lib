package dea;


/**
 * An enum of the possible Model Types (e.g. CCR, SBM...).
 * <p>
 * The enum lists, for each model, the model characteristics. These are - amongst others:
 * <ul>
 * <li>DEAModelOrientation (e.g. input/output oriented),</li>
 * <li>Efficiency type (e.g. mix or technical),</li>
 * <li>Return To Scale (Constant, Variable).</li>
 * </ul>
 * </p>
 * @author Hubert Virtos
 *
 */
public enum DEAModelType {
	CCRI ("The Charnes Cooper and Rhodes Model called CCR." +
			"This model was first introduced in 1978 and assumes constant RTS.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.TECH,
			DEAReturnToScale.Constant),
	CCRO ("The Charnes Cooper and Rhodes Model called CCR." +
			"This model was first introduced in 1978 and assumes constant RTS.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.TECH,
			DEAReturnToScale.Constant),
	BCCI ("The Banker Charnes and Cooper Model called BCC." +
			"This model was first introduced in 1984 to introduce Variable Returns to Scale (the CCR model" +
			"only assumed Constant RTS). The only difference with the CCR model is the convexity constraint" +
			"e*Lambdas = 1 / or uo in the multiplier form.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.TECH,
			DEAReturnToScale.Variable),
	BCCO ("The Banker Charnes and Cooper Model called BCC." +
			"This model was first introduced in 1984 to introduce Variable Returns to Scale (the CCR model" +
			"only assumed Constant RTS). The only difference with the CCR model is the convexity constraint" +
			"e*Lambdas = 1 / or uo in the multiplier form.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.TECH,
			DEAReturnToScale.Variable),
	SBM ("The Slack Based Model first (SBM) introduced by Tone (1997, 2001). " +
			"The model assumes constant RTS and is non-oriented." +
			"The model is also both unit invariant in respect to the unit of measurement and" +
			"monotone decreasing in each input and output slack.",
			DEAModelOrientation.NonOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Constant),
	SBMV ("The Non-Oriented version of the SBM model assuming Variable RTS.",
			DEAModelOrientation.NonOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Variable),
	SBMGRS ("The Non-Oriented version of the SBM model assuming General RTS." +
			"This model requires a set of two parameters Lower Limit and Upper Limit.",
			DEAModelOrientation.NonOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.General),
	SBMI ("The Input Oriented version of the SBM model assuming Constant RTS.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Constant),
	SBMIV ("The Input Oriented version of the SBM model assuming Variable RTS.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Variable),	
	SBMIGRS ("The Input Oriented version of the SBM model assuming General RTS.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.General),	
	SBMO ("The Ouput Oriented version of the SBM model assuming Constant RTS.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Constant),
	SBMOV ("The Ouput Oriented version of the SBM model assuming Variable RTS.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Variable),
	SBMOGRS ("The Output Oriented version of the SBM model assuming General RTS.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.General);
	
	
	private String Description;
	private DEAModelOrientation Orientation;
	private DEAEfficiencyType EfficiencyType;
	private DEAReturnToScale RTS;
	
	
	DEAModelType(String Desc, DEAModelOrientation Orient, DEAEfficiencyType EffType, DEAReturnToScale rts) {
		this.Description = Desc;
		this.Orientation = Orient;
		this.EfficiencyType = EffType;
		this.RTS = rts;
	}


	public String getDescription()
	{
		return Description;
	}
	
	public DEAModelOrientation getOrientation() {
		return Orientation;
	}
	
	public DEAEfficiencyType getDEAEfficiencyType() {
		return EfficiencyType;
	}
	
	public DEAReturnToScale getDEAReturnToScale() {
		return RTS;
	}
	
	
}

