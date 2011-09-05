
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
 * A DEAException extending InconsistentData thrown when number of DMUs do not match between different
 * element of the DEAProblem.
 * </br>
 * @author Hubert Virtos
 *
 */
public class InconsistentNoOfDMUs extends InconsistentData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InconsistentNoOfDMUs() {
		super("The number of DMUs does not seem to match with the data. Please check the DMUNames and DataMatrix.");
	}
	
}
