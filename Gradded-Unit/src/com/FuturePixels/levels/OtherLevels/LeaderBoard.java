/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.OtherLevels;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Utils.LevelLoader;
import com.FuturePixels.Engine.Components.Vector;
import java.awt.Color;
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
    private int previousind = 0;
    private static int Currentind = 1;
    private ArrayList<Double> times = new ArrayList<Double>();

    public static int getCurrentind() {
        return Currentind;
    }

    public static void setCurrentind(int Currentind) {
        LeaderBoard.Currentind = Currentind;
    }

    public LeaderBoard() {
        super();
        System.out.println("com.game.levels.LeaderBoard.<init>()");
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        GetSprite("/Images/background.png");

        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.15f * (i % 6)) + 0.1f), ((0.1f * (i / 6)) + 0.1f)), ("Level" + (i + 1)), new HUDAbstract() {
                public void OnClick(Button b) {
                    String mess = b.getMessage().substring(5);
                    System.out.println(".OnClick() " + mess);
                    LeaderBoard.setCurrentind(Integer.parseInt(mess));
                }
            }));
        }
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new Mouse());
    }

    @Override
    public void Update(ActionEvent ae) {
        if (previousind != Currentind) {
            try {
                times = new ArrayList<Double>();
                System.out.println("com.FuturePixels.levels.OtherLevels.LeaderBoard.Update() " + FileURI + Currentind + EndURI);
                File file = new File(FileURI + Currentind + EndURI);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileReader fis = new FileReader(file);
                Scanner scan = new Scanner(fis);
                while (scan.hasNextLine()) {
                    times.add(Double.parseDouble(scan.nextLine()));
                }
                times.sort(new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return o1 > o2 ? 1 : -1;
                    }
                });

            } catch (IOException ex) {
                Logger.getLogger(LeaderBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            previousind = Currentind;
        }
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/WIP Background.png"), Game.g.getWindowWidth(), 0, (Game.g.getWindowWidth() * -1), (Game.g.getWindowHeight()), null);
        float y = 0.3f;
        g.setColor(new Color(55,55,55,150));
        g.fillRect((int) ((0.03f) * Game.g.getWindowWidth()), (int) ((0.235f) * Game.g.getWindowHeight()), (int) ((((times.size() / 20) * 0.13f)+0.13f) * Game.g.getWindowWidth()), (int) (((((times.size() >= 20f ? 20f : times.size())) * 0.0295f)) * Game.g.getWindowHeight()));
        g.setColor(Color.WHITE);
        for (int i = 0; i < times.size(); i++) {
            Double s = times.get(i);
            g.drawString("No " + (i + 1) + " Place with " + s + " secs", (((i / 20) * 0.13f) + 0.03f) * Game.g.getWindowWidth(), (((i % 20) * 0.03f) + 0.25f) * Game.g.getWindowHeight());
            y += 0.03f;
        }
    }

    @Override
    public void keyPress(KeyEvent e) {

    }

    @Override
    public void keyRelease(KeyEvent e) {

    }

    public void dispose() {
        super.dispose();
        Currentind = 1;
        previousind = 0;
        times = new ArrayList<Double>();
    }
}
