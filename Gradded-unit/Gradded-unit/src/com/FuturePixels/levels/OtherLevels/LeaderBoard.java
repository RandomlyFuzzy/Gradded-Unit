/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.OtherLevels;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.levels.Menus.MainMenu;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.FileUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LeaderBoard extends ILevel {

    private static String FileURI = "resources/Savedata/Level";
    private static String EndURI = "Solo.txt";
    private String previousind = "";
    private static String Currentind = "Level1Solo";
    private ArrayList<String> times = new ArrayList<String>();

    /**
     *
     * @return
     */
    public static String getCurrentind() {
        return Currentind;
    }

    /**
     *
     * @param Currentind
     */
    public static void setCurrentind(String Currentind) {
        LeaderBoard.Currentind = Currentind;
    }

    /**
     *
     */
    public LeaderBoard() {
        super();
        System.out.println("com.game.levels.LeaderBoard.<init>()");
        setStopAudioOnStart(false);
    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/Images/backgrounds/background1.png");

        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.2f * (i % 5)) + 0.1f), ((0.1f * (i / 5)) + 0.1f)), ("Level" + ((i % 5) + 1)) + ((i >= 5) ? "Coop" : "Solo"), new HUDdelegate() {
                public void OnClick(Button b) {
                    LeaderBoard.setCurrentind(b.getMessage());
                }
            }));
        }
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));

        AddObject(new Mouse());
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        if (previousind != Currentind) {
            times = new ArrayList<String>();
            times.addAll(
                    FileUtils.GetFileSplit("Resources/savedata/" + Currentind + ".txt", "\n", true)
            );

            System.out.println("com.FuturePixels.levels.OtherLevels.LeaderBoard.Update() " + times.size());
            if (times.size() > 0) {
                times.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (o2.equals(new String())) {
                            return -1;
                        }
                        if (o1.equals(new String())) {
                            return 1;
                        }
                        return Double.parseDouble(o1) > Double.parseDouble(o2) ? 1 : -1;
                    }
                });
                if (times.size() > 10) {
                    String set = "";
                    for (int i = 0; i < 10; i++) {
                        set += times.get(i) + "\n";
                    }
                    FileUtils.SetFileContence("Resources/savedata/" + Currentind + ".txt", set);
                }
            }
            previousind = Currentind;
        }
    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/backgrounds/background1.png"), Game.getWindowWidth(), 0, (Game.getWindowWidth() * -1), (Game.getWindowHeight()), null);
        float y = 0.3f;
        Font f = g.getFont();
        Font f2 = f.deriveFont(1, Game.WorldScale().getY() * 13);
        g.setFont(f2);
        if ((times.size() == 1 && !times.get(0).equals(new String()) && !Double.isNaN(Double.parseDouble(times.get(0)))) || times.size() > 1) {
            g.setColor(new Color(55, 55, 55, 150));

            g.setFont(f.deriveFont(1, f.getSize() + (Game.WorldScale().getY() * ((int) Math.pow(15 - 0, 2) / 10))));
            String s = times.get(0);
            String str = "No " + (0 + 1) + " Place with " + s + " secs";
            int w = (int) (g.getFontMetrics().stringWidth(str) * 1.05f);
            g.fillRect(
                    (int) ((Game.getWindowWidth() / 2) - w / 2),
                    (int) ((0.225f) * Game.getWindowHeight()),
                    (int) (w),
                    (int) (0.620f * Game.getWindowHeight()));
            w = (int) (g.getFontMetrics().stringWidth(Currentind) * 1.05f);
            g.drawString(Currentind, (Game.getWindowWidth() / 2 - (w / 2)), y);

            g.setColor(Color.WHITE);
            int inc = 0;
            for (int i = 0; i < 10; i++) {
                g.setFont(f.deriveFont(1, f.getSize() + (Game.WorldScale().getY() * ((int) Math.pow(15 - i, 2) / 10))));
                s = times.get(i);
                str = "No " + (i + 1) + " is Ricky in " + s + " secs";
                w = g.getFontMetrics().stringWidth(str);
                g.drawString(str,
                        Game.getWindowWidth() / 2 - w / 2,
                        (((i % 20) * 0.06f) + 0.3f) * Game.getWindowHeight());
                y += 0.03f;
            }
        }
        g.setFont(f);
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {

    }

    /**
     *
     */
    public void dispose() {
        super.dispose();
        Currentind = "Level2Solo";
        previousind = "Level1Solo";
        times = new ArrayList<String>();
    }
}
