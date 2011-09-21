package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNDOGRS {
	
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
		
		
		Objectives[0] = 0.4068264909881983;
		Objectives[1] = 0.7916654052935285;
		Objectives[2] = 0.247226941731393;
		Objectives[3] = 0.04732523144232018;
		Objectives[4] = 0.5987975694446058;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.8264429738068237;
		Objectives[8] = 0.40963970457298693;
		Objectives[9] = 0.10279147301059323;
		Objectives[10] = 0.004039310720985335;
		Objectives[11] = 1;
		Objectives[12] = 0.11635998865351643;
		Objectives[13] = 0.01281087354522336;
		Objectives[14] = 0.920795679366727;
		Objectives[15] = 0.35981051987304424;
		Objectives[16] = 1;
		Objectives[17] = 0.9303828323248632;
		Objectives[18] = 1;
		Objectives[19] = 0.12959594071414754;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 12;
		ranks[1] = 9;
		ranks[2] = 14;
		ranks[3] = 18;
		ranks[4] = 10;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 8;
		ranks[8] = 11;
		ranks[9] = 17;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 7;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 6;
		ranks[18] = 1;
		ranks[19] = 15;
		
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
		try {
			tester.setRTSLowerBound(0.8);
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.6338438966840266));
		refSet.add(new NonZeroLambda(6, 0.17210472094637472));
		refSet.add(new NonZeroLambda(16, 0.3940513823695983));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1.0074074074074073));
		refSet.add(new NonZeroLambda(18, 0.19259259259259207));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5726768813668038));
		refSet.add(new NonZeroLambda(16, 0.4266624370865499));
		refSet.add(new NonZeroLambda(18, 0.20066068154664626));
		referenceSets[10] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1.2));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[1] [0] = 459.32814814814816;
		slackValues[1] [2] = 305.3983703714024;
		slackValues[2] [0] = 917.722000003852;
		slackValues[2] [1] = 48.400000000843626;
		slackValues[2] [2] = 610.7319999619219;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3208.400705621719;
		projectionValues[0] [3] = 3306.7413990969067;
		
		projectionValues[2] [0] = 619.967999996148;
		projectionValues[2] [1] = 99.59999999915638;
		projectionValues[2] [2] = 4533.971999961922;
		projectionValues[2] [3] = 4075.283999972178;
		
		projectionValues[14] [0] = 327.25;
		projectionValues[14] [1] = 43.0;
		projectionValues[14] [2] = 1608.557083749296;
		projectionValues[14] [3] = 2083.9802390468735;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [6];
		weightValues[0] [2] = 0.0014252293707407682;
		weightValues[0] [3] = 0.0164883983627012;
		weightValues[0] [4] = 0;
		weightValues[0] [5] = 7.433452020778274E-4;
		
		weightValues[3] [2] = 0.012618917438317053;
		weightValues[3] [3] = 0.14598754551407087;
		weightValues[3] [4] = 0;
		weightValues[3] [5] = 0.006581545346847439;
		
		weightValues[10] [2] = 0.10989294714575837;
		weightValues[10] [3] = 2.556182636028189;
		weightValues[10] [4] = 0;
		weightValues[10] [5] = 0.08880994671403196;
		
		weightValues[14] [2] = 6.448390682577904E-4;
		weightValues[14] [3] = 0.014999383237277949;
		weightValues[14] [4] = 0;
		weightValues[14] [5] = 5.211264669710044E-4;
		
		return weightValues;
	}
	
	
	@Test
	public void testNDOGRS() {
		
		buildDEAProblem(ModelType.ND_O_GRS); //, DEAModelOrientation.NonOriented);
		
		
		
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
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
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
			l.add(1);
			l.add(2);
			for(Integer i : l){
				double[] slackValues = getTestSlackValues()[i];
				for(int sIndex = 0; sIndex < slackValues.length; sIndex++) {
					assertEquals(slackValues[sIndex], tester.getSlacks(i)[sIndex], 0.1);
				}
			}
			
			//PROJECTIONS
			l.clear();
			l.add(0);
			l.add(2);
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
				for(int wIndex = 2; wIndex < weightValues.length; wIndex++) {
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
