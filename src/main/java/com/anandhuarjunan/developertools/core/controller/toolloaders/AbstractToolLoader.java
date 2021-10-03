/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders;

import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.anandhuarjunan.developertools.core.database.persistables.ToolActivityHistory;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.DBService;
import com.anandhuarjunan.developertools.core.database.services.LogToolActivityService;
import com.anandhuarjunan.developertools.core.database.services.ServiceArguments;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.pages.ErrorPage;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public abstract class AbstractToolLoader implements ToolLoader{
	
	TabPane tab = null;
	private ExecutorService executorService = null;
	protected AbstractToolLoader(TabPane tab,ExecutorService executorService) {
	        this.executorService =executorService;
		this.tab = tab;
	}

	@Override
	public void loadTool(Tools tool) throws ToolError {
		beforeLoad(tool);
		load(tool);
	}
	
	@Override
	public void loadTool(Tools tool,Map<String,Object> toolArgs) throws ToolError {
		beforeLoad(tool);
		load(tool,toolArgs);
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
			e.printStackTrace();
		}
	}
 public abstract void load(Tools tool) throws ToolError;
 public abstract void load(Tools tool,Map<String,Object> toolArgs) throws ToolError;
 

public TabPane getTabPane() {
	return tab;
}


 
public ExecutorService getExecutorService() {
    return executorService;
}


public void loadErrorPage(String uid, Tools tools) {
	ErrorPage errorPage = new ErrorPage();
	Button button = new Button("Check Log");
	errorPage.getvBox().getChildren().add(button);
	FxComponentUtils.loadTab(getTabPane(),tools.getToolName(),uid,errorPage,null,true,true);

}
 
}
