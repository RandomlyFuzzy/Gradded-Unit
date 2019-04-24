/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Utils.FileUtils;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Utils.MusicUtils;
import com.Liamengine.Engine.Components.SpriteSheet;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.logging.Logger;

/**
 *The flag is situated at the end of the level and when the player collides with
 *it, the next level starts.
 * @author Liam Woolley 1748910
 */
public class Flag extends IDrawable {

    private SpriteSheet flag = new SpriteSheet(0, 0, 75, 75);
    private float ind = 0;
    private ILevel next;
    private String seed = "";
    private boolean ran = false;

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
     *Initialises flag, image and size
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
     *Updates image every frame
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImageAsSpriteSheet(g,flag);
    }


    /**
     *When player has collided with flag, takes time taken for current level, 
     *plays sounds, stops player movement, waits 2 seconds and then loads next level
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
            //Play sounds, lock player movement
            Level().play("/sounds/win1.wav");
            Level().play("/sounds/levelcompleate_" + new Random().nextInt(3) + ".wav");
            Player.setLock(true);
            new Thread(() -> {
                try {
                    //Wait 2 seconds, stop current music, load next level
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
