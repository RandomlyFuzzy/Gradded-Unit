/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses.Utils;

import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.AbstractClasses.ILevel;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.levels.Menus.*;
import com.FuturePixels.levels.OtherLevels.*;
import com.FuturePixels.levels.SoloLevels.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RandomlyFuzzy
 */
public class LevelLoader {

    private static final ILevel[] LEVELS = new ILevel[]{
        new MainMenu(), new Settings(), new LevelSelect(), new LeaderBoard(), new Level1Solo(), new Level2Solo(),new Level3Solo()
    };

    public LevelLoader(ILevel level) {
        Game.SetLevelActive(level);
    }

    public LevelLoader(String level){
        for (ILevel i : LEVELS) {
            if (i.getClass().getName().contains(level)) {
                try {
                    System.out.println("com.FuturePixels.MainClasses.LevelLoader.<init>() " + i.getClass().getName().toString());
                    Game.SetLevelActive(i.getClass().newInstance());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(LevelLoader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(LevelLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
