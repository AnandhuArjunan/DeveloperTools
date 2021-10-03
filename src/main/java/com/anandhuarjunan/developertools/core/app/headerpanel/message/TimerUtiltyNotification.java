/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.headerpanel.message;

import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TimerUtiltyNotification extends MessageNotificationPanelContent {

  

    public TimerUtiltyNotification(int priority,String headerName) {
	super(priority,headerName);
    }

    @Override
    public Node setIcon() {
	ImageView imageView = FxComponentUtils.loadImage("gif/icons8-clock.gif");
	imageView.setPreserveRatio(true);
	imageView.setFitHeight(30);
	imageView.setFitWidth(30);
  	return imageView;
      }
    @Override
      public void labelColor() {
  	getLabel().setTextFill(Color.web("#212121"));
      }
    @Override
      public void panelColor() {
	getBox().setStyle("-fx-background-color: #ffffff");
      }

 
    

}
