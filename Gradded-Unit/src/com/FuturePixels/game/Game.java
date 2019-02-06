/*
ASSESSMENT TASK 2 "Cookie Chaser"

Liam Woolley 1748910

30/1/2019

I certify that this is my own work and I have not used code from any other source.
 */
package com.FuturePixels.game;

import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.levels.SetClasses.ILevel;
import com.FuturePixels.levels.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Game {

    public static Game g;

    private static final int WINDOW_WIDTH = 600, WINDOW_HEIGHT = 600;
    private static JFrame gameWindow;
    static ArrayList<ILevel> Levels = new ArrayList();
    private static HashMap<String, Integer> LevelFinder = new HashMap<String, Integer>();
    private double DeltaTime = 0;
    private static long deltalong = 0;
    private static ILevel CurrentLevel;

    public void SetDelta() {
        DeltaTime = System.nanoTime() - deltalong;
        deltalong = System.nanoTime();
    }

    public double getDelta() {
        return DeltaTime / 5000000000.0;
    }

    public int getWindowWidth() {
        return gameWindow.getWidth();
    }

    public int getWindowHeight() {
        return gameWindow.getHeight();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new imageUtils();
        Game window = new Game();
        deltalong = System.nanoTime();
    }

    public Game() {
        this.g = this;
        InitWindow();
        this.AddLevel(new StartGamePanel(this), "StartGamePanel");
        this.AddLevel(new Level1(this), "Level1");
        this.AddLevel(new LeaderBoard(this), "LeaderBoard");
        Game.SetLevelActive("StartGamePanel");
        
    }

    public void InitWindow() {
        gameWindow = new JFrame();
        gameWindow.setMinimumSize(new Dimension(200, 350));
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setTitle("Cookie Chaser");
        gameWindow.setVisible(true);
    }

    public void AddLevel(ILevel level, String Name) {
        level.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        Levels.add(level);
        LevelFinder.put(Name, Levels.size() - 1);
    }

    public static void SetLevelActive(String name) {
        gameWindow.getContentPane().add(Levels.get(LevelFinder.get(name)), name);
        CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        Levels.get(LevelFinder.get(name)).requestFocus();
        Levels.get(LevelFinder.get(name)).start();
        CurrentLevel = Levels.get(LevelFinder.get(name));
    }

}
