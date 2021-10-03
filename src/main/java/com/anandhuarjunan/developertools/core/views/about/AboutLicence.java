/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.about;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.TextViewerStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;

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
