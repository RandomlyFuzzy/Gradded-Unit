/*
ASSESSMENT TASK 2 "Cookie Chaser"

Liam Woolley 1748910

30/1/2019

I certify that this is my own work and I have not used code from any other source.
 */
package com.FuturePixels.Entry;

import com.FuturePixels.Drawables.Levels.Player;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.levels.Menus.MainMenu;
import com.FuturePixels.MainClasses.AbstractClasses.ILevel;
import com.FuturePixels.MainClasses.Utils.MusicUtils;
import com.FuturePixels.MainClasses.Utils.UtilManager;
import com.FuturePixels.MainClasses.Utils.imageUtils;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Game {

    public static Game g = null;

    private static boolean isFullScreen = false;
    private static final int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
    private static JFrame gameWindow;
    private static long deltalong = 0;
    private static ILevel CurrentLevel;
    private static Rectangle FrameBounds;
    private static Cursor Swap;
    private static String LastLevelName = "";

    public static String getLastLevelName() {
        return LastLevelName;
    }

    public static void setLastLevelName(String LastLevelName) {
        Game.LastLevelName = LastLevelName;
    }
    private float DeltaTime = 0;

    public void SetDelta() {
        DeltaTime = System.nanoTime() - deltalong;
        deltalong = System.nanoTime();
    }

    /*
        this is the Delta time in milliseconds for each time the screen is drawn
     */
    public float getDelta() {
        return DeltaTime / 1000000000.0f;
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

    public Game() {
        this.g = this;
        InitWindow();
//        FullScreen();
        Game.SetLevelActive(new MainMenu());
        toggleCursor();
    }

    public void InitWindow() {
        gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setLocation(WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3);
        gameWindow.setMinimumSize(new Dimension(20, 20));
        SetDimentions(WINDOW_WIDTH, WINDOW_HEIGHT);
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.setTitle("Gradded unit");
//        gameWindow.pack();

        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        FrameBounds = gameWindow.getBounds();
    }

    public static void toggleCursor() {
        Cursor blankCursor = gameWindow.getContentPane().getCursor();
        gameWindow.getContentPane().setCursor(Swap);
        Swap = blankCursor;
    }

    public static void FullScreen() {
        GraphicsDevice graphicalDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        gameWindow.dispose();
        if (!isFullScreen) {
            FrameBounds = gameWindow.getBounds();
            gameWindow.setLocation(0, 0);
            gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            gameWindow.setUndecorated(true);
            gameWindow.pack();
        } else {
            gameWindow.setExtendedState(JFrame.NORMAL);
            gameWindow.setUndecorated(false);
            gameWindow.pack();
            gameWindow.setBounds(FrameBounds);
        }
        gameWindow.setVisible(true);
        SetDimentions(gameWindow.getWidth(), gameWindow.getHeight());
        isFullScreen = !isFullScreen;
    }

    public JFrame GetFrame() {
        return gameWindow;
    }

    public synchronized static void SetLevelActive(ILevel Level) {
        System.out.println("com.FuturePixels.Entry.Game.SetLevelActive() " + Level.getClass().toString() + " loading");
        try {
            if (CurrentLevel != null) {
                LastLevelName = CurrentLevel.getClass().toString();
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
            if (CurrentLevel.StopAudioOnStart()) {
                MusicUtils.StopAllSounds();
            }
            CurrentLevel.OnStart();
            CurrentLevel.start();

        } catch (Exception e) {

        }
    }

    public static void SetDimentions(int w, int h) {
        Rectangle bo = Game.g.GetFrame().getBounds();
        gameWindow.setBounds(bo.x, bo.y, w, h);
        GamePreferences.CalculateDims();
    }

    public static ILevel GetLevel() {
        return CurrentLevel;
    }
}
