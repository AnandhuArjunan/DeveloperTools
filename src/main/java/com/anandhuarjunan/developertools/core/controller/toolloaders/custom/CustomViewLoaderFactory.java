/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders.custom;

import java.util.concurrent.ExecutorService;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.pages.LoadingPage;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;
import com.anandhuarjunan.developertools.core.views.CustomTool;

import javafx.concurrent.Task;
import javafx.scene.control.TabPane;

public class CustomViewLoaderFactory {

	private TabPane tabPane = null;

	public CustomViewLoaderFactory(TabPane tabPane) {
		super();
		this.tabPane = tabPane;
	}
	
	public void loadCustomeTool(CustomTool customTool) {
		String id = customTool.getId();
		Task<Void> task = new Task<Void>() {
			
			@Override
			protected Void call()  {
					
				try {
					customTool .open();
				} catch (ToolError e) {
					//
 				}
				return null;
			}
		};
		task.setOnRunning(e->FxComponentUtils.loadTab(getTabPane(), new LoadingPage(),id,customTool.getHeaderName(),cl->{
			
		}));
		task.setOnSucceeded(e->	{
			try {
				FxComponentUtils.getLoadedTab(getTabPane(), id).setContent(customTool.view());
			} catch (ToolError e1) {
				//
			}
		});
		ExecutorService executorService = UIResources.getInstance().getExecutorService();
		executorService.execute(task);
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
	
	
	
}
