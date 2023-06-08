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

import org.apache.commons.lang3.StringUtils;

import com.anzoft.developertools.app.ActionExecutor;
import com.anzoft.developertools.app.Validator;
import com.anzoft.developertools.cache.keyvalue.KeyValueConfigurationImpl;
import com.anzoft.developertools.constants.AppConfigurations;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ValidateError;
import com.anzoft.developertools.function.Action;
import com.anzoft.developertools.utils.FileUtils;
import com.anzoft.developertools.utils.PropertyUtils;

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

