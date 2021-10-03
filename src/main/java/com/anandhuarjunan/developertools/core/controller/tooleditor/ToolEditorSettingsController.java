/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.tooleditor;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class ToolEditorSettingsController {

	    @FXML
	    private Label toolName;

	    @FXML
	    private Label toolId;
	    

		@FXML
		private TextField toolCatEd;

		@FXML
		private ComboBox<String> tlCategory;

		@FXML
		private Pane editPane;
		
		@FXML
	    private TextField categoryEdit;


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
	    
	    private ToggleGroup toggleGroupForMultiTab = new ToggleGroup();
	    
	    private ToggleGroup toggleGroupForMenuVisible = new ToggleGroup();
	    
	    private ToggleGroup toggleGroupForExtTool = new ToggleGroup();



	    
	    
	    @FXML
	    void initialize() throws IOException {
	    	enableInMenu.setToggleGroup(toggleGroupForMenuVisible);
	    	disableInMenu.setToggleGroup(toggleGroupForMenuVisible);
	    	multiTabYs.setToggleGroup(toggleGroupForMultiTab);
	    	multiTabNo.setToggleGroup(toggleGroupForMultiTab);
	    	extToolYes.setToggleGroup(toggleGroupForExtTool);
	    	extToolNo.setToggleGroup(toggleGroupForExtTool);

	    }



		public Label getToolName() {
			return toolName;
		}



		public Label getToolId() {
			return toolId;
		}



		public TextField getToolCatEd() {
			return toolCatEd;
		}



		public ComboBox<String> getTlCategory() {
			return tlCategory;
		}



		public Pane getEditPane() {
			return editPane;
		}



		public TextField getCategoryEdit() {
			return categoryEdit;
		}



		public RadioButton getEnableInMenu() {
			return enableInMenu;
		}



		public RadioButton getDisableInMenu() {
			return disableInMenu;
		}



		public RadioButton getMultiTabYs() {
			return multiTabYs;
		}



		public RadioButton getMultiTabNo() {
			return multiTabNo;
		}



		public Button getUpdateBtn() {
			return updateBtn;
		}



		public RadioButton getExtToolYes() {
			return extToolYes;
		}



		public RadioButton getExtToolNo() {
			return extToolNo;
		}



		public ComboBox<?> getExtToolType() {
			return extToolType;
		}



		public Label getExtLoc() {
			return extLoc;
		}



		public Button getExtBrowseButn() {
			return extBrowseButn;
		}



		public TextField getExtLocTextView() {
			return extLocTextView;
		}

}
