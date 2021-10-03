/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.file;


import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.CompareToolStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.commonlibs.file.FileOperations;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JsonToCSVConverter extends  AbstractTool {
	
	JsonToCSVUI jsontocsv = new JsonToCSVUI();

	@Override
	public void open() throws ToolError {
		InputStream input = getClass().getClassLoader().getResourceAsStream("images/batch.png");
	     Image image = new Image(input);
	     ImageView imageView = new ImageView(image);
		Label label = new Label("Click here to Convert Batch XML files to JSON files");
		jsontocsv.addItemsToToolBar(imageView);
		jsontocsv.addItemsToToolBar(label);		
		
	}

	@Override
	public Node view() throws ToolError {
		
		return jsontocsv;
	}

	
	class JsonToCSVUI extends CompareToolStyle{

		@Override
		protected void onKey() {
			try {
				execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
	}


	public void execute() throws Exception {
		if(!StringUtils.isEmpty(jsontocsv.input.getText())) {
			jsontocsv.output.setText(FileOperations.convertJsonToCsv(jsontocsv.input.getText()));
		}
	 
		
	}

}
