/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.tooleditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddToolController {

    @FXML
    private TextField toolName;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<?> toolType;

    @FXML
    private ComboBox<?> category;

    @FXML
    private TextField filePath;

    @FXML
    private RadioButton enableRadio;

    @FXML
    private RadioButton disableRadio;

    @FXML
    private Button fileBrowseButton;

}
