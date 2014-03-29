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




public class LibraryTestSBMTest {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}
	


	private double[] createDEAModelObjectives() {
		
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
	

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.976470588235294));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1.0120481927710843));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.14815147612412316));
		refSet.add(new NonZeroLambda(16, 1.3101781132362378));
		referenceSets[10] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.41553852633734845));
		refSet.add(new NonZeroLambda(16, 1.7127575578500023));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [2] = 264.952999998976;
		slackValues[7] [0] = 380.28659633902515;
		slackValues[7] [3] = 114.45239247508653;
		slackValues[9] [1] = 39.050020169423156;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 253.19882352941175;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 2982.7270588229057;
		projectionValues[0] [3] = 2877.6881176470342;
		
		projectionValues[3] [0] = 205.75700000000006;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 1515.662999998976;
		projectionValues[3] [3] = 3406.7972500022474;
		
		projectionValues[14] [0] = 322.7708746460196;
		projectionValues[14] [1] = 43.0;
		projectionValues[14] [2] = 1971.8168463154711;
		projectionValues[14] [3] = 1918.92;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0012160420264121093;
		weightValues[0] [1] = 0.006024096385543771;
		weightValues[0] [2] = 1.1544349903340183E-4;
		weightValues[0] [3] = 1.610890211746355E-4;
		
		weightValues[3] [0] = 0.001385041551246094;
		weightValues[3] [1] = 0.006024096385544099;
		weightValues[3] [2] = 2.655638258981306E-5;
		weightValues[3] [3] = 2.1860164057435895E-4;
		
		weightValues[10] [0] = 0.001301578293839844;
		weightValues[10] [1] = 0.010721099611275514;
		weightValues[10] [2] = 1.418800146811658E-4;
		weightValues[10] [3] = 2.766873855053114E-4;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBM() {
		
		buildDEAProblem(ModelType.SBM);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		checkData();
		
		
		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.SBM);
		
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
			
			//OBJECTIVES
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
			//EFFICIENCIES
			for(int i = 0 ; i < 20; i++) {
				assertTrue(tester.getEfficiencyStatus(i) == createEfficiencyValues()[i]);
			}
			
			//RANKS
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 8), createSolRanks());
			
			//REFERENCE SET
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(0);
			l.add(1);
			l.add(10);
			l.add(12);
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
			l.add(7);
			l.add(9);
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
			l.add(14);
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
			l.add(10);
			for(Integer i : l){
				double[] weightValues = getTestWeightValues()[i];
				for(int wIndex = 0; wIndex < weightValues.length; wIndex++) {
					assertEquals(weightValues[wIndex], tester.getWeight(i)[wIndex],0.001);
				}
			}
			
			//OPTIMISATION STATUS OK
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
}
