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




public class LibraryTestDRSOTest {

	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/

	DEAProblem tester = new DEAProblem(20, 4);


	public void buildDEAProblem(ModelType ModelType) {


		tester.setModelType(ModelType);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());


	}


	private double[] createDEAModelObjectives() {

		double[] Objectives = new double[20];


		Objectives[0] = 0.540126501539801;
		Objectives[1] = 0.93354925149474;
		Objectives[2] = 1;
		Objectives[3] = 0.375433203387031;
		Objectives[4] = 0.803996071700928;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.865788985503832;
		Objectives[8] = 0.562736097594507;
		Objectives[9] = 0.567189831743521;
		Objectives[10] = 0.469111868112587;
		Objectives[11] = 1;
		Objectives[12] = 0.737946888943664;
		Objectives[13] = 0.367928609122649;
		Objectives[14] = 0.954489410345333;
		Objectives[15] = 0.658038271209613;
		Objectives[16] = 1;
		Objectives[17] = 0.991088109520716;
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
		ranks[3] = 18;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 10;
		ranks[8] = 15;
		ranks[9] = 14;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 12;
		ranks[13] = 19;
		ranks[14] = 8;
		ranks[15] = 13;
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
		refSet.add(new NonZeroLambda(5, 0.5960127451425468));
		refSet.add(new NonZeroLambda(6, 0.3740622730161569));
		refSet.add(new NonZeroLambda(11, 0.02992498184129616));
		referenceSets[0] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(2, 0.015384615384615717));
		refSet.add(new NonZeroLambda(5, 0.9846153846153832));
		referenceSets[1] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 0.9999999999999998));
		referenceSets[11] = refSet;

		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.4691358024691351));
		refSet.add(new NonZeroLambda(18, 0.5308641975308633));
		referenceSets[17] = refSet;

		return referenceSets;
	}

	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];

		SlacksArray[2] [3] = 7.686521357201955E-7;
		SlacksArray[8] [1] = 2.054247299292464;
		SlacksArray[9] [1] = 51.94181048433048;
		SlacksArray[10] [3] = 957.1511964786159;
		SlacksArray[12] [3] = 2018.5378679630871;
		SlacksArray[13] [3] = 2042.8089839973368;
		SlacksArray[14] [2] = 1255.9205125782103;
		SlacksArray[17] [2] = 1755.6615459227132;
		SlacksArray[19] [1] = 26.00265680881663;

		return SlacksArray;
	}

	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3475.445094156343;
		projectionValues[0] [3] = 3127.731191579121;

		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3331.378228445569;
		projectionValues[3] [3] = 3000.087709847526;

		projectionValues[10] [0] = 295.0744000000004;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 3004.720399998104;
		projectionValues[10] [3] = 981.1540000002921;

		return projectionValues;
	}

	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015297275502017802;
		weightValues[0] [1] = 0.0040648322369322785;
		weightValues[0] [2] = 5.327139645638672E-4;
		weightValues[0] [3] = 0;

		weightValues[3] [0] = 0.0022959550676699067;
		weightValues[3] [1] = 0.006100872127446207;
		weightValues[3] [2] = 7.995458579520284E-4;
		weightValues[3] [3] = 0;

		return weightValues;
	}


	@Test
	public void testDRSO() {



		buildDEAProblem(ModelType.DRS_O); //, DEAModelOrientation.NonOriented);

		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		checkData();



		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.DRS_O); //, DEAModelOrientation.NonOriented);

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

			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 7), createSolRanks());

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
