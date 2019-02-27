/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Utils.LevelLoader;
import com.FuturePixels.levels.Menus.MainMenu;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author RandomlyFuzzy
 */
public class Flag extends IDrawable {

    private float ind = 0;
    private ILevel next;
    public Flag(ILevel nextLevel){
       super();
       next = nextLevel;
    }
    
    
    @Override
    public void init() {
        for (int i = 1; i < 8; i++) {
            GetSprite("/images/flagAniamtion/Flag" + i + ".png");
        }
    }

    @Override
    public void doMove() {
        ind += 0.25f;
        ind = ind % 8;
    }

    @Override
    public void Update(Graphics2D g) {
        GetSprite("/images/flagAniamtion/Flag" + (1 + (int) ind) + ".png");
        DrawLastLoadedImage(g);
    }

    boolean ran = false;

    @Override
    public void onCollison(IDrawable im) {

        if (im instanceof Player && !ran) {
            PrintStream FileStream;
            FileReader FileReader;
            try {
                String Slim = Level().getClass().toString().substring(Level().getClass().toString().lastIndexOf(".") + 1);
                File file = new File("resources/Savedata/" + Slim + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileReader fis = new FileReader(file);
                char[] data = new char[(int) file.length()];
                fis.read(data);
                fis.close();
                String str = new String(data);
                str += "" + String.format("%.2f", Level().getTime()) + "\n";
                FileStream = new PrintStream(new File("resources/Savedata/" + Slim + ".txt"));
                FileStream.print(str);
                FileStream.close();
                Level().play("/sounds/LevelCompleate.wav");
                Player.setLock(true);
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
 
                        new LevelLoader(next.getClass().newInstance());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }).start();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Flag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        } else {
            return;
        }
        ran = true;
    }
}
