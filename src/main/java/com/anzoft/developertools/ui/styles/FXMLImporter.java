/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.ui.styles;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class FXMLImporter extends AnchorPanePlane {

	
	public FXMLImporter() throws IOException {
		super();
		Node node=FXMLLoader.load(FXMLImporter.class.getResource(fxmlPath()));
		AnchorPane.setBottomAnchor(node, 0d);
		AnchorPane.setTopAnchor(node, 0d);
		AnchorPane.setLeftAnchor(node, 0d);
		AnchorPane.setRightAnchor(node, 0d);
		anchorPane.getChildren().setAll(node);
		
		String[] styleSheetPath = styleSheetPath();
		if(null != styleSheetPath) {
			Arrays.stream(styleSheetPath).forEach(str->{
				anchorPane.getStylesheets().add(str);
			});
			
		}
			
		
	}
	
	public abstract String fxmlPath();
	public abstract String[] styleSheetPath();
	
	
}
