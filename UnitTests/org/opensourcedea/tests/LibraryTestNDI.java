package org.opensourcedea.tests;


import static org.junit.Assert.*;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNDI {
	
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
		
		
		Objectives[0] = 0.37845396540712406;
		Objectives[1] = 0.3848570911399461;
		Objectives[2] = 0.2020726878876469;
		Objectives[3] = 0.27063176342146034;
		Objectives[4] = 0.5004759922450744;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.4517615843106061;
		Objectives[8] = 0.539682742929521;
		Objectives[9] = 0.5671898317435166;
		Objectives[10] = 0.2834996940702576;
		Objectives[11] = 1;
		Objectives[12] = 0.18857905048019374;
		Objectives[13] = 0.2638630134408464;
		Objectives[14] = 0.4939730441173949;
		Objectives[15] = 0.3389719641556545;
		Objectives[16] = 1;
		Objectives[17] = 0.4601818107607662;
		Objectives[18] = 1;
		Objectives[19] = 0.2070798409636201;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 12;
		ranks[2] = 19;
		ranks[3] = 16;
		ranks[4] = 8;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 11;
		ranks[8] = 7;
		ranks[9] = 6;
		ranks[10] = 15;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 17;
		ranks[14] = 9;
		ranks[15] = 14;
		ranks[16] = 1;
		ranks[17] = 10;
		ranks[18] = 1;
		ranks[19] = 18;
		
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
		refSet.add(new NonZeroLambda(6, 0.45389563426871143));
		refSet.add(new NonZeroLambda(11, 0.1814837920184306));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5012046138367214));
		refSet.add(new NonZeroLambda(6, 0.37557620277414655));
		refSet.add(new NonZeroLambda(11, 0.18062137613361373));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.5213064189239172));
		referenceSets[10] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.14842196337893057));
		refSet.add(new NonZeroLambda(11, 0.8756787544797552));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [1] = 55.2814039460805;
		slackValues[10] [1] = 34.76422770241286;
		slackValues[10] [3] = 10.64529572318176;
		slackValues[17] [2] = 691.0201118459736;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 155.60891695644722;
		projectionValues[0] [1] = 49.10718884990946;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 97.69806659514718;
		projectionValues[3] [1] = 27.718596053919498;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 151.94;
		
		projectionValues[10] [0] = 108.90612397739538;
		projectionValues[10] [1] = 30.23577229758714;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 21.90529572318176;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0024320840528248653;
		weightValues[0] [1] = 0;
		weightValues[0] [2] = 1.8760659962299453E-4;
		weightValues[0] [3] = 1.953705109519351E-5;
		
		weightValues[3] [0] = 0.002770083102493075;
		weightValues[3] [1] = 0;
		weightValues[3] [2] = 2.1367923979774694E-4;
		weightValues[3] [3] = 2.225221412412952E-5;
		
		weightValues[10] [0] = 0.0026031565876782187;
		weightValues[10] [1] = 0;
		weightValues[10] [2] = 2.011278025400005E-4;
		weightValues[10] [3] = 0;
		
		weightValues[14] [0] = 0.0030557677616501163;
		weightValues[14] [1] = 0.037278961525586345;
		weightValues[14] [2] = 0;
		weightValues[14] [3] = 0.0010927857282834158;
		
		return weightValues;
	}
	
	
	@Test
	public void testNDI() {
		
		buildDEAProblem(ModelType.ND_I); //, DEAModelOrientation.NonOriented);
		
		
		
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
			l.add(3);
			l.add(10);
			l.add(17);
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
