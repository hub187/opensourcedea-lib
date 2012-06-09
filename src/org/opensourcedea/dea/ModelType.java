
/*	<DEASolver (googleproject opensourcedea) is a java DEA solver.>
    Copyright (C) <2010>  <Hubert Paul Bernard VIRTOS>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    
    @author Hubert Paul Bernard VIRTOS
    
*/

package org.opensourcedea.dea;



/**
 * An enum of the possible Model Types (e.g. CCR, SBM...). If new models are added to the library,
 * they will have to be listed in this enum.
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
	CCR_I ("The Charnes Cooper and Rhodes Model called CCR. " +
			"This model was first introduced in 1978 and assumes CONSTANT RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.STANDARD),
	CCR_O ("The Charnes Cooper and Rhodes Model called CCR. " +
			"This model was first introduced in 1978 and assumes CONSTANT RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.STANDARD),
	BCC_I ("The Banker Charnes and Cooper Model called BCC. " +
			"This model was first introduced in 1984 to introduce VARIABLE Returns to Scale (the CCR model " +
			"only assumed CONSTANT RTS).\rThe only difference with the CCR model is the convexity constraint " +
			"e*Lambdas = 1 corresponding to the uo weight in the multiplier form.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.STANDARD),
	BCC_O ("The Banker Charnes and Cooper Model called BCC. " +
			"This model was first introduced in 1984 to introduce VARIABLE Returns to Scale (the CCR model " +
			"only assumed CONSTANT RTS).\rThe only difference with the CCR model is the convexity constraint " +
			"e*Lambdas = 1 / or uo in the multiplier form.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.STANDARD),
	GRS_I ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint can be relaxed.\r" +
			"The only difference with the BCCI model is the fact e*Lambdas are as follows: " +
			"0 <= e*Lambdas <= Positive_Infinity. " +
			"Setting e*Lambdas = 1 is equivalent to the BCC model.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.GENERAL,
			ModelVariablesType.STANDARD),
	GRS_O ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint can be relaxed.\r" +
			"The only difference with the BCCO model is the fact e*Lambdas are as follows: " +
			"0 <= e*Lambdas <= Positive_Infinity. " +
			"Setting e*Lambdas = 1 is equivalent to the BCC model.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.GENERAL,
			ModelVariablesType.STANDARD),
	IRS_I ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only " +
			"allow INCREASING Returns to Scale. " +
			"\rThe only difference with the BCCI model is the fact e*Lambdas are as follows: " +
			"1 <= e*Lambdas",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.INCREASING,
			ModelVariablesType.STANDARD),
	IRS_O ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only " +
			"allow INCREASING Returns to Scale. " +
			"\rThe only difference with the BCCO model is the fact e*Lambdas are as follows: " +
			"1 <= e*Lambdas",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.INCREASING,
			ModelVariablesType.STANDARD),
	DRS_I ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only " +
			"allow DECREASING Returns to Scale. " +
			"\rThe only difference with the BCCI model is the fact e*Lambdas are as follows: " +
			"0 <= e*Lambdas <= 1.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.DECREASING,
			ModelVariablesType.STANDARD),
	DRS_O ("An extension of the Banker Charnes and Cooper Model (BCC) where the convexity constraint only " +
			"allow DECREASING Returns to Scale. " +
			"\rThe only difference with the BCCO model is the fact e*Lambdas are as follows: " +
			"0 <= e*Lambdas <= 1.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.DECREASING,
			ModelVariablesType.STANDARD),
	SBM ("The Slack Based Model first (SBM) introduced by Tone (1997, 2001). " +
			"The model assumes CONSTANT RTS and is non-oriented. " +
			"\rThe model is also both unit invariant in respect to the unit of measurement and " +
			"monotone DECREASING in each input and output slack.",
			ModelOrientation.NON_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.STANDARD),
	SBM_V ("The Non-Oriented version of the SBM model assuming VARIABLE RTS.",
			ModelOrientation.NON_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.STANDARD),
	SBM_GRS ("The Non-Oriented version of the SBM model assuming GENERAL RTS. " +
			"This model requires a set of two parameters Lower Limit and Upper Limit.",
			ModelOrientation.NON_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.GENERAL,
			ModelVariablesType.STANDARD),
	SBM_I ("The Input Oriented version of the SBM model assuming CONSTANT RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.STANDARD),
	SBM_I_V ("The Input Oriented version of the SBM model assuming VARIABLE RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.STANDARD),	
	SBM_I_GRS ("The Input Oriented version of the SBM model assuming GENERAL RTS.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.GENERAL,
			ModelVariablesType.STANDARD),	
	SBM_O ("The Ouput Oriented version of the SBM model assuming CONSTANT RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.STANDARD),
	SBM_O_V ("The Ouput Oriented version of the SBM model assuming VARIABLE RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.STANDARD),
	SBM_O_GRS ("The Output Oriented version of the SBM model assuming GENERAL RTS.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.MIX,
			ReturnsToScale.GENERAL,
			ModelVariablesType.STANDARD),
	NC_I ("The Non-Controllable Model in its Input Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming CONSTANT RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_I_V ("The Non-Controllable Model in its Input Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming VARIABLE RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_O ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming CONSTANT RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_O_V ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming VARIABLE RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_I_GRS ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming GENERAL RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.GENERAL,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_O_GRS ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming GENERAL RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.GENERAL,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_I_IRS ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming INCREASING RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.INCREASING,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_O_IRS ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming INCREASING RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.INCREASING,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_I_DRS ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming DECREASING RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.DECREASING,
			ModelVariablesType.NON_CONTROLLABLE),
	NC_O_DRS ("The Non-Controllable Model in its Output Oriented version. This model does not allow " +
			"any slacks on the Non-Controllable variables. The model is assuming DECREASING RTS." +
			"\rNon-Controllable variables needs to be set with type: NON_CONTROLLABLE_INPUT / OUTPUT " +
			"accordingly. The model solves NC problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.DECREASING,
			ModelVariablesType.NON_CONTROLLABLE),
	ND_I ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming CONSTANT RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_I_V ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming VARIABLE RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_I_IRS ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming INCREASING RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.INCREASING,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_I_DRS ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming DECREASING RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.DECREASING,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_I_GRS ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming GENERAL RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.INPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.GENERAL,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_O ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming CONSTANT RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE.",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.CONSTANT,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_O_V ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming VARIABLE RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE. ",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.VARIABLE,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_O_IRS ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming INCREASING RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE. ",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.INCREASING,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_O_DRS ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming DECREASING RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE. ",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.DECREASING,
			ModelVariablesType.NON_DISCRETIONARY),
	ND_O_GRS ("The Non-Discretionary Model in its Input Oriented version. This model allows " +
			"slacks on the Non-discretionary variables but these do not contribute to the score." +
			"\rThe model is assuming GENERAL RTS." +
			"\rNon-discretionary variables need to be set with type: NON_DISCRETIONARY_INPUT / OUTPUT " +
			"accordingly. The model solves ND problems in ONE OPTIMISATION STAGE. ",
			ModelOrientation.OUTPUT_ORIENTED,
			EfficiencyType.TECH,
			ReturnsToScale.GENERAL,
			ModelVariablesType.NON_DISCRETIONARY);
	
	
	private String description;
	private ModelOrientation orientation;
	private EfficiencyType efficiencyType;
	private ReturnsToScale rts;
	private ModelVariablesType modVarType;
	
	
	ModelType(String desc, ModelOrientation orient, EfficiencyType effType, ReturnsToScale rts, ModelVariablesType modVarType) {
		this.description = desc;
		this.orientation = orient;
		this.efficiencyType = effType;
		this.rts = rts;
		this.modVarType = modVarType;
	}

	
	/**
	 * Returns the description of the ModelType.
	 * @return The String corresponding to the description of the ModelType.
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Returns the Model Orientation of the ModelType (e.g. input oriented, output oriented).
	 * @return The ModelOrientation corresponding to the ModelType
	 */
	public ModelOrientation getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Returns the Efficiency Type of the ModelType.
	 * @return The EfficiencyType of the ModelType
	 */
	public EfficiencyType getEfficiencyType() {
		return this.efficiencyType;
	}
	
	/**
	 * Returns the Return To Scale corresponding to the ModelType.
	 * @return The ReturnToScale corresponding to the ModelType.
	 */
	public ReturnsToScale getReturnToScale() {
		return this.rts;
	}
	
	/**
	 * Returns the name of the ModelType.
	 * @return The String of the Name of the ModelType.
	 */
	public String getName() {
		return this.name();		
	}
	
	/**
	 * Returns the number of ModelTypes (DEA Models) available in the OSDEA library.
	 * @return The int corresponding to the number of ModelTypes.
	 */
	public static int getNumberOfModelsAvailable() {
		int nbModels = ModelType.values().length;
		return nbModels;
	}
	
	public ModelVariablesType getModelVariablesType() {
		return this.modVarType;
	}
	
	
}

