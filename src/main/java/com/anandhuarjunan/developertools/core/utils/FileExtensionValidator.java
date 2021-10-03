package com.anandhuarjunan.developertools.core.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.anandhuarjunan.developertools.core.constants.FileExtensions;

public class FileExtensionValidator{

	
	private FileExtensionValidator(){}
	
	
	
	
	public static boolean validate(FileExtensions extension,String fileName) {
		if(null != extension && !StringUtils.isEmpty(fileName)) {
			String fileExtension = FilenameUtils.getExtension(fileName);
			return extension.getFileExtension().equals(fileExtension);
		}
		return false;
	}

}
