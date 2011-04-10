package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import utils.Rank;

public class RankTest {

	@Test
	public void testGetRankTrue() {
		
		double[] ArrayToSort = createArrayToSort();
		int[] rankArray;
		int[] resultRankArray = createRankResultArray();

		rankArray = Rank.GetRanks(ArrayToSort, true);
		
		assertArrayEquals(rankArray, resultRankArray);
		
	}

	@Test
	public void testGetRankFalse() {
		
		double[] ArrayToSort = createArrayToSort();
		int[] rankArray;
		int[] resultRankArrayInv = createRankArrayInv();
		
		rankArray = Rank.GetRanks(ArrayToSort, false);
		
		assertArrayEquals(rankArray, resultRankArrayInv);
	}

	
	private double[] createArrayToSort() {
		double[] ArrayToSort = new double[5];
		ArrayToSort[0] = 6.5;
		ArrayToSort[1] = 1.3;
		ArrayToSort[2] = 4.2;
		ArrayToSort[3] = 0.24;
		ArrayToSort[4] = 9.58;
		return ArrayToSort;
	}
	


	private int[] createRankResultArray() {
		int[] resultRankArray = new int[5];
		resultRankArray[0] = 3;
		resultRankArray[1] = 1;
		resultRankArray[2] = 2;
		resultRankArray[3] = 0;
		resultRankArray[4] = 4;
		return resultRankArray;
	}
	
	private int[] createRankArrayInv() {
		int[] resultRankArrayInv = new int[5];
		resultRankArrayInv[0] = 4;
		resultRankArrayInv[1] = 0;
		resultRankArrayInv[2] = 2;
		resultRankArrayInv[3] = 1;
		resultRankArrayInv[4] = 3;
		return resultRankArrayInv;
	}

	
}
