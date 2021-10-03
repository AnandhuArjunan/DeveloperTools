/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.home;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.anandhuarjunan.developertools.core.controller.HomeDashboard;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.DBService;
import com.anandhuarjunan.developertools.core.database.services.ServiceArguments;
import com.anandhuarjunan.developertools.core.database.services.tooleditor.GetAllTools;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;

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
