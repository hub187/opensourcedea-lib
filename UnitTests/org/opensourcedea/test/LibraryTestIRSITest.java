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




public class LibraryTestIRSITest {
	
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
		
		
		Objectives[0] = 0.567313572258633;
		Objectives[1] = 0.872474445374321;
		Objectives[2] = 0.549048143682244;
		Objectives[3] = 0.453081726873951;
		Objectives[4] = 0.784594194952889;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.830850567998129;
		Objectives[8] = 0.601271371703667;
		Objectives[9] = 0.869147014001681;
		Objectives[10] = 0.518227961399705;
		Objectives[11] = 1;
		Objectives[12] = 0.564403802355244;
		Objectives[13] = 0.476989923125339;
		Objectives[14] = 0.805598209487005;
		Objectives[15] = 0.730169470543935;
		Objectives[16] = 1;
		Objectives[17] = 0.739084603920947;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 15;
		ranks[1] = 7;
		ranks[2] = 17;
		ranks[3] = 20;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 9;
		ranks[8] = 14;
		ranks[9] = 8;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 16;
		ranks[13] = 19;
		ranks[14] = 10;
		ranks[15] = 13;
		ranks[16] = 1;
		ranks[17] = 12;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	


	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.35925763536919986));
		refSet.add(new NonZeroLambda(11, 0.26573073609164544));
		refSet.add(new NonZeroLambda(16, 0.010203198439172882));
		refSet.add(new NonZeroLambda(18, 0.3648084300999731));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.2865352114316331));
		refSet.add(new NonZeroLambda(11, 0.7654346275520056));
		refSet.add(new NonZeroLambda(18, 2.5551112322974596));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(16, 0.6801710356252257));
		refSet.add(new NonZeroLambda(18, 1.1782713659083064));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		
		slackValues[1] [0] = 0.0;
		slackValues[8] [1] = 0.0;
		slackValues[9] [1] = 26.83096330525152;
		slackValues[10] [3] = 542.0875816317231;
		slackValues[12] [3] = 1013.0927278508085;
		slackValues[13] [3] = 867.6346308832921;
		slackValues[14] [2] = 215.80784619923102;
		slackValues[17] [2] = 576.3316367291786;
		slackValues[19] [1] = 0.0;

		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 233.26232150557283;
		projectionValues[0] [1] = 47.087026497464656;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 163.56250340149626;
		projectionValues[3] [1] = 37.605783330537925;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 913.0431110239376;
		
		projectionValues[10] [0] = 199.07421912864484;
		projectionValues[10] [1] = 33.68438872250589;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 553.3475816317231;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 9.352402077983287E-4;
		weightValues[0] [1] = 0.00741514799710315;
		weightValues[0] [2] = 1.7184879747594165E-4;
		weightValues[0] [3] = 6.439390561199953E-5;
		
		weightValues[3] [0] = 0.001293113196935107;
		weightValues[3] [1] = 0.006423929348270199;
		weightValues[3] [2] = 1.3050809836249994E-4;
		weightValues[3] [3] = 0;
		
		return weightValues;
	}
	
	
	@Test
	public void testIRSI() {
				
		
		
		buildDEAProblem(ModelType.IRS_I); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			
			assertArrayEquals(tester.getObjectives(), createDEAModelObjectives(),0.0001);
			
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
