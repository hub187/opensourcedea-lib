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
		
//		double[] ArrayToSort = new double[20];		
//		ArrayToSort[0] = 0.519434453047034;
//		ArrayToSort[1] = 0.872474445374321;
//		ArrayToSort[2] = 0.549048143682244;
//		ArrayToSort[3] = 0.322936931532726;
//		ArrayToSort[4] = 0.782832225762655;
//		ArrayToSort[5] = 1;
//		ArrayToSort[6] = 1;
//		ArrayToSort[7] = 0.830850567998129;
//		ArrayToSort[8] = 0.539682742929534;
//		ArrayToSort[9] = 0.567189831743522;
//		ArrayToSort[10] = 0.459594713883521;
//		ArrayToSort[11] = 1;
//		ArrayToSort[12] = 0.564403802355244;
//		ArrayToSort[13] = 0.338735130146885;
//		ArrayToSort[14] = 0.805598209487005;
//		ArrayToSort[15] = 0.65721528448653;
//		ArrayToSort[16] = 1;
//		ArrayToSort[17] = 0.739084603920947;
//		ArrayToSort[18] = 1;
//		ArrayToSort[19] = 0.207079840963623;
		            
		            
		            
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
//		resultRankArrayInv[0] = 4;
//		resultRankArrayInv[1] = 0;
//		resultRankArrayInv[2] = 2;
//		resultRankArrayInv[3] = 1;
//		resultRankArrayInv[4] = 3;

		resultRankArrayInv[0] = 1;
		resultRankArrayInv[1] = 3;
		resultRankArrayInv[2] = 2;
		resultRankArrayInv[3] = 4;
		resultRankArrayInv[4] = 0;
		
		return resultRankArrayInv;
	}

	
}
