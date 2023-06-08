/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.anzoft.developertools.app.config.LoggerConfiguration;
import com.anzoft.developertools.constants.LoggerConfiguations;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.LoggerError;
import com.anzoft.developertools.utils.FileUtils;


public class LoggerFactory {

	static LoggerFactory factory = null;
	Logger logger = null;
	String name = null;
	String path = null;
	public static synchronized LoggerFactory getInstance() {
	
		if (null != factory) {
			return factory;
		} else {
			factory = new LoggerFactory();
			return factory;
		}
		
	}

	public Logger getLogger() {
		return logger;
	}
	
	public void initialize(LoggerConfiguration loggerConfiguration) throws InternalError {
		path = loggerConfiguration.getConfiguration(LoggerConfiguations.LOGGER_PATH);
		name = 	loggerConfiguration.getConfiguration(LoggerConfiguations.LOGGER_NAME);	
		FileUtils.makeFolder(path);	
		logger = Logger.getLogger(name);
		logger.setUseParentHandlers(false);
		FileHandler fh;
		try {
			if ( name.endsWith(".log")) {
				fh = new FileHandler(path + File.separator+name);
			} else {
				fh = new FileHandler(path +File.separator+ name + ".log");
			}
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException | IOException e) {
			throw new LoggerError("Unable to start logging.");
		}
	}

}
