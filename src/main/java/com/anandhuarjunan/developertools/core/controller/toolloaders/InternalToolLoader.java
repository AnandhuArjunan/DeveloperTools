/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.config.FXIDClassMapping;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.DBService;
import com.anandhuarjunan.developertools.core.database.services.ServiceArguments;
import com.anandhuarjunan.developertools.core.database.services.tooleditor.GetAllTools;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.thread.AsyncTask;
import com.anandhuarjunan.developertools.core.ui.styles.pages.LoadingPage;
import com.anandhuarjunan.developertools.core.utils.DeveloperToolsUtils;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;

import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anandhuarjunan.developertools.core.views.ClosableTool;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class InternalToolLoader extends AbstractToolLoader{
	
	List<AbstractTool> loadedTools = new ArrayList<>();
	public InternalToolLoader(TabPane tab,ExecutorService executorService) {
		super(tab, executorService);
	}
	
	
	
	


	@Override
	public void load(Tools tools) throws ToolError {
		String uid = UUID.randomUUID().toString();

		if(null != tools) {
		try {
			
			FXIDClassMapping fxidClassMapping = GlobalConfig.getInstance().getFxIDMapping();
			AbstractTool tool = (AbstractTool) Class.forName(fxidClassMapping.getConfiguration(tools.getId())).getDeclaredConstructor().newInstance();
			
			
			
			Task<Void> task = createToolInvokeTask(uid, tools, tool);
			startLoadingTool(tool,task);
			
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		    throw e;
		}
		catch(Exception e) {
			handleError(e,uid,tools);
		}
		}
	}
	
	
	private void startLoadingTool(AbstractTool tool,Task<Void> task) {
		getExecutorService().execute(task);
		loadedTools.add(tool);

	}
	
	
	private void handleError(Exception e, String uid,Tools tools) throws ToolError {
		loadErrorPage(uid,tools);
		ToolError error = new ToolError();
		error.setStackTrace(e.getStackTrace());
		throw error;
	}

	private void onclose(AbstractTool abstractTool) {
	
	}
	private List<Tools> getTools() throws ServiceException{
		 DBService<List<Tools>>  db = new GetAllTools();
		 return  db.run(new ServiceArguments()).getResult();
	}



	
	private Task<Void> createToolInvokeTask(String uid,Tools tools,AbstractTool abstractTool) throws ServiceException {

		Task<Void> task = new Task<Void>() {
			
			@Override
			protected Void call()  {
					
				try {
					abstractTool.open();
				} catch (ToolError e) {
					loadErrorPage(uid,tools);
				}
				return null;
			}
		};
		abstractTool.setToolID(tools.getId());
		abstractTool.setToolName(tools.getToolName());
		abstractTool.setUniqueId(uid);
		abstractTool.setTools(getTools());
		abstractTool.setGlobalConfig(GlobalConfig.getInstance());
		abstractTool.setUiResources(UIResources.getInstance());
		abstractTool.setTabLoader(new ToolLoaderFactory(getTabPane()));
		task.setOnRunning(e->DeveloperToolsUtils.loadTab(getTabPane(), abstractTool, new LoadingPage(),o->onclose(abstractTool),true,tools.isClosable()));
		task.setOnSucceeded(e->	{
			try {
				Tab tab = FxComponentUtils.getLoadedTab(getTabPane(), uid);
				tab.setContent(abstractTool.view());
				
				
				tab.setOnCloseRequest(DeveloperToolsUtils.closeEvent(abstractTool));

			} catch (ToolError e1) {
				loadErrorPage(uid,tools);
			}
		});
		
		return task;
		
	}



	@Override
	public void load(Tools tools, Map<String, Object> toolArgs) throws ToolError {
		String uid = UUID.randomUUID().toString();

		if(null != tools) {
		try {
			
			FXIDClassMapping fxidClassMapping = GlobalConfig.getInstance().getFxIDMapping();
			Class<?> toolClz =  Class.forName(fxidClassMapping.getConfiguration(tools.getId()));
			Constructor<?> constructor = toolClz.getConstructor(Map.class);
			AbstractTool abstractTool = (AbstractTool) constructor.newInstance(toolArgs);
			Task<Void> task = createToolInvokeTask(uid, tools, abstractTool);
			startLoadingTool(abstractTool,task);
			
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		    throw e;
		}
		catch(Exception e) {
			handleError(e,uid,tools);
		}
	}






	}
	
}
