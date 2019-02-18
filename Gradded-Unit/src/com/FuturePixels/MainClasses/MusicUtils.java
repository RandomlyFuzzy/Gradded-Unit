/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author RandomlyFuzzy
 */
public class MusicUtils {

    private static ArrayList<Thread> sounds = new ArrayList<Thread>();
    
    public synchronized static void play(String soundResource) {
        try {

            Thread d = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(soundResource));
                        clip.open(ais);
                        clip.start();
                    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {

                    }
                }
            });
            d.start();
            sounds.add(d);
        } catch (Exception ex) {
            System.out.println("Error playing sound " + ex.getMessage());
        }
    }

    public static void StopAllSounds() {
        sounds.forEach((A) -> {
            A.stop();
        });
    }
}
