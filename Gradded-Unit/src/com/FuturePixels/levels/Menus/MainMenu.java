/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.levels.OtherLevels.LeaderBoard;
import java.awt.Color;
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

    public MainMenu() {
        super();
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        
        play("/sounds/music.wav", 254,-1);

        AddObject(new Button(new Vector(0.15f, 0.2f), "Solo", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelectSolo());
            }
        }));
         AddObject(new Button(new Vector(0.15f, 0.3f), "Coop", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelectCoop());
            }
        }));
//        AddObject(new Button(new Vector(0.5f, 0.4f), "To Game Coop", new HUDAbstract() {
//            @Override
//            public void OnClick(Button b) {
//                Game.SetLevelActive(new Level1Solo());
//            }
//        }));
        AddObject(new Button(new Vector(0.15f, 0.4f), "Leaderboard", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LeaderBoard());
            }
        }));
        AddObject(new Button(new Vector(0.15f, 0.6f), "", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        })).GetSprite("/images/Settings.png");;
        AddObject(new Button(new Vector(0.98f, 0.91f), "", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                System.exit(0);
            }
        })).GetSprite("/images/Quit.png");
        AddObject(new Mouse());
        AddObject(new HUD());
    }

    @Override
    public void Update(ActionEvent ae) {

    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        Font title = new Font("Arial", 0, 22);
        g.setFont(title);
        
        g.drawImage(GetSprite("/Images/WIP Background.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
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
