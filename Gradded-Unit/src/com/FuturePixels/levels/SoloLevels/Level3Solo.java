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
import com.FuturePixels.levels.Menus.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Woolley 1748910
 * @author Liam Rickman
 */
public class Level3Solo extends ILevel {

    private Player player1;
    private Vector Cameraopos = Vector.Zero();

    public Level3Solo() {
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

        AddObject(player1).setPosition(100, 0);
        AddObject(new Flag(new MainMenu())).setPosition(new Vector(150, 200));
        //Adding Platforms
        AddObject(new PlatForm(new Vector(100, 100), 0)).GetSprite("/images/Platform/rock_platform_clean_01.png");
        AddObject(new DestroyingPlatForm(new Vector(300, 0), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new PlatForm(new Vector(475, -150), -0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new DestroyingPlatForm(new Vector(600, -350), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new PlatForm(new Vector(425, -550), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new DestroyingPlatForm(new Vector(150, -625), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new DestroyingPlatForm(new Vector(400, -850), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new DestroyingPlatForm(new Vector(601, -850), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new DestroyingPlatForm(new Vector(400, -1050), 0)).GetSprite("/images/Platform/wooden_platform_01.png");
        AddObject(new PlatForm(new Vector(200, -1200), 0.35)).GetSprite("/images/Platform/rock_platform_moss_01.png");

        //AddObject(new MovingPlatoform(new Vector(75, 0), 0, new Vector[]{
        //new Vector(75, 0), new Vector(150, 0), new Vector(0, 0)
        //}, 1)).GetSprite("/images/Platform/rock_platform_moss_01.png").UpdateBounds();
//        AddObject(new DebugObject());
        AddObject(new HUD());
        AddObject(new Lava());
//        AddObject(new ScrollingBackground());
//        LeaderBoard.AddTime(System.nanoTime());
//        Cameraopos = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getScaledWidth() / 2, Game.g.getScaledHeight() / 2));
//        play("/sounds/soung.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/Images/background.png"));
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
