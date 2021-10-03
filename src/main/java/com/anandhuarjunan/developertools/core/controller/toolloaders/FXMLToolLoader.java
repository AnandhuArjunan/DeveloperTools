/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.DBService;
import com.anandhuarjunan.developertools.core.database.services.ServiceArguments;
import com.anandhuarjunan.developertools.core.database.services.tooleditor.GetAllTools;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.pages.ErrorPage;
import com.anandhuarjunan.developertools.core.ui.styles.pages.LoadingPage;
import com.anandhuarjunan.developertools.core.utils.DeveloperToolsUtils;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anandhuarjunan.developertools.core.views.ClosableTool;
import com.anandhuarjunan.developertools.core.views.FxmlLoaderTool;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;


import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FXMLToolLoader extends AbstractToolLoader {
	
	List<AbstractTool> loadedTools = new ArrayList<>();
	Map<String,Object> arguments = null;

	
	public FXMLToolLoader(TabPane tab,ExecutorService executorService) {
		super(tab,executorService);
	}
	
	

	@Override
	public void load(Tools tools) throws ToolError {

		try {
			
			FxmlLoaderTool fxmlLoaderTool = new FxmlLoaderTool();
			Task<Void> task = createLoaderTask(fxmlLoaderTool,tools);
			loadedTools.add(fxmlLoaderTool);
			getExecutorService().execute(task);
			
		}
		catch(Exception e) {
		    e.printStackTrace();
			throw new ToolError("Unable to Open "+tools.getToolName()+"!");
		}

	}
	
	
	
	private Task<Void> createLoaderTask(FxmlLoaderTool fxmlLoaderTool,Tools tools) throws ServiceException{	
		String uid = UUID.randomUUID().toString();

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
	
		task.setOnRunning(e->DeveloperToolsUtils.loadTab(getTabPane(), fxmlLoaderTool, new LoadingPage(),null,true,tools.isClosable()));
		task.setOnSucceeded(e->	{
			try {
				Object controller = fxmlLoaderTool.getController();
				if(controller instanceof FxmlLoaderTool fxmlController && null !=arguments) {
					fxmlController.setArguments(arguments);
				}
				Tab tab = FxComponentUtils.getLoadedTab(getTabPane(), uid);
				tab.setContent(fxmlLoaderTool.view());
				
				tab.setOnCloseRequest(DeveloperToolsUtils.closeEvent(controller));
				
			} catch (ToolError e1) {
				e1.printStackTrace();
			}
		});
		
		task.setOnFailed(e->{
			
			Tab tab = FxComponentUtils.getLoadedTab(getTabPane(), uid);
			if(null != tab) {
				tab.setContent(new ErrorPage());

			}
			
			
			
		});
		
		
		
		
		return task;
	}
	
	
	
	
	private List<Tools> getTools() throws ServiceException{
		 DBService<List<Tools>>  db = new GetAllTools();
		 return  db.run(new ServiceArguments()).getResult();
	}



	@Override
	public void load(Tools tool, Map<String, Object> toolArgs) throws ToolError {
		this.arguments  = toolArgs;
		load(tool);
	}



}
