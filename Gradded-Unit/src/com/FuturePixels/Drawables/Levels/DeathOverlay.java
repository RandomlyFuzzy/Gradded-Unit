/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.Components.Transform;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Utils.MusicUtils;
import com.Liamengine.Engine.Utils.imageUtils;
import com.FuturePixels.levels.Menus.MainMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 *
 * @author Liam Woolley 1748910
 */
public class DeathOverlay extends IDrawable {

    public DeathOverlay() {
        super();
        UseTransforms(false);
    }

    @Override
    public void init() {
        Level().AddObject(new Button(new Vector(0.3f, 0.8f), "[R] Retry", new HUDdelegate() {
            public void OnClick(Button b) {
                try {
                    LevelLoader.LoadLevel(b.Level().getClass().newInstance());
                } catch (InstantiationException ex) {
                    Logger.getLogger(DeathOverlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(DeathOverlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }));

        Level().AddObject(new Button(new Vector(0.7f, 0.8f), "[M] Main Menu", new HUDdelegate() {
            public void OnClick(Button b) {
                MusicUtils.StopAllSounds();
                LevelLoader.LoadLevel(new MainMenu());
            }
        }));
        Level().AddObject(new Mouse());
        GetSprite("API/cat");
    }

    @Override

    public void doMove() {
        setPosition(new Vector(Game.getWindowWidth() * .5f, Game.getWindowHeight() * 1.3f));
        setSpriteWidth((int) (Game.getWindowWidth() * .5f));
        setSpriteHeight((int) (Game.getWindowHeight() * .6f));
    }

    @Override
    public void Update(Graphics2D g) {
        float w = Game.getWindowWidth();
        float h = Game.getWindowHeight();
        g.setColor(new Color(100, 100, 100, 100));
        g.fillRect((int) (0.05f * w), (int) (0.05f * w), (int) (0.9f * w), (int) (h - ((0.1f * w))));
        g.drawImage(getLastImage(), (int) (-(getSpriteWidth()) / 2 + (w * 0.5f)), (int) (-(getSpriteHeight()) / 2 + (h * 0.43f)), (int) (getSpriteWidth()), (int) (getSpriteHeight()), null);
        Font pre = g.getFont();
        Font title = new Font("Comic sans serif ms", 0, (int) (Game.ButtonDims().getY() * 30f));
        g.setFont(title);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        float wid = metrics.stringWidth("You lost but here's a cat to cheer you up!") / 2;
        float hei = g.getFont().getSize();
        g.setColor(new Color(100, 100, 100, 200));
        g.fillRect((int) ((0.5f * w) - wid), (int) ((h * 0.13f) - hei), (int) (wid * 2), (int) hei);
        g.setColor(new Color(128, 0, 128));
        g.drawString("You lost but here's a cat to cheer you up!", (0.5f * w) - wid, (h * 0.125f));
        g.setFont(pre);

        if (Level().getLastKeyPress().getKeyCode() == KeyEvent.VK_R) {
            LevelLoader.LoadLevel(Level().getClass().getName());
        }
        if (Level().getLastKeyPress().getKeyCode() == KeyEvent.VK_M) {
            LevelLoader.LoadLevel(Game.getDefualtLevel());
        }
    }

    @Override
    public void onCollison(IDrawable im) {
    }

}
