/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.ServiceResult;
import com.anzoft.developertools.database.services.common.FindEntityService;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.utils.ConcurrencyUtils;
import com.anzoft.developertools.views.AbstractTool;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ToolLoaderFactory {
	
	
	private TabPane tabPane = null;
	private ExecutorService executorService = null;
	public ToolLoaderFactory(TabPane pane) {
		this.tabPane = pane;
		executorService =  Executors.newFixedThreadPool(2,ConcurrencyUtils.giveNameToTheThread("TOOL LOADER"));
	}
	
	
	public void loadTool(String toolId){
	    				 try {
					Tools tools = getTool(toolId);
					boolean checkIfToolIsRUnning=checkIfToolIsRUnning(tools);
					if(checkIfToolIsRUnning) {
						return;
					}
					if(null != tools) {
					switch(tools.getToolType().getId()) {
					case "I":
						ToolLoader toolLoader = new InternalToolLoader(tabPane,executorService);
						toolLoader.loadTool(tools);
						break;
					case "F":
						ToolLoader toolFXMLLoader = new FXMLToolLoader(tabPane,executorService);
						toolFXMLLoader.loadTool(tools);
						break;
					case "E":
						ToolLoader externalToolLoader = new ExternalToolLoader(tabPane,executorService);
						externalToolLoader.loadTool(tools);
						break;
							
					default:
						throw new ToolError("Tool Category is wrong.");
					}
					}
					    }
					    catch(ToolError e) {
						e.printStackTrace();
					    }
		
		

			    }	    	  
	  

		
		
	
	
	
	private boolean checkIfToolIsRUnning(Tools tools) {
		if(null != tools) {
		
		if(!tools.isMultiTabSupport()) {
			List<Tab> tabs =tabPane.getTabs();
			if(null != tabs && !tabs.isEmpty()) {
				Optional<Tab> isRunning = tabs.stream().filter(t->tools.getId().equalsIgnoreCase(((AbstractTool)t.getUserData()).getToolID())).findAny();
				if(isRunning.isPresent()) {
					tabPane.getSelectionModel().select(isRunning.get());
					return true;
				}
			}
		}
		}
		return false;
	}
	

	private com.anzoft.developertools.database.persistables.Tools getTool(String toolId) {
		ServiceResult<com.anzoft.developertools.database.persistables.Tools> result = null;
		FindEntityService<com.anzoft.developertools.database.persistables.Tools> dbService = new FindEntityService<>();
		try {
			result = dbService.findEntity(com.anzoft.developertools.database.persistables.Tools.class, toolId);
		} catch (ServiceException e) {
			
		}
		return null != result?result.getResult():null;
	}


	public TabPane getTabPane() {
		return tabPane;
	}


	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	
	
	
	
	
	
}
