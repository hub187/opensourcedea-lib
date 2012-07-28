package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

//import dea.DEAModelOrientation;




public class LibraryTestCCRITest {
	
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
		
		ranks[0] = 16;
		ranks[1] = 6;
		ranks[2] = 14;
		ranks[3] = 19;
		ranks[4] = 9;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 7;
		ranks[8] = 15;
		ranks[9] = 12;
		ranks[10] = 17;
		ranks[11] = 1;
		ranks[12] = 13;
		ranks[13] = 18;
		ranks[14] = 8;
		ranks[15] = 11;
		ranks[16] = 1;
		ranks[17] = 10;
		ranks[18] = 1;
		ranks[19] = 20;
		
		return ranks;
	}

	private double[] createDEAModelObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.519434453047034;
		Objectives[1] = 0.872474445374321;
		Objectives[2] = 0.549048143682244;
		Objectives[3] = 0.322936931532726;
		Objectives[4] = 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.830850567998129;
		Objectives[8] = 0.539682742929534;
		Objectives[9] = 0.567189831743522;
		Objectives[10] = 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 0.564403802355244;
		Objectives[13] = 0.338735130146885;
		Objectives[14] = 0.805598209487005;
		Objectives[15] = 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 0.739084603920947;
		Objectives[18] = 1;
		Objectives[19] = 0.207079840963623;
		return Objectives;
	}
	
	

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.2928997131064447));
		refSet.add(new NonZeroLambda(6, 0.11677214628697842));
		refSet.add(new NonZeroLambda(11, 0.15304743070117197));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.28653521143162586));
		refSet.add(new NonZeroLambda(11, 0.7654346275520094));
		refSet.add(new NonZeroLambda(18, 2.555111232297472));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(16, 0.6801710356252553));
		refSet.add(new NonZeroLambda(18, 1.1782713659082373));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		
		slackValues[2] [3] = 809.7943541461939;
		slackValues[8] [1] = 1.4451552285174623;
		slackValues[9] [1] = 29.46086674906089;
		
		slackValues[10] [3] = 230.19991584427473;
		slackValues[12] [3] = 1013.092727850814;
		slackValues[13] [3] = 65.34994950039902;
		slackValues[14] [2] = 215.80784619923003;
		slackValues[17] [2] = 576.3316367292407;
		slackValues[19] [1] = 5.384626036601417;

		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 213.57586405931025;
		projectionValues[0] [1] = 43.113059602896;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 116.5802322833114;
		projectionValues[3] [1] = 26.803765317215642;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 151.94;
		
		projectionValues[10] [0] = 176.5527228897552;
		projectionValues[10] [1] = 29.87363493809456;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 241.45991584427472;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 8.238375939425327E-4;
		weightValues[0] [1] = 0.00796702043974276;
		weightValues[0] [2] = 2.3361549984873023E-4;
		weightValues[0] [3] = 6.013373452236329E-5;
		
		weightValues[3] [0] = 1.0747669873946357E-4;
		weightValues[3] [1] = 0.011580733876566912;
		weightValues[3] [2] = 2.5650054592859264E-4;
		weightValues[3] [3] = 1.4012990222248727E-5;
		
		return weightValues;
	}
	
	
	@Test
	public void testCCRI() {
				
		
		
		buildDEAProblem(ModelType.CCR_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		checkData();
		
		
		
		tester = new DEAProblem(20, 4);
		buildDEAProblem(ModelType.CCR_I); //, DEAModelOrientation.NonOriented);
		
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
			l.add(2);
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
