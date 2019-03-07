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
import com.FuturePixels.levels.CoopLevels.Level1Coop;
import com.FuturePixels.levels.CoopLevels.Level2Coop;
import com.FuturePixels.levels.CoopLevels.Level3Coop;
import com.FuturePixels.levels.Menus.DebugLevel;
import com.FuturePixels.levels.Menus.LevelSelectSolo;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.levels.Menus.Settings;
import com.FuturePixels.levels.OtherLevels.LeaderBoard;
import com.FuturePixels.levels.SoloLevels.Level1Solo;
import com.FuturePixels.levels.SoloLevels.Level2Solo;
import com.FuturePixels.levels.SoloLevels.Level3Solo;
import com.FuturePixels.levels.SoloLevels.Level4Solo;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Entry {

    public static void main(String[] args) {
        ILevel[] arr = new ILevel[]{new MainMenu(), new Settings(), new LevelSelectSolo(), new LeaderBoard(), new Level1Solo(), new Level2Solo(), new Level3Solo(),new Level4Solo(), new Level1Coop(), new Level2Coop(), new Level3Coop()};
        LevelLoader.LL.SetLevels(arr);
        new GamePreferences();
        Game.setDefualtLevel(new MainMenu());
//        new Game(new DebugLevel());
        new Game(new MainMenu());
        Game.GetFrame().setTitle("Graded Unit");
    }
}
