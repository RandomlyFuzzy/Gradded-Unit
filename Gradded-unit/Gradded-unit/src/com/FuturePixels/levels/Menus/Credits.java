package com.FuturePixels.levels.Menus;

import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Vector;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.FileUtils;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Utils.MusicUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import javax.sound.sampled.Clip;

/**
 * Credits show a scrolling menu with developer names and information about the game
 * @author Liam Woolley 1748910
 */
public class Credits extends ILevel {

    private double i = 0;
    private int ind = 0;
    private boolean changed = false;
    private boolean once = true;
    private String[] credits;

    /**
     *
     */
    public Credits() {
        super();

        //Stops any audio currently playing
        setStopAudioOnStart(true);
        setSimpleCollison(true);
    }

    /**
     *
     */
    @Override
    public void init() {
        //Adds a mouse object
        AddObject(new Mouse());
        //Sets the background image to level 1
        setBackgroundimage(GetSprite("/images/backgrounds/level0.png"));
        //Loads the credits file from the resources folder and stores it in a variable
        credits = FileUtils.GetFileSplit("resources/data/credits.txt", "\n");
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        //Scrolls the bcakground down constantly
        i += Game.WorldScale().getY() * ILevel.getDelta();
    }

    /**
     * Draws the 
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {

        //Plays the credit song on a loop
        play("/sounds/creditsong.wav", 0, Clip.LOOP_CONTINUOUSLY);

        int val = (int) ((Math.sin(i) + 1f) * 127) + 1;
        int val2 = (int) ((Math.cos(i) + 1f) * 100f);
        if (val >= 253 && once) {
            ind++;
            ind %= 4;
            changed = true;
            once = false;

        } else if (val >= 253) {
        } else if (changed) {
            setBackgroundimage(GetSprite("/images/backgrounds/level" + (ind) + ".png"));
            changed = false;
            once = true;
        }
        g.setColor(new Color(0, 0, 0, val));
        g.fillRect(0, 0, Game.getWindowWidth(), Game.getWindowHeight());
        g.setColor(new Color(55 + val2, 55 + val2, 55 + val2));

        for (int j = 0; j < credits.length; j++) {
            int offset = g.getFontMetrics().stringWidth(credits[j]);
            g.drawString(credits[j], Game.getWindowWidth() / 2 - offset / 2, (int) ((Game.getWindowHeight() + (int) (j * g.getFont().getSize())) - (i * 50)));

            if (j != credits.length - 1) {
                continue;
            }
            if ((int) ((Game.getWindowHeight() + (int) (j * g.getFont().getSize())) - (i * 50)) < -Game.getScaledHeight()/4) {
                LevelLoader.LoadLevel(new MainMenu());
            }
        }

        //Draws text telling the user how to exit the game
        Font f = g.getFont();
        g.setFont(f.deriveFont(f.getSize() * 0.6f));
        g.drawString("Esc to exit", 20, Game.getWindowHeight() - g.getFont().getSize() * 2);
        g.setFont(f);
    }

    /** 
     * If the player presses ESCAPE the music will end and the main menu will be loaded
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            MusicUtils.StopAllSounds();
            LevelLoader.LoadLevel(new MainMenu(Vector.Zero()));
        }
    }

    /**
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {

    }

}
