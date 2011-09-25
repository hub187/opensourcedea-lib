package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNCIV {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public void buildDEAProblem(ModelType ModelType) { 
		
		tester.setModelType(ModelType);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setVariableTypes(TestData.createTestVariableTypes(VariableType.NON_CONTROLLABLE));
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}

	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.440051949975726;
		Objectives[1] = 0.422617807577486;
		Objectives[2] = 1;
		Objectives[3] = 0.387885024554886;
		Objectives[4] = 0.51256252524238;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.459218777376301;
		Objectives[8] = 1;
		Objectives[9] = 1;
		Objectives[10] = 0.361804451375677;
		Objectives[11] = 1;
		Objectives[12] = 0.505251546536799;
		Objectives[13] = 0.374441475015705;
		Objectives[14] = 1;
		Objectives[15] = 0.523495521953959;
		Objectives[16] = 1;
		Objectives[17] = 1;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 17;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 13;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 15;
		ranks[8] = 1;
		ranks[9] = 1;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 19;
		ranks[14] = 11;
		ranks[15] = 12;
		ranks[16] = 1;
		ranks[17] = 1;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	

	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.418994961605266));
		refSet.add(new NonZeroLambda(9, 0.510513059735956));
		refSet.add(new NonZeroLambda(11, 0.0651410577580579));
		refSet.add(new NonZeroLambda(16, 0.00534092090072073));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(2, 0.00476917774042331));
		refSet.add(new NonZeroLambda(5, 0.650229099248335));
		refSet.add(new NonZeroLambda(6, 0.345001723021242));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 1));
		referenceSets[6] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.285157247550584));
		refSet.add(new NonZeroLambda(16, 0.553453003609665));
		refSet.add(new NonZeroLambda(18, 0.161379748839751));
		referenceSets[15] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [3] = 391.9777;
		slackValues[10] [3] = 94.177796;
		slackValues[13] [3] = 108.7712;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 180.9362;
		projectionValues[0] [1] = 83;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 140.0265;
		projectionValues[3] [1] = 83;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 543.9177;
		
		projectionValues[10] [0] = 138.9836;
		projectionValues[10] [1] = 65;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 105.438;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [5];
		weightValues[0] [1] = 0.0024320840528248657;
		weightValues[0] [2] = 0;
		weightValues[0] [3] = 0;
		weightValues[0] [4] = 1.5805913768025566E-5;
		
		weightValues[3] [1] = 0.0027700831024930744;
		weightValues[3] [2] = 0;
		weightValues[3] [3] = 0;
		weightValues[3] [4] = 0;
		
		weightValues[10] [1] = 0.0026031565876782187;
		weightValues[10] [2] = 0;
		weightValues[10] [3] = 0;
		weightValues[10] [4] = 0;
		
		weightValues[14] [1] = 0.0030557677616501145;
		weightValues[14] [2] = 0;
		weightValues[14] [3] = 0;
		weightValues[14] [4] = 0.0024473888636939506;
		
		return weightValues;
	}
	
	
	@Test
	public void testNCIV() {
		
		buildDEAProblem(ModelType.NC_I_V); //, DEAModelOrientation.NonOriented);
		
		
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			
			//OBJECTIVES
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
			//RANKS
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 8), createSolRanks());
			
			//REFERENCE SET
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(0);
			l.add(1);
			l.add(6);
			l.add(15);
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
			l.add(10);
			l.add(13);
			for(Integer i : l){
				double[] slackValues = getTestSlackValues()[i];
				for(int sIndex = 0; sIndex < slackValues.length; sIndex++) {
					assertEquals(slackValues[sIndex], tester.getSlacks(i)[sIndex], 0.1);
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
			l.add(10);
			l.add(14);
			for(Integer i : l){
				double[] weightValues = getTestWeightValues()[i];
				for(int wIndex = 1; wIndex < weightValues.length; wIndex++) {
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
