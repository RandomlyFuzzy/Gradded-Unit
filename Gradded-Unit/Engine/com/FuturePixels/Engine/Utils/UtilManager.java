/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Engine.Utils;

import com.FuturePixels.Engine.Utils.imageUtils;
import com.FuturePixels.Engine.Utils.MusicUtils;
import com.FuturePixels.Drawables.Menus.GamePreferences;

/**
 *
 * @author RandomlyFuzzy
 */
public class UtilManager {

    public UtilManager() {
        new imageUtils();
        new MusicUtils();
        new GamePreferences();
    }

    public static void FindUseClass(int depth) {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.err.println("FindUseClass() " + e.getStackTrace()[depth == -1 ? e.getStackTrace().length - 1 : depth]);
        }
    }

}
