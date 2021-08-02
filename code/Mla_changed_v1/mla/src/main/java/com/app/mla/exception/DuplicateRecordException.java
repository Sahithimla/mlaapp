package com.app.mla.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author Sahithi
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends MLAServiceException {

	private static final long serialVersionUID = -9159417738942120593L;

	public DuplicateRecordException(String className, String methodName, String message, Throwable cause) {
		super(className, methodName, message, cause);
	}

	public DuplicateRecordException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public DuplicateRecordException(String msg) {
		super(msg);
	}

	public DuplicateRecordException(Throwable ex) {
		super(ex);
	}

}
