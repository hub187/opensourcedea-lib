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

	
	public NonZeroLambda getNonZeroLambda() {
		return this;
	}

	public void setLambdaValue(double lValue) {
		lambdaValue = lValue;
	}

	public double getLambdaValue() {
		return lambdaValue;
	}

	public void setDMUIndex(int dMUIndex) {
		dmuIndex = dMUIndex;
	}

	public int getDMUIndex() {
		return dmuIndex;
	}
	
}
