/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.anzoft.developertools.app.ActionExecutor;
import com.anzoft.developertools.app.Validator;
import com.anzoft.developertools.cache.keyvalue.KeyValueConfigurationImpl;
import com.anzoft.developertools.constants.LoggerConfiguations;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ValidateError;
import com.anzoft.developertools.function.Action;
import com.anzoft.developertools.logging.LoggerFactory;
import com.anzoft.developertools.utils.PropertyUtils;

public class LoggerConfiguration extends KeyValueConfigurationImpl<String, String> implements Validator,ActionExecutor{

		private static final String LOGGER_CONFIG_FILE = "conf/Logger.properties";

		public LoggerConfiguration() throws InternalError, ValidateError {
super();
}
		
		private void loadPropertiesFromFile() throws InternalError {
			try {
				Map<String,String> properties = PropertyUtils.convertToMap(new File(LOGGER_CONFIG_FILE));
				properties.forEach(super::addConfiguration);
			}
		catch(IOException e) {
			throw new InternalError("Logger Configurations are not loaded.");
		}
		}

		@Override
		public void validate() throws ValidateError {
			String name = getConfiguration(LoggerConfiguations.LOGGER_NAME);
			String path = getConfiguration(LoggerConfiguations.LOGGER_PATH);
			if(null == name && null==path){
				throw new ValidateError("Unable to start Logger");
			}
			if (null == name || name.isEmpty()) {
				throw new ValidateError(LoggerConfiguations.LOGGER_NAME+" should not be empty");
			}
			if (null == path || path.isEmpty()) {
				throw new ValidateError(LoggerConfiguations.LOGGER_PATH+" should not be empty");
			}

			
		}

		@Override
		public void loadConfiguration() throws InternalError {
			loadPropertiesFromFile();
			
		}

		@Override
		public Action action() {
			return ()->		LoggerFactory.getInstance().initialize(this);

		}

		


	}

