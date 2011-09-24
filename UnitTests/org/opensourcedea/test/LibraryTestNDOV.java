package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNDOV {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution getModelResults() {
		
		
		DEAPSolution DEAModelSol = new DEAPSolution(20, 4);
		
		DEAModelSol.setObjectives(createDEAModelObjectives());		
		
		return DEAModelSol;
	}


	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.4212063233481851;
		Objectives[1] = 0.8293556964374044;
		Objectives[2] = 1;
		Objectives[3] = 0.04904995053692204;
		Objectives[4] = 0.614845234980636;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.8657889855035715;
		Objectives[8] = 0.4315747010383549;
		Objectives[9] = 0.11002538366022485;
		Objectives[10] = 0.0041487977630938;
		Objectives[11] = 1;
		Objectives[12] = 0.1396319863842197;
		Objectives[13] = 0.013309947160102633;
		Objectives[14] = 0.9544894103450399;
		Objectives[15] = 0.3741316642904926;
		Objectives[16] = 1;
		Objectives[17] = 0.99108810952099;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 14;
		ranks[1] = 11;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 12;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 13;
		ranks[9] = 17;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 9;
		ranks[15] = 15;
		ranks[16] = 1;
		ranks[17] = 8;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	
	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setVariableTypes(TestData.createTestVariableTypes(VariableType.NON_DISCRETIONARY));
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

		
	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.6011833036709137));
		refSet.add(new NonZeroLambda(6, 0.3810915098255714));
		refSet.add(new NonZeroLambda(16, 0.01772518650351489));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.6562685500573601));
		refSet.add(new NonZeroLambda(16, 0.2590065117198367));
		refSet.add(new NonZeroLambda(18, 0.08472493822280278));
		referenceSets[10] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [2] = 2040.885518622202;
		slackValues[7] [0] = 192.25000000000003;
		slackValues[7] [1] = 1.0000000000000169;
		slackValues[7] [2] = 541.1400000010237;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3448.486199236283;
		projectionValues[0] [3] = 3193.8504372545917;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3291.595518622202;
		projectionValues[3] [3] = 3097.658577364479;
		
		projectionValues[14] [0] = 327.25;
		projectionValues[14] [1] = 43.0;
		projectionValues[14] [2] = 1784.821089712438;
		projectionValues[14] [3] = 2010.4151803070572;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [5];
		weightValues[0] [1] = 0.0014252293707407691;
		weightValues[0] [2] = 0.016488398362701186;
		weightValues[0] [3] = 0;
		weightValues[0] [4] = 7.433452020778274E-4;
		
		weightValues[3] [1] = 0.012618917438317065;
		weightValues[3] [2] = 0.14598754551407075;
		weightValues[3] [3] = 0;
		weightValues[3] [4] = 0.006581545346847439;
		
		weightValues[10] [1] = 0.10989294714575856;
		weightValues[10] [2] = 2.5561826360281885;
		weightValues[10] [3] = 0;
		weightValues[10] [4] = 0.08880994671403196;
		
		weightValues[14] [1] = 6.448390682577918E-4;
		weightValues[14] [2] = 0.014999383237277949;
		weightValues[14] [3] = 0;
		weightValues[14] [4] = 5.211264669710044E-4;
		
		return weightValues;
	}
	
	
	@Test
	public void testNDOV() {
		
		buildDEAProblem(ModelType.ND_O_V); //, DEAModelOrientation.NonOriented);
		
		
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			DEAPSolution CheckedSol = getModelResults();
			
			//OBJECTIVES
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
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
