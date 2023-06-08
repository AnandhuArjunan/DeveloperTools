/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app;

import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.function.Action;

public interface ActionExecutor {

	
	public  Action  action();
	
	public default void executeAction() throws InternalError {
		Action action = action();
		if(null != action) {
			action.execue();
		}
	}
	
	
	
	
}
