/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.headerpanel;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.services.background.AlwaysListenService;
import com.anandhuarjunan.developertools.core.services.background.ServiceExecuter;
import com.anandhuarjunan.developertools.core.utils.ConcurrencyUtils;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DisplayPanelContent {

    private DisplayPanelContent() {}
    private static Queue<HeaderPanelContent> panelShowQueue = new PriorityBlockingQueue<>();
    private static VBox panel = null;
    static Logger log = Logger.getLogger(DisplayPanelContent.class);  
    private static AlwaysListenService alwaysListenService =  null;
    
    public static void  setPanel(VBox panel) {
	DisplayPanelContent.panel = panel;
	
	alwaysListenService = new AlwaysListenService("Notification Panel", "Service that accepts input to the notification Panel and displayes the information") {

	    @Override
	    public void execute() throws InternalError {
		super.execute();

		     while(!panelShowQueue.isEmpty()) {
			 
			  HeaderPanelContent element = panelShowQueue.poll();
			  List<Node> nodes = element.show();
		      if(null != panel) {
			  try {
			    Platform.runLater(()->{
				setHeading(element);
				panel.getChildren().addAll(nodes);
			    });
			    Thread.sleep(element.showDuration());
			    Platform.runLater(()->panel.getChildren().clear());
			} catch (InterruptedException e) {
			    log.error(e);
			    panel.getChildren().clear();
			    Thread.currentThread().interrupt();
			}
		     }
		  }
	      
	    }
	    
	};
	
	
	try {
	    ServiceExecuter.startService(alwaysListenService);
	} catch (InternalError e) {
	    e.printStackTrace();
	}
	
	
    }
    private static void setHeading(HeaderPanelContent panelContent) {
	      
	     Label label =  new Label(panelContent.heading());
	     FontAwesomeIconView awesomeIconView =  new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
	     awesomeIconView.setFill(Color.valueOf("#ffffff"));
	     label.setGraphic(awesomeIconView);
	     label.setFont(Font.font("Calibri"));
	     label.setTextFill(Color.valueOf("#ffffff"));
	     label.setStyle("-fx-background-color: #373737;-fx-font-weight: bold");
	     label.setPadding(new Insets(0,5,0,5));
	     DropShadow dropShadow = new DropShadow(); 
	      dropShadow.setBlurType(BlurType.GAUSSIAN); 
	      label.setEffect(dropShadow);
	     panel.getChildren().add(label);
	     panel.setFillWidth(true);
	     label.setMaxWidth(Double.MAX_VALUE);


	    }
    public static void display(HeaderPanelContent content) {
	panelShowQueue.add(content);
    }
    
    public static void stopService() {
	try {
	    alwaysListenService.stop();
	} catch (InternalError e) {
	    e.printStackTrace();
	}
    }
    
    
    
    
}
