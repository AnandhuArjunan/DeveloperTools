/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.theme;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.configuration2.ex.ConfigurationException;

import com.anandhuarjunan.developertools.core.app.config.AppConfiguration;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.app.theme.model.Theme;
import com.anandhuarjunan.developertools.core.app.theme.model.Themes;
import com.anandhuarjunan.developertools.core.constants.AppConfigurations;
import com.anandhuarjunan.developertools.core.constants.CommonConstant;
import com.anandhuarjunan.developertools.core.constants.FileNames;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.utils.ConfigUtils;
import com.anandhuarjunan.developertools.core.utils.FileUtils;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;
import com.anandhuarjunan.developertools.core.utils.JAXBUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class ThemeManager  {
	
	private Map<String,String[]> definedthemes = new HashMap<>();
	private Scene scene = null;
	private static final String THEME_INFO_FILE = "theme.xml";
	
	public ThemeManager(Scene scene) throws InternalError {
		this.scene = scene;
		loadDefinedThemes();
		validateConf();
	}

	
	
	public void loadDefinedThemes() throws InternalError{
		
		
		
		
			File themeFile = FileUtils.pathToFile(CommonConstant.THEME_FOLDER+THEME_INFO_FILE);
			
			Themes loadedthemes = JAXBUtils.unmarchal(themeFile, Themes.class);
			
			
			if(null != loadedthemes) {
				Theme[] themes = loadedthemes.getTheme();
				if(null != themes && themes.length>0) {
					
					for(Theme theme : themes) {
						definedthemes.put(theme.getId(),new String[] {theme.getName(),CommonConstant.THEME_FOLDER+theme.getLocation()});
					}
				}else {
					throw new InternalError("No themes are present");

				}
			}else {
				throw new InternalError("Failed to load Themes.");
			}
			
			
			
			
			
		


	}
	
	
	
	
	
	
	public void loadThemesOnMenu(Menu menu) {
		AppConfiguration appConfiguration = GlobalConfig.getInstance().getAppConfiguration();
		String theme = appConfiguration.getConfiguration(AppConfigurations.THEME);
		 ToggleGroup tg = new ToggleGroup(); 
		 definedthemes.entrySet().forEach(t->{
			 RadioMenuItem item = new RadioMenuItem(t.getValue()[0]);
			 item.setToggleGroup(tg);
			 if(t.getKey().equalsIgnoreCase(theme)) {
				 item.setSelected(true); 
			 }
			 FxComponentUtils.addMenuItemToTheMenu(menu,item);
		 });
		 ChangeListener<Toggle> listener=(ObservableValue<? extends Toggle> ob,  Toggle o, Toggle n) ->{
			try {
				loadTheme(tg);
			} catch (Exception e) {
				NotifyMessage.notifyError("Failed to Load the Theme.");
			}
		};
		tg.selectedToggleProperty().addListener(listener);
		 
		 
		 
	}
	
	private void loadTheme(Object input) throws   ConfigurationException, InternalError{
		if(input instanceof ToggleGroup) {
			RadioMenuItem rb = (RadioMenuItem)((ToggleGroup)input).getSelectedToggle();
			if(null!= rb) {
				Optional<Entry<String, String[]>> op = definedthemes.entrySet().stream().filter(e->rb.getText().equalsIgnoreCase(e.getValue()[0])).findFirst();
			if(op.isPresent()) {
				loadTheme(scene, op);
				saveThemeConfiguration(CommonConstant.CONF_FOLDER+File.separator+FileNames.APP_CONFIGURATIONS,AppConfigurations.THEME , op.get().getKey());
			}
		}
		}
	}
	
	public void loadTheme() {
		AppConfiguration appConfiguration = GlobalConfig.getInstance().getAppConfiguration();
		String theme = appConfiguration.getConfiguration(AppConfigurations.THEME);
		Optional<Entry<String, String[]>> op = definedthemes.entrySet().stream().filter(e->theme.equalsIgnoreCase(e.getKey())).findFirst();
		if(op.isPresent()) {
			loadTheme(scene, op);
			
		}
	}
	
	private void loadTheme(Scene scene,Optional<Entry<String, String[]>> op)  {
		if(op.isPresent()) {
		scene.getStylesheets().clear();
		
		File themeLocation = new File(op.get().getValue()[1]);
		
		
		
				for(File themeFolder : themeLocation.listFiles((dir, name) -> name.endsWith(".css"))) {
					scene.getStylesheets().add(themeFolder.toURI().toString());	
				}
			

			
			
		
		}
		}
	
	private void validateConf() throws InternalError {
	
		AppConfiguration appConfiguration = GlobalConfig.getInstance().getAppConfiguration();
		String theme = appConfiguration.getConfiguration(AppConfigurations.THEME);
		if(!definedthemes.containsKey(theme)) {
					throw new InternalError("Wrong Theme Configured in Application.properties.");
				}
			}
	
	
	private void saveThemeConfiguration(String filePath,String key, String value) throws ConfigurationException {
		ConfigUtils.modifyConfiguration(filePath, key, value);
	}
		
		
	
	
	
	
}
