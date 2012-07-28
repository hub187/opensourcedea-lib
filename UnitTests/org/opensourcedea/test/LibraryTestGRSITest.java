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




public class LibraryTestGRSITest {

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
		} catch (InvalidPropertyValueException e) {
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


		Objectives[0] = 0.545153233382534;
		Objectives[1] = 0.916420765303269;
		Objectives[2] = 0.576955373697832;
		Objectives[3] = 0.395110938239753;
		Objectives[4] = 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.840457911368163;
		Objectives[8] = 0.56963424991349;
		Objectives[9] = 0.780578643995372;
		Objectives[10] = 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 0.586868373478382;
		Objectives[13] = 0.414890646282276;
		Objectives[14] = 0.89951220302041;
		Objectives[15] = 0.681491797903215;
		Objectives[16] = 1;
		Objectives[17] = 0.889932140757229;
		Objectives[18] = 1;
		Objectives[19] = 0.830866336505974;
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

		ranks[0] = 17;
		ranks[1] = 6;
		ranks[2] = 15;
		ranks[3] = 20;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 9;
		ranks[8] = 16;
		ranks[9] = 12;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 14;
		ranks[13] = 19;
		ranks[14] = 7;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 8;
		ranks[18] = 1;
		ranks[19] = 10;

		return ranks;
	}



	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {

		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];

		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.13035660447794287));
		refSet.add(new NonZeroLambda(6, 0.25473170369939513));
		refSet.add(new NonZeroLambda(11, 0.21332325001755104));
		refSet.add(new NonZeroLambda(18, 0.20158844180512098));
		referenceSets[0] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.7815415773050643));
		refSet.add(new NonZeroLambda(11, 0.20132993792404402));
		refSet.add(new NonZeroLambda(18, 0.21712848477087057));
		referenceSets[1] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.4098430324725566));
		refSet.add(new NonZeroLambda(18, 0.7901569675274086));
		referenceSets[17] = refSet;

		return referenceSets;
	}

	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];

		slackValues[1] [0] = 442.75976503264894;
		slackValues[8] [1] = 0.44916215966878037;
		slackValues[9] [1] = 27.99854402548128;
		slackValues[10] [3] = 230.19991584427146;
		slackValues[12] [0] = 407.7859365052183;
		slackValues[13] [3] = 507.27606780686267;
		slackValues[14] [2] = 835.8124362101066;
		slackValues[17] [2] = 1570.7474917198738;
		slackValues[19] [1] = 1.2540109770285939;

		return slackValues;
	}

	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 224.15065496987538;
		projectionValues[0] [1] = 45.24771837074605;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;

		projectionValues[3] [0] = 142.63504870455043;
		projectionValues[3] [1] = 32.7942078738994;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 546.0566777108515;

		projectionValues[10] [0] = 176.55272288975516;
		projectionValues[10] [1] = 29.873634938094554;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 241.4599158442828;

		return projectionValues;
	}

	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 7.339959398765476E-4;
		weightValues[0] [1] = 0.008412083004830847;
		weightValues[0] [2] = 1.960236277451367E-4;
		weightValues[0] [3] = 6.725033298266409E-5;

		weightValues[3] [0] = 0.001293113196935107;
		weightValues[3] [1] = 0.006423929348270199;
		weightValues[3] [2] = 1.3050809836249994E-4;
		weightValues[3] [3] = 0;

		return weightValues;
	}


	@Test
	public void testGRSI() {



		buildDEAProblem(ModelType.GRS_I); //, DEAModelOrientation.NonOriented);

		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();



		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.GRS_I); //, DEAModelOrientation.NonOriented);

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
