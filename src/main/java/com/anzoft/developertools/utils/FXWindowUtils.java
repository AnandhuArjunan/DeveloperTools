/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXWindowUtils {

	
	
	
	
	public static void loadUndecoratedStage(String fxmlLocation,Stage stage) throws IOException {
		
		  Parent root = FXMLLoader.load(FXWindowUtils.class.getResource(fxmlLocation));
		  stage.setScene(new Scene(root));
		  //stage.initStyle(StageStyle.UNDECORATED);
		  stage.show();
	}
}
