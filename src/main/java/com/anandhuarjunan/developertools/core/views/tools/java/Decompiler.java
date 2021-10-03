/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.java;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.anandhuarjunan.developertools.core.app.config.AppConfiguration;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.app.messages.Labels;
import com.anandhuarjunan.developertools.core.constants.AppConfigurations;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.ui.components.ProgressPane;
import com.anandhuarjunan.developertools.core.ui.styles.TreeListWebViewStyle;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;
import com.anandhuarjunan.developertools.core.utils.JarUtils;
import com.anandhuarjunan.developertools.core.views.AbstractTool;
import com.anzoft.app.HtmlGenerator;
import com.anzoft.model.style.Style;
import com.strobel.decompiler.PlainTextOutput;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
		Button addProjectButton = new Button(Labels.DecompilerTool.ADD_JAR);

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
			com.anandhuarjunan.developertools.core.utils.FileUtils.makeTemporaryFolder(getToolID());
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
