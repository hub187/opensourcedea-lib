package org.opensourcedea.tests;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opensourcedea.dea.DEAPSolution;
import org.opensourcedea.dea.DEAProblem;
import org.opensourcedea.dea.ModelType;
import org.opensourcedea.dea.NonZeroLambda;
import org.opensourcedea.dea.RankingType;
import org.opensourcedea.dea.SolverReturnStatus;
import org.opensourcedea.dea.VariableType;





public class LibraryTestNCI {
	
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
		
		
		Objectives[0] = 0.439796449574369;
		Objectives[1] = 0.384857091140152;
		Objectives[2] = 0.229893314639692;
		Objectives[3] = 0.38474444530869;
		Objectives[4] = 0.508773473545725;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.451761584310805;
		Objectives[8] = 0.655642935463584;
		Objectives[9] = 1;
		Objectives[10] = 0.351590715961988;
		Objectives[11] = 1;
		Objectives[12] = 0.213673748925812;
		Objectives[13] = 0.369906628802482;
		Objectives[14] = 0.82925507717443;
		Objectives[15] = 0.361508497158669;
		Objectives[16] = 1;
		Objectives[17] = 1;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 14;
		ranks[2] = 19;
		ranks[3] = 15;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 12;
		ranks[8] = 10;
		ranks[9] = 1;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 16;
		ranks[14] = 9;
		ranks[15] = 17;
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
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.421930871619895));
		refSet.add(new NonZeroLambda(9, 0.511591679127981));
		refSet.add(new NonZeroLambda(11, 0.0629410894212169));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.501204613837549));
		refSet.add(new NonZeroLambda(6, 0.375576202773501));
		refSet.add(new NonZeroLambda(11, 0.180621376133376));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 1));
		referenceSets[6] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.111079611602825));
		refSet.add(new NonZeroLambda(9, 0.597659261779738));
		refSet.add(new NonZeroLambda(11, 0.737193030387624));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [3] = 18.333333;
		slackValues[10] [3] = 103.9972;
		slackValues[13] [3] = 112.7358;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 180.8311;
		projectionValues[0] [1] = 83;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 138.8927;
		projectionValues[3] [1] = 83;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 170.2733;
		
		projectionValues[10] [0] = 135.0601;
		projectionValues[10] [1] = 65;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 115.2572;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0024320840528248657;
		weightValues[0] [1] = 0;
		weightValues[0] [2] = 0;
		weightValues[0] [3] = 7.379673342093572E-6;
		
		weightValues[3] [0] = 0.002770083102493075;
		weightValues[3] [1] = 0;
		weightValues[3] [2] = 0;
		weightValues[3] [3] = 0;
		
		weightValues[10] [0] = 0.0026031565876782183;
		weightValues[10] [1] = 0;
		weightValues[10] [2] = 0;
		weightValues[10] [3] = 0;
		
		weightValues[14] [0] = 0.0030557677616501137;
		weightValues[14] [1] = 0;
		weightValues[14] [2] = 0;
		weightValues[14] [3] = 0.001285853711243773;
		
		return weightValues;
	}
	
	
	@Test
	public void TestNCI() {
		
		BuildDEAProblem(ModelType.NC_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.setVariableTypes(TestData.createTestVariableTypes(VariableType.NON_CONTROLLABLE));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
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
