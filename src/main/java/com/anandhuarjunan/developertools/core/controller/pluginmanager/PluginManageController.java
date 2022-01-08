/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.pluginmanager;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.controller.servicemanager.ServiceManager;
import com.anandhuarjunan.developertools.core.controller.toolloaders.ToolLoaderFactory;
import com.anandhuarjunan.developertools.core.controller.toolloaders.custom.CustomFXMLLoaderFactory;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.views.CustomFxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PluginManageController {


    @FXML
    private TextField selectPluginTextField;

    @FXML
    private Button selectPluginButton;

    @FXML
    private Button installedAddon;

    @FXML
    private Button importFromFS;

    @FXML
    private Label repoURL;

    @FXML
    private Button urlChangeButton;

    @FXML
    private TableView<?> pluginDataTableView;

    @FXML
    private TableColumn<?, ?> nameTableColumn;

    @FXML
    private TableColumn<?, ?> synopsisTableColumn;

    @FXML
    private TableColumn<?, ?> versionTableColumn;

    @FXML
    private TableColumn<?, ?> downloadTableColumn;
    
    @FXML
    private Button convertJar;

    @FXML
    private Button createPlugin;

    @FXML
    void searchPlugin(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	importFromFS.setOnAction(e->onClickImport());
    	createPlugin.setOnAction(e->onClickCreate());
    }

	private void onClickImport() {
		ToolLoaderFactory loaderFactory = UIResources.getInstance().getTabLoader();
    	loaderFactory.loadTool("IMPORT_LOCAL_PLUGINS");
	}
	private void onClickCreate() {
		CustomFXMLLoaderFactory customFXMLLoaderFactory = UIResources.getInstance().getMainController().getCustomFXMLLoaderFactory();
		CustomFxml customFxml = new CustomFxml("Create Plugins", "/fxml/pluginmanager/CreatePlugin.fxml",new CreatePluginController());    
		customFXMLLoaderFactory.loadFXML(customFxml);
	}
    
}
