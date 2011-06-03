
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

package dea;

/**
 * 
 * 
 * This class only deals with implementing the bubblesort algorithm to find the DMU ranks.
 * <p>
 * Most of the code is based on the code developed by algolist which can be found below.
 * </p>
 * <p>
 * @see <a href="http://www.algolist.net/Algorithms/Sorting/Bubble_sort">Algolist Bubble sort Algorithm Implemetation</a>
 * </p>
 * @author Hubert Virtos
 *
 */
public class Rank {

	/**
	 * Returns the ranks.
	 * @param arrayToSort The Array To sort (DMU Objectives)
	 * @param highestIsOne A boolean. If true, specifies that the highest values of the ArrayToSort
	 * should be '1' (i.e. first).
	 * @param precision An int value to specify at which decimal place the scores need to be rounded up prior to been ranked.
	 * This is important as most of the scores of the 'efficient' DMUs are not 1 but some value very close to 1
	 * (e.g. 1.00000000000002).
	 * All precisions between 0 and 16 are taken into account, any other int value would leave the scores unchanged.
	 * @return An int[] with the rank positions.
	 */
	public static int[] getRanks(double[] arrayToSort, boolean highestIsOne, RankingType typeOfRanking, int precision) {
		
		//Copy original Array to avoid messing with external data
		int arrLength = arrayToSort.length - 1;
		int tempRank = 1;
		int incTempRank;
		double[] workArray = new double[arrLength + 1];
		int[] rankArray = new int[arrLength + 1];
		int[] posArray = new int[arrLength + 1];
		int[] newRankArray = new int[arrLength + 1];
		
	
		System.arraycopy(arrayToSort, 0, workArray, 0, arrayToSort.length);
		
		if(precision >= 0 && precision <=16) {
			for(int i = 0; i <=arrLength; i++) {
				workArray[i] = roundToDecimals(workArray[i],precision);
			}
		}
		
		//Create rank Array (function 'range' in Python)		
		for(int i = 0; i < posArray.length; i++) {
			posArray[i] = i;
		}
		
		
		//Get the positions of each value
		quickSort(workArray, posArray, 0, workArray.length - 1);
		
		
		//Create the rank array (where the highest values have a rank of 1 (first)).
		switch (typeOfRanking) {
			case DENSE: 
			rankArray[posArray[arrLength ]] = tempRank;
				for(int i = 0; i < arrLength - 1 ; i++) {
					if(workArray[arrLength - i] == workArray[arrLength - 1 - i]) {
						rankArray[posArray[arrLength - 1 - i]] = tempRank;
					}
					else {
						tempRank++;
						rankArray[posArray[arrLength - 1 - i]] = tempRank;
					}
				}
				tempRank++;
				rankArray[posArray[0]] = tempRank;
			break;
			case STANDARD: 
				rankArray[posArray[arrLength ]] = tempRank;
				incTempRank = tempRank;
				for(int i = 0; i < arrLength - 1 ; i++) {
					if(workArray[arrLength - i] == workArray[arrLength - 1 - i]) {
						rankArray[posArray[arrLength - 1 - i]] = tempRank;
						incTempRank++;
					}
					else {
						incTempRank++;
						tempRank = incTempRank;
						rankArray[posArray[arrLength - 1 - i]] = tempRank;
					}
				}
				tempRank++;
				rankArray[posArray[0]] = tempRank;
			break;
		}
		

		//Check whether the ranks need to be reverted (i.e. Highest is no longer one but Array.Length - 1).
		if(highestIsOne != true) {
			//Find Maximum
		    int maximum = rankArray[0];   // start with the first value
		    for (int i=1; i<rankArray.length; i++) {
		        if (rankArray[i] > maximum) {
		            maximum = rankArray[i];   // new maximum
		        }
		    }
			
		    //Revert rankArray
			for(int i = 0; i <= arrLength; i++) {
				newRankArray[i] = maximum + 1 - rankArray[i];
			}
			rankArray = newRankArray;
		}
		
		
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
	
	
	public static double roundToDecimals(double d, int c) {
//		int temp=(int)((d*Math.pow(10,c)));
//		return (((double)temp)/Math.pow(10,c));
		
		long pow = (long)Math.pow(10, c + 1);
		double result = d * pow;
		result = Math.round(result);
		result = result / pow;
		return result;

	}
	
	
	/* An equivalent sort method using the bubble sort algorithm. For information purposes only as this is often less
	 * efficient than the quick sort algorithm.*/
	
//	private static void bubble sort(double[] ArrayToSort, int[] rankArray) {
//		
//		double[] WorkArray = new double[rankArray.length];
//		System.arraycopy(ArrayToSort, 0, WorkArray, 0, ArrayToSort.length);
//		boolean swapped = true;
//		int j = 0;
//		double tmp;
//		int rankTmp;
//		while (swapped) {
//		      swapped = false;
//		      j++;
//		      for (int i = 0; i < WorkArray.length - j; i++) {                                       
//		            if (WorkArray[i] > WorkArray[i + 1]) {                          
//		                  tmp = WorkArray[i];
//		                  WorkArray[i] = WorkArray[i + 1];
//		                  WorkArray[i + 1] = tmp;
//		                  
//		                  rankTmp = rankArray[i];
//		                  rankArray[i] = rankArray[i + 1];
//		                  rankArray[i + 1] = rankTmp;
//		                  swapped = true;
//		            }
//		      }                
//		}
//	}

	
}
