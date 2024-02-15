/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
â•šðŸ�§  ðŸŽ€  ð��µð�“Ž  ð�’œð�“ƒð�’¶ð�“ƒð�’¹ð�’½ð�“Š ð�’œð�“‡ð�’¿ð�“Šð�“ƒð�’¶ð�“ƒ  ðŸŽ€  ðŸ�§ 
 */
package com.anandhuarjunan.developertools;


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

public class CompareToolStyle extends AnchorPane{

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
    




}
