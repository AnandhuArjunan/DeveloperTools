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
import javafx.scene.control.TextField;

public class RepeatText extends AbstractTool {
	
	RepeatTextUI repeatTextUI = new RepeatTextUI();
	TextField textField ;
	Label sepL = new Label(Labels.SEPARATED_BY);
	Label noOfTimesL = new Label(Labels.NO_OF_TIMES);

	ComboBox<String> items;
	
	
	void loadUIComponents() {
	textField = new TextField(String.valueOf(2));
	textField.setText(String.valueOf(2));
	@SuppressWarnings("unchecked")
	String[] types = 
    {Labels.NEW_LINE,Labels.COMMA,Labels.PIPE,Labels.TAB,Labels.SPACE,Labels.DOT}; 
    items= new ComboBox<>(FXCollections .observableArrayList(types));
    items.getItems().addAll();
	items.getSelectionModel().selectFirst();
	repeatTextUI.addItemsToToolBar(sepL,items,noOfTimesL,textField);
	}




	private String convertToDelimiterType(String selectedItem) {
		if(Labels.NEW_LINE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_NEW_LINE;
		}
		else if(Labels.COMMA.equalsIgnoreCase(selectedItem)) {
			return Constants.COMMA;
		}	
		else if(Labels.PIPE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_PIPE;
		}
		else if(Labels.TAB.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_TAB;
		}	
		else if(Labels.SPACE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_SPACE;
		}
		else if(Labels.DOT.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_DOT;
		}
		else 
			return null;
		
	}


	
	void execute() {
		if(!repeatTextUI.input.getText().isEmpty()) {
			repeatTextUI.output.setText(WordOperations.repeatText(repeatTextUI.input.getText(), Integer.parseInt(textField.getText()),convertToDelimiterType(items.getValue()) ));	
		}
		
	}


	class RepeatTextUI extends CompareToolStyle{

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
		return repeatTextUI;
	}
}
