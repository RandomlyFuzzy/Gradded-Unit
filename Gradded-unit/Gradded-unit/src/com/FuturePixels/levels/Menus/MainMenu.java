package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Components.Transform;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/**
 * @author Liam Woolley 1748910
 * This class is for the Main menu which is the first screen the player sees.
 * Links to other screens and menus via buttons
 */
public class MainMenu extends ILevel {

    //Positions for the transitions 
    //transpos = position it is is trying to reach
    //StartPos = position that it starts from
    private static Vector transpos = Vector.Zero();
    private static Vector StartPos = Vector.Zero();

    /**
     *
     * @param transpos hello
     */
    public static void setTranspos(Vector transpos) {
        MainMenu.transpos = transpos;
    }

    public MainMenu() {
        super();
        //Stops any audio already playing
        setStopAudioOnStart(false);
        //Resets StartPos
        StartPos = Vector.Zero();
    }

    /**
     * @param StartPos
     */
    public MainMenu(Vector StartPos) {
        super();
        //Stops any audio playing
        setStopAudioOnStart(false);
        this.StartPos = StartPos;
    }
   
    @Override
    public void init() {
        setBackground(Color.BLACK);
        //Resets transpos
        if (transpos == null) {
            transpos = Vector.Zero();
        }
        //Moves the camera to the starting position
        Transform.setOffsetTranslation(StartPos);
        //Sets world dimensions
        Game.setWorldrelDims(new Vector(1, 1));

        //Creates a button that links to the solo level select when clicked
        AddObject(new Button(new Vector(0.218f, 0.415f), "Solo", new HUDdelegate() {
            @Override
            
            public void OnClick(Button b) {
                //Create a transition effect using by sliding the background 
                MainMenu.setTranspos(new Vector(0, Game.getWindowHeight()));
                new Thread(() -> {
                    try {
                        //Adds a delay to give time for the transition
                        Thread.sleep(750);
                    } 
                    //Catches the error incase it cannot add the delay
                    catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Loads the solo level select menu
                    Game.SetLevelActive(new LevelSelectSolo());
                    //Resets the static variable
                    MainMenu.setTranspos(new Vector(0, 0));
                }).start();
            }
        }, false)).GetSprite("/images/button_0.png");
        
        
        //Creates an button that links to the coop level select when clicked
        AddObject(new Button(new Vector(0.365f, 0.415f), "Coop", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                //Creates the transition effect when switching menus
                MainMenu.setTranspos(new Vector(-Game.getWindowWidth(), 0));
                new Thread(() -> {
                    try {
                        //Adds a delay so the transition has time to complete
                        Thread.sleep(750);
                    } 
                    //Catches the error incase it cannot add the delay
                    catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Loads the coop level select menu
                    Game.SetLevelActive(new LevelSelectCoop());
                    //Resets the static variable
                    MainMenu.setTranspos(new Vector(0, 0));
                }).start();
            }
        }, false)).GetSprite("/images/button_0.png");
        
        //Creates a button object that loads the leaderboard menu when clicked
        AddObject(new Button(new Vector(0.29f, 0.53f), "Leaderboard", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LeaderBoard());
            }
        }, false));

        //Creates a button object that loads the settings menu when clicked
        AddObject(new Button(new Vector(0.29f, 0.645f), "Settings", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }, false));

        
        //Creates a button object that loads the credits screen when clicked
        AddObject(new Button(new Vector(0.29f, 0.76f), "Credits", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Credits());
            }
        }, false));

        //Creates a button object that exits the game when clicked
        AddObject(new Button(new Vector(0.29f, 0.875f), "Exit Game", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                System.exit(0);
            }
        }, false));

        //Adds the mouse object so the player can see what they are pointing at
        AddObject(new Mouse());
        //Adds the HUD object to the level.
        AddObject(new HUD());
        
        //Plays the main menu music on a loop
        play("/sounds/music.wav", 0, Clip.LOOP_CONTINUOUSLY);
        
        //Trys to load a font from the resources folder
        try {
            //Importing the font
            InputStream myStream = new BufferedInputStream(new FileInputStream("resources/fonts/maintext.ttf"));
            //Setting the font as a variable
            Font title = Font.createFont(Font.TRUETYPE_FONT, myStream);
            //Setting font style and size
            title = title.deriveFont(Font.PLAIN, (Game.ButtonDims().getY() * 50f));
            //Sets the default font to this font.
            ILevel.setDefaultFont(title);
        }
        //
        catch (FontFormatException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {

    }

    /**
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        //Sets the colour for HUD elements to white
        g.setColor(Color.WHITE);
        //Gets variables for the camera positions
        int x = (int) Transform.getOffsetTranslation().getX();
        int y = (int) Transform.getOffsetTranslation().getY();
        //Draws the background images for the transition effect with three total backgrounds
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient.png"), x, y - Game.getWindowHeight(), Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient2.png"), x + Game.getWindowWidth(), y, Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/mainmenu.png"), x, y, Game.getWindowWidth(), Game.getWindowHeight(), this);

    }

    /**
     * @param g
     */
    @Override
    public void PostDraw(Graphics2D g) {

        float Time = 0.075f;
        Vector transspos = Transform.getOffsetTranslation();
        float x0 = (1 - Time) * transspos.getX() + Time * transpos.getX();
        float y0 = (1 - Time) * transspos.getY() + Time * transpos.getY();
        Transform.setOffsetTranslation(new Vector(x0, y0));

    }

    /**
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {
    }

    /**
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {
        
    }

}
