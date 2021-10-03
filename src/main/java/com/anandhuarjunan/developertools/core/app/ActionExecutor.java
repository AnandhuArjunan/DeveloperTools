/*
 * 
 */
package com.anandhuarjunan.developertools.core.app;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.function.Action;

public interface ActionExecutor {

	
	public  Action  action();
	
	public default void executeAction() throws InternalError {
		Action action = action();
		if(null != action) {
			action.execue();
		}
	}
	
	
	
	
}
