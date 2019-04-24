package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.levels.Playable.CoopLevels.LevelCoop;
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
 * LevelSelectCoop lets the user start the Coop Level via a button
 * @author Liam Woolley 1748910
 */
public class LevelSelectCoop extends ILevel {

    //Creates the end position for the transition effect
    public Vector transpos = Vector.Zero();

    public LevelSelectCoop() {
        super();
        //Stops any audio already playing
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        //Moves the camera for the transition
        Transform.setOffsetTranslation(new Vector(-Game.getWindowWidth(), 0));
        setBackground(Color.BLACK);
        
        //Adds a button that will load the Coop level when clicked
        AddObject(new Button(new Vector(0.5f, 0.5f), ("Coop Level"), new HUDdelegate() {
            public void OnClick(Button b) {
                LevelLoader.LoadLevel(new LevelCoop());
            }
        },false));

        //Creates a button that loads the main menu when clicked
        //Reverses the transition the main menu has when clicking coop levels
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                //Sets the end position for the transition
                 transpos = (new Vector(-Game.getWindowWidth(), 0));
                new Thread(() -> {
                    try {
                        //Creates a delay that allows the transition to run
                        Thread.sleep(750);
                    } 
                    //Catches the error to stop the game crashing
                    catch (InterruptedException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Loads the main menu
                    Game.SetLevelActive(new MainMenu(new Vector(-Game.getWindowWidth(),0)));
                }).start();
            }
        },false)).GetSprite("/images/button_0.png");
        
        //Adds the mouse object so the player can click on the buttons
        AddObject(new Mouse());
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
        
        //Draws background images for the transition using camera positions.
        g.drawImage(GetSprite("/images/backgrounds/cooplevels.png"), x, y, Game.getWindowWidth(), Game.getWindowHeight(), this);
        g.drawImage(GetSprite("/images/backgrounds/brickbackgroundgradient2.png"), x + Game.getWindowWidth(), y, Game.getWindowWidth(), Game.getWindowHeight(), this);
    }

    /**
     * This method continually moves the transition until it reaches the end point set earlier
     * @param g
     */
    @Override
    public void PostDraw(Graphics2D g) {
        //Controls how far the transition moves per frame
        float Time = 0.075f;
        //Gets the camera position
        Vector transspos = Transform.getOffsetTranslation();
        //Changes the X and Y positions
        float x0 = (1 - Time) * transspos.getX() + Time * transpos.getX();
        float y0 = (1 - Time) * transspos.getY() + Time * transpos.getY();
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
