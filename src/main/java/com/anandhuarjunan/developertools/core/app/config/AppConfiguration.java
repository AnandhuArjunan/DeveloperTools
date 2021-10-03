/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.anandhuarjunan.developertools.core.app.ActionExecutor;
import com.anandhuarjunan.developertools.core.app.Validator;
import com.anandhuarjunan.developertools.core.cache.keyvalue.KeyValueConfigurationImpl;
import com.anandhuarjunan.developertools.core.constants.AppConfigurations;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;
import com.anandhuarjunan.developertools.core.function.Action;
import com.anandhuarjunan.developertools.core.utils.FileUtils;
import com.anandhuarjunan.developertools.core.utils.PropertyUtils;

public class AppConfiguration extends KeyValueConfigurationImpl<String,String> implements Validator,ActionExecutor {

		public AppConfiguration() throws InternalError, ValidateError {
		super();
	}

		private static final String APP_CONFIG_FILE = "conf/Application.properties";
		
		private void loadPropertiesFromFile() throws InternalError {
			try {
				Map<String,String> properties = PropertyUtils.convertToMap(new File(APP_CONFIG_FILE));
				properties.forEach(super::addConfiguration);
			}
		catch(IOException e) {
			throw new InternalError("Application Configurations are not loaded.");
		}
		}

		@Override
		public void validate() throws ValidateError {
			if(!isPresent(AppConfigurations.TEMP_FOLDER)) {
				throw new ValidateError(AppConfigurations.TEMP_FOLDER+" Not Found in Application.properties");
			}
			if(!isPresent(AppConfigurations.THEME)) {
				throw new ValidateError(AppConfigurations.THEME+" Not Found in Application.properties");
			}
			if(isPresent(AppConfigurations.TEMP_FOLDER) && StringUtils.isEmpty(getConfiguration(AppConfigurations.TEMP_FOLDER))) {
				throw new ValidateError(AppConfigurations.TEMP_FOLDER+" Not configured in Application.properties");
			}
			if(isPresent(AppConfigurations.THEME) && StringUtils.isEmpty(getConfiguration(AppConfigurations.THEME))) {
				throw new ValidateError(AppConfigurations.THEME+" Not configured in Application.properties");
			}
			
		}

		@Override
		public void loadConfiguration() throws InternalError {
			loadPropertiesFromFile();
			
		}

		@Override
		public Action action() {
			return ()->FileUtils.makeFolder(getConfiguration(AppConfigurations.TEMP_FOLDER));
		}
		
	}

