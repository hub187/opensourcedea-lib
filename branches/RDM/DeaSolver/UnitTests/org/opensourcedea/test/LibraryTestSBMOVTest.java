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




public class LibraryTestSBMOVTest {
	
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
		
		
		Objectives[0] = 0.474879271928326;
		Objectives[1] = 0.87861716340644;
		Objectives[2] = 1;
		Objectives[3] = 0.0868841360674911;
		Objectives[4] = 0.697298191554183;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.861259537998267;
		Objectives[8] = 0.488504526738576;
		Objectives[9] = 0.195229627696065;
		Objectives[10] = 0.0082326586135507;
		Objectives[11] = 1;
		Objectives[12] = 0.235266808629956;
		Objectives[13] = 0.0257755689617094;
		Objectives[14] = 0.427059536559875;
		Objectives[15] = 0.509184998979921;
		Objectives[16] = 1;
		Objectives[17] = 0.0969837757380851;
		Objectives[18] = 1;
		Objectives[19] = 1;
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
		efficiencies[19] = true;
		return efficiencies;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 8;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 10;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 9;
		ranks[8] = 12;
		ranks[9] = 16;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 15;
		ranks[13] = 19;
		ranks[14] = 14;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 17;
		ranks[18] = 1;
		ranks[19] = 1;		
		
		return ranks;
	}

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.5913084277951038));
		refSet.add(new NonZeroLambda(6, 0.3988435825132118));
		refSet.add(new NonZeroLambda(18, 0.009847989691684149));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 1.0));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1.0));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.46913580246896797));
		refSet.add(new NonZeroLambda(18, 0.5308641975310321));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 2.0220265679824038E-8;
		SlacksArray[8] [2] = 1344.2496564856106;
		SlacksArray[9] [1] = 40.85518920944173;
		SlacksArray[10] [3] = 2702.7792573874776;
		SlacksArray[12] [3] = 2921.8700000010244;
		SlacksArray[13] [3] = 2859.2626912984233;
		SlacksArray[14] [2] = 1354.5481538354818;
		SlacksArray[17] [2] = 1756.5101234566616;
		SlacksArray[19] [1] = 0;
		
		return SlacksArray;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 411.17;
		projectionValues[0] [1] = 83.0;
		projectionValues[0] [2] = 3453.907420806783;
		projectionValues[0] [3] = 3190.5099567695806;

		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83.0;
		projectionValues[3] [2] = 3291.5955186222027;
		projectionValues[3] [3] = 3097.65857736448;
		
		projectionValues[10] [0] = 384.149;
		projectionValues[10] [1] = 65.0;
		projectionValues[10] [2] = 2681.2776009222052;
		projectionValues[10] [3] = 2714.039257387478;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.0015436420591269844;
		weightValues[0] [1] = 0.018789885538884162;
		weightValues[0] [2] = 2.6635698228173824E-4;
		weightValues[0] [3] = 3.716726010390625E-4;
		
		weightValues[3] [0] = 0.0075596211054060935;
		weightValues[3] [1] = 0.08919233378090376;
		weightValues[3] [2] = 3.997729289765626E-4;
		weightValues[3] [3] = 0.003290772673421875;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMOV() {
				
		
		buildDEAProblem(ModelType.SBM_O_V);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		checkData();
		
		
		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.SBM_O_V);
		
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
