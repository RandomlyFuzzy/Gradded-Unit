/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.FileUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;

/**
 *
 * @author Liam Rickman 1748905
 */
public class DebugLevel extends ILevel {

    @Override
    public void init() {

    }

    @Override
    public void Update(ActionEvent ae) {
    }

    @Override
    public void Draw(Graphics2D gd) {
    }

    @Override
    public void keyPress(KeyEvent ke) {
        System.out.println("com.FuturePixels.levels.Menus.DebugLevel.init()");
        String file = "";
        for (int i = 0; i < 0xFFFF; i++) {
            if (!KeyEvent.getKeyText(i).contains("Unknown keyCode")) {
                file += ""   + KeyEvent.getKeyText(i) + ":" + i + "\n";
                System.out.println("" + KeyEvent.getKeyText(i) + ":" + i);
            }
        }
        FileUtils.SetFileContence("resources/data/keys.txt", file);
    }

    @Override
    public void keyRelease(KeyEvent ke) {
    }

}
