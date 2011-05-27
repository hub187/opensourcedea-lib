package tests;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

import java.util.ArrayList;

//import dea.DEAModelOrientation;
import dea.*;

public class DEAProblemTest {

	DEAProblem tester = new DEAProblem(5, 3);
	static String[] TestDMUNames = new String[5];
	static String[] TestVariableNames = new String [3];
	static DEAVariableType[] TestVariableTypes = new DEAVariableType[3];
	static double[] [] TestDataMatrix = new double[5] [3];

	
	@BeforeClass
	public static void setUpBeforeClass()
	{

			//Set up the DMU Names
			TestDMUNames[0] = "DMU 1";
			TestDMUNames[1] = "DMU 2";
			TestDMUNames[2] = "DMU 3";
			TestDMUNames[3] = "DMU 4";
			TestDMUNames[4] = "DMU 5";
			
			//Set up the Variable Names
			TestVariableNames[0] = "Wood";
			TestVariableNames[1] = "Twigs";
			TestVariableNames[2] = "Fire";
			
			//Set up the Data Matrix
			TestDataMatrix [0] [0] = 1;
			TestDataMatrix [0] [1] = 7;
			TestDataMatrix [0] [2] = 5;
			
			TestDataMatrix [1] [0] = 1;
			TestDataMatrix [1] [1] = 9;
			TestDataMatrix [1] [2] = 8;
			
			TestDataMatrix [2] [0] = 1;
			TestDataMatrix [2] [1] = 3;
			TestDataMatrix [2] [2] = 3;
			
			TestDataMatrix [3] [0] = 9;
			TestDataMatrix [3] [1] = 4;
			TestDataMatrix [3] [2] = 4;
			
			TestDataMatrix [4] [0] = 1;
			TestDataMatrix [4] [1] = 8;
			TestDataMatrix [4] [2] = 3;
			
			//Set up the variable types
			TestVariableTypes[0] = DEAVariableType.Input;
			TestVariableTypes[1] = DEAVariableType.Input;
			TestVariableTypes[2] = DEAVariableType.Output;

		
		
	}
	


	@Test
	public void testSetGetModelType() {
		tester.setModelType(DEAModelType.CCRI);
		assertSame(tester.getModelType(), DEAModelType.CCRI);
	}
	
	
	@Test
	public void testSetGetDMUNames() {
		tester.setDMUNames(TestDMUNames);
		assertArrayEquals(tester.getDMUNames(), TestDMUNames);
	}
	
	
//	@Test
//	public void testSetGetModelOrientation() {
//		tester.setModelOrientation(DEAModelOrientation.InputOriented);
//		assertSame(tester.getModelOrientation(), DEAModelOrientation.InputOriented);
//	}

	
	@Test
	public void testSetGetVariableNames() {
		tester.setVariableNames(TestVariableNames);
		assertArrayEquals("Assert Array Equals", tester.getVariableNames(), TestVariableNames);
	}

		
	@Test
	public void testSetGetVariableType() {
		tester.setVariableTypes(TestVariableTypes);
		assertArrayEquals("Assert Array Equals", tester.getVariableTypes(), TestVariableTypes);
	}

	@Test
	public void testSetGetDataMatrix() {
		tester.setDataMatrix(TestDataMatrix);
		assertArrayEquals("Assert Array Equals", tester.getDataMatrix(), TestDataMatrix);
	}
	
	@Test
	public void testSetGetNumberOfOutputs() {
		tester.setVariableTypes(TestVariableTypes);
		assertEquals(tester.getNumberOfOutputs(),1,0);
	}
	
	@Test
	public void testSetGetNumberOfInputs() {
		tester.setVariableTypes(TestVariableTypes);
		assertEquals(tester.getNumberOfInputs(),2,0);
	}
	
	@Test
	public void testReferenceSet() {
		tester.setModelType(DEAModelType.CCRI);
		tester.setDMUNames(TestDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		
		try {
			tester.solve();
		}
		catch (DEAException e) {
			System.out.println(e.toString());
		}
		
		ArrayList<Integer>[] validatedRefSet = getTestReferenceSet();
		ArrayList<Integer>[] solRefSet = tester.getReferenceSet();
		int iter;
		
		//Testing getReferenceSet (all DMUs)
		for(int i = 0; i < validatedRefSet.length; i++) {
			iter = 0;
			for(Integer testInt: validatedRefSet[i]) {
				assertEquals(testInt, solRefSet[i].get(iter));
				iter++;
			}
		}
		//Test getReferenceSet (single DMU)
		assertEquals(tester.getReferenceSet(0), validatedRefSet[0]);
		
	}
	
	
	private ArrayList<Integer>[] getTestReferenceSet() {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] ReferenceSet = (ArrayList<Integer>[])new ArrayList[5];
		
		ReferenceSet[0] = new ArrayList<Integer>();
		ReferenceSet[0].add(1);
		ReferenceSet[0].add(2);
		
		ReferenceSet[1] = new ArrayList<Integer>();
		ReferenceSet[1].add(1);
		
		ReferenceSet[2] = new ArrayList<Integer>();
		ReferenceSet[2].add(2);
		
		ReferenceSet[3] = new ArrayList<Integer>();
		ReferenceSet[3].add(2);
		
		ReferenceSet[4] = new ArrayList<Integer>();
		ReferenceSet[4].add(1);
		ReferenceSet[4].add(2);
				
		return ReferenceSet;
	}
	
	
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
		} catch (InvalidPropertyValue e) {
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
	public void TestToSeeObjectProperties() {
		tester.setModelType(DEAModelType.CCRI);
		tester.setDMUNames(TestDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		assertTrue(true);	
	}
	
	@Test
	public void testMissingDataException() {
		try {
			tester.solve();
		}
		catch (DEAException e) {
			assertSame(e.getMessage(), "The DEAProblem is missing some data. Please check the DataMatrix, DMUNames, ModelType, VariableNames and VariableTypes.");
		}
		
	}

	
	
	
	@Test
	public void testMissingDMUCountDiscrepancy() {
		
		tester.setModelType(DEAModelType.CCRI);
		tester.setDMUNames(TestDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		
		String[] dmuNames = new String[3];
		tester.setDMUNames(dmuNames);
		
		
		try {
			tester.solve();
		}
		catch (DEAException e) {
			assertSame(e.getMessage(), "The number of DMUs does not seem to match with the data. Please check the DMUNames and DataMatrix.");
		}
		
	}
	
	
	@Test
	public void testMissingVariableCountDiscrepancy() {
		
		tester.setModelType(DEAModelType.CCRI);
		tester.setDMUNames(TestDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		
		String[] variableNames = new String[3];
		tester.setVariableNames(variableNames);
		
		
		try {
			tester.solve();
		}
		catch (DEAException e) {
			assertSame(e.getMessage(), "The number of variables does not seem to match with the data. Please check the VariableNames, VariableTypes and DataMatrix.");
		}
		
	}
	
	@Test
	public void testRTSBoundsNotSet() {
		
		tester.setModelType(DEAModelType.SBMGRS);
		tester.setDMUNames(TestDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		
		String[] variableNames = new String[3];
		tester.setVariableNames(variableNames);
		
		
		for (DEAModelType mt : DEAModelType.values())
			if(mt.getDEAReturnToScale() == DEAReturnToScale.General) {
				try {
					tester.setModelType(mt);
					tester.solve();
				}
				catch (DEAException e) {
					assertSame(e.getMessage(), "RTS Bounds not set correctly!");
				}
			}
	}
	
	@Test
	public void testGetModelEfficiencyType() {
		tester.setModelType(DEAModelType.SBM);
		try {
			assertSame(tester.getModelEfficiencyType(), DEAEfficiencyType.MIX);
		} catch (MissingData e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetModelOrientation() {
		tester.setModelType(DEAModelType.SBM);
		try {
			assertSame(tester.getModelOrientation(), DEAModelOrientation.NonOriented);
		} catch (MissingData e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetModelRTS() {
		tester.setModelType(DEAModelType.SBM);
		try {
			assertSame(tester.getModelRTS(), DEAReturnToScale.Constant);
		} catch (MissingData e) {
			e.printStackTrace();
		}
	}
	
}


