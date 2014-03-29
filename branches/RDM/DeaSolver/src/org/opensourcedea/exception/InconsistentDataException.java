
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

package org.opensourcedea.exception;


/**
 * A DEAException thrown when number of variables or DMUs do not match between different element of the DEAProblem
 * (e.g. 1050 VariableTypes but only 1040 VariableNames).
 * </br>
 * @author Hubert Virtos
 *
 */
public class InconsistentDataException extends DEAException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InconsistentDataException() {
		super("The data is inconsistent.");
	}
	public InconsistentDataException(String detailMsg) {
		super(detailMsg);
	}

}
