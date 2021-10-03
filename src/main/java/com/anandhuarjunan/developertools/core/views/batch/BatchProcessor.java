/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.batch;

import com.anandhuarjunan.developertools.core.exceptions.ProcessError;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

public abstract class BatchProcessor {

	
	public void init() { 
		loadLabelsAndData();
		} 
	
	
	public void process() {
		try {
		validate();
		execute();
		afterExecute();
		}
		catch(ToolError e) {
			handleError(e);
		}
	}
	public void handleError(ToolError e) {
	}
	public void afterExecute() {
	}
	
	abstract void loadLabelsAndData();//Initializes labels
	abstract void execute() throws ProcessError; // Logic for the operation
	abstract void validate() throws ValidateError;


}
