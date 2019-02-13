package com.FuturePixels.levels;

import com.FuturePixels.Utils.ILevel;
import com.FuturePixels.characters.Player;
import com.FuturePixels.characters.Cookie;
import com.FuturePixels.characters.PlatForm;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.game.Game;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Level1 extends ILevel {

    Image background;
    private int move = 0;
    boolean Pressed = false, p2 = false;
    IDrawable player;

    public Level1(Game theGame) {
        super(theGame);
        System.out.println("com.game.levels.level1.<init>()");
        player = new Player();
        Add(player);
        Add(new PlatForm());
    }

    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        checkCollisions();
        movement();

        this.repaint();
    }

    public void init() {
        super.init();

        background = GetSprite("/Images/background.png");

        LeaderBoard.AddTime(System.nanoTime());
    }

    public void movement() {
        super.movement();
    }

    private void checkCollisions() {
        super.checkCollionsions();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
//        System.out.println("com.game.levels.Level1.paintComponent()");
        DrawObjs(g);
        g.dispose();
    }

    public void DrawObjs(Graphics g) {
        super.DrawObjs(g);
    }

    @Override
    public void keyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                Pressed = true;
                move |= 1;
                break;
            case KeyEvent.VK_DOWN:
                move |= 2;
                break;
            case KeyEvent.VK_LEFT:
                move |= 4;
                break;
            case KeyEvent.VK_RIGHT:
                move |= 8;
                break;
        }
        ((Player) player).move(move);
        if (Pressed) {
        }
    }

    @Override
    public void keyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                move -= 1;
                Pressed = false;
                break;
            case KeyEvent.VK_DOWN:
                move -= 2;
                break;
            case KeyEvent.VK_LEFT:
                move -= 4;
                break;
            case KeyEvent.VK_RIGHT:
                move -= 8;
                break;
        }

        System.out.println("com.game.levels.level1.TAdapter.keyReleased() " + move);
        ((Player) player).move(move);
    }

}
