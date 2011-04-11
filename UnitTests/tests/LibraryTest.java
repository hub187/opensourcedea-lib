package tests;


import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import dea.DEAModelOrientation;
import dea.DEAModelType;
import dea.DEAProblem;
import dea.DEAVariableType;


public class LibraryTest {
	
	/* If this Unit Test fails, please read the instructions in the
	 * Lpsolve class.*/
	
	DEAProblem tester = new DEAProblem(20, 4);
	static String[] TestDMUNames = new String[20];
	static String[] TestVariableNames = new String [4];
	static DEAVariableType[] TestVariableTypes = new DEAVariableType[4];
	static double[] [] TestDataMatrix = new double[20] [4];

	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		//Set up the DMU Names
		TestDMUNames[0] = "DMU 1";
		TestDMUNames[1] = "DMU 2";
		TestDMUNames[2] = "DMU 3";
		TestDMUNames[3] = "DMU 4";
		TestDMUNames[4] = "DMU 5";
		TestDMUNames[5] = "DMU 6";
		TestDMUNames[6] = "DMU 7";
		TestDMUNames[7] = "DMU 8";
		TestDMUNames[8] = "DMU 9";
		TestDMUNames[9] = "DMU 10";
		TestDMUNames[10] = "DMU 11";
		TestDMUNames[11] = "DMU 12";
		TestDMUNames[12] = "DMU 13";
		TestDMUNames[13] = "DMU 14";
		TestDMUNames[14] = "DMU 15";
		TestDMUNames[15] = "DMU 16";
		TestDMUNames[16] = "DMU 17";
		TestDMUNames[17] = "DMU 18";
		TestDMUNames[18] = "DMU 19";
		TestDMUNames[19] = "DMU 20";		

		
		
		//Set up the Variable Names
		TestVariableNames[0] = "Surface";
		TestVariableNames[1] = "Number of Employees";
		TestVariableNames[2] = "Marketing Profits";
		TestVariableNames[3] = "Sales Profits";
		
		//Set up the Data Matrix
		TestDataMatrix [0] [0] = 4;
		TestDataMatrix [0] [1] = 3;
		TestDataMatrix [0] [2] = 1;
		TestDataMatrix [0] [3] = 1;
		
		TestDataMatrix [1] [0] = 7;
		TestDataMatrix [1] [1] = 3;
		TestDataMatrix [1] [2] = 1;
		TestDataMatrix [1] [3] = 1;		
		
		TestDataMatrix [2] [0] = 8;
		TestDataMatrix [2] [1] = 1;
		TestDataMatrix [2] [2] = 1;
		TestDataMatrix [2] [3] = 1;
		
		TestDataMatrix [3] [0] = 4;
		TestDataMatrix [3] [1] = 2;
		TestDataMatrix [3] [2] = 1;
		TestDataMatrix [3] [3] = 1;
		
		TestDataMatrix [4] [0] = 2;
		TestDataMatrix [4] [1] = 4;
		TestDataMatrix [4] [2] = 1;
		TestDataMatrix [4] [3] = 1;
		
		TestDataMatrix [5] [0] = 10;
		TestDataMatrix [5] [1] = 1;
		TestDataMatrix [5] [2] = 1;
		TestDataMatrix [5] [3] = 1;
		
		TestDataMatrix [6] [0] = 3;
		TestDataMatrix [6] [1] = 7;
		TestDataMatrix [6] [2] = 1;
		TestDataMatrix [6] [3] = 1;	
		
		TestDataMatrix [7] [0] = 3;
		TestDataMatrix [7] [1] = 7;
		TestDataMatrix [7] [2] = 1;
		TestDataMatrix [7] [3] = 1;	

		TestDataMatrix [8] [0] = 3;
		TestDataMatrix [8] [1] = 7;
		TestDataMatrix [8] [2] = 1;
		TestDataMatrix [8] [3] = 1;	
		
		TestDataMatrix [9] [0] = 3;
		TestDataMatrix [9] [1] = 7;
		TestDataMatrix [9] [2] = 1;
		TestDataMatrix [9] [3] = 1;			

		TestDataMatrix [10] [0] = 3;
		TestDataMatrix [10] [1] = 7;
		TestDataMatrix [10] [2] = 1;
		TestDataMatrix [10] [3] = 1;			

		TestDataMatrix [11] [0] = 3;
		TestDataMatrix [11] [1] = 7;
		TestDataMatrix [11] [2] = 1;
		TestDataMatrix [11] [3] = 1;			

		TestDataMatrix [12] [0] = 3;
		TestDataMatrix [12] [1] = 7;
		TestDataMatrix [12] [2] = 1;
		TestDataMatrix [12] [3] = 1;			

		TestDataMatrix [13] [0] = 3;
		TestDataMatrix [13] [1] = 7;
		TestDataMatrix [13] [2] = 1;
		TestDataMatrix [13] [3] = 1;			

		TestDataMatrix [14] [0] = 3;
		TestDataMatrix [14] [1] = 7;
		TestDataMatrix [14] [2] = 1;
		TestDataMatrix [14] [3] = 1;			

		TestDataMatrix [15] [0] = 3;
		TestDataMatrix [15] [1] = 7;
		TestDataMatrix [15] [2] = 1;
		TestDataMatrix [15] [3] = 1;			

		TestDataMatrix [16] [0] = 3;
		TestDataMatrix [16] [1] = 7;
		TestDataMatrix [16] [2] = 1;
		TestDataMatrix [16] [3] = 1;			

		TestDataMatrix [17] [0] = 3;
		TestDataMatrix [17] [1] = 7;
		TestDataMatrix [17] [2] = 1;
		TestDataMatrix [17] [3] = 1;			

		TestDataMatrix [18] [0] = 3;
		TestDataMatrix [18] [1] = 7;
		TestDataMatrix [18] [2] = 1;
		TestDataMatrix [18] [3] = 1;			

		TestDataMatrix [19] [0] = 3;
		TestDataMatrix [19] [1] = 7;
		TestDataMatrix [19] [2] = 1;
		TestDataMatrix [19] [3] = 1;			
	
		
		//Set up the variable types
		TestVariableTypes[0] = DEAVariableType.Input;
		TestVariableTypes[1] = DEAVariableType.Input;
		TestVariableTypes[2] = DEAVariableType.Output;
		TestVariableTypes[3] = DEAVariableType.Output;
		

		

	}
	
	private class DEASolution {
		
		double[] Objectives;
		//double[] Lambdas;
		
	}
	
	public DEASolution GetCCRIResults() {
		
		DEASolution CCRISol = new DEASolution();
		
		double[] Objectives = new double[20];
		Objectives[0] = 0.519434812021246;
		Objectives[1] = 0.872473221578998;
		Objectives[2] = 0.549046590401745;
		Objectives[3] = 0.322936418671813;
		Objectives[4] = 0.782831804168681;
		Objectives[5] = 1;
		Objectives[6] = 1;
		Objectives[7] = 0.830850222668304;
		Objectives[8] = 0.53968434894831;
		Objectives[9] = 0.567195656859274;
		Objectives[10] = 0.459596265794807;
		Objectives[11] = 1;
		Objectives[12] = 0.564402493712204;
		Objectives[13] = 0.338734740348399;
		Objectives[14] = 0.805611390795234;
		Objectives[15] = 0.657217760429428;
		Objectives[16] = 1;
		Objectives[17] = 0.739090018036911;
		Objectives[18] = 1;
		Objectives[19] = 0.207059764006891;
		
		           
		CCRISol.Objectives = Objectives;
		
		
		return CCRISol;
	}
	
	public void BuildDEAProblem(DEAModelType ModelType, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestVariableNames);
		tester.setVariableTypes(TestVariableTypes);
		tester.setDataMatrix(TestDataMatrix);
		tester.setDMUNames(TestDMUNames);
	}
	
	@Test
	public void TestCCRI() {
		
		BuildDEAProblem(DEAModelType.CCR, DEAModelOrientation.InputOriented);
		
		tester.solve();
		
		DEASolution CheckedSol = GetCCRIResults();
		
		double[] ObjectivesToCheck = tester.getObjectives();
		
		
		assertArrayEquals(ObjectivesToCheck, CheckedSol.Objectives,0.0000001);	
	}
	
	
	
	
	
}
