/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LevelSelectSolo extends ILevel {

    public Vector transpos = Vector.One();

    /**
     *
     */
    public LevelSelectSolo() {
        super();
        setStopAudioOnStart(false);
    }

    /**
     *
     */
    @Override
    public void init() {
        Transform.setOffsetTranslation(new Vector(0, Game.getWindowHeight()));
        setBackground(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.15f)), ((0.13f * (i % 6)) + 0.29f)), ("Level " + (i + 1)), new HUDdelegate() {
                public void OnClick(Button b) {
                    LevelLoader.LoadLevel(b.getMessage().replace(" ", ""));
                }
            }, false)).GetSprite("/images/button_2.png");
        }

        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                transpos = (new Vector(0, Game.getWindowHeight()));
                new Thread(() -> {
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Game.SetLevelActive(new MainMenu(new Vector(0, Game.getWindowHeight())));
                }).start();
            }
        }, false)).GetSprite("/images/button_0.png");
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/images/backgrounds/sololevels.png"));
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

        int x = (int) Transform.getOffsetTranslation().getX();
        int y = (int) Transform.getOffsetTranslation().getY();
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient.png"), x, y - Game.getWindowHeight(), Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/sololevels.png"), x, y, Game.getWindowWidth(), Game.getWindowHeight(), this);

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
    }

}
