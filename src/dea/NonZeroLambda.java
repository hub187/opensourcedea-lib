package dea;

/**
 * This class represents the Non Zero Lambda elements of a reference set.
 * @author Hubert.Virtos
 *
 */
public class NonZeroLambda {
	private int dmuIndex;
	private double lambdaValue;
	
	/**
	 * Constructor of the NonZeroLambda class.
	 * @param dmuIndex The index corresponding to the specific DMU.
	 * @param lambdaValue The associated Lamda value corresponding to the specific DMU index.
	 */
	public NonZeroLambda(int dmuIndex, double lambdaValue) {
		setDMUIndex(dmuIndex);
		setLambdaValue(lambdaValue);
	}

	
	/**
	 * Gets the NonZeroLambda (this).
	 * @return
	 */
	public NonZeroLambda getNonZeroLambda() {
		return this;
	}

	/**
	 * Updates the value of a NonZeroLambda.
	 * @param lValue The value of the Lambda to set.
	 */
	public void setLambdaValue(double lValue) {
		lambdaValue = lValue;
	}
	
	/**
	 * Gets the value of a NonZeroLambda.
	 * @return The value of a NonZeroLambda.
	 */
	public double getLambdaValue() {
		return lambdaValue;
	}
	
	/**
	 * Updates the DMU Index of a NonZeroLambda.
	 * @param dMUIndex The index of the DMU to set.
	 */
	public void setDMUIndex(int dMUIndex) {
		dmuIndex = dMUIndex;
	}

	/**
	 * Gets the DMU Index of a NonZeroLambda.
	 * @return The index of the DMU.
	 */
	public int getDMUIndex() {
		return dmuIndex;
	}
	
}
