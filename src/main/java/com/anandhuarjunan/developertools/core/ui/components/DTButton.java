/*
 * 
 */
package com.anandhuarjunan.developertools.core.ui.components;

import javafx.scene.control.Button;

public class DTButton extends Button {
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";

	public DTButton() {
		setStyle("-fx-background-color: #ffffff00;-fx-border-color: #29b6f6;-fx-border-width: 2;");
		setOnMouseEntered(e -> setStyle(HOVERED_BUTTON_STYLE));
        setOnMouseExited(e -> setStyle(IDLE_BUTTON_STYLE));
	}
}
