/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.plugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.anandhuarjunan.developertools.core.app.UIResources;
import com.anandhuarjunan.developertools.core.app.DeveloperTools.App;
import com.anandhuarjunan.developertools.core.controller.MainController.MenuLoader;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.utils.PluginLoaderUtils;

public class PluginLoaderFactory {
    
    private PluginLoaderFactory(){}
    

	static Logger log = Logger.getLogger(PluginLoaderFactory.class);

    public static  void  loadAllPlugins() throws ServiceException {

	File libPath = new File("plugins/fxml/");
	
	File[] companies = libPath.listFiles();

	
	for(File company : companies) {
		
		if(company.isDirectory()) {
		log.info("Started Loading FXML Plugins from "+company.getName());
		File[] jarFiles = company.listFiles((dir, name) -> name.endsWith(".dtet"));
		for (File file : jarFiles) {
		    
			try {
				PluginLoaderUtils.loadJar(file);
				log.info("Loaded Plugin File : "+file.getName());

			} catch (NoSuchMethodException | MalformedURLException | IllegalAccessException
					| InvocationTargetException e) {
				log.error("Failed to Load Plugin: "+file.getName(),e);
				
			}

		}
		}
	}
	
	
	
	MenuLoader menuLoader = UIResources.getInstance().getMenuLoader();
	menuLoader.loadToolsInMenu();
	
    }


 
    
    
    
    




    

    
    
}
