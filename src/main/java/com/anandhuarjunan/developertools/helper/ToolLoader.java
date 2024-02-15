package com.anandhuarjunan.developertools.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.anandhuarjunan.developertools.annotations.FXMLMapping;
import com.anandhuarjunan.developertools.annotations.Node;
import com.anandhuarjunan.developertools.annotations.Tool;
import com.anandhuarjunan.developertools.database.entity.Tools;
import com.anandhuarjunan.jfxawesome.exception.JFXException;
import com.anandhuarjunan.jfxawesome.injection.FXMLView;
import com.anandhuarjunan.jfxawesome.utils.AnnotationUtils;
import com.anandhuarjunan.jfxawesome.utils.FXMLUtils;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ToolLoader {
	
	private TabPane pane;
	
	public ToolLoader(TabPane pane) {
		this.pane = pane;
	}


	public void loadTool(Tools tool) {
		try {
			Class<?> clazz = Class.forName(tool.getToolImplementation());
			
			if(FXMLView.class.isAssignableFrom(clazz)) {
				Tab tab = new Tab(tool.getToolName());
				tab.setContent(((FXMLView)clazz.getConstructor().newInstance()).getView());
				pane.getTabs().add(tab);
			}else if(Initializable.class.isAssignableFrom(clazz)) {
				if(clazz.isAnnotationPresent(FXMLMapping.class)) {
					 FXMLMapping fxmlMapping = clazz.getAnnotation(FXMLMapping.class);
					 if(fxmlMapping.fxmlLocation() != null) {
						 Tab tab = new Tab(tool.getToolName());
							tab.setContent(FXMLUtils.loadFXML(clazz,fxmlMapping.fxmlLocation() ));
							pane.getTabs().add(tab);
						 
					 }
				}
			}else {
				
				Object instance = 	clazz.getConstructor().newInstance(); 
				Method[] methods = clazz.getDeclaredMethods();
				if(methods == null || methods.length == 0) {
					throw new JFXException("No methods found in "+clazz.getName());
				}
				// can assume that it is just node returning impl
				for(Method m :methods ) {
					if(m.isAnnotationPresent(Node.class)) {
						
						Tab tab = new Tab(tool.getToolName());
						tab.setContent((javafx.scene.Node)AnnotationUtils.invokeAnnotedMethodAndGetReturn(Node.class, clazz, instance));
						pane.getTabs().add(tab);
						
					}
					
				}
			}
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | JFXException e) {
			Tab tab =  new Tab(tool.getToolName() +" of "+tool.getToolPlgin().getFileName());
			tab.setContent(new Label(e.getMessage()));
			pane.getTabs().add(tab);
		}
		
	}
	
}
