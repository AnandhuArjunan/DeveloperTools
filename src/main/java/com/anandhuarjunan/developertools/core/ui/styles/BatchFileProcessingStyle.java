/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.ui.styles;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class BatchFileProcessingStyle extends AnchorPane {
	
	    protected final SplitPane splitpane;
	    protected final AnchorPane anchorpane1;
	    protected final Label label;
	    protected final TextArea textArea;
	    protected final Label label0;
	    protected final ToolBar toolbar;
	    protected final ProgressBar progressBar;
	    protected final Label label1;


	    public BatchFileProcessingStyle() {

	    	 splitpane = new SplitPane();
	         anchorpane1 = new AnchorPane();
	         label = new Label();
	         textArea = new TextArea();
	         label0 = new Label();
	         toolbar = new ToolBar();
	         progressBar = new ProgressBar();
	         label1 = new Label();

	         setPrefHeight(720.0);
	         setPrefWidth(1080.0);
	        // setStyle("-fx-background-color: white;");

	         AnchorPane.setBottomAnchor(splitpane, 100.0);
	         AnchorPane.setLeftAnchor(splitpane, 40.0);
	         AnchorPane.setRightAnchor(splitpane, 40.0);
	         AnchorPane.setTopAnchor(splitpane, 70.0);
	         splitpane.setLayoutX(41.0);
	         splitpane.setLayoutY(29.0);
	         splitpane.setPrefHeight(1080.0);
	         splitpane.setPrefWidth(1920.0);
	       //  splitpane.setStyle("-fx-border-color: #29b6f6; -fx-border-width: 2;");

	         anchorpane1.setFocusTraversable(true);
	         anchorpane1.setMinHeight(0.0);
	         anchorpane1.setMinWidth(0.0);
	         anchorpane1.setPrefHeight(160.0);
	         anchorpane1.setPrefWidth(100.0);
	       //  anchorpane1.setStyle("-fx-border-width: 2; -fx-background-color: #22a5e0;");

	         label.setLayoutY(4.0);
	         label.setText("Result");
	         //label.setTextFill(javafx.scene.paint.Color.WHITE);
	         label.setFont(new Font("Candara Bold", 19.0));
	         label.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));

	         AnchorPane.setBottomAnchor(textArea, 0.0);
	         AnchorPane.setLeftAnchor(textArea, 0.0);
	         AnchorPane.setRightAnchor(textArea, 0.0);
	         AnchorPane.setTopAnchor(textArea, 30.0);
	         textArea.setEditable(false);
	         textArea.setFocusTraversable(false);
	         textArea.setLayoutY(26.0);
	         textArea.setPrefHeight(200.0);
	         textArea.setPrefWidth(200.0);

	         AnchorPane.setRightAnchor(label0, 25.0);
	         label0.setLayoutX(430.0);
	         label0.setLayoutY(5.0);
	         label0.setText("Clear");
	         //label0.setTextFill(javafx.scene.paint.Color.WHITE);
	         label0.setFont(new Font("Calibri Bold", 15.0));

	         AnchorPane.setLeftAnchor(toolbar, 40.0);
	         AnchorPane.setRightAnchor(toolbar, 40.0);
	         AnchorPane.setTopAnchor(toolbar, 15.0);
	         toolbar.setLayoutX(40.0);
	         toolbar.setLayoutY(7.0);
	         toolbar.setPrefHeight(40.0);
	         toolbar.setPrefWidth(1000.0);
	       //  toolbar.setStyle("-fx-border-color: #29b6f6; -fx-background-color: #ffffff00; -fx-border-width: 2;");

	         AnchorPane.setBottomAnchor(progressBar, 65.0);
	         AnchorPane.setLeftAnchor(progressBar, 40.0);
	         AnchorPane.setRightAnchor(progressBar, 40.0);
	         progressBar.setLayoutX(40.0);
	         progressBar.setLayoutY(660.0);
	         progressBar.setProgress(0);
	         AnchorPane.setBottomAnchor(label1, 40.0);
	         AnchorPane.setLeftAnchor(label1, 40.0);
	         AnchorPane.setRightAnchor(label1, 40.0);
	         label1.setPrefWidth(1000.0);
	         label1.setLayoutX(40.0);
	         label1.setLayoutY(675.0);
	         label1.setText("Label");

	         anchorpane1.getChildren().add(label);
	         anchorpane1.getChildren().add(textArea);
	         anchorpane1.getChildren().add(label0);
	         splitpane.getItems().add(anchorpane1);
	         getChildren().add(splitpane);
	         getChildren().add(toolbar);
	         getChildren().add(progressBar);
	         getChildren().add(label1);

	    }
	    
	    public synchronized void setProgressBar(double d) {
	    	progressBar.setProgress(progressBar.getProgress()+d);
	    }
	    

}
