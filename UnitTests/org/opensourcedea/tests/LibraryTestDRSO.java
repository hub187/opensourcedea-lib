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




public class LibraryTestDRSO {
	
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
		
		
		Objectives[0] = 0.540126501539801;
		Objectives[1] = 0.93354925149474;
		Objectives[2] = 1;
		Objectives[3] = 0.375433203387031;
		Objectives[4] = 0.803996071700928;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.865788985503832;
		Objectives[8] = 0.562736097594507;
		Objectives[9] = 0.567189831743521;
		Objectives[10] = 0.469111868112587;
		Objectives[11] = 1;
		Objectives[12] = 0.737946888943664;
		Objectives[13] = 0.367928609122649;
		Objectives[14] = 0.954489410345333;
		Objectives[15] = 0.658038271209613;
		Objectives[16] = 1;
		Objectives[17] = 0.991088109520716;
		Objectives[18] = 1;
		Objectives[19] = 0.207079840963623;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 9;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 15;
		ranks[9] = 14;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 12;
		ranks[13] = 19;
		ranks[14] = 8;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 7;
		ranks[18] = 1;
		ranks[19] = 20;
		
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
	public void testDRSO() {
		
		buildDEAProblem(ModelType.DRS_O); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();

		
		DEAPSolution CheckedSol = getModelResults();
		
		
		assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
		
		assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 8), createSolRanks());
		
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
