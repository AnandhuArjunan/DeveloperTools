/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.headerpanel.message;

import com.anandhuarjunan.developertools.core.controller.custom.textreader.TextReaderInterface;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class ErrorMessageNotificationPanel extends MessageNotificationPanelContent{

    public ErrorMessageNotificationPanel(int priority) {
	super(priority);
    }

    @Override
    public void load(Object... objects) {
	super.load(objects);
	   Button button = new Button("Check Log");
		if(objects.length==2 && null != objects[1]) {
			String logPath =  (String) objects[1];
			if(!logPath.isEmpty()) {
			 
			    button.setOnAction(e->{
				TextReaderInterface.loadFile(logPath);
			    });
			}
		}else {
		    button.setOnAction(e->{
		 			TextReaderInterface.loadFile("logs/app_log.log");
		 		    });
		}
		getBox().getChildren().add(button);
    }

    @Override
    public Node setIcon() {
	  	FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
	  	icon.setSize("30");
	  	icon.setFill(Color.web("#ffffff"));
	  	return icon;
      }
    @Override
      public void panelColor() {
    		getBox().setStyle("-fx-background-color: #af4448");
      }
    @Override
    public void labelColor() {
    		getLabel().setTextFill(Color.web("#ffffff"));
      }
    
    
}
