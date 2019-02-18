/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.ButtonAbstract;
import com.FuturePixels.MainClasses.ILevel;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Vector;
import java.awt.Font;
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

    private Image backgroundImage;
    Mouse m;

    public MainMenu() {
        super();
    }

    @Override
    public void init() {
        Game.toggleCursor();
        AddObject(new Mouse());
        AddObject(new HUD());

        AddObject(new Button(new Vector(0.5f, 0.3f), "To Game Solo", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Level1Solo());
            }
        }));
        AddObject(new Button(new Vector(0.5f, 0.4f), "To Game Coop", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Level1Coop());
            }
        }));
        AddObject(new Button(new Vector(0.5f, 0.5f), "To Leaderboard", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LeaderBoard());
            }
        }));
        AddObject(new Button(new Vector(0.5f, 0.7f), "To Settings", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }));
        AddObject(new Button(new Vector(0.5f, 0.8f), "Quit", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                System.exit(0);
            }
        }));

    }

    @Override
    public void Update(ActionEvent ae) {

    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/background.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
//        for (int i = 0; i < Game.g.getWindowWidth(); i++) {
//            for (int j = 0; j < Game.g.getWindowHeight(); j++) {
//                if (m != null && m.getBounds().contains(i, j)) {
//                    g.drawRect(i, j, 1, 1);
//                }
//            }
//        }

        Font f = new Font("Comic sans", 0, WIDTH);

    }

    @Override
    public void keyPress(KeyEvent e) {
    }

    @Override
    public void keyRelease(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_P) {
//        }
    }

}
