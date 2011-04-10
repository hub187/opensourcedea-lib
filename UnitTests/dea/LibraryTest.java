package dea;


import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import utils.Rank;
import dea.enums.*;


public class LibraryTest {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(7, 3);
	static String[] TestDMUNames = new String[7];
	static String[] TestVariableNames = new String [3];
	static DEAVariableType[] TestVariableTypes = new DEAVariableType[3];
	static double[] [] TestDataMatrix = new double[7] [3];

	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		//Set up the DMU Names
		TestDMUNames[0] = "DMU A";
		TestDMUNames[1] = "DMU B";
		TestDMUNames[2] = "DMU C";
		TestDMUNames[3] = "DMU D";
		TestDMUNames[4] = "DMU E";
		TestDMUNames[5] = "DMU F";
		TestDMUNames[6] = "DMU G";
		
		//Set up the Variable Names
		TestVariableNames[0] = "Wood";
		TestVariableNames[1] = "Twigs";
		TestVariableNames[2] = "Fire";
		
		//Set up the Data Matrix
		TestDataMatrix [0] [0] = 4;
		TestDataMatrix [0] [1] = 3;
		TestDataMatrix [0] [2] = 1;
		
		TestDataMatrix [1] [0] = 7;
		TestDataMatrix [1] [1] = 3;
		TestDataMatrix [1] [2] = 1;
		
		TestDataMatrix [2] [0] = 8;
		TestDataMatrix [2] [1] = 1;
		TestDataMatrix [2] [2] = 1;
		
		TestDataMatrix [3] [0] = 4;
		TestDataMatrix [3] [1] = 2;
		TestDataMatrix [3] [2] = 1;
		
		TestDataMatrix [4] [0] = 2;
		TestDataMatrix [4] [1] = 4;
		TestDataMatrix [4] [2] = 1;

		TestDataMatrix [5] [0] = 10;
		TestDataMatrix [5] [1] = 1;
		TestDataMatrix [5] [2] = 1;
		
		TestDataMatrix [6] [0] = 3;
		TestDataMatrix [6] [1] = 7;
		TestDataMatrix [6] [2] = 1;
		
		
		//Set up the variable types
		TestVariableTypes[0] = DEAVariableType.Input;
		TestVariableTypes[1] = DEAVariableType.Input;
		TestVariableTypes[2] = DEAVariableType.Output;
		

	}
	
	@Test
	public void Debug() {
		
		tester.setModelType(DEAModelType.CCR);
		tester.setDMUNames(TestDMUNames);
		tester.setModelOrientation(DEAModelOrientation.InputOriented);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);

		
		
		tester.solve();
		
		double[] ObjectivesTarget = new double[7];
		ObjectivesTarget[0] = 0.8571428571428571;
		ObjectivesTarget[1] = 0.6315789473684215;
		ObjectivesTarget[2] = 0.9999999999999999;
		ObjectivesTarget[3] = 0.9999999999999999;
		ObjectivesTarget[4] = 0.9999999999999999;
		ObjectivesTarget[5] = 0.9999999999999999;
		ObjectivesTarget[6] = 0.6666666666666671;
		
		double[] Objectives = tester.getObjectives();
//		double[] [] Lambdas = tester.getLambdas(); //new double[7] [7];
//		double[] [] ProjectionPercentages = tester.getProjectionPercentages();
//		double[] ProjectionPercentages1 = tester.getProjectionPercentages(1);
		
		assertArrayEquals(Objectives, ObjectivesTarget,0.0000000001);	
	}
	
	
	
	
	
}
