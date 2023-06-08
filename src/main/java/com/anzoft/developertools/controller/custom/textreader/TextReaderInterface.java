/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.custom.textreader;

import java.io.File;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.controller.MainController;
import com.anzoft.developertools.views.CustomFxml;

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
