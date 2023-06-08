/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.headerpanel.message;


import java.util.ArrayList;
import java.util.List;

import com.anzoft.developertools.app.headerpanel.HeaderPanelContent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class MessageNotificationPanelContent extends HeaderPanelContent {
    
    public MessageNotificationPanelContent(int priority) {
	super(priority);
    }
    
    
    public MessageNotificationPanelContent(int priority, String headingName) {
	super(priority, headingName);
    }


    private HBox box = null;
    private  Label  label = null;
    @Override
    public void load(Object... objects) {
	initializeMessageBlock(objects);
	

    }
    
    
    public void initializeMessageBlock(Object... objects) {
	String errorMessage = (String) objects[0];
	label = new Label(errorMessage);
	labelColor();
	label.setStyle("-fx-font-weight: bold");
	box = new HBox();
	box.getChildren().add(setIcon());
	box.getChildren().add(label);
	box.setPrefHeight(Region.USE_COMPUTED_SIZE);
	box.setPrefWidth(Region.USE_COMPUTED_SIZE);
	box.setAlignment(Pos.CENTER);
	box.setSpacing(10);
	panelColor();
	box.setPadding(new Insets(10));
	 DropShadow dropShadow = new DropShadow(); 
	      
	      //setting the type of blur for the shadow 
	      dropShadow.setBlurType(BlurType.GAUSSIAN); 
	box.setEffect(dropShadow);
    }

    @Override
    public List<Node> show() {
	List<Node> nodes =  new ArrayList<>();
	nodes.add(box);
	return nodes;
    }
    
    
    public Node setIcon() {
	FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
	icon.setSize("30");
	return icon;
    }

    public void labelColor() {
	label.setTextFill(Color.web("#000a12"));
    }
    public void panelColor() {
	box.setStyle("-fx-background-color: #a5d6a7");
    }

    public HBox getBox() {
        return box;
    }

    public void setBox(HBox box) {
        this.box = box;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    
    
}
