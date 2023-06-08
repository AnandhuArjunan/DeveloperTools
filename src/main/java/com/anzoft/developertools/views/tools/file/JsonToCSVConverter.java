/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.file;


import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.anzoft.commonlibs.file.FileOperations;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.CompareToolStyle;
import com.anzoft.developertools.views.AbstractTool;

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
