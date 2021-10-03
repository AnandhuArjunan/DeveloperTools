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

public class AppendAWord extends AbstractTool
{
	TextField append = new TextField();
	Label appendL = new Label(Labels.APPEND_WITH);
	Label appendType = new Label(Labels.APPEND_AT);
	Label sep = new Label(Labels.SEPARATED_BY);
	ComboBox<String> appendTypesCombo ;
	ComboBox<String> delimitedBy;
	AppendAWordUI appendAWordUI = new AppendAWordUI();

	
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
		else if(Labels.EMPTY.equalsIgnoreCase(selectedItem)) {
			return Constants.EMPTY;
		}
		else 
			return null;
		
	}
	
	private byte getMode(String apm) {
	if(Labels.APPENDATSTART.equalsIgnoreCase(apm)) {
		return 0;
	}
	else if(Labels.APPENDATEND.equalsIgnoreCase(apm)) {
		return 1;
	}
	else if(Labels.APPENDATSTARTANDEND.equalsIgnoreCase(apm)) {
		return 2;
	}
	return 3;
	}

	

	
	void execute() {
		String selectedItem = delimitedBy.getValue();
		appendAWordUI.output.setText(String.valueOf(WordOperations.appendAtStartOrEnd(getMode(appendTypesCombo.getValue()), appendAWordUI.input.getText(), append.getText(), convertToDelimiterType(selectedItem))));

	}
	

	@Override
	public void open() throws ToolError {
		String[] appendTypes = 
            { Labels.APPENDATSTART,Labels.APPENDATEND,Labels.APPENDATSTARTANDEND }; 
		String[] delimiter = {Labels.EMPTY,Labels.NEW_LINE,Labels.COMMA,Labels.PIPE,Labels.TAB,Labels.SPACE,Labels.DOT};
		appendTypesCombo= new ComboBox<>(FXCollections .observableArrayList(appendTypes));
		appendTypesCombo.getSelectionModel().selectFirst();
		delimitedBy = new ComboBox<>(FXCollections .observableArrayList(delimiter));
		delimitedBy.getSelectionModel().selectFirst();
		appendAWordUI.addItemsToToolBar(appendType,appendTypesCombo,sep,delimitedBy,appendL,append);

		
	}

	@Override
	public Node view() throws ToolError {
		
		return appendAWordUI;
	}
	
	
	class AppendAWordUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			execute();
			
		}

	
	}
}
