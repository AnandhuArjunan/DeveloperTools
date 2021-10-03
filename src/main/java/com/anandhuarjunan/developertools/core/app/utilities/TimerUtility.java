/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.utilities;

import com.anandhuarjunan.developertools.core.app.headerpanel.DisplayPanelContent;
import com.anandhuarjunan.developertools.core.app.headerpanel.ShowPriority;
import com.anandhuarjunan.developertools.core.app.headerpanel.message.TimerUtiltyNotification;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.function.Action;
import com.anandhuarjunan.developertools.core.services.background.OneTimeExecutionService;
import com.anandhuarjunan.developertools.core.utils.AudioUtils;

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
    
    
 
