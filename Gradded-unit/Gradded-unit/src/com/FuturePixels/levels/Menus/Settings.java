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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Settings extends ILevel {


    public Settings() {
        super();
        System.out.println("com.game.levels.Settings.<init>()");
        setStopAudioOnStart(false);
        setSimpleCollison(true);
    }

    @Override
    public void init() {

        AddObject(new HUD());
        AddObject(new Button(new Vector(0.2f, 0.1f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new Slider(new Vector(0.55f, 0.1f), 0.0465f, new HUDdelegate() {
            @Override
            public void OnChange(Slider s, float Value) {
                System.out.println(""+Value);
//                ILevel.setFPS(30 + (int) (Value * 60f));
                MusicUtils.SetVolume(Value);
            }
        }));

        AddObject(new DropDownButton(new Vector(0.35f, 0.3f), "Resolution", new Vector(0.0f, 0.1f), new String[]{"1920X1080", " 1600X900", "1280X720", "860X540", "640X360"},
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
                    Game.GetFrame().setBounds(bo.x, bo.y, 640, 360);
                }
            }
                }));

        AddObject(new Button(new Vector(0.35f, 0.1f), "Fullscreen", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.FullScreen();
                
            }

        }));
        AddObject(new Button(new Vector(0.8f, 0.1f), "Controls", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Controls());
            }
        }));
         AddObject(new Button(new Vector(0.8f, 0.3f), "Credit", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Credits());
            }
        }));
        GetObject(0).setScale(new Vector(0.5f, 0.7f));
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/Images/backgrounds/background1.png"));
    }

    @Override
    public void Update(ActionEvent ae) {
    }

    @Override
    public void Draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        
    }

    @Override
    public void keyPress(KeyEvent e) {

    }

    @Override
    public void keyRelease(KeyEvent e) {

    }

}
