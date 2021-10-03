/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */

package com.anandhuarjunan.developertools.core.ui.styles;

import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class TextViewerStyle extends AnchorPane {

    protected final AnchorPane anchorpane1;
    protected final TextArea input;
    protected final DropShadow dropShadow;

    public TextViewerStyle() {


    	anchorpane1 = new AnchorPane();
        input = new TextArea();
        dropShadow = new DropShadow();

        setPrefHeight(720.0);
        setPrefWidth(1080.0);
        //setStyle("-fx-background-color: white;");

        AnchorPane.setBottomAnchor(anchorpane1, 40.0);
        AnchorPane.setLeftAnchor(anchorpane1, 40.0);
        AnchorPane.setRightAnchor(anchorpane1, 40.0);
        AnchorPane.setTopAnchor(anchorpane1, 60.0);
        anchorpane1.setFocusTraversable(true);
        anchorpane1.setMinHeight(0.0);
        anchorpane1.setMinWidth(0.0);
        anchorpane1.setPrefHeight(661.0);
        anchorpane1.setPrefWidth(1029.0);

        AnchorPane.setBottomAnchor(input, 0.0);
        AnchorPane.setLeftAnchor(input, 0.0);
        AnchorPane.setRightAnchor(input, 0.0);
        AnchorPane.setTopAnchor(input, 0.0);
        input.setEditable(false);
        input.setFocusTraversable(false);
        input.setPrefHeight(978.0);
        input.setPrefWidth(538.0);
       // input.setStyle("-fx-background-color: white;");

       // input.setEffect(dropShadow);
        input.setFont(new Font("Candara", 18.0));
       
     


    }
    
    public void setText(String text) {
    	input.setText(text);	
    }

    public void addChild() {
    	anchorpane1.getChildren().add(input);
        getChildren().add(anchorpane1);
    }
}
