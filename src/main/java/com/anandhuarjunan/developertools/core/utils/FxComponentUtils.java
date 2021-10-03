
package com.anandhuarjunan.developertools.core.utils;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.imageio.ImageIO;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * It provides the utility methods for JavaFx UI Components.
 * 
 * @author Anandhu Arjunan
 */
public class FxComponentUtils {

	public static void addFilesToTheTreeView(TreeView<String> treeView, File path) {
		TreeItem<String> item = new TreeItem<>();
		addTreeItem(item, path);
		treeView.setRoot(item);
	}

	private static void addTreeItem(TreeItem<String> item, File f) {
		List<TreeItem<String>> dirList = new ArrayList<>();
		List<TreeItem<String>> fileList = new ArrayList<>();
		item.setValue(f.getName());
		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				TreeItem<String> it = new TreeItem<>();
				addTreeItem(it, file);
				dirList.add(it);
			} else {
				TreeItem<String> it = new TreeItem<>(file.getName());
				fileList.add(it);
			}
		}
			item.getChildren().addAll(dirList);
			item.getChildren().addAll(fileList);	


	}
	
	/**
	 * Gets the path of the treeItem.
	 * @param treeView
	 * @param separator
	 * @return
	 */
	public static String getPathOfTreeItem(TreeView<String> treeView,String separator) {
		StringBuilder pathBuilder = new StringBuilder();
		for (TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
		    item != null ; item = item.getParent()) {

		    pathBuilder.insert(0, item.getValue());
		    pathBuilder.insert(0, separator);
		}
		return pathBuilder.toString();
		
	}
	
	public static void addMenuItemToTheMenu(Menu menu,MenuItem...items) {
		menu.getItems().addAll(items);
	}
	
	 public void fillListsViews(ListView<Object> list,Object...objects) {
	    	for(Object object : objects) {
	    		list.getItems().add(object);
	    	}
	    
	 }
	 
	 
	 public static void makeWebViewEditable(WebView view,boolean toEditable) {
		 if(toEditable) {
			 view.getEngine().loadContent("<html><head>"
		                + "</head><body onLoad='document.body.focus();' contenteditable='true'>"
		                +"</body></html>");
		 }
	 }
	 
	 
	 
	 
	 public static Timeline setBackgroundUITask(double duration,EventHandler<ActionEvent> eventHandler) {
		 Timeline timer = new Timeline(new KeyFrame(Duration.seconds(duration),eventHandler));
    		timer.setCycleCount(Animation.INDEFINITE);
    		timer.play();
    		return timer;
	 }
	 
	
		
		public static void loadTab(TabPane tabPane,Node content,String id,String text,EventHandler<Event> onclosed) {
			Tab tab = new Tab();
	    	tab.setId(id);
			tab.setText(text);
			tab.setContent(content);
			tab.setOnClosed(onclosed);
			tab.setClosable(true);
			tabPane.getTabs().add(tab);
				tabPane.getSelectionModel().select(tab);
			
		}
		
		
		public static void loadTab(TabPane tabPane,Node content,String text) {
			Tab tab = new Tab();
			tab.setText(text);
			tab.setContent(content);
			tab.setClosable(true);
			tabPane.getTabs().add(tab);			
		}
		
		
		public static void loadTab(TabPane tabPane,String toolName,String uid,AnchorPane content,EventHandler<Event> onclosed,boolean setFocus,boolean closable) {
			Tab tab = new Tab();
	    	tab.setId(uid);
			tab.setText(toolName);
			tab.setContent(content);
			tab.setOnClosed(onclosed);
			tab.setClosable(closable);
			tabPane.getTabs().add(tab);
			if(setFocus) {
				tabPane.getSelectionModel().select(tab);
			}
		}
		
		
		public static <T> T showAnotherView(String viewLocation, String title) {
	        try {
	            FXMLLoader loader = new FXMLLoader(FxComponentUtils.class.getResource(viewLocation));
	            Parent root = loader.load();
	            T controller = loader.getController();
	            Stage stage = new Stage();
	            stage.setResizable(false);
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.setTitle(title);
	            stage.setAlwaysOnTop(true);
	            stage.show();
	            return controller;
	        } catch (IOException e) {
	            return null;
	        }
		}
		
		
		
		
		public static Tab getLoadedTab(TabPane tabPane,String id) {
				Optional<Tab> loadedTab =	tabPane.getTabs().stream().filter(e->e.getId().equalsIgnoreCase(id)).findFirst();
				return loadedTab.orElse(null);	
		}
		
		public static FXMLLoader getFXMLLoader(String fxmlFile) {
			return  new FXMLLoader(FxComponentUtils.class.getResource(fxmlFile));
		}
		
		public static Scene initializeFXController(FXMLLoader fxmlLoader) throws IOException {
			return new Scene(fxmlLoader.load());
		}
		
		public static Node initializeFXML(FXMLLoader fxmlLoader) throws IOException {
			return fxmlLoader.load();
		}
		
		public static <T> T getFXController(FXMLLoader fxmlLoader) {
			return fxmlLoader.getController();
		}
		
		public static Node loadFXML(String fxmlPath) throws IOException {
			FXMLLoader fxmlLoader = FxComponentUtils.getFXMLLoader(fxmlPath);
			return FxComponentUtils.initializeFXML(fxmlLoader);
		}
		
		
		public static void takeScreenShot(Scene scene,String path) throws IOException {
			   WritableImage snapshoth =scene.snapshot(null);
	           RenderedImage renderedImage = SwingFXUtils.fromFXImage(snapshoth, null);
	            ImageIO.write(renderedImage, "png", new File(path));
		}
		
		public static void expandTreeView(TreeItem<?> item){
		    if(item != null && !item.isLeaf()){
		        item.setExpanded(true);
		        for(TreeItem<?> child:item.getChildren()){
		            expandTreeView(child);
		        }
		    }
		}
		
		public void collapseTreeView(TreeItem<?> item){
		    if(item != null && !item.isLeaf()){
		        item.setExpanded(false);
		        for(TreeItem<?> child:item.getChildren()){
		            collapseTreeView(child);
		        }
		    }
		}
		
		public static TreeItem<String> searchTreeItem(TreeItem<String> item, String name) {
			
	         
		    if(null != item){
		    	if(item.getValue().toLowerCase(Locale.ENGLISH).contains(name.toLowerCase(Locale.ENGLISH))) { return item;} // hit!
		        // continue on the children:
			    TreeItem<String> result = null;
			    for(TreeItem<String> child : item.getChildren()){
			         result = searchTreeItem(child, name);
			         if(result != null) return result; // hit!
			    }
		    }
		 
		
		     
		    //no hit:
		    return null;
		}
	
		public static ImageView loadImage(String location) {
		    InputStream input = FxComponentUtils.class.getClassLoader().getResourceAsStream(location);
		     Image image = new Image(input);
		    return new ImageView(image);
		}
		
		
		
		
		
		public static FileChooser getFileChooser(String fileTypeName,String extension) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(fileTypeName, extension));
			return fileChooser;

		}
		
		public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		    for (Node node : gridPane.getChildren()) {
		        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
		            return node;
		        }
		    }
		    return null;
		}

}
