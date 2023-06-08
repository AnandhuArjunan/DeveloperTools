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

public class RemoveDuplicateLines extends AbstractTool{

	RemoveDuplicateLinesUI duplicateLinesUI = new RemoveDuplicateLinesUI();
	
	void loadUIComponents() {
		duplicateLinesUI.disableToolBar();
		
	}

	
	void execute()  {
		duplicateLinesUI.output.setText(WordOperations.removeDuplicates(duplicateLinesUI.input.getText()));	
		
	}



	@Override
	public void open() throws ToolError {
		loadUIComponents();
		
	}


	@Override
	public Node view() throws ToolError {
		// TODO Auto-generated method stub
		return duplicateLinesUI;
	}
	
	class RemoveDuplicateLinesUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			execute();
			
		}

	
	}


}
