/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.controller.toolloaders.ToolLoaderFactory;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.thread.AsyncTask;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public abstract class AbstractTool implements Tool{

	private String uniqueId;
	private String toolID;
	private String toolName;
	
	private  List<AsyncTask<?, ?, ?>> asyncTasks = null;
	private ToolLoaderFactory tabLoader = null;
	private List<Tools> tools = null;
	private GlobalConfig globalConfig = null;
	private UIResources uiResources = null;
	private ToolFooterDataFactory toolFooterDataFactory = new ToolFooterDataFactory();
	private TaskExecutor executor = null;
	
	public  AbstractTool() {
	    asyncTasks = new ArrayList<>();
	    executor = new TaskExecutor();
	}
	
	@Override
	public boolean close() {
		boolean flag = true;
		if(null != asyncTasks) {
			for(AsyncTask<?, ?, ?> task : asyncTasks) {
				flag = !task.isAlive() || task.isInterrupted();
			}
			
		}
		return flag;
	}
	
	
	public String getTempFolder() {
		return File.separator+
				globalConfig.getAppConfiguration().getConfiguration("temp_folder")
				+File.separator+toolID+File.separator;
	}
	
	public String getDataFolder() {
		return 
				globalConfig.getAppConfiguration().getConfiguration("data_folder")
				+File.separator+toolID+File.separator;
	}
	
	public Tools getToolData() {
		if(null != tools) {
			Optional<Tools> tool = tools.stream().filter(tl->tl.getId().equalsIgnoreCase(toolID)).findAny();
			if(tool.isPresent()) {
				return tool.get();
			}
		}
		else {
			return null;
		}
		return null;
	}
	
	
	public List<AsyncTask<?, ?, ?>> gettingRunningTasks(){
		if(null != asyncTasks) {
			return asyncTasks.stream().filter(predicate->!(predicate.isAlive()||predicate.isInterrupted())).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}


	public String getUniqueId() {
		return uniqueId;
	}


	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	public String getToolName() {
		return toolName;
	}


	public void setToolName(String toolName) {
		this.toolName = toolName;
	}


	public List<AsyncTask<?, ?, ?>> getAsyncTasks() {
		return asyncTasks;
	}


	public String getToolID() {
		return toolID;
	}


	public void setToolID(String toolID) {
		this.toolID = toolID;
	}


	public void setAsyncTasks(List<AsyncTask<?, ?, ?>> asyncTasks) {
		this.asyncTasks = asyncTasks;
	}


	public ToolLoaderFactory getTabLoader() {
		return tabLoader;
	}


	public void setTabLoader(ToolLoaderFactory tabLoader) {
		this.tabLoader = tabLoader;
	}


	public List<Tools> getTools() {
		return tools;
	}


	public void setTools(List<Tools> tools) {
		this.tools = tools;
	}


	public GlobalConfig getGlobalConfig() {
		return globalConfig;
	}


	public void setGlobalConfig(GlobalConfig globalConfig) {
		this.globalConfig = globalConfig;
	}


	public UIResources getUiResources() {
		return uiResources;
	}


	public void setUiResources(UIResources uiResources) {
		this.uiResources = uiResources;
	}
	
	
	    public TaskExecutor getExecutor() {
	    return executor;
	}

	    public void loadToolFooter() {
		Platform.runLater(()->{
	    		toolFooterDataFactory.load();	

		});
	    }
	
		
	  class ToolFooterDataFactory{
		  
		  
		  void load() {
			  loadToolDescriptionAndInfo();
		  }
			
		  
		  void loadToolDescriptionAndInfo() {
			  		HBox toolFooter = uiResources.getMainController().getToolFooter();
			  		toolFooter.getChildren().clear();
			  		if(!(null == getToolData().getDescription() || getToolData().getDescription().trim().isEmpty())) {
					FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
					FontAwesomeIconView navIcon = new FontAwesomeIconView(FontAwesomeIcon.NAVICON);
					Label label = new Label(getToolData().getDescription());
					Label clickHereForInfo = new Label("More Info");
					label.setGraphic(icon);
					clickHereForInfo.setGraphic(navIcon);
					toolFooter.getChildren().addAll(label);
					toolFooter.getChildren().add(clickHereForInfo);
				}
			  		
				/*
				 * MemoryMeter meter = new MemoryMeter(); DecimalFormat decformat = new
				 * DecimalFormat("#0.00"); final double RAM_USED_PERCENTAGE =
				 * ((meter.measureDeep(this) * 1.0) / Runtime.getRuntime().totalMemory() )* 100;
				 * toolFooter.getChildren().add(new Label(
				 * decformat.format(RAM_USED_PERCENTAGE)));
				 */
		  
	   }
	

	}
	
	
	
	 class TaskExecutor{
	    
	    
	    public void executeTask(AsyncTask<?, ?, ?> asyncTask) {
		if(null != asyncTask) {
			asyncTasks.add(asyncTask);
			//asyncTask.execute(params);
		}
	    }
	    
	    
	}
	
	
}
