/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views;

import java.io.IOException;

import com.anzoft.developertools.exceptions.ToolError;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class CustomFxml implements Tool{

	String fxml;
	Object controller;
	Node node = null;
	String name;

	public String getFxml() {
		return fxml;
	}

	public void setFxml(String fxml) {
		this.fxml = fxml;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public CustomFxml(String name,String fxml, Object controller) {
		super();
		this.fxml = fxml;
		this.name = name;
		this.controller = controller;
	}

	@Override
	public void open() throws ToolError {
		FXMLLoader loader = new FXMLLoader(CustomFxml.class.getResource(fxml));
		loader.setController(controller);
		try {
			node = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean close() throws ToolError {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Node view() throws ToolError {
		
		return node;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}