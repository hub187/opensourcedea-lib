
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
 * An enum listing all the different ranking types (standard or dense) and their corresponding description.
 * 
 * @author Hubert Virtos
 *
 */
public enum RankingType {
	STANDARD ("Thus if A ranks ahead of B and C (which compare equal) which are both ranked ahead of D," +
			"then A gets ranking number 1 (first), B gets ranking number 2 (joint second)," +
			"C also gets ranking number 2 (joint second)and D gets ranking number 4 (fourth)." +
			"In this case, nobody would get ranking number 3 (third) and that would be left as a gap."),
	DENSE ("Thus if A ranks ahead of B and C (which compare equal) which are both ranked ahead of D," +
			"then A gets ranking number 1 (first), B gets ranking number 2 (joint second)," +
			"C also gets ranking number 2 (joint second) and D gets ranking number 3 (third)");
	
	
	private String rankingDescription;
	RankingType(String desc) {
		this.rankingDescription = desc;
	}
	
	//Method to access _ModelType
	public String GetStatusDescription()
	{
		return rankingDescription;
	}
	
}
