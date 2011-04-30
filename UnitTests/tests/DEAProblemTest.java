package tests;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;

import dea.DEAModelOrientation;
import dea.DEAModelType;
import dea.DEAProblem;
import dea.DEAVariableType;

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
		tester.setModelType(DEAModelType.CCR);
		assertSame(tester.getModelType(), DEAModelType.CCR);
	}
	
	
	@Test
	public void testSetGetDMUNames() {
		tester.setDMUNames(TestDMUNames);
		assertArrayEquals(tester.getDMUNames(), TestDMUNames);
	}
	
	
	@Test
	public void testSetGetModelOrientation() {
		tester.setModelOrientation(DEAModelOrientation.InputOriented);
		assertSame(tester.getModelOrientation(), DEAModelOrientation.InputOriented);
	}

	
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
	public void TestToSeeObjectProperties() {
		tester.setModelType(DEAModelType.CCR);
		tester.setDMUNames(TestDMUNames);
		tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		assertTrue(true);	
	}
	

	
	
}


