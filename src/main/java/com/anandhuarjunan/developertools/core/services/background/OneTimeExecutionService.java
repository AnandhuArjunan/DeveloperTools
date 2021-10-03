/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import java.util.Timer;
import java.util.TimerTask;

import com.anandhuarjunan.developertools.core.constants.CommonConstant;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;

import javafx.util.Duration;

public abstract class OneTimeExecutionService extends AbstractBackgroundService {
    private  Timer timer = null; 
    private Duration delay = null;

    public OneTimeExecutionService(String name,String description) {
	super(name,description);

    }
    
    public OneTimeExecutionService(String name,String description,Duration duration) {
	this(name,description);
	this.delay = duration;
    }

    @Override
    public ServiceTypes serviceType() {	
	return ServiceTypes.ONE_TIME;
    }

    @Override
    public synchronized void start() throws InternalError {
	super.start();
	timer = new Timer(CommonConstant.THREAD_IDENTIFIER+"ONE_TIME_SERVICE_THREAD - "+getName());
	TimerTask timerTask = new TimerTask() {
	    
	    @Override
	    public void run() {
		  try {
			    execute();
			    stop();
			} catch (InternalError e) {
			    e.printStackTrace();
	    }
	};

	};
	timer.schedule(timerTask, null != delay ?(long) delay.toMillis():0);
    }

    @Override
    public synchronized void stop() throws InternalError {
	timer.cancel();
	super.stop();

    }

}
