/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Utils.FileUtils;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Utils.MusicUtils;
import com.FuturePixels.levels.Menus.MainMenu;
import com.Liamengine.Engine.Components.SpriteSheet;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Flag extends IDrawable {

    private SpriteSheet flag = new SpriteSheet(0, 0, 75, 75);
    private float ind = 0;
    private ILevel next;
    private String seed = "";

    /**
     *
     * @param nextLevel
     */
    public Flag(ILevel nextLevel) {
        super();
        next = nextLevel;
    }

    /**
     *
     * @param nextLevel
     * @param seed
     */
    public Flag(ILevel nextLevel, int seed) {
        super();
        next = nextLevel;
        this.seed = "" + seed;
    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/images/flaga.png");
        flag.inputImage(getLastImage());
        setSpriteWidth(75);
        setSpriteHeight(75);
    }

    /**
     *
     */
    @Override
    public void doMove() {
        flag.IncrementX(0.1f);
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImageAsSpriteSheet(g,flag);
    }

    boolean ran = false;

    /**
     *
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {

        if (im instanceof Player && !ran) {
            String Slim = Level().getClass().toString().substring(Level().getClass().toString().lastIndexOf(".") + 1);
            if (!seed.equals("")) {
                FileUtils.AppendToFile("resources/savedata/coop/" + seed + ".txt", "" + String.format("%.2f", (float)Level().getTime()) + "\n");
            } else {
                FileUtils.AppendToFile("resources/savedata/" + Slim + ".txt", "" + String.format("%.2f", (float)Level().getTime()) + "\n");
            }
            Level().play("/sounds/win1.wav");
            Level().play("/sounds/levelcompleate_" + new Random().nextInt(3) + ".wav");
            Player.setLock(true);
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    MusicUtils.StopAllSounds();
                    LevelLoader.LoadLevel(next.getClass().newInstance());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }).start();
        } else {
            return;
        }
        ran = true;
    }
}
