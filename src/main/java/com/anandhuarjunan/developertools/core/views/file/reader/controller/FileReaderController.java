package com.anandhuarjunan.developertools.core.views.file.reader.controller;

import java.io.File;

import org.apache.commons.compress.utils.FileNameUtils;

import com.anandhuarjunan.developertools.core.constants.FileExtensions;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

public interface FileReaderController {

	public void read(File file) throws InternalError;
	public FileExtensions getFileExtension(File file);

	public default void validate(File file) throws ValidateError {
		String ext1 = FileNameUtils.getExtension(file.getAbsolutePath());
		if(!getFileExtension(file).getFileExtension().equalsIgnoreCase(ext1)) {
			throw new ValidateError(file.getName()+" "+"is not a "+getFileExtension(file).getFileExtension()+"!");
		}
	}
	
	
	
	
}
