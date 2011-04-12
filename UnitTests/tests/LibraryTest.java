package tests;


import static org.junit.Assert.assertArrayEquals;
import java.util.*;
//import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import dea.DEAModelOrientation;
import dea.DEAModelType;
import dea.DEAProblem;
import dea.DEAVariableType;

import dea.DEAPSolution;



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
		TestDataMatrix [0] [0] = 411.17;
		TestDataMatrix [0] [1] = 83;
		TestDataMatrix [0] [2] = 1877.18;
		TestDataMatrix [0] [3] = 1345.27;
		
		TestDataMatrix [1] [0] = 1023.92;
		TestDataMatrix [1] [1] = 84;
		TestDataMatrix [1] [2] = 3529.32;
		TestDataMatrix [1] [3] = 2816.55;		
		
		TestDataMatrix [2] [0] = 1537.69;
		TestDataMatrix [2] [1] = 148;
		TestDataMatrix [2] [2] = 3923.24;
		TestDataMatrix [2] [3] = 1007.52;
		
		TestDataMatrix [3] [0] = 361;
		TestDataMatrix [3] [1] = 83;
		TestDataMatrix [3] [2] = 1250.71;
		TestDataMatrix [3] [3] = 151.94;
		
		TestDataMatrix [4] [0] = 492.78;
		TestDataMatrix [4] [1] = 83;
		TestDataMatrix [4] [2] = 2982.66;
		TestDataMatrix [4] [3] = 2059.93;
		
		TestDataMatrix [5] [0] = 516.64;
		TestDataMatrix [5] [1] = 83;
		TestDataMatrix [5] [2] = 3778.31;
		TestDataMatrix [5] [3] = 3396.07;
		
		TestDataMatrix [6] [0] = 259.3;
		TestDataMatrix [6] [1] = 85;
		TestDataMatrix [6] [2] = 3054.6;
		TestDataMatrix [6] [3] = 2947.03;	
		
		TestDataMatrix [7] [0] = 708.89;
		TestDataMatrix [7] [1] = 84;
		TestDataMatrix [7] [2] = 3237.17;
		TestDataMatrix [7] [3] = 2940.28;	

		TestDataMatrix [8] [0] = 266.28;
		TestDataMatrix [8] [1] = 87;
		TestDataMatrix [8] [2] = 1729.98;
		TestDataMatrix [8] [3] = 1277.12;	
		
		TestDataMatrix [9] [0] = 113.91;
		TestDataMatrix [9] [1] = 85;
		TestDataMatrix [9] [2] = 817.38;
		TestDataMatrix [9] [3] = 193.87;			

		TestDataMatrix [10] [0] = 384.149;
		TestDataMatrix [10] [1] = 65;
		TestDataMatrix [10] [2] = 1409.55;
		TestDataMatrix [10] [3] = 11.26;			

		TestDataMatrix [11] [0] = 208.91;
		TestDataMatrix [11] [1] = 58;
		TestDataMatrix [11] [2] = 2703.88;
		TestDataMatrix [11] [3] = 42.02;			

		TestDataMatrix [12] [0] = 1174.17;
		TestDataMatrix [12] [1] = 103;
		TestDataMatrix [12] [2] = 2821.10;
		TestDataMatrix [12] [3] = 474.20;			

		TestDataMatrix [13] [0] = 349.33;
		TestDataMatrix [13] [1] = 75;
		TestDataMatrix [13] [2] = 1190.92;
		TestDataMatrix [13] [3] = 38.57;			

		TestDataMatrix [14] [0] = 327.25;
		TestDataMatrix [14] [1] = 43;
		TestDataMatrix [14] [2] = 504.83;
		TestDataMatrix [14] [3] = 1918.92;			

		TestDataMatrix [15] [0] = 289.263;
		TestDataMatrix [15] [1] = 39;
		TestDataMatrix [15] [2] = 1199.11;
		TestDataMatrix [15] [3] = 691.50;			

		TestDataMatrix [16] [0] = 99.16;
		TestDataMatrix [16] [1] = 40;
		TestDataMatrix [16] [2] = 730.44;
		TestDataMatrix [16] [3] = 1641.83;			

		TestDataMatrix [17] [0] = 456.51;
		TestDataMatrix [17] [1] = 40;
		TestDataMatrix [17] [2] = 94.37;
		TestDataMatrix [17] [3] = 1951.99;			

		TestDataMatrix [18] [0] = 229.11;
		TestDataMatrix [18] [1] = 2;
		TestDataMatrix [18] [2] = 147.57;
		TestDataMatrix [18] [3] = 708.89;			

		TestDataMatrix [19] [0] = 59.65;
		TestDataMatrix [19] [1] = 44;
		TestDataMatrix [19] [2] = 152.98;
		TestDataMatrix [19] [3] = 68.68;			
	
		
		//Set up the variable types
		TestVariableTypes[0] = DEAVariableType.Input;
		TestVariableTypes[1] = DEAVariableType.Input;
		TestVariableTypes[2] = DEAVariableType.Output;
		TestVariableTypes[3] = DEAVariableType.Output;
		

		

	}
	

	
	public DEAPSolution GetCCRIResults() {
		
		
		DEAPSolution CCRISol = new DEAPSolution(20, 4);
		
		CCRISol.Objectives  = createCCRIObjectives();		
		
		CCRISol.Lambdas = createCCRILambdas();
		
		
		return CCRISol;
	}

	private double[] [] createCCRILambdas() {
		
		List<Lambda> Lambdas = new ArrayList<Lambda>();
		
		
		//Set the lambdas
		
		Lambda Lambda = new Lambda();
		Lambda.setDMUNumber(0);
		Lambda.setLambdaNumber(6);
		Lambda.setLambdaValue(0.292899713106514);
		Lambdas.add(Lambda);
		
		Lambda = new Lambda();
		Lambda.setDMUNumber(0);
		Lambda.setLambdaNumber(7);
		Lambda.setLambdaValue(0.116772146287042);
		Lambdas.add(Lambda);
		
		Lambda = new Lambda();
		Lambda.setDMUNumber(0);
		Lambda.setLambdaNumber(12);
		Lambda.setLambdaValue(0.153047430701115);
		Lambdas.add(Lambda);
		
		
		
		
		double[] [] LambdasArray = new double[20] [20];
		
		Iterator<Lambda> iterator;
		iterator = Lambdas.iterator();
		
		while (iterator.hasNext()) {
			Lambda = iterator.next();
			LambdasArray[Lambda.getDMUNumber()] [Lambda.getLambdaNumber()] = Lambda.getLambdaValue();
		}
		
		return LambdasArray;
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
	
	
	private static class Lambda {
		private int DMUNumber;
		private int LambdaNumber;
		private double LambdaValue;
		
		public void setDMUNumber(int dMUNumber) {
			DMUNumber = dMUNumber;
		}
		public int getDMUNumber() {
			return DMUNumber;
		}
		public void setLambdaNumber(int lambdaNumber) {
			LambdaNumber = lambdaNumber;
		}
		public int getLambdaNumber() {
			return LambdaNumber;
		}
		public void setLambdaValue(double lambdaValue) {
			LambdaValue = lambdaValue;
		}
		public double getLambdaValue() {
			return LambdaValue;
		}
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
		
		DEAPSolution CheckedSol = GetCCRIResults();
		
		double[] ObjectivesToCheck = tester.getObjectives();
		
		
		assertArrayEquals(ObjectivesToCheck, CheckedSol.Objectives,0.000001);	
	}
	
	
	
	
	
}
