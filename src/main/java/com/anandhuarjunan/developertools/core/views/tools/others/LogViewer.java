/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.others;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import com.anandhuarjunan.developertools.core.app.config.AppConfiguration;
import com.anandhuarjunan.developertools.core.app.config.LoggerConfiguration;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.styles.TextViewerToolBarStyle;
import com.anandhuarjunan.developertools.core.views.AbstractTool;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;

public class LogViewer extends AbstractTool {
	 
	 int textSize=15;
	 
	Label label;
	AppConfiguration appConfig = GlobalConfig.getInstance().getAppConfiguration();
	LoggerConfiguration loggerConfig = GlobalConfig.getInstance().getLoggerConfiguration();
	LogViewerUI logViewerUI = new LogViewerUI();
	File logFile = null; 
	Separator zoomSep = null;
	
	@Override
	public void open() throws ToolError {
	
	logViewerUI.input.setWrapText(true);
	logViewerUI.input.setEditable(false);
	logViewerUI.input.textProperty().addListener((obs, oldText, newText) -> {
		logViewerUI.input.setStyleSpans(0, computeHighlighting(newText));
    });
	logViewerUI.input.setPadding(new Insets(50));
	logViewerUI.input.getStylesheets().add(LogViewer.class.getResource("/css/logviewer.css").toExternalForm());
	logViewerUI.input.setStyle("-fx-font-size: 15");

	
	}
		
		
	

	 private  StyleSpans<Collection<String>> computeHighlighting(String text) {
	 	 StyleSpansBuilder<Collection<String>> spansBuilder= new StyleSpansBuilder<>();

		 Platform.runLater(()->{
			 String expr= loadExpressions();
		        int lastKwEnd = 0;

		 	if(null != expr && !expr.isEmpty()) {
		 	Pattern pattern = Pattern.compile(expr);

	    	Matcher matcher = pattern.matcher(text);
	       
	        while(matcher.find()) {
	            String styleClass ="error";
	            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
	            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
	            lastKwEnd = matcher.end();
	        }
		 	}
	        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		 });
	        return spansBuilder.create();

	    }

	private  String loadExpressions()  {
		
			 
			String words = null;
			try {
				words = FileUtils.readFileToString(new File(File.separator+getGlobalConfig().getAppConfiguration().getConfiguration("data_folder")+File.separator+"logviewer"),StandardCharsets.UTF_8);
				return getPattern(words);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}


	 

	
	  private String getPattern(String words) {
	      if(!StringUtils.isEmpty(words)) {
		    StringBuilder pattern = new StringBuilder();
		       Arrays.asList(words.split(",\\s*"))
		               .stream()
		               .map(t -> "(?=.*\\b" + t + "\\b).*")
		               .forEach(pattern::append);
		       return "(?i)" + pattern.toString();
	      }
	      return null;
	   
	   }




	private void onClickZoomOut() {
		textSize--;
		logViewerUI.input.setStyle("-fx-font-size: "+textSize+"");
	}


	private void onClickZoomIn() {
		textSize++;
		logViewerUI.input.setStyle("-fx-font-size: "+textSize+"");
	}
	
	
	class LogViewerUI extends TextViewerToolBarStyle{

		@Override
		protected void makeToolBarConfig(ToolBar toolBar) {
			Button listenButton = new Button("Browse");
			label= new Label();
			label.setStyle("-fx-font-size: 20");
			
			MaterialIconView iconzmi = new MaterialIconView(MaterialIcon.ZOOM_IN);
			iconzmi.setSize("3em");
			
			MaterialIconView iconzmo = new MaterialIconView(MaterialIcon.ZOOM_OUT);
			iconzmo.setSize("3em");
			zoomSep = new Separator(Orientation.VERTICAL);
			FontAwesomeIconView fontAwesomeIconView = 
					  new FontAwesomeIconView(FontAwesomeIcon.MAGIC);
			
			fontAwesomeIconView.setSize("3em");
			
			toolBar.getItems().add(listenButton);
			toolBar.getItems().add(label);
			toolBar.getItems().add(new Separator(Orientation.VERTICAL));	
			toolBar.getItems().add(iconzmi);iconzmi.setVisible(true);
			toolBar.getItems().add(iconzmo);iconzmo.setVisible(true);
			toolBar.getItems().add(zoomSep);zoomSep.setVisible(true);
			toolBar.getItems().add(fontAwesomeIconView);
			iconzmi.setOnMouseClicked(ev->onClickZoomIn());
			iconzmo.setOnMouseClicked(ev->onClickZoomOut());
			listenButton.setOnAction(this::loadFileSystem);
			
		}
		private void loadFileSystem(ActionEvent actionEvent) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Log File", "*.log"));
			logFile = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());
			LogViewerThread logViewerThread  = new LogViewerThread(actionEvent);
			 ExecutorService executorService
	         = Executors.newFixedThreadPool(1);
	      executorService.execute(logViewerThread);
	      executorService.shutdown();
			    
		}
		
	}
	
	
	
	class LogViewerThread extends Task<Void> {
		ActionEvent event = null;
		LogViewerThread(ActionEvent event){
			this.event=event;
		}

		@Override
		protected Void call() throws Exception {
			Platform.runLater(()->{
				try {
					
					logViewerUI.input.clear();
					logViewerUI.input.replaceText(0, 0, FileUtils.readFileToString(logFile,StandardCharsets.UTF_8));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			});

				label.setText(logFile.getName());
				label.setTooltip(new Tooltip(logFile.getAbsolutePath()));
				System.out.println(logViewerUI.input.getStyle());

			return null;
		}
		
	}



	@Override
	public Node view() throws ToolError {
		return logViewerUI;
	}

}
