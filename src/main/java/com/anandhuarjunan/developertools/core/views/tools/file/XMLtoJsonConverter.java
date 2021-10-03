/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.file;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.anandhuarjunan.developertools.core.exceptions.ProcessError;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.utils.DialogBox;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.file.FileOperations;

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
