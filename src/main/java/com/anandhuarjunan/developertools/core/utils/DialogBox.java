
package com.anandhuarjunan.developertools.core.utils;

import java.util.Optional;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class DialogBox {
	
	private DialogBox() {}

	public static void showWarningBox(ToolError e) {
        Alert a = new Alert(AlertType.WARNING); 
        a.setContentText(e.getMessage());
        a.show();

	}
	
	public static void showErrorBox(ToolError e) {
        Alert a = new Alert(AlertType.ERROR); 
        a.setContentText(e.getMessage());
        a.show();
	}
	
	public static boolean confirmationDialog(String title,String headerText,String contentText,Stage context) {
		Alert alert = getAlert(AlertType.CONFIRMATION,title,headerText,contentText);
if(null!=context) {
	alert.initOwner(context);
}
		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent()&&result.get() == ButtonType.OK;
	}
	
	public static boolean confirmationDialog(String title,String headerText,String contentText) {
		Alert alert = getAlert(AlertType.CONFIRMATION,title,headerText,contentText);
		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent()&&result.get() == ButtonType.OK;
	}
	
	
	
	public static Alert getAlert(AlertType alertType,String title,String headerText,String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		return alert;
	}
	
	
	
}
