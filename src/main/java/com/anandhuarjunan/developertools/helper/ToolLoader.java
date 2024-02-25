package com.anandhuarjunan.developertools.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kordamp.ikonli.javafx.FontIcon;

import com.anandhuarjunan.developertools.annotations.FXMLMapping;
import com.anandhuarjunan.developertools.annotations.Node;
import com.anandhuarjunan.developertools.database.entity.Tools;
import com.anandhuarjunan.jfxawesome.exception.JFXException;
import com.anandhuarjunan.jfxawesome.injection.FXMLView;
import com.anandhuarjunan.jfxawesome.utils.AnnotationUtils;
import com.anandhuarjunan.jfxawesome.utils.FXMLUtils;

import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
						addTab(tool,FXMLUtils.loadFXML(clazz,fxmlMapping.fxmlLocation()));
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
						addTab(tool,(javafx.scene.Node)AnnotationUtils.invokeAnnotedMethodAndGetReturn(Node.class, clazz, instance));
					}
					
				}
			}
			
		} catch (Exception e) {
			Tab tab =  new Tab(tool.getToolName() +" of "+tool.getToolPlgin().getFileName());
			tab.setContent(new Label(e.getMessage()));
			pane.getTabs().add(tab);
		}
		
	}
	
	private void addTab(Tools tools, javafx.scene.Node content) {
		
		if(pane.getTabs().stream().noneMatch(t-> null!=t.getUserData() && ((Tools)t.getUserData()).equals(tools))) {
			addTab(tools.getToolName(),content,tools.getIsclosable(),tools);
		}else {
			Optional<Tab> tab = pane.getTabs().stream().filter(t->null!=t.getUserData() &&((Tools)t.getUserData()).equals(tools)).findFirst();
			if(tab.isPresent()) {
				pane.getSelectionModel().select(tab.get());
			}
		}
		
	}
	
	public  void addTab(String name,javafx.scene.Node content,boolean isclosable,Object userData) {
			Tab tab = new Tab(name);
			AnchorPane anchorPane = new AnchorPane(content);
			BorderPane borderPane = new BorderPane(anchorPane);
			BorderPane.setMargin(anchorPane, new Insets(20, 0, 0, 0));
			AnchorPane.setBottomAnchor(content, 0d);
			AnchorPane.setTopAnchor(content, 0d);

			AnchorPane.setLeftAnchor(content, 0d);
			AnchorPane.setRightAnchor(content, 0d);
			content.getStyleClass().add("border-rad2");
			tab.setContent(borderPane);
			tab.setClosable(isclosable);
			tab.setGraphic(new FontIcon("gmi-apps") {{setIconSize(20);}});
			tab.setUserData(userData);
			pane.getTabs().add(tab);
	}
	
}
