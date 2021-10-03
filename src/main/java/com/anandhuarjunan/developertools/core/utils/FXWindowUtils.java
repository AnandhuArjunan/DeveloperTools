
package com.anandhuarjunan.developertools.core.utils;

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
