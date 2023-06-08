/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.ui.styles;

import com.anzoft.developertools.ui.components.DTAnchorPane;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public abstract class CompareToolStyle extends DTAnchorPane{

    public final SplitPane splitpane;
    public final AnchorPane anchorpane1;
    public final TextArea input;
    public final Reflection reflection;
    public final Label label;
    public final AnchorPane anchorpane2;
    public final TextArea output;
    public final Label label0;
    public final ToolBar toolbar;
    public Label labelI = new Label();


    public CompareToolStyle() {

        splitpane = new SplitPane();
        anchorpane1 = new AnchorPane();
        input = new TextArea();
        reflection = new Reflection();
        label = new Label();
        anchorpane2 = new AnchorPane();
        output = new TextArea();
        label0 = new Label();
        toolbar = new ToolBar();

        setPrefHeight(720.0);
        setPrefWidth(1080.0);

        AnchorPane.setBottomAnchor(splitpane, 35.0);
        AnchorPane.setLeftAnchor(splitpane, 40.0);
        AnchorPane.setRightAnchor(splitpane, 40.0);
        AnchorPane.setTopAnchor(splitpane, 60.0);
        splitpane.setDividerPositions(0.5);
        splitpane.setLayoutX(41.0);
        splitpane.setLayoutY(29.0);
        splitpane.setPrefHeight(1080.0);
        splitpane.setPrefWidth(1920.0);

        anchorpane1.setFocusTraversable(true);
        anchorpane1.setMinHeight(0.0);
        anchorpane1.setMinWidth(0.0);
        anchorpane1.setPrefHeight(160.0);
        anchorpane1.setPrefWidth(100.0);

        AnchorPane.setBottomAnchor(input, 0.0);
        AnchorPane.setLeftAnchor(input, 0.0);
        AnchorPane.setRightAnchor(input, 0.0);
        AnchorPane.setTopAnchor(input, 30.0);
        input.textProperty().addListener(listener->onKey());
        input.setPrefHeight(978.0);
        input.setPrefWidth(538.0);

        input.setEffect(reflection);
        input.setFont(new Font("Candara", 18.0));

        label.setLayoutY(4.0);
        label.setText("Input");
        label.setFont(new Font("Candara Bold", 19.0));
        label.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));

        anchorpane2.setMinHeight(0.0);
        anchorpane2.setMinWidth(0.0);
        anchorpane2.setPrefHeight(160.0);
        anchorpane2.setPrefWidth(100.0);

        AnchorPane.setBottomAnchor(output, 0.0);
        AnchorPane.setLeftAnchor(output, 0.0);
        AnchorPane.setRightAnchor(output, 0.0);
        AnchorPane.setTopAnchor(output, 30.0);
        output.setEditable(false);
        output.setPrefHeight(200.0);
        output.setPrefWidth(200.0);
        output.setFont(new Font("Candara", 18.0));
        output.textProperty().addListener(c->setAnimation());

        label0.setLayoutY(4.0);
        label0.setText("Output");
        label0.setFont(new Font("Candara Bold", 19.0));
        label0.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));

        AnchorPane.setLeftAnchor(toolbar, 40.0);
        AnchorPane.setRightAnchor(toolbar, 40.0);
        toolbar.setLayoutX(40.0);
        toolbar.setLayoutY(7.0);
        toolbar.setPrefHeight(40.0);
        toolbar.setPrefWidth(1000.0);

        anchorpane1.getChildren().add(input);
        anchorpane1.getChildren().add(label);
        splitpane.getItems().add(anchorpane1);
        anchorpane2.getChildren().add(output);
        anchorpane2.getChildren().add(label0);
        splitpane.getItems().add(anchorpane2);
        getChildren().add(splitpane);
        getChildren().add(toolbar);

    }
    
    
    public void addItemsToToolBar(Node node) {
    	toolbar.getItems().add(node);
    	refresh();
    }
    
    public void addItemsToToolBar(Node... node) {
    	toolbar.getItems().addAll(node);
    	refresh();
    }
    
    
    
    
    
    private void refresh() {
    	ObservableList<Node> nodes = toolbar.getItems();	
    	for(Node n : nodes) {
    		if(n instanceof Label) {
    			Label l = (Label)n;
    			l.setFont(Font.font(l.getFont().getFamily(), FontWeight.BOLD, 15));
    		}
    		if(n instanceof TextField) {
    			TextField textField = (TextField)n;
    			textField.textProperty().addListener(listener->onKey());

    		}
    		
    	}
    }
    protected void setAnimation() {	
   		 Rectangle rect = new Rectangle();

   		    FillTransition tr = new FillTransition();
   		    rect.setFill(Color.LIMEGREEN);
   		    tr.setShape(rect);
   		    tr.setDuration(Duration.millis(1000));
   		    tr.setFromValue(Color.LIMEGREEN);
   		    tr.setToValue(Color.valueOf("#29b6f6"));
   		    tr.setInterpolator(new Interpolator() {
   		        @Override
   		        protected double curve(double t) {
   		        	anchorpane2.setBackground(new Background(new BackgroundFill(rect.getFill(), CornerRadii.EMPTY, Insets.EMPTY)));
   		            return t;
   		        }
   		    });

   		    tr.play();

   	}
    	
    	

    	
    public void disableToolBar() {
    	toolbar.setVisible(false);
		AnchorPane.setTopAnchor(splitpane, 40.0);
    }
    

    protected abstract void onKey();



}
