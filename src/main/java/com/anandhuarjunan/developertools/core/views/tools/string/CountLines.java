/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.app.messages.Labels;
import com.anandhuarjunan.developertools.core.exceptions.ProcessError;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.constants.Constants;
import com.anzoft.commonlibs.string.WordOperations;

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
