/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.app.messages.Labels;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.constants.Constants;
import com.anzoft.commonlibs.string.WordOperations;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class RemoveBlankLines extends AbstractTool{
	Label entryLabel = new Label(Labels.REPLACE_WITH);
	ComboBox<String> combobox;
	RemoveBlankLinesUI blankLinesUI = new RemoveBlankLinesUI();

	private String convertToCharType(String selectedItem) {
		if(Labels.COMMA.equalsIgnoreCase(selectedItem)) {
			return Constants.COMMA;
		}
		else if(Labels.EMPTY.equalsIgnoreCase(selectedItem)) {
			return Constants.EMPTY;
		}	
		else if(Labels.PIPE.equalsIgnoreCase(selectedItem)) {
			return Constants.PIPE;
		}

		else 
			return null;
		
	}


	
	void loadUIComponents() {
		String[] delimtedBy = {Labels.EMPTY,Labels.PIPE,Labels.COMMA};
		combobox = new ComboBox<>(FXCollections .observableArrayList(delimtedBy)) ;
		combobox.getSelectionModel().selectFirst();
		blankLinesUI.addItemsToToolBar(entryLabel,combobox);
		
	}

	
	void execute()  {
		String selectedItem = combobox.getValue();
		blankLinesUI.output.setText(WordOperations.removeBlankLinesWith(blankLinesUI.input.getText(),convertToCharType(selectedItem)));
		
	}

	
	

	class RemoveBlankLinesUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			execute();
			
		}

	
	}




	@Override
	public void open() throws ToolError {
		// TODO Auto-generated method stub
		loadUIComponents();
	}



	@Override
	public Node view() throws ToolError {
		// TODO Auto-generated method stub
		return blankLinesUI;
	}
}
