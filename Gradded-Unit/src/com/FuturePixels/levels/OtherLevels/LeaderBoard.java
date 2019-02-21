/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.OtherLevels;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.ButtonAbstract;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.MainClasses.ILevel;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.LevelLoader;
import com.FuturePixels.MainClasses.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<String> times = new ArrayList<String>();

    public static int getCurrentind() {
        return Currentind;
    }

    public static void setCurrentind(int Currentind) {
        LeaderBoard.Currentind = Currentind;
    }

    public LeaderBoard() {
        super();
        System.out.println("com.game.levels.LeaderBoard.<init>()");
    }

    @Override
    public void init() {
        GetSprite("/Images/background.png");

        for (int i = 0; i < 2; i++) {
            AddObject(new Button(new Vector(((0.15f * (i % 6)) + 0.1f), ((0.1f * (i / 6)) + 0.1f)), ("Level" + (i + 1)), new ButtonAbstract() {
                public void OnClick(Button b) {
                    String mess = b.getMessage().substring(5);
                    System.out.println(".OnClick() " + mess);
                    LeaderBoard.setCurrentind(Integer.parseInt(mess));
                }
            }));
        }
        AddObject(new Mouse());
    }

    @Override
    public void Update(ActionEvent ae) {
        if (previousind != Currentind) {
            try {
                times = new ArrayList<String>();
                System.out.println("com.FuturePixels.levels.OtherLevels.LeaderBoard.Update() "+FileURI + Currentind + EndURI);
                File file = new File(FileURI + Currentind + EndURI);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileReader fis = new FileReader(file);
                Scanner scan = new Scanner(fis);
                while (scan.hasNextLine()) {
                    times.add(scan.nextLine());
                }
            } catch (IOException ex) {
                Logger.getLogger(LeaderBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            previousind = Currentind;
        }
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/background.png"), Game.g.getWindowWidth(), 0, (Game.g.getWindowWidth() * -1), (Game.g.getWindowHeight()), null);
        float y = 0.3f;
        for (String s : times) {
            g.drawString(s, 0.05f * Game.g.getWindowWidth(), y * Game.g.getWindowHeight());
            y += 0.05f;
        }
    }

    @Override
    public void keyPress(KeyEvent e) {

    }

    @Override
    public void keyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            new LevelLoader(new MainMenu());
        }
    }

    public void dispose() {
        super.dispose();
        Currentind = 1;
    }
}
