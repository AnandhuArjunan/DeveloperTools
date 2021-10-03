/*
 * 
 */
package com.anandhuarjunan.developertools.core.app;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.anandhuarjunan.developertools.core.app.config.AppConfiguration;
import com.anandhuarjunan.developertools.core.app.config.FXIDClassMapping;
import com.anandhuarjunan.developertools.core.app.config.LoggerConfiguration;
import com.anandhuarjunan.developertools.core.app.config.global.GlobalConfig;
import com.anandhuarjunan.developertools.core.controller.LoaderUIController;
import com.anandhuarjunan.developertools.core.controller.MainController;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;
import com.anandhuarjunan.developertools.core.utils.ConcurrencyUtils;
import com.anandhuarjunan.developertools.core.utils.FxComponentUtils;

import javafx.application.Application;
import javafx.application.Platform;
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
public class DeveloperTools {

	public static void main(String[] args) {
		App.main(args);
	}

	public static class App extends Application {
		private static final String LOADER_UI_FXML = "/fxml/Loader.fxml";
		private static final String MAIN_FXML = "/fxml/Main.fxml";
		static Logger log = Logger.getLogger(App.class);

		@Override
		public void start(Stage stage) throws Exception {
			Platform.setImplicitExit(false);
			
			log.error("afaf");

			LoaderUIController loaderController = loadStartWindow(stage);
			if (null != loaderController) {
				Task<Scene> loaderTask = loadMainUI();
				loaderController.onCloseAction(() -> 
					System.exit(0)
				);
				loaderTask.setOnSucceeded(ev -> {
					loadMainWindow(new Stage(), loaderTask.getValue());
					stage.close();
				});
				 loaderTask.setOnFailed(ev ->
				loaderController.showError(loaderTask.getException().getMessage()));

				ConcurrencyUtils.fxTaskExecuter(loaderTask);
			}

		}

		private Task<Scene> loadMainUI() {

			return new Task<Scene>() {

				@Override
				protected Scene call() throws Exception {
					loadConfigurations();
					FXMLLoader fxmlLoader = FxComponentUtils.getFXMLLoader(MAIN_FXML);
					Scene scene = FxComponentUtils.initializeFXController(fxmlLoader);
					MainController dtController = FxComponentUtils.getFXController(fxmlLoader);
					dtController.setScene(scene);
					scene.setUserData(dtController);
					dtController.loadThemes();
					return scene;
				}

			};

		}

		private LoaderUIController loadStartWindow(Stage stage) {
			try {
				FXMLLoader fxmlLoader = FxComponentUtils.getFXMLLoader(LOADER_UI_FXML);
				Scene scene = FxComponentUtils.initializeFXController(fxmlLoader);
				scene.getStylesheets().add("/css/loaderUI/loader.css");
				stage.setScene(scene);
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
			((MainController) mainScene.getUserData()).setStage(stage);
			stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/3d.png")));
			stage.setOnCloseRequest(ev -> ((MainController) mainScene.getUserData()).onCloseApplication(ev));
			stage.show();
		}

		private void loadConfigurations() throws ValidateError, InternalError {
			GlobalConfig.getInstance().setAppConfiguration(new AppConfiguration());
			GlobalConfig.getInstance().setLoggerConfiguration(new LoggerConfiguration());
			GlobalConfig.getInstance().setFxIDMapping(new FXIDClassMapping());

		}

		

		public static void main(String[] args) {
			launch(args);
		}
	}
}
