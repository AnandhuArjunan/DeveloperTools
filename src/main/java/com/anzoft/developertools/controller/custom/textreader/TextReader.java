/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.custom.textreader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.anzoft.developertools.app.headerpanel.ShowPriority;
import com.anzoft.developertools.app.headerpanel.message.NotifyMessage;
import com.anzoft.developertools.logging.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TextReader {
    @FXML
    private TextArea text;
    
    public void loadFile(File file) {
	try {
	    text.setText(FileUtils.readFileToString(file,StandardCharsets.UTF_8));
		} catch (IOException e) {
		      	NotifyMessage.notifyError("Failed to Read the File !",ShowPriority.HIGH_PRIORITY);
			LoggerFactory.getInstance().getLogger().warning(e.getMessage());
		}
    }
    
}
