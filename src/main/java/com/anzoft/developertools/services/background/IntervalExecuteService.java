/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.services.background;

import java.util.Timer;
import java.util.TimerTask;

import com.anzoft.developertools.constants.CommonConstant;
import com.anzoft.developertools.exceptions.InternalError;

import javafx.util.Duration;

public abstract class IntervalExecuteService extends AbstractBackgroundService {
    private Timer timer  = null;
    private Duration duration = null;
    private Duration startInterval = null;

   public IntervalExecuteService(Duration timeInterval,Duration startInterval,String name,String description) {
	super(name,description);
	duration = timeInterval ;
	this.startInterval = startInterval;
	}
    
    @Override
    public synchronized void start() throws InternalError {
	super.start();
	timer = new Timer(CommonConstant.THREAD_IDENTIFIER+"INTERVAL_EXECUTE_SERVICE THREAD- "+getName());
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		try {
		    execute();
		} catch (InternalError e) {
		    e.printStackTrace();
		}
	    }
	}, (long)startInterval.toMillis(), (long) duration.toMillis());
	
    }
 
    @Override
    public synchronized void stop() throws InternalError {
	timer.cancel();
	super.stop();
	
    }  


    @Override
    public ServiceTypes serviceType() {
	return ServiceTypes.PERIODIC;
    }

}
