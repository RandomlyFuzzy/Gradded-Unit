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
import com.Liamengine.Engine.Utils.MusicUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Credits extends ILevel {

    private static BlackoutButton BB = null;
    private Button B = null;
    double i = 0;
    int ind = 0;
    boolean changed = false;
    String[] credits;

    /**
     *
     */
    public Credits() {
        super();
        System.out.println("com.game.levels.Settings.<init>()");
        setStopAudioOnStart(false);
        setSimpleCollison(true);
    }

    /**
     *
     */
    @Override
    public void init() {
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/Images/backgrounds/background1.png"));
        credits = FileUtils.GetFileSplit("resources/data/Credits.txt", "\n");
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        i += 1f * Game.getDelta();
    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {

        int val = (int) ((Math.sin(i ) + 1f) * 127)+1;
        int val2 = (int) ((Math.cos(i ) + 1f) * 100f);
        if (val >= 250) {
            ind++;
            ind %= 2;
            changed = true;
        }else
        if (changed) {
            setBackgroundimage(GetSprite("/Images/backgrounds/background" + (ind + 1) + ".png"));
            changed = false;
        }
        g.setColor(new Color(0, 0, 0, val));
        g.fillRect(0, 0, Game.getWindowWidth(), Game.getWindowHeight());
        g.setColor(new Color(55+val2, 55+val2, 55+val2));

        for (int j = 0; j < credits.length; j++) {
            int offset = g.getFontMetrics().stringWidth(credits[j]);
            g.drawString(credits[j], Game.getWindowWidth()/2-offset/2, (int)((Game.getWindowHeight()+(int) (j * g.getFont().getSize()))-(i*50)));
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
