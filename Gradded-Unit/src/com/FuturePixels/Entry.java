/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels;

import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Utils.LevelLoader;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.levels.CoopLevels.Level1Coop;
import com.FuturePixels.levels.CoopLevels.Level2Coop;
import com.FuturePixels.levels.CoopLevels.Level3Coop;
import com.FuturePixels.levels.Menus.LevelSelectSolo;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.levels.Menus.Settings;
import com.FuturePixels.levels.OtherLevels.LeaderBoard;
import com.FuturePixels.levels.SoloLevels.Level1Solo;
import com.FuturePixels.levels.SoloLevels.Level2Solo;
import com.FuturePixels.levels.SoloLevels.Level3Solo;

/**
 *
 * @author RandomlyFuzzy
 */
public class Entry {

    public static void main(String[] args) {
        ILevel[] arr = new ILevel[]{new MainMenu(), new Settings(), new LevelSelectSolo(), new LeaderBoard(), new Level1Solo(), new Level2Solo(), new Level3Solo(), new Level1Coop(), new Level2Coop(), new Level3Coop()};
        LevelLoader.LL.SetLevels(arr);
        new GamePreferences();
        new Game(new MainMenu());

    }
}
