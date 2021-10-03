/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.ui.components;

import javafx.scene.layout.AnchorPane;

/**
 * Custom Anchor Pane with a Pane node at the bottom.
 * @author Anandhu Arjunan
 *
 */
public class DTAnchorPane extends AnchorPane {
	
	protected InfoPane infoPane;
	
	
	protected DTAnchorPane() {
		 infoPane = new InfoPane();
		 infoPane.setPrefHeight(20);
	     AnchorPane.setBottomAnchor(infoPane, 0.0);
	     AnchorPane.setLeftAnchor(infoPane, 0.0);
	     AnchorPane.setRightAnchor(infoPane, 0.0);
		 getChildren().add(infoPane);
	}


	protected InfoPane getInfoPane() {
		return infoPane;
	}


	protected void setInfoPane(InfoPane infoPane) {
		this.infoPane = infoPane;
	}
	
	

}
