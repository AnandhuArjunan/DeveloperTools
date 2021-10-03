/*
 * 
 */

package com.anandhuarjunan.developertools.core.app.config.global;

import com.anandhuarjunan.developertools.core.app.config.AppConfiguration;
import com.anandhuarjunan.developertools.core.app.config.FXIDClassMapping;
import com.anandhuarjunan.developertools.core.app.config.LoggerConfiguration;

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
