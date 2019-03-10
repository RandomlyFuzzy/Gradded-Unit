/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Entry.Game;
import com.FuturePixels.levels.CoopLevels.Level1Coop;
import com.FuturePixels.levels.CoopLevels.Level2Coop;
import com.FuturePixels.levels.CoopLevels.Level3Coop;
import com.FuturePixels.levels.Menus.Credits;
import com.FuturePixels.levels.Menus.DebugLevel;
import com.FuturePixels.levels.Menus.LevelSelectSolo;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.levels.Menus.Settings;
import com.FuturePixels.levels.OtherLevels.LeaderBoard;
import com.FuturePixels.levels.SoloLevels.Level1Solo;
import com.FuturePixels.levels.SoloLevels.Level2Solo;
import com.FuturePixels.levels.SoloLevels.Level3Solo;
import com.FuturePixels.levels.SoloLevels.Level4Solo;
import com.Liamengine.Engine.Utils.FileUtils;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Entry {

    public static void main(String[] args) {
        ILevel[] arr = new ILevel[]{new MainMenu(), new Settings(), new LevelSelectSolo(), new LeaderBoard(), new Level1Solo(), new Level2Solo(), new Level3Solo(), new Level4Solo(), new Level1Coop(), new Level2Coop(), new Level3Coop(),new Credits()};
        LevelLoader.LL.SetLevels(arr);
        new GamePreferences();
        Game.setDefualtLevel(new MainMenu());

        String[] preferences =  FileUtils.GetFileSplit("resources/data/Preferences.txt", "\n");
        GamePreferences.gp.setKeyLeftP1(Integer.parseInt(preferences[0].trim()));
        GamePreferences.gp.setKeyRightP1(Integer.parseInt(preferences[1].trim()));
        GamePreferences.gp.setKeyJumpP1(Integer.parseInt(preferences[2].trim()));
        GamePreferences.gp.setKeyLeftP2(Integer.parseInt(preferences[3].trim()));
        GamePreferences.gp.setKeyRightP2(Integer.parseInt(preferences[4].trim()));
        GamePreferences.gp.setKeyJumpP2(Integer.parseInt(preferences[5].trim()));
        GamePreferences.gp.setKeyDropP1(Integer.parseInt(preferences[6].trim()));
        GamePreferences.gp.setKeyDropP2(Integer.parseInt(preferences[7].trim()));

        new Game(new MainMenu());
        Game.GetFrame().setTitle("Graded Unit");
    }
}
