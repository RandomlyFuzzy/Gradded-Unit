/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.levels.SetClasses.ILevel;
import com.FuturePixels.levels.SetClasses.ILevelInterface;
import com.FuturePixels.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Liam Woolley 1748910
 */
public class StartGamePanel extends ILevel implements ILevelInterface  {

    private Game game; // this is a link back to the game's main window. private BufferedImage backgroundImage = null;
    private Image backgroundImage;

    public StartGamePanel(Game theGame) {
        super();
        game = theGame;
        init();
        addKeyListener(new TAdapter());
    }

    @Override
    public void init() {

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/background.png"));
        } catch (Exception ex) {
            System.err.println("Error Loading Image");
        } finally {
        }
        setFocusable(true);
    }


    @Override
    public void paintComponent(Graphics g) {
       // Call the paintComponent method on the superclass to initialise drawing 
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0,(Game.g.getWindowWidth()),(Game.g.getWindowHeight()), null);
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                Game.SetLevelActive("Level1");
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }
    }

}
