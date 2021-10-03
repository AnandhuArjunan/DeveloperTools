/*
 * 
 */
package com.anandhuarjunan.developertools.core.app;

import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

public interface Validator {

	public void validate() throws ValidateError;
	
}
