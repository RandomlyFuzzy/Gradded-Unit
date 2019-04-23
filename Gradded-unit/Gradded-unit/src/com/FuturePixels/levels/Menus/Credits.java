/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Vector;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.DropDownButton;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.Slider;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.FileUtils;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Utils.MusicUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Credits extends ILevel {

    private double i = 0;
    private int ind = 0;
    private boolean changed = false;
    private boolean once = true;
    private String[] credits;

    /**
     *
     */
    public Credits() {
        super();

        setStopAudioOnStart(true);
        setSimpleCollison(true);
    }

    /**
     *
     */
    @Override
    public void init() {
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/images/backgrounds/level0.png"));
        credits = FileUtils.GetFileSplit("resources/data/credits.txt", "\n");
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        i += Game.WorldScale().getY() * ILevel.getDelta();
    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {

        play("/sounds/creditsong.wav", 0, Clip.LOOP_CONTINUOUSLY);

        int val = (int) ((Math.sin(i) + 1f) * 127) + 1;
        int val2 = (int) ((Math.cos(i) + 1f) * 100f);
        if (val >= 253 && once) {
            ind++;
            ind %= 4;
            changed = true;
            once = false;

        } else if (val >= 253) {
        } else if (changed) {
            setBackgroundimage(GetSprite("/images/backgrounds/level" + (ind) + ".png"));
            changed = false;
            once = true;
        }
        g.setColor(new Color(0, 0, 0, val));
        g.fillRect(0, 0, Game.getWindowWidth(), Game.getWindowHeight());
        g.setColor(new Color(55 + val2, 55 + val2, 55 + val2));

        for (int j = 0; j < credits.length; j++) {
            int offset = g.getFontMetrics().stringWidth(credits[j]);
            g.drawString(credits[j], Game.getWindowWidth() / 2 - offset / 2, (int) ((Game.getWindowHeight() + (int) (j * g.getFont().getSize())) - (i * 50)));

            if (j != credits.length - 1) {
                continue;
            }
            if ((int) ((Game.getWindowHeight() + (int) (j * g.getFont().getSize())) - (i * 50)) < -Game.getScaledHeight()/4) {
                LevelLoader.LoadLevel(new MainMenu());
            }
        }

        Font f = g.getFont();
        g.setFont(f.deriveFont(f.getSize() * 0.6f));
        g.drawString("Esc to exit", 20, Game.getWindowHeight() - g.getFont().getSize() * 2);
        g.setFont(f);
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            MusicUtils.StopAllSounds();
            LevelLoader.LoadLevel(new MainMenu(Vector.Zero()));
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {

    }

}
