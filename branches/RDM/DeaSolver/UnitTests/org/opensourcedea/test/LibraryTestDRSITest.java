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

//import dea.DEAModelOrientation;




public class LibraryTestDRSITest {

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

	}

	private double[] createDEAModelObjectives() {

		double[] Objectives = new double[20];


		Objectives[0] = 0.519434453047034;
		Objectives[1] = 0.920072185311782;
		Objectives[2] = 1;
		Objectives[3] = 0.322936931532726;
		Objectives[4] = 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.844109331376676;
		Objectives[8] = 0.539682742929534;
		Objectives[9] = 0.567189831743522;
		Objectives[10] = 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 0.589846230572703;
		Objectives[13] = 0.338735130146885;
		Objectives[14] = 0.944298220114217;
		Objectives[15] = 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 0.986772936684553;
		Objectives[18] = 1;
		Objectives[19] = 0.207079840963623;
		return Objectives;
	}


	private boolean[] createEfficiencyValues() {

		boolean[] efficiencies = new boolean[20];


		efficiencies[0] = false;
		efficiencies[1] = false;
		efficiencies[2] = true;
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

		ranks[0] = 16;
		ranks[1] = 9;
		ranks[2] = 1;
		ranks[3] = 19;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 15;
		ranks[9] = 14;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 13;
		ranks[13] = 18;
		ranks[14] = 8;
		ranks[15] = 12;
		ranks[16] = 1;
		ranks[17] = 7;
		ranks[18] = 1;
		ranks[19] = 20;

		return ranks;
	}



	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {

		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];

		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.29289971310644924));
		refSet.add(new NonZeroLambda(6, 0.11677214628697319));
		refSet.add(new NonZeroLambda(11, 0.15304743070117155));
		referenceSets[0] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.8226707583517153));
		refSet.add(new NonZeroLambda(11, 0.15445950249431953));
		refSet.add(new NonZeroLambda(18, 0.0228697391539411));
		referenceSets[1] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.46260391935025913));
		refSet.add(new NonZeroLambda(18, 0.5373960806495937));
		referenceSets[17] = refSet;

		return referenceSets;
	}

	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];

		slackValues[1] [0] = 479.54787078573077;
		slackValues[8] [1] = 1.4451552285174922;
		slackValues[9] [1] = 29.46086674906213;
		slackValues[10] [3] = 230.1999158442828;
		slackValues[12] [0] = 444.33179074762575;
		slackValues[13] [3] = 65.34994950039146;
		slackValues[14] [2] = 1131.4822839270616;
		slackValues[17] [2] = 1732.7945541424192;
		slackValues[19] [1] = 5.384626036601374;

		return slackValues;
	}

	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 213.57586405930996;
		projectionValues[0] [1] = 43.11305960289595;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;

		projectionValues[3] [0] = 116.58023228331146;
		projectionValues[3] [1] = 26.803765317215657;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 151.94;

		projectionValues[10] [0] = 176.55272288975505;
		projectionValues[10] [1] = 29.873634938094536;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 241.4599158442828;

		return projectionValues;
	}

	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 8.238375939425325E-4;
		weightValues[0] [1] = 0.007967020439742756;
		weightValues[0] [2] = 2.336154998487302E-4;
		weightValues[0] [3] = 6.0133734522363256E-5;

		weightValues[3] [0] = 1.074766987394631E-4;
		weightValues[3] [1] = 0.01158073387656691;
		weightValues[3] [2] = 2.565005459285927E-4;
		weightValues[3] [3] = 1.4012990222248573E-5;

		return weightValues;
	}


	@Test
	public void testDRSI() {



		buildDEAProblem(ModelType.DRS_I); //, DEAModelOrientation.NonOriented);

		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();



		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.DRS_I); //, DEAModelOrientation.NonOriented);

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
