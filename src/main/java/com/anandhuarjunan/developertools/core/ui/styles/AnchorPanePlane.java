/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
βπ§  π  π΅π  πππΆππΉπ½π πππΏπππΆπ  π  π§ 
 */
package com.anandhuarjunan.developertools.core.ui.styles;
import javafx.scene.layout.AnchorPane;
public class AnchorPanePlane  extends AnchorPane{
	public final AnchorPane anchorPane;


	    public AnchorPanePlane() {

	        anchorPane = new AnchorPane();

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

	        getChildren().add(anchorPane);

	    }
}
