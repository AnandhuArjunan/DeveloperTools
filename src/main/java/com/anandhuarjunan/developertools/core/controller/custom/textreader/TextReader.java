/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.custom.textreader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.anandhuarjunan.developertools.core.app.headerpanel.ShowPriority;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.logging.LoggerFactory;

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
