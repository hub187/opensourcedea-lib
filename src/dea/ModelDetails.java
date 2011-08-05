package dea;

/**
 * This class describes details of a DEA Model in terms of model type, and bounds.
 * If more information need to be added to a model, this is the right place to do it.
 * @author Hubert Virtos
 *
 */
public class ModelDetails {
	private ModelType modelType;
	private double rtsLowerBound;
	private double rtsUpperBound;
	
	
	/**
	 * Gets the Model ModelType.
	 * @return
	 */
	public ModelType getModelType() {
		return modelType;
	}
	
	/**
	 * Sets the Model ModelType.
	 * @param modelType The Model ModelType
	 */
	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}
	
	
	/**
	 * Gets the Model Return To Scale Lower Bound limit (rtsLowerBound).
	 * @return The Model rtsLowerBound.
	 */
	public double getRTSLowerBound() {
		return rtsLowerBound;
	}
	
	/**
	 * Sets the Model Return To Scale Lower Bound limit (RTSLowerBound).
	 * @param rtsLowerBound The Model rtsLowerBound.
	 * @throws InvalidPropertyValue
	 */
	public void setRTSLowerBound(double rtsLowerBound) throws InvalidPropertyValue {
		if(rtsLowerBound < 0 || rtsLowerBound > 1) {
			throw new InvalidPropertyValue("Lower Bound must be as follows: 0 <= LowerB <= 1.");
		}
		this.rtsLowerBound = rtsLowerBound;
	}
	
	
	/**
	 * Gets the Model Return To Scale Upper Bound (rtsUpperBound).
	 * @return The Model rtsUpperBound
	 */
	public double getRTSUpperBound() {
		return rtsUpperBound;
	}
	
	/**
	 * Sets the Model Return To Scale Upper Bound (rtsUpperBound).
	 * @param rtsUpperBound The Model rtsUpperBound.
	 * @throws InvalidPropertyValue
	 */
	public void setRTSUpperBound(double rtsUpperBound) throws InvalidPropertyValue {
		if(rtsUpperBound < 1) {
			throw new InvalidPropertyValue("Upper Bound must be greater or equal to 1.");
		}
		this.rtsUpperBound = rtsUpperBound;
	}
	
	
	
}
