/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels;

import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Entry.Game;
import com.FuturePixels.levels.Playable.CoopLevels.LevelCoop;
import com.FuturePixels.levels.Menus.Credits;
import com.FuturePixels.levels.Menus.LevelSelectSolo;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.levels.Menus.Settings;
import com.FuturePixels.levels.Menus.LeaderBoard;
import com.FuturePixels.levels.Playable.SoloLevels.Level1Solo;
import com.FuturePixels.levels.Playable.SoloLevels.Level2Solo;
import com.FuturePixels.levels.Playable.SoloLevels.Level3Solo;
import com.FuturePixels.levels.Playable.SoloLevels.Level4Solo;
import com.FuturePixels.levels.Playable.SoloLevels.Level5Solo;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.FileUtils;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Entry {

    /**
     *
     * @param args 
     */
    public static void main(String[] args) {
        ILevel[] arr = new ILevel[]{new MainMenu(Vector.Zero()), new Settings(), new LevelSelectSolo(), new LeaderBoard(), new Level1Solo(), new Level2Solo(), new Level3Solo(), new Level4Solo(),new Level5Solo(), new LevelCoop(), new Credits()};
        LevelLoader.LL.SetLevels(arr);
        new GamePreferences();
        Game.setDefualtLevel(new MainMenu(Vector.Zero()));

        // Reads preferences from file and sets to controlls
        String[] preferences = FileUtils.GetFileSplit("resources/data/preferences.txt", "\n");
        GamePreferences.gp.setKeyLeftP1(Integer.parseInt(preferences[0].trim()));
        GamePreferences.gp.setKeyRightP1(Integer.parseInt(preferences[1].trim()));
        GamePreferences.gp.setKeyJumpP1(Integer.parseInt(preferences[2].trim()));
        GamePreferences.gp.setKeyLeftP2(Integer.parseInt(preferences[3].trim()));
        GamePreferences.gp.setKeyRightP2(Integer.parseInt(preferences[4].trim()));
        GamePreferences.gp.setKeyJumpP2(Integer.parseInt(preferences[5].trim()));
        GamePreferences.gp.setKeyDropP1(Integer.parseInt(preferences[6].trim()));
        GamePreferences.gp.setKeyDropP2(Integer.parseInt(preferences[7].trim()));
        new Game(new MainMenu(Vector.Zero()));
        
        //Sets title of window
        Game.GetFrame().setTitle("Breaking Through - FuturePixels");
    }
}
