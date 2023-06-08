/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.tooleditor.GetAllTools;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.page.LoadingPage;
import com.anzoft.developertools.utils.FxComponentUtils;
import com.anzoft.developertools.views.AbstractTool;
import com.anzoft.developertools.views.FxmlLoaderTool;

import javafx.concurrent.Task;
import javafx.scene.control.TabPane;

public class FXMLToolLoader extends AbstractToolLoader {
	
	List<AbstractTool> loadedTools = new ArrayList<>();

	
	public FXMLToolLoader(TabPane tab,ExecutorService executorService) {
		super(tab,executorService);
		listenToTabChanges();
	}
	
	
	private void listenToTabChanges() {
		modifyToolInfoForCurrentTab();
	}
	
	
	
	private void modifyToolInfoForCurrentTab() {
		getTabPane().getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
		       Optional<AbstractTool> optional = loadedTools.stream().filter(at->at.getUniqueId().equalsIgnoreCase(newTab.getId())).findFirst();
		       if(optional.isPresent()) {
		    	   AbstractTool abstractTool = optional.get();
		    	   abstractTool.loadToolFooter();
		       }
		    });
	}
	@Override
	public void load(Tools tools) throws ToolError {

		try {
			
			String uid = UUID.randomUUID().toString();
			FxmlLoaderTool fxmlLoaderTool = new FxmlLoaderTool();
			Task<Void> task = new Task<Void>() {
				
				@Override
				protected Void call() throws ToolError {
						
					fxmlLoaderTool.open();
					return null;
				}
			};
			fxmlLoaderTool.setToolID(tools.getId());
			fxmlLoaderTool.setToolName(tools.getToolName());
			fxmlLoaderTool.setUniqueId(uid);
			fxmlLoaderTool.setTools(getTools());
			fxmlLoaderTool.setGlobalConfig(GlobalConfig.getInstance());
			fxmlLoaderTool.setUiResources(UIResources.getInstance());
			fxmlLoaderTool.loadToolFooter();
			task.setOnRunning(e->FxComponentUtils.loadTab(getTabPane(), fxmlLoaderTool, new LoadingPage(),null,true,tools.isClosable()));
			task.setOnSucceeded(e->	{
				try {
					FxComponentUtils.getLoadedTab(getTabPane(), uid).setContent(fxmlLoaderTool.view());
				} catch (ToolError e1) {
					e1.printStackTrace();
				}
			});
			loadedTools.add(fxmlLoaderTool);
			getExecutorService().execute(task);
			
		}
		catch(Exception e) {
		    e.printStackTrace();
			throw new ToolError("Unable to Open "+tools.getToolName()+"!");
		}

	}
	private List<Tools> getTools() throws ServiceException{
		 DBService<List<Tools>>  db = new GetAllTools();
		 return  db.run(new ServiceArguments()).getResult();
	}
}
