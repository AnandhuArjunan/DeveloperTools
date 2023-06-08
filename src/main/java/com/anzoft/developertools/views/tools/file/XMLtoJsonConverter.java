/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.file;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.anzoft.commonlibs.file.FileOperations;
import com.anzoft.developertools.exceptions.ProcessError;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.CompareToolStyle;
import com.anzoft.developertools.utils.DialogBox;
import com.anzoft.developertools.views.AbstractTool;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class XMLtoJsonConverter extends AbstractTool{
	
	
	private XmlToJsonUI xmlToJsonUI = new XmlToJsonUI();
	

	void execute() throws ProcessError {
		try {
			if(!StringUtils.isEmpty(xmlToJsonUI.input.getText())) {
				xmlToJsonUI.output.setText(FileOperations.convertXMLToJson(xmlToJsonUI.input.getText()));
			}
		}
		catch(RuntimeException e) {
		    throw e;
		}catch (Exception e) {
			throw new ProcessError("Invalid XML");
		}
		
	}


	class XmlToJsonUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			try {
				execute();
			} catch (ProcessError e) {
				e.printStackTrace();
			}
			
		}

		
		
	}


	@Override
	public void open() throws ToolError {
		try {
			 InputStream input = getClass().getClassLoader().getResourceAsStream("images/batch.png");
		     Image image = new Image(input);
		     ImageView imageView = new ImageView(image);
			Label label = new Label("Click here to Convert Batch XML files to JSON files");
			xmlToJsonUI.addItemsToToolBar(imageView);
			xmlToJsonUI.addItemsToToolBar(label);
			}
		catch(RuntimeException e) {
		    throw e;
		}
			catch (Exception e) {
			DialogBox.showErrorBox(new ToolError(ExceptionUtils.getStackTrace(e)));	
			}
		
	}

	@Override
	public Node view() throws ToolError {
		
		return xmlToJsonUI;
	}

}
