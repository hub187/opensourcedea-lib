package org.opensourcedea.test;


import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import org.opensourcedea.dea.*;
import org.opensourcedea.exception.*;

//import org.opensourcedea.dea.DEAProblem;
//import org.opensourcedea.dea.ModelType;
//import org.opensourcedea.dea.NonZeroLambda;
//import org.opensourcedea.dea.RankingType;
//import org.opensourcedea.dea.VariableOrientation;
//import org.opensourcedea.exception.DEAException;
//import org.opensourcedea.exception.InconsistentNoOfDMUsException;
//import org.opensourcedea.exception.InconsistentNoOfVariablesException;
//import org.opensourcedea.exception.MissingDataException;
//import org.opensourcedea.exception.ProblemNotSolvedProperlyException;


/*
 * This class is used as an example of how to call the OSDEASolver.
 */
public class LibraryCallExample {
	
	
	//Create some array for the DMU and Variable Names, types and the Data Matrix
	static String[] testDMUNames = new String[7];
	static String[] testVariableNames = new String [3];
	static VariableOrientation[] testVariableOrientations = new VariableOrientation[3];
	static VariableType[] testVariableTypes = new VariableType[3];
	static double[] [] testDataMatrix = new double[7] [3];

	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		//Set up the DMU Names
		testDMUNames[0] = "DMU A";
		testDMUNames[1] = "DMU B";
		testDMUNames[2] = "DMU C";
		testDMUNames[3] = "DMU D";
		testDMUNames[4] = "DMU E";
		testDMUNames[5] = "DMU F";
		testDMUNames[6] = "DMU G";
		
		//Set up the Variable Names
		testVariableNames[0] = "Wood";
		testVariableNames[1] = "Twigs";
		testVariableNames[2] = "Fire";
		
		//Set up the Data Matrix
		testDataMatrix [0] [0] = 4;
		testDataMatrix [0] [1] = 3;
		testDataMatrix [0] [2] = 1;
		
		testDataMatrix [1] [0] = 7;
		testDataMatrix [1] [1] = 3;
		testDataMatrix [1] [2] = 1;
		
		testDataMatrix [2] [0] = 8;
		testDataMatrix [2] [1] = 1;
		testDataMatrix [2] [2] = 1;
		
		testDataMatrix [3] [0] = 4;
		testDataMatrix [3] [1] = 2;
		testDataMatrix [3] [2] = 1;
		
		testDataMatrix [4] [0] = 2;
		testDataMatrix [4] [1] = 4;
		testDataMatrix [4] [2] = 1;

		testDataMatrix [5] [0] = 10;
		testDataMatrix [5] [1] = 1;
		testDataMatrix [5] [2] = 1;
		
		testDataMatrix [6] [0] = 3;
		testDataMatrix [6] [1] = 7;
		testDataMatrix [6] [2] = 1;
		
		
		//Set up the variable types
		testVariableOrientations[0] = VariableOrientation.INPUT;
		testVariableOrientations[1] = VariableOrientation.INPUT;
		testVariableOrientations[2] = VariableOrientation.OUTPUT;
		
		testVariableTypes[0] = VariableType.STANDARD;
		testVariableTypes[1] = VariableType.STANDARD;
		testVariableTypes[2] = VariableType.STANDARD;

	}
	
	@Test
	public void exampleUnitTest() {
		
		//Create a DEAProblem and specify number of DMUs (7) and number of variables (3).
		DEAProblem tester = new DEAProblem(7, 3);
				
		
		//Set the DEA Problem Model Type (CCR).
		tester.setModelType(ModelType.CCR_I);

		
		//Set the DEA Problem DMU Names where testDMUName is a string[].
		tester.setDMUNames(testDMUNames);

		
		//Set the DEA Problem Variable Names where testVariableName is a String[].
		tester.setVariableNames(testVariableNames);
		
		//Set the DEA Problem Variable Orientations where testVariableOrientation is a VariableOrientation[].
		tester.setVariableOrientations(testVariableOrientations);
		
		//Set the DEA Problem Variable Types where testVariableTypes is a VariableType[].
		tester.setVariableTypes(testVariableTypes);
		
		/* Set the DEA Problem Data Matrix where testDataMatrix is a double[] [].
		 * Each row of the Matrix corresponds to the DMU in the DMUNames array.
		 * Each Column of the Matrix corresponds to the Variable in the Variables Arrays.*/
		tester.setDataMatrix(testDataMatrix);

		
		//Solve the DEA Problem

		try {
			tester.solve();
		} catch (InconsistentNoOfDMUsException e1) {
			/* These Exceptions are generated before the model is solved when there is a discrepancy
			 * in the number of DMUs.*/
			e1.printStackTrace();
		} catch (InconsistentNoOfVariablesException e1) {
			/* These Exceptions are generated before the model is solved when there is a discrepancy
			 * in the number of Variables.*/
			e1.printStackTrace();
		} catch (MissingDataException e1) {
			/* These Exceptions are generated before the model is solved when the models has not
			 * been correctly constructed (for example missing VariableTypes).*/
			e1.printStackTrace();
		} catch (DEAException e1) {
			/* These Exceptions are generated when the solver has encountered a unexpected
			 * problem.*/
			e1.printStackTrace();
		}


		
		try {
		//Get the solution Objectives
		double[] Objectives = tester.getObjectives();
		
		/* Get the solution Lambdas.
		 * This returns an Array of ArrayLists of NonZeroLamdas.*/
		 @SuppressWarnings("unchecked")
		 ArrayList<NonZeroLambda>[] referenceSets = new ArrayList[7];
		    referenceSets = tester.getReferenceSet();
		
		/* Get the solution Slacks.
		 * The first array corresponds to the DMUs.
		 * The second nested array corresponds to the Slack values.*/
		double[] [] Slacks = tester.getSlacks();
		
		/* Get the solution Projections.
		 * The first array corresponds to the DMUs.
		 * The second nested array corresponds to the Projection values.*/
		double[] [] Projections = tester.getProjections();
		
		/* Get the solution Weights.
		 * The first array corresponds to the DMUs.
		 * The second nested array corresponds to the Weight values.*/
		double[] [] Weights = tester.getWeight();
		
		/* Get the DMU ranks.
		 * The boolean confirms that the Highest DMU score is ranked first.
		 * The STANDARD ranking type confirms that the ranking is standard.
		 * This means that if they are two DMUs with an efficiency score of 1 both will be ranked first.
		 * However, the following DMU will only be ranked 3rd as they are two DMUs which score better than it.
		 * Conversely, a DENSE RankingType will have given the following (3rd) DMU the ranking of second.*/
		int[] Ranks = tester.getRanks(true, RankingType.STANDARD, 10);
		
		
		//Get the objective values for the CCR-I model
		double[] ObjectivesTarget = getTargetObjectives();
		
		//AssertArrayEquals
		assertArrayEquals(Objectives, ObjectivesTarget,0.0000000001);
		}
		catch (ProblemNotSolvedProperlyException e) {
			e.printStackTrace();
		}

		
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
