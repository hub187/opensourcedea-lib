package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

//import dea.DEAModelOrientation;




public class LibraryTestBCCO {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution getModelResults() {
		
		
		DEAPSolution DEAModelSol = new DEAPSolution(20, 4);
		
		DEAModelSol.setObjectives(createDEAModelObjectives());		
		
		return DEAModelSol;
	}


	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.540126501537219;
		Objectives[1] = 0.933549251485867;
		Objectives[2] = 1;
		Objectives[3] = 0.375433203385158;
		Objectives[4] = 0.803996071697327;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.865788985495174;
		Objectives[8] = 0.562736097590251;
		Objectives[9] = 0.765136031217916;
		Objectives[10] = 0.469111868112257;
		Objectives[11] = 1;
		Objectives[12] = 0.737946888936728;
		Objectives[13] = 0.367928609120761;
		Objectives[14] = 0.954489410343586;
		Objectives[15] = 0.658038271209364;
		Objectives[16] = 1;
		Objectives[17] = 0.991088109517482;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 17;
		ranks[1] = 10;
		ranks[2] = 1;
		ranks[3] = 19;
		ranks[4] = 12;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 11;
		ranks[8] = 16;
		ranks[9] = 13;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 20;
		ranks[14] = 9;
		ranks[15] = 15;
		ranks[16] = 1;
		ranks[17] = 8;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	
	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		
		tester.setModelType(ModelType);

		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}
	
	
	@Test
	public void testBCCO() {
		
		buildDEAProblem(ModelType.BCC_O); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		DEAPSolution CheckedSol = getModelResults();
		
		try {
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 8), createSolRanks());
			
//			assertEquals(getTestReferenceSet(),tester.getReferenceSet());
			
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	
}
