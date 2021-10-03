/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.utils.ConcurrencyUtils;

public abstract class AlwaysListenService extends AbstractBackgroundService {

    private   ExecutorService executorService = null;
    private volatile boolean cancelled;

    
    public AlwaysListenService(String name,String description) {
	super(name,description);
    }

    @Override
    public ServiceTypes serviceType() {
	return ServiceTypes.ALWAYS_LISTEN;
    }

    @Override
    public synchronized void start() throws InternalError {
	super.start();
	cancelled = false;
	Runnable runnable = ()->{
	    
	    while(!isCancelled()) {
		try {
		    execute();
		} catch (Exception e) {		     
		    e.printStackTrace();
		}
	    }
	};
	executorService = Executors.newSingleThreadExecutor(ConcurrencyUtils.giveNameToTheThread("ALWAYS_LISTEN_SERVICE_THREAD - "+getName()));

	executorService.execute(runnable);
	
    }

    @Override
    public synchronized void stop() throws InternalError {

	cancel();
	executorService.shutdownNow();
	super.stop();
	
    }

    public void cancel()
 {
	      cancelled = true;  
	   }

	   public boolean isCancelled() {
	      return cancelled;
	   }
    
    
}
