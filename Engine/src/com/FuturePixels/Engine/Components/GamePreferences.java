package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Vector;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * this is a singleton for all game related variables that need to controlled
 * cross level like that of audio and display settings(aspect ration resolution
 * and other things)
 *
 * @author RandomlyFuzzy
 */
public class GamePreferences {

    public static GamePreferences gp;

    public GamePreferences() {
        gp = this;
    }

    private static Vector buttondims = Vector.Zero();

    public static void CalculateDims() {
        float hypot = (float) Math.sqrt((Game.g.getWindowWidth() * Game.g.getWindowWidth()) + (Game.g.getWindowHeight() * Game.g.getWindowHeight()));
        buttondims = new Vector((Game.g.getWindowWidth() / hypot) * Game.g.getWindowWidth() / 1400, (Game.g.getWindowHeight() / hypot) * Game.g.getWindowHeight() / 500);
    }

    public static Vector ButtonDims() {
        return buttondims;
    }
    
    private static HashMap<String,Object> valuesKeeper = new HashMap<>();

    public static Object getValuesKeeper(String Key) {
        return valuesKeeper.get(Key);
    }

    public static void setValuesKeeper(String Key,Object value) {
        valuesKeeper.put(Key, value);
    }
    
    
    
    

}
