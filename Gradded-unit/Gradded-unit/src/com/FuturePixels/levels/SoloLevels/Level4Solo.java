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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Rickman
 */
public class Level4Solo extends ILevel {

    private Player player1;
    private Vector Cameraopos = Vector.Zero();
           private Vector StartingPosition = Vector.Zero();

    /**
     *
     */
    public Level4Solo() {
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

        AddObject(player1).setPosition(0, -50);
        AddObject(new Flag(new Level5Solo())).setPosition(new Vector(950, -3300));
        //Setting platform sprites
        BufferedImage moss1 = GetSprite("/images/platform/rock_platform_moss_01.png");
        BufferedImage wood1 = GetSprite("/images/platform/wooden_platform_01.png");
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");
        BufferedImage clean2 = GetSprite("/images/platform/rock_platform_clean_02.png");

        //Adding Platforms
        AddObject(new PlatForm(new Vector(0, 0), 0)).setLastimage(clean1);
        AddObject(new MovingPlatform(new Vector(300, -150), 0, new Vector[]{new Vector(200, -150), new Vector(700, -150)}, 1.5f, true))
                .GetSprite("/images/Platform/floatingPlatform.png", 150, 150);
        AddObject(new PlatForm(new Vector(800, -300), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(400, -500), 0.25)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(700, -700), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(250, -850), 0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(500, -1000), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(650, -1200), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(400, -1400), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(100, -1550), 0)).setLastimage(wood1);
        AddObject(new MovingPlatform(new Vector(200, -1700), 0, new Vector[]{new Vector(200, -1700), new Vector(600, -1700)}, 1.5f, true))
                .GetSprite("/images/Platform/floatingPlatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(600, -1850), 0, new Vector[]{new Vector(600, -1850), new Vector(1000, -1850)}, 1, true))
                .GetSprite("/images/Platform/floatingPlatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(500, -2000), 0, new Vector[]{new Vector(500, -2000), new Vector(0, -2000)}, 2, true))
                .GetSprite("/images/Platform/floatingPlatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(1100, -2150), 0, new Vector[]{new Vector(600, -2150), new Vector(1100, -2150)}, 1.5f, true))
                .GetSprite("/images/Platform/floatingPlatform.png", 150, 150);
        AddObject(new PlatForm(new Vector(500, -2300), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(275, -2500), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(100, -2700), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(300, -2900), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(600, -3100), -0.25)).setLastimage(clean1);
        AddObject(new MovingPlatform(new Vector(0, -3250), 0, new Vector[]{new Vector(0, -3250), new Vector(400, -3250)}, 1.5f, true))
                .GetSprite("/images/Platform/floatingPlatform.png", 150, 150);
        AddObject(new DestroyingPlatForm(new Vector(600, -3450), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(900, -3250), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(795, -3340), 0)).setLastimage(clean2);

        //AddObject(new MovingPlatform(new Vector(75, 0), 0, new Vector[]{
        //new Vector(75, 0), new Vector(150, 0), new Vector(0, 0)
        //}, 1)).GetSprite("/images/Platform/rock_platform_moss_01.png").UpdateBounds();
//        AddObject(new DebugObject());
        AddObject(new HUD());
        AddObject(new Lava());
        play("/Sounds/Lvl4Song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/Images/backgrounds/level3.png"));
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
