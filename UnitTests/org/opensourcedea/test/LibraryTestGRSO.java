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




public class LibraryTestGRSO {
	
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

	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.519434453047034;
		Objectives[1] = 0.918212606063258;
		Objectives[2] = 0.845181758758218;
		Objectives[3] = 0.341382546208781;
		Objectives[4] = 0.783616224357704;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.84387830661803;
		Objectives[8] = 0.539682742929534;
		Objectives[9] = 0.660194603726043;
		Objectives[10] = 0.464481615490394;
		Objectives[11] = 1;
		Objectives[12] = 0.621175185816311;
		Objectives[13] = 0.342539976784181;
		Objectives[14] = 0.920795679367037;
		Objectives[15] = 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 0.930382832324589;
		Objectives[18] = 1;
		Objectives[19] = 0.477119680444747;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 8;
		ranks[2] = 9;
		ranks[3] = 20;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 15;
		ranks[9] = 12;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 19;
		ranks[14] = 7;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 6;
		ranks[18] = 1;
		ranks[19] = 17;
		
		return ranks;
	}
	

	

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5638819515886706));
		refSet.add(new NonZeroLambda(6, 0.22480631695152467));
		refSet.add(new NonZeroLambda(11, 0.29464243236656384));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.8731362031046783));
		refSet.add(new NonZeroLambda(11, 0.19421370622359005));
		refSet.add(new NonZeroLambda(18, 0.1326500906717347));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1.0000000000000002));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.46419753086420623));
		refSet.add(new NonZeroLambda(18, 0.7358024691358123));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 1104.6579764043213;
		SlacksArray[8] [1] = 2.67778662084418;
		SlacksArray[9] [1] = 44.81336983941109;
		SlacksArray[10] [3] = 80.9605281789788;
		SlacksArray[12] [3] = 3186.952731110766;
		SlacksArray[13] [3] = 662.2988040461644;
		SlacksArray[14] [2] = 1060.30299078136;
		SlacksArray[17] [2] = 1761.0331789146953;
		SlacksArray[19] [1] = 8.125428016285555;
		
		return SlacksArray;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3613.891972295;
		projectionValues[0] [3] = 2589.874414584267;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3663.6612325053934;
		projectionValues[3] [3] = 1709.1906667598982;
		
		projectionValues[10] [0] = 252.3512857142883;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 3034.6733928549384;
		projectionValues[10] [3] = 105.20260714286559;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015860280139484474;
		weightValues[0] [1] = 0.015337874476766503;
		weightValues[0] [2] = 4.4974972006254615E-4;
		weightValues[0] [3] = 1.1576770499072393E-4;
		
		weightValues[3] [0] = 0.0022959550676699063;
		weightValues[3] [1] = 0.0061008721274462396;
		weightValues[3] [2] = 7.995458579520284E-4;
		weightValues[3] [3] = 0;
		
		return weightValues;
	}
	
	
	@Test
	public void testGRSO() {
				
		
		
		buildDEAProblem(ModelType.GRS_O);
		
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
