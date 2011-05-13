package tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

//import dea.DEAModelOrientation;
import dea.*;




public class LibraryTestSBMIV {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetSBMCResults() {
		
		
		DEAPSolution SBMCSol = new DEAPSolution(20, 4);
		
		SBMCSol.Objectives  = createSBMIObjectives();		
		
		return SBMCSol;
	}


	private double[] createSBMIObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.538022090458506;
		Objectives[1] = 0.685898941154542;
		Objectives[2] = 1;
		Objectives[3] = 0.446963678331783;
		Objectives[4] = 0.721210167858574;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.726535481352475;
		Objectives[8] = 0.60006666659683;
		Objectives[9] = 0.696433222387383;
		Objectives[10] = 0.513272350686686;
		Objectives[11] = 1;
		Objectives[12] = 0.400617403076217;
		Objectives[13] = 0.473247437790967;
		Objectives[14] = 0.887025518881808;
		Objectives[15] = 0.727925309558822;
		Objectives[16] = 1;
		Objectives[17] = 0.883141327984349;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 14;
		ranks[2] = 1;
		ranks[3] = 19;
		ranks[4] = 12;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 11;
		ranks[8] = 15;
		ranks[9] = 13;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 18;
		ranks[14] = 8;
		ranks[15] = 10;
		ranks[16] = 1;
		ranks[17] = 9;
		ranks[18] = 1;
		ranks[19] = 1;		
		
		return ranks;
	}
	
	public void BuildDEAProblem(DEAModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableTypes(TestData.createTestDEAVariableTypes());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}
	
//	private ArrayList<ArrayList<Integer>> getTestReferenceSet() {
//		ArrayList<ArrayList<Integer>> ReferenceSet = new ArrayList<ArrayList<Integer>>();
//		
//		ArrayList<Integer> Array0 = new ArrayList<Integer>();
//		Array0.add(6);
//		ReferenceSet.add(Array0);
//		
//		ArrayList<Integer> Array1 = new ArrayList<Integer>();
//		Array1.add(5);
//		ReferenceSet.add(Array1);
//		
//		ArrayList<Integer> Array2 = new ArrayList<Integer>();
//		Array2.add(6);
//		Array2.add(16);
//		ReferenceSet.add(Array2);
//				
//		ArrayList<Integer> Array3 = new ArrayList<Integer>();
//		Array3.add(16);
//		ReferenceSet.add(Array3);
//		
//		ArrayList<Integer> Array4 = new ArrayList<Integer>();
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
//		Array7.add(6);
//		ReferenceSet.add(Array7);
//		
//		ArrayList<Integer> Array8 = new ArrayList<Integer>();
//		Array8.add(6);
//		ReferenceSet.add(Array8);
//		
//		ArrayList<Integer> Array9 = new ArrayList<Integer>();
//		Array9.add(16);
//		ReferenceSet.add(Array9);
//		
//		ArrayList<Integer> Array10 = new ArrayList<Integer>();
//		Array10.add(6);
//		Array10.add(16);
//		ReferenceSet.add(Array10);
//		
//		ArrayList<Integer> Array11 = new ArrayList<Integer>();
//		Array11.add(11);
//		ReferenceSet.add(Array11);
//		
//		ArrayList<Integer> Array12 = new ArrayList<Integer>();
//		Array12.add(5);
//		Array12.add(16);
//		ReferenceSet.add(Array12);
//		
//		ArrayList<Integer> Array13 = new ArrayList<Integer>();
//		Array13.add(16);
//		ReferenceSet.add(Array13);
//		
//		ArrayList<Integer> Array14 = new ArrayList<Integer>();
//		Array14.add(5);
//		Array14.add(18);
//		ReferenceSet.add(Array14);
//		
//		ArrayList<Integer> Array15 = new ArrayList<Integer>();
//		Array15.add(6);
//		ReferenceSet.add(Array15);
//		
//		ArrayList<Integer> Array16 = new ArrayList<Integer>();
//		Array16.add(16);
//		ReferenceSet.add(Array16);
//		
//		ArrayList<Integer> Array17 = new ArrayList<Integer>();
//		Array17.add(5);
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
	public void TestSBMIV() {
		
		BuildDEAProblem(DEAModelType.SBMIV); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (DEAException e) {
			System.out.println(e.toString());
		}
		
		DEAPSolution CheckedSol = GetSBMCResults();
		
		
		assertArrayEquals(tester.getObjectives(), CheckedSol.Objectives,0.00001);
		
//		assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
		
		//assertEquals(getTestReferenceSet(),tester.getReferenceSet());
		
		assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OptimalSolutionFound);
		
	}
	
	
	
}
