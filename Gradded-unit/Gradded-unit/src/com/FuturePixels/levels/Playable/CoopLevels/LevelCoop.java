package com.FuturePixels.levels.Playable.CoopLevels;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.levels.Menus.MainMenu;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.FileUtils;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.util.Random;
import javax.sound.sampled.Clip;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LevelCoop extends ILevel {

    private Player player1;
    private Player player2;

    /**
     *
     */
    public LevelCoop() {
        super();
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

        setSimpleCollison(false);
        // (float) Math.PI * -0.25f / 3f));
        player1 = new Player();
        player2 = new Player();
//        player1.setScale(new Vector(0.3f,0.5f));
        //Adding Platforms
        
        int seed = Integer.parseInt(FileUtils.GetFileContence("resources/data/coopseed").trim());
        Random r = new Random(seed);

        int i = 0;
        int start = ((r.nextInt() % (Game.getWindowWidth() / 5)) - 200) + 100;
        AddObject(player1).setPosition(start-200, -50);
        AddObject(player2).setPosition(start-200, -50);
        
        AddObject(new PlatForm(new Vector(start-200, 0), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");

        for (i = 1; i < 20; i += 1) {
            AddObject(new PlatForm(new Vector(((r.nextInt() % (Game.getWindowWidth() / 5)) - 200) + 100, -i*200), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");;
        }

        int last = ((r.nextInt() % (Game.getWindowWidth() / 5)) - 200) + 100;
        AddObject(new PlatForm(new Vector(last, -(i*200)), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");;
        AddObject(new Flag(new MainMenu(new Vector(0, Game.getWindowHeight())), seed)).setPosition(new Vector(last, -(i*200 + 50)));

//        AddObject(new DebugObject());
        AddObject(new HUD());
        AddObject(new Lava());
//        AddObject(new ScrollingBackground());
//        LeaderBoard.AddTime(System.nanoTime());
//        Cameraopos = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getScaledWidth() / 2, Game.g.getScaledHeight() / 2));
        Transform.setOffsetTranslation(new Vector((Game.getWindowWidth() / 2f), 0));
        play("/sounds/lvl4song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        setBackgroundimage(GetSprite("/images/backgrounds/level"+(r.nextInt(5))+".png"));

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
//        
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
            if (code == GamePreferences.gp.getKeyRightP2()) {
                player2.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(true);

            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(true);

            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(true);
            }

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
            if (code == GamePreferences.gp.getKeyRightP2()) {
                player2.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(false);

            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(false);

            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(false);
            }
        } catch (Exception ex) {

        }

    }

}
