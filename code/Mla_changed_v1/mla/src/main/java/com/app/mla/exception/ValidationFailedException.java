package com.app.mla.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sahithi
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationFailedException extends MLAServiceException {

	private static final long serialVersionUID = 7907264450408199260L;

	public ValidationFailedException(String className, String methodName, String message, Throwable cause) {
		super(className, methodName, message, cause);
	}

	public ValidationFailedException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public ValidationFailedException(String msg) {
		super(msg);
	}

	public ValidationFailedException(Throwable ex) {
		super(ex);
	}

}
