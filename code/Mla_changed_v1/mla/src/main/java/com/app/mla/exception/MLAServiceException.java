package com.app.mla.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Sahithi
 * 
 */
public class MLAServiceException extends Exception {

	private static final long serialVersionUID = 9013171275718934501L;
	private String className;
	private String methodName;
	private String localStackTrace;
	private String nestedStackTrace;
	private Throwable cause;

	public MLAServiceException(String className, String methodName,
			String message, Throwable cause) {
		super(message);
		this.className = className != null ? className : "";
		this.methodName = methodName != null ? methodName : "";
		this.cause = cause;
		this.localStackTrace = MLAServiceException
				.computeStackTraceAsString(this);
		this.nestedStackTrace = MLAServiceException
				.computeStackTraceAsCauseString(cause);
	}


	public MLAServiceException(String msg) {
		super(msg);
		
	}
	public MLAServiceException(String msg, Throwable ex) {
		super(msg, ex);
		
	}

	public MLAServiceException(Throwable ex) {
		super(ex);
		
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	protected static String computeStackTraceAsString(Throwable throwable) {
		if (throwable == null) {
			return "";
		}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		if (throwable instanceof MLAServiceException) {
			((MLAServiceException) throwable).basePrintStackTrace(pw);
		} else {
			throwable.printStackTrace(pw);
		}
		pw.flush();

		return sw.toString();
	}

	public Throwable getCause() {
		return cause;
	}

	public String getCompleteStackTrace() {
		return localStackTrace + nestedStackTrace + "\n";
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream ps) {
		ps.print(localStackTrace);
		ps.println(nestedStackTrace);
	}

	public void printStackTrace(PrintWriter pw) {
		pw.print(localStackTrace);
		pw.println(nestedStackTrace);
	}

	protected final void basePrintStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
	}

	protected final void basePrintStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
	}

	public String toString() {
		String s = getClass().getName();
		String message = getLocalizedMessage();
		return ((message != null) ? (s + ": " + message) : s) + " in "
				+ className + "." + methodName;
	}

	protected static String computeStackTraceAsCauseString(Throwable cause) {
		return computeStackTraceAsCauseString(null, cause);
	}

	protected static String computeStackTraceAsCauseString(StringBuffer sb,
			Throwable throwable) {
		if (throwable == null) {
			return "";
		}

		StringBuffer traceBuffer = sb != null ? sb : new StringBuffer();
		traceBuffer.append("Caused by: " + throwable);
		traceBuffer
				.append("\n"
						+ computeStackTraceAsString(throwable instanceof InvocationTargetException ? ((InvocationTargetException) throwable)
								.getTargetException() : throwable));

		if (throwable instanceof MLAServiceException
				&& ((MLAServiceException) throwable).getCause() != null) {
			computeStackTraceAsCauseString(traceBuffer,
					((MLAServiceException) throwable).getCause());
		}

		return traceBuffer.toString();
	}

}

