/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.pluginmanager;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.controller.toolloaders.ToolLoaderFactory;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.exceptions.ToolError;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PluginManageController {

    @FXML
    private TextField selectPluginTextField;

    @FXML
    private Button selectPluginButton;

    @FXML
    private TableView<Tools> pluginDataTableView;

    @FXML
    private TableColumn<?, ?> nameTableColumn;

    @FXML
    private TableColumn<?, ?> synopsisTableColumn;

    @FXML
    private TableColumn<?, ?> versionTableColumn;

    @FXML
    private TableColumn<?, ?> isEnableTableColumn;

    @FXML
    private TableColumn<?, ?> downloadTableColumn;
    

    @FXML
    private JFXButton importFrom;

    @FXML
    void searchPlugin(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	importFrom.setOnAction(e->onClickImport());

    }

	private void onClickImport() {
		ToolLoaderFactory loaderFactory = UIResources.getInstance().getTabLoader();
    	loaderFactory.loadTool("IMPORT_LOCAL_PLUGINS");
	}
    
}
