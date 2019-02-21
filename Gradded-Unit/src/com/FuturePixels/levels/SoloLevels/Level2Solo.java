package com.FuturePixels.levels.SoloLevels;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.Drawables.Levels.DebugObject;
import com.FuturePixels.MainClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.MainClasses.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Level2Solo extends ILevel {

    private Player player1;
    private Vector Cameraopos = Vector.Zero();

    public Level2Solo() {
        super();
    }

    @Override
    public void init() {
        // (float) Math.PI * -0.25f / 3f));
        //Adding Platforms
//        AddObject(new DebugObject());
        System.out.println("com.game.levels.level1.<init>()");
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
        AddObject(new PlatForm(new Vector(-250, -1700), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-500, -1750), 0.25)).GetSprite("/images/Platform/rock_platform_moss_01.png");
        AddObject(new PlatForm(new Vector(-400, -1950), 0)).GetSprite("/images/Platform/rock_platform_moss_01.png");
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
        AddObject(new Flag()).setPosition(new Vector(500, -3550));

        player1 = new Player();
        AddObject(player1).setPosition(500, -3600);
        AddObject(new HUD());
//        AddObject(new ScrollingBackground());

        Game.toggleCursor();
//        LeaderBoard.AddTime(System.nanoTime());
//        Cameraopos = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getWindowWidth() / 2, Game.g.getWindowHeight() / 2));

    }

    @Override
    public void Update(ActionEvent ae) {

        if (player1 == null) {
            return;
        }
//        if (-player1.getPosition().getX() != Cameraopos.getX()-Game.g.getWindowWidth() / 2) {
//            Cameraopos.setX(-player1.getPosition().getX()+Game.g.getWindowWidth() / 2);
//        }
//        if (-player1.getPosition().getY() > Cameraopos.getY()-Game.g.getWindowHeight() / 2) {
//            Cameraopos.setY(-player1.getPosition().getY()+Game.g.getWindowHeight() / 2);
//        }
//        //screen scroller
//        Cameraopos.setY(temp.getY()+Game.g.getDelta()*30f);
        Cameraopos = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getWindowWidth() / 2, Game.g.getWindowHeight() / 2));
        Transform.setOffsetTranslation(Cameraopos);
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/background.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
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
