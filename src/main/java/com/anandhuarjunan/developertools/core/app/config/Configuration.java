/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.config;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public interface Configuration<T> {

	
	void loadConfiguration() throws InternalError;
		
	void reloadConfiguration()  throws InternalError;
	
	T getConfiguration();
	
	
	
}
