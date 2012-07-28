package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

//import dea.DEAModelOrientation;




public class LibraryTestSBMOTest {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public void buildDEAProblem(ModelType ModelType) { 
		
		tester.setModelType(ModelType);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}


	private double[] createDEAModelObjectives() {
		
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
	
	private boolean[] createEfficiencyValues() {

		boolean[] efficiencies = new boolean[20];


		efficiencies[0] = false;
		efficiencies[1] = false;
		efficiencies[2] = false;
		efficiencies[3] = false;
		efficiencies[4] = false;
		efficiencies[5] = true;
		efficiencies[6] = true;
		efficiencies[7] = false;
		efficiencies[8] = false;
		efficiencies[9] = false;
		efficiencies[10] = false;
		efficiencies[11] = true;
		efficiencies[12] = false;
		efficiencies[13] = false;
		efficiencies[14] = false;
		efficiencies[15] = false;
		efficiencies[16] = true;
		efficiencies[17] = false;
		efficiencies[18] = true;
		efficiencies[19] = false;
		return efficiencies;
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
	



	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5996449704142015));
		refSet.add(new NonZeroLambda(6, 0.3909349112426031));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.9563222737219073));
		refSet.add(new NonZeroLambda(18, 2.312625640540849));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.9999999999999999));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.3508623528528267));
		refSet.add(new NonZeroLambda(11, 0.1508772685878267));
		refSet.add(new NonZeroLambda(18, 1.0637715675502284));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 8073.813528453641;
		SlacksArray[8] [2] = 1399.1469568382304;
		SlacksArray[9] [1] = 39.05002016942316;
		SlacksArray[10] [3] = 3233.54397677341;
		SlacksArray[12] [3] = 6120.429959739905;
		SlacksArray[13] [3] = 3496.792300773234;
		SlacksArray[14] [2] = 1468.1554412626149;
		SlacksArray[17] [2] = 1796.2315356204128;
		SlacksArray[19] [1] = 19.93787817668415;
		
		return SlacksArray;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3459.794368047951;
		projectionValues[0] [3] = 3188.5332061548597;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 1592.5725286412799;
		projectionValues[3] [3] = 3840.904817793142;
		
		projectionValues[10] [0] = 384.149;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 3244.8039767734103;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015357409928576124;
		weightValues[0] [1] = 0.01777325534200409;
		weightValues[0] [2] = 2.663569822817383E-4;
		weightValues[0] [3] = 3.716726010390624E-4;
		
		weightValues[3] [0] = 0.00940007387736287;
		weightValues[3] [1] = 0.11906970277424861;
		weightValues[3] [2] = 3.997729289765625E-4;
		weightValues[3] [3] = 0.003290772673421876;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMO() {
				
		
		
		buildDEAProblem(ModelType.SBM_O);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		checkData();
		
		
		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.SBM_O);
		
		try {
			for(int i = 0; i < tester.getNumberOfDMUs(); i++) {
				tester.solveOne(i);
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		checkData();
		
		
		
	}
	
	private void checkData() {
		try {
			
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
			//EFFICIENCIES
			for(int i = 0 ; i < 20; i++) {
				assertTrue(tester.getEfficiencyStatus(i) == createEfficiencyValues()[i]);
			}
			
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 7), createSolRanks());
			
			//REFERENCE SET
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(0);
			l.add(1);
			l.add(11);
			l.add(17);
			for(Integer i : l){
				ArrayList<NonZeroLambda> refSet = getTestReferenceSet()[i];
				for(int nzlIndex = 0; nzlIndex < refSet.size();nzlIndex++) {
					assertEquals(refSet.get(nzlIndex).getDMUIndex(), tester.getReferenceSet(i).get(nzlIndex).getDMUIndex());
					assertEquals(refSet.get(nzlIndex).getLambdaValue(),
							tester.getReferenceSet(i).get(nzlIndex).getLambdaValue(), 0.0001);
				}
			}
			
			//SLACKS
			l.clear();
			l.add(3);
			l.add(9);
			l.add(12);
			l.add(17);
			for(Integer i : l){
				double[] slackValues = getTestSlackValues()[i];
				for(int sIndex = 0; sIndex < slackValues.length; sIndex++) {
					if(slackValues[sIndex] != 0) {
						assertEquals(slackValues[sIndex], tester.getSlacks(i)[sIndex], 0.1);
					}
				}
			}
			
			//PROJECTIONS
			l.clear();
			l.add(0);
			l.add(3);
			l.add(10);
			for(Integer i : l){
				double[] projValues = getTestProjectionValues()[i];
				for(int pIndex = 0; pIndex < projValues.length; pIndex++) {
					assertEquals(projValues[pIndex], tester.getProjections(i)[pIndex], 0.1);
				}
			}
			
			
			
			//WEIGHTS
			l.clear();
			l.add(0);
			l.add(3);
			for(Integer i : l){
				double[] weightValues = getTestWeightValues()[i];
				for(int wIndex = 0; wIndex < weightValues.length; wIndex++) {
					assertEquals(weightValues[wIndex], tester.getWeight(i)[wIndex],0.001);
				}
			}
			
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	
}
