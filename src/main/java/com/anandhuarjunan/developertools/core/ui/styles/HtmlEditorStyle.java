/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.ui.styles;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
public class HtmlEditorStyle  extends AnchorPane{
	   protected final AnchorPane anchorPane;
	    protected final VBox vBox;
	    protected final HTMLEditor hTMLEditor;

	    public HtmlEditorStyle() {

	        anchorPane = new AnchorPane();
	        vBox = new VBox();
	        hTMLEditor = new HTMLEditor();

	        setPrefHeight(720.0);
	        setPrefWidth(1280.0);

	        AnchorPane.setBottomAnchor(anchorPane, 0.0);
	        AnchorPane.setLeftAnchor(anchorPane, 0.0);
	        AnchorPane.setRightAnchor(anchorPane, 0.0);
	        AnchorPane.setTopAnchor(anchorPane, 0.0);
	        anchorPane.setMaxHeight(USE_PREF_SIZE);
	        anchorPane.setMaxWidth(USE_PREF_SIZE);
	        anchorPane.setMinHeight(USE_PREF_SIZE);
	        anchorPane.setMinWidth(USE_PREF_SIZE);
	        anchorPane.setPrefHeight(400.0);
	        anchorPane.setPrefWidth(600.0);

	        AnchorPane.setBottomAnchor(vBox, 0.0);
	        AnchorPane.setLeftAnchor(vBox, 0.0);
	        AnchorPane.setRightAnchor(vBox, 0.0);
	        AnchorPane.setTopAnchor(vBox, 0.0);
	        vBox.setPrefHeight(200.0);
	        vBox.setPrefWidth(100.0);

	        VBox.setVgrow(hTMLEditor, javafx.scene.layout.Priority.ALWAYS);
	        hTMLEditor.setHtmlText("<html><head></head><body contenteditable='true'></body></html>");
	        hTMLEditor.setPrefHeight(200.0);
	        hTMLEditor.setPrefWidth(506.0);

	        vBox.getChildren().add(hTMLEditor);
	        anchorPane.getChildren().add(vBox);
	        getChildren().add(anchorPane);

	    }
}
