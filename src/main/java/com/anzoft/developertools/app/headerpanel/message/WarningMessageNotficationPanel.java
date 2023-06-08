/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.headerpanel.message;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Node;

public class WarningMessageNotficationPanel extends MessageNotificationPanelContent{
    public WarningMessageNotficationPanel(int priority) {
 	super(priority);
     }

     @Override
     public Node setIcon() {
   	FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
   	icon.setSize("30");
   	return icon;
       }
     @Override
       public void panelColor() {
   	getBox().setStyle("-fx-background-color: #ffb74d");
       }
    
     
}
