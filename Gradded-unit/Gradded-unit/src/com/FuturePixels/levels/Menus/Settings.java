package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Vector;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.DropDownButton;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.Slider;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.MusicUtils;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author Liam Woolley 1748910
 * This class lets the user change and view options
 */

//The class takes information from the ILevel class in the LiamEngine library in order to create the level
public class Settings extends ILevel {

    /**
     *
     */
    public Settings() {
        super();
        //Stops any audio currently playing
        setStopAudioOnStart(false);
        //Lets the cursor only highlight one button or option at a time.
        setSimpleCollison(true);
    }

    /**
     *
     */
    @Override
    public void init() {
        //Adds the HUD to the level and scales it appropriately
        AddObject(new HUD()).setScale(new Vector(0.5f, 0.7f));
        //Back button that links back to the main menu when clicked
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu(Vector.Zero()));
            }
        })).GetSprite("/images/button_0.png");
        
        //Adds text for the value of the Master Volume set by the slider object below       
        HUD.AddText(String.format("%.0f ", MusicUtils.GetVolume()*100), new Vector(0.775f * Game.getWindowWidth(), 0.25f * Game.getWindowHeight()));
        //Adds text for a heading for the Master Volume
        HUD.AddText("Master Volume", new Vector(0.775f * Game.getWindowWidth(), 0.20f * Game.getWindowHeight()));
        //Creates a slider object that changes the master volume for the game when clicked and dragged.
        //Left = lower volume | Right = higher volume
        AddObject(new Slider(new Vector(0.675f, 0.3f), 0.074f, new HUDdelegate() {
            @Override
            //Runs when the slider is moved
            public void OnChange(Slider s, float Value) {
//                ILevel.setFPS(30 + (int) (Value * 60f));
                //Sets the volume to the slider value
                MusicUtils.SetVolume(Value);
                //Updates the value of master volume on the HUD
                HUD.EditText(0, String.format("%.0f ", Value*100));
            }
        }));
        //Creates a new drop down button for the resolutions. When clicked shows 5 new buttons
        AddObject(new DropDownButton(new Vector(0.14f, 0.3f), "Resolution", new Vector(0.0f, 0.115f),
                //String of the text that will be displayed on each button
                new String[]{"1920X1080", " 1600X900", "1280X720", "860X540", "640X360"},
                new HUDdelegate[]{
                    new HUDdelegate() {
                @Override
                //When clicked changes the window size to 1920x1080
                public void OnClick(Button b) {
                    
                    Game.SetDimentions(1920, 1080);
                }
            },
                    new HUDdelegate() {
                @Override
                //When clicked changes the window size to 1600x900
                public void OnClick(Button b) {
                    Game.SetDimentions(1600, 900);
                }
            },
                    new HUDdelegate() {
                @Override
                //When clicked changes the window size to 1280x720
                public void OnClick(Button b) {
                    Game.SetDimentions(1280, 720);
                }
            },
                    new HUDdelegate() {
                @Override
                //When clicked changes the window size to 860x540
                public void OnClick(Button b) {
                    Game.SetDimentions(860, 540);
                }
            },
                    new HUDdelegate() {
                @Override
                //When clicked changes the window size to 640x360
                public void OnClick(Button b) {
                    Rectangle bo = Game.GetFrame().getBounds();
                    Game.SetDimentions(640, 360);
                }
            }
                }));

        //Creates a button that will remove the borders of the game window and makes the window size to fill the screen when clicked. 
        AddObject(new Button(new Vector(0.425f, 0.3f), "Fullscreen", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.FullScreen();

            }

        }));
        //Adds a button that links to the controls menu when clicked
        AddObject(new Button(new Vector(0.425f, 0.415f), "Controls", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Controls());
            }
        }));
        //Adds the mouse object to the menu so the player can see where they are pointing
        AddObject(new Mouse());
        
        //Sets the background image to the settings background
        setBackgroundimage(GetSprite("/images/backgrounds/settings.png"));
        
    }

    /**
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        //
        HUD.EditText(1, new Vector(0.765f * Game.getWindowWidth(), 0.25f * Game.getWindowHeight()+Game.WorldScale().getY()*10));
        HUD.EditText(0, new Vector(0.765f * Game.getWindowWidth(), 0.3f * Game.getWindowHeight()+Game.WorldScale().getY()*10));
    }

    /**
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        //Sets HUD elements to white
        g.setColor(Color.WHITE);
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
