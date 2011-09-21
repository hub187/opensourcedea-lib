package org.opensourcedea.tests;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opensourcedea.dea.*;

//import dea.DEAModelOrientation;




public class LibraryTestCCRI {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);
	
	public DEAPSolution getCCRIResults() {
		
		
		DEAPSolution CCRISol = new DEAPSolution(20, 4);
		
		CCRISol.setObjectives(createCCRIObjectives());		
		
		CCRISol.setSlacks(createCCRISlacks());
		
		
		return CCRISol;
	}

	private double[] [] createCCRISlacks() {
		
		
		double[] [] SlacksArray = new double[20] [4];
		
		SlacksArray[2] [3] = 809.794354144468;
		SlacksArray[8] [1] = 1.44515522851532;
		SlacksArray[9] [1] = 29.460866749061;
		
		SlacksArray[10] [3] = 230.186902567291;
		SlacksArray[12] [3] = 1013.09272784905;
		SlacksArray[13] [3] = 65.3499495003992;
		SlacksArray[14] [2] = 215.807846199549;
		SlacksArray[17] [2] = 576.331636729437;
		SlacksArray[19] [1] = 5.38462603660133;
		
		return SlacksArray;
		
	}

	private double[] createCCRIObjectives() {
		
		double[] Objectives = new double[20];
		
		
		Objectives[0] = 0.519434453047034;
		Objectives[1] = 0.872474445374321;
		Objectives[2] = 0.549048143682244;
		Objectives[3] = 0.322936931532726;
		Objectives[4] = 0.782832225762655;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.830850567998129;
		Objectives[8] = 0.539682742929534;
		Objectives[9] = 0.567189831743522;
		Objectives[10] = 0.459594713883521;
		Objectives[11] = 1;
		Objectives[12] = 0.564403802355244;
		Objectives[13] = 0.338735130146885;
		Objectives[14] = 0.805598209487005;
		Objectives[15] = 0.65721528448653;
		Objectives[16] = 1;
		Objectives[17] = 0.739084603920947;
		Objectives[18] = 1;
		Objectives[19] = 0.207079840963623;
		return Objectives;
	}
	
	
	public void buildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
	}
	
	@Test
	public void testCCRI() throws Exception {
		
		buildDEAProblem(ModelType.CCR_I);

		
		try {
			tester.solve();
		}
		catch (DEAException e) {
			e.fillInStackTrace();
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
		}
		
		DEAPSolution CheckedSol = getCCRIResults();
		
		try {
			//Test Objectives
			assertArrayEquals(tester.getObjectives(), CheckedSol.getObjectives(),0.000001);
			
			//Test Slacks
			for(int i = 0; i < tester.getNumberOfDMUs(); i++) {
				assertArrayEquals(tester.getSlacks(i), CheckedSol.getSlacks(i), 0.1);
			}
			
			//int[] ranks = tester.getDMURanks(true);
			
			//Test Weighted Inputs = 1
			for(int i = 0; i < tester.getNumberOfDMUs(); i++) {
				assertEquals(tester.getDataMatrix(i, 0) * tester.getWeight(i, 0) + tester.getDataMatrix(i, 1) * tester.getWeight(i, 1),
						1, 0.00001);
			}
			
			//test return status is OK
			assertEquals(tester.getOptimisationStatus(),SolverReturnStatus.OPTIMAL_SOLUTION_FOUND);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	
	
	
	
}
