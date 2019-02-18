package com.FuturePixels.levels;

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
public class Level1Coop extends ILevel {

    Image background;
    boolean Pressed = false, p2 = false;
    Player player1, player2;

    public Level1Coop() {
        super();
    }

    @Override
    public void init() {

        AddObject(new DebugObject());
        AddObject(new HUD());
        System.out.println("com.game.levels.level1.<init>()");
        AddObject(new PlatForm(new Vector(100, 50), (float) Math.PI * 0.25f / 3f));
        AddObject(new PlatForm(new Vector(100, 200), (float) Math.PI * 0.25f / 3f));
        AddObject(new PlatForm(new Vector(400, 200), 0));// (float) Math.PI * -0.25f / 3f));
        player1 = new Player();
        player2 = new Player();
        AddObject(player1);
        AddObject(player2);

        Game.toggleCursor();
        background = GetSprite("/Images/background.png");
//        LeaderBoard.AddTime(System.nanoTime());

    }

    @Override
    public void Update(ActionEvent ae) {

    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(background, 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
//        System.out.println("com.game.levels.Level1Solo.paintComponent()");
    }

    @Override
    public void keyPress(KeyEvent e) {
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP1()) {
                Pressed = true;
                player1.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(true);

            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(true);

            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(true);
            } else if (code == GamePreferences.gp.getKeyRightP2()) {
                Pressed = true;
                player2.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(true);

            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(true);

            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(true);
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
                Pressed = true;
                player1.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(false);

            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(false);

            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(false);
            } else if (code == GamePreferences.gp.getKeyRightP2()) {
                Pressed = true;
                player2.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(false);

            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(false);

            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(false);
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
