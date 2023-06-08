/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */

package com.anzoft.developertools.ui.styles;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;

public abstract class RichTextViewerStyle extends AnchorPane {

    protected final AnchorPane anchorpane1;
    public  CodeArea input =null;
    protected  VirtualizedScrollPane<CodeArea> virtualizedScrollPane;
    protected final DropShadow dropShadow;

    public RichTextViewerStyle() {


    	anchorpane1 = new AnchorPane();
        input = new CodeArea();
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
        input.prefWidthProperty().bind(anchorpane1.widthProperty());
        input.prefHeightProperty().bind(anchorpane1.heightProperty());
        virtualizedScrollPane=new VirtualizedScrollPane<>(input);

       
        addChild();
     


    }

    protected void addChild() {
    	anchorpane1.getChildren().add(virtualizedScrollPane);
        getChildren().add(anchorpane1);
    }
}
