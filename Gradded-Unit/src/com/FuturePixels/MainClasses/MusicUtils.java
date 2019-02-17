/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses;

import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author RandomlyFuzzy
 */
public class MusicUtils {

    private static ArrayList<Thread> sounds = new ArrayList<Thread>();

    static synchronized void play(InputStream soundResource) {
        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                    clip.open(ais);
                    ais.skip((ais.getFrameLength() / ais.getFormat().getSampleSizeInBits()) * 20);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error playing sound " + ex.getMessage());
                }
            }
        });
        d.start();
        sounds.add(d);
    }
    
    public static void StopAllSounds(){
        sounds.forEach((A)->{
            A.stop();
        });
        
    }

}
