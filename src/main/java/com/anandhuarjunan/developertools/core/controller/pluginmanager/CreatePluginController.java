package com.anandhuarjunan.developertools.core.controller.pluginmanager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.zeroturnaround.zip.ZipUtil;

import com.anandhuarjunan.developertools.core.constants.CustomPluginTemplateLocation;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.utils.FileUtils;
import com.anandhuarjunan.developertools.core.utils.PropertyUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreatePluginController {
	
	private static final String OUTPUT_FOLDER_LOC = "temp/plugin_output";
	private String outputFolderName = null;
	private String currentFileWorkContext = null;
    @FXML
    private TextField companyId;

    @FXML
    private Button create;

    @FXML
    private TextArea description;

    @FXML
    private TextField fxmlFileName;

    @FXML
    private TextField name;

    @FXML
    private TextField version;
    
    @FXML
    void initialize() {
    	create.setOnAction(event->{
    		try {
				createOutputFolder();
				unzipTemplateToOutputFolder();
				setCurrentContext();
				makeJavaPackage();
				makeResourcesFolder();
				editDtPropertiesFile();
				provideFileName();
			} catch (InternalError e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }

	private void makeResourcesFolder() throws InternalError {
		String companyIdDelimited = companyId.getText().replace('.',File.separatorChar);
		File css = new File(currentFileWorkContext+"FXML_PLUGIN_FILE_NAME_MAVEN"+File.separator
				+"src"+File.separator+"main"+File.separator+"resources"+File.separator+companyIdDelimited+File.separator+"dtplugin"+File.separator+name.getText().replace(" ", "")+File.separator+"css");
		File fxml = new File(currentFileWorkContext+"FXML_PLUGIN_FILE_NAME_MAVEN"+File.separator
				+"src"+File.separator+"main"+File.separator+"resources"+File.separator+companyIdDelimited+File.separator+"dtplugin"+File.separator+name.getText().replace(" ", "")+File.separator+"fxml");
		FileUtils.makeFolder(css);	
		FileUtils.makeFolder(fxml);	
		
		
	}

	private void provideFileName() {
		
	}

	private void editDtPropertiesFile() throws IOException {
		File dtProperties = new File(currentFileWorkContext+"FXML_PLUGIN_FILE_NAME_MAVEN"+File.separator
				+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"DT_PROPERTIES.properties");
		
		Properties properties = PropertyUtils.loadProperty(dtProperties);
		properties.setProperty("COMPANY_ID", companyId.getText());
		properties.setProperty("VERSION", version.getText());
		properties.setProperty("NAME", name.getText());
		PropertyUtils.saveProperties(properties, dtProperties);
		 
	}

	private void setCurrentContext() {
		currentFileWorkContext = OUTPUT_FOLDER_LOC+File.separator+outputFolderName+File.separator;
		
	}

	private void makeJavaPackage() throws InternalError {
		
		String companyIdDelimited = companyId.getText().replace('.',File.separatorChar);
		File curContextFile = new File(currentFileWorkContext+"FXML_PLUGIN_FILE_NAME_MAVEN"+File.separator
				+"src"+File.separator+"main"+File.separator+"java"+File.separator+companyIdDelimited+File.separator+"dtplugin"+File.separator+name.getText().replace(" ", ""));
		FileUtils.makeFolder(curContextFile);
	}

	private void unzipTemplateToOutputFolder() {
		ZipUtil.unpack(new File(CustomPluginTemplateLocation.MAVEN_SIMPLE_FXML_PLUGIN_LOC), new File(OUTPUT_FOLDER_LOC+File.separator+outputFolderName+File.separator));

	}

	private void createOutputFolder() throws InternalError {
			outputFolderName = LocalDate.now().toString();
			FileUtils.makeFolder(OUTPUT_FOLDER_LOC+"/"+outputFolderName); 
		}
		
	

}