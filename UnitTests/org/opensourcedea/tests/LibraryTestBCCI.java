package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.opensourcedea.dea.DEAPSolution;
import org.opensourcedea.dea.DEAProblem;
import org.opensourcedea.dea.ModelType;
import org.opensourcedea.dea.NonZeroLambda;
import org.opensourcedea.dea.RankingType;
import org.opensourcedea.dea.SolverReturnStatus;

//import dea.DEAModelOrientation;




public class LibraryTestBCCI {
	
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
		
		
		Objectives[0] = 0.567311991306061;
		Objectives[1] = 0.920072185311599;
		Objectives[2] = 1;
		Objectives[3] = 0.453078828334519;
		Objectives[4] = 0.784593172313617;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.844109331376493;
		Objectives[8] = 0.601269603604185;
		Objectives[9] = 0.86914258558318;
		Objectives[10] = 0.518224791143689;
		Objectives[11] = 1;
		Objectives[12] = 0.589846230572554;
		Objectives[13] = 0.47698681816151;
		Objectives[14] = 0.944298220111978;
		Objectives[15] = 0.730166645993769;
		Objectives[16] = 1;
		Objectives[17] = 0.986772936679716;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 17;
		ranks[1] = 10;
		ranks[2] = 1;
		ranks[3] = 20;
		ranks[4] = 13;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 12;
		ranks[8] = 15;
		ranks[9] = 11;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 9;
		ranks[15] = 14;
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
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.3592576353691921));
		refSet.add(new NonZeroLambda(11, 0.26573073609165077));
		refSet.add(new NonZeroLambda(16, 0.010203198439188862));
		refSet.add(new NonZeroLambda(18, 0.3648084300999693));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.8226707583517102));
		refSet.add(new NonZeroLambda(11, 0.15445950249432536));
		refSet.add(new NonZeroLambda(18, 0.022869739153964914));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.4626039193502596));
		refSet.add(new NonZeroLambda(18, 0.5373960806495911));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [3] = 761.103111023872;
		slackValues[9] [1] = 26.83096330525018;
		slackValues[12] [0] = 444.3317907476238;
		slackValues[17] [0] = 88.34920839504281;
		slackValues[17] [2] = 1732.7945541424235;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 233.26232150557206;
		projectionValues[0] [1] = 47.0870264974645;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 163.5625034014968;
		projectionValues[3] [1] = 37.605783330538046;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 913.0431110238719;
		
		projectionValues[10] [0] = 199.07421912864586;
		projectionValues[10] [1] = 33.684388722506064;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 553.347581631604;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 9.352402077983277E-4;
		weightValues[0] [1] = 0.0074151479971031435;
		weightValues[0] [2] = 1.7184879747594143E-4;
		weightValues[0] [3] = 6.439390561199946E-5;
		
		weightValues[3] [0] = 0.0012931131969351071;
		weightValues[3] [1] = 0.006423929348270198;
		weightValues[3] [2] = 1.3050809836249983E-4;
		weightValues[3] [3] = 0;
		
		return weightValues;
	}
	
	
	@Test
	public void testBCCI() {
				
		
		
		buildDEAProblem(ModelType.BCC_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			DEAPSolution CheckedSol = getModelResults();
			
			
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
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
