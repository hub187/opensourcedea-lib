
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
	 * @return The ModelType.
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
