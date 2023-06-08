/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.home;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class FooterViewController {

    HBox footerHBOX = null;
	public FooterViewController(HBox footerHBOX ) {
		this.footerHBOX = footerHBOX;
	}
	
	public void addItemToFooter(Node node) {
		node.prefWidth(Region.USE_COMPUTED_SIZE);
		footerHBOX.getChildren().add(node);
	}
	
	public ObservableList<Node> getAllItems() {
		return footerHBOX.getChildren();
	}
	
}
