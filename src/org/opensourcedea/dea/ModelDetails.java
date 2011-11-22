
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

import org.opensourcedea.exception.InvalidPropertyValueException;
import org.opensourcedea.model.Model;

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
	private int nbDMUs;
	private int nbVariables;
	private Model model;
	private String ModelName;
	
	public void setModel(Model mod) {
		model = mod;
	}
	
	public Model getModel() {
		return model;
	}
	
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
	 * @throws InvalidPropertyValueException
	 */
	public void setRTSLowerBound(double rtsLowerBound) throws InvalidPropertyValueException {
		if(rtsLowerBound < 0 || rtsLowerBound > 1) {
			throw new InvalidPropertyValueException("Lower Bound must be as follows: 0 <= LowerB <= 1.");
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
	 * @throws InvalidPropertyValueException
	 */
	public void setRTSUpperBound(double rtsUpperBound) throws InvalidPropertyValueException {
		if(rtsUpperBound < 1) {
			throw new InvalidPropertyValueException("Upper Bound must be greater or equal to 1.");
		}
		this.rtsUpperBound = rtsUpperBound;
	}
	
	/**
	 * Sets the number of DMUs in the DEAProblem
	 * @param nbDMUs
	 */
	public void setNbDMUs(int nbDMUs) {
		this.nbDMUs = nbDMUs;
	}
	
	/**
	 * Sets the number of Variable in the DEA Problem
	 * @return the number of DMUs
	 */
	public int getNbDMUs() {
		return nbDMUs;
	}
	
	/**
	 * Sets the number of Variables in the DEAProblem
	 * @param nbVariables
	 */
	public void setNbVariables(int nbVariables) {
		this.nbVariables = nbVariables;
	}
	
	/**
	 * Gets the number of Variable in the DEA Problem
	 * @return
	 */
	public int getNbVariables() {
		return nbVariables;
	}

	public void setModelName(String modelName) {
		ModelName = modelName;
	}

	public String getModelName() {
		return ModelName;
	}



	
	
	
}
