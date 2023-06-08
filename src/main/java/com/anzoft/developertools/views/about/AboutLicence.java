/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.about;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.TextViewerStyle;
import com.anzoft.developertools.views.AbstractTool;

import javafx.scene.Node;

public class AboutLicence extends AbstractTool{
	
	TextViewerStyle textViewerStyle = new TextViewerStyle();

	

	@Override
	public void open() throws ToolError {
		try {
			textViewerStyle. addChild();
			textViewerStyle.setText(FileUtils.readFileToString(new File("conf/licence.txt"),StandardCharsets.UTF_8));
			}
			catch(Exception e) {
				throw new ToolError("File Not Found conf/licence.txt or Not readable");
			}
		
	}

	@Override
	public Node view() throws ToolError {
		return textViewerStyle;
	}

	
	
	

}
