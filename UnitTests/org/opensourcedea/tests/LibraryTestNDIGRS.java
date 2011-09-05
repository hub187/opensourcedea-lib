package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNDIGRS {
	
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
		
		
		Objectives[0] = 0.39322925208581716;
		Objectives[1] = 0.3848570911399463;
		Objectives[2] = 0.26195215973894403;
		Objectives[3] = 0.31616485359746777;
		Objectives[4] = 0.5004759922450752;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.4517615843106065;
		Objectives[8] = 0.5696342499134717;
		Objectives[9] = 0.780578643995369;
		Objectives[10] = 0.3202807623603137;
		Objectives[11] = 1;
		Objectives[12] = 0.18857905048019372;
		Objectives[13] = 0.3155838518258622;
		Objectives[14] = 0.5923808271180819;
		Objectives[15] = 0.3908868002704586;
		Objectives[16] = 1;
		Objectives[17] = 0.636052044517598;
		Objectives[18] = 1;
		Objectives[19] = 0.8308663365059732;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 15;
		ranks[2] = 19;
		ranks[3] = 17;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 12;
		ranks[8] = 10;
		ranks[9] = 7;
		ranks[10] = 16;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 18;
		ranks[14] = 9;
		ranks[15] = 14;
		ranks[16] = 1;
		ranks[17] = 8;
		ranks[18] = 1;
		ranks[19] = 6;
		
		return ranks;
	}
	
	public void BuildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
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
		} catch (InvalidPropertyValue e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.33858657468341874));
		refSet.add(new NonZeroLambda(11, 0.25635369029906535));
		refSet.add(new NonZeroLambda(16, 0.20505973501751043));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5012046138367229));
		refSet.add(new NonZeroLambda(6, 0.37557620277414533));
		refSet.add(new NonZeroLambda(11, 0.1806213761336134));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.5045928887843505));
		refSet.add(new NonZeroLambda(19, 0.29540711121564955));
		referenceSets[10] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.14842196337893));
		refSet.add(new NonZeroLambda(11, 0.8756787544797539));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [1] = 42.10133658959507;
		slackValues[10] [1] = 22.735699557019075;
		slackValues[10] [3] = 30.231553585009234;
		slackValues[14] [2] = 539.3903504697425;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 161.68407158012545;
		projectionValues[0] [1] = 51.85076228613709;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 114.13551214868586;
		projectionValues[3] [1] = 40.89866341040493;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 151.94;
		
		projectionValues[14] [0] = 193.85662567439232;
		projectionValues[14] [1] = 43.0;
		projectionValues[14] [2] = 1044.2203504697425;
		projectionValues[14] [3] = 1918.92;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [6];
		weightValues[0] [2] = 0.002432084052824866;
		weightValues[0] [3] = 0;
		weightValues[0] [4] = 1.5434983590166507E-4;
		weightValues[0] [5] = 2.3552118916764417E-5;
		
		weightValues[3] [2] = 0.002770083102493074;
		weightValues[3] [3] = 0;
		weightValues[3] [4] = 1.621898774049462E-4;
		weightValues[3] [5] = 1.0035798730719161E-5;
		
		weightValues[10] [2] = 0.0026031565876782187;
		weightValues[10] [3] = 0;
		weightValues[10] [4] = 1.5231767308669525E-4;
		weightValues[10] [5] = 0;
		
		weightValues[14] [2] = 0.0030557677616501167;
		weightValues[14] [3] = 0.0710791792825824;
		weightValues[14] [4] = 0;
		weightValues[14] [5] = 0.002469517645410395;
		
		return weightValues;
	}
	
	
	@Test
	public void TestNDIGRS() {
		
		BuildDEAProblem(ModelType.ND_I_GRS); //, DEAModelOrientation.NonOriented);
		
		
		
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
			l.add(14);
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
