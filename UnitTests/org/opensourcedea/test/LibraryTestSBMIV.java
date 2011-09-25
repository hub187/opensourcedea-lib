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




public class LibraryTestSBMIV {
	
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
		
		
		Objectives[0] = 0.538022090458506;
		Objectives[1] = 0.685898941154542;
		Objectives[2] = 1;
		Objectives[3] = 0.446963678331783;
		Objectives[4] = 0.721210167858574;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.726535481352475;
		Objectives[8] = 0.60006666659683;
		Objectives[9] = 0.696433222387383;
		Objectives[10] = 0.513272350686686;
		Objectives[11] = 1;
		Objectives[12] = 0.400617403076217;
		Objectives[13] = 0.473247437790967;
		Objectives[14] = 0.887025518881808;
		Objectives[15] = 0.727925309558822;
		Objectives[16] = 1;
		Objectives[17] = 0.883141327984349;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	

	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 14;
		ranks[2] = 1;
		ranks[3] = 19;
		ranks[4] = 12;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 11;
		ranks[8] = 15;
		ranks[9] = 13;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 18;
		ranks[14] = 8;
		ranks[15] = 10;
		ranks[16] = 1;
		ranks[17] = 9;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	


	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.19849587157795248));
		refSet.add(new NonZeroLambda(11, 0.3473142508074158));
		refSet.add(new NonZeroLambda(16, 0.45418987761463175));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.8226707583502072));
		refSet.add(new NonZeroLambda(11, 0.15445950249359));
		refSet.add(new NonZeroLambda(18, 0.02286973915403348));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.44400878518131115));
		refSet.add(new NonZeroLambda(16, 0.05356022106027732));
		refSet.add(new NonZeroLambda(18, 0.5024309937584118));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		
		slackValues[1] [0] = 561.3875588012145;
		slackValues[8] [1] = 34.55777396779228;
		slackValues[9] [1] = 44.20700908059031;
		slackValues[10] [3] = 368.4146064835641;
		slackValues[12] [0] = 933.519017956193;
		slackValues[13] [3] = 1229.962352846577;
		slackValues[14] [2] = 967.1714096764433;
		slackValues[17] [2] = 1696.4991027590083;
		slackValues[19] [1] = 0.0;

		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 169.06486790060714;
		projectionValues[0] [1] = 55.18397073554135;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 128.09406057442834;
		projectionValues[3] [1] = 44.74544957029353;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 1220.0623512756804;
		
		projectionValues[10] [0] = 219.1378150928486;
		projectionValues[10] [1] = 29.645661128736336;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 379.6746064835641;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0012160420264121093;
		weightValues[0] [1] = 0.006024096385539063;
		weightValues[0] [2] = 1.6856606344169806E-4;
		weightValues[0] [3] = 5.673215250554413E-5;
		
		weightValues[3] [0] = 0.0013850415512460935;
		weightValues[3] [1] = 0.0060240963855390629;
		weightValues[3] [2] = 1.3197363243319356E-4;
		weightValues[3] [3] = 0;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMIV() {
				
		
		
		buildDEAProblem(ModelType.SBM_I_V); //, DEAModelOrientation.NonOriented);
		
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
