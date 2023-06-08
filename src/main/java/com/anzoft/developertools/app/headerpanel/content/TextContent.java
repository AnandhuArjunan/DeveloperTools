/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.headerpanel.content;

import java.util.Arrays;
import java.util.List;

import com.anzoft.developertools.app.headerpanel.HeaderPanelContent;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class TextContent extends HeaderPanelContent {
   TextArea field = null;
    public TextContent(int priority, String headingName) {
	super(priority, headingName);
    }

    @Override
    public void load(Object... objects) {
	String content  = (String) objects[0];
	field =  new TextArea(content);
	field.setFocusTraversable(false);
	field.setWrapText(true);
	field.setPrefRowCount(2);
	field.setEditable(false);	
    }

    @Override
    public List<Node> show() {
	return Arrays.asList(field);
    }

    
    
}
