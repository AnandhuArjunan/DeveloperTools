/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders;

import java.util.concurrent.ExecutorService;

import com.anzoft.developertools.database.persistables.ToolActivityHistory;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.LogToolActivityService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.page.ErrorPage;
import com.anzoft.developertools.utils.FxComponentUtils;
import com.jfoenix.controls.JFXButton;

import javafx.scene.control.TabPane;

public abstract class AbstractToolLoader implements ToolLoader{
	
	TabPane tab = null;
	private ExecutorService executorService = null;
	public AbstractToolLoader(TabPane tab,ExecutorService executorService) {
	        this.executorService =executorService;
		this.tab = tab;
	}

	@Override
	public void loadTool(Tools tool) throws ToolError {
		beforeLoad(tool);
		load(tool);
	}
	
	public void beforeLoad(Tools tool){
		logActivity(tool.getId());
	}
	private void logActivity(String toolId)	 {
		DBService<ToolActivityHistory> logService = new LogToolActivityService();
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(DBService.PRIMARY_KEY, toolId);
		try {
			logService.run(arguments);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 public abstract void load(Tools tool) throws ToolError;

public TabPane getTabPane() {
	return tab;
}


 
public ExecutorService getExecutorService() {
    return executorService;
}


public void loadErrorPage(String uid, Tools tools) {
	ErrorPage errorPage = new ErrorPage();
	JFXButton button = new JFXButton("Check Log");
	errorPage.getvBox().getChildren().add(button);
	FxComponentUtils.loadTab(getTabPane(),tools.getToolName(),uid,errorPage,null,true,true);

}
 
}
