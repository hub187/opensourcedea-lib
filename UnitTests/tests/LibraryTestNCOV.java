package tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
//import static java.util.ArrayList.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

//import dea.DEAModelOrientation;
import dea.*;




public class LibraryTestNCOV {
	
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
		
		
		Objectives[0] = 0.7834555804547183;
		Objectives[1] = 0.8720619693539163;
		Objectives[2] = 1;
		Objectives[3] = 0.16252779378635834;
		Objectives[4] = 0.701325815901633;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.958323171613544;
		Objectives[8] = 1;
		Objectives[9] = 1;
		Objectives[10] = 0.00528976501644403;
		Objectives[11] = 1;
		Objectives[12] = 0.24920687577012907;
		Objectives[13] = 0.029468239137107582;
		Objectives[14] = 0.9999985717201444;
		Objectives[15] = 0.37797528006451586;
		Objectives[16] = 1;
		Objectives[17] = 1;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 14;
		ranks[1] = 13;
		ranks[2] = 1;
		ranks[3] = 18;
		ranks[4] = 15;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 12;
		ranks[8] = 1;
		ranks[9] = 1;
		ranks[10] = 20;
		ranks[11] = 1;
		ranks[12] = 17;
		ranks[13] = 19;
		ranks[14] = 11;
		ranks[15] = 16;
		ranks[16] = 1;
		ranks[17] = 1;
		ranks[18] = 1;
		ranks[19] = 1;
		
		return ranks;
	}
	
	public void BuildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableTypes(TestData.createTestVariableTypes());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());

	}
	
	private ArrayList<NonZeroLambda>[] getTestReferenceSet() {
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(2, 0.08676180103337995));
		refSet.add(new NonZeroLambda(6, 0.20103617440083132));
		refSet.add(new NonZeroLambda(8, 0.5230446306459055));
		refSet.add(new NonZeroLambda(16, 0.18915739391988332));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(2, 0.004769177754682589));
		refSet.add(new NonZeroLambda(5, 0.6502290992725076));
		refSet.add(new NonZeroLambda(6, 0.34500172297280984));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 1.0000000000000002));
		referenceSets[6] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.24057256501532656));
		refSet.add(new NonZeroLambda(16, 0.3185225469260754));
		refSet.add(new NonZeroLambda(17, 0.14236224869914968));
		refSet.add(new NonZeroLambda(18, 0.29854263935944847));
		referenceSets[15] = refSet;
		
		return referenceSets;
	}
	
	private double[] [] getTestSlackValues() {
		double[] [] slackValues = new double[20] [4];
		slackValues[1] [0] = 591.1931744433991;
		slackValues[4] [0] = 207.38563128567984;
		slackValues[7] [0] = 373.0269226482977;
		return slackValues;
	}
	
	private double[] [] getTestProjectionValues() {
		double[] [] projectionValues = new double[20] [4];
		projectionValues[1] [0] = 432.72682555660083;
		projectionValues[1] [1] = 84.0;
		projectionValues[1] [2] = 3529.32;
		projectionValues[1] [3] = 3229.759006790188;
		
		projectionValues[3] [0] = 361.0;
		projectionValues[3] [1] = 83;
		projectionValues[3] [2] = 1250.71;
		projectionValues[3] [3] = 934.8554881617606;
		
		projectionValues[10] [0] = 384.149;
		projectionValues[10] [1] = 65;
		projectionValues[10] [2] = 1409.55;
		projectionValues[10] [3] = 2128.6389782904525;
		
		return projectionValues;
	}
	
	private double[] [] getTestWeightValues() {
		double[] [] weightValues = new double[20] [4];
		weightValues[0] [1] = 2.5610733103445874E-4;
		weightValues[0] [2] = 0;
		weightValues[0] [3] = 0;
		weightValues[0] [4] = 7.433452020778272E-4;
		
		weightValues[3] [1] = 0.0022451908515050824;
		weightValues[3] [2] = 0;
		weightValues[3] [3] = 0;
		weightValues[3] [4] = 0.00658154534684744;
		
		weightValues[10] [1] = 0.030598002595114342;
		weightValues[10] [2] = 0;
		weightValues[10] [3] = 0;
		weightValues[10] [4] = 0.08880994671403199;
		
		weightValues[14] [1] = 6.506695691624096E-4;
		weightValues[14] [2] = 0;
		weightValues[14] [3] = 0;
		weightValues[14] [4] = 5.211264669710045E-4;
		
		return weightValues;
	}
	
	
	@Test
	public void TestNCOV() {
		
		BuildDEAProblem(ModelType.NCOV); //, DEAModelOrientation.NonOriented);
		
		try {
			tester.setVariableType(1, VariableType.NON_CONTROLLABLE_INPUT);
			tester.setVariableType(2, VariableType.NON_CONTROLLABLE_OUTPUT);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
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
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 8), createSolRanks());
			
			//REFERENCE SET
			List<Integer> l = Arrays.asList(0, 1, 6, 12);
			for(Integer i : l){
				ArrayList<NonZeroLambda> refSet = getTestReferenceSet()[i];
				for(int nzlIndex = 0; nzlIndex < refSet.size();nzlIndex++) {
					assertEquals(refSet.indexOf(nzlIndex), tester.getReferenceSet(i).indexOf(nzlIndex));
				}
			}
			
			//SLACKS
			l.clear();
			l = Arrays.asList(3, 10, 13);
			for(Integer i : l){
				for(double slackValues : getTestSlackValues()[i]) {
					assertEquals(slackValues, tester.getSlacks(i));
				}
			}
			
			//PROJECTIONS
			l.clear();
			l = Arrays.asList(0, 3, 10);
			for(Integer i : l){
				for(double projectionValues : getTestProjectionValues()[i]) {
					assertEquals(projectionValues, tester.getProjections(i));
				}
			}
			
			//WEIGHTS
			l.clear();
			l = Arrays.asList(0, 3, 10, 14);
			for(Integer i : l){
				for(double weightValues : getTestWeightValues()[i]) {
					assertEquals(weightValues, tester.getWeight(i));
				}
			}
			
			//OPTIMISATION STATUS OK
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
