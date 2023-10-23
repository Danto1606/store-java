package com.danny.store.java.exceptions;

public class UserBadRequestException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserBadRequestException() {
		super();
		
	}

	public UserBadRequestException(String message) {
		super(message);
		
	}

}
