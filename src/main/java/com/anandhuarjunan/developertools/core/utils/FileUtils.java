
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;

import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.constants.AppConfigurations;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public class FileUtils {

	

	public static void makeFolder(String path) throws InternalError {
		File file = new File(path); 
		if(!file.exists()) {
			try {
				org.apache.commons.io.FileUtils.forceMkdir(file);
			} catch (IOException e) {
				throw new InternalError("Unable to Create the Folder in "+path, e);
			}
		}
		
	}
	
	public static void makeFolder(File f) throws InternalError {
		if(!f.exists())
			try {
			org.apache.commons.io.FileUtils.forceMkdir(f);

			} catch (IOException e) {
				throw new InternalError("Unable to Create the Folder in "+f, e);
			}	}
	
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
	
	public static File[] getSubFiles(File file) throws InternalError {
		if(file.isFile()) {
			throw new InternalError("The Given location is a file , not a directory");
		}else {
			return file.listFiles();
		}
	        
	    }
	
	public static File pathToFile(String filePath) {
		return new File(FilenameUtils.separatorsToSystem(filePath));
	        
	    }
	
	
	
}
