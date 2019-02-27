/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Engine.Utils;

import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RandomlyFuzzy
 */
public class LevelLoader {

    public static ArrayList<ILevel> LEVELS = new ArrayList<ILevel>();

    public static ArrayList<ILevel> getLEVELS() {
        return LEVELS;
    }

    public static void setLEVELS(ArrayList<ILevel> LEVELS) {
        LevelLoader.LEVELS = LEVELS;
    }

    public LevelLoader(ILevel level) {
        if (!LEVELS.contains(level)) {
            LEVELS.add(level);
        }
        Game.SetLevelActive(level);
    }

    public LevelLoader(String level) {
        for (ILevel i : LEVELS) {
            if (i.getClass().getName().contains(level)) {
                try {
                    System.out.println("com.FuturePixels.MainClasses.LevelLoader.<init>() " + i.getClass().getName().toString());
                    Game.SetLevelActive(i.getClass().newInstance());
                    return;
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(LevelLoader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(LevelLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.err.println("couldnt find level with the string " + level + " inside of it try adding it to the LevelLoader.AddtoLevels");
    }

    public LevelLoader() {
        LEVELS = new ArrayList<ILevel>();;
    }
}
