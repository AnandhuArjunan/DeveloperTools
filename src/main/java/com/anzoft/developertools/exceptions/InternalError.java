/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.exceptions;

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
