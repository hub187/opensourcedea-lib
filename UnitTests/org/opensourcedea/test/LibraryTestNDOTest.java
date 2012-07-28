package org.opensourcedea.test;


import static org.junit.Assert.*;

import org.junit.Test;
import org.opensourcedea.dea.*;

import java.util.ArrayList;





public class LibraryTestNDOTest {

	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/

	DEAProblem tester = new DEAProblem(20, 4);

	public void buildDEAProblem(ModelType ModelType) { 

		tester.setModelType(ModelType);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setVariableTypes(TestData.createTestVariableTypes(VariableType.NON_DISCRETIONARY));
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}


	private double[] createDEAModelObjectives() {

		double[] Objectives = new double[20];


		Objectives[0] = 0.34736491268774067;
		Objectives[1] = 0.5561237719119163;
		Objectives[2] = 0.1109440586938961;
		Objectives[3] = 0.039558387205049186;
		Objectives[4] = 0.5633631520711179;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.6933833944262365;
		Objectives[8] = 0.3485593763286176;
		Objectives[9] = 0.1027914730105932;
		Objectives[10] = 0.0034701633998842686;
		Objectives[11] = 1;
		Objectives[12] = 0.07190699143014578;
		Objectives[13] = 0.010909772950728187;
		Objectives[14] = 0.8055982094866486;
		Objectives[15] = 0.349967423680898;
		Objectives[16] = 1;
		Objectives[17] = 0.7390846039210686;
		Objectives[18] = 1;
		Objectives[19] = 0.06953895653782798;
		return Objectives;
	}

	private boolean[] createEfficiencyValues() {

		boolean[] efficiencies = new boolean[20];


		efficiencies[0] = false;
		efficiencies[1] = false;
		efficiencies[2] = false;
		efficiencies[3] = false;
		efficiencies[4] = false;
		efficiencies[5] = true;
		efficiencies[6] = true;
		efficiencies[7] = false;
		efficiencies[8] = false;
		efficiencies[9] = false;
		efficiencies[10] = false;
		efficiencies[11] = true;
		efficiencies[12] = false;
		efficiencies[13] = false;
		efficiencies[14] = false;
		efficiencies[15] = false;
		efficiencies[16] = true;
		efficiencies[17] = false;
		efficiencies[18] = true;
		efficiencies[19] = false;
		return efficiencies;
	}

	private int[] createSolRanks() {
		int[] ranks = new int[20];

		ranks[0] = 13;
		ranks[1] = 10;
		ranks[2] = 14;
		ranks[3] = 18;
		ranks[4] = 9;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 8;
		ranks[8] = 12;
		ranks[9] = 15;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 6;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 7;
		ranks[18] = 1;
		ranks[19] = 17;

		return ranks;
	}



	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {

		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];

		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.12318538769513686));
		refSet.add(new NonZeroLambda(16, 1.7821127684435683));
		refSet.add(new NonZeroLambda(18, 0.7455510417804561));
		referenceSets[0] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.7546577232730912));
		refSet.add(new NonZeroLambda(16, 0.4044692174326456));
		refSet.add(new NonZeroLambda(18, 2.5923201355138095));
		referenceSets[1] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.05316281840194715));
		refSet.add(new NonZeroLambda(16, 1.4686276512255685));
		refSet.add(new NonZeroLambda(18, 0.9211900118078246));
		referenceSets[10] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.230013865449835));
		refSet.add(new NonZeroLambda(16, 1.9087143054373188));
		refSet.add(new NonZeroLambda(18, 3.7801384750854727));
		referenceSets[12] = refSet;

		return referenceSets;
	}

	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[3] [2] = 341.862528641279;
		slackValues[9] [1] = 39.05002016942315;
		slackValues[9] [2] = 21.712581686163997;
		slackValues[17] [2] = 813.1061308397875;
		return slackValues;
	}

	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 3872.786084210277;

		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 1592.572528641279;
		projectionValues[3] [3] = 3840.904817793142;

		projectionValues[9] [0] = 113.91;
		projectionValues[9] [1] = 45.94997983057685;
		projectionValues[9] [2] = 839.092581686164;
		projectionValues[9] [3] = 1886.0513846320762;

		return projectionValues;
	}

	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0022323211247707434;
		weightValues[0] [1] = 0.03064253904208537;
		weightValues[0] [2] = 3.102405006399997E-4;
		weightValues[0] [3] = 7.433452020778273E-4;

		weightValues[3] [0] = 0.018404041681919496;
		weightValues[3] [1] = 0.22452084559105345;
		weightValues[3] [2] = 0;
		weightValues[3] [3] = 0.0065815453468474425;

		weightValues[10] [0] = 0.266702898714265;
		weightValues[10] [1] = 3.660967006854135;
		weightValues[10] [2] = 0.03706547409380905;
		weightValues[10] [3] = 0.08880994671403196;

		weightValues[14] [0] = 0.0014572311993990622;
		weightValues[14] [1] = 0.017777550538378175;
		weightValues[14] [2] = 0.;
		weightValues[14] [3] = 5.211264669710046E-4;

		return weightValues;
	}


	@Test
	public void testNDO() {

		buildDEAProblem(ModelType.ND_O); //, DEAModelOrientation.NonOriented);

		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();



		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.ND_O); //, DEAModelOrientation.NonOriented);

		try {
			for(int i = 0; i < tester.getNumberOfDMUs(); i++) {
				tester.solveOne(i);
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();


	}


	private void checkData() {
		try {

			//OBJECTIVES
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);

			//EFFICIENCIES
			for(int i = 0 ; i < 20; i++) {
				assertTrue(tester.getEfficiencyStatus(i) == createEfficiencyValues()[i]);
			}

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
			l.add(9);
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
			l.add(9);
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
