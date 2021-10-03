/*
 * 
 */
package com.anandhuarjunan.developertools.core.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.controller.toolloaders.ToolLoaderFactory;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.thread.AsyncTask;




public abstract class AbstractTool implements Tool{

	private String uniqueId;
	private String toolID;
	private String toolName;
	
	private ToolLoaderFactory tabLoader = null;
	private List<Tools> tools = null;
	private GlobalConfig globalConfig = null;
	private UIResources uiResources = null;
	private Map<String,Object> arguments = null;
	
	protected  AbstractTool() {
	}protected  AbstractTool(Map<String,Object> arguments) {
	   this();
	   this.arguments = arguments;
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


	


	public String getToolID() {
		return toolID;
	}


	public void setToolID(String toolID) {
		this.toolID = toolID;
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
	
	
	   
	
	
	 public Map<String, Object> getArguments() {
			return arguments;
		}




	public void setArguments(Map<String, Object> arguments) {
		this.arguments = arguments;
	}


	
	
}
