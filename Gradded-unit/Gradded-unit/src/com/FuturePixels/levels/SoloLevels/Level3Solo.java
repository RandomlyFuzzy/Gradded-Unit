package com.FuturePixels.levels.SoloLevels;

import com.Liamengine.Engine.Components.Transform;
import com.FuturePixels.Drawables.Levels.DebugObject;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.imageUtils;
import com.FuturePixels.levels.Menus.MainMenu;
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
public class Level3Solo extends ILevel {

    private Player player1;
        private Vector StartingPosition = Vector.Zero();

    /**
     *
     */
    public Level3Solo() {
        super();
        setSimpleCollison(false);
        setStopAudioOnStart(true);
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

        AddObject(player1).setPosition(100, 0);
        AddObject(new Flag(new Level4Solo())).setPosition(new Vector(200, -4450));
        //Setting platform sprites
        BufferedImage moss1 = GetSprite("/images/platform/rock_platform_moss_01.png");
        BufferedImage wood1 = GetSprite("/images/platform/wooden_platform_01.png");
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");

        //Adding Platforms
        AddObject(new PlatForm(new Vector(100, 100), 0)).setLastimage(clean1);
        AddObject(new DestroyingPlatForm(new Vector(300, 0), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(475, -150), -0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(600, -350), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(425, -550), 0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(150, -625), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(400, -850), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(601, -850), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(400, -1050), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(200, -1200), 0.20)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(300, -1400), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(600, -1600), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(500, -1800), 0.15)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(250, -2000), 0.10)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(600, -2050), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(500, -2250), 0.15)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(225, -2450), 0.15)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(350, -2650), -0.10)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(550, -2850), -0.10)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(450, -3000), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(200, -3150), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(500, -3300), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(150, -3450), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(600, -3600), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(500, -3800), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(300, -4000), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(600, -4100), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(400, -4200), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(200, -4400), 0)).setLastimage(clean1);

        //AddObject(new MovingPlatoform(new Vector(75, 0), 0, new Vector[]{
        //new Vector(75, 0), new Vector(150, 0), new Vector(0, 0)
        //}, 1)).GetSprite("/images/Platform/rock_platform_moss_01.png").UpdateBounds();
//        AddObject(new DebugObject());
        AddObject(new HUD());
        AddObject(new Lava());
//        play("/Sounds/Lvl3Song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/images/backgrounds/level2.png"));
        StartingPosition = new Vector((Game.getScaledWidth() * 0.4f) / 2, 0);
        Transform.setOffsetTranslation(StartingPosition);
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {

        if (player1 == null) {
            return;
        }

    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {

        Vector pos = Transform.getOffsetTranslation();
        float xpos = (pos.getX() - Game.getWindowWidth() * 1.3f / 5) - StartingPosition.getX();
        float ypos = (((pos.getY()) / 5) - Game.getScaledHeight()* 1.2f) - StartingPosition.getY();
        g.drawImage(getBackgroundimage(), (int) (xpos), (int) (ypos), (int) (Game.getWindowWidth()* 1.3f), (int) (Game.getScaledHeight() * 2.3f), null);
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

            //switches are good for definate cases not for what we need though
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_SPACE:
//                    Pressed = true;
//                    player1.setUp(true);
//                    break;
//                case KeyEvent.VK_DOWN:
//                    player1.setDown(true);
//                    break;
//                case KeyEvent.VK_LEFT:
//                    player1.setLeft(true);
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    player1.setRight(true);
//                    break;
//            }
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

//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_SPACE:
//                    Pressed = true;
//                    player1.setUp(false);
//                    break;
//                case KeyEvent.VK_DOWN:
//                    player1.setDown(false);
//                    break;
//                case KeyEvent.VK_LEFT:
//                    player1.setLeft(false);
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    player1.setRight(false);
//                    break;
//            }
        } catch (Exception ex) {

        }

    }

}
