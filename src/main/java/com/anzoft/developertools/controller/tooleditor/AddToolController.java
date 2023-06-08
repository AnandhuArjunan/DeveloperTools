/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.tooleditor;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddToolController {

    @FXML
    private TextField toolName;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private JFXComboBox<?> toolType;

    @FXML
    private JFXComboBox<?> category;

    @FXML
    private TextField filePath;

    @FXML
    private JFXRadioButton enableRadio;

    @FXML
    private JFXRadioButton disableRadio;

    @FXML
    private Button fileBrowseButton;

}
