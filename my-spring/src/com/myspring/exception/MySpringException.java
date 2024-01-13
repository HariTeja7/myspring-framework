package com.myspring.exception;

/**
 * <p>
 * MySpringException is custom framework exception
 * </p>
 */
public class MySpringException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MySpringException() {
		super();
	}

	public MySpringException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySpringException(String message) {
		super(message);
	}

}
