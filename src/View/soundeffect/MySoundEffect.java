/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.soundeffect;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author ppolo
 */
public class MySoundEffect {

    public static final String PATH_SCAN_SUCCESS = "soundEffect\\scannerSuccess.wav";
    public static final String PATH_CAPTURE_PICTURE = "soundEffect\\cameraEffect.wav";

    private static URL getUrl(String path) {
        File file = new File(path);
        URL url = null;
        if (file.canRead()) {
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        return url;
    }
    
    public static void play(String path) {
        AudioClip clip = Applet.newAudioClip(getUrl(path));
        clip.play();
    }
}
