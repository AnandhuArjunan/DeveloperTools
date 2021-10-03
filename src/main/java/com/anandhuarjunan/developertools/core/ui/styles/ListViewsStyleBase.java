/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.ui.styles;


import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public abstract class ListViewsStyleBase extends AnchorPane {


	 protected final AnchorPane anchorPane;
	    protected final GridPane gridPane;
	    protected final ColumnConstraints columnConstraints;
	    protected final ColumnConstraints columnConstraints0;
	    protected final ColumnConstraints columnConstraints1;
	    protected final RowConstraints rowConstraints;
	    protected final VBox masterBox;
	    protected final ListView<Object> master;
	    protected final VBox datasetBox1;
	    protected final ListView<Object> dataset1;
	    protected final VBox datasetBox2;
	    protected final ListView<Object> dataset2;

	    public ListViewsStyleBase() {

	        anchorPane = new AnchorPane();
	        gridPane = new GridPane();
	        columnConstraints = new ColumnConstraints();
	        columnConstraints0 = new ColumnConstraints();
	        columnConstraints1 = new ColumnConstraints();
	        rowConstraints = new RowConstraints();
	        masterBox = new VBox();
	        master = new ListView<>();
	        datasetBox1 = new VBox();
	        dataset1 = new ListView<>();
	        datasetBox2 = new VBox();
	        dataset2 = new ListView<>();

	        setPrefHeight(720.0);
	        setPrefWidth(1280.0);

	        AnchorPane.setBottomAnchor(anchorPane, 0.0);
	        AnchorPane.setLeftAnchor(anchorPane, 0.0);
	        AnchorPane.setRightAnchor(anchorPane, 0.0);
	        AnchorPane.setTopAnchor(anchorPane, 0.0);
	        anchorPane.setMaxHeight(USE_PREF_SIZE);
	        anchorPane.setMaxWidth(USE_PREF_SIZE);
	        anchorPane.setMinHeight(USE_PREF_SIZE);
	        anchorPane.setMinWidth(USE_PREF_SIZE);
	        anchorPane.setPrefHeight(400.0);
	        anchorPane.setPrefWidth(600.0);

	        AnchorPane.setBottomAnchor(gridPane, 0.0);
	        AnchorPane.setLeftAnchor(gridPane, 0.0);
	        AnchorPane.setRightAnchor(gridPane, 0.0);
	        AnchorPane.setTopAnchor(gridPane, 0.0);
	        gridPane.setLayoutY(40.0);
	     //   gridPane.setStyle("-fx-background-color: grey;");

	        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
	        columnConstraints.setMinWidth(10.0);
	        columnConstraints.setPrefWidth(100.0);

	        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
	        columnConstraints0.setMinWidth(10.0);
	        columnConstraints0.setPrefWidth(100.0);

	        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
	        columnConstraints1.setMinWidth(10.0);
	        columnConstraints1.setPrefWidth(100.0);

	        rowConstraints.setMinHeight(10.0);
	        rowConstraints.setPrefHeight(30.0);
	        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

	        VBox.setVgrow(master, javafx.scene.layout.Priority.ALWAYS);

	        GridPane.setColumnIndex(datasetBox1, 1);

	        VBox.setVgrow(dataset1, javafx.scene.layout.Priority.ALWAYS);

	        GridPane.setColumnIndex(datasetBox2, 2);

	        VBox.setVgrow(dataset2, javafx.scene.layout.Priority.ALWAYS);

	        gridPane.getColumnConstraints().add(columnConstraints);
	        gridPane.getColumnConstraints().add(columnConstraints0);
	        gridPane.getColumnConstraints().add(columnConstraints1);
	        gridPane.getRowConstraints().add(rowConstraints);
	        masterBox.getChildren().add(master);
	        gridPane.getChildren().add(masterBox);
	        datasetBox1.getChildren().add(dataset1);
	        gridPane.getChildren().add(datasetBox1);
	        datasetBox2.getChildren().add(dataset2);
	        gridPane.getChildren().add(datasetBox2);
	        anchorPane.getChildren().add(gridPane);
	        getChildren().add(anchorPane);
	    }
   
    
   
    
   
    
}
