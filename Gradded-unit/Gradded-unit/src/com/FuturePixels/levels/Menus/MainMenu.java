/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Components.Transform;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Woolley 1748910
 */
public class MainMenu extends ILevel {

    private static Vector transpos = Vector.Zero();
    private static Vector StartPos = Vector.Zero();

    public static void setTranspos(Vector transpos) {
        MainMenu.transpos = transpos;
    }

    public MainMenu() {
        super();
        setStopAudioOnStart(false);
        StartPos = Vector.Zero();
    }

    /**
     *
     */
    public MainMenu(Vector StartPos) {
        super();
        setStopAudioOnStart(false);
        this.StartPos = StartPos;

    }

    /**
     *
     */
    @Override
    public void init() {
        setBackground(Color.BLACK);
        if (transpos == null) {
            transpos = Vector.Zero();
        }
        Transform.setOffsetTranslation(StartPos);
        Game.setWorldrelDims(new Vector(1, 1));

        AddObject(new Button(new Vector(0.218f, 0.415f), "Solo", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                MainMenu.setTranspos(new Vector(0, Game.getWindowHeight()));
                new Thread(() -> {
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Game.SetLevelActive(new LevelSelectSolo());
                    MainMenu.setTranspos(new Vector(0, 0));
                }).start();
            }
        }, false)).GetSprite("/images/button_0.png");
        AddObject(new Button(new Vector(0.365f, 0.415f), "Coop", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {

                MainMenu.setTranspos(new Vector(-Game.getWindowWidth(), 0));
                new Thread(() -> {
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Game.SetLevelActive(new LevelSelectCoop());
                    MainMenu.setTranspos(new Vector(0, 0));
                }).start();
            }
        }, false)).GetSprite("/images/button_0.png");
        AddObject(new Button(new Vector(0.29f, 0.53f), "Leaderboard", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LeaderBoard());
            }
        }, false));

        AddObject(new Button(new Vector(0.29f, 0.645f), "Settings", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }, false));

        AddObject(new Button(new Vector(0.29f, 0.76f), "Credits", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Credits());
            }
        }, false));

        AddObject(new Button(new Vector(0.29f, 0.875f), "Exit Game", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                System.exit(0);
            }
        }, false));

        AddObject(new Mouse());
        AddObject(new HUD());
        play("/sounds/music.wav", 0, Clip.LOOP_CONTINUOUSLY);
//        setBackgroundimage(GetSprite("/images/backgrounds/mainmenu.png"));
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
        int x = (int) Transform.getOffsetTranslation().getX();
        int y = (int) Transform.getOffsetTranslation().getY();
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient.png"), x, y - Game.getWindowHeight(), Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient2.png"), x + Game.getWindowWidth(), y, Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/mainmenu.png"), x, y, Game.getWindowWidth(), Game.getWindowHeight(), this);

    }

    @Override
    public void PostDraw(Graphics2D g) {

        float Time = 0.075f;
        Vector transspos = Transform.getOffsetTranslation();
        float x0 = (1 - Time) * transspos.getX() + Time * transpos.getX();
        float y0 = (1 - Time) * transspos.getY() + Time * transpos.getY();
        Transform.setOffsetTranslation(new Vector(x0, y0));

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
