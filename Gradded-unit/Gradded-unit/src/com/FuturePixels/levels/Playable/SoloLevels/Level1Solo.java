package com.FuturePixels.levels.Playable.SoloLevels;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;

/**
 * @author Liam Rickman 1748905
 * Level 1 class which includes one basic platform that doesn't affect player movement
 */

//The class takes information from the ILevel class in the LiamEngine library in order to create the level
public class Level1Solo extends ILevel {

    //Declaring variables
    private Player player1;
    private Vector StartingPosition = Vector.Zero();
    
    /**
     *
     */
    public Level1Solo() {
        super();
        setSimpleCollison(false);
        //Stops any audio playing
        setStopAudioOnStart(true);
    }

    /**
     *
     */
    @Override
    public void init() {
        new Thread(new Runnable() {
            @Override
            
            //Displays the random cat image when a level is failed using an online API.
            public void run() {
                imageUtils.T.setImage("/API/cat", Game.GetLevel().getOnlineImage("https://cataas.com/cat/says/better%20luck%20next%20time?size=50&color=blue"));
                Thread.currentThread()
                        .stop();
            }
        }).start();
        
        //Creates a new player object and sets adds it to the level at the specified vector position.
        player1 = new Player();
        AddObject(player1).setPosition(0, -50);
        
        //Sets sprite variables to more easily set each platform sprite
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        
        //Adding Platforms by specifying playform type, their position and angle, as well as the sprite they should use.
        AddObject(new PlatForm(new Vector(0, 0), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -150), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(100, -350), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(400, -500), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -650), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(375, -850), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(385, -1000), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(250, -1150), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(150, -1325), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(350, -1475), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -1650), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -1650), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(350, -1850), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(450, -2000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(325, -2200), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(450, -2400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -2600), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(250, -2800), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(300, -3000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -3200), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -3400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(450, -3600), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(200, -3800), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(300, -4000), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(400, -4200), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(200, -4400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(300, -4600), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(400, -4800), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -5000), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(50, -5200), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(500, -5400), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(650, -5600), 0)).setLastimage(clean1);
        
        //Creates the HUD and Lava objects
        AddObject(new HUD());
        AddObject(new Lava());
        
        //The flag creates the next Level and allows it to be loaded next.
        AddObject(new Flag(new Level2Solo())).setPosition(new Vector(650, -5650));
        
        //We used the DebugObject to give a visual representation of collision boxes.
//        AddObject(new DebugObject());
        
        //Gives the vector Starting Position a new value that will be used later for scaling the background image
        StartingPosition = new Vector((Game.getScaledWidth() * .6f) / 2, 0);
        
        //Sets the camera position to half of the screen width
        Transform.setOffsetTranslation(new Vector((Game.getScaledWidth() * .6f) / 2, 0));
        
        //Plays the level 1 music on repeat
        play("/sounds/lvl1song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        //Loads and sets the background image for level 1
        setBackgroundimage(GetSprite("/images/backgrounds/level0.png"));
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
        //Keeps the background image scaled correctly when changing resolutions or going fullscreen.
        Vector pos = Transform.getOffsetTranslation();
        float xpos = (pos.getX() - Game.getWindowWidth() * 1.3f / 5) - StartingPosition.getX();
        float ypos = (((pos.getY()) / 5) - Game.getScaledHeight()* 1.9f) - StartingPosition.getY();
        g.drawImage(getBackgroundimage(), (int) (xpos), (int) (ypos), (int) (Game.getWindowWidth()* 1.3f), (int) (Game.getScaledHeight() * 3), null);
    }

    /**
     * @param e
     */
    @Override
    //Starts the player movement on specific key presses
    public void keyPress(KeyEvent e) {
        //Trys to see if the player is moving
        //If the player is not moving nothing will happen as the catch statement is blank. 
        try {
            //Sets a variable for key inputs
            int code = e.getKeyCode();
            //These statements check whether a Player1 Movement key is being pressed.
            //If they are, player 1 will move in that direction.
            //Gathers the player movement keys from the GamePreferences class
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(true);
            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(true);
            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(true);
            }
        } catch (Exception ex) {

        }
    }

    /**
     * @param e
     */
    @Override
    //Stops the player movement on specific key releases
    public void keyRelease(KeyEvent e) {
        //Trys to see if the player has released a key
        //If the player hasn't released a key nothing happens as the catch statement is blank.
        try {
            //Sets a variable for key inputs
            int code = e.getKeyCode();
            //These statements check whether a Player1 Movement key has been released
            //If they have, the player will stop moving in the that direction.
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(false);
            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(false);
            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(false);
            }
        } catch (Exception ex) {

        }
    }
}
