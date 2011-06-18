package tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
//import static java.util.ArrayList.*;

import java.util.ArrayList;

//import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

//import dea.DEAModelOrientation;
import dea.*;




public class LibraryTestNCI {
	
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
		
		
		Objectives[0] = 0.439796449574369;
		Objectives[1] = 0.384857091140152;
		Objectives[2] = 0.229893314639692;
		Objectives[3] = 0.38474444530869;
		Objectives[4] = 0.508773473545725;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.451761584310805;
		Objectives[8] = 0.655642935463584;
		Objectives[9] = 1;
		Objectives[10] = 0.351590715961988;
		Objectives[11] = 1;
		Objectives[12] = 0.213673748925812;
		Objectives[13] = 0.369906628802482;
		Objectives[14] = 0.82925507717443;
		Objectives[15] = 0.361508497158669;
		Objectives[16] = 1;
		Objectives[17] = 1;
		Objectives[18] = 1;
		Objectives[19] = 1;
		return Objectives;
	}
	
	private int[] createSolRanks() {
		int[] ranks = new int[20];
		
		ranks[0] = 13;
		ranks[1] = 14;
		ranks[2] = 19;
		ranks[3] = 15;
		ranks[4] = 11;
		ranks[5] = 1;
		ranks[6] = 1;
		ranks[7] = 12;
		ranks[8] = 10;
		ranks[9] = 1;
		ranks[10] = 18;
		ranks[11] = 1;
		ranks[12] = 20;
		ranks[13] = 16;
		ranks[14] = 9;
		ranks[15] = 17;
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
		refSet.add(new NonZeroLambda(6, 0.421930871619895));
		refSet.add(new NonZeroLambda(9, 0.511591679127981));
		refSet.add(new NonZeroLambda(11, 0.0629410894212169));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(5, 0.501204613837549));
		refSet.add(new NonZeroLambda(6, 0.375576202773501));
		refSet.add(new NonZeroLambda(11, 0.180621376133376));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 1));
		referenceSets[6] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(6, 0.111079611602825));
		refSet.add(new NonZeroLambda(9, 0.597659261779738));
		refSet.add(new NonZeroLambda(11, 0.737193030387624));
		referenceSets[6] = refSet;
		
		return referenceSets;
	}
	
	@Test
	public void TestNCI() {
		
		BuildDEAProblem(ModelType.NCI); //, DEAModelOrientation.NonOriented);
		
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
			
			
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
			
			assertArrayEquals(tester.getRanks(true, RankingType.STANDARD, 10), createSolRanks());
			
			//Check Reference Set
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(0);
			l.add(1);
			l.add(6);
			l.add(12);
			for(Integer i : l){
				ArrayList<NonZeroLambda> refSet = getTestReferenceSet()[i];
				for(int nzlIndex = 0; nzlIndex < refSet.size();nzlIndex++) {
					assertEquals(refSet.indexOf(nzlIndex), tester.getReferenceSet()[i].indexOf(nzlIndex));
				}
			}
			
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
