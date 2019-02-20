package com.FuturePixels.levels;

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
public class Level1Solo extends ILevel {

    Image background;
    boolean Pressed = false, p2 = false;
    Player player1;
    Vector temp = Vector.Zero();

    public Level1Solo() {
        super();
    }

    @Override
    public void init() {

//        AddObject(new DebugObject());
        System.out.println("com.game.levels.level1.<init>()");
        AddObject(new PlatForm(new Vector(100, 50), (float) Math.PI * 0.25f / 3f)).GetSprite("/images/Platform.png");
        AddObject(new PlatForm(new Vector(100, 200), (float) Math.PI * 0.1f)).GetSprite("/images/Platform.png");
        AddObject(new PlatForm(new Vector(400, 200), 0)).GetSprite("/images/Platform.png");// (float) Math.PI * -0.25f / 3f));
        player1 = new Player();
        AddObject(player1);
        AddObject(new HUD());
//        AddObject(new ScrollingBackground());

        Game.toggleCursor();
        background = GetSprite("/Images/background.png");
//        LeaderBoard.AddTime(System.nanoTime());
//        temp = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getWindowWidth() / 2, Game.g.getWindowHeight() / 2));

    }

    @Override
    public void Update(ActionEvent ae) {

        if (player1 == null) {
            return;
        }
//        if (-player1.getPosition().getX() != temp.getX()-Game.g.getWindowWidth() / 2) {
//            temp.setX(-player1.getPosition().getX()+Game.g.getWindowWidth() / 2);
//        }
//        if (-player1.getPosition().getY() > temp.getY()-Game.g.getWindowHeight() / 2) {
//            temp.setY(-player1.getPosition().getY()+Game.g.getWindowHeight() / 2);
//        }
//        //screen scroller
//        temp.setY(temp.getY()+Game.g.getDelta()*30f);
        temp = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getWindowWidth() / 2, Game.g.getWindowHeight() / 2));
        Transform.setOffsetTranslation(temp);
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
