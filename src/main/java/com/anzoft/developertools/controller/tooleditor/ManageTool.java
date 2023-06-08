/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.tooleditor;

import java.util.List;
import java.util.stream.Collectors;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.controller.toolloaders.ToolLoaderFactory;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.common.UpdateEntity;
import com.anzoft.developertools.database.services.tooleditor.GetAllTools;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.jfoenix.controls.JFXButton;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class ManageTool {

    @FXML
    private TextField selectPluginTextField;


    
    @FXML
    private TableView<Tools> pluginDataTableView;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<Tools, String> nameTableColumn;

    @FXML
    private TableColumn<Tools, String> description;

    @FXML
    private TableColumn<Tools, Boolean> isEnableTableColumn;

    @FXML
    private TableColumn<Tools, Boolean> isClosable;

    @FXML
    private TableColumn<Tools, Boolean> multitab;

    @FXML
    private TableColumn<?, ?> category;

    private ObservableList<Tools> tools = null;
    
    @FXML
    private TableColumn<Tools, Void> open;

    
    @FXML
    private Button inbuilt;


    UpdateEntity<Tools> dbService = new UpdateEntity<>();

    @FXML
    void searchPlugin(ActionEvent event) {

    }
    
    @FXML
    void initialize() {
    	try {


    		pluginDataTableView.setEditable(true);
			tools = getTools();
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("toolName"));
			description.setCellValueFactory(new PropertyValueFactory<>("description"));
			category.setCellValueFactory(new PropertyValueFactory<>("category"));
			//open.setCellValueFactory(e->new SimpleStringProperty("Open"));
			open.setStyle( "-fx-alignment: CENTER;");
			setEditableNameField();
			setEditableDescriptionField();
			setEditableHidden();
			setEditableClosable();
			setEditableMultitab();
			addOpenButtonToTable();

			selectPluginTextField.textProperty().addListener((obs, oldText, newText)->onSearch(newText));
			
			
			inbuilt.setOnAction(e->onClickInbuiltApps());

			
			pluginDataTableView.setItems(tools);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    }
    
    

	private void addOpenButtonToTable() {

            Callback<TableColumn<Tools, Void>, TableCell<Tools, Void>> cellFactory = new Callback<TableColumn<Tools, Void>, TableCell<Tools, Void>>() {
                @Override
                public TableCell<Tools, Void> call(final TableColumn<Tools, Void> param) {
                   return new TableCell<Tools, Void>() {

                        private final Button btn = new JFXButton("Open Tool");

                        {
                        	btn.setStyle("-fx-background-color:#2196F3");
                            btn.setOnAction((ActionEvent event) -> {
                            	Tools data = getTableView().getItems().get(getIndex());
                            	ToolLoaderFactory toolLoaderFactory = UIResources.getInstance().getTabLoader();
                            	toolLoaderFactory.loadTool(data.getId());
                            });
                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                            }
                        }
                    };
                }
            };

            open.setCellFactory(cellFactory);

            pluginDataTableView.getColumns().add(open);

    }
    
 
    
    private void onClickInbuiltApps() {
    	ToolLoaderFactory loaderFactory = UIResources.getInstance().getTabLoader();
    	loaderFactory.loadTool("INBUILT_TOOLS");
	}

	private void onSearch(String newText) {
    	List<Tools> filteredTools = tools.stream().filter(predicate->predicate.getToolName().toLowerCase().startsWith(newText.toLowerCase())).collect(Collectors.toList());
		pluginDataTableView.setItems(FXCollections.observableArrayList(filteredTools));

	}

	private void setEditableNameField() {
		nameTableColumn.setCellFactory(TextFieldTableCell.<Tools> forTableColumn());
    	nameTableColumn.setOnEditCommit((CellEditEvent<Tools, String> event) -> {
    		
            TablePosition<Tools, String> pos = event.getTablePosition();
 
            String newFullName = event.getNewValue();
 
            int row = pos.getRow();
            Tools tool = event.getTableView().getItems().get(row);
 
            tool.setToolName(newFullName);
            dbService.update(tool);
        });
    }
    private void setEditableDescriptionField() {
    	description.setCellFactory(TextFieldTableCell.<Tools> forTableColumn());

    	description.setOnEditCommit((CellEditEvent<Tools, String> event) -> {
    		
            TablePosition<Tools, String> pos = event.getTablePosition();
 
 
            int row = pos.getRow();
            Tools tool = event.getTableView().getItems().get(row);
 
            tool.setDescription( event.getNewValue());
            dbService.update(tool);
        });
    }
    private void setEditableClosable() {
    	isClosable.setCellFactory(column -> new CheckBoxTableCell<>());
    	isClosable.setCellValueFactory(cellData -> {
            Tools cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(cellValue.isClosable());

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) ->{ cellValue.setClosable(newValue);
            update(cellValue);
            });

            return property;
        });

    }
    private void setEditableMultitab() {
    	multitab.setCellFactory(column -> new CheckBoxTableCell<>());
    	 multitab.setCellValueFactory(cellData -> {
             Tools cellValue = cellData.getValue();
             BooleanProperty property = new SimpleBooleanProperty(cellValue.isMultiTabSupport());

             // Add listener to handler change
             property.addListener((observable, oldValue, newValue) ->{ cellValue.setMultiTabSupport(newValue);
             update(cellValue);
             });

             return property;
         });

    
    }
    
    private void update(Tools tools) {
    	dbService.update(tools);
    }
    
    private void setEditableHidden() {
    	isEnableTableColumn.setCellFactory(column -> new CheckBoxTableCell<>());
    	isEnableTableColumn.setCellValueFactory(cellData -> {
            Tools cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(cellValue.isVisible());

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) ->{ cellValue.setVisible(newValue);
            update(cellValue);
            });

            return property;
        });

   
    }
    
    
    
    private ObservableList<Tools> getTools() throws ServiceException{
		 DBService<List<Tools>>  db = new GetAllTools();
		 return FXCollections.observableArrayList(db.run(new ServiceArguments()).getResult());
	}
	
}
