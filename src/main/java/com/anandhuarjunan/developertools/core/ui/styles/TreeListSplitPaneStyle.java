/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.ui.styles;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

public abstract class TreeListSplitPaneStyle extends AnchorPane {
	  protected final SplitPane splitPane;
	    protected final ToolBar toolBar;
	    protected final AnchorPane anchorPane;
	    public final TreeView<String> treeView;
	    protected final AnchorPane anchorPane0;

	    public TreeListSplitPaneStyle() {

	        toolBar = new ToolBar();
	        splitPane = new SplitPane();
	        anchorPane = new AnchorPane();
	        treeView = new TreeView<>();
	        anchorPane0 = new AnchorPane();

	        setPrefHeight(720.0);
	        setPrefWidth(1080.0);
	       // setStyle("-fx-background-color: white;");

	        AnchorPane.setLeftAnchor(toolBar, 20.0);
	        AnchorPane.setRightAnchor(toolBar, 20.0);
	        AnchorPane.setTopAnchor(toolBar, 20.0);
	        toolBar.setPrefWidth(200.0);

	        AnchorPane.setBottomAnchor(splitPane, 20.0);
	        AnchorPane.setLeftAnchor(splitPane, 20.0);
	        AnchorPane.setRightAnchor(splitPane, 20.0);
	        AnchorPane.setTopAnchor(splitPane, 70.0);
	        splitPane.setDividerPositions(0.2);
	        splitPane.setPrefHeight(645.0);
	        splitPane.setPrefWidth(1040.0);

	        anchorPane.setMinHeight(0.0);
	        anchorPane.setMinWidth(0.0);
	        anchorPane.setPrefHeight(160.0);
	        anchorPane.setPrefWidth(100.0);

	        AnchorPane.setBottomAnchor(treeView, 0.0);
	        AnchorPane.setLeftAnchor(treeView, 0.0);
	        AnchorPane.setRightAnchor(treeView, 0.0);
	        AnchorPane.setTopAnchor(treeView, 0.0);
	        treeView.setFocusTraversable(false);
	        treeView.setPrefHeight(200.0);
	        treeView.setPrefWidth(200.0);

	        anchorPane0.setMinHeight(0.0);
	        anchorPane0.setMinWidth(0.0);
	        anchorPane0.setPrefHeight(160.0);
	        anchorPane0.setPrefWidth(100.0);


	        getChildren().add(toolBar);
	        anchorPane.getChildren().add(treeView);
	        splitPane.getItems().add(anchorPane);
	        addToSecondarySpace(addSecondaryComponent());
	        splitPane.getItems().add(anchorPane0);
	        getChildren().add(splitPane);


	    }
	    
	    
	    
	    protected abstract Node addSecondaryComponent();
	    
	    private void addToSecondarySpace(Node component) {
	        anchorPane0.getChildren().add(component);

	    }


	    
	    public void addComponentToToolBar(Node control,boolean isSeparatorRequired) {
	    	if(null != control) {
		    	toolBar.getItems().add(control);
	    	}
	    	if(isSeparatorRequired) {
	    		toolBar.getItems().add(new Separator(Orientation.VERTICAL));
	    	}
	    }
	    
	    protected void removeComponentFromToolBar(Node control) {
	    	if(null != control) {
		    	toolBar.getItems().remove(control);
	    	}
	    
	    }
	    

}
