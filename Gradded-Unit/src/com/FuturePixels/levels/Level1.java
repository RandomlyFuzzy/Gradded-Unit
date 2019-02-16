package com.FuturePixels.levels;

import com.FuturePixels.Components.DebugComponent;
import com.FuturePixels.Utils.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Level1 extends ILevel {

    Image background;
    boolean Pressed = false, p2 = false;
    Player player;

    public Level1() {
        super();
    }

    @Override
    public void init() {

        System.out.println("com.game.levels.level1.<init>()");
        AddObject(new PlatForm(new Vector(100, 200), (float) Math.PI * 0.25f / 3f));
        AddObject(new PlatForm(new Vector(400, 200), (float) Math.PI * -0.25f / 3f));
        AddObject(new DebugComponent());
        player = new Player();
        AddObject(player);
        AddObject(new HUD());

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
//        System.out.println("com.game.levels.Level1.paintComponent()");
    }

    @Override
    public void keyPress(KeyEvent e) {
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    Pressed = true;
                    player.setUp(true);
                    break;
                case KeyEvent.VK_DOWN:
                    player.setDown(true);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setRight(true);
                    break;
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void keyRelease(KeyEvent e) {
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    Pressed = true;
                    player.setUp(false);
                    break;
                case KeyEvent.VK_DOWN:
                    player.setDown(false);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setLeft(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setRight(false);
                    break;
            }
        } catch (Exception ex) {

        }

    }

}
