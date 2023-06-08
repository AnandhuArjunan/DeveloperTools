package com.anzoft.developertools.controller.servicemanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ThreadUtils;

import com.anzoft.developertools.services.background.AbstractBackgroundService;
import com.anzoft.developertools.services.background.BackgroundService;
import com.anzoft.developertools.services.background.ServiceExecuter;
import com.anzoft.developertools.services.background.ServiceTypes;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ThreadManager {

    @FXML
    private TextField selectServiceTextField;

    @FXML
    private Label liveThreadsCount;

    @FXML
    private Label deamonThreadsCount;

    @FXML
    private FontAwesomeIconView refresh;

    @FXML
    private TableView<Thread> serviceDataTableView;

    @FXML
    private TableColumn<Thread, Long> id;

    @FXML
    private TableColumn<Thread, String> nameTableColumn;

    @FXML
    private TableColumn<Thread, Boolean> deamon;

    @FXML
    private TableColumn<Thread, String> status;

    
    @FXML
    void initialize() {
	
	
	
	loadThreads();
        loadThreadCount();

	id.setCellValueFactory(c-> new SimpleObjectProperty<Long>(c.getValue().getId()));
	nameTableColumn.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getName()));
	deamon.setCellValueFactory(c-> new SimpleObjectProperty<Boolean>(c.getValue().isDaemon()));
	status.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getState().toString()));


}


    private void loadThreadCount() {
	List<Thread> backgroundThreads = new ArrayList<>(ThreadUtils.getAllThreads());
	if( !backgroundThreads.isEmpty()) {
	    long liveThreadCount = backgroundThreads.stream().filter(t->!t.isDaemon()).count();
	    long deamonThreadCount = backgroundThreads.stream().filter(Thread::isDaemon).count();
	    liveThreadsCount.setText(String.valueOf(liveThreadCount));
	    deamonThreadsCount.setText(String.valueOf(deamonThreadCount));

	}else {
	    liveThreadsCount.setText("0");
	    deamonThreadsCount.setText("0");

	}
	
    }


    private void loadThreads() {
	List<Thread> backgroundThreads = new ArrayList<>(ThreadUtils.getAllThreads());
	ObservableList<Thread> abstractBackgroundServices =FXCollections.observableArrayList(backgroundThreads);
	serviceDataTableView.setItems(abstractBackgroundServices);
    }
    
    
}