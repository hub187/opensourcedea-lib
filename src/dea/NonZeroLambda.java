package dea;

/**
 * This class represents the Non Zero Lambda elements of a reference set.
 * @author Hubert.Virtos
 *
 */
public class NonZeroLambda {
	private int DMUIndex;
	private double LambdaValue;
	
	/**
	 * Constructor of the NonZeroLambda class.
	 * @param dmuIndex The index corresponding to the specific DMU.
	 * @param lambdaValue The associated Lamda value corresponding to the specific DMU index.
	 */
	public NonZeroLambda(int dmuIndex, double lambdaValue) {
		setDMUIndex(dmuIndex);
		setLambdaValue(lambdaValue);
	}

	
	public NonZeroLambda getNonZeroLambda() {
		return this;
	}

	public void setLambdaValue(double lambdaValue) {
		LambdaValue = lambdaValue;
	}

	public double getLambdaValue() {
		return LambdaValue;
	}

	public void setDMUIndex(int dMUIndex) {
		DMUIndex = dMUIndex;
	}

	public int getDMUIndex() {
		return DMUIndex;
	}
	
}
