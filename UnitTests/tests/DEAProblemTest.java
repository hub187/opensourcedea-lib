package tests;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

//import java.util.ArrayList;

//import dea.DEAModelOrientation;
import dea.*;

public class DEAProblemTest {

	DEAProblem tester = new DEAProblem(5, 3);
	static String[] testDMUNames = new String[5];
	static String[] testVariableNames = new String [3];
	static VariableType[] testVariableTypes = new VariableType[3];
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
			testVariableTypes[0] = VariableType.INPUT;
			testVariableTypes[1] = VariableType.INPUT;
			testVariableTypes[2] = VariableType.OUTPUT;

		
		
	}
	


	@Test
	public void testSetGetModelType() {
		tester.setModelType(ModelType.CCRI);
		assertSame(tester.getModelType(), ModelType.CCRI);
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
	public void testSetGetVariableType() {
		tester.setVariableTypes(testVariableTypes);
		assertArrayEquals("Assert Array Equals", tester.getVariableTypes(), testVariableTypes);
	}

	@Test
	public void testSetGetDataMatrix() {
		tester.setDataMatrix(testDataMatrix);
		assertArrayEquals("Assert Array Equals", tester.getDataMatrix(), testDataMatrix);
	}
	
	@Test
	public void testSetGetNumberOfOutputs() throws Exception {
		tester.setVariableTypes(testVariableTypes);
		try {
			assertEquals(tester.getNumberOfOutputs(),1,0);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	@Test
	public void testSetGetNumberOfInputs() throws Exception {
		tester.setVariableTypes(testVariableTypes);
		try {
			assertEquals(tester.getNumberOfInputs(),2,0);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	@Test
	public void testReferenceSet() {
		tester.setModelType(ModelType.CCRI);
		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableTypes(testVariableTypes);
		tester.setDataMatrix(testDataMatrix);
		
		try {
			tester.solve();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
//		ArrayList<Integer>[] validatedRefSet = getTestReferenceSet();
//		ArrayList<Integer>[] solRefSet = tester.getReferenceSet();
//		int iter;
//		
//		//Testing getReferenceSet (all DMUs)
//		for(int i = 0; i < validatedRefSet.length; i++) {
//			iter = 0;
//			for(Integer testInt: validatedRefSet[i]) {
//				assertEquals(testInt, solRefSet[i].get(iter));
//				iter++;
//			}
//		}
//		//Test getReferenceSet (single DMU)
//		assertEquals(tester.getReferenceSet(0), validatedRefSet[0]);
		
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
	public void testToSeeObjectProperties() {
		tester.setModelType(ModelType.CCRI);
		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableTypes(testVariableTypes);
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
		
		tester.setModelType(ModelType.CCRI);
		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableTypes(testVariableTypes);
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
		
		tester.setModelType(ModelType.CCRI);
		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableTypes(testVariableTypes);
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
		
		tester.setModelType(ModelType.SBMGRS);
		tester.setDMUNames(testDMUNames);
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(testVariableNames);
		tester.setVariableTypes(testVariableTypes);
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
		} catch (MissingData e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetModelOrientation() {
		tester.setModelType(ModelType.SBM);
		try {
			assertSame(tester.getModelOrientation(), ModelOrientation.NON_ORIENTED);
		} catch (MissingData e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetModelRTS() {
		tester.setModelType(ModelType.SBM);
		try {
			assertSame(tester.getModelRTS(), ReturnToScale.CONSTANT);
		} catch (MissingData e) {
			e.printStackTrace();
		}
	}
	
}


