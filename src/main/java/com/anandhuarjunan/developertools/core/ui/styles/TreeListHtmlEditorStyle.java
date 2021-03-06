/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
βπ§  π  π΅π  πππΆππΉπ½π πππΏπππΆπ  π  π§ 
 */
package com.anandhuarjunan.developertools.core.ui.styles;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;

public abstract class TreeListHtmlEditorStyle extends TreeListSplitPaneStyle {

	    protected HTMLEditor htmlEditor;

	    public TreeListHtmlEditorStyle() {
			super();
	    }
	    
	    @Override
	    protected Node addSecondaryComponent() {
	    	htmlEditor = new HTMLEditor();
	    	  AnchorPane.setBottomAnchor(htmlEditor, 0.0);
		      AnchorPane.setLeftAnchor(htmlEditor, 0.0);
		      AnchorPane.setRightAnchor(htmlEditor, 0.0);
		      AnchorPane.setTopAnchor(htmlEditor, 0.0);
		      htmlEditor.setPrefHeight(200.0);
		      htmlEditor.setPrefWidth(200.0);
			  return htmlEditor;
	    }

	    
	    

	    
	    

}
