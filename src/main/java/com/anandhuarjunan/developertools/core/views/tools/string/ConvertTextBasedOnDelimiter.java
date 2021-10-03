
/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.app.messages.Labels;
import com.anandhuarjunan.developertools.core.exceptions.ProcessError;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.utils.StringUtils;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.string.WordOperations;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ConvertTextBasedOnDelimiter extends AbstractTool{
	ConvertTexBasedOnDelimiterUI basedOnDelimiterUI = new ConvertTexBasedOnDelimiterUI();
	ComboBox<String> inputSep;
	ComboBox<String> outputSep;
	Label inputSepLabel = new Label(Labels.SEPARATED_BY);
	Label outputSepLabel = new Label(Labels.SEPARATED_TO);


	
	

	
	void execute() throws ProcessError {	
		basedOnDelimiterUI.output.setText(WordOperations.convertBasedOnDelimiter(basedOnDelimiterUI.input.getText(), StringUtils.convertToDelimiterType(inputSep.getValue()), StringUtils.convertToDirectCharType(outputSep.getValue())));	
	}


	class ConvertTexBasedOnDelimiterUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			try {
				execute();
			} catch (ProcessError e) {
				
			}
			
		}

	
	}


	@Override
	public void open() throws ToolError {
		String[] types = 
            { Labels.COMMA,Labels.DOT,Labels.TAB,Labels.NEW_LINE,Labels.SPACE,Labels.PIPE }; 
		inputSep= new ComboBox<>(FXCollections .observableArrayList(types));
		inputSep.getSelectionModel().selectFirst();
		outputSep= new ComboBox<>(FXCollections .observableArrayList(types));
		outputSep.getSelectionModel().selectFirst();
		basedOnDelimiterUI.addItemsToToolBar(inputSepLabel,inputSep,outputSepLabel,outputSep);
		
	}


	@Override
	public Node view() throws ToolError {
		return basedOnDelimiterUI;
	}

}
