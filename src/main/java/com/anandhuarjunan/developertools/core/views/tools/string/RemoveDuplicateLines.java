/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.string.WordOperations;

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
