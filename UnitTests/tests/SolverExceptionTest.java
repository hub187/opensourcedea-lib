package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opensourcedea.dea.*;
import org.opensourcedea.linearSolver.*;
//import org.junit.BeforeClass;



public class SolverExceptionTest {
	
	DEAProblem tester = new DEAProblem(5, 3);
//	static String[] TestDMUNames = new String[5];
//	static String[] TestVariableNames = new String [3];
//	static DEAVariableType[] TestVariableTypes = new DEAVariableType[3];
//	static double[] [] TestDataMatrix = new double[5] [3];
//
//	
//	@BeforeClass
//	public static void setUpBeforeClass()
//	{
//
//			//Set up the DMU Names
//			TestDMUNames[0] = "DMU 1";
//			TestDMUNames[1] = "DMU 2";
//			TestDMUNames[2] = "DMU 3";
//			TestDMUNames[3] = "DMU 4";
//			TestDMUNames[4] = "DMU 5";
//			
//			//Set up the Variable Names
//			TestVariableNames[0] = "Wood";
//			TestVariableNames[1] = "Twigs";
//			TestVariableNames[2] = "Fire";
//			
//			//Set up the Data Matrix
//			TestDataMatrix [0] [0] = 1;
//			TestDataMatrix [0] [1] = 7;
//			TestDataMatrix [0] [2] = 5;
//			
//			TestDataMatrix [1] [0] = 1;
//			TestDataMatrix [1] [1] = 9;
//			TestDataMatrix [1] [2] = 8;
//			
//			TestDataMatrix [2] [0] = 1;
//			TestDataMatrix [2] [1] = 3;
//			TestDataMatrix [2] [2] = 3;
//			
//			TestDataMatrix [3] [0] = 9;
//			TestDataMatrix [3] [1] = 4;
//			TestDataMatrix [3] [2] = 4;
//			
//			TestDataMatrix [4] [0] = 1;
//			TestDataMatrix [4] [1] = 8;
//			TestDataMatrix [4] [2] = 3;
//			
//			//Set up the variable types
//			TestVariableTypes[0] = DEAVariableType.Input;
//			TestVariableTypes[1] = DEAVariableType.Input;
//			TestVariableTypes[2] = DEAVariableType.Output;
//
//		
//		
//	}
	
	@Test
	public void testLinearSolverException() {
		
		tester.setModelType(ModelType.CCR_I);
	//	tester.setDMUNames(TestDMUNames);
	//	//tester.setModelOrientation(DEAModelOrientation.InputOriented);
	//	tester.setVariableNames(TestVariableNames);
	//	tester.setVariableTypes(TestVariableTypes);
	//	tester.setDataMatrix(TestDataMatrix);
	//	
	//	String[] variableNames = new String[3];
	//	tester.setVariableNames(variableNames);
		
		ArrayList<double[]> Constraints = new ArrayList<double []>();
		double[] ObjF = new double [4];
		double[] RHS = new double [5]; //RHS Phase I
		
		int[] SolvEqType = new int[5];
	//	for(int i = 0; i < 5; i++) {
	//		SolvEqType
	//	}
		
		//This will purposely FAIL (and send an java.lang.IndexOutOfBoundsException)!
		try {
			Lpsolve.solveLPProblem(Constraints, ObjF, RHS, SolverObjDirection.MIN, SolvEqType);
		}
		catch (DEAException e) {
				assertTrue(e.getClass() == DEASolverException.class);
		}
	}
	
}


//java.lang.AssertionError: expected same:
//<An error was thrown by the LpSolve class when attempting to solve the problem.This is likely caused by inconsistent data sent to the solver.The error details are as follows: java.lang.IndexOutOfBoundsException: Index: 0, Size: 0: Index: 0, Size: 0>
//<An error was thrown by the LpSolve class when attempting to solve the problem.This is likely caused by inconsistent data sent to the solver.The error details are as follows: java.lang.IndexOutOfBoundsException: Index: 0, Size: 0: Index: 0, Size: 0>



