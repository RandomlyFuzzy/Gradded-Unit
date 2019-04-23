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
public class Level4Solo extends ILevel {

    //Declaring variables
    private Player player1;
    private Vector StartingPosition = Vector.Zero();

    public Level4Solo() {
        //INSERT COMMENT
        super();
        setSimpleCollison(false);
        setStopAudioOnStart(true);
    }
    
    @Override
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageUtils.T.setImage("/API/cat", Game.GetLevel().getOnlineImage("https://cataas.com/cat/says/better%20luck%20next%20time?size=50&color=blue"));
                Thread.currentThread()
                        .stop();
            }
        }).start();
        
        player1 = new Player();
        AddObject(player1).setPosition(0, -50);
        
        
        
        //Sets sprite variables to more easily set each platform sprite
        BufferedImage moss1 = GetSprite("/images/platform/rock_platform_moss_01.png");
        BufferedImage wood1 = GetSprite("/images/platform/wooden_platform_01.png");
        BufferedImage clean1 = GetSprite("/images/platform/rock_platform_clean_01.png");
        BufferedImage clean0 = GetSprite("/images/platform/rock_platform_clean_00.png");
        BufferedImage clean2 = GetSprite("/images/platform/rock_platform_clean_02.png");

        //Adding Platforms by specifying playform type, their position and angle, as well as the sprite they should use.
        //Moving platforms needs a starting position, two positions it moves between and a speed for input variables.
        AddObject(new PlatForm(new Vector(0, 0), 0)).setLastimage(clean1);
        AddObject(new MovingPlatform(new Vector(300, -150), 0, new Vector[]{new Vector(200, -150), new Vector(700, -150)}, 1.5f, true)).GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new PlatForm(new Vector(800, -300), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(400, -500), 0.25)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(700, -700), -0.30)).setLastimage(moss1);
        AddObject(new PlatForm(new Vector(250, -850), 0.25)).setLastimage(moss1);
        AddObject(new DestroyingPlatForm(new Vector(500, -1000), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(650, -1200), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(400, -1400), 0)).setLastimage(wood1);
        AddObject(new DestroyingPlatForm(new Vector(100, -1550), 0)).setLastimage(wood1);
        AddObject(new MovingPlatform(new Vector(200, -1700), 0, new Vector[]{new Vector(200, -1700), new Vector(600, -1700)}, 1.5f, true)).GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(600, -1850), 0, new Vector[]{new Vector(600, -1850), new Vector(1000, -1850)}, 1, true)).GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(500, -2000), 0, new Vector[]{new Vector(500, -2000), new Vector(0, -2000)}, 2, true)).GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new MovingPlatform(new Vector(1100, -2150), 0, new Vector[]{new Vector(600, -2150), new Vector(1100, -2150)}, 1.5f, true)).GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new PlatForm(new Vector(500, -2300), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(275, -2500), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(100, -2700), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(300, -2900), 0)).setLastimage(clean0);
        AddObject(new PlatForm(new Vector(600, -3100), -0.25)).setLastimage(clean1);
        AddObject(new MovingPlatform(new Vector(0, -3250), 0, new Vector[]{new Vector(0, -3250), new Vector(400, -3250)}, 1.5f, true)).GetSprite("/images/platform/floatingplatform.png", 150, 150);
        AddObject(new DestroyingPlatForm(new Vector(600, -3450), 0)).setLastimage(wood1);
        AddObject(new PlatForm(new Vector(900, -3250), 0)).setLastimage(clean1);
        AddObject(new PlatForm(new Vector(795, -3340), 0)).setLastimage(clean2);

        //Creates the HUD and Lava objects
        AddObject(new HUD());
        AddObject(new Lava());
        
        //The flag creates the next Level and allows it to be loaded next.
        AddObject(new Flag(new Level5Solo())).setPosition(new Vector(950, -3300));
        
        //Plays the level 4 music on repeat
        play("/sounds/lvl4song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        
        //Loads and sets the background image for level 4
        setBackgroundimage(GetSprite("/images/backgrounds/level3.png"));
        
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
        float ypos = (((pos.getY()) / 5) - Game.getScaledHeight()* 1.2f) - StartingPosition.getY();
        g.drawImage(getBackgroundimage(), (int) (xpos), (int) (ypos), (int) (Game.getWindowWidth()* 1.3f), (int) (Game.getScaledHeight() * 2.3f), null);

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
