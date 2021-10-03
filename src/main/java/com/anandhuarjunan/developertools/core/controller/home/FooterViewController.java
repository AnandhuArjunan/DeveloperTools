/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.home;

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
