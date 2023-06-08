/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.batch;

import com.anzoft.developertools.exceptions.ProcessError;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.exceptions.ValidateError;

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
