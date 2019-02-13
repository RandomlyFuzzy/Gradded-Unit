/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.GameClasses.Button;
import com.FuturePixels.GameClasses.Mouse;
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
public class StartGamePanel extends ILevel{

    private Image backgroundImage;

    public StartGamePanel() {
        super();
    }

    @Override
    public void init() {
        Add(new Mouse());
        Add(new Button());
    }

    @Override
    public void Update(ActionEvent ae){
    
    }
    
    
    @Override
    public void Draw(Graphics g) {
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
