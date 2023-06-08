/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.ui.page;

import com.anzoft.developertools.ui.styles.AnchorPanePlane;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class IconLabelPage extends AnchorPanePlane {
	
	private final VBox vBox;
	private final ImageView imageView;

	public IconLabelPage(String imagePath,String label) {

        vBox = new VBox();
        imageView = new ImageView();

        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 0.0);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setLayoutX(212.0);
        vBox.setLayoutY(123.0);
        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);

        imageView.setFitHeight(182.0);
        imageView.setFitWidth(229.0);
        imageView.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(IconLabelPage.class.getResource(imagePath).toExternalForm()));

        vBox.getChildren().add(imageView);
        vBox.setSpacing(20);
        Label label2 = new Label(label);
        label2.setFont(new Font("Calibri", 30));

        vBox.getChildren().add(label2);
        
        anchorPane.getChildren().add(vBox);
	}

	public VBox getvBox() {
		return vBox;
	}
	
}
