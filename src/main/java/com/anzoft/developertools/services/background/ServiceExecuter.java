/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.services.background;

import java.util.ArrayList;
import java.util.List;

import com.anzoft.developertools.exceptions.InternalError;

public class ServiceExecuter {
    
    private ServiceExecuter(){}

    static List<BackgroundService> runningServices = new  ArrayList<>();
    
    
    
    public static void startService(BackgroundService backgroundService) throws InternalError {
	runningServices.add(backgroundService);
	backgroundService.start();
    }
    
    public static void stopService(BackgroundService backgroundService) throws InternalError {
   	backgroundService.stop();
       }
    
    public static void stopAllServices() throws InternalError {
	if(null != runningServices && !runningServices.isEmpty()) {
	    for(BackgroundService backgroundService : runningServices) {
		stopService(backgroundService);
	    }
	}
    }

    public static List<BackgroundService> getRunningServices() {
        return runningServices;
    }
       
    
}
