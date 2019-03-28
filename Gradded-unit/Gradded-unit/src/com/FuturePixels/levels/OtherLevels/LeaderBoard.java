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
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

    private String previousind = "";
    private static String Currentind = "Level4";
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
        GetSprite("/images/backgrounds/leaderboard.png");
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.15f)), ((0.15f * (i % 6)) + 0.15f)), ("Level " + ((i % 5) + 1)) , new HUDdelegate() {
                public void OnClick(Button b) {
                    LeaderBoard.setCurrentind(b.getMessage().replace(" ",""));
                }
            }));
        }

        setBackgroundimage(GetSprite("/Images/backgrounds/leaderboard.png"));
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
                    FileUtils.GetFileSplit("Resources/savedata/" + Currentind + "solo.txt", "\n", true)
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
        float y = 0.3f;
        Font f = g.getFont();
        Font f2 = f.deriveFont(1, Game.WorldScale().getY() * 55);
        g.setFont(f2);
        int w = (int) (g.getFontMetrics().stringWidth(Currentind.replace("solo","")) * 1.05f);
        g.drawString(Currentind.replace("solo",""), (int) ((Game.getWindowWidth() / 2) - w / 2),
                (int) ((0.205f) * Game.getWindowHeight()));
        f2 = f.deriveFont(1, Game.WorldScale().getY() * 10);
        g.setFont(f2);
        if ((times.size() == 1 && !times.get(0).equals(new String()) && !Double.isNaN(Double.parseDouble(times.get(0)))) || times.size() > 1) {
            g.setColor(new Color(55, 55, 55, 150));

            g.setFont(f.deriveFont(1, f.getSize() + (Game.WorldScale().getY() * ((int) Math.pow(15 - 0, 2) / 10))));
            String s = times.get(0);
            String str = "No " + (0 + 1) + " Place with " + s + " secs";
            w = (int) (g.getFontMetrics().stringWidth(str) * 1.05f);
            g.fillRect(
                    (int) ((Game.getWindowWidth() / 2) - w / 2),
                    (int) ((0.225f) * Game.getWindowHeight()),
                    (int) (w),
                    (int) ((0.650f * Game.getWindowHeight()) * ((float) (10 > times.size() ? times.size() : 10f) / 10f)));
            w = (int) (g.getFontMetrics().stringWidth(Currentind) * 1.05f);
            g.drawString(Currentind, (Game.getWindowWidth() / 2 - (w / 2)), y);

            g.setColor(Color.WHITE);
            int inc = 0;
            for (int i = 0; i < (10 > times.size() ? times.size() : 10); i++) {
                g.setFont(f.deriveFont(1, f.getSize() + (Game.WorldScale().getY() * ((int) Math.pow(15 - i, 2) / 10))));
                s = times.get(i);
                str = "No. " + (i + 1) + ": " + s + " secs";
                w = g.getFontMetrics().stringWidth(str);
                g.drawString(str,
                        Game.getWindowWidth() / 20,
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
        Currentind = "Level2";
        previousind = "Level1";
        times = new ArrayList<String>();
    }
}
