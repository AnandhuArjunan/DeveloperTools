/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */

package com.anzoft.developertools.views.tools.string;

import com.anzoft.commonlibs.string.WordOperations;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.CompareToolStyle;
import com.anzoft.developertools.views.AbstractTool;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindAndReplace extends AbstractTool {

	FindAndReplaceUI findAndReplaceUI = new FindAndReplaceUI();
	TextField replWith = new TextField();
	TextField findWith = new TextField();
	Label findWithL = new Label("Find With");
	Label repWithL = new Label("Replace With");
	
	
	void loadUIComponents() {
		replWith.setOnKeyPressed(event->execute());
		findWithL.setOnKeyPressed(event->execute());
		findWith.setOnKeyReleased(event->execute());
		replWith.setOnKeyReleased(event->execute());
		findAndReplaceUI.toolbar.getItems().addAll(findWithL,findWith,repWithL,replWith);
		
	}

	
	void execute() {
		findAndReplaceUI.output.setText(WordOperations.findAndReplace(findAndReplaceUI.input.getText(),findWith.getText(),replWith.getText()));
		
	}


	class FindAndReplaceUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			execute();
			
		}

	
	}


	@Override
	public void open() throws ToolError {
		loadUIComponents();
	}

	@Override
	public Node view() throws ToolError {
		// TODO Auto-generated method stub
		return findAndReplaceUI;
	}

}
