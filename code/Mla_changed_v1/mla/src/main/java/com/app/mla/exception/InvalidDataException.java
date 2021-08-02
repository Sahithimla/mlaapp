package com.app.mla.exception;

/**
 * @author Sahithi
 *
 */
public class InvalidDataException extends MLAServiceException {

	private static final long serialVersionUID = 7907264450408199260L;

	public InvalidDataException(String className, String methodName, String message, Throwable cause) {
		super(className, methodName, message, cause);
	}

	public InvalidDataException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public InvalidDataException(String msg) {
		super(msg);
	}

	public InvalidDataException(Throwable ex) {
		super(ex);
	}

}
