/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
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
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Settings extends ILevel {

    /**
     *
     */
    public Settings() {
        super();
        
        setStopAudioOnStart(false);
        setSimpleCollison(true);
    }

    /**
     *
     */
    @Override
    public void init() {
        AddObject(new HUD()).setScale(new Vector(0.5f, 0.7f));
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu(Vector.Zero()));
            }
        })).GetSprite("/images/Button_0.png");
        HUD.AddText(String.format("%.0f ", MusicUtils.GetVolume()*100), new Vector(0.775f * Game.getWindowWidth(), 0.25f * Game.getWindowHeight()));
        HUD.AddText("Master", new Vector(0.775f * Game.getWindowWidth(), 0.20f * Game.getWindowHeight()));
        AddObject(new Slider(new Vector(0.675f, 0.3f), 0.074f, new HUDdelegate() {
            @Override
            public void OnChange(Slider s, float Value) {
//                ILevel.setFPS(30 + (int) (Value * 60f));
                MusicUtils.SetVolume(Value);
                HUD.EditText(0, String.format("%.0f ", Value*100));
            }
        }));

        AddObject(new DropDownButton(new Vector(0.15f, 0.3f), "Resolution", new Vector(0.0f, 0.115f),
                new String[]{"1920X1080", " 1600X900", "1280X720", "860X540", "640X360"},
                new HUDdelegate[]{
                    new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    //1920 x 1080
                    Game.SetDimentions(1920, 1080);
                }
            },
                    new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    //1600 x 900
                    Game.SetDimentions(1600, 900);
                }
            },
                    new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    //1280 x 720
                    Game.SetDimentions(1280, 720);
                }
            },
                    new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    //860 x 540
                    Game.SetDimentions(860, 540);
                }
            },
                    new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    Rectangle bo = Game.GetFrame().getBounds();
                    //640 x 360
                    Game.SetDimentions(640, 360);
                }
            }
                }));

        AddObject(new Button(new Vector(0.425f, 0.3f), "Fullscreen", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.FullScreen();

            }

        }));
        AddObject(new Button(new Vector(0.425f, 0.415f), "Controls", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Controls());
            }
        }));
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/Images/backgrounds/Settings.png"));
        
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        HUD.EditText(1, new Vector(0.765f * Game.getWindowWidth(), 0.25f * Game.getWindowHeight()+Game.WorldScale().getY()*10));
        HUD.EditText(0, new Vector(0.765f * Game.getWindowWidth(), 0.3f * Game.getWindowHeight()+Game.WorldScale().getY()*10));

    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.WHITE);

    }

    /**
     *
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {

    }

}
