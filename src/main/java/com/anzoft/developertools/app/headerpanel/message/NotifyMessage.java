/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.headerpanel.message;

import com.anzoft.developertools.app.headerpanel.DisplayPanelContent;
import com.anzoft.developertools.app.headerpanel.ShowPriority;

public class NotifyMessage {

    
    private NotifyMessage(){}
    
    public static void notifyInfo(String msg,int priority) {
	  MessageNotificationPanelContent content =  new InfoMessageNotficationPanel(priority);
	        content.load(msg);
	        DisplayPanelContent.display(content);
    }
    
public static void notifyError(String msg,int priority) {
    MessageNotificationPanelContent content =  new ErrorMessageNotificationPanel( priority);
    content.load(msg);
    DisplayPanelContent.display(content);
    }

public static void notifyError(String msg) {
    MessageNotificationPanelContent content =  new ErrorMessageNotificationPanel( ShowPriority.HIGH_PRIORITY);
    content.load(msg);
    DisplayPanelContent.display(content);
    }
public static void notifyError(String msg,int priority,String path) {
    MessageNotificationPanelContent content =  new ErrorMessageNotificationPanel( priority);
    content.load(msg,path);
    DisplayPanelContent.display(content);
    }   


public static void notifyWarning(String msg,int priority) {
    MessageNotificationPanelContent content =  new WarningMessageNotficationPanel(priority);
    content.load(msg);
    DisplayPanelContent.display(content);
}

    
}
