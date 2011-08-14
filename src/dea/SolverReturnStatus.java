
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
 * An enum listing the possible Solver Return Status.
 * </br>
 * @author Hubert Virtos
 *
 */
public enum SolverReturnStatus {
	NOT_SOLVED ("Model Initialised but not solved."),
	/* Each solution of an instance of a DEAProblem class is created by default with the 'NA' return status.
	 * After optimisation, each instance of a DEAProblem class MUST have another status corresponding to the
	 * optimisation results.
	 * This is important as the getWhateverPartOfTheSolution methods will in future test whether a the problem has
	 * been solved prior to returning the solution (i.e. check whether InstanceOfDEAProblem.Solution.Status
	 * 	== SolverReturnStatus.NA).*/
	MODEL_CREATION_FAILURE ("One or all the Linear Problems could not be created"),
	OPTIMAL_SOLUTION_FOUND ("All the models could be created and optimal solutions were found each time."),
	OPTIMAL_SOLUTION_NOT_FOUND ("One or all the Models could be created but an optimal solution was not found for one or several DEA problems."),
	UNKNOWN_ERROR ("An unknown problem occured which unexpectedly stopped the optimisation process.");
	
	
	private String description;
	SolverReturnStatus(String desc) {
		this.description = desc;
	}
	
	//Method to access _ModelType
	public String getStatusDescription()
	{
		return this.description;
	}
	
}
