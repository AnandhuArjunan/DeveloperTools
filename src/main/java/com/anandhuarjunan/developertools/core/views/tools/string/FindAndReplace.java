/*
 * 
 */

package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.string.WordOperations;

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
