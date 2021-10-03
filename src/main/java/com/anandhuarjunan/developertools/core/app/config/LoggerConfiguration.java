/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.anandhuarjunan.developertools.core.app.ActionExecutor;
import com.anandhuarjunan.developertools.core.app.Validator;
import com.anandhuarjunan.developertools.core.cache.keyvalue.KeyValueConfigurationImpl;
import com.anandhuarjunan.developertools.core.constants.LoggerConfiguations;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;
import com.anandhuarjunan.developertools.core.function.Action;
import com.anandhuarjunan.developertools.core.logging.LoggerFactory;
import com.anandhuarjunan.developertools.core.utils.PropertyUtils;

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

