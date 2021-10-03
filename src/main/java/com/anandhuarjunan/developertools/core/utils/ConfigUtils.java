
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigUtils {
	/*public static String getConfigPath(Tools tool,String fileName) {
		ToolMetaData toolMetaData = tool.metadata();
		StringBuilder builder = new StringBuilder();
		builder.append(CommonConstant.CONF_FOLDER);
		builder.append(File.separator);
		if(null != toolMetaData.getCategoryName()) {
			builder.append(toolMetaData.getCategoryName());
			builder.append(File.separator);
		}
		if(null != toolMetaData.getConfFolder()) {
			builder.append(toolMetaData.getConfFolder());
			builder.append(File.separator);
		}
		builder.append(fileName);
		return builder.toString();
	}
	*/
	
	
/*	public static CompositeConfiguration getConfiguration(Tools tool) throws ConfigurationException {
		 CompositeConfiguration config = new CompositeConfiguration();
		 Parameters params = new Parameters();
		 ToolMetaData toolMetaData = tool.metadata();
		 if(null != toolMetaData && null != toolMetaData.getConfigName() && toolMetaData.getConfigName().length>0) {
			 	for(String fileName : toolMetaData.getConfigName()) {
			 		FileBasedConfigurationBuilder<PropertiesConfiguration> builderDefaults =
			 			     new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
			 			     .configure(params.properties()
			 			         .setFile(new File(ConfigUtils.getConfigPath(tool, fileName))));
			 		config.addConfiguration(builderDefaults.getConfiguration());
				} 
			 	
			 	return config;
		 }
		 else {
			 return null;
		 }
	}
	*/
	public static CompositeConfiguration getConfiguration(String path,String... fileNames) throws ConfigurationException {
		 CompositeConfiguration config = new CompositeConfiguration();
		 Parameters params = new Parameters();
		 if(null != path && null != fileNames && fileNames.length>0) {
			 	for(String fileName : fileNames) {
			 		FileBasedConfigurationBuilder<PropertiesConfiguration> builderDefaults =
			 			     new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
			 			     .configure(params.properties()
			 			         .setFile(new File(path+File.separator+fileName)));
			 		config.addConfiguration(builderDefaults.getConfiguration());
				} 
			 	return config;
		 }
		 else {
			 return null;
		 }
		
	}
	
	public static void modifyConfiguration(String filePath,String key,String value) throws ConfigurationException {
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
		    new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class, null, true)
		    .configure(params.properties()
		        .setFile(new File(filePath)));

		Configuration cc = new CompositeConfiguration(builder.getConfiguration());
		cc.setProperty(key,value);

		builder.save();
	}
	
	
	
	
}
