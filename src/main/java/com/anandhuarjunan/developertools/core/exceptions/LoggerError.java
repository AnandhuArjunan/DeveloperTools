
package com.anandhuarjunan.developertools.core.exceptions;

public class LoggerError extends InternalError {
	private static final long serialVersionUID = 1L;
	public LoggerError(String msg){
		super(msg);
	}
	
	public LoggerError(String msg, Throwable cause){
	    super(msg, cause);
	}

}
