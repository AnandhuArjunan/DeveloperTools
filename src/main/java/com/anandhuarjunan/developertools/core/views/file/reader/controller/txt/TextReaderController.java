package com.anandhuarjunan.developertools.core.views.file.reader.controller.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.fxmisc.richtext.StyleClassedTextArea;

import com.anandhuarjunan.developertools.core.constants.FileExtensions;
import com.anandhuarjunan.developertools.core.views.file.reader.controller.FileReaderController;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TextReaderController implements FileReaderController {

    @FXML
    private StyleClassedTextArea textarea;
	@Override
	public void read(File file) throws InternalError {
		Platform.runLater(()->		textarea.setWrapText(true));

		try (FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader)) {
				StringBuilder sb = new StringBuilder();

Platform.runLater(()->{
	 String haveRead;       

	 
	 
	 
	try {
		while ((haveRead = reader.readLine()) != null) {
			sb.append(haveRead);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
});

Platform.runLater(()->textarea.appendText(sb.toString()+"\n"));
		}
		catch(IOException exception) {
			
		}
		
	}

	@Override
	public FileExtensions getFileExtension(File file) {
		return FileExtensions.TXT;
	}

	

    private static List<String> randomAccessFileLinkedList(String path) {
        return randomAccessFile(path, new LinkedList<>());
    }


    private static List<String> randomAccessFile(String path, List<String> list) {
        try {
            RandomAccessFile file = new RandomAccessFile(path, "r");
            String str;
            while ((str = file.readLine()) != null) {
                list.add(str);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	
}
