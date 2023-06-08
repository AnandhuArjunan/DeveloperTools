/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders.custom;

import java.util.concurrent.ExecutorService;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.page.LoadingPage;
import com.anzoft.developertools.utils.FxComponentUtils;
import com.anzoft.developertools.views.CustomTool;

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
			try {
				customTool.close();
			} catch (ToolError e2) {
				//
			}
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
