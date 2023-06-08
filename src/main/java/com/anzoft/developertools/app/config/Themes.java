/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.configuration2.ex.ConfigurationException;

import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.app.messages.Labels;
import com.anzoft.developertools.constants.AppConfigurations;
import com.anzoft.developertools.constants.CommonConstant;
import com.anzoft.developertools.constants.FileNames;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.utils.ConfigUtils;
import com.anzoft.developertools.utils.FxComponentUtils;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class Themes  {
	
	private Map<String,String[]> definedthemes = new HashMap<>();

	private Themes(){
		
	}
	
	
	private static Themes themes = null;

	
	public static synchronized Themes getInstance() throws  InternalError {
		if(null == themes) {
			themes = new Themes();
			themes.loadDefinedThemes();
			themes.validateConf();
		}
		return themes;
	}
	
	
	public void loadDefinedThemes(){
		definedthemes.put("MODENA", new String[] {Labels.Theme.MODENA,Application.STYLESHEET_MODENA});
		definedthemes.put("CASPIAN", new String[] {Labels.Theme.CASPIAN,Application.STYLESHEET_CASPIAN});
		definedthemes.put("ANZOFT_LIGHT", new String[] {Labels.Theme.ANZOFT_LIGHT,Themes.class.getResource("/styles/AnzoftLight.css").toExternalForm()});
		definedthemes.put("ANZOFT_DARK", new String[] {Labels.Theme.ANZOFT_BLACK,Themes.class.getResource("/styles/AnzoftDark.css").toExternalForm()});

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
			}
		};
		tg.selectedToggleProperty().addListener(listener);
		 
		 
		 
	}
	
	private void loadTheme(Object input) throws   ConfigurationException{
		if(input instanceof ToggleGroup) {
			RadioMenuItem rb = (RadioMenuItem)((ToggleGroup)input).getSelectedToggle();
			if(null!= rb) {
				Optional<Entry<String, String[]>> op = definedthemes.entrySet().stream().filter(e->rb.getText().equalsIgnoreCase(e.getValue()[0])).findFirst();
			if(op.isPresent()) {
				Application.setUserAgentStylesheet(op.get().getValue()[1]);
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
			Application.setUserAgentStylesheet(op.get().getValue()[1]);
		}
	}
	
	private void validateConf() throws InternalError {
	
		AppConfiguration appConfiguration = GlobalConfig.getInstance().getAppConfiguration();
		String theme = appConfiguration.getConfiguration(AppConfigurations.THEME);
		if(!definedthemes.containsKey(theme)) {
					throw new InternalError(AppConfigurations.THEME+" - Wrong Configuration in Application.properties.");
				}
			}
	
	
	private void saveThemeConfiguration(String filePath,String key, String value) throws ConfigurationException {
		ConfigUtils.modifyConfiguration(filePath, key, value);
	}
		
		
	
	
	
	
}
