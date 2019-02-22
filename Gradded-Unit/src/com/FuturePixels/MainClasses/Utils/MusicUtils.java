/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static ArrayList<MusicThread> sounds = new ArrayList<MusicThread>();

    public synchronized static void play(String soundResource) {
        play(soundResource, 0);
    }

    public synchronized static void play(String soundResource, float time) {
        try {
            MusicThread d = new MusicThread(soundResource);
            d.Search(time);
            d.Start();
            sounds.add(d);
        } catch (Exception ex) {
            System.out.println("Error playing sound " + ex.getMessage());
        }
    }

    public static void StopAllSounds() {
        sounds.forEach((A) -> {
            A.Stop();
        });
    }

    private static class MusicThread {

        private Clip clip;
        private AudioInputStream ais;

        public MusicThread(String Source) {
            super();
            try {
                clip = AudioSystem.getClip();
                this.ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(Source));
                clip.open(ais);

            } catch (LineUnavailableException ex) {
                Logger.getLogger(MusicUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MusicUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(MusicUtils.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public Clip getClip() {
            return clip;
        }

        public void Search(float time) {
            if (time * clip.getFormat().getFrameRate() >= ais.getFrameLength()) {
                System.err.println("input size to big");
            } else {
                clip.setFramePosition(((int) (time * clip.getFormat().getFrameRate())));
            }
        }

        public void Start() {
            new Thread(() -> {
                clip.start();
            }).start();
        }

        public void Stop() {
            clip.stop();
        }
    }

}
