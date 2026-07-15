/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import java.util.ArrayList;
import java.util.List;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

public class ServiceExecuter {
    
    private ServiceExecuter(){}

    private static List<BackgroundService> runningServices = new CopyOnWriteArrayList<>();
    
    
    
    public static void startService(BackgroundService backgroundService) throws InternalError {
	runningServices.add(backgroundService);
	backgroundService.start();
    }
    
    public static void stopService(BackgroundService backgroundService) throws InternalError {
   	backgroundService.stop();
       }
    
    public static void stopAllServices() throws InternalError {
	if(!runningServices.isEmpty()) {
	    for(BackgroundService backgroundService : runningServices) {
		stopService(backgroundService);
	    }
	}
    }

    public static List<BackgroundService> getRunningServices() {
        return Collections.unmodifiableList(runningServices);
    }
       
    
}
