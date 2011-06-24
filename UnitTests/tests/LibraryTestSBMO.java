package tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

//import dea.DEAModelOrientation;
import dea.*;




public class LibraryTestSBMO {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetSBMCResults() {
		
		
		DEAPSolution SBMCSol = new DEAPSolution(20, 4);
		
		SBMCSol.setObjectives(createSBMOObjectives());		
		
		return SBMCSol;
	}


	private double[] createSBMOObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.474691622106158;
		Objectives[1] = 0.700369387101254;
		Objectives[2] = 0.199729334370413;
		Objectives[3] = 0.0753226899284763;
		Objectives[4] = 0.697242683806256;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.784849181107451;
		Objectives[8] = 0.479389421563248;
		Objectives[9] = 0.185960069769944;
		Objectives[10] = 0.00691638342862426;
		Objectives[11] = 1;
		Objectives[12] = 0.134166475259275;
		Objectives[13] = 0.0215334101757831;
		Objectives[14] = 0.40691982739752;
		Objectives[15] = 0.500882069903829;
		Objectives[16] = 1;
		Objectives[17] = 0.0950844869027731;
		Objectives[18] = 1;
		Objectives[19] = 0.115923957753396;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 11;
		ranks[1] = 7;
		ranks[2] = 13;
		ranks[3] = 18;
		ranks[4] = 8;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 6;
		ranks[8] = 10;
		ranks[9] = 14;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 15;
		ranks[13] = 19;
		ranks[14] = 12;
		ranks[15] = 9;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 16;		
		
		return ranks;
	}
	
	public void BuildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}
	
//	private ArrayList<Integer>[] getValidatedReferenceSet() {
//		@SuppressWarnings("unchecked")
//		ArrayList<Integer>[] ReferenceSet = (ArrayList<Integer>[])new ArrayList[5];
//		
//		ReferenceSet[0] = new ArrayList<Integer>();
//		ReferenceSet[0].add(5);
//		ReferenceSet[0].add(6);
//		
//		ReferenceSet[1] = new ArrayList<Integer>();
//		ReferenceSet[1].add(5);
//		ReferenceSet[1].add(18);
//		
//		ReferenceSet[2] = new ArrayList<Integer>();
//		ReferenceSet[2].add(5);
//		ReferenceSet[2].add(16);
//		ReferenceSet[2].add(18);
//		
//		ReferenceSet[3] = new ArrayList<Integer>();
//		ReferenceSet[3].add(16);
//		ReferenceSet[3].add(18);
//		
//		ReferenceSet[4] = new ArrayList<Integer>();
//		ReferenceSet[4].add(5);
//		ReferenceSet[4].add(6);
//		
//		
//		
//		ArrayList<Integer> Array0 = new ArrayList<Integer>();
//		Array0.add(5);
//		Array0.add(6);
//		ReferenceSet.add(Array0);
//		
//		ArrayList<Integer> Array1 = new ArrayList<Integer>();
//		Array1.add(5);
//		Array1.add(18);
//		ReferenceSet.add(Array1);
//		
//		ArrayList<Integer> Array2 = new ArrayList<Integer>();
//		Array2.add(5);
//		Array2.add(16);
//		Array2.add(18);
//		ReferenceSet.add(Array2);
//				
//		ArrayList<Integer> Array3 = new ArrayList<Integer>();
//		Array3.add(16);
//		Array3.add(18);
//		ReferenceSet.add(Array3);
//		
//		ArrayList<Integer> Array4 = new ArrayList<Integer>();
//		Array4.add(5);
//		Array4.add(6);
//		ReferenceSet.add(Array4);
//		
//		ArrayList<Integer> Array5 = new ArrayList<Integer>();
//		Array5.add(5);
//		ReferenceSet.add(Array5);
//		
//		ArrayList<Integer> Array6 = new ArrayList<Integer>();
//		Array6.add(6);
//		ReferenceSet.add(Array6);
//		
//		ArrayList<Integer> Array7 = new ArrayList<Integer>();
//		Array7.add(5);
//		Array7.add(18);
//		ReferenceSet.add(Array7);
//		
//		ArrayList<Integer> Array8 = new ArrayList<Integer>();
//		Array8.add(5);
//		Array8.add(6);
//		ReferenceSet.add(Array8);
//		
//		ArrayList<Integer> Array9 = new ArrayList<Integer>();
//		Array9.add(16);
//		ReferenceSet.add(Array9);
//		
//		ArrayList<Integer> Array10 = new ArrayList<Integer>();
//		Array10.add(5);
//		Array10.add(16);
//		Array10.add(18);
//		ReferenceSet.add(Array10);
//		
//		ArrayList<Integer> Array11 = new ArrayList<Integer>();
//		Array11.add(11);
//		ReferenceSet.add(Array11);
//		
//		ArrayList<Integer> Array12 = new ArrayList<Integer>();
//		Array12.add(5);
//		Array12.add(16);
//		Array12.add(18);
//		ReferenceSet.add(Array12);
//		
//		ArrayList<Integer> Array13 = new ArrayList<Integer>();
//		Array13.add(16);
//		Array13.add(18);
//		ReferenceSet.add(Array13);
//		
//		ArrayList<Integer> Array14 = new ArrayList<Integer>();
//		Array14.add(5);
//		Array14.add(18);
//		ReferenceSet.add(Array14);
//		
//		ArrayList<Integer> Array15 = new ArrayList<Integer>();
//		Array15.add(5);
//		Array15.add(18);
//		ReferenceSet.add(Array15);
//		
//		ArrayList<Integer> Array16 = new ArrayList<Integer>();
//		Array16.add(16);
//		ReferenceSet.add(Array16);
//		
//		ArrayList<Integer> Array17 = new ArrayList<Integer>();
//		Array17.add(5);
//		Array17.add(11);
//		Array17.add(18);
//		ReferenceSet.add(Array17);
//		
//		ArrayList<Integer> Array18 = new ArrayList<Integer>();
//		Array18.add(18);
//		ReferenceSet.add(Array18);
//		
//		ArrayList<Integer> Array19 = new ArrayList<Integer>();
//		Array19.add(16);
//		ReferenceSet.add(Array19);
//		
//		return ReferenceSet;
//	}
	
	@Test
	public void TestSBMO() {
		
		BuildDEAProblem(ModelType.SBM_O); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();

		
		DEAPSolution CheckedSol = GetSBMCResults();
		
		
		assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.00001);
		
		assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
		
//		int iter = 0;
//		for(ArrayList<Integer> testArray: getValidatedReferenceSet()) {
//			assertEquals(testArray, tester.getReferenceSet(iter));
//			iter++;
//		}
		
		assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	
}
