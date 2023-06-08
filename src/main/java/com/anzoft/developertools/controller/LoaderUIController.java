/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller;

	import java.net.URL;
import java.util.ResourceBundle;

import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.function.Action;
import com.jfoenix.controls.JFXProgressBar;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

	public  class LoaderUIController extends DTController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private VBox downBlock;

	    @FXML
	    private Label infoLine;

	    @FXML
	    private JFXProgressBar progressBar;

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
	    }

	    public void showError(String errorMsg) {
		    progressBar.progressProperty().unbind();
	    	progressBar.setProgress(1);
	    	progressBar.lookup(".bar").setStyle("-fx-background-color:red");
	    	infoLine.setText(errorMsg);
	    }
	    
	    public void onCloseAction(Action onCloseAction) {
	    	this.onCloseAction = onCloseAction;
	    }
	    
	    

	
	    
	}


