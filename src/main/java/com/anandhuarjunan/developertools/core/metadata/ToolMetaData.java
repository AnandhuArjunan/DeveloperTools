/*
 * 
 */
package com.anandhuarjunan.developertools.core.metadata;

public class ToolMetaData {

	 String id = null;
	 String name = null;
	 String tempFolder = null;
	 String description = null;
	 String categoryName = null;
	 String[] configName = null;
	 String confFolder = null; 
	 String dataFolder = null;
	 
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTempFolder() {
		return tempFolder;
	}

	public String getDescription() {
		return description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String[] getConfigName() {
		return configName.clone();
	}

	public String getConfFolder() {
		return confFolder;
	}

	public String getDataFolder() {
		return dataFolder;
	}

	private ToolMetaData(ToolMetaDataBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.tempFolder = builder.tempFolder;
		this.description = builder.description;
		this.categoryName = builder.categoryName;
		this.configName = builder.configName;
		this.confFolder = builder.confFolder;
		this.dataFolder = builder.dataFolder;

	}

	public static class ToolMetaDataBuilder{
		 String id = null;
		 String name = null;
		 String tempFolder = null;
		 String description = null;
		 String categoryName = null;
		 String[] configName = null;
		 String confFolder = null; 
		 String dataFolder = null;
	
		
		public  ToolMetaDataBuilder ofName(String name) {
			this.name = name;
			return this;
		}
		public  ToolMetaDataBuilder ofId(String id) {
			this.id = id;
			return this;
		}
		public  ToolMetaDataBuilder ofDescription(String description) {
			this.description = description;
			return this;
		}
		
		public  ToolMetaDataBuilder ofTempFolder(String tempFolder) {
			this.tempFolder = tempFolder;
			return this;
		}
		public  ToolMetaDataBuilder ofCategoryName(String categoryName) {
			this.categoryName = categoryName;
			return this;
		}
		
		public  ToolMetaDataBuilder ofConfigName(String[] configName) {
			this.configName =configName;
			return this;
		}
		public  ToolMetaDataBuilder ofConfFolder(String confFolder) {
			this.confFolder = confFolder;
			return this;
		}
		
		public  ToolMetaDataBuilder ofDataFolder(String dataFolder) {
			this.dataFolder =dataFolder;
			return this;
		}
			
		
		
		public ToolMetaData build() {
			return new ToolMetaData(this);
			
		}
		
		
	}
	
	
}

	
	
