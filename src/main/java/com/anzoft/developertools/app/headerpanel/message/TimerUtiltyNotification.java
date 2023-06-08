/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.headerpanel.message;

import com.anzoft.developertools.utils.FxComponentUtils;

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
