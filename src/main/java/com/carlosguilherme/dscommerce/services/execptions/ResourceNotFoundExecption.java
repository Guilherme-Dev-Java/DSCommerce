package com.carlosguilherme.dscommerce.services.execptions;

public class ResourceNotFoundExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundExecption(String msn) {
		super(msn);
	}
}
