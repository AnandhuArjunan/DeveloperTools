/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.plugins;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.zeroturnaround.zip.ZipUtil;

import com.anzoft.developertools.database.persistables.Category;
import com.anzoft.developertools.database.persistables.FxmlLoaderTools;
import com.anzoft.developertools.database.persistables.ToolTypes;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.common.FindEntityService;
import com.anzoft.developertools.database.services.common.GetAllEntityService;
import com.anzoft.developertools.database.services.common.PersistEntity;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.utils.FileUtils;
import com.anzoft.developertools.utils.PropertyUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class ImportPluginController {

    @FXML
    private JFXTextField pluginName;

    @FXML
    private JFXButton generateBtn;

    @FXML
    private JFXTextField filelocation;

    @FXML
    private JFXButton browse;

    @FXML
    private JFXTextField pluginID;

    @FXML
    private JFXTextField pluginDesc;

    @FXML
    private HBox resultPane;

    @FXML
    private Label resultLabel;
    
    @FXML
    private JFXComboBox<Category> category;
    
    private List<Category> categories;

    
 
    
    @FXML
    void initialize() {
    	categories = getCategories();
    	category.getItems().addAll(FXCollections.observableArrayList(categories));
    	generateBtn.setOnAction(ev->importPlugin());
    	browse.setOnAction(ev->choosePlugin(ev));
    	pluginName.textProperty().addListener(listener->pluginID.setText(pluginName.getText().toUpperCase()));

    }

    private List<Category> getCategories(){
    	GetAllEntityService<Category> dbService = new GetAllEntityService<>();
    	try {
			return dbService.getAllRecords(Category.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	return null;
    }


	private void choosePlugin(ActionEvent ev) {
		resultPane.setVisible(false);
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Java Archive", "*.dtpl"));
		File inputJar = fileChooser.showOpenDialog(((Node) ev.getSource()).getScene().getWindow());
		if (null != inputJar && inputJar.exists()) {
			filelocation.setText(FilenameUtils.separatorsToSystem(inputJar.getAbsolutePath()));
		}
	}




	private void importPlugin() {
		
		Tools tools = new Tools();
		tools.setClosable(true);
		tools.setDescription(pluginDesc.getText());
		tools.setCategory(category.getSelectionModel().getSelectedItem());
		tools.setMultiTabSupport(true);
		tools.setVisible(true);
		FindEntityService<ToolTypes> dbServiceq = new FindEntityService<>();

		try {
			tools.setToolType(dbServiceq.findEntity(ToolTypes.class, "F").getResult());
		} catch (ServiceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String id = pluginID.getText();
		tools.setId(id);
		tools.setToolName( pluginName.getText());
		
		FxmlLoaderTools fxmlLoaderTools = new FxmlLoaderTools();
		fxmlLoaderTools.setTools(tools);
		try {
		byte[] bytes = ZipUtil.unpackEntry(new File(filelocation.getText()), "DTPLUGIN.properties");
    	String content = new String(bytes,StandardCharsets.UTF_8);
    	Properties properties  = PropertyUtils.parsePropertiesString(content);
		fxmlLoaderTools.setFxmlPath(properties.getProperty("FXML"));
		fxmlLoaderTools.setStylesheets(properties.getProperty("CSS"));

		PersistEntity<Tools> dbService = new PersistEntity<>();
    	dbService.persist(tools);
    	PersistEntity<FxmlLoaderTools> dbServiceex = new PersistEntity<>();
    	dbServiceex.persist(fxmlLoaderTools);
    	
    	File file =new File(filelocation.getText());
    	String tempLoc = "plugins"+File.separator+file.getName();
    	File dest = new File(tempLoc);
    	
    	
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		resultPane.setVisible(true);
		resultLabel.setText("Imported Successfully..");
		

	}
    

}
