/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.tooleditor;

import java.util.List;
import java.util.stream.Collectors;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.controller.toolloaders.ToolLoaderFactory;
import com.anandhuarjunan.developertools.core.database.persistables.InternalTools;
import com.anandhuarjunan.developertools.core.database.services.common.DeleteEntity;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ManageInbuiltTools {

	  @FXML
	    private TextField selectPluginTextField;

	    @FXML
	    private TableView<InternalTools> pluginDataTableView;

	    @FXML
	    private TableColumn<InternalTools, String> id;

	    @FXML
	    private TableColumn<InternalTools, String> nameTableColumn;

	    @FXML
	    private TableColumn<InternalTools, String> description;

	    @FXML
	    private TableColumn<InternalTools, String> className;

	    @FXML
	    private TableColumn<InternalTools, Void> open;

		private ObservableList<InternalTools> tools;
	    @FXML
	    private TableColumn<InternalTools, Void> delete;
	    
	   

    
    @FXML
    void initialize() {
    	pluginDataTableView.setEditable(true);
		tools = getInternalTools();
		id.setCellValueFactory(celldata->new SimpleStringProperty(celldata.getValue().getTool().getId()));
		nameTableColumn.setCellValueFactory(celldata->new SimpleStringProperty(celldata.getValue().getTool().getToolName()));
		description.setCellValueFactory(celldata->new SimpleStringProperty(celldata.getValue().getTool().getDescription()));
		className.setCellValueFactory(new PropertyValueFactory<>("className"));

		open.setStyle( "-fx-alignment: CENTER;");
		delete.setStyle( "-fx-alignment: CENTER;");
		addOpenButtonToTable();
		addDeleteButtonToTable();

		selectPluginTextField.textProperty().addListener((obs, oldText, newText)->onSearch(newText));
		
		
		pluginDataTableView.setItems(tools);
    }
    
    
    private ObservableList<InternalTools> getInternalTools() {
    	 GetAllEntityService<InternalTools>  db = new GetAllEntityService<>();
		 try {
			return FXCollections.observableArrayList(db.getAllRecords(InternalTools.class));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tools;
	}


	private void addDeleteButtonToTable() {
    	  Callback<TableColumn<InternalTools, Void>, TableCell<InternalTools, Void>> cellFactory = new Callback<TableColumn<InternalTools, Void>, TableCell<InternalTools, Void>>() {
              @Override
              public TableCell<InternalTools, Void> call(final TableColumn<InternalTools, Void> param) {
                 return new TableCell<InternalTools, Void>() {

                      private final Button btn = new Button("Delete Tool");

                      {
                      	btn.setStyle("-fx-background-color:#2196F3");

                          btn.setOnAction((ActionEvent event) -> {
                        	 InternalTools data = getTableView().getItems().get(getIndex());
                          	DeleteEntity<InternalTools> deleteEntity = new DeleteEntity<>();
                          	try {
								deleteEntity.delete(data);
							} catch (ServiceException e) {
								NotifyMessage.notifyError("Unable to Delete ! ");
							}
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

          delete.setCellFactory(cellFactory);

          pluginDataTableView.getColumns().add(delete);
		
	}

	private void addOpenButtonToTable() {

            Callback<TableColumn<InternalTools, Void>, TableCell<InternalTools, Void>> cellFactory = new Callback<TableColumn<InternalTools, Void>, TableCell<InternalTools, Void>>() {
                @Override
                public TableCell<InternalTools, Void> call(final TableColumn<InternalTools, Void> param) {
                   return new TableCell<InternalTools, Void>() {

                        private final Button btn = new Button("Open Tool");

                        {
                        	btn.setStyle("-fx-background-color:#2196F3");
                            btn.setOnAction((ActionEvent event) -> {
                            	InternalTools data = getTableView().getItems().get(getIndex());
                            	ToolLoaderFactory toolLoaderFactory = UIResources.getInstance().getTabLoader();
                            	toolLoaderFactory.loadTool(data.getTool().getId());
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
    
   

	private void onSearch(String newText) {
    	List<InternalTools> filteredTools = tools.stream().filter(predicate->predicate.getTool().getToolName().toLowerCase().startsWith(newText.toLowerCase())).collect(Collectors.toList());
		pluginDataTableView.setItems(FXCollections.observableArrayList(filteredTools));

	}


    

}
