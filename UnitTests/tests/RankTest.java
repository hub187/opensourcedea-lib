package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import utils.*;

public class RankTest {
	
	@Test
	/**
	 * A simple test unit to test the getRank method. Only checks the DENSE ranking. Useful for quick hacking.
	 * Use the 'Complex' method to fully test the getRank method.
	 */
	public void testGetRankSimpleDense() {
		
		double[] ArrayToSort = createArrayToSortSimple();
		int[] resultRankArray = createRankResultArraySimple();
		int[] resultRankArrayInv = createRankArrayInvSimple();

		
		assertArrayEquals(Rank.getRanks(ArrayToSort, true, RankingType.DENSE), resultRankArray);
		assertArrayEquals(Rank.getRanks(ArrayToSort, false, RankingType.DENSE), resultRankArrayInv);
	}

	@Test
	/**
	 * Unit tests checking the getRank method. Tests both types of ranking (STANDARD and DENSE) with both
	 * direction (Highest Is One [HIO] and Highest Is Not One [HINO]).
	 */
	public void testGetRankComplex() {
		double[] ArrayToSort = createArrayToSortComplex();
		int[] resultRankArrayStandard = createRankResultArrayComplexHIOStandard();
		int[] resultRankArrayDense = createRankResultArrayComplexHIODense();
		int[] resultRankArrayStandardHINO = createRankResultArrayComplexHINOStandard();
		int[] resultRankArrayDenseHINO = createRankResultArrayComplexHINODense();
		assertArrayEquals(Rank.getRanks(ArrayToSort, true, RankingType.STANDARD), resultRankArrayStandard);
		assertArrayEquals(Rank.getRanks(ArrayToSort, true, RankingType.DENSE), resultRankArrayDense);
		assertArrayEquals(Rank.getRanks(ArrayToSort, false, RankingType.STANDARD), resultRankArrayStandardHINO);
		assertArrayEquals(Rank.getRanks(ArrayToSort, false, RankingType.DENSE), resultRankArrayDenseHINO);
		
	}
	

	
	private double[] createArrayToSortSimple() {
		double[] ArrayToSort = new double[5];
		ArrayToSort[0] = 9.58;
		ArrayToSort[1] = 1.3;
		ArrayToSort[2] = 4.2;
		ArrayToSort[3] = 0.24;
		ArrayToSort[4] = 9.58;
		
		return ArrayToSort;
	}
	


	private int[] createRankResultArraySimple() {
		int[] resultRankArray = new int[5];

		resultRankArray[0] = 1;
		resultRankArray[1] = 3;
		resultRankArray[2] = 2;
		resultRankArray[3] = 4;
		resultRankArray[4] = 1;
		return resultRankArray;
	}
	
	private int[] createRankArrayInvSimple() {
		int[] resultRankArrayInv = new int[5];

		resultRankArrayInv[0] = 4;
		resultRankArrayInv[1] = 2;
		resultRankArrayInv[2] = 3;
		resultRankArrayInv[3] = 1;
		resultRankArrayInv[4] = 4;
		
		return resultRankArrayInv;
	}

	
	
	private double[] createArrayToSortComplex() {
		
		double[] ArrayToSort = new double[20];		
		ArrayToSort[0] = 0.519434453047034;
		ArrayToSort[1] = 0.872474445374321;
		ArrayToSort[2] = 0.549048143682244;
		ArrayToSort[3] = 0.322936931532726;
		ArrayToSort[4] = 0.782832225762655;
		ArrayToSort[5] = 1;
		ArrayToSort[6] = 1;
		ArrayToSort[7] = 0.830850567998129;
		ArrayToSort[8] = 0.539682742929534;
		ArrayToSort[9] = 0.567189831743522;
		ArrayToSort[10] = 0.459594713883521;
		ArrayToSort[11] = 1;
		ArrayToSort[12] = 0.564403802355244;
		ArrayToSort[13] = 0.338735130146885;
		ArrayToSort[14] = 0.805598209487005;
		ArrayToSort[15] = 0.65721528448653;
		ArrayToSort[16] = 1;
		ArrayToSort[17] = 0.739084603920947;
		ArrayToSort[18] = 1;
		ArrayToSort[19] = 0.207079840963623;

		return ArrayToSort;
	}
	
	private int[] createRankResultArrayComplexHIOStandard() {
		int[] resultRankArray = new int[20];

		resultRankArray[0] = 16;
		resultRankArray[1] = 6;
		resultRankArray[2] = 14;
		resultRankArray[3] = 19;
		resultRankArray[4] = 9;
		resultRankArray[5] = 1;
		resultRankArray[6] = 1;
		resultRankArray[7] = 7;
		resultRankArray[8] = 15;
		resultRankArray[9] = 12;
		resultRankArray[10] = 17;
		resultRankArray[11] = 1;
		resultRankArray[12] = 13;
		resultRankArray[13] = 18;
		resultRankArray[14] = 8;
		resultRankArray[15] = 11;
		resultRankArray[16] = 1;
		resultRankArray[17] = 10;
		resultRankArray[18] = 1;
		resultRankArray[19] = 20;
		
		return resultRankArray;
	}
	
	private int[] createRankResultArrayComplexHINOStandard() {
		int[] resultRankArray = new int[20];

		resultRankArray[0] = 5;
		resultRankArray[1] = 15;
		resultRankArray[2] = 7;
		resultRankArray[3] = 2;
		resultRankArray[4] = 12;
		resultRankArray[5] = 20;
		resultRankArray[6] = 20;
		resultRankArray[7] = 14;
		resultRankArray[8] = 6;
		resultRankArray[9] = 9;
		resultRankArray[10] = 4;
		resultRankArray[11] = 20;
		resultRankArray[12] = 8;
		resultRankArray[13] = 3;
		resultRankArray[14] = 13;
		resultRankArray[15] = 10;
		resultRankArray[16] = 20;
		resultRankArray[17] = 11;
		resultRankArray[18] = 20;
		resultRankArray[19] = 1;
		
		return resultRankArray;
	}
	
	private int[] createRankResultArrayComplexHIODense() {
		int[] resultRankArray = new int[20];

		resultRankArray[0] = 12;
		resultRankArray[1] = 2;
		resultRankArray[2] = 10;
		resultRankArray[3] = 15;
		resultRankArray[4] = 5;
		resultRankArray[5] = 1;
		resultRankArray[6] = 1;
		resultRankArray[7] = 3;
		resultRankArray[8] = 11;
		resultRankArray[9] = 8;
		resultRankArray[10] = 13;
		resultRankArray[11] = 1;
		resultRankArray[12] = 9;
		resultRankArray[13] = 14;
		resultRankArray[14] = 4;
		resultRankArray[15] = 7;
		resultRankArray[16] = 1;
		resultRankArray[17] = 6;
		resultRankArray[18] = 1;
		resultRankArray[19] = 16;
		
		return resultRankArray;
	}
	
	private int[] createRankResultArrayComplexHINODense() {
		int[] resultRankArray = new int[20];

		resultRankArray[0] = 5;
		resultRankArray[1] = 15;
		resultRankArray[2] = 7;
		resultRankArray[3] = 2;
		resultRankArray[4] = 12;
		resultRankArray[5] = 16;
		resultRankArray[6] = 16;
		resultRankArray[7] = 14;
		resultRankArray[8] = 6;
		resultRankArray[9] = 9;
		resultRankArray[10] = 4;
		resultRankArray[11] = 16;
		resultRankArray[12] = 8;
		resultRankArray[13] = 3;
		resultRankArray[14] = 13;
		resultRankArray[15] = 10;
		resultRankArray[16] = 16;
		resultRankArray[17] = 11;
		resultRankArray[18] = 16;
		resultRankArray[19] = 1;
		
		return resultRankArray;
	}
	
}
