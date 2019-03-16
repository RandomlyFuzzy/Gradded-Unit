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
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Rickman
 */
public class Level4Solo extends ILevel {

    private Player player1;
    private Vector Cameraopos = Vector.Zero();

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
                imageUtils.T.setImage("API/cat", Game.GetLevel().getFromApi("http://aws.random.cat/meow"));
            }
        }).start();
        player1 = new Player();

        AddObject(player1).setPosition(0, -50);
        AddObject(new Flag(new MainMenu())).setPosition(new Vector(950, -3300));
        //Adding Platforms
        AddObject(new PlatForm(new Vector(0, 0), 0)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new MovingPlatoform(new Vector(300, -150), 0,new Vector[]{new Vector(200,-150),new Vector(700,-150)},1.5f)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(800, -300), 0)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(400, -500), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(700, -700), -0.30)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(250, -850), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new DestroyingPlatForm(new Vector(500, -1000), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new DestroyingPlatForm(new Vector(650, -1200), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new DestroyingPlatForm(new Vector(400, -1400), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new DestroyingPlatForm(new Vector(100, -1550), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new MovingPlatoform(new Vector(200, -1700), 0,new Vector[]{new Vector(200,-1700),new Vector(600,-1700)},1.5f)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new MovingPlatoform(new Vector(600, -1850), 0,new Vector[]{new Vector(600,-1850),new Vector(1000,-1850)},1)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new MovingPlatoform(new Vector(500, -2000), 0,new Vector[]{new Vector(500,-2000),new Vector(0,-2000)},2)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new MovingPlatoform(new Vector(1100, -2150), 0,new Vector[]{new Vector(600,-2150),new Vector(1100,-2150)},1.5f)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(500, -2300), 0)).GetSprite("/images/Platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(275, -2500), 0)).GetSprite("/images/Platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(100, -2700), 0)).GetSprite("/images/Platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(300, -2900), 0)).GetSprite("/images/Platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(600, -3100), -0.25)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new MovingPlatoform(new Vector(0, -3250), 0,new Vector[]{new Vector(0,-3250),new Vector(400,-3250)},1.5f)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new DestroyingPlatForm(new Vector(600, -3450), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new PlatForm(new Vector(900, -3250), 0)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        
        //AddObject(new MovingPlatoform(new Vector(75, 0), 0, new Vector[]{
        //new Vector(75, 0), new Vector(150, 0), new Vector(0, 0)
        //}, 1)).GetSprite("/images/Platform/rock_platform_moss_01.png").UpdateBounds();
//        AddObject(new DebugObject());
        AddObject(new HUD());
        AddObject(new Lava());
        play("/Sounds/song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/Images/backgrounds/background.png"));
        Transform.setOffsetTranslation(new Vector((Game.getScaledWidth() * 0.4f) / 2, 0));
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
//        System.out.println("com.game.levels.Level1Solo.paintComponent()");
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
