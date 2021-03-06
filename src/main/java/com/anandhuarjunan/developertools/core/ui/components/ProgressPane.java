/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
βπ§  π  π΅π  πππΆππΉπ½π πππΏπππΆπ  π  π§ 
 */
package com.anandhuarjunan.developertools.core.ui.components;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
public final class ProgressPane extends HBox {

	protected final Label label;
    protected final ProgressBar progressBar;


	    public ProgressPane() {

	    	  label = new Label();
	    	  progressBar = new ProgressBar();

	          setAlignment(javafx.geometry.Pos.CENTER);
	          setMaxHeight(USE_PREF_SIZE);
	          setMaxWidth(USE_PREF_SIZE);
	          setMinHeight(USE_PREF_SIZE);
	          setMinWidth(USE_PREF_SIZE);
	          setSpacing(20.0);

	          label.setText("Progress Label");

	          setPadding(new Insets(10.0, 20.0, 10.0, 20.0));

	          getChildren().add(label);
	          getChildren().add(progressBar);

	    }
	    
	    public void setProgress(double value) {
	    	progressBar.setProgress(value);
	    }
	    public void setProgressLabel(String text) {
	    	label.setText(text);
	    }
	    
	    
	    
	}


