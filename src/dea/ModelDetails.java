package dea;

public class ModelDetails {
	private ModelType modelType;
	private double rtsLowerBound;
	private double rtsUpperBound;
	
	
	public ModelType getModelType() {
		return modelType;
	}
	
	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}
	
	
	public double getRTSLowerBound() {
		return rtsLowerBound;
	}
	
	public void setRTSLowerBound(double rtsLowerBound) throws InvalidPropertyValue {
		if(rtsLowerBound < 0 || rtsLowerBound > 1) {
			throw new InvalidPropertyValue("Lower Bound must be as follows: 0 <= LowerB <= 1.");
		}
		this.rtsLowerBound = rtsLowerBound;
	}
	
	
	public double getRTSUpperBound() {
		return rtsUpperBound;
	}
	
	public void setRTSUpperBound(double rtsUpperBound) throws InvalidPropertyValue {
		if(rtsUpperBound < 1) {
			throw new InvalidPropertyValue("Upper Bound must be greater or equal to 1.");
		}
		this.rtsUpperBound = rtsUpperBound;
	}
	
	
	
}
