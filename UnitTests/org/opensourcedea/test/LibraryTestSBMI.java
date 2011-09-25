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




public class LibraryTestSBMI {
	
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
		
		
		Objectives[0] = 0.485053421558462;
		Objectives[1] = 0.684214621325777;
		Objectives[2] = 0.405470259017482;
		Objectives[3] = 0.302295376011453;
		Objectives[4] = 0.717138904136575;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.725880792155402;
		Objectives[8] = 0.531377253110481;
		Objectives[9] = 0.393890615572574;
		Objectives[10] = 0.374336031913962;
		Objectives[11] = 1;
		Objectives[12] = 0.400138842637063;
		Objectives[13] = 0.303136690227086;
		Objectives[14] = 0.746986522059294;
		Objectives[15] = 0.55719855115612;
		Objectives[16] = 1;
		Objectives[17] = 0.730090905380776;
		Objectives[18] = 1;
		Objectives[19] = 0.145890908729517;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 10;
		ranks[2] = 14;
		ranks[3] = 19;
		ranks[4] = 9;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 8;
		ranks[8] = 12;
		ranks[9] = 16;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 15;
		ranks[13] = 18;
		ranks[14] = 6;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 7;
		ranks[18] = 1;
		ranks[19] = 20;		
		
		return ranks;
	}


	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.45389563426871143));
		refSet.add(new NonZeroLambda(11, 0.1814837920184306));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.8275128242219796));
		refSet.add(new NonZeroLambda(11, 0.14894152888183096));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.9999999999999999));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(16, 0.9752583964384889));
		refSet.add(new NonZeroLambda(18, 0.4948320712302291));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		
		slackValues[1] [0] = 565.2783996952528;
		slackValues[8] [1] = 41.49275659364882;
		slackValues[9] [1] = 66.2497310508621;
		slackValues[10] [0] = 275.2428760226046;
		slackValues[12] [0] = 927.2256121963641;
		slackValues[13] [1] = 49.31922247400062;
		slackValues[14] [0] = 165.59732131258116;
		slackValues[17] [0] = 246.4324015696016;
		slackValues[19] [1] = 40.27311303420205;

		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 155.6089169564472;
		projectionValues[0] [1] = 49.10718884990945;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 97.69806659514722;
		projectionValues[3] [1] = 27.71859605391957;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 151.94;
		
		projectionValues[10] [0] = 108.9061239773954;
		projectionValues[10] [1] = 30.23577229758716;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 21.905295723182917;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0012160420264121093;
		weightValues[0] [1] = 0.006024096385539061;
		weightValues[0] [2] = 2.2239527019917376E-4;
		weightValues[0] [3] = 5.023338678908341E-5;
		
		weightValues[3] [0] = 0.001385041551246094;
		weightValues[3] [1] = 0.00602409638553906;
		weightValues[3] [2] = 2.354315902865408E-4;
		weightValues[3] [3] = 5.159096830355026E-5;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMI() {
				
		
		
		buildDEAProblem(ModelType.SBM_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
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
			l.add(1);
			l.add(8);
			l.add(9);
			l.add(10);
			l.add(12);
			l.add(13);
			l.add(14);
			l.add(17);
			l.add(19);
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
