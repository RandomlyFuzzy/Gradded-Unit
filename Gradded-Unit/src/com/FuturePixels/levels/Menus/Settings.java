/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.MainClasses.AbstractClasses.ILevel;
import com.FuturePixels.MainClasses.Components.Vector;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Drawables.Menus.DropDownButton;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.Slider;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Utils.MusicUtils;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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
    }

    @Override
    public void init() {
        AddObject(new Mouse());
        AddObject(new HUD());
        AddObject(new Button(new Vector(0.2f, 0.1f), "Back", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new Slider(new Vector(0.55f, 0.1f), 0.0465f, new HUDAbstract() {
            @Override
            public void OnChange(Slider s,float Value) {
                System.out.println(".OnChange() "+Value);
//                MusicUtils.ChangeMasterVolume(Value);
            }
        }));
        HUD.AddText("60 FPS",(new Vector(Game.g.getWindowWidth()*0.65f,Game.g.getWindowHeight()*0.1f)));

        AddObject(new DropDownButton(new Vector(0.4f, 0.2f), "Random DropDown", new Vector(0.0f, 0.1f), new String[]{"1920X1080", " 1600X900", "1280X720", "860X540", "640X360"}, new HUDAbstract[]{
            new HUDAbstract() {
                @Override
                public void OnClick(Button b) {
                    //1920X1080
                    Game.SetDimentions(1920, 1080);
                }
            },
            new HUDAbstract() {
                @Override
                public void OnClick(Button b) {
                    //1920X1080
                    Game.SetDimentions(1600, 900);
                }
            },
            new HUDAbstract() {
                @Override
                public void OnClick(Button b) {
                    Game.SetDimentions(1280, 720);
                }
            },
            new HUDAbstract() {
                @Override
                public void OnClick(Button b) {
                    Game.SetDimentions(860, 540);
                }
            },
            new HUDAbstract() {
                @Override
                public void OnClick(Button b) {
                    Rectangle bo = Game.g.GetFrame().getBounds();
                    Game.SetDimentions(640, 360);
                    Game.g.GetFrame().setBounds(bo.x, bo.y, 640, 360);
                }
            }
        }));
        AddObject(new BlackoutButton("To Game Solo", new HUDAbstract() {
            @Override
            public void OnClick(BlackoutButton b) {
                b.setEnabled(false);
            }
        })).setEnabled(false);
        AddObject(new Button(new Vector(0.4f, 0.1f), "fullscreen", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.FullScreen();
            }
        }));
//        GetObject(0).setScale(new Vector(0.5f, 0.7f));
    }

    @Override
    public void Update(ActionEvent ae) {
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/background.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
    }

    @Override
    public void keyPress(KeyEvent e) {

    }

    @Override
    public void keyRelease(KeyEvent e) {

    }

}
