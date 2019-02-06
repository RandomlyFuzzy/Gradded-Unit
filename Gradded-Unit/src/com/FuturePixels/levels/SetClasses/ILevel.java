/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.SetClasses;

import com.FuturePixels.game.Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author RandomlyFuzzy
 */
public class ILevel extends JPanel implements ActionListener {

    public Image backgroundImage;
    public Timer timer;

    public ILevel() {
        timer = new Timer(16, this);
    }

    public void init() {

    }

    public void actionPerformed(ActionEvent ae) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();

    }

    public synchronized void play(InputStream soundResource) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                    ais.skip((ais.getFrameLength()/ais.getFormat().getSampleSizeInBits())*20);
                    
                    clip.open(ais);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error playing sound " + ex.getMessage());
                }
            }
        }).start();
    }
}
