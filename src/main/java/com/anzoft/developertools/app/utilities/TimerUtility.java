/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.utilities;

import com.anzoft.developertools.app.headerpanel.DisplayPanelContent;
import com.anzoft.developertools.app.headerpanel.ShowPriority;
import com.anzoft.developertools.app.headerpanel.message.TimerUtiltyNotification;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.function.Action;
import com.anzoft.developertools.services.background.OneTimeExecutionService;
import com.anzoft.developertools.utils.AudioUtils;

import javafx.util.Duration;

public class TimerUtility extends OneTimeExecutionService{
   private Action action = null;
    public TimerUtility(String name,String description, Duration duration,Action action) {
	super(name, description,duration);
	this.action = action;
    }
    public TimerUtility(String name,String description, Duration duration) {
	super(name, description,duration);
    }



    @Override
    public void execute() throws InternalError {
	super.execute();
	TimerUtiltyNotification notification = new TimerUtiltyNotification(ShowPriority.HIGH_PRIORITY,null !=getName()? "Timer - "+getName():"Timer");
	notification.load("Time is Up ! ");
	AudioUtils.playSound("/sounds/timer.mp3");
	DisplayPanelContent.display(notification);
	if(null != action) {
	    try {
		action.execue();
	    } catch (InternalError e) {
		e.printStackTrace();
	    }
	}
    }

   

    
	
}
    
    
 
