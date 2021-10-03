package com.anandhuarjunan.developertools.core.utils;


import java.text.DecimalFormat;

import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anandhuarjunan.developertools.core.views.ClosableTool;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class DeveloperToolsUtils {
	public static void loadTab(TabPane tabPane,AbstractTool abstractTool,AnchorPane content,EventHandler<Event> onclosed,boolean setFocus,boolean closable) {
		Tab tab = new Tab();
    	tab.setId(abstractTool.getUniqueId());
		tab.setText(abstractTool.getToolName());
		tab.setUserData(abstractTool);
		tab.setContent(content);
		tab.setOnClosed(onclosed);
		tab.setClosable(closable);
		tabPane.getTabs().add(tab);
		if(setFocus) {
			tabPane.getSelectionModel().select(tab);
		}
	}
	
	public static String getFormattedMemoryValue() {
		long freemem = 0;
		freemem = Runtime.getRuntime().freeMemory();
		long totalmem = 0;
		totalmem = Runtime.getRuntime().totalMemory();
		double usedmem = (totalmem - freemem);
		double currentmem = (usedmem / totalmem) * 100;
		DecimalFormat decformat = new DecimalFormat("#0.00");
		return decformat.format(currentmem);

	}
	
	
	
	public static EventHandler<Event> closeEvent(Object object){
		return ev->{

			if(object instanceof ClosableTool closable && !closable.performClose()) {
					ev.consume();
			}
			
		};
	}
}
