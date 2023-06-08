/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.services.background;

import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.function.Action;

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
