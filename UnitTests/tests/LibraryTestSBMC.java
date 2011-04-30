package tests;


import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dea.DEAModelOrientation;
import dea.DEAModelType;
import dea.DEAProblem;
import dea.DEAPSolution;
import dea.RankingType;



public class LibraryTestSBMC {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetSBMCResults() {
		
		
		DEAPSolution SBMCSol = new DEAPSolution(20, 4);
		
		SBMCSol.Objectives  = createSBMCObjectives();		
		
		return SBMCSol;
	}


	private double[] createSBMCObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.433416455031194;
		Objectives[1] = 0.655740942924697;
		Objectives[2] = 0.191774112152313;
		Objectives[3] = 0.0664286665377734;
		Objectives[4] = 0.631545023036202;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.717802718554146;
		Objectives[8] = 0.478929894370442;
		Objectives[9] = 0.143243925812364;
		Objectives[10] = 0.00623104440127262;
		Objectives[11] = 1;
		Objectives[12] = 0.13400714727165;
		Objectives[13] = 0.0189248341986655;
		Objectives[14] = 0.404882233834635;
		Objectives[15] = 0.451730076717645;
		Objectives[16] = 1;
		Objectives[17] = 0.0866211335899279;
		Objectives[18] = 1;
		Objectives[19] = 0.0896594379050175;
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
		ranks[8] = 9;
		ranks[9] = 14;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 15;
		ranks[13] = 19;
		ranks[14] = 12;
		ranks[15] = 10;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 16;		
		
		return ranks;
	}
	
	public void BuildDEAProblem(DEAModelType ModelType, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableTypes(TestData.createTestDEAVariableTypes());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}
	
	@Test
	public void TestSBMC() {
		
		BuildDEAProblem(DEAModelType.SBMC, DEAModelOrientation.OutputOriented);
		
		
		tester.solve();
		
		DEAPSolution CheckedSol = GetSBMCResults();
		
		//Test Objectives
		assertArrayEquals(tester.getObjectives(), CheckedSol.Objectives,0.0001);
		
		assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
		
//		
//		//int[] ranks = tester.getDMURanks(true);
//		
		tester.getReferenceSet();
		

		
		
		
		
	}

	
	
	
	
}
