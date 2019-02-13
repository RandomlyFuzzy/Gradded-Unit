/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.Utils.ILevel;
import com.FuturePixels.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
public class MainMenu extends ILevel {

    private Game game; // this is a link back to the game's main window. private BufferedImage backgroundImage = null;
    private Image backgroundImage;

    public MainMenu() {
        super();
    }

    @Override
    public void init() {
    }

    @Override
    public void Update(ActionEvent ae) {

    }

    @Override
    public void Draw(Graphics g) {
        // Call the paintComponent method on the superclass to initialise drawing 
        g.drawImage(GetSprite("/Images/background.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
    }

    @Override
    public void keyPress(KeyEvent e) {
    }

    @Override
    public void keyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            Game.SetLevelActive("Level1");
        }
    }

}
