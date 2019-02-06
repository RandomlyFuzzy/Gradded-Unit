/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.SetClasses;

import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.characters.SetClasses.IMoveable;
import com.FuturePixels.game.Game;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.levels.StartGamePanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class ILevel extends JPanel implements ActionListener {

    public Game game;
    public Timer timer;
    private ArrayList<IMoveable> gameObjs = new ArrayList<IMoveable>();

    public ILevel(Game theGame) {
        timer = new Timer(16, this);
        game = theGame;
    }

    public void init() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
    }

    public void actionPerformed(ActionEvent ae) {

    }

    public void movement() {
        gameObjs.forEach((obj) -> {
            obj.doMove();
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public void DrawObjs(Graphics g) {
        game.SetDelta();
        gameObjs.forEach((obj) -> {
            obj.draw(g);
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();

    }

    public void Add(IMoveable im) {
        gameObjs.add(im);
    }

    public synchronized void play(InputStream soundResource) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                    ais.skip((ais.getFrameLength() / ais.getFormat().getSampleSizeInBits()) * 20);

                    clip.open(ais);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error playing sound " + ex.getMessage());
                }
            }
        }).start();
    }

    public BufferedImage GetSprite(String URI) {
        BufferedImage g = imageUtils.T.GetImage(URI);
        return g;
    }

    public void checkCollionsions() {

//        thePlayer.checkCollision(theTreasure);
    }

    public abstract void keyPress(KeyEvent e);

    public abstract void keyRelease(KeyEvent e);

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            keyRelease(e);

        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPress(e);
        }
    }
}
