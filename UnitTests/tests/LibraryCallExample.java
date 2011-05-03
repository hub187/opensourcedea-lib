package tests;


import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import dea.*;


public class LibraryCallExample {
	
	
	//Create some array for the DMU and Variable Names, types and the Data Matrix
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
	public void exampleUnitTest() {
		
		//Create a DEAProblem and specify number of DMUs (7) and number of variables (3).
		DEAProblem tester = new DEAProblem(7, 3);
				
		
		//Set the DEA Problem Model Type (CCR).
		tester.setModelType(DEAModelType.CCRI);
		
		//Set the DEA Problem DMU Names where TestDMUName is a double[].
		tester.setDMUNames(TestDMUNames);
		
		//Set the DEA problem Model Orientation (Input Oriented).
		//tester.setModelOrientation(DEAModelOrientation.InputOriented);
		
		//Set the DEA Problem Variable Names where TestVariableName is a String[].
		tester.setVariableNames(TestVariableNames);
		
		//Set the DEA Problem Variable Types where TestVariableType is a DEAVariableType[].
		tester.setVariableTypes(TestVariableTypes);
		
		/* Set the DEA Problem Data Matrix where TestDataMatrix is a double[] [].
		 * Each row of the Matrix corresponds to the DMU in the DMUNames array.
		 * Each Column of the Matrix corresponds to the Variable in the Variables Arrays.*/
		tester.setDataMatrix(TestDataMatrix);

		
		//Solve the DEA Problem
		tester.solve();
		
		
		//Get the solution Objectives
		double[] Objectives = tester.getObjectives();
		
//		/* Get the solution Lambdas.
//		 * The first array corresponds to the DMUs.
//		 * The second nested array corresponds to the Lambda values.*/
//		double[] [] Lambdas = tester.getLambdas();
//		
//		/* Get the solution Slacks.
//		 * The first array corresponds to the DMUs.
//		 * The second nested array corresponds to the Slack values.*/
//		double[] [] Slacks = tester.getSlacks();
//		
//		/* Get the solution Projections.
//		 * The first array corresponds to the DMUs.
//		 * The second nested array corresponds to the Projection values.*/
//		double[] [] Projections = tester.getProjections();
//		
//		/* Get the solution Weights.
//		 * The first array corresponds to the DMUs.
//		 * The second nested array corresponds to the Weight values.*/
//		double[] [] Weights = tester.getWeight();
//		
//		/* Get the DMU ranks.
//		 * The boolean confirms that the Highest DMU score is ranked first.
//		 * The STANDARD ranking type confirms that the ranking is standard.
//		 * This means that if they are two DMUs with an efficiency score of 1 both will be ranked first.
//		 * However, the following DMU will only be ranked 3rd as they are two DMUs which score better than it.
//		 * Conversely, a DENSE RankingType will have given the following (3rd) DMU the ranking of second.*/
//		int[] Ranks = tester.getRanks(true, RankingType.STANDARD);
		
		
		//Get the objective values for the CCR-I model
		double[] ObjectivesTarget = getTargetObjectives();
		
		//AssertArrayEquals
		assertArrayEquals(Objectives, ObjectivesTarget,0.0000000001);	
	}

	private double[] getTargetObjectives() {
		double[] ObjectivesTarget = new double[7];
		ObjectivesTarget[0] = 0.8571428571428571;
		ObjectivesTarget[1] = 0.6315789473684215;
		ObjectivesTarget[2] = 0.9999999999999999;
		ObjectivesTarget[3] = 0.9999999999999999;
		ObjectivesTarget[4] = 0.9999999999999999;
		ObjectivesTarget[5] = 0.9999999999999999;
		ObjectivesTarget[6] = 0.6666666666666671;
		return ObjectivesTarget;
	}
	
	
	
	
	
}
