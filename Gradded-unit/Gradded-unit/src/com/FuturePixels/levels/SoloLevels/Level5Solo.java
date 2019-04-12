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
public class Level5Solo extends ILevel {

    private Player player1;
    private Vector Cameraopos = Vector.Zero();
    private Vector StartingPosition = Vector.Zero();

    /**
     *
     */
    public Level5Solo() {
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

        //Player and Flag Objects
        AddObject(player1).setPosition(850, -50);
        AddObject(new Flag(new MainMenu(new Vector(0,Game.getWindowHeight())))).setPosition(new Vector(225, -6450));
        //Setting platform sprites
        BufferedImage moss1 = GetSprite("/images/platform/rock_platform_moss_01.png");
        BufferedImage wood1 = GetSprite("/images/platform/wooden_platform_01.png");
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");
        BufferedImage bouncy1 = GetSprite("/images/platform/bouncyplatform_01.png");

        //Adding Platforms
        AddObject(new PlatForm(new Vector(800, 0), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -100), 0.20)).setLastimage(moss1);
        AddObject(new bouncyPlatform(new Vector(200, -250), 0, new Vector[]{new Vector(200, -250), new Vector(200, -250)}, 1.5f)).setLastimage(bouncy1);;
        AddObject(new PlatForm(new Vector(450, -575), -0.20)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(800, -750), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(650, -950), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(450, -1150), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(250, -1350), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(450, -1550), -0.25)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(150, -1750), 0.20)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(550, -1900), -0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(750, -2100), 0)).setLastimage(wood1);
        AddObject(new MovingPlatform(new Vector(300, -2250), 0, new Vector[]{new Vector(100, -2250), new Vector(600, -2250)}, 1.5f, true))
                .GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new DestroyingPlatForm(new Vector(300, -2450), 0)).setLastimage(wood1);
        AddObject(new bouncyPlatform(new Vector(0, -2650), 0, new Vector[]{new Vector(0, -2650), new Vector(0, -2650)}, 1.5f)).setLastimage(bouncy1);
        AddObject(new bouncyPlatform(new Vector(250, -2975), 0, new Vector[]{new Vector(250, -2975), new Vector(250, -2975)}, 1.5f)).setLastimage(bouncy1);
        AddObject(new PlatForm(new Vector(500, -3275), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -3475), 0.25)).setLastimage(moss1);
        AddObject(new MovingPlatform(new Vector(0, -3625), 0, new Vector[]{new Vector(0, -3625), new Vector(450, -3625)}, 1.5f, true))
                .GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(1000, -3775), 0, new Vector[]{new Vector(1000, -3775), new Vector(550, -3775)}, 1.5f, true))
                .GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(-200, -3925), 0, new Vector[]{new Vector(450, -3925), new Vector(0, -3925)}, 1.5f, true))
                .GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new bouncyPlatform(new Vector(200, -4075), 0, new Vector[]{new Vector(200, -4075), new Vector(200, -4075)}, 1.5f)).setLastimage(bouncy1);
        AddObject(new PlatForm(new Vector(400, -4400), -0.25)).setLastimage(moss1);
        AddObject(new MovingPlatform(new Vector(600, -4400), 0, new Vector[]{new Vector(600, -4400), new Vector(600, -5000)}, 1.5f, true))
                .GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new PlatForm(new Vector(300, -5000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(100, -5200), 0.35)).setLastimage(moss1);
        AddObject(new bouncyPlatform(new Vector(300, -5400), 0, new Vector[]{new Vector(300, -5400), new Vector(300, -5400)}, 1.5f)).setLastimage(bouncy1);
        AddObject(new MovingPlatform(new Vector(600, -5700), 0, new Vector[]{new Vector(0, -5700), new Vector(600, -5700)}, 1.5f, true))
                .GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new PlatForm(new Vector(750, -5900), -0.25)).setLastimage(moss1);
        AddObject(new bouncyPlatform(new Vector(500, -6100), 0, new Vector[]{new Vector(500, -6100), new Vector(500, -6100)}, 1.5f)).setLastimage(bouncy1);
        AddObject(new PlatForm(new Vector(200, -6400), 0)).setLastimage(clean1);

//        AddObject(new DebugObject());
        AddObject(new HUD());
        AddObject(new Lava());
        play("/sounds/lvl5song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/images/backgrounds/level4.png"));
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
        float ypos = (((pos.getY()) / 5) - Game.getScaledHeight()* 1.5f) - StartingPosition.getY();
        g.drawImage(getBackgroundimage(), (int) (xpos), (int) (ypos), (int) (Game.getWindowWidth()* 1.3f), (int) (Game.getScaledHeight() * 2.7f), null);
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
