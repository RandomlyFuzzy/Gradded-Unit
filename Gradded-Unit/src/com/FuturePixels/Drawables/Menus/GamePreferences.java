package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Vector;
import java.awt.event.KeyEvent;

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
    private int KeyLeftP1 = KeyEvent.VK_A, KeyRightP1 = KeyEvent.VK_D, KeyJumpP1 = KeyEvent.VK_SPACE, KeyDropP1 = KeyEvent.VK_S;
    private int KeyLeftP2 = KeyEvent.VK_LEFT, KeyRightP2 = KeyEvent.VK_RIGHT, KeyJumpP2 = KeyEvent.VK_UP, KeyDropP2 = KeyEvent.VK_DOWN;

    public int getKeyLeftP1() {
        return KeyLeftP1;
    }

    public void setKeyLeftP1(int KeyLeftP1) {
        this.KeyLeftP1 = KeyLeftP1;
    }

    public int getKeyRightP1() {
        return KeyRightP1;
    }

    public void setKeyRightP1(int KeyRightP1) {
        this.KeyRightP1 = KeyRightP1;
    }

    public int getKeyJumpP1() {
        return KeyJumpP1;
    }

    public void setKeyJumpP1(int KeyJumpP1) {
        this.KeyJumpP1 = KeyJumpP1;
    }

    public int getKeyDropP1() {
        return KeyDropP1;
    }

    public void setKeyDropP1(int KeyDropP1) {
        this.KeyDropP1 = KeyDropP1;
    }

    public int getKeyLeftP2() {
        return KeyLeftP2;
    }

    public void setKeyLeftP2(int KeyLeftP2) {
        this.KeyLeftP2 = KeyLeftP2;
    }

    public int getKeyRightP2() {
        return KeyRightP2;
    }

    public void setKeyRightP2(int KeyRightP2) {
        this.KeyRightP2 = KeyRightP2;
    }

    public int getKeyJumpP2() {
        return KeyJumpP2;
    }

    public void setKeyJumpP2(int KeyJumpP2) {
        this.KeyJumpP2 = KeyJumpP2;
    }

    public int getKeyDropP2() {
        return KeyDropP2;
    }

    public void setKeyDropP2(int KeyDropP2) {
        this.KeyDropP2 = KeyDropP2;
    }

    public static Vector ButonDims() {
        float hypot = (float) Math.sqrt((Game.g.getWindowWidth() * Game.g.getWindowWidth()) + (Game.g.getWindowHeight() * Game.g.getWindowHeight()));
        return new Vector((Game.g.getWindowWidth() / hypot) * Game.g.getWindowWidth() / 1400, (Game.g.getWindowHeight() / hypot) * Game.g.getWindowHeight() / 500);
    }
}
