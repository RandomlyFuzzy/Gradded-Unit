package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * LevelSelectSolo lets the user pick which solo level they start playing on
 * @author Liam Woolley 1748910
 */
public class LevelSelectSolo extends ILevel {

    //Creates the end position for the a transition effect
    public Vector transpos = Vector.One();

    public LevelSelectSolo() {
        super();
        //Stops any audio already playing
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        //Moves the camera for the transition
        Transform.setOffsetTranslation(new Vector(0, Game.getWindowHeight()));
        setBackground(Color.BLACK);
        //Loops for the amount of levels in the game to add enough buttons
        //When the buttons are clicked it will load the appropriate level
        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.15f)), ((0.13f * (i % 6)) + 0.29f)), ("Level " + (i + 1)), new HUDdelegate() {
                public void OnClick(Button b) {
                    LevelLoader.LoadLevel(b.getMessage().replace(" ", ""));
                }
            }, false)).GetSprite("/images/button_2.png");
        }
        //Creates a button that links back to the main menu when clicked
        //Has the reverse of the transition that the main menu has when the Solo button is clicked.
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                //Sets the end position for the transition
                transpos = (new Vector(0, Game.getWindowHeight()));
                new Thread(() -> {
                    try {
                        //Creates a delay that allows the transition to run
                        Thread.sleep(750);
                    } 
                    //Catches the error to stop the game failing
                    catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Loads the main menu
                    Game.SetLevelActive(new MainMenu(new Vector(0, Game.getWindowHeight())));
                }).start();
            }
        }, false)).GetSprite("/images/button_0.png");
        
        //Adds the mouse object so the player can click on the buttons
        AddObject(new Mouse());
        
        //Sets the background image to the sololevels background
        setBackgroundimage(GetSprite("/images/backgrounds/sololevels.png"));
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

        //Gets camera position variables
        int x = (int) Transform.getOffsetTranslation().getX();
        int y = (int) Transform.getOffsetTranslation().getY();
        //Draws background images for the transition using camera positions
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient.png"), x, y - Game.getWindowHeight(), Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/sololevels.png"), x, y, Game.getWindowWidth(), Game.getWindowHeight(), this);

    }

    /**
     * This method continually moves the transition until it reaches the end point set earlier
     * @param g
     */
    @Override
    public void PostDraw(Graphics2D g) { 
        //Controls how far the transition moves per frame
        float Percent = 0.075f;
        //Gets the camera position
        Vector transspos = Transform.getOffsetTranslation();
        //Changes the X and Y positions
        float x0 = (1 - Percent) * transspos.getX() + Percent * transpos.getX();
        float y0 = (1 - Percent) * transspos.getY() + Percent * transpos.getY();
        //Moves the camera and shows the transition
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
