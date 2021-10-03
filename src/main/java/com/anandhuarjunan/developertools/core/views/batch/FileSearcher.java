/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.batch;

import com.anandhuarjunan.developertools.core.exceptions.ProcessError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FileSearcher extends BatchProcessor{
	TextField findWith = new TextField();
	Label findWithL = new Label("Find In");
	Button b = new Button("Start");
	@Override
	void loadLabelsAndData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void execute() throws ProcessError {
		// TODO Auto-generated method stub
		
	}
	@Override
	void validate() throws ValidateError {
		// TODO Auto-generated method stub
		
	}
	

}
