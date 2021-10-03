/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.ServiceResult;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityService;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.utils.ConcurrencyUtils;
import com.anandhuarjunan.developertools.core.views.AbstractTool;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ToolLoaderFactory {
	
	
	private TabPane tabPane = null;
	private ExecutorService executorService = null;
	public ToolLoaderFactory(TabPane pane) {
		this.tabPane = pane;
		executorService =  Executors.newCachedThreadPool(ConcurrencyUtils.giveNameToTheThread("TOOL LOADER"));
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
	  

	public void loadTool(String toolId,Map<String,Object> args){
		if(null == args) {
			loadTool(toolId);
		}else {
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
		toolLoader.loadTool(tools,args);
		break;
	case "F":
		ToolLoader toolFXMLLoader = new FXMLToolLoader(tabPane,executorService);
		toolFXMLLoader.loadTool(tools,args);
		break;
	case "E":
		ToolLoader externalToolLoader = new ExternalToolLoader(tabPane,executorService);
		externalToolLoader.loadTool(tools,args);
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

}	    	  
		
		
	
	
	
	private boolean checkIfToolIsRUnning(Tools tools) {
		if(null != tools) {
		
		if(!tools.isMultiTabSupport()) {
			List<Tab> tabs =tabPane.getTabs().stream().filter(t->t.getUserData() instanceof AbstractTool).collect(Collectors.toList());
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
	

	private com.anandhuarjunan.developertools.core.database.persistables.Tools getTool(String toolId) {
		ServiceResult<com.anandhuarjunan.developertools.core.database.persistables.Tools> result = null;
		FindEntityService<com.anandhuarjunan.developertools.core.database.persistables.Tools> dbService = new FindEntityService<>();
		try {
			result = dbService.findEntity(com.anandhuarjunan.developertools.core.database.persistables.Tools.class, toolId);
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
