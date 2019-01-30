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
import java.awt.Graphics2D;
import java.awt.Image;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Liam Woolley 1748910
 */
public class level1 extends ILevel implements ILevelInterface {

    private Game game;
    Image background;
    private Player thePlayer;
    private Cookie theTreasure;

    public level1(Game theGame) {
        game = theGame;
        init();
        System.out.println("com.game.levels.level1.<init>()");
        thePlayer = new Player(this);
        theTreasure = new Cookie();
    }

    public void actionPerformed(ActionEvent ae) {
//        System.out.println("com.game.levels.level1.actionPerformed()");
        checkCollisions();
        game.SetDelta();
        movement();

        if (LeaderBoard.CheckForFinish()) {
            stop();
            game.ShowLeaderBoard();
        }
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
        LeaderBoard.AddTime(System.nanoTime());
    }

    private void movement() {
        thePlayer.doMove();
        theTreasure.doMove();
    }

    private void checkCollisions() {
        thePlayer.checkCollision(theTreasure);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("com.game.levels.level1.paintComponent()");
        g.drawImage(background, 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
        thePlayer.doMove();
        thePlayer.draw(g);
        theTreasure.draw(g);
        g.dispose();
    }

    private class TAdapter extends KeyAdapter {

        private int move = 0;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    move |= 1;
                    break;
                case KeyEvent.VK_DOWN:
                    move |= 2;
                    break;
                case KeyEvent.VK_LEFT:
                    move |= 4;
                    break;
                case KeyEvent.VK_RIGHT:
                    move |= 8;
                    break;
            }
            thePlayer.move(move);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    move -= 1;
                    break;
                case KeyEvent.VK_DOWN:
                    move -= 2;
                    break;
                case KeyEvent.VK_LEFT:
                    move -= 4;
                    break;
                case KeyEvent.VK_RIGHT:
                    move -= 8;
                    break;
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                timer.stop();
//                game.DeleteGame();
//                game.playGame();
                game.ShowLeaderBoard();
            }
            System.out.println("com.game.levels.level1.TAdapter.keyReleased() " + move);
            thePlayer.move(move);
            // thePlayer.stop();
        }
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}
