/*
ASSESSMENT TASK 2 "Cookie Chaser"

Liam Woolley 1748910

30/1/2019

I certify that this is my own work and I have not used code from any other source.
 */
package com.FuturePixels.Entry;

import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.Utils.ILevel;
import com.FuturePixels.Utils.UtilManager;
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
public final class Game {

    //Keeps instance to be GC later
    public static Game g = null;

    private static final int WINDOW_WIDTH = 600, WINDOW_HEIGHT = 600;
    private static JFrame gameWindow;
    private static HashMap<String, Integer> LevelFinder = new HashMap<String, Integer>();
    static ArrayList<ILevel> Levels = new ArrayList();
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
        new UtilManager();
        Game window = new Game();
        deltalong = System.nanoTime();
    }

    private static ArrayList<Class> currentThings = new ArrayList<Class>();

    public static <T extends ILevel> void Add(Class<T> obj) {
        currentThings.add(obj);
    }

    public static <T extends ILevel> void Remove(Class<T> obj) {
        currentThings.remove(obj);
    }

    public static int GetTotal() {
        return currentThings.size();
    }

    public static void PrintAllTotal() {
        currentThings.forEach((a) -> {
            System.out.println("" + a.getClass().toString());
        });
    }

    public Game() {
        this.g = this;
        InitWindow();
        this.AddLevel(new MainMenu(), "MainMenu");
        this.AddLevel(new Level1(), "Level1");
        this.AddLevel(new LeaderBoard(), "LeaderBoard");
        Game.SetLevelActive("MainMenu");

    }

    public void InitWindow() {
        gameWindow = new JFrame();
        gameWindow.setMinimumSize(new Dimension(200, 350));
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setTitle("Gradded unit");
        gameWindow.setVisible(true);
    }

    public JFrame GetFrame() {
        return gameWindow;
    }

    public void AddLevel(ILevel level, String Name) {
        level.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        Levels.add(level);
        LevelFinder.put(Name, Levels.size() - 1);
    }

    public static void SetLevelActive(String name) {
        try {
            if (CurrentLevel != null) {
                gameWindow.getContentPane().removeAll();
                CurrentLevel.removeAll();
                CurrentLevel.stop();
                CurrentLevel.dispose();
                CurrentLevel.setFocusable(false);
                CurrentLevel.setEnabled(false);
                CurrentLevel = null;
                System.gc();
            }
            gameWindow.getContentPane().add(Levels.get(LevelFinder.get(name)), name);
            CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
            cl.next(gameWindow.getContentPane());
            Levels.get(LevelFinder.get(name)).requestFocus();
            CurrentLevel = Levels.get(LevelFinder.get(name));
            CurrentLevel.OnStart();
            CurrentLevel.start();
        } catch (Exception e) {

        }
    }

    public static ILevel GetLevel() {
        return CurrentLevel;
    }
}
