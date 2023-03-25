package com.hms.exceptions;

public class UserAlreadyExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }

}
