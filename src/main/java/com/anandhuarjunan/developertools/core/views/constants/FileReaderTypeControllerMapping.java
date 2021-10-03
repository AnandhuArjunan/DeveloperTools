package com.anandhuarjunan.developertools.core.views.constants;

import com.anandhuarjunan.developertools.core.views.file.reader.controller.txt.TextReaderController;

public enum FileReaderTypeControllerMapping {

	
	TXT(new Object[]{"/fxml/custom/filereaders/txtreader.fxml",new TextReaderController()});
	
	
	
	private Object[] fxmlControllerMapping = null;
	
	FileReaderTypeControllerMapping(Object[] fxmlControllerMapping){
		this.fxmlControllerMapping = fxmlControllerMapping;
	}

	public Object[] getFxmlControllerMapping() {
		return fxmlControllerMapping;
	}

	
	
	
}
