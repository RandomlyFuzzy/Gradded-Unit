/*
ASSESSMENT TASK 2 "Cookie Chaser"

Liam Woolley 1748910

30/1/2019

I certify that this is my own work and I have not used code from any other source.
 */
package com.FuturePixels.Entry;

import com.FuturePixels.MainClasses.imageUtils;
import com.FuturePixels.MainClasses.ILevel;
import com.FuturePixels.MainClasses.UtilManager;
import com.FuturePixels.levels.*;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

    private static final int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
    private static JFrame gameWindow;
    private double DeltaTime = 0;
    private static long deltalong = 0;
    private static ILevel CurrentLevel;
    private static Rectangle FrameBounds;
    private static boolean isDecorate = false;

    private static Cursor Swap;

    public void SetDelta() {
        DeltaTime = System.nanoTime() - deltalong;
        deltalong = System.nanoTime();
    }

    /*
        this is the Delta time in milliseconds for each time the screen is drawn
    */
    public double getDelta() {
        return DeltaTime / 1000000000.0;
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
        Swap = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
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
        Game.SetLevelActive(new MainMenu());
    }

    public void InitWindow() {
        gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.setTitle("Gradded unit");
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setMinimumSize(new Dimension(20, 20));
        gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        FrameBounds = gameWindow.getBounds();
    }

    public static void toggleCursor() {
        Cursor blankCursor = gameWindow.getContentPane().getCursor();
        gameWindow.getContentPane().setCursor(Swap);
        Swap = blankCursor;
    }

    private static boolean isFullScreen = false, isHalfFullScreen = false;

    public static void FullScreen() {
        GraphicsDevice graphicalDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        gameWindow.dispose();
        if (!isFullScreen) {
            FrameBounds = gameWindow.getBounds();
            gameWindow.setLocation(0, 0);
            gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            gameWindow.setUndecorated(true);
            isDecorate = true;
            gameWindow.pack();
        } else {
            gameWindow.setExtendedState(JFrame.NORMAL);
            gameWindow.setUndecorated(false);
            isDecorate = false;
            gameWindow.pack();
            gameWindow.setBounds(FrameBounds);
        }
        gameWindow.setVisible(true);

        isFullScreen = !isFullScreen;
    }

    public JFrame GetFrame() {
        return gameWindow;
    }

    public static void SetLevelActive(ILevel Level) {
        System.out.println("com.FuturePixels.Entry.Game.SetLevelActive() " + Level.getClass().toString() + " loading");
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
            Level.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
            gameWindow.getContentPane().add(Level);
            CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
            cl.next(gameWindow.getContentPane());
            Level.requestFocus();
            CurrentLevel = Level;
            CurrentLevel.OnStart();
            CurrentLevel.start();
        } catch (Exception e) {

        }
    }

    public void SetDimentions(int w, int h) {
        Rectangle bo = Game.g.GetFrame().getBounds();
        gameWindow.setBounds(bo.x, bo.y, w, h);
    }

  

    public static ILevel GetLevel() {
        return CurrentLevel;
    }
}
