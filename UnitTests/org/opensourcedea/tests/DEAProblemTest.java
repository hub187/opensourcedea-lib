package org.opensourcedea.tests;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.BeforeClass;
import org.opensourcedea.dea.*;

//import java.util.ArrayList;

//import dea.DEAModelOrientation;

public class DEAProblemTest {

	DEAProblem tester = new DEAProblem(5, 3);
	static String[] testDMUNames = new String[5];
	static String[] testVariableNames = new String [3];
	static VariableOrientation[] testVariableOrientations = new VariableOrientation[3];
	static double[] [] testDataMatrix = new double[5] [3];

	
	@BeforeClass
	public static void setUpBeforeClass()
	{

			//Set up the DMU Names
			testDMUNames[0] = "DMU 1";
			testDMUNames[1] = "DMU 2";
			testDMUNames[2] = "DMU 3";
			testDMUNames[3] = "DMU 4";
			testDMUNames[4] = "DMU 5";
			
			//Set up the Variable Names
			testVariableNames[0] = "Wood";
			testVariableNames[1] = "Twigs";
			testVariableNames[2] = "Fire";
			
			//Set up the Data Matrix
			testDataMatrix [0] [0] = 1;
			testDataMatrix [0] [1] = 7;
			testDataMatrix [0] [2] = 5;
			
			testDataMatrix [1] [0] = 1;
			testDataMatrix [1] [1] = 9;
			testDataMatrix [1] [2] = 8;
			
			testDataMatrix [2] [0] = 1;
			testDataMatrix [2] [1] = 3;
			testDataMatrix [2] [2] = 3;
			
			testDataMatrix [3] [0] = 9;
			testDataMatrix [3] [1] = 4;
			testDataMatrix [3] [2] = 4;
			
			testDataMatrix [4] [0] = 1;
			testDataMatrix [4] [1] = 8;
			testDataMatrix [4] [2] = 3;
			
			//Set up the variable types
			testVariableOrientations[0] = VariableOrientation.INPUT;
			testVariableOrientations[1] = VariableOrientation.INPUT;
			testVariableOrientations[2] = VariableOrientation.OUTPUT;

		
		
	}
	


	@Test
	public void testSetGetModelType() {

		tester.setModelType(ModelType.CCR_I);

		assertSame(tester.getModelType(), ModelType.CCR_I);
	}
	
	
	@Test
	public void testSetGetDMUNames() {
		tester.setDMUNames(testDMUNames);
		assertArrayEquals(tester.getDMUNames(), testDMUNames);
	}
	
	
//	@Test
//	public void testSetGetModelOrientation() {
//		tester.setModelOrientation(DEAModelOrientation.InputOriented);
//		assertSame(tester.getModelOrientation(), DEAModelOrientation.InputOriented);
//	}

	
	@Test
	public void testSetGetVariableNames() {
		tester.setVariableNames(testVariableNames);
		assertArrayEquals("Assert Array Equals", tester.getVariableNames(), testVariableNames);
	}

		
	@Test
	public void testSetGetVariableOrientation() {
		tester.setVariableOrientations(testVariableOrientations);
		assertArrayEquals("Assert Array Equals", tester.getVariableOrientation(), testVariableOrientations);
	}
	
	@Test
	public void testSetGetVariableType() {
		VariableType[] varTypes = new VariableType[] {VariableType.STANDARD, VariableType.NON_CONTROLLABLE, VariableType.NON_DISCRETIONARY};
		tester.setVariableTypes(varTypes);
		try {
			assertEquals(tester.getVariableType(0), VariableType.STANDARD);
			assertEquals(tester.getVariableType(1), VariableType.NON_CONTROLLABLE);
			assertEquals(tester.getVariableType(2), VariableType.NON_DISCRETIONARY);
			assertArrayEquals(tester.getVariableTypes(), varTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSetGetDataMatrix() {
		tester.setDataMatrix(testDataMatrix);
		assertArrayEquals("Assert Array Equals", tester.getDataMatrix(), testDataMatrix);
	}
	
	@Test
	public void testSetGetNumberOfOutputs() throws Exception {
		tester.setVariableOrientations(testVariableOrientations);
		try {
			assertEquals(tester.getNumberOfOutputs(),1,0);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	@Test
	public void testSetGetNumberOfInputs() throws Exception {
		tester.setVariableOrientations(testVariableOrientations);
		try {
			assertEquals(tester.getNumberOfInputs(),2,0);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	@Test
	public void testGetReferenceSet() {

		tester.setModelType(ModelType.CCR_I);

		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableOrientations(testVariableOrientations);
		tester.setDataMatrix(testDataMatrix);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[20];
		
		ArrayList<NonZeroLambda> refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(1, 0.5263157894736831));
		refSet.add(new NonZeroLambda(2, 0.26315789473684525));
		referenceSets[0] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(1, 1));
		referenceSets[1] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(2, 1));
		referenceSets[2] = refSet;
		
		refSet = new ArrayList<NonZeroLambda>();
		refSet.add(new NonZeroLambda(1, 0.348837209302325));
		refSet.add(new NonZeroLambda(2, 0.06976744186046668));
		referenceSets[4] = refSet;
		
		@SuppressWarnings("unchecked")
		ArrayList<NonZeroLambda>[] wholeRefSet = new ArrayList[20];
		try {
			wholeRefSet = tester.getReferenceSet();
		} catch (ProblemNotSolvedProperlyException e1) {
			e1.printStackTrace();
		}
		
		List<Integer> l = Arrays.asList(0, 1, 2, 4);
		for(Integer i : l){
			for(int nzlIndex = 0; nzlIndex < refSet.size();nzlIndex++) {
				try {
					assertEquals(refSet.indexOf(nzlIndex), tester.getReferenceSet(i).indexOf(nzlIndex));
					assertEquals(refSet.indexOf(nzlIndex), wholeRefSet[i].indexOf(nzlIndex));
				} catch (ProblemNotSolvedProperlyException e) {

					e.printStackTrace();
				}
			}
		}
		
	}
	
	
//	private ArrayList<Integer>[] getTestReferenceSet() {
//		@SuppressWarnings("unchecked")
//		ArrayList<Integer>[] ReferenceSet = (ArrayList<Integer>[])new ArrayList[5];
//		
//		ReferenceSet[0] = new ArrayList<Integer>();
//		ReferenceSet[0].add(1);
//		ReferenceSet[0].add(2);
//		
//		ReferenceSet[1] = new ArrayList<Integer>();
//		ReferenceSet[1].add(1);
//		
//		ReferenceSet[2] = new ArrayList<Integer>();
//		ReferenceSet[2].add(2);
//		
//		ReferenceSet[3] = new ArrayList<Integer>();
//		ReferenceSet[3].add(2);
//		
//		ReferenceSet[4] = new ArrayList<Integer>();
//		ReferenceSet[4].add(1);
//		ReferenceSet[4].add(2);
//				
//		return ReferenceSet;
//	}
	
	
	@Test
	public void testSetGetRTSLowerBound() {
		try {
		tester.setRTSLowerBound(0.8);
		}
		catch (Exception e) {
			e.getMessage();
		}
		assertEquals(tester.getRTSLowerBound(),0.8,0);
	}
	
	@Test
	public void testSetRTSLowerBoundError() {
		String testErrorMsg = "";
		try {
			tester.setRTSLowerBound(-1);
			}
		catch (Exception e) {
				testErrorMsg = e.getMessage();
			}
		assertEquals(testErrorMsg, "Lower Bound must be as follows: 0 <= LowerB <= 1.");
		
		try {
			tester.setRTSLowerBound(2);
			}
		catch (Exception e) {
				testErrorMsg = e.getMessage();
			}
		assertEquals(testErrorMsg, "Lower Bound must be as follows: 0 <= LowerB <= 1.");
			
	}
	
	@Test
	public void testSetGetRTSUpperBound() {
		try {
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
		assertEquals(tester.getRTSUpperBound(),1.2,0);
	}
	
	@Test
	public void testSetRTSUpperBoundError() {
		String testErrorMsg = "";
		try {
			tester.setRTSUpperBound(-1);
			}
		catch (Exception e) {
				testErrorMsg = e.getMessage();
			}
		assertEquals(testErrorMsg, "Upper Bound must be greater or equal to 1.");
					
	}
	
	

	
	@Test
	public void testToSeeObjectProperties() {

		tester.setModelType(ModelType.CCR_I);

		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableOrientations(testVariableOrientations);
		tester.setDataMatrix(testDataMatrix);
		assertTrue(true);	
	}
	
	@Test
	public void testMissingDataException() {
		try {
			tester.solve();
		}
		catch (DEAException e) {
			assertSame(e.getMessage(), "The DEAProblem is missing some data. " +
					"Please check the DataMatrix, DMUNames,ModelType, VariableNames and VariableTypes.");
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testMissingDMUCountDiscrepancy() {
		

		tester.setModelType(ModelType.CCR_I);


		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableOrientations(testVariableOrientations);
		tester.setDataMatrix(testDataMatrix);
		
		String[] dmuNames = new String[3];
		tester.setDMUNames(dmuNames);
		
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			assertSame(e.getMessage(), "The number of DMUs does not seem to match with the data. Please check the DMUNames and DataMatrix.");
		}
		
	}
	
	
	@Test
	public void testMissingVariableCountDiscrepancy() {
		
		
		tester.setModelType(ModelType.CCR_I);

		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableOrientations(testVariableOrientations);
		tester.setDataMatrix(testDataMatrix);
		
		String[] variableNames = new String[3];
		tester.setVariableNames(variableNames);
		
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			assertSame(e.getMessage(), "The number of variables does not seem to match with the data. Please check the VariableNames, VariableTypes and DataMatrix.");
		}
		
	}
	
	@Test
	public void testRTSBoundsNotSet() {
		
		
		tester.setModelType(ModelType.SBM_GRS);

		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableOrientations(testVariableOrientations);
		tester.setDataMatrix(testDataMatrix);
		
		String[] variableNames = new String[3];
		tester.setVariableNames(variableNames);
		
		
		for (ModelType mt : ModelType.values())
			if(mt.getReturnToScale() == ReturnToScale.GENERAL) {
				try {
					tester.setModelType(mt);
					tester.solve();
				}
				catch (Exception e) {
					assertSame(e.getMessage(), "RTS Bounds not set correctly!");
				}
			}
	}
	
	@Test
	public void testGetModelEfficiencyType() {
		
		tester.setModelType(ModelType.SBM);


		try {
			assertSame(tester.getModelEfficiencyType(), EfficiencyType.MIX);
		} catch (MissingDataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetModelOrientation() {
		
		tester.setModelType(ModelType.SBM);

		try {
			assertSame(tester.getModelOrientation(), ModelOrientation.NON_ORIENTED);
		} catch (MissingDataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetModelRTS() {
		
		tester.setModelType(ModelType.SBM);

		try {
			assertSame(tester.getModelRTS(), ReturnToScale.CONSTANT);
		} catch (MissingDataException e) {
			e.printStackTrace();
		}
	}
	
	
}


