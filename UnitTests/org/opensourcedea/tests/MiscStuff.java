package org.opensourcedea.tests;

import org.junit.Test;

import org.opensourcedea.dea.DEAException;
import org.opensourcedea.dea.DEAProblem;
import org.opensourcedea.dea.InvalidPropertyValueException;
import org.opensourcedea.dea.ModelType;

public class MiscStuff {
	
	DEAProblem tester;// = new DEAProblem(20, 4);
	
	public void BuildDEAProblem(ModelType ModelType) { //, DEAModelOrientation ModelOrientation) {
		
		tester.setModelType(ModelType);
		//tester.setModelOrientation(ModelOrientation);
		tester.setVariableNames(TestData.createTestVariableNames());
		tester.setVariableOrientations(TestData.createTestVariableOrientation());
		tester.setDataMatrix(TestData.createTestDataMatrix());
		tester.setDMUNames(TestData.createTestDMUNames());
		try {
			tester.setRTSLowerBound(0.8);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
		try {
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValueException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CheckWeightArrayLength() throws Exception {
		
		for(ModelType modType : ModelType.values()) {
			tester = new DEAProblem(20, 4);
			BuildDEAProblem(modType);
			try {
				//System.out.print("Solving: " + tester.getModelType().toString() + "\r\n");
				tester.solve();
			}
			catch (DEAException e) {
				System.out.print("There was a problem solving model: " + tester.getModelType().toString());
				e.printStackTrace();
			}
			if (tester.getWeight(0).length > 4) {
				System.out.println("Model Type: " + modType.toString() + " has: " + tester.getWeight(0).length + " weights");
			}
		}
		
		
	}
	
}
