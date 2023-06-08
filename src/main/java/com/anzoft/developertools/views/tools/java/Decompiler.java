/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.java;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.anzoft.app.HtmlGenerator;
import com.anzoft.developertools.app.config.AppConfiguration;
import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.app.messages.Labels;
import com.anzoft.developertools.constants.AppConfigurations;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.ui.components.ProgressPane;
import com.anzoft.developertools.ui.styles.TreeListWebViewStyle;
import com.anzoft.developertools.utils.FxComponentUtils;
import com.anzoft.developertools.utils.JarUtils;
import com.anzoft.developertools.views.AbstractTool;
import com.anzoft.model.style.Style;
import com.jfoenix.controls.JFXButton;
import com.strobel.decompiler.PlainTextOutput;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.stage.FileChooser;

public class Decompiler extends AbstractTool {

	File currentFile = null;
	private ProgressPane pgPane= null;
	TreeListWebViewStyle webViewStyle = new TreeListWebViewStyle();
	
	AppConfiguration appConfig = GlobalConfig.getInstance().getAppConfiguration();



	public void process() throws InternalError {

		PlainTextOutput t = new PlainTextOutput();
		//com.strobel.decompiler.Decompiler
				//.decompile(appConfig.getConfiguration(AppConfigurations.TEMP_FOLDER) + File.separator + CommonConstant.JavaDecompiler.TEMP_PATH +File.separator+currentFile.getPath(), t);
		HtmlGenerator generator = new HtmlGenerator(Style.DEFAULT_STYLE, t.toString());
		generator.convert();
		webViewStyle.webView.getEngine().loadContent("<pre style=\"font-size:15px;\">" + generator.getHtml() + "</pre>");

	}

	private void loadToolBarComponents(){
		JFXButton addProjectButton = new JFXButton(Labels.DecompilerTool.ADD_JAR);

		addProjectButton.setFocusTraversable(false);

		addProjectButton.setOnAction(e -> {
			
			
			
			
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Java Archive", "*.jar"));
			File inputJar = fileChooser.showOpenDialog(((Node) e.getSource()).getScene().getWindow());
			if (null != inputJar && inputJar.exists()) {
				pgPane = new ProgressPane();
				webViewStyle.addComponentToToolBar(pgPane, true);
				pgPane.setProgressLabel(Labels.LOADING+ " "+inputJar.getName());
				ExecutorService service = Executors.newSingleThreadExecutor();
				try {
					service.submit(new JarLoaderThread(inputJar));
				} catch (InternalError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				service.shutdown();
			}

		});

		webViewStyle.treeView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue)-> {
				currentFile = new File(FxComponentUtils.getPathOfTreeItem(webViewStyle.treeView, File.separator));
				try {
					process();
				} catch (InternalError e) {
					e.printStackTrace();
				}
			}

		);

		webViewStyle.addComponentToToolBar(addProjectButton, true);
		
		
		
		
		
		
	}

	
	
	class JarLoaderThread implements Runnable {
		File tempLocation = null;
		File inputJar = null;

		public JarLoaderThread(File inputJar) throws InternalError {
			this.inputJar = inputJar;
			tempLocation = new File(appConfig.getConfiguration(AppConfigurations.TEMP_FOLDER) + File.separator + getToolID());

		}

		public void loadFileTree(File selectedFile) throws IOException {
			JarUtils.decompress(selectedFile, new File(tempLocation + File.separator + selectedFile.getName()));
			Platform.runLater(()->
				FxComponentUtils.addFilesToTheTreeView(webViewStyle.treeView,new File(tempLocation + File.separator + selectedFile.getName())));
			

		}

		@Override
		public void run() {
			try {
				loadFileTree(inputJar);
			} catch (IOException e) {
				//
			}

		}

	}




	@Override
	public void open() throws ToolError {
		try {
			com.anzoft.developertools.utils.FileUtils.makeTemporaryFolder(getToolID());
		} catch (InternalError e) {
			throw new ToolError("Cannot make temporary folder ");
		}
		loadToolBarComponents();
		
	}

	@Override
	public Node view() throws ToolError {
		return webViewStyle;
	}
}
