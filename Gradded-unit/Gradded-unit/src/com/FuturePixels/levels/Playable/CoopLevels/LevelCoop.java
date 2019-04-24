package com.FuturePixels.levels.Playable.CoopLevels;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.levels.Menus.MainMenu;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.FileUtils;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.util.Random;
import javax.sound.sampled.Clip;

/**
 * @author Liam Woolley 1748910
 * Coop Level class for the two player mode
 */
public class LevelCoop extends ILevel {

    //Creates new player objects for the level
    private Player player1;
    private Player player2;

    /**
     *
     */
    public LevelCoop() {
        super();
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

        setSimpleCollison(false);
        
        //Creates two player objects
        player1 = new Player();
        player2 = new Player();
        
        //Imports a seed for the platform generation from a text file.
        int seed = Integer.parseInt(FileUtils.GetFileContence("resources/data/coopseed").trim());
        //Creates a randomiser for generating the random platforms
        Random r = new Random(seed);

        int i = 0;
        //creates the start position for the first platform and player spawns
        int start = ((r.nextInt() % (Game.getWindowWidth() / 5)) - 200) + 100;
        //Sets the players initial positions to above the first platform
        AddObject(player1).setPosition(start-200, -50);
        AddObject(player2).setPosition(start-200, -50);
        
        //Creates the starting platform for the level
        AddObject(new PlatForm(new Vector(start-200, 0), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");

        //For loop to add all of the platforms needed for the level
        for (i = 1; i < 20; i += 1) {
            AddObject(new PlatForm(new Vector(((r.nextInt() % (Game.getWindowWidth() / 5)) - 200) + 100, -i*200), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");;
        }

        //Creates the last position for the last platform and the flag spawn.
        int last = ((r.nextInt() % (Game.getWindowWidth() / 5)) - 200) + 100;
        //Adds the last platform to the game  using the variable above
        AddObject(new PlatForm(new Vector(last, -(i*200)), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");;
        
        //The flag creates the next Level and allows it to be loaded next.
        AddObject(new Flag(new MainMenu(new Vector(0, Game.getWindowHeight())), seed)).setPosition(new Vector(last, -(i*200 + 50)));

        //Creates the HUD and Lava objects
        AddObject(new HUD());
        AddObject(new Lava());
        
        //We used the DebugObject to give a visual representation of collision boxes.
//        AddObject(new DebugObject());
        
        //Sets the camera position to half of the screen width
        Transform.setOffsetTranslation(new Vector((Game.getWindowWidth() / 2f), 0));
        
        //Plays the level 4 music on repeat (can be changed to a different level if desired)
        play("/sounds/lvl4song.wav", 0, Clip.LOOP_CONTINUOUSLY);
        
        //Loads a random background image from the level backgrounds
        setBackgroundimage(GetSprite("/images/backgrounds/level"+(r.nextInt(5))+".png"));

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
      
    }

    /**
     * @param e
     */
    @Override
    //Starts players movements depending on their key presses
    public void keyPress(KeyEvent e) {
        //Trys to see if the player is trying to move
        //If the player is not, nothing will happen.
        try {
            //Sets a variable for key inputs
            int code = e.getKeyCode();
            //These statements check whether a Player1 movement key is being pressed
            //If they are, player 1 will move in that direction
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
            
            //These statements check whether a Player2 movement key is being pressed
            //If they are, player 2 will move in that direction
            //Gathers the player movement keys from the GamePreferences class
            if (code == GamePreferences.gp.getKeyRightP2()) {
                player2.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(true);
            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(true);
            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(true);
            }
        } catch (Exception ex) {

        }
    }

    /**
     * @param e
     */
    @Override
    //Stops players movement on specific key releases
    public void keyRelease(KeyEvent e) {
        //Trys to see if the players have released a key.
        //If the players haven't nothing will happen.
        try {
            //Sets a variable for key inputs
            int code = e.getKeyCode();
            //These statements check whether a Player1 movement key has been released
            //If so Player 1 will stop moving in that direction
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(false);
            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(false);
            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(false);
            }
            //These statements check whether a Player2 movement key has been released
            //If so Player 2 will stop moving in that direction
            if (code == GamePreferences.gp.getKeyRightP2()) {
                player2.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(false);
            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(false);
            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(false);
            }
        } catch (Exception ex) {

        }
    }
}
