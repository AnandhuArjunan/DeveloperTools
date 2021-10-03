/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller;

	import java.net.URL;
import java.util.ResourceBundle;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.function.Action;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

	public  class LoaderUIController extends DTController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;


	    @FXML
	    private Label infoLine;
	    @FXML
	    private MFXProgressBar progressBar;


	    @FXML
	    private FontAwesomeIconView closeButton;
	    
	    private Action  onCloseAction;

	    	    
	    @FXML
	    void initialize() {
	    	
	    	
	    	closeButton.setOnMouseClicked(e->{
	    		if(null != onCloseAction) {
	    			try {
						onCloseAction.execue();
					} catch (InternalError e1) {
						
					}
	    		}
	    	});
	    	//throw new RuntimeException();

	    }

	    public void showError(String errorMsg) {
		    progressBar.progressProperty().unbind();
	    	progressBar.setProgress(1);
	    	progressBar.setStyle("-fx-accent: red");
	    	infoLine.setText("Error, "+errorMsg);
	    }
	    
	    public void onCloseAction(Action onCloseAction) {
	    	this.onCloseAction = onCloseAction;
	    }
	    
	    

	
	    
	}


