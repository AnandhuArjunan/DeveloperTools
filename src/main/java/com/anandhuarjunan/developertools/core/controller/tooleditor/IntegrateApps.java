/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.tooleditor;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.anandhuarjunan.developertools.core.database.persistables.Category;
import com.anandhuarjunan.developertools.core.database.persistables.ExternalAppTypes;
import com.anandhuarjunan.developertools.core.database.persistables.ExternalTools;
import com.anandhuarjunan.developertools.core.database.persistables.ToolTypes;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityService;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.database.services.common.PersistEntity;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class IntegrateApps {

    @FXML
    private TextField name;

    @FXML
    private ComboBox<ExternalAppTypes> appType;

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField command;

    @FXML
    private TextField browsefield;

    @FXML
    private Button browse;

    @FXML
    private TextArea description;

    @FXML
    private Button integrate;
    
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
        	try {
				dbService.persist(tools);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	PersistEntity<ExternalTools> dbServiceex = new PersistEntity<>();
        	try {
				dbServiceex.persist(externalTools);
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
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
