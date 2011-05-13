package dea;


/**
 * An enum of the possible Model Types (e.g. CCR...).
 * <p>
 * The enum also lists all the model characteristics. These are - amongst others:
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
	SBM ("The Slack Based Model first (SBM) introduced by Tone (1997, 2001). " +
			"The model assumes constant RTS and is non-oriented." +
			"The model is also both unit invariant in respect to the unit of measurement and" +
			"monotone decreasing in each input and output slack.",
			DEAModelOrientation.NonOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Constant),
	SBMI ("The Input Oriented version of the SBM model assuming Constant RTS.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Constant),
	SBMO ("The Ouput Oriented version of the SBM model assuming Constant RTS.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Constant),
	SBMV ("The Non-Oriented version of the SBM model assuming Variable RTS.",
			DEAModelOrientation.NonOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Variable),
	SBMIV ("The Input Oriented version of the SBM model assuming Variable RTS.",
			DEAModelOrientation.InputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Variable),
	SBMOV ("The Ouput Oriented version of the SBM model assuming Variable RTS.",
			DEAModelOrientation.OutputOriented,
			DEAEfficiencyType.MIX,
			DEAReturnToScale.Variable);
	
	
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

