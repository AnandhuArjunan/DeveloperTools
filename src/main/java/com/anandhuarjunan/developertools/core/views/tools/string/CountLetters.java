/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.string;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.string.WordOperations;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class CountLetters extends AbstractTool {
	 RadioButton r1 = new RadioButton("Count Letters"); 
     RadioButton r2 = new RadioButton("Count Words"); 
     ToggleGroup tg = new ToggleGroup(); 

     CountLettersUI countLettersUI = new CountLettersUI();

	


	void execute() {
		RadioButton rb = (RadioButton)tg.getSelectedToggle(); 
		if(r1.getText().equalsIgnoreCase(rb.getText())) {
			int[] dt = WordOperations.countLetters(countLettersUI.input.getText());
			String text = "With Space : "+dt[0]+"\n"+"Without Space : "+dt[1];
			countLettersUI.output.setText(text);	

		}
		else if(r2.getText().equalsIgnoreCase(rb.getText())) {
			countLettersUI.output.setText(String.valueOf(WordOperations.countWords(countLettersUI.input.getText())));	

		}
		
	}



	@Override
	public void open() throws ToolError {
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r1.setSelected(true);
		countLettersUI.addItemsToToolBar(r1,r2);
		ChangeListener<Toggle> listener=(ObservableValue<? extends Toggle> ob,  
                Toggle o, Toggle n) ->execute();
		tg.selectedToggleProperty().addListener(listener);
		
	}

	@Override
	public Node view() throws ToolError {
		
		return countLettersUI;
	}

	
	
	class CountLettersUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			execute();
			
		}

	
	}

}
