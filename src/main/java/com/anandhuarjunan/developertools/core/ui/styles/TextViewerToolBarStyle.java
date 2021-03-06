/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
βπ§  π  π΅π  πππΆππΉπ½π πππΏπππΆπ  π  π§ 
 */

package com.anandhuarjunan.developertools.core.ui.styles;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

public abstract class TextViewerToolBarStyle extends RichTextViewerStyle {

  private ToolBar toolBar;

    public TextViewerToolBarStyle() {

    	super();
	toolBar =new ToolBar();
    	makeToolBarConfig(toolBar);

    }

	@Override
	protected void addChild() {
		AnchorPane.setLeftAnchor(toolBar, 40.0);
	    AnchorPane.setRightAnchor(toolBar, 40.0);
	    toolBar.setLayoutX(40.0);
	    toolBar.setLayoutY(7.0);
	    toolBar.setPrefHeight(40.0);
	    toolBar.setPrefWidth(1000.0);
		getChildren().add(toolBar);
		super.addChild();
	}
    
    protected abstract void makeToolBarConfig(ToolBar toolBar);
    
}
