package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;
import org.opensourcedea.exception.InvalidPropertyValueException;

//import dea.DEAModelOrientation;




public class LibraryTestSBMGRSTest {

	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/

	DEAProblem tester = new DEAProblem(20, 4);

	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {

		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
		try {
			tester.setRTSLowerBound(0.8);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		try {
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
	}



	private double[] createDEAModelObjectives() {

		double[] Objectives = new double[20];


		Objectives[0] = 0.433416455031194;
		Objectives[1] = 0.655740942924697;
		Objectives[2] = 0.202694119860539;
		Objectives[3] = 0.0694880306719787;
		Objectives[4] = 0.631545023036202;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.717802718554146;
		Objectives[8] = 0.478929894370442;
		Objectives[9] = 0.143243925812365;
		Objectives[10] = 0.00643080773298032;
		Objectives[11] = 1;
		Objectives[12] = 0.140393744400076;
		Objectives[13] = 0.0198346435765732;
		Objectives[14] = 0.40792722440984;
		Objectives[15] = 0.472090161717494;
		Objectives[16] = 1;
		Objectives[17] = 0.0866211335899282;
		Objectives[18] = 1;
		Objectives[19] = 0.183568479036878;
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

		ranks[0] = 11;
		ranks[1] = 7;
		ranks[2] = 13;
		ranks[3] = 18;
		ranks[4] = 8;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 6;
		ranks[8] = 9;
		ranks[9] = 15;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 12;
		ranks[15] = 10;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 14;

		return ranks;
	}


	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {

		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];

		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.976470588235294));
		referenceSets[0] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1.0120481927710843));
		referenceSets[1] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1.0000000000000002));
		referenceSets[11] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.4698102995192672));
		refSet.add(new NonZeroLambda(18, 0.5028725699504085));
		referenceSets[17] = refSet;

		return referenceSets;
	}

	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];

		slackValues[1] [0] = 501.05542168674725;
		slackValues[8] [1] = 0.0;
		slackValues[9] [1] = 39.05002016942316;
		slackValues[10] [3] = 2258.2700881885066;
		slackValues[12] [0] = 921.192491223518;
		slackValues[13] [3] = 2108.1820195523615;
		slackValues[14] [2] = 1434.78730181681;
		slackValues[17] [2] = 1754.9278579248487;
		slackValues[19] [1] = 10.007795494811441;

		return slackValues;
	}

	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 253.19882352941178;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 2982.727058822905;
		projectionValues[0] [3] = 2877.6881176470347;

		projectionValues[3] [0] = 144.77400531812089;
		projectionValues[3] [1] = 55.244849752188344;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 2180.328842147046;

		projectionValues[10] [0] = 155.71844872993432;
		projectionValues[10] [1] = 58.32028345726889;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 2269.530088188507;

		return projectionValues;
	}

	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0012160420264121093;
		weightValues[0] [1] = 0.006024096385543769;
		weightValues[0] [2] = 1.1544349903340183E-4;
		weightValues[0] [3] = 1.6108902117463553E-4;

		weightValues[3] [0] = 0.0013850415512460942;
		weightValues[3] [1] = 0.00602409638553906;
		weightValues[3] [2] = 8.365418247282566E-5;
		weightValues[3] [3] = 2.286693124653426E-4;

		return weightValues;
	}


	@Test
	public void testSBM_GRS() {



		buildDEAProblem(ModelType.SBM_GRS);

		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();



		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.SBM_GRS);

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

			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
			//EFFICIENCIES
			for(int i = 0 ; i < 20; i++) {
				assertTrue(tester.getEfficiencyStatus(i) == createEfficiencyValues()[i]);
			}
			
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
			l.add(1);
			l.add(8);
			l.add(9);
			l.add(10);
			l.add(12);
			l.add(13);
			l.add(14);
			l.add(17);
			l.add(19);
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
