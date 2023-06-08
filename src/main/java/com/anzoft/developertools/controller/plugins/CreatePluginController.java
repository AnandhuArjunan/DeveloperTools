/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.plugins;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;
import org.zeroturnaround.zip.ZipUtil;

import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.utils.JarUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class CreatePluginController {
	
	
	private static final String CREATE_PLUGINS="CREATE_PLUGINS";

    @FXML
    private JFXTextField filelocation;

    @FXML
    private JFXButton browse;

    @FXML
    private HBox generate;

    @FXML
    private JFXTextField fxmlLocation;

    @FXML
    private HBox resultPane;

    @FXML
    private Label resultLabel;

    @FXML
    private JFXButton gotolocation;
    
    @FXML
    private JFXButton generateBtn;

    @FXML
    private JFXTextField css;
    
    @FXML
    void initialize() {
    	
    	generateBtn.setOnAction(ev->generatePlugin());
    	browse.setOnAction(this::chooseJar);
    	gotolocation.setOnAction(this::gotoLocation);

    }
    
    private void chooseJar(ActionEvent ev) {
		resultPane.setVisible(false);

    	FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Java Archive", "*.jar"));
		File inputJar = fileChooser.showOpenDialog(((Node) ev.getSource()).getScene().getWindow());
		if (null != inputJar && inputJar.exists()) {
			filelocation.setText(FilenameUtils.separatorsToSystem(inputJar.getAbsolutePath()));
		}
	}
    
    private void gotoLocation(ActionEvent ev) {
    	if(SystemUtils.IS_OS_WINDOWS) {
        	String tempFolder = GlobalConfig.getInstance().getAppConfiguration().getConfiguration("temp_folder");
        	String tempLoc = tempFolder+File.separator+CREATE_PLUGINS+File.separator;
        	File dest = new File(tempLoc);
    		try {
				Runtime.getRuntime().exec("explorer "+dest.getAbsolutePath()+File.separator);
			} catch (IOException e) {
				e.printStackTrace();
			}

    	}
    }


	private void generatePlugin() {
    	try {File jarSource = new File(filelocation.getText());
    	String tempFolder = GlobalConfig.getInstance().getAppConfiguration().getConfiguration("temp_folder");
    	String tempLoc = tempFolder+File.separator+CREATE_PLUGINS+File.separator+FilenameUtils.removeExtension(jarSource.getName())+File.separator;
    	File dest = new File(tempLoc);
    	JarUtils.decompress(jarSource,dest );
    	File deployFile = new File(tempLoc+"DTPLUGIN.properties");
        FileUtils.touch(deployFile);
        String content = "FXML="+fxmlLocation.getText()+"\n"+"CSS="+css.getText();
		FileUtils.writeStringToFile(deployFile,content,StandardCharsets.UTF_8);    	
		ZipUtil.pack(new File(tempLoc), 
				new File(tempFolder+File.separator+CREATE_PLUGINS+File.separator+FilenameUtils.removeExtension(jarSource.getName())+".dtpl"));

		resultPane.setVisible(true);
		gotolocation.setVisible(true);
		resultLabel.setText("Plugin has generated Successfully - "+jarSource.getName()+".dtpl");
		} catch (Exception e) {
			resultPane.setVisible(true);
			gotolocation.setVisible(false);
			resultLabel.setText("Plugin Generation has failed ."+e.getMessage());
		}
    }
	

}