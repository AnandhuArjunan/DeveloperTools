/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views;

import java.io.IOException;

import com.anzoft.developertools.app.headerpanel.message.NotifyMessage;
import com.anzoft.developertools.database.persistables.FxmlLoaderTools;
import com.anzoft.developertools.database.services.common.FindEntityService;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.styles.FXMLImporter;

import javafx.scene.Node;

public class FxmlLoaderTool extends AbstractTool {
	
	String fxmlLocation = null;
	FXMLImporter fxmlImporter = null;

	@Override
	public void open() throws ToolError {
		try {
			FindEntityService<FxmlLoaderTools> dbService = new FindEntityService<>();
		FxmlLoaderTools fxmlLoaderTools = dbService.findEntity(FxmlLoaderTools.class, getToolID()).getResult();
		
			 fxmlImporter = new FXMLImporter() {
				
				@Override
				public String[] styleSheetPath() {
					return null != fxmlLoaderTools.getStylesheets()?fxmlLoaderTools.getStylesheets().split(","):null;
				}
				
				@Override
				public String fxmlPath() {
					return fxmlLoaderTools.getFxmlPath();
				}
			};
			
			
			
		} catch (IOException | ServiceException e) {
		    NotifyMessage.notifyError( e.getMessage());
			throw new ToolError("Tool Cannot Be Opened");
		}

	}
	
	




	@Override
	public Node view() throws ToolError {
		return fxmlImporter;
	}

}
