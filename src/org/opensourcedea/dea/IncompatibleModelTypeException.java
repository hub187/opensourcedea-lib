package org.opensourcedea.dea;

public class IncompatibleModelTypeException extends DEAException {

	/**
	 * This Exception is thrown when a method is called but is incompatible with the ModelType (e.g. trying to get the
	 * u0 weights which correspond to the convexity constraint on a Constant RTS model).
	 */
	private static final long serialVersionUID = 1L;

	public IncompatibleModelTypeException(String detailMsg) {
		super(detailMsg);
	}
	
	public IncompatibleModelTypeException() {
		super("The method used is incompatible with the ModelType.");
	}

}
