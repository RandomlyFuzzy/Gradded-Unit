/*
ASSESSMENT TASK 2 "Cookie Chaser"

Liam Woolley 1748910

30/1/2019

I certify that this is my own work and I have not used code from any other source.
 */
package com.FuturePixels.game;

import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.levels.StartGamePanel;
import com.FuturePixels.levels.level1;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
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
    private StartGamePanel startScreen;
    level1 lvl1;
    LeaderBoard lb;
    private double DeltaTime = 0;
    private static long deltalong = 0;

    public void SetDelta() {
        DeltaTime = System.nanoTime() - deltalong;
        deltalong = System.nanoTime();
    }

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
        Game window = new Game();
        deltalong = System.nanoTime();
        window.showStartScreen();

    }

    public Game() {
        this.g = this;
        InitWindow();
        initScreens();
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

    public void initScreens() {
        startScreen = new StartGamePanel(this);
        startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        lvl1 = new level1(this);
        lvl1.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        lb = new LeaderBoard(this);
        lb.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        // This will add a start Screen to the Main Window   
        gameWindow.getContentPane().add(startScreen, "INTRO");
        gameWindow.getContentPane().add(lvl1, "LVL1");
        gameWindow.getContentPane().add(lb, "LeaderBoard");
    }

    public void showStartScreen() {
        gameWindow.setVisible(true);
        startScreen.requestFocus();
    }

    public void DeleteGame() {
        gameWindow.remove(lvl1);
        lvl1 = null;
        gameWindow.revalidate();
        lvl1 = new level1(this);
        lvl1.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        gameWindow.getContentPane().add(lvl1, "LVL1");
    }

    public void playGame() {
        // This method will start the main game    
        CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lvl1.requestFocus();
        lvl1.start();
    }

    public void ShowLeaderBoard() {
        // This method will start the main game    
        CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lb.requestFocus();
        lb.start();
    }
}
