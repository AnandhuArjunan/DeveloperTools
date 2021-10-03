/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.ui.components.homepage;

import javafx.event.EventHandler;import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ToolBlock extends VBox {

	Label label = null;
	
	public ToolBlock(String id,Node node,String color,Label label,EventHandler<MouseEvent> actionEvent) {
		super(node,label);
		this.label = label;
		super.setAlignment(Pos.CENTER);
		super.setId(id);
		//label.setFont(Font.font("Calibri", 15));
		VBox.setVgrow(node, Priority.ALWAYS);
		VBox.setVgrow(label, Priority.ALWAYS);
		getStyleClass().add("toolBlock");
		
		super.setOnMouseClicked(actionEvent);
	}
public void setContextmenu(ContextMenu context) {
	label.setContextMenu(context);
}

}
