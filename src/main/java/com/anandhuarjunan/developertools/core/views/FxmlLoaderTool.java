/*
 * 
 */
package com.anandhuarjunan.developertools.core.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.database.persistables.ExternalTools;
import com.anandhuarjunan.developertools.core.database.persistables.FxmlLoaderTools;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityByFieldsService;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityService;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.FXMLImporter;

import javafx.scene.Node;

public class FxmlLoaderTool extends AbstractTool {
	
	String fxmlLocation = null;
	FXMLImporter fxmlImporter = null;

	@Override
	public void open() throws ToolError {
		try {
			
			FindEntityByFieldsService<FxmlLoaderTools> dbService = new FindEntityByFieldsService<>();
			Map<String, Object> map = new HashMap<>();
			map.put("tools", getToolData());
			FxmlLoaderTools fxmlLoaderTools = dbService.findEntityByFields(FxmlLoaderTools.class, map).getResult();
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
			
			
			
		} catch (Exception e) {
		    NotifyMessage.notifyError("Failed to Load FXML Tool!");
		    e.printStackTrace();
			throw new ToolError("Tool Cannot Be Opened",e);
		}

	}
	
	
	
    


	public Object getController() {
		return fxmlImporter.getController();
	}






	@Override
	public Node view() throws ToolError {
		return fxmlImporter;
	}

}
