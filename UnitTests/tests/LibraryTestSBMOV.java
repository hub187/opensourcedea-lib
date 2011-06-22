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




public class LibraryTestSBMOV {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetDEAModelResults() {
		
		
		DEAPSolution DEAModelSol = new DEAPSolution(20, 4);
		
		DEAModelSol.setObjectives(createSBMOObjectives());		
		
		return DEAModelSol;
	}


	private double[] createSBMOObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.474879271928326;
		Objectives[1] = 0.87861716340644;
		Objectives[2] = 1;
		Objectives[3] = 0.0868841360674911;
		Objectives[4] = 0.697298191554183;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.861259537998267;
		Objectives[8] = 0.488504526738576;
		Objectives[9] = 0.195229627696065;
		Objectives[10] = 0.0082326586135507;
		Objectives[11] = 1;
		Objectives[12] = 0.235266808629956;
		Objectives[13] = 0.0257755689617094;
		Objectives[14] = 0.427059536559875;
		Objectives[15] = 0.509184998979921;
		Objectives[16] = 1;
		Objectives[17] = 0.0969837757380851;
		Objectives[18] = 1;
		Objectives[19] = 1;// Solver inaccuracy? 0.999808634775049;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 8;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 10;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 9;
		ranks[8] = 12;
		ranks[9] = 16;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 15;
		ranks[13] = 19;
		ranks[14] = 14;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 1;		
		
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
	
//	private ArrayList<ArrayList<Integer>> getTestReferenceSet() {
//		ArrayList<ArrayList<Integer>> ReferenceSet = new ArrayList<ArrayList<Integer>>();
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
//	
	@Test
	public void TestSBMO() {
		
		BuildDEAProblem(ModelType.SBM_O_V); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
	
			
			DEAPSolution CheckedSol = GetDEAModelResults();
			
			
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.00001);
			
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
	//		assertEquals(getTestReferenceSet(),tester.getReferenceSet());
			
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	
}
