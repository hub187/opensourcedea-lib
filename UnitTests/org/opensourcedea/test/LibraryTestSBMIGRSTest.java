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




public class LibraryTestSBMIGRSTest {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public void buildDEAProblem(ModelType ModelType) {
		
		tester.setModelType(ModelType);
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
		
		
		Objectives[0] = 0.508968615718462;
		Objectives[1] = 0.684214621325777;
		Objectives[2] = 0.43319047834667;
		Objectives[3] = 0.390585346275588;
		Objectives[4] = 0.717138904136575;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.725880792155402;
		Objectives[8] = 0.567052858191271;
		Objectives[9] = 0.605827713099746;
		Objectives[10] = 0.455225917740534;
		Objectives[11] = 1;
		Objectives[12] = 0.400138842637063;
		Objectives[13] = 0.412043296443192;
		Objectives[14] = 0.796190413560248;
		Objectives[15] = 0.666196273270143;
		Objectives[16] = 1;
		Objectives[17] = 0.818026022259435;
		Objectives[18] = 1;
		Objectives[19] = 0.815138908266967;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 15;
		ranks[1] = 11;
		ranks[2] = 17;
		ranks[3] = 20;
		ranks[4] = 10;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 9;
		ranks[8] = 14;
		ranks[9] = 13;
		ranks[10] = 16;
		ranks[11] = 1;
		ranks[12] = 19;
		ranks[13] = 18;
		ranks[14] = 8;
		ranks[15] = 12;
		ranks[16] = 1;
		ranks[17] = 6;
		ranks[18] = 1;
		ranks[19] = 7;
		
		return ranks;
	}

	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.338586574683416));
		refSet.add(new NonZeroLambda(11, 0.25635369029906757));
		refSet.add(new NonZeroLambda(16, 0.2050597350175162));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.8275128242219797));
		refSet.add(new NonZeroLambda(11, 0.14894152888183068));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(11, 1));
		referenceSets[11] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.2551052375793562));
		refSet.add(new NonZeroLambda(16, 0.4456967304229512));
		refSet.add(new NonZeroLambda(18, 0.4991980319976926));
		referenceSets[17] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		
		slackValues[1] [0] = 565.2783996952529;
		slackValues[8] [1] = 37.89098241719666;
		slackValues[9] [1] = 50.874521647478595;
		slackValues[10] [3] = 218.93722683868543;
		slackValues[12] [0] = 927.2256121963642;
		slackValues[13] [3] = 783.1670906037149;
		slackValues[14] [2] = 539.3903504697458;
		slackValues[17] [2] = 1268.7180435507587;
		slackValues[19] [1] = 9.011935025802655;

		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[0] [0] = 161.68407158012494;
		projectionValues[0] [1] = 51.85076228613691;
		projectionValues[0] [2] = 1877.18;
		projectionValues[0] [3] = 1345.27;
		
		projectionValues[3] [0] = 116.38653256242912;
		projectionValues[3] [1] = 38.077937003405225;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 773.2670890328232;
		
		projectionValues[10] [0] = 173.08259502173067;
		projectionValues[10] [1] = 29.892211820945036;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 230.19722683868542;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [0] = 0.001216042026412109;
		weightValues[0] [1] = 0.006024096385539061;
		weightValues[0] [2] = 1.6856606344169787E-4;
		weightValues[0] [3] = 5.673215250554426E-5;
		
		weightValues[3] [0] = 0.001385041551246094;
		weightValues[3] [1] = 0.006024096385539064;
		weightValues[3] [2] = 1.3197363243319394E-4;
		weightValues[3] [3] = 0;
		
		return weightValues;
	}
	
	
	@Test
	public void testSBMIGRS() {
				
		
		
		buildDEAProblem(ModelType.SBM_I_GRS); //, DEAModelOrientation.NonOriented);
		
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
