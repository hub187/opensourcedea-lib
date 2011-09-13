package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.DEAPSolution;
import org.opensourcedea.dea.DEAProblem;
import org.opensourcedea.dea.ModelType;
import org.opensourcedea.dea.RankingType;
import org.opensourcedea.dea.SolverReturnStatus;

//import dea.DEAModelOrientation;




public class LibraryTestBCCI {
	
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
		
		
		Objectives[0] = 0.567311991306061;
		Objectives[1] = 0.920072185311599;
		Objectives[2] = 1;
		Objectives[3] = 0.453078828334519;
		Objectives[4] = 0.784593172313617;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.844109331376493;
		Objectives[8] = 0.601269603604185;
		Objectives[9] = 0.86914258558318;
		Objectives[10] = 0.518224791143689;
		Objectives[11] = 1;
		Objectives[12] = 0.589846230572554;
		Objectives[13] = 0.47698681816151;
		Objectives[14] = 0.944298220111978;
		Objectives[15] = 0.730166645993769;
		Objectives[16] = 1;
		Objectives[17] = 0.986772936679716;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 17;
		ranks[1] = 10;
		ranks[2] = 1;
		ranks[3] = 20;
		ranks[4] = 13;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 12;
		ranks[8] = 15;
		ranks[9] = 11;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 9;
		ranks[15] = 14;
		ranks[16] = 1;
		ranks[17] = 8;
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
	
	
	@Test
	public void TestBCCI() {
				
		
		
		BuildDEAProblem(ModelType.BCC_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			DEAPSolution CheckedSol = GetModelResults();
			
			
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	
	
}
