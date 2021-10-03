/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.tooleditor;

import java.io.IOException;

import com.anandhuarjunan.developertools.core.utils.FXWindowUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ToolMakerController {
	 @FXML
	    private Label toolName;

	    @FXML
	    private Label toolId;

	    @FXML
	    private RadioButton enableInMenu;

	    @FXML
	    private RadioButton disableInMenu;

	    @FXML
	    private RadioButton multiTabYs;

	    @FXML
	    private RadioButton multiTabNo;

	    @FXML
	    private Button updateBtn;

	    @FXML
	    private RadioButton extToolYes;

	    @FXML
	    private RadioButton extToolNo;

	    @FXML
	    private ComboBox<?> extToolType;

	    @FXML
	    private Label extLoc;

	    @FXML
	    private Button extBrowseButn;

	    @FXML
	    private TextField extLocTextView;

	    @FXML
	    void changeCategory(MouseEvent event) {
	    	try {
				FXWindowUtils.loadUndecoratedStage("/fxml/tooleditor/ChooseCategory.fxml",new Stage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
}
