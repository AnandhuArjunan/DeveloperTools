/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */

package com.anzoft.developertools.app.config.global;

import com.anzoft.developertools.app.config.AppConfiguration;

import com.anzoft.developertools.app.config.FXIDClassMapping;
import com.anzoft.developertools.app.config.LoggerConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalConfig.
 */
public class GlobalConfig {

	/** The global config. */
	private static GlobalConfig globalConfig = null;

	/** The app configuration. */
	private AppConfiguration appConfiguration = null;
	
	/** The logger configuration. */
	private LoggerConfiguration loggerConfiguration = null;
	
	
	/** The fx ID mapping. */
	private FXIDClassMapping fxIDMapping = null;

	/**
	 * Gets the app configuration.
	 *
	 * @return the app configuration
	 */
	public AppConfiguration getAppConfiguration() {
		return appConfiguration;
	}

	/**
	 * Sets the app configuration.
	 *
	 * @param appConfiguration the new app configuration
	 */
	public void setAppConfiguration(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}

	/**
	 * Gets the logger configuration.
	 *
	 * @return the logger configuration
	 */
	public LoggerConfiguration getLoggerConfiguration() {
		return loggerConfiguration;
	}

	/**
	 * Sets the logger configuration.
	 *
	 * @param loggerConfiguration the new logger configuration
	 */
	public void setLoggerConfiguration(LoggerConfiguration loggerConfiguration) {
		this.loggerConfiguration = loggerConfiguration;
	}


	/**
	 * Gets the fx ID mapping.
	 *
	 * @return the fx ID mapping
	 */
	public FXIDClassMapping getFxIDMapping() {
		return fxIDMapping;
	}

	/**
	 * Sets the fx ID mapping.
	 *
	 * @param fxIDMapping the new fx ID mapping
	 */
	public void setFxIDMapping(FXIDClassMapping fxIDMapping) {
		this.fxIDMapping = fxIDMapping;
	}

	/**
	 * Gets the single instance of GlobalConfig.
	 *
	 * @return single instance of GlobalConfig
	 */
	public static synchronized GlobalConfig getInstance() {
		if (globalConfig == null)
			globalConfig = new GlobalConfig();

		return globalConfig;
	}
}
