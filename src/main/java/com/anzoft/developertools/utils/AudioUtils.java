/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioUtils {

    
    public static void playSound(String path) {
	Media sound = new Media(AudioUtils.class.getResource(path).toExternalForm());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	mediaPlayer.play();
    }
    
    
}
