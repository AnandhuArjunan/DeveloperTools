
package com.anandhuarjunan.developertools.core.exceptions;

public class InternalError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InternalError(String msg) {
			super(msg);
	}
	public InternalError(String msg,Throwable e) {
		super(msg,e);
}
	
	
	

}
