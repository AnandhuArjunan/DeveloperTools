/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.headerpanel.message;

import com.anandhuarjunan.developertools.core.app.headerpanel.DisplayPanelContent;
import com.anandhuarjunan.developertools.core.app.headerpanel.ShowPriority;

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
