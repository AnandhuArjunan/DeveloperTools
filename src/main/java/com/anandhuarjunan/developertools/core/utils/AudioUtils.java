
package com.anandhuarjunan.developertools.core.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioUtils {

    private AudioUtils() {}

    
    public static void playSound(String path) {
        java.net.URL url = AudioUtils.class.getResource(path);
        if (url != null) {
            Media sound = new Media(url.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
    }
    
    
}
