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
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LevelSelectSolo extends ILevel {

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
        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.2f * (i % 5)) + 0.1f), ((0.1f * (i / 6)) + 0.1f)), ("Level" + (i + 1)) + "Solo", new HUDdelegate() {
                public void OnClick(Button b) {
                    LevelLoader.LoadLevel(b.getMessage());
                }
            }));
        }
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/Images/backgrounds/background1.png"));
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
        
         try {
            Font title = new Font("comic sans ms", 1, 20);
            title = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/font.ttf"));
            title = title.deriveFont(Font.PLAIN, (Game.ButtonDims().getY() * 50f));
            g.setFont(title);
        } catch (FontFormatException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
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
