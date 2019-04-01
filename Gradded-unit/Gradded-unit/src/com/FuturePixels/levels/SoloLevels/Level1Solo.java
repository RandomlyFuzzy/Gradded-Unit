package com.FuturePixels.levels.SoloLevels;

import com.Liamengine.Engine.Components.Transform;
import com.FuturePixels.Drawables.Levels.DebugObject;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.levels.Menus.MainMenu;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Rickman
 */
public class Level1Solo extends ILevel {

    private Player player1;
    private Vector StartingPosition;
    
    
    

    /**
     *
     */
    public Level1Solo() {
        super();
        setSimpleCollison(false);
    }

    /**
     *
     */
    @Override
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageUtils.T.setImage("/API/cat", Game.GetLevel().getOnlineImage("https://cataas.com/cat/says/better%20luck%20next%20time?size=50&color=blue"));
                Thread.currentThread()
                        .stop();
            }
        }).start();

        player1 = new Player();
        AddObject(player1).setPosition(0, -50);
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");
        //Adding Platforms
        AddObject(new PlatForm(new Vector(0, 0), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -150), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(100, -350), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(400, -500), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -650), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(375, -850), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(385, -1000), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(250, -1150), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(150, -1325), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(350, -1475), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -1650), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -1650), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(350, -1850), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(450, -2000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(325, -2200), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(450, -2400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -2600), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(250, -2800), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(300, -3000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -3200), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -3400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(450, -3600), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -3800), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(300, -4000), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(400, -4200), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(200, -4400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(300, -4600), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(400, -4800), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -5000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(50, -5200), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -5400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(650, -5600), 0)).setLastimage(clean1);
        AddObject(new HUD());
        AddObject(new Lava());
        AddObject(new Flag(new Level2Solo())).setPosition(new Vector(650, -5650));
//        AddObject(new DebugObject());

        StartingPosition = new Vector((Game.getScaledWidth() * .6f) / 2, 0);
        Transform.setOffsetTranslation(new Vector((Game.getScaledWidth() * .6f) / 2, 0));
        play("/Sounds/Lvl1Song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/Images/backgrounds/level0.png"));
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {

    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        
        Vector pos = Transform.getOffsetTranslation();
        float xpos = (pos.getX()-Game.getWindowWidth()*1.3f/5)-StartingPosition.getX();
        float ypos =(((pos.getY())/5)-Game.getWindowHeight()*1.7f)-StartingPosition.getY();
        g.drawImage(getBackgroundimage(), (int)(xpos),(int)(ypos),(int)(Game.getWindowWidth()*1.3f),(int)(Game.getWindowWidth()*1.7f),null);
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(true);

            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(true);

            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(true);
            }

        } catch (Exception ex) {

        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(false);

            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(false);

            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(false);
            }

        } catch (Exception ex) {

        }

    }

}
