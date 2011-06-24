package tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

import dea.*;




public class LibraryTestNDIV {
	
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
		
		
		Objectives[0] = 0.41117996911400906;
		Objectives[1] = 0.41810203479741914;
		Objectives[2] = 1;
		Objectives[3] = 0.3441117317897633;
		Objectives[4] = 0.5052233413353793;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.45921877738433436;
		Objectives[8] = 0.5973524274727394;
		Objectives[9] = 0.869147014001678;
		Objectives[10] = 0.34667610892555434;
		Objectives[11] = 1;
		Objectives[12] = 0.19226490636341428;
		Objectives[13] = 0.34461011921684087;
		Objectives[14] = 0.7740510377617102;
		Objectives[15] = 0.5234978417587581;
		Objectives[16] = 1;
		Objectives[17] = 0.766282655967783;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 16;
		ranks[1] = 15;
		ranks[2] = 1;
		ranks[3] = 19;
		ranks[4] = 13;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 14;
		ranks[8] = 11;
		ranks[9] = 8;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 18;
		ranks[14] = 9;
		ranks[15] = 12;
		ranks[16] = 1;
		ranks[17] = 10;
		ranks[18] = 1;
		ranks[19] = 1;
		
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

		
	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.1984958715779536));
		refSet.add(new NonZeroLambda(11, 0.34731425080740863));
		refSet.add(new NonZeroLambda(16, 0.4541898776146334));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.6559533514796543));
		refSet.add(new NonZeroLambda(6, 0.34404664852034156));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.49259869065819895));
		refSet.add(new NonZeroLambda(19, 0.507401309341801));
		referenceSets[10] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.3342267335766408));
		refSet.add(new NonZeroLambda(11, 0.6657732664233595));
		referenceSets[12] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [1] = 33.405444418829234;
		slackValues[10] [1] = 14.103618330785208;
		slackValues[10] [3] = 44.287318907052416;
		slackValues[14] [2] = 967.1714096764351;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 169.0648679006071;
		projectionValues[0] [1] = 55.18397073554188;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 124.22433517610455;
		projectionValues[3] [1] = 49.594555581170766;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 151.94;
		
		projectionValues[14] [0] = 253.3082021075197;
		projectionValues[14] [1] = 43.0;
		projectionValues[14] [2] = 1472.001409676435;
		projectionValues[14] [3] = 1918.92;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [5];
		weightValues[0] [1] = 0.0024320840528248657;
		weightValues[0] [2] = 0;
		weightValues[0] [3] = 1.543498359016649E-4;
		weightValues[0] [4] = 2.355211891676422E-5;
		
		weightValues[3] [1] = 0.002770083102493074;
		weightValues[3] [2] = 0;
		weightValues[3] [3] = 1.621898774049462E-4;
		weightValues[3] [4] = 1.0035798730719163E-5;
		
		weightValues[10] [1] = 0.0026031565876782187;
		weightValues[10] [2] = 0;
		weightValues[10] [3] = 1.5231767308669525E-4;
		weightValues[10] [4] = 0;
		
		weightValues[14] [1] = 0.0030557677616501197;
		weightValues[14] [2] = 0.07107917928258242;
		weightValues[14] [3] = 0;
		weightValues[14] [4] = 0.0024695176454103953;
		
		return weightValues;
	}
	
	
	@Test
	public void TestNDIV() {
		
		BuildDEAProblem(ModelType.ND_I_V); //, DEAModelOrientation.NonOriented);
		
		
		
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
