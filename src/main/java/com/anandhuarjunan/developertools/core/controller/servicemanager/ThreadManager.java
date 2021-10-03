/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.servicemanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ThreadUtils;

import com.anandhuarjunan.developertools.core.app.headerpanel.ShowPriority;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.function.Action;
import com.anandhuarjunan.developertools.core.services.background.AbstractBackgroundService;
import com.anandhuarjunan.developertools.core.services.background.BackgroundService;
import com.anandhuarjunan.developertools.core.services.background.IntervalExecuteCustomService;
import com.anandhuarjunan.developertools.core.services.background.ServiceExecuter;
import com.anandhuarjunan.developertools.core.services.background.ServiceTypes;
import com.anandhuarjunan.developertools.core.views.ClosableTool;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
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
import javafx.util.Duration;

public class ThreadManager implements ClosableTool{

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
	IntervalExecuteCustomService customService = null;

    
    @FXML
    void initialize() {
	
	
	loadThreads();
        loadThreadCount();

	id.setCellValueFactory(c-> new SimpleObjectProperty<Long>(c.getValue().getId()));
	nameTableColumn.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getName()));
	deamon.setCellValueFactory(c-> new SimpleObjectProperty<Boolean>(c.getValue().isDaemon()));
	status.setCellValueFactory(c-> new SimpleObjectProperty<String>(c.getValue().getState().toString()));
	
	  Action e =()->Platform.runLater(this::loadThreads);
	  customService =  new IntervalExecuteCustomService(Duration.millis(1000), Duration.millis(0), "Thread Manager Refresh Task","",  e);

			try {
			    ServiceExecuter.startService(customService);
			} catch (InternalError e1) {
			    NotifyMessage.notifyWarning("Thread Manager Refresh Task Not loaded Properly",ShowPriority.HIGH_PRIORITY);
			}


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
    	serviceDataTableView.getItems().clear();
	List<Thread> backgroundThreads = new ArrayList<>(ThreadUtils.getAllThreads());
	ObservableList<Thread> abstractBackgroundServices =FXCollections.observableArrayList(backgroundThreads);
	serviceDataTableView.setItems(abstractBackgroundServices);
    }


	@Override
	public boolean canBeClosed() {
		return true;
	}


	@Override
	public void close() {
		try {
			ServiceExecuter.stopService(customService);
		} catch (InternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}