package org.opensourcedea.test;

import org.opensourcedea.dea.VariableOrientation;
import org.opensourcedea.dea.VariableType;

public class TestData {
	
	
	public static String[] createTestDMUNames() {
		String[] TestDMUNames = new String[20];
		
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
		
		return TestDMUNames;
	}
	
	public static String[] createTestVariableNames() {
		String[] TestVariableNames = new String[4];
		
		//Set up the Variable Names
		TestVariableNames[0] = "Surface";
		TestVariableNames[1] = "Number of Employees";
		TestVariableNames[2] = "Marketing Profits";
		TestVariableNames[3] = "Sales Profits";
		
		return TestVariableNames;
	}
	
	
	public static double[][] createTestDataMatrix() {
		double[][] TestDataMatrix = new double[20][4];
		
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
		
		return TestDataMatrix;
	}
	
	public static VariableOrientation[] createTestVariableOrientation() {

		VariableOrientation[] testVariableOrientations = new VariableOrientation[4];
						
		//Set up the variable types
		testVariableOrientations[0] = VariableOrientation.INPUT;
		testVariableOrientations[1] = VariableOrientation.INPUT;
		testVariableOrientations[2] = VariableOrientation.OUTPUT;
		testVariableOrientations[3] = VariableOrientation.OUTPUT;
		
		return testVariableOrientations;
	}
	
	public static VariableType[] createTestVariableTypes(VariableType varType) {
		VariableType[] testVariableTypes = new VariableType[4];
		
		//Set up the variable types
		testVariableTypes[0] = VariableType.STANDARD;
		testVariableTypes[1] = varType;
		testVariableTypes[2] = varType;
		testVariableTypes[3] = VariableType.STANDARD;
		
		return testVariableTypes;
	}
	

}
