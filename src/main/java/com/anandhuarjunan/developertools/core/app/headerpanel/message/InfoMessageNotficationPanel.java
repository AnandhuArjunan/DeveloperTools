/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.headerpanel.message;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Node;

public class InfoMessageNotficationPanel extends MessageNotificationPanelContent{
    public InfoMessageNotficationPanel(int priority) {
 	super(priority);
     }

     @Override
     public Node setIcon() {
   	FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
   	icon.setSize("30");
   	return icon;
       }
     @Override
       public void panelColor() {
   	getBox().setStyle("-fx-background-color: #9ccc65");
       }
    
     
}
