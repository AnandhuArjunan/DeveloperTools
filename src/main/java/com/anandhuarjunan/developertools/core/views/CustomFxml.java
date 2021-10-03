/*
 * 
 */
package com.anandhuarjunan.developertools.core.views;

import java.io.IOException;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.utils.DialogBox;

import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

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

	public boolean close() {
		if(controller instanceof ClosableTool closable) {
			return closable.performClose();
		}
		return true;
		
		
	
		
		
	}

	@Override
	public Node view() throws ToolError {
		AnchorPane anchorPane = new AnchorPane(node);
		 AnchorPane.setBottomAnchor(node, 0.0);
	        AnchorPane.setLeftAnchor(node, 0.0);
	        AnchorPane.setRightAnchor(node, 0.0);
	        AnchorPane.setTopAnchor(node, 0.0);
		return anchorPane;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}