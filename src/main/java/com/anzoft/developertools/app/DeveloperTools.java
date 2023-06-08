/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.anzoft.developertools.app.config.AppConfiguration;

import com.anzoft.developertools.app.config.FXIDClassMapping;
import com.anzoft.developertools.app.config.LoggerConfiguration;
import com.anzoft.developertools.app.config.Themes;
import com.anzoft.developertools.app.config.global.GlobalConfig;
import com.anzoft.developertools.controller.LoaderUIController;
import com.anzoft.developertools.controller.MainController;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ValidateError;
import com.anzoft.developertools.utils.ConcurrencyUtils;
import com.anzoft.developertools.utils.FxComponentUtils;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Hello world!
 *
 */
public class DeveloperTools extends Application {

	private static final String LOADER_UI_FXML = "/fxml/Loader.fxml";
	private static final String MAIN_FXML = "/fxml/Main.fxml";
	   static Logger log = Logger.getLogger(DeveloperTools.class);  

	@Override
	public void start(Stage stage) throws Exception {
	    log.debug("Hello this is a debug message");  
	      log.info("Hello this is an info message");  
	      log.error("gffgdd");
		LoaderUIController loaderController = loadStartWindow(stage);
		if (null != loaderController) {
			Task<Scene> loaderTask = loaderTask();
			loaderController.onCloseAction(() -> {
				System.exit(0);
			});
			loaderTask.setOnSucceeded(ev -> {
				loadMainWindow(new Stage(), loaderTask.getValue());
				stage.close();
			});
			loaderTask.setOnFailed(ev -> loaderController.showError(loaderTask.getException().getMessage()));

			ConcurrencyUtils.fxTaskExecuter(loaderTask);
		}

	}

	private Task<Scene> loaderTask() {

		return new Task<Scene>() {

			@Override
			protected Scene call() throws Exception {
				loadConfigurations();
				loadTheme();
				FXMLLoader fxmlLoader = FxComponentUtils.getFXMLLoader(MAIN_FXML);
				Scene scene = FxComponentUtils.initializeFXController(fxmlLoader);
				scene.setUserData(FxComponentUtils.getFXController(fxmlLoader));
				return scene;
			}

		};

	}

	private LoaderUIController loadStartWindow(Stage stage) {
		try {
			FXMLLoader fxmlLoader = FxComponentUtils.getFXMLLoader(LOADER_UI_FXML);
			stage.setScene(FxComponentUtils.initializeFXController(fxmlLoader));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/3d.png")));
			stage.show();
			return FxComponentUtils.getFXController(fxmlLoader);
		} catch (IOException e) {
			return null;
		}

	}

	private void loadMainWindow(Stage stage, Scene mainScene) {
		stage.setTitle("Developer Tools");
		stage.setMaximized(true);
		stage.setScene(mainScene);
		((MainController)mainScene.getUserData()).setStage(stage);
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/3d.png")));
		stage.setOnHiding(ev -> ((MainController) mainScene.getUserData()).onCloseApplication());
		stage.show();
	}

	private void loadConfigurations() throws ValidateError, InternalError {
		GlobalConfig.getInstance().setAppConfiguration(new AppConfiguration());
		GlobalConfig.getInstance().setLoggerConfiguration(new LoggerConfiguration());
		GlobalConfig.getInstance().setFxIDMapping(new FXIDClassMapping());
		


}

	private void loadTheme() throws InternalError {
		Themes.getInstance().loadTheme();

	}
}
