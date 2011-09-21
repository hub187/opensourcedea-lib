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




public class LibraryTestGRSI {
	
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
		
		
		Objectives[0] = 0.545153233382534;
		Objectives[1] = 0.916420765303269;
		Objectives[2] = 0.576955373697832;
		Objectives[3] = 0.395110938239753;
		Objectives[4] = 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.840457911368163;
		Objectives[8] = 0.56963424991349;
		Objectives[9] = 0.780578643995372;
		Objectives[10] = 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 0.586868373478382;
		Objectives[13] = 0.414890646282276;
		Objectives[14] = 0.89951220302041;
		Objectives[15] = 0.681491797903215;
		Objectives[16] = 1;
		Objectives[17] = 0.889932140757229;
		Objectives[18] = 1;
		Objectives[19] = 0.830866336505974;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 17;
		ranks[1] = 6;
		ranks[2] = 15;
		ranks[3] = 20;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 9;
		ranks[8] = 16;
		ranks[9] = 12;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 19;
		ranks[14] = 7;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 8;
		ranks[18] = 1;
		ranks[19] = 10;
		
		return ranks;
	}
	
	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		
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
	public void testGRSI() {
		
		buildDEAProblem(ModelType.GRS_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();

		
		DEAPSolution CheckedSol = getModelResults();
		
		
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
