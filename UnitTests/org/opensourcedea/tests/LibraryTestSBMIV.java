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




public class LibraryTestSBMIV {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution getSBMCResults() {
		
		
		DEAPSolution SBMCSol = new DEAPSolution(20, 4);
		
		SBMCSol.setObjectives(createSBMIObjectives());		
		
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
	

	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}

	
	@Test
	public void testSBMIV() {
		
		buildDEAProblem(ModelType.SBM_I_V); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();

			
			DEAPSolution CheckedSol = getSBMCResults();
			
			
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.00001);
			
	//		assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
			//assertEquals(getTestReferenceSet(),tester.getReferenceSet());
			
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	
}
