/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import java.util.Timer;
import java.util.TimerTask;

import com.anandhuarjunan.developertools.core.constants.CommonConstant;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;

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
