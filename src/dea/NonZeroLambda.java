package dea;

public class NonZeroLambda {
	private int DMUIndex;
	private double LambdaValue;
	
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
