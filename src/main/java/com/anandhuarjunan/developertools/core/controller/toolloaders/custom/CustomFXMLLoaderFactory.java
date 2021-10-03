/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders.custom;

import java.util.UUID;

import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.utils.DeveloperToolsUtils;
import com.anandhuarjunan.developertools.core.views.CustomFxml;

import javafx.application.Platform;
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
			tab.setOnCloseRequest(DeveloperToolsUtils.closeEvent(customFxml.getController()));
			
			tab.setContent(customFxml.view());
			tab.setUserData(customFxml.getController());
			tabPane.getSelectionModel().select(tab);
			Platform.runLater(()->tabPane.getTabs().add(tab));
			
		} catch (ToolError e) {
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
