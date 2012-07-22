
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

import java.io.Serializable;

/**
 * This class represents the Non Zero Lambda elements of a reference set.
 * @author Hubert.Virtos
 *
 */
public class NonZeroLambda implements Serializable {
	
	/**
	 * The auto-generated serial version UID
	 */
	private static final long serialVersionUID = -6788465242305575325L;
	
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
	 * @return The NonZeroLambda
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
