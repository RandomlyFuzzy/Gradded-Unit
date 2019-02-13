/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.Utils.*;
import com.FuturePixels.game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LeaderBoard extends ILevel {

    private Timer timer;
    Image background;
    private static long[] CollectionTimes = new long[6];
    private static int ind = 0;

    public LeaderBoard() {
        super();
        System.out.println("com.game.levels.LeaderBoard.<init>()");
    }

    @Override
    public void Update(ActionEvent ae) {
        this.repaint();
    }

    @Override
    public void init() {
    }

    @Override
    public void Draw(Graphics g) {

//        System.out.println("com.game.levels.level1.paintComponent()");
        g.drawImage(GetSprite("p/Images/background.png"), Game.g.getWindowWidth(), 0, (Game.g.getWindowWidth() * -1), (Game.g.getWindowHeight()), null);
        for (int i = 1; i < 6; i++) {
            g.drawString("" + ((CollectionTimes[i] - CollectionTimes[0]) / 1000000000f + " Seconds"), 20, (i * 30) + Game.g.getWindowHeight() / 3);
        }

    }

    public static void AddTime(long Time) {
        if (ind >= CollectionTimes.length) {
            return;
        }
        CollectionTimes[ind++] = Time;
    }

    @Override
    public void keyPress(KeyEvent e) {

    }

    @Override
    public void keyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            timer.stop();
            Game.SetLevelActive("ShowStartScreen");
        }
    }

}
