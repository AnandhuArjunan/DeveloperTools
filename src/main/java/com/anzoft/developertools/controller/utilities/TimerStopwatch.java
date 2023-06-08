package com.anzoft.developertools.controller.utilities;

import java.net.URL;
import java.util.ResourceBundle;

import com.anzoft.developertools.app.headerpanel.DisplayPanelContent;
import com.anzoft.developertools.app.headerpanel.ShowPriority;
import com.anzoft.developertools.app.headerpanel.message.TimerUtiltyNotification;
import com.anzoft.developertools.utils.AudioUtils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class TimerStopwatch  implements Initializable{
    
    @FXML
    private TabPane tab;
    
    @FXML
    private TextField hField;

    @FXML
    private TextField mField;

    @FXML
    private TextField sField;

    @FXML
    private Label timeLbl;

    @FXML
    private Button startBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private Button resumeBtn;

    @FXML
    private Button resetBtn;
    

    @FXML
    private TextField timerName;

    long seconds;
    Timeline stopWatchTimeline ;
    
    String defaultValue = "00:00:00";
    
    @FXML
    void handleReset(ActionEvent event) {
	     
	resetTimer();
    }

   private void resetTimer() {
       stopWatchTimeline. stop();
	startBtn.setDisable(false);
	timeLbl.setText(defaultValue);
   }

    @FXML
    void handleStart(ActionEvent event) {
	startBtn.setDisable(true);
	seconds = Integer.parseInt(hField.getText()) * 3600 +
                  Integer.parseInt(mField.getText()) * 60 +
                  Integer.parseInt(sField.getText());
      
	stopWatchTimeline   = new Timeline(new KeyFrame(Duration.seconds(1), ev-> {
	  if(seconds == 0) {
	      resetTimer();
	      TimerUtiltyNotification notification = new TimerUtiltyNotification(ShowPriority.HIGH_PRIORITY,null !=timerName.getText()? "Timer - "+timerName.getText():"Timer");
		notification.load("Time is Up ! ");
		AudioUtils.playSound("/sounds/timer.mp3");
		DisplayPanelContent.display(notification);
	  }else {
	  seconds--;
	  }
	  timeLbl.setText(String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));

      }));
      stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
      stopWatchTimeline.play();
    }
    
    
    
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
	
	
	
	
	
	
    }
    
    
    
}
