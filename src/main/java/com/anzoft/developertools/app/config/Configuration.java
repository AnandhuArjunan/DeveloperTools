/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.config;

import com.anzoft.developertools.exceptions.InternalError;

public interface Configuration<T> {

	
	void loadConfiguration() throws InternalError;
		
	void reloadConfiguration()  throws InternalError;
	
	T getConfiguration();
	
	
	
}
