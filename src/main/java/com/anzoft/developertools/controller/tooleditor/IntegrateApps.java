/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.tooleditor;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.anzoft.developertools.database.persistables.Category;
import com.anzoft.developertools.database.persistables.ExternalAppTypes;
import com.anzoft.developertools.database.persistables.ExternalTools;
import com.anzoft.developertools.database.persistables.ToolTypes;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.common.FindEntityService;
import com.anzoft.developertools.database.services.common.GetAllEntityService;
import com.anzoft.developertools.database.services.common.PersistEntity;
import com.anzoft.developertools.exceptions.ServiceException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;

public class IntegrateApps {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXComboBox<ExternalAppTypes> appType;

    @FXML
    private JFXComboBox<Category> category;

    @FXML
    private JFXTextField command;

    @FXML
    private JFXTextField browsefield;

    @FXML
    private JFXButton browse;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXButton integrate;
    
    private List<ExternalAppTypes> externalAppsTypes;
    
    private List<Category> categories;
    
    File inputJar = null;


    @FXML
    void initialize() {
    	externalAppsTypes = getExternalAppTypes();
    	categories = getCategories();
    	appType.getItems().addAll(FXCollections.observableArrayList(externalAppsTypes));
    	category.getItems().addAll(FXCollections.observableArrayList(categories));
    	appType.setOnAction(ev->{
    		ExternalAppTypes appTypes = appType.getSelectionModel().getSelectedItem();
    		if("JAR".equalsIgnoreCase(appTypes.getType())) {
    			command.setText("java -jar");
    		}
    	});
    	browse.setOnAction(e->{
    		ExternalAppTypes appTypes = appType.getSelectionModel().getSelectedItem();

        	if("JAR".equalsIgnoreCase(appTypes.getType())) {
        		FileChooser fileChooser = new FileChooser();
    			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Java Archive", "*.jar"));
    			inputJar = fileChooser.showOpenDialog(((Node) e.getSource()).getScene().getWindow());
    			if (null != inputJar && inputJar.exists()) {
    				browsefield.setText(FilenameUtils.separatorsToSystem(inputJar.getAbsolutePath()));
    			}
        	}	
    	});
    	integrate.setOnAction(e->{
    		Tools tools = new Tools();
    		tools.setClosable(true);
    		tools.setDescription(description.getText());
    		tools.setCategory(category.getSelectionModel().getSelectedItem());
    		tools.setMultiTabSupport(true);
    		tools.setVisible(true);
    		FindEntityService<ToolTypes> dbServiceq = new FindEntityService<>();
  
    		try {
				tools.setToolType(dbServiceq.findEntity(ToolTypes.class, "E").getResult());
			} catch (ServiceException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		String id = name.getText().toUpperCase();
    		tools.setId(id);
    		tools.setToolName( name.getText());
        	ExternalTools externalTools = new ExternalTools();
        	externalTools.setTools(tools);
        	externalTools.setToolCommand(command.getText());
        	externalTools.setWorkingDir(inputJar.toPath().getParent().toString());
        	externalTools.setFileName(inputJar.getName());
        	FindEntityService<ExternalAppTypes> dbServiceqq = new FindEntityService<>();
        	  
    		try {
    			externalTools.setAppType(dbServiceqq.findEntity(ExternalAppTypes.class, "JAR").getResult());
			} catch (ServiceException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		PersistEntity<Tools> dbService = new PersistEntity<>();
        	dbService.persist(tools);
        	PersistEntity<ExternalTools> dbServiceex = new PersistEntity<>();
        	dbServiceex.persist(externalTools);
        	
    	});
    	
		
    }
    
    
    private List<ExternalAppTypes> getExternalAppTypes(){
    	GetAllEntityService<ExternalAppTypes> dbService = new GetAllEntityService<>();
    	try {
			return dbService.getAllRecords(ExternalAppTypes.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    	return null;
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
}
