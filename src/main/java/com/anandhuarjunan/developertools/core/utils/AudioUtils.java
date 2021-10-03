
package com.anandhuarjunan.developertools.core.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioUtils {

    
    public static void playSound(String path) {
	Media sound = new Media(AudioUtils.class.getResource(path).toExternalForm());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	mediaPlayer.play();
    }
    
    
}
