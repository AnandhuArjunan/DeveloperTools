
/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.string;

import com.anzoft.commonlibs.string.WordOperations;
import com.anzoft.developertools.app.messages.Labels;
import com.anzoft.developertools.exceptions.ProcessError;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.CompareToolStyle;
import com.anzoft.developertools.utils.StringUtils;
import com.anzoft.developertools.views.AbstractTool;

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
