package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;




public class LibraryTestCCROTest {
	
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
		
		
		Objectives[0] = 0.519434453047034;
		Objectives[1] = 0.872474445374321;
		Objectives[2] = 0.549048143682244;
		Objectives[3] = 0.322936931532726;
		Objectives[4] = 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.830850567998129;
		Objectives[8] = 0.539682742929534;
		Objectives[9] = 0.567189831743522;
		Objectives[10] = 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 0.564403802355244;
		Objectives[13] = 0.338735130146885;
		Objectives[14] = 0.805598209487005;
		Objectives[15] = 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 0.739084603920947;
		Objectives[18] = 1;
		Objectives[19] = 0.207079840963623;
		return Objectives;
	}
	

	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 6;
		ranks[2] = 14;
		ranks[3] = 19;
		ranks[4] = 9;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 7;
		ranks[8] = 15;
		ranks[9] = 12;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 13;
		ranks[13] = 18;
		ranks[14] = 8;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 10;
		ranks[18] = 1;
		ranks[19] = 20;
		
		return ranks;
	}
	

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.56388195158856));
		refSet.add(new NonZeroLambda(6, 0.2248063169510746));
		refSet.add(new NonZeroLambda(11, 0.2946424323673838));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.3284167381071161));
		refSet.add(new NonZeroLambda(11, 0.8773146670487018));
		refSet.add(new NonZeroLambda(18, 2.928580024142089));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.9999999999999996));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(16, 0.9202884649701224));
		refSet.add(new NonZeroLambda(18, 1.5942307005959757));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 1474.90591392133;
		SlacksArray[8] [1] = 2.67778662084071;
		
		SlacksArray[9] [1] = 51.94181048433;
		SlacksArray[10] [3] = 500.847585086953;
		SlacksArray[12] [3] = 1794.97856609299;
		SlacksArray[13] [3] = 192.923448690019;
		SlacksArray[14] [2] = 267.885210838506;
		SlacksArray[17] [2] = 779.791154722907;
		SlacksArray[19] [1] = 26.0026568088161;
		
		return SlacksArray;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3613.891972295424;
		projectionValues[0] [3] = 2589.874414584571;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3872.9234035385743;
		projectionValues[3] [3] = 470.4943447590976;
		
		projectionValues[10] [0] = 384.149;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 3066.9434834381714;
		projectionValues[10] [3] = 525.3761238765051;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015860280139486337;
		weightValues[0] [1] = 0.0153378744767683;
		weightValues[0] [2] = 4.497497200625987E-4;
		weightValues[0] [3] = 1.1576770499073764E-4;
		
		weightValues[3] [0] = 3.3281018132351486E-4;
		weightValues[3] [1] = 0.03586066734951187;
		weightValues[3] [2] = 7.942744260038437E-4;
		weightValues[3] [3] = 4.339234337720519E-5;
		
		return weightValues;
	}
	
	
	@Test
	public void testCCRO() {
				
		
		
		buildDEAProblem(ModelType.CCR_O); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		try {
		
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
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