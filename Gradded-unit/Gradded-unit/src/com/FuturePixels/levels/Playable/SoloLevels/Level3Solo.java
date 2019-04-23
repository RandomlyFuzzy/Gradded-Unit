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
 * @author Liam Rickman
 */

//The class takes information from the ILevel class in the LiamEngine library in order to create the level
public class Level3Solo extends ILevel {

    //Declaring variables
    private Player player1;
    private Vector StartingPosition = Vector.Zero();

    public Level3Solo() {
        super();
        setSimpleCollison(false);
        //Stops any audio playing
        setStopAudioOnStart(true);
    }
    
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
        AddObject(player1).setPosition(100, 0);
        
        //Sets sprite variables to more easily set each platform sprite
        BufferedImage moss1 = GetSprite("/images/platform/rock_platform_moss_01.png");
        BufferedImage wood1 = GetSprite("/images/platform/wooden_platform_01.png");
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");

        
        //Adding Platforms by specifying playform type, their position and angle, as well as the sprite they should use.
        AddObject(new PlatForm(new Vector(100, 100), 0)).setLastimage(clean1);
        AddObject(new DestroyingPlatForm(new Vector(300, 0), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(475, -150), -0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(600, -350), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(425, -550), 0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(150, -625), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(400, -850), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(601, -850), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(400, -1050), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(200, -1200), 0.20)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(300, -1400), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(600, -1600), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(500, -1800), 0.15)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(250, -2000), 0.10)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(600, -2050), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(500, -2250), 0.15)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(225, -2450), 0.15)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(350, -2650), -0.10)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(550, -2850), -0.10)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(450, -3000), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(200, -3150), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(500, -3300), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(150, -3450), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(600, -3600), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(500, -3800), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(300, -4000), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(600, -4100), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(400, -4200), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(200, -4400), 0)).setLastimage(clean1);
        
        //Creates the HUD and Lava objects
        AddObject(new HUD());
        AddObject(new Lava());
        
        //The flag creates the next Level and allows it to be loaded next.
        AddObject(new Flag(new Level4Solo())).setPosition(new Vector(200, -4450));
        
        //Plays the level 3 music on repeat
        play("/Sounds/Lvl3Song.wav", 0, Clip.LOOP_CONTINUOUSLY);
       
        //Loads and sets the background image for level 3
        setBackgroundimage(GetSprite("/images/backgrounds/level2.png"));
        
        //Gives the vector Starting Position a new value that will be used later for scaling the background image
        StartingPosition = new Vector((Game.getScaledWidth() * 0.4f) / 2, 0);
        
        Transform.setOffsetTranslation(StartingPosition);
    }

    /**
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {

        if (player1 == null) {
            return;
        }

    }

    /**
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        //Keeps the background image scaled correctly when changing resolutions or going fullscreen.
        Vector pos = Transform.getOffsetTranslation();
        float xpos = (pos.getX() - Game.getWindowWidth() * 1.3f / 5) - StartingPosition.getX();
        float ypos = (((pos.getY()) / 5) - Game.getScaledHeight()* 1.5f) - StartingPosition.getY();
        g.drawImage(getBackgroundimage(), (int) (xpos), (int) (ypos), (int) (Game.getWindowWidth()* 1.3f), (int) (Game.getScaledHeight() * 2.6f), null);
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
            //If they are, the player will move in the that direction.
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
