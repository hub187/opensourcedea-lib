package tests;

import org.junit.Test;

import dea.DEAException;
import dea.DEAProblem;
import dea.InvalidPropertyValue;
import dea.ModelType;

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
		} catch (InvalidPropertyValue e) {
			e.printStackTrace();
		}
		try {
			tester.setRTSUpperBound(1.2);
		} catch (InvalidPropertyValue e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CheckWeightArrayLength() throws Exception {
		
		for(ModelType modType : ModelType.values()) {
			tester = new DEAProblem(20, 4);
			BuildDEAProblem(modType);
			try {
				tester.solve();
			}
			catch (DEAException e) {
				e.printStackTrace();
			}
			if (tester.getWeight(0).length > 4) {
				//System.out.println("Model Type: " + modType.toString() + " has more than 4 weights");
			}
		}
		
		
	}
	
}
