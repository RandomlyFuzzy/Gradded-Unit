/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.levels.SetClasses.ILevel;
import com.FuturePixels.levels.SetClasses.ILevelInterface;
import com.FuturePixels.characters.Player;
import com.FuturePixels.characters.Cookie;
import com.FuturePixels.game.Game;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LeaderBoard extends ILevel implements ILevelInterface {

    private Game game;
    private Timer timer;
    Image background;
    private static long[] CollectionTimes = new long[6];
    private static int ind = 0;

    public LeaderBoard(Game theGame) {
        game = theGame;
        init();
        System.out.println("com.game.levels.LeaderBoard.<init>()");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.repaint();
    }

    public void init() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        try {
            background = ImageIO.read(getClass().getResource("/Images/background.png"));
        } catch (Exception ex) {
            System.err.println("Error loading background image");
        }
        timer = new Timer(10, this);

    }

    public static boolean CheckForFinish() {
        return ind == 6;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//        System.out.println("com.game.levels.level1.paintComponent()");
        g.drawImage(background, Game.g.getWindowWidth(), 0, (Game.g.getWindowWidth() * -1), (Game.g.getWindowHeight()), null);
        for (int i = 1; i < 6; i++) {
            g.drawString("" + ((CollectionTimes[i] - CollectionTimes[0]) / 1000000000f + " Seconds"), 20, (i * 30) + Game.g.getWindowHeight() / 3);
        }

        g.dispose();
    }

    public static void AddTime(long Time) {
        if (ind >= CollectionTimes.length) {
            return;
        }

        CollectionTimes[ind++] = Time;

    }

    public void start() {
        timer.start();
    }

    public void stop() {
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_P) {
                timer.stop();
                Game.SetLevelActive("ShowStartScreen");
            }
        }
    }

}
