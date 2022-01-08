/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.app.messages.Labels;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anandhuarjunan.developertools.core.views.ClosableTool;
import com.anzoft.commonlibs.constants.Constants;
import com.anzoft.commonlibs.string.WordOperations;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ConvertCase extends AbstractTool{
	ConvertCaseUI caseUI = new ConvertCaseUI();
	ComboBox<String> items = null;
	
	@Override
	public void open() {
		
		
		Label caseConvType = new Label(Labels.CASE_CONV_TYPE_LABEL);
			String[] types = 
	            { Labels.CAMEL_CASE,Labels.LOWER_CASE,Labels.UPPER_CASE,Labels.TITLE_CASE }; 
			items= new ComboBox<>(FXCollections .observableArrayList(types));
			items.getSelectionModel().selectFirst();
			caseUI.addItemsToToolBar(caseConvType);
			caseUI.addItemsToToolBar(items);	
			items.setOnAction(e->onchangeCaseType());
			caseUI.output.setText("ccc");   
			
	}



	 void execute() {
		String selectedItem = items.getSelectionModel().getSelectedItem();
		caseUI.output.setText(WordOperations.caseConversion(caseUI.input.getText(), convertToCaseConvType(selectedItem)));
		
	}
	@Override
	public Node view() {
		return caseUI;
	}
	private String convertToCaseConvType(String selectedItem) {
		if(Labels.CAMEL_CASE.equalsIgnoreCase(selectedItem)) {
			return Constants.CAMEL_CASE;
		}
		else if(Labels.LOWER_CASE.equalsIgnoreCase(selectedItem)) {
			return Constants.LOWER_CASE;
		}	
		else if(Labels.UPPER_CASE.equalsIgnoreCase(selectedItem)) {
			return Constants.UPPER_CASE;
		}
		else if(Labels.TITLE_CASE.equalsIgnoreCase(selectedItem)) {
			return Constants.TITLE_CASE;
		}
		else 
			return null;
		
	}
	
	
	private void onchangeCaseType() {
		execute();
	}
	

	class ConvertCaseUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			execute();
			
		}

		
		
		
		
	}


	

	
}
