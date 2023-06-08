/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.string;

import com.anzoft.commonlibs.constants.Constants;
import com.anzoft.commonlibs.string.WordOperations;
import com.anzoft.developertools.app.messages.Labels;
import com.anzoft.developertools.exceptions.ProcessError;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.CompareToolStyle;
import com.anzoft.developertools.views.AbstractTool;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class CountLines extends AbstractTool {

	ComboBox<String> comboBox ;
	Label sep = new Label(Labels.SEPARATED_BY);

	CountLinesUI countLinesUI = new CountLinesUI();
	private String convertToDelimiterType(String selectedItem) {
		if(Labels.NEW_LINE.equalsIgnoreCase(selectedItem)) {
			return Constants.NEW_LINE;
		}
		else if(Labels.COMMA.equalsIgnoreCase(selectedItem)) {
			return Constants.COMMA;
		}	
		else if(Labels.PIPE.equalsIgnoreCase(selectedItem)) {
			return Constants.PIPE;
		}
		else if(Labels.TAB.equalsIgnoreCase(selectedItem)) {
			return Constants.TAB;
		}	
		else if(Labels.SPACE.equalsIgnoreCase(selectedItem)) {
			return Constants.SPACE;
		}
		else if(Labels.DOT.equalsIgnoreCase(selectedItem)) {
			return Constants.DOT;
		}
		else 
			return null;
		
	}

	

	
	void loadUIComponents() {
		String[] delimtedBy = {Labels.NEW_LINE,Labels.COMMA,Labels.PIPE,Labels.TAB,Labels.SPACE,Labels.DOT};
		comboBox = new ComboBox<>(FXCollections .observableArrayList(delimtedBy)) ;
		comboBox.getSelectionModel().selectFirst();
		countLinesUI.splitpane.setDividerPositions(0.8);
		countLinesUI.output.setFont(Font.font(50));
		countLinesUI.toolbar.getItems().addAll(sep,comboBox);
		
	}

	void execute() throws ProcessError {
		String selectedItem = comboBox.getValue();
		countLinesUI.output.setText(String.valueOf(WordOperations.countLines(countLinesUI.input.getText(),convertToDelimiterType(selectedItem))));
		
	}

	
	
	class CountLinesUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			try {
				execute();
			} catch (ProcessError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	
	}



	@Override
	public void open() throws ToolError {
		loadUIComponents();
		
	}




	@Override
	public Node view() throws ToolError {
		
		return countLinesUI;
	}

}
