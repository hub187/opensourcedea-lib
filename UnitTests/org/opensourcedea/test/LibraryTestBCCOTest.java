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




public class LibraryTestBCCOTest {
	
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
		
		
		Objectives[0] = 0.540126501537219;
		Objectives[1] = 0.933549251485867;
		Objectives[2] = 1;
		Objectives[3] = 0.375433203385158;
		Objectives[4] = 0.803996071697327;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.865788985495174;
		Objectives[8] = 0.562736097590251;
		Objectives[9] = 0.765136031217916;
		Objectives[10] = 0.469111868112257;
		Objectives[11] = 1;
		Objectives[12] = 0.737946888936728;
		Objectives[13] = 0.367928609120761;
		Objectives[14] = 0.954489410343586;
		Objectives[15] = 0.658038271209364;
		Objectives[16] = 1;
		Objectives[17] = 0.991088109517482;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 17;
		ranks[1] = 10;
		ranks[2] = 1;
		ranks[3] = 19;
		ranks[4] = 12;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 11;
		ranks[8] = 16;
		ranks[9] = 13;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 20;
		ranks[14] = 9;
		ranks[15] = 15;
		ranks[16] = 1;
		ranks[17] = 8;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5960127451425468));
		refSet.add(new NonZeroLambda(6, 0.3740622730161569));
		refSet.add(new NonZeroLambda(11, 0.0299249818412961));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(2, 0.01538461538461519));
		refSet.add(new NonZeroLambda(5, 0.984615384615385));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.9999999999999999));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.4691358024691351));
		refSet.add(new NonZeroLambda(18, 0.5308641975308633));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [3] = 2595.381896325583;
		slackValues[9] [1] = 36.85856593636426;
		slackValues[12] [0] = 343.36076923045414;
		slackValues[17] [0] = 92.50938271604993;
		slackValues[17] [2] = 1755.6615459227132;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3475.445094156343;
		projectionValues[0] [3] = 3127.731191579121;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3331.3782284455688;
		projectionValues[3] [3] = 3000.0877098475185;
		
		projectionValues[10] [0] = 295.0744000000002;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 3004.7203999981034;
		projectionValues[10] [3] = 981.1540000002891;
		
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015297275502017802;
		weightValues[0] [1] = 0.004064832236932274;
		weightValues[0] [2] = 5.32713964563867E-4;
		weightValues[0] [3] = 0;
		
		weightValues[3] [0] = 0.002295955067669916;
		weightValues[3] [1] = 0.00610087212744616;
		weightValues[3] [2] = 7.995458579520284E-4;
		weightValues[3] [3] = 0;
		
		return weightValues;
	}
	
	
	@Test
	public void testBCCO() {
				
		
		
		buildDEAProblem(ModelType.BCC_O); //, DEAModelOrientation.NonOriented);
		
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
