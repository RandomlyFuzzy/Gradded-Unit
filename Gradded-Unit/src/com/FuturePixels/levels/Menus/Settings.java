/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Drawables.Menus.DropDownButton;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Drawables.Menus.Slider;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Utils.MusicUtils;
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

    private static BlackoutButton BB = null;
    private static ILevel level;
    private Button B = null;

    public Settings() {
        super();
        System.out.println("com.game.levels.Settings.<init>()");
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        level = this;
        AddObject(new Mouse());

        BB = AddObject(new BlackoutButton("Player1 Left", new HUDAbstract() {
            @Override
            public void OnClick(BlackoutButton b) {
                b.setEnabled(false);
            }
        }));
//                .setEnabled(false);

        AddObject(new HUD());
        AddObject(new Button(new Vector(0.2f, 0.1f), "Back", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new Slider(new Vector(0.55f, 0.1f), 0.0465f, new HUDAbstract() {
            @Override
            public void OnChange(Slider s, float Value) {
                System.out.println(".OnChange() " + Value);
                ILevel.setFPS(30 + (int)(Value*60f));
//                MusicUtils.ChangeMasterVolume(Value);
            }
        }));

        AddObject(new DropDownButton(new Vector(0.4f, 0.2f), "Random DropDown", new Vector(0.0f, 0.1f), new String[]{"1920X1080", " 1600X900", "1280X720", "860X540", "640X360"},
                new HUDAbstract[]{
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

        AddObject(new Button(new Vector(0.4f, 0.1f), "fullscreen", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.FullScreen();
            }
        }));
//        GetObject(0).setScale(new Vector(0.5f, 0.7f));

    }

    public static void SetToCurrent(int ind){
        BB = (BlackoutButton)level.GetObject(ind);
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
        if (BB != null) {
            BB.setMessage("" + e.getKeyChar());
//            B.setMessage(B.getMessage().substring(0,B.getMessage().length()-2)+e.getKeyChar());
        }
    }

    @Override
    public void keyRelease(KeyEvent e) {

    }

}
