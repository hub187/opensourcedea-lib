package tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dea.*;



public class LibraryTestCCRO {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);

	public DEAPSolution GetCCROResults() {
		
		
		DEAPSolution CCRISol = new DEAPSolution(20, 4);
		
		CCRISol.setObjectives(createCCROObjectives());		
		
		CCRISol.setSlacks(createCCROSlacks());
		
		
		return CCRISol;
	}

	private double[] [] createCCROSlacks() {
		
		
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 1474.90591392133;
		SlacksArray[8] [1] = 2.67778662084071;
		
		SlacksArray[9] [1] = 51.94181048433;
		SlacksArray[10] [3] = 500.847585086953;
		SlacksArray[12] [3] = 1794.97856609299;
		SlacksArray[13] [3] = 192.923448690019;
		SlacksArray[14] [2] = 267.885210838506;
		SlacksArray[17] [2] = 779.791154722907;
		SlacksArray[19] [1] = 26.0026568088161;
		
		return SlacksArray;
		
	}

	private double[] createCCROObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 1 / 0.519434453047034;
		Objectives[1] = 1 / 0.872474445374321;
		Objectives[2] = 1 / 0.549048143682244;
		Objectives[3] = 1 / 0.322936931532726;
		Objectives[4] = 1 / 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 1 / 0.830850567998129;
		Objectives[8] = 1 / 0.539682742929534;
		Objectives[9] = 1 / 0.567189831743522;
		Objectives[10] = 1 / 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 1 / 0.564403802355244;
		Objectives[13] = 1 / 0.338735130146885;
		Objectives[14] = 1 / 0.805598209487005;
		Objectives[15] = 1 / 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 1 / 0.739084603920947;
		Objectives[18] = 1;
		Objectives[19] = 1 / 0.207079840963623;
		return Objectives;
	}
	
	
	public void BuildDEAProblem(DEAModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableTypes(TestData.createTestDEAVariableTypes());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}
	
	@Test
	public void TestCCRO() {
		
		BuildDEAProblem(DEAModelType.CCRO);  //, DEAModelOrientation.OutputOriented);
		
		
		try {
			tester.solve();
		}
		catch (DEAException e) {
			System.out.println(e.toString());
		}
		
		
		
		DEAPSolution CheckedSol = GetCCROResults();
		
		//Test Objectives
		assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.0001);
		
		//Test Slacks
		for(int i = 0; i < tester.getNumberOfDMUs(); i++) {
			assertArrayEquals(tester.getSlacks(i), CheckedSol.getSlacks()[i], 0.1);
		}
		
		//int[] ranks = tester.getDMURanks(true);
		
		//Test Weighted Inputs = 1
		for(int i = 0; i < tester.getNumberOfDMUs(); i++) {
			assertEquals(tester.getDataMatrix(i, 2) * tester.getWeight(i, 2) + tester.getDataMatrix(i, 3) * tester.getWeight(i, 3),
					1, 0.0001);
		}
		
		//test return status is OK
		assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OptimalSolutionFound);
		
	}
	
	

}