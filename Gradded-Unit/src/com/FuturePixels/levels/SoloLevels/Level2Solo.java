package com.FuturePixels.levels.SoloLevels;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.Drawables.Levels.DebugObject;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.Engine.Utils.imageUtils;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Level2Solo extends ILevel {

    private Player player1;
    private Vector Cameraopos = Vector.Zero();

    public Level2Solo() {
        super();
        setSimpleCollison(false);
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageUtils.T.setImage("API/cat", Game.GetLevel().getFromApi("http://aws.random.cat/meow"));
            }
        }).start();
        player1 = new Player();
        //Adding Platforms
        AddObject(player1).setPosition(0, -50);
        AddObject(new Flag(new Level3Solo())).setPosition(new Vector(500, -3550));
        AddObject(new PlatForm(new Vector(75, 0), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-200, -75), 0.35)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-425, -140), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-90, -330), -0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(150, -425), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-170, -600), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-400, -600), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-300, -800), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-150, -800), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-00, -1000), -0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-200, -1200), 0.45)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-450, -1250), 0.45)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-300, -1450), -0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-100, -1550), -0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-250, -1750), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-500, -1800), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-400, -2000), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-150, -2150), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-350, -2300), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-200, -2500), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-450, -2700), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-350, -2850), 0)).GetSprite("/images/Platform/rock_platform_moss_00.png");
        AddObject(new PlatForm(new Vector(-200, -3000), 0)).GetSprite("/images/Platform/rock_platform_moss_00.png");
        AddObject(new PlatForm(new Vector(-50, -3150), 0)).GetSprite("/images/Platform/rock_platform_moss_00.png");
        AddObject(new PlatForm(new Vector(100, -3300), 0)).GetSprite("/images/Platform/rock_platform_moss_00.png");
        AddObject(new PlatForm(new Vector(250, -3450), 0)).GetSprite("/images/Platform/rock_platform_moss_00.png");
        AddObject(new PlatForm(new Vector(500, -3500), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
//        AddObject(new DebugObject());
        AddObject(new Lava());
        AddObject(new HUD());

//        AddObject(new ScrollingBackground());
        Transform.setOffsetTranslation(new Vector((Game.g.getWindowWidth()) / 2, 0));
//        play("/sounds/soung.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/Images/WIP Background.png"));
    }

    @Override
    public void Update(ActionEvent ae) {

        if (player1 == null) {
            return;
        }

    }

    @Override
    public void Draw(Graphics2D g) {
//        System.out.println("com.game.levels.Level1Solo.paintComponent()");
    }

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
