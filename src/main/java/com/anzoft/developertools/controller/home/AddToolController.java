/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.home;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.anzoft.developertools.controller.HomeDashboard;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.tooleditor.GetAllTools;
import com.anzoft.developertools.exceptions.ServiceException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class AddToolController implements Initializable {

    @FXML private ComboBox<Tools> kindComboBox;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML static AddToolController classController;
    Tools tool = null;
    private int id = 0;
    private HomeDashboard home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classController = this;
        bindComboBoxTypes();

        createButton.setOnAction(e -> {
			try {
				createButtonAction();
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        cancelButton.setOnAction(e -> cancelButtonAction());
    }
    
    

    public HomeDashboard getHome() {
		return home;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setHome(HomeDashboard home) {
		this.home = home;
	}



	private List<Tools> getTools() throws ServiceException{
		 DBService<List<Tools>>  db = new GetAllTools();
		 return  db.run(new ServiceArguments()).getResult();
	}

    private void bindComboBoxTypes() {
    	try {
			List<Tools> list =  getTools();
			if(null != list && !list.isEmpty()) {
				for(Tools tools: list) {
					kindComboBox.getItems().add(tools);
				    kindComboBox.getSelectionModel().select(0);

				}
			}
			
			
		} catch (ServiceException e) {
			
		}
      
    }

    private void createButtonAction() throws ServiceException {
        tool = kindComboBox.getSelectionModel().getSelectedItem();
        cancelButtonAction();
        home.addToolAction();
    }

    public Tools getTool() {
		return tool;
	}

	public void setTool(Tools tool) {
		this.tool = tool;
	}

	private void cancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public static AddToolController getInstance(){
        return classController;
    }
}
