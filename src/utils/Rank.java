
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
    @version 0.1 2011-02-04
*/

package utils;

/**
 * 
 * 
 * This class only deals with implementing the bubblesort algorithm to find the DMU ranks.
 * <p>
 * Most of the code is based on the code developed by algolist which can be found below.
 * <p>
 * @see <a href="http://www.algolist.net/Algorithms/Sorting/Bubble_sort">Algolist Bubble sort Algorithm Implemetation</a>
 * <p>
 * @author Hubert Virtos
 *
 */
public class Rank {

	/**
	 * Returns the ranks.
	 * @param ArrayToSort The Array To sort (DMU Objectives)
	 * @param LowestIsZero A boolean. If true, specifies that the lowest value of the Array is rank '0'.
	 * @return An int[] with the rank positions.
	 */
	public static int[] GetRanks(double[] ArrayToSort, boolean LowestIsZero) {
		
		//Copy original Array to avoid messing with external data
		double[] WorkArray = new double[ArrayToSort.length];
		System.arraycopy(ArrayToSort, 0, WorkArray, 0, ArrayToSort.length);
		//Create rank Array (function 'range' in Python)
		int[] rankArray = new int[ArrayToSort.length];
		for(int i = 0; i < rankArray.length; i++) {
			rankArray[i] = i;
		}
		
		
	      boolean swapped = true;
	      int j = 0;
	      double tmp;
	      int rankTmp;
	      while (swapped) {
	            swapped = false;
	            j++;
	            for (int i = 0; i < WorkArray.length - j; i++) {                                       
	                  if (WorkArray[i] > WorkArray[i + 1]) {                          
	                        tmp = WorkArray[i];
	                        WorkArray[i] = WorkArray[i + 1];
	                        WorkArray[i + 1] = tmp;
	                        
	                        rankTmp = rankArray[i];
	                        rankArray[i] = rankArray[i + 1];
	                        rankArray[i + 1] = rankTmp;
	                        swapped = true;
	                  }
	            }                
	      }
		
		
		
		//quickSort(WorkArray, rankArray, 0, WorkArray.length - 1);
		
		
		
//		int Length = rankArray.length;
//		int[] newRankArray = new int[Length];
//		
//		for(int i = 0; i < Length - 1; i++) {
//			if(WorkArray[Length - 1 - i] == WorkArray[Length - 2 - i]) {
//				for(int j = 0; j < Length; j++) {
//					if(rankArray[j] >= Length - 1 - i) {
//						rankArray[j] = rankArray[j] - 1;
//					}
//				}
//			}
//		}
		
//		if(LowestIsZero != true) {
//			//Needs to swap the ranks within the rankArray
//			for(int i = 0; i < Length; i++) {
//				newRankArray[i] = (int)(Length / 2) * 2 - rankArray[i];
//			}
//			rankArray = newRankArray;
//		}
		
		//in case we have any equals values, need to make ranks even
//		for(int i = 0; i < Length; i++) {
//			if(LowestIsZero != true) {
//				if(rankArray[Length - i] == rankArray[Length - i - 1]) {
//					rankArray[i + 1] = rankArray[i];
//				}
//			}
//			else {
//				
//			}
//		}
		
		return rankArray;
		
		
	}

	private static int partition(double arr[], int[] rankArray, int left, int right) {
	      int i = left, j = right;
	      double tmp;
	      int rankTmp;
	      double pivot = arr[(left + right) / 2];
	     
	      while (i <= j) {
	            while (arr[i] < pivot)
	                  i++;
	            while (arr[j] > pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  rankTmp = rankArray[i];
	                  rankArray[i] = rankArray[j];
	                  rankArray[j] = rankTmp;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	 
	private static void quickSort(double arr[], int[] rankArray, int left, int right) {
		int index = partition(arr, rankArray, left, right);
	    if (left < index - 1)
            quickSort(arr,rankArray, left, index - 1);
	    if (index < right)
            quickSort(arr, rankArray, index, right);
	}
}
