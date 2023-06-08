/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.servicemanager;

import java.util.List;
import java.util.stream.Collectors;

import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.services.background.AbstractBackgroundService;
import com.anzoft.developertools.services.background.BackgroundService;
import com.anzoft.developertools.services.background.ServiceExecuter;
import com.anzoft.developertools.services.background.ServiceStatus;
import com.anzoft.developertools.services.background.ServiceTypes;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ServiceManager {

    @FXML
    private TextField selectServiceTextField;

    @FXML
    private Button inbuilt;
  
    @FXML
    private Button allBtn;

    @FXML
    private Button runningServicesBtn;

    @FXML
    private Button stoppedServiceBtn;

    @FXML
    private FontAwesomeIconView refresh;
    
    @FXML
    private TableColumn<AbstractBackgroundService, String> id;

    @FXML
    private TableColumn<AbstractBackgroundService, String> nameTableColumn;

    @FXML
    private TableColumn<AbstractBackgroundService, String> serviceClass;

    @FXML
    private TableColumn<AbstractBackgroundService, ServiceTypes> serviceType;

    @FXML
    private TableColumn<AbstractBackgroundService, ServiceStatus> serviceStatus;

    @FXML
    private TableColumn<AbstractBackgroundService, Void> action;

    
    @FXML
    private TableColumn<AbstractBackgroundService, String> description;
    

    @FXML
    private TableView<AbstractBackgroundService> serviceDataTableView;
    
    
    @FXML
    void initialize() {
	
	
	
	loadServices();
	loadSearchEvent();
	loadButtonEvents();

	id.setCellValueFactory(new PropertyValueFactory<>("id"));
	nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	serviceType.setCellValueFactory(new PropertyValueFactory<>("serviceTypes"));
	serviceStatus.setCellValueFactory(new PropertyValueFactory<>("serviceStatus"));
	serviceClass.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getClass().getName()));
	serviceType.setCellValueFactory(c-> new SimpleObjectProperty<ServiceTypes>(c.getValue().serviceType()));
	description.setCellValueFactory(new PropertyValueFactory<>("description"));
	addActionButtonToTable();
	highlightRows();
}
    
    
    
    
    private void highlightRows() {
	serviceDataTableView.setRowFactory(tv -> new TableRow<AbstractBackgroundService>() {
	    @Override
	    protected void updateItem(AbstractBackgroundService item, boolean empty) {
	        super.updateItem(item, empty);
	        if (item == null || item.getServiceStatus() == null)
	            setStyle("");
	        else if (item.getServiceStatus() == ServiceStatus.RUNNING)
	            setStyle("-fx-background-color:  #c8e6c9;");
	        else if (item.getServiceStatus() == ServiceStatus.STOPPED)
	            setStyle("-fx-background-color: #e1bee7;");
	        else
	            setStyle("-fx-background-color: #ffd7d1;");
	    }
	});	
    }




    private void loadSearchEvent() {
	selectServiceTextField.textProperty().addListener((obs, oldText, newText)->onSearch(newText));
    }




    private void onSearch(String newText) {
	@SuppressWarnings("unchecked")
	List<AbstractBackgroundService> backgroundServices = (List<AbstractBackgroundService>)(List<?>)ServiceExecuter.getRunningServices();

    	List<AbstractBackgroundService> filteredTools = backgroundServices.stream().filter(predicate->predicate.getName().toLowerCase().startsWith(newText.toLowerCase())).collect(Collectors.toList());
    	serviceDataTableView.setItems(FXCollections.observableArrayList(filteredTools));

	}
    
    
    
    private void loadButtonEvents() {
	allBtn.setOnAction(ev-> loadServices());
	runningServicesBtn.setOnAction(ev->{
	    loadServices();
	    serviceDataTableView.setItems(FXCollections.observableArrayList(serviceDataTableView.getItems().stream().filter(sr->sr.getServiceStatus()==ServiceStatus.RUNNING).collect(Collectors.toList())));
	});
	   stoppedServiceBtn.setOnAction(ev->{
	    loadServices();
	    serviceDataTableView.setItems(FXCollections.observableArrayList(serviceDataTableView.getItems().stream().filter(sr->sr.getServiceStatus()==ServiceStatus.STOPPED).collect(Collectors.toList())));
	});
	   refresh.setOnMouseClicked(m->{
		    loadServices();
		    serviceDataTableView.refresh();
	   });
    }





    private void addActionButtonToTable() {
	action.setCellFactory(new Callback<TableColumn<AbstractBackgroundService,Void>, TableCell<AbstractBackgroundService,Void>>() {
	    
	    @Override
	    public TableCell<AbstractBackgroundService, Void> call(TableColumn<AbstractBackgroundService, Void> param) {

		return new TableCell<AbstractBackgroundService, Void>(){
		    
		    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(addStartorStopButtonToTable(getTableView().getItems().get(getIndex())));
                        }
                    }
		};
		
		
		
	    }
	});
	
    }

    private Button addStartorStopButtonToTable(AbstractBackgroundService backgroundService) {
	Button btn = null;
        if(backgroundService.getServiceStatus().equals(ServiceStatus.STOPPED)) {
            btn= new Button("START");
            btn.setOnAction(ac->{
    	    try {
    		backgroundService.start();
    		serviceDataTableView.refresh();
		    } catch (InternalError e) {
			e.printStackTrace();
		    }
    	});
        }
        else {
    	 btn = new Button("STOP");
    	btn.setOnAction(ac->{
    	    try {
			ServiceExecuter.stopService(backgroundService);
			serviceDataTableView.refresh();
		    } catch (InternalError e) {
			e.printStackTrace();
		    }
    	});
        }
    return btn;
    }
    

    
    private void loadServices() {
	List<BackgroundService> backgroundServices = ServiceExecuter.getRunningServices();
	@SuppressWarnings("unchecked")
	ObservableList<AbstractBackgroundService> abstractBackgroundServices =FXCollections.observableArrayList( (List<AbstractBackgroundService>)(List<?>)backgroundServices);
	serviceDataTableView.setItems(abstractBackgroundServices);
    }
    
    
    
}
