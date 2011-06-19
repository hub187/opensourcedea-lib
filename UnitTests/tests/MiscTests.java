package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import dea.ModelType;

public class MiscTests {
	
	@Test
	/**
	 * Prints the number of models available in the ModelType class.
	 */
	public void testTest() {
		System.out.println("The number of available models is: " + ModelType.getNumberOfModelsAvailable());
		assertTrue(true);
	}

	
}
