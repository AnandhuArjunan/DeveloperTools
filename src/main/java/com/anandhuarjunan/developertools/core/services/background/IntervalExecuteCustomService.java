/*
 * 
 */
package com.anandhuarjunan.developertools.core.services.background;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.function.Action;

import javafx.util.Duration;

public final class IntervalExecuteCustomService extends IntervalExecuteService {


   private Action action = null;
   private String description = null;
   
   
    public IntervalExecuteCustomService(Duration timeInterval,Duration startInterval,String name ,String description,Action action) {
	super(timeInterval,startInterval, name,description);
	this.action = action;
	this.description = description;
    }

  

    @Override
    public void execute() throws InternalError {
	super.execute();
	action.execue();
    }


    
    
}
