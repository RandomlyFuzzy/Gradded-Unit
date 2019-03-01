/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
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
import javax.sound.sampled.Clip;
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

        play("/sounds/music.wav", 0, Clip.LOOP_CONTINUOUSLY);

        AddObject(new Button(new Vector(0.15f, 0.2f), "Solo", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelectSolo());
            }
        }));
        AddObject(new Button(new Vector(0.15f, 0.4f), "Coop", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelectCoop());
            }
        }));
        AddObject(new Button(new Vector(0.15f, 0.6f), "Leaderboard", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LeaderBoard());
            }
        }));
        AddObject(new Button(new Vector(0.85f, 0.9f), "", new HUDAbstract() {
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

        setBackgroundimage(GetSprite("/Images/WIP Background.png"));
    }

    @Override
    public void Update(ActionEvent ae) {

    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        Font title = new Font("Comic sans serif ms", 0, (int) (GamePreferences.ButtonDims().getY() * 30f));
        g.setFont(title);
//        for (int i = 0; i < Game.g.getScaledWidth(); i++) {
//            for (int j = 0; j < Game.g.getScaledHeight(); j++) {
//                if (m != null && m.getBounds().contains(i, j)) {
//                    g.drawRect(i, j, 1, 1);
//                }
//            }
//        }


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
