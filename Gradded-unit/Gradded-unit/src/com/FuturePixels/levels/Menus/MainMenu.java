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
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.FuturePixels.levels.OtherLevels.LeaderBoard;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

/**
 *
 * @author Liam Woolley 1748910
 */
public class MainMenu extends ILevel {

    /**
     *
     */
    public MainMenu() {
        super();
        setStopAudioOnStart(false);
    }

    /**
     *
     */
    @Override
    public void init() {
        Game.setWorldrelDims(new Vector(1,1));

        AddObject(new Button(new Vector(0.2215f, 0.425f), "Solo", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelectSolo());
            }
        })).GetSprite("/images/Button_0.png");
        AddObject(new Button(new Vector(0.3575f, 0.425f), "Coop", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelectCoop());
            }
        })).GetSprite("/images/Button_0.png");
        AddObject(new Button(new Vector(0.29f, 0.55f), "Leaderboard", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LeaderBoard());
            }
        }));
        
        AddObject(new Button(new Vector(0.29f, 0.675f), "Settings", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }));
        
        AddObject(new Button(new Vector(0.29f, 0.8f), "Exit Game", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                System.exit(0);
            }
        }));
      
        //OLD SETTINGS/QUIT ICON
//        AddObject(new Button(new Vector(0.85f, 0.9f), "", new HUDdelegate() {
//            @Override
//            public void OnClick(Button b) {
//                Game.SetLevelActive(new Settings());
//            }
//        })).GetSprite("/images/Settings.png");
//        
//        AddObject(new Button(new Vector(0.98f, 0.91f), "", new HUDdelegate() {
//            @Override
//            public void OnClick(Button b) {
//                System.exit(0);
//            }
//        })).GetSprite("/images/Quit.png");
        AddObject(new Mouse());
        AddObject(new HUD());
        play("/sounds/music.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/Images/backgrounds/mainmenu.png"));
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("resources/fonts/maintext.ttf"));
            Font title = Font.createFont(Font.TRUETYPE_FONT, myStream);
            title = title.deriveFont(Font.PLAIN, (Game.ButtonDims().getY() * 50f));
            ILevel.setDefaultFont(title);
        } catch (FontFormatException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {

    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.WHITE);

//        

//        for (int i = 0; i < Game.g.getScaledWidth(); i++) {
//            for (int j = 0; j < Game.g.getScaledHeight(); j++) {
//                if (m != null && m.getBounds().contains(i, j)) {
//                    g.drawRect(i, j, 1, 1);
//                }
//            }
//        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_P) {
//        }
    }

}
