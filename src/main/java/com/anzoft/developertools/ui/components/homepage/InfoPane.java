/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.ui.components.homepage;

import de.jensd.fx.glyphs.GlyphIcon;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class InfoPane extends HBox {

	public InfoPane(GlyphIcon<?> icon,Label value,EventHandler<MouseEvent> actionEvent) {
		super(icon,value);
		icon.setSize("20px");
		VBox.setVgrow(value, Priority.ALWAYS);
		VBox.setVgrow(icon, Priority.ALWAYS);
		super.setAlignment(Pos.CENTER_LEFT);
		super.setPadding(new Insets(10));
		super.setSpacing(5);
		super.setStyle("-fx-background-color:#fbc02d");
		super.setOnMouseClicked(actionEvent);
	}
	
	
}
