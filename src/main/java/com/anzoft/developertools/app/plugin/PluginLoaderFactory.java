package com.anzoft.developertools.app.plugin;

import java.io.File;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;

import com.anzoft.developertools.app.UIResources;
import com.anzoft.developertools.controller.MainController.MenuLoader;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ServiceException;

public class PluginLoaderFactory {
    
    private PluginLoaderFactory(){}
    


    public static  void  loadAllPlugins() throws ServiceException {

	File libPath = new File("plugins/");
	File[] jarFiles = libPath.listFiles((dir, name) -> name.endsWith(".dtpl") || name.endsWith(".dt"));
	for (File file : jarFiles) {
	    if (null != file) {
		String fileExtn = FilenameUtils.getExtension(file.getName());
		switch (fileExtn) {
		case "dtpl":
		    PluginLoader pluginLoader = new FxmlPluginLoader();
		    try {
			Properties value = pluginLoader.init(file);
		    } catch (InternalError e) {
			e.printStackTrace();
		    }
		    break;
		case "dt":
		    PluginLoader internaLpluginLoader = new InternalPluginLoader();
		    try {
			Properties value = internaLpluginLoader.init(file);
		    } catch (InternalError e) {
			e.printStackTrace();
		    }
		    break;
		default:
		    break;
		}
	    }

	}
	
	MenuLoader menuLoader = UIResources.getInstance().getMenuLoader();
	menuLoader.loadToolsInMenu();
	
    }


 
    
    
    
    




    

    
    
}
