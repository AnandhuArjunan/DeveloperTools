package com.anandhuarjunan.developertools.core.controller.custom.filereader;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.controller.MainController;
import com.anandhuarjunan.developertools.core.views.CustomFxml;
import com.anandhuarjunan.developertools.core.views.constants.FileReaderTypeControllerMapping;
import com.anandhuarjunan.developertools.core.views.file.reader.controller.FileReaderController;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

public class FileReader {

	private FileReader(){}
	
	public static void read(FileReaderTypeControllerMapping fileReaderTypeControllerMapping,String fileStr) throws ValidateError, InternalError {
		if(!StringUtils.isEmpty(fileStr)) {
			
			
			Runnable runnable = ()->{

				Object[] fxmlControllerMapping = fileReaderTypeControllerMapping.getFxmlControllerMapping();
				File file = new File(FilenameUtils.separatorsToSystem(fileStr));
				FileReaderController fileReaderController = (FileReaderController)fxmlControllerMapping[1];
				CustomFxml customFxml = new CustomFxml(file.getName(), (String)fxmlControllerMapping[0], fxmlControllerMapping[1]);
				MainController mainController = UIResources.getInstance().getMainController();
				mainController.getCustomFXMLLoaderFactory().loadFXML(customFxml);
				try {
					fileReaderController.validate(file);
				} catch (ValidateError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fileReaderController.read(file);
				} catch (InternalError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			
			new Thread(runnable).start();
			
		}
		else {
			throw new ValidateError("Please provide a valid file.");
		}
	}
	
	
	
	
}
