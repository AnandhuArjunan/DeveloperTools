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
import com.anzoft.developertools.app.config.FXIDClassMapping;
import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.tooleditor.GetAllTools;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.thread.AsyncTask;
import com.anzoft.developertools.ui.page.LoadingPage;
import com.anzoft.developertools.utils.FxComponentUtils;
import com.anzoft.developertools.views.AbstractTool;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;

public class InternalToolLoader extends AbstractToolLoader{
	
	List<AbstractTool> loadedTools = new ArrayList<>();
	public InternalToolLoader(TabPane tab,ExecutorService executorService) {
		super(tab, executorService);
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
		String uid = UUID.randomUUID().toString();

		if(null != tools) {
		try {
			
			FXIDClassMapping fxidClassMapping = GlobalConfig.getInstance().getFxIDMapping();
			AbstractTool tool = (AbstractTool) Class.forName(fxidClassMapping.getConfiguration(tools.getId())).getDeclaredConstructor().newInstance();
			Task<Void> task = new Task<Void>() {
				
				@Override
				protected Void call()  {
						
					try {
						tool.open();
					} catch (ToolError e) {
						loadErrorPage(uid,tools);
					}
					return null;
				}
			};
			tool.setToolID(tools.getId());
			tool.setToolName(tools.getToolName());
			tool.setUniqueId(uid);
			tool.setTools(getTools());
			tool.setGlobalConfig(GlobalConfig.getInstance());
			tool.setUiResources(UIResources.getInstance());
			tool.setTabLoader(new ToolLoaderFactory(getTabPane()));
			tool.loadToolFooter();
			task.setOnRunning(e->FxComponentUtils.loadTab(getTabPane(), tool, new LoadingPage(),o->onclose(tool),true,tools.isClosable()));
			task.setOnSucceeded(e->	{
				try {
					FxComponentUtils.getLoadedTab(getTabPane(), uid).setContent(tool.view());
				} catch (ToolError e1) {
					loadErrorPage(uid,tools);
				}
			});
			loadedTools.add(tool);
			getExecutorService().execute(task);
			
		}
		catch(RuntimeException e) {
		    throw e;
		}
		catch(Exception e) {
			loadErrorPage(uid,tools);
			ToolError error = new ToolError();
			error.setStackTrace(e.getStackTrace());
			throw error;
		}
		}
	}

	private void onclose(AbstractTool abstractTool) {
		boolean canBeclosed = abstractTool.close();
		if(!canBeclosed) {
			StringBuilder builder = new StringBuilder();
			List<AsyncTask<?, ?, ?>> asyncTask = abstractTool.gettingRunningTasks();
			for(AsyncTask<?, ?, ?> asyncTask2 : asyncTask) {
				builder.append(asyncTask2.name()+" , ");
			}
			Alert alert = new Alert(AlertType.CONFIRMATION, "Tasks such as "+builder+" are still running ,Do you want to close it anyway? ", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				asyncTask.forEach(async->async.interrupt());
			}
		}
	}
	private List<Tools> getTools() throws ServiceException{
		 DBService<List<Tools>>  db = new GetAllTools();
		 return  db.run(new ServiceArguments()).getResult();
	}
	
	
}
