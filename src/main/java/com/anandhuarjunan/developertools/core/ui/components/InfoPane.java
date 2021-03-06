/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
βπ§  π  π΅π  πππΆππΉπ½π πππΏπππΆπ  π  π§ 
 */
package com.anandhuarjunan.developertools.core.ui.components;



import org.apache.commons.lang3.StringUtils;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class InfoPane extends HBox {

	Label text = new Label();
	Label errorLog = new Label("Click here for Application Log");
	
	public InfoPane() {
	     setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
	     setVisible(false);
         setSpacing(8);
         setMargin(errorLog, new Insets(0, 10, 0, 0));
	}
	
	public void setLoadingEffect(boolean enable) {
		setVisible(true);getChildren().clear();text.setText("Loading");
		getChildren().add(text);
		text.setFont(new Font("System Bold", 12.0));
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),e-> {setVisible(true);setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));text.setTextFill(Color.BLACK);})
				,new KeyFrame(Duration.millis(1000),e-> setVisible(false)));
		if(enable) {
			  timeline.setCycleCount(Animation.INDEFINITE);
			  timeline.play();
		}
		else {
			  timeline.stop();
			  setVisible(true);
		}
	}
	
	public void setError(String errorText) {
		setVisible(true);getChildren().clear();
		text.setText("Sorry, Some Error Occured !"+" "+(!StringUtils.isEmpty(errorText)?errorText:""));
		errorLog.setTextFill(Color.WHITE);
		errorLog.setUnderline(true);
		errorLog.setFont(new Font("System Bold", 12.0));
		text.setFont(new Font("System Bold", 12.0));
		text.setTextFill(Color.WHITE);getChildren().addAll(text,errorLog);
		setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		PauseTransition  pause = new PauseTransition(Duration.seconds(20));
		pause.play();
		pause.setOnFinished(event->setVisible(false));
		errorLog.setOnMouseClicked(e->invokeLogViewer());
	}
	
	public void setInfo(String infoText) {
		setVisible(true);
		text.setText("Good !"+" "+(!StringUtils.isEmpty(infoText)?infoText:""));
		text.setTextFill(Color.WHITE);getChildren().add(text);
		text.setFont(new Font("System Bold", 15.0));
		setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		PauseTransition  pause = new PauseTransition(Duration.seconds(10));
		pause.play();
		pause.setOnFinished(event->setVisible(false));
	}
	
	private void invokeLogViewer() {
		/*
		 * try { ToolLoaderFactory tabLoader =
		 * Suppliers.getInstance().getTabLoader().get();
		 * 
		 * //tabLoader.loadTool("Log Viewer",
		 * "DEVELOPER_NOTES",ToolClasses.DEVELOPER_NOTES,true); } catch(ToolError e) {
		 * Suppliers.getInstance().getExceptionHandler().get().handleException(e,Labels.
		 * INTERNAL_ERROR,Icons.ERROR_MARK); }
		 */
	}

}
