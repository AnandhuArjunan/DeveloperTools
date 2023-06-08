/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.constants.AppConfigurations;
import com.anzoft.developertools.exceptions.InternalError;

public class FileUtils {

	
	public static void makeFolder(String path) throws InternalError {
		File f= new File(path);
		if(!f.exists() && !f.mkdir())
			throw new InternalError("Can't create folder "+path);
	}
	
	public static void makeFolder(File f) throws InternalError {
		if(!f.exists() && !f.mkdir())
			throw new InternalError("Can't create folder ");
	}
	
	public static void makeTemporaryFolder(String foldername) throws InternalError {
		String path = GlobalConfig.getInstance().getAppConfiguration().getConfiguration(AppConfigurations.TEMP_FOLDER)+File.separator+foldername;
		makeFolder(path);
	}

	public static void copyFile(File source,File destinationFolder) throws IOException {
	        Path from = source.toPath();
	        Path to = destinationFolder.toPath();
	        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
	    }
	
	public static String readFileToString(String fileName) throws IOException {
	       File file = new File(fileName);
	       return org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	    }
}
