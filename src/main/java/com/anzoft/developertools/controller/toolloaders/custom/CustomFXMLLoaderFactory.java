/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders.custom;

import java.util.UUID;

import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.views.CustomFxml;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CustomFXMLLoaderFactory {

	
	private TabPane tabPane = null;

	public CustomFXMLLoaderFactory(TabPane tabPane) {
		super();
		this.tabPane = tabPane;
	}
	
	public void loadFXML(CustomFxml customFxml) {
		try {
			customFxml.open();
			Tab tab = new Tab();
			tab.setId(UUID.randomUUID().toString());
			tab.setText(customFxml.getName());
			tab.setClosable(true);
			tab.setOnCloseRequest(e->{
				try {
					customFxml.close();
				} catch (ToolError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			tab.setContent(customFxml.view());
			tabPane.getSelectionModel().select(tab);
			tabPane.getTabs().add(tab);
		} catch (ToolError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
	
	
}
