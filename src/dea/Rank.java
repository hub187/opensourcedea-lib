package dea;

public class Rank {
	
	public static int[] GetRanks(double[] ArrayToSort, boolean LowestIsZero) {
		
		//Copy original Array to avoid messing with external data
		double[] WorkArray = ArrayToSort;
		//Create rank Array (function 'range' in Python)
		int[] rankArray = new int[ArrayToSort.length];
		for(int i = 0; i < rankArray.length; i++) {
			rankArray[i] = i;
		}
		
		quickSort(WorkArray, rankArray, 0, WorkArray.length - 1);
				
		int Length = rankArray.length - 1;
		
		if(LowestIsZero != true) {
			int temp;
			for(int i = 0; i <= Length / 2; i++) {
				temp = rankArray[i];
				rankArray[i] = rankArray[Length - i];
				rankArray[Length - i] = temp;				
			}
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
}
