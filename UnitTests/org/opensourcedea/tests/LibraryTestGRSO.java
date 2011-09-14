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




public class LibraryTestGRSO {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetModelResults() {
		
		
		DEAPSolution DEAModelSol = new DEAPSolution(20, 4);
		
		DEAModelSol.setObjectives(createDEAModelObjectives());		
		
		return DEAModelSol;
	}


	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.519434453047034;
		Objectives[1] = 0.918212606063258;
		Objectives[2] = 0.845181758758218;
		Objectives[3] = 0.341382546208781;
		Objectives[4] = 0.783616224357704;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.84387830661803;
		Objectives[8] = 0.539682742929534;
		Objectives[9] = 0.660194603726043;
		Objectives[10] = 0.464481615490394;
		Objectives[11] = 1;
		Objectives[12] = 0.621175185816311;
		Objectives[13] = 0.342539976784181;
		Objectives[14] = 0.920795679367037;
		Objectives[15] = 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 0.930382832324589;
		Objectives[18] = 1;
		Objectives[19] = 0.477119680444747;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 8;
		ranks[2] = 9;
		ranks[3] = 20;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 15;
		ranks[9] = 12;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 19;
		ranks[14] = 7;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 6;
		ranks[18] = 1;
		ranks[19] = 17;
		
		return ranks;
	}
	
	public void BuildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		
		tester.setModelType(ModelType);

		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
		try {
			tester.setRTSLowerBound(0.8);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
		try {
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void TestGRSO() {
		
		BuildDEAProblem(ModelType.GRS_O); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();

		
		DEAPSolution CheckedSol = GetModelResults();
		
		
		assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
		
		assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
		
//		assertEquals(getTestReferenceSet(),tester.getReferenceSet());
		
		assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	
}
