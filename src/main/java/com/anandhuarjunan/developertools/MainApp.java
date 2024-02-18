package com.anandhuarjunan.developertools;

import org.kordamp.ikonli.material.Material;
import org.hibernate.Session;

import com.anandhuarjunan.developertools.plugins.loader.PluginsLoader;
import com.anandhuarjunan.developertools.utils.HibernateUtils;
import com.anandhuarjunan.developertools.views.MainView;
import com.anandhuarjunan.jfxawesome.AwesomeApplication;
import com.anandhuarjunan.jfxawesome.Tweaks;
import com.anandhuarjunan.jfxawesome.annotations.InitApplication;
import com.anandhuarjunan.jfxawesome.annotations.MaterialWithHeadingThemeForFxmlView;


@MaterialWithHeadingThemeForFxmlView
(appIcon = "", headingData = { "Developer Tools" ,"Simple Tools for Productivity" }, headingIcon = Material.CASINO, tweak = { Tweaks.STRETCH_TO_PARENT,Tweaks.ADD_THEME_CHANGER }, view = MainView.class)
public class MainApp  {
	
	@InitApplication
	public void init() {
		PluginsLoader loader = new PluginsLoader();
		loader.scanAndLoad();
	}
	
	public static void main(String[] args) {
		AwesomeApplication.run(MainApp.class,args);
	}

	}
	


  

