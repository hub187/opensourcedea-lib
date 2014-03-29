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
import org.opensourcedea.exception.InvalidPropertyValueException;

//import dea.DEAModelOrientation;




public class LibraryTestSBMOGRSTest {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	
	public void buildDEAProblem(ModelType ModelType) { 
		
		tester.setModelType(ModelType);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
		try {
		tester.setRTSLowerBound(0.8);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
	}


	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.474691622106158;
		Objectives[1] = 0.851175498333408;
		Objectives[2] = 0.384575675921289;
		Objectives[3] = 0.0848529089827132;
		Objectives[4] = 0.697242683806256;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.835214451051194;
		Objectives[8] = 0.479389421563248;
		Objectives[9] = 0.185960069769944;
		Objectives[10] = 0.00802107465753847;
		Objectives[11] = 1;
		Objectives[12] = 0.196055673858297;
		Objectives[13] = 0.0249009657336429;
		Objectives[14] = 0.407976972938612;
		Objectives[15] = 0.503974545691682;
		Objectives[16] = 1;
		Objectives[17] = 0.09610488990776;
		Objectives[18] = 1;
		Objectives[19] = 0.207123599802275;
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
		ranks[1] = 6;
		ranks[2] = 13;
		ranks[3] = 18;
		ranks[4] = 8;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 7;
		ranks[8] = 10;
		ranks[9] = 16;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 15;
		ranks[13] = 19;
		ranks[14] = 12;
		ranks[15] = 9;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 14;
		
		return ranks;
	}


	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5996449704142008));
		refSet.add(new NonZeroLambda(6, 0.390934911242604));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1.0074074074074073));
		refSet.add(new NonZeroLambda(18, 0.1925925925925924));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1.0));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.46419753086390864));
		refSet.add(new NonZeroLambda(18, 0.735802469136015));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 3067.763999997636;
		SlacksArray[8] [2] = 1399.1469568382297;
		SlacksArray[9] [1] = 39.050020169423135;
		SlacksArray[10] [3] = 2776.344316127224;
		SlacksArray[12] [3] = 3601.084000001229;
		SlacksArray[13] [3] = 2972.1536531408237;
		SlacksArray[14] [2] = 1465.136537233091;
		SlacksArray[17] [2] = 1768.0945432093474;
		SlacksArray[19] [1] = 10.007795494811447;
		
		return SlacksArray;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3459.7943680479507;
		projectionValues[0] [3] = 3188.5332061548597;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3051.5100250076384;
		projectionValues[3] [3] = 3210.54953920688;
		
		projectionValues[10] [0] = 384.149;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 2505.013594958933;
		projectionValues[10] [3] = 2787.604316127224;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015357409928576145;
		weightValues[0] [1] = 0.017773255342004076;
		weightValues[0] [2] = 2.6635698228173835E-4;
		weightValues[0] [3] = 3.716726010390621E-4;
		
		weightValues[3] [0] = 0.007559621105406089;
		weightValues[3] [1] = 0.08919233378090372;
		weightValues[3] [2] = 3.9977292897656246E-4;
		weightValues[3] [3] = 0.003290772673421875;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMOGRS() {
				
		buildDEAProblem(ModelType.SBM_O_GRS);

		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();


		tester = new DEAProblem(20, 4);

		buildDEAProblem(ModelType.SBM_O_GRS);

		try {
			for(int i = 0 ; i < tester.getNumberOfDMUs(); i++){
				tester.solveOne(i, tester.getTranspose(true));
			}
			//			tester.solve();
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
