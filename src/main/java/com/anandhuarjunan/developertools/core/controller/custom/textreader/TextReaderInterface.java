/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.custom.textreader;

import java.io.File;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.controller.MainController;
import com.anandhuarjunan.developertools.core.views.CustomFxml;

public class TextReaderInterface {

    public static void loadFile(String path) {
		File file = new File(path);
		TextReader reader = new TextReader();
		CustomFxml  customFxml =  new CustomFxml(file.getName(), "/fxml/custom/TextReader.fxml", reader);
		MainController mainController = UIResources.getInstance().getMainController();
		mainController.getCustomFXMLLoaderFactory().loadFXML(customFxml);
		reader.loadFile(file);

    }
    
}
