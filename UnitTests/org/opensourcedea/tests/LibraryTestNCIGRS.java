package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNCIGRS {
	
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
		
		
		Objectives[0] = 0.4397964495743679;
		Objectives[1] = 0.3848570911399453;
		Objectives[2] = 0.7934749007220597;
		Objectives[3] = 0.38474444530886304;
		Objectives[4] = 0.508773473545721;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.4517615843106051;
		Objectives[8] = 0.6556429354635763;
		Objectives[9] = 1;
		Objectives[10] = 0.351582478750792;
		Objectives[11] = 1;
		Objectives[12] = 0.23478680983515413;
		Objectives[13] = 0.3699066288024821;
		Objectives[14] = 0.8292550771730149;
		Objectives[15] = 0.39201585569045827;
		Objectives[16] = 1;
		Objectives[17] = 1;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 14;
		ranks[1] = 16;
		ranks[2] = 10;
		ranks[3] = 17;
		ranks[4] = 12;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 13;
		ranks[8] = 11;
		ranks[9] = 1;
		ranks[10] = 19;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 18;
		ranks[14] = 9;
		ranks[15] = 15;
		ranks[16] = 1;
		ranks[17] = 1;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	
	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
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
		refSet.add(new NonZeroLambda(6, 0.42193087161974047));
		refSet.add(new NonZeroLambda(9, 0.5115916791280192));
		refSet.add(new NonZeroLambda(11, 0.06294108942137502));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5012046138367192));
		refSet.add(new NonZeroLambda(6, 0.37557620277414927));
		refSet.add(new NonZeroLambda(11, 0.1806213761336142));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 1));
		referenceSets[6] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(9, 0.02191102309541193));
		refSet.add(new NonZeroLambda(11, 0.3239417099786477));
		refSet.add(new NonZeroLambda(16, 0.4083839716532434));
		refSet.add(new NonZeroLambda(19, 0.0457632952727061));
		referenceSets[15] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [3] = 18.333325421222444;
		slackValues[10] [3] = 103.99724870761032;
		slackValues[13] [3] = 112.73582426570167;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 180.83110617149288;
		projectionValues[0] [1] = 83;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 138.89274475649955;
		projectionValues[3] [1] = 83;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 170.27332542122244;
		
		projectionValues[10] [0] = 135.060057629638;
		projectionValues[10] [1] = 65;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 115.25724870761033;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [6];
		weightValues[0] [2] = 0.0024320840528248657;
		weightValues[0] [3] = 0;
		weightValues[0] [4] = 0;
		weightValues[0] [5] = 7.379673342093626E-6;
		
		weightValues[3] [2] = 0.002770083102493075;
		weightValues[3] [3] = 0;
		weightValues[3] [4] = 0;
		weightValues[3] [5] = 0;
		
		weightValues[10] [2] = 0.0026031565876782187;
		weightValues[10] [3] = 0;
		weightValues[10] [4] = 0;
		weightValues[10] [5] = 0;
		
		weightValues[14] [2] = 0.003055767761650114;
		weightValues[14] [3] = 0;
		weightValues[14] [4] = 0;
		weightValues[14] [5] = 0.0012858537112437726;
		
		return weightValues;
	}
	
	
	@Test
	public void testNCIGRS() {
		
		buildDEAProblem(ModelType.NC_I_GRS); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.setRTSLowerBound(0.8);
			tester.setRTSUpperBound(1.2);
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
			DEAPSolution CheckedSol = getModelResults();
			
			//OBJECTIVES
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
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
