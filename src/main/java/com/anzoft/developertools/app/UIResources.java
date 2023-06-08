/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app;

import java.util.concurrent.ExecutorService;

import com.anzoft.developertools.controller.MainController;
import com.anzoft.developertools.controller.MainController.MenuLoader;
import com.anzoft.developertools.controller.toolloaders.ToolLoaderFactory;


public class UIResources {

 private static UIResources suppliers = null; 
	 
	 private UIResources() {}
	 
	 ToolLoaderFactory tabLoader = null;
	 ExecutorService executorService = null;
	 MainController mainController  = null;
	 MenuLoader menuLoader = null;


		public static synchronized UIResources getInstance() 
	    { 
	        if (suppliers == null) 
	        	suppliers = new UIResources(); 
	  
	        return suppliers; 
	    }



		public ToolLoaderFactory getTabLoader() {
			return tabLoader;
		}



		public MenuLoader getMenuLoader() {
		    return menuLoader;
		}



		public void setMenuLoader(MenuLoader menuLoader) {
		    this.menuLoader = menuLoader;
		}



		public void setTabLoader(ToolLoaderFactory tabLoader) {
			this.tabLoader = tabLoader;
		}



		public ExecutorService getExecutorService() {
			return executorService;
		}



		public void setExecutorService(ExecutorService executorService) {
			this.executorService = executorService;
		}



		public MainController getMainController() {
			return mainController;
		}



		public void setMainController(MainController mainController) {
			this.mainController = mainController;
		}
	
	
		
}
