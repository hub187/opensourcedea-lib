package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static java.util.ArrayList.*;


import java.util.ArrayList;


//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

//import dea.DEAModelOrientation;




public class LibraryTestNCO {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetModelResults() {
		
		
		DEAPSolution DEAModelSol = new DEAPSolution(20, 4);
		
		DEAModelSol.setObjectives(createDEAModelObjectives());		
		
		return DEAModelSol;
	}


	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.347364912687656;
		Objectives[1] = 0.55612377191188;
		Objectives[2] = 0.110944058693948;
		Objectives[3] = 0.0427857919624224;
		Objectives[4] = 0.563363152071657;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.693383394426502;
		Objectives[8] = 0.348559376328825;
		Objectives[9] = 1;
		Objectives[10] = 0.00347019230493515;
		Objectives[11] = 1;
		Objectives[12] = 0.0719069914301707;
		Objectives[13] = 0.011633776910525;
		Objectives[14] = 0.935279650783773;
		Objectives[15] = 0.349969019385356;
		Objectives[16] = 1;
		Objectives[17] = 1;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 15;
		ranks[1] = 12;
		ranks[2] = 16;
		ranks[3] = 18;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 14;
		ranks[9] = 1;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 17;
		ranks[13] = 19;
		ranks[14] = 9;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 1;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	
	public void BuildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setVariableTypes(TestData.createTestVariableTypes(VariableType.NON_CONTROLLABLE));
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.123185387695197));
		refSet.add(new NonZeroLambda(16, 1.78211276844345));
		refSet.add(new NonZeroLambda(18, 0.745551041780373));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.754657723273458));
		refSet.add(new NonZeroLambda(16, 0.40446921743191));
		refSet.add(new NonZeroLambda(18, 2.5923201355133));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 1));
		referenceSets[6] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.18567717719513));
		refSet.add(new NonZeroLambda(16, 0.55963867978833));
		refSet.add(new NonZeroLambda(18, 0.60162355063549));
		referenceSets[15] = refSet;
		
		return referenceSets;
	}
	
//	private double[] [] getTestSlackValues() {
//		double[] [] slackValues = new double[20] [4];
//		slackValues[3] [3] = 18.333333;
//		slackValues[10] [3] = 103.9972;
//		slackValues[13] [3] = 112.7358;
//		return slackValues;
//	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 3872.786;
		
		projectionValues[3] [0] = 361;
		projectionValues[3] [1] = 83;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 3551.179;
		
		projectionValues[10] [0] = 384.14;
		projectionValues[10] [1] = 65;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 3244.777;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.002232321124770743;
		weightValues[0] [1] = 0;
		weightValues[0] [2] = 0;
		weightValues[0] [3] = 7.433452020778272E-4;
		
		weightValues[3] [0] = 0.015640717071369825;
		weightValues[3] [1] = 0;
		weightValues[3] [2] = 0;
		weightValues[3] [3] = 0.00658154534684744;
		
		weightValues[10] [0] = 0.2667028987142651;
		weightValues[10] [1] = 0;
		weightValues[10] [2] = 0;
		weightValues[10] [3] = 0.08880994671403199;
		
		weightValues[14] [0] = 0.0012384312800032995;
		weightValues[14] [1] = 0;
		weightValues[14] [2] = 0;
		weightValues[14] [3] = 5.211264669710045E-4;
		
		return weightValues;
	}
	
	
	@Test
	public void TestNCO() {
		
		BuildDEAProblem(ModelType.NC_O); //, DEAModelOrientation.NonOriented);
		
		
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			DEAPSolution CheckedSol = GetModelResults();
			
			//OBJECTIVES
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
			//RANKS
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
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
