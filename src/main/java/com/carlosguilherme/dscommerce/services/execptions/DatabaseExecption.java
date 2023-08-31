package com.carlosguilherme.dscommerce.services.execptions;

public class DatabaseExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseExecption(String msn) {
		super(msn);
	}
}
