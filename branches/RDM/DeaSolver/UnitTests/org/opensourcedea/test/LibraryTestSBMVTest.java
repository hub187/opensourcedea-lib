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




public class LibraryTestSBMVTest {
	
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
		
		
		Objectives[0] = 0.4337207968687;
		Objectives[1] = 0.655740942924697;
		Objectives[2] = 1;
		Objectives[3] = 0.0711847916698139;
		Objectives[4] = 0.633843170637834;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.718162581012847;
		Objectives[8] = 0.478929894370442;
		Objectives[9] = 0.14541274686293;
		Objectives[10] = 0.00662837755677064;
		Objectives[11] = 1;
		Objectives[12] = 0.14246955242863;
		Objectives[13] = 0.0204266899493119;
		Objectives[14] = 0.427059536602581;
		Objectives[15] = 0.489456449809396;
		Objectives[16] = 1;
		Objectives[17] = 0.0870188714455836;
		Objectives[18] = 1;
		Objectives[19] = 0.9999999999999;
		return Objectives;
	}
	
	private boolean[] createEfficiencyValues() {

		boolean[] efficiencies = new boolean[20];


		efficiencies[0] = false;
		efficiencies[1] = false;
		efficiencies[2] = true;
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
		efficiencies[19] = true;
		return efficiencies;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 9;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 10;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 8;
		ranks[8] = 12;
		ranks[9] = 15;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 14;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.955555555555555));
		refSet.add(new NonZeroLambda(16, 0.04444444444444489));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.9999999999999998));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.9999999999999993));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.4643292580804643));
		refSet.add(new NonZeroLambda(11, 0.0069523231336140875));
		refSet.add(new NonZeroLambda(18, 0.5287184187859216));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 0;
		SlacksArray[8] [2] = 1324.6199999986793;
		SlacksArray[9] [1] = 43.316682156133844;
		SlacksArray[10] [3] = 2011.9440757967288;
		SlacksArray[12] [3] = 2341.7012481088695;
		SlacksArray[13] [3] = 1861.856007160583;
		SlacksArray[14] [2] = 1354.5481538354816;
		SlacksArray[17] [2] = 1756.831103633229;
		SlacksArray[19] [2] = 1.1740981782286082E-10;
		
		return SlacksArray;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 252.1826666666665;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 2951.3039999993753;
		projectionValues[0] [3] = 2889.02111111111;
		
		projectionValues[3] [0] = 135.00780643336253;
		projectionValues[3] [1] = 50.07338135070136;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 1934.002829755267;
		
		projectionValues[10] [0] = 145.95224984517597;
		projectionValues[10] [1] = 53.14881505578193;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 2023.2040757967288;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0012160420264121093;
		weightValues[0] [1] = 0.006314705985199403;
		weightValues[0] [2] = 1.1552459715053574E-4;
		weightValues[0] [3] = 1.6120218489903398E-4;
		
		weightValues[3] [0] = 0.0013850415512460935;
		weightValues[3] [1] = 0.006024096385539068;
		weightValues[3] [2] = 8.051851791138854E-5;
		weightValues[3] [3] = 2.3425296719037283E-4;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMV() {
				
		buildDEAProblem(ModelType.SBM_V);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		checkData();
		
		
		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.SBM_V);
		
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
