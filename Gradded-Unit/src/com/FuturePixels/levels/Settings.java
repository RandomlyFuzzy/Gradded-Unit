/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.MainClasses.ILevel;
import com.FuturePixels.MainClasses.Vector;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.ButtonAbstract;
import com.FuturePixels.Drawables.Menus.DropDownButton;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Entry.Game;
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

    private Mouse m;

    public Settings() {
        super();
        System.out.println("com.game.levels.Settings.<init>()");
    }

    /*
        Game 
            Button
        Audio 
            Master
        Controls 
            Player 1
            Player 2
    
    
     */
    @Override
    public void init() {
        m = new Mouse();
        AddObject(m);
        AddObject(new HUD());
        AddObject(new Button(new Vector(0.2f, 0.1f), "Back", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new DropDownButton(new Vector(0.4f, 0.2f), "Random DropDown", new Vector(0.0f, 0.1f), new String[]{"1920X1080", " 1600X900", "1280X720", "860X540", "640X360"}, new ButtonAbstract[]{
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    //1920X1080
                    Game.g.SetDimentions(1920, 1080);
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    //1920X1080
                    Game.g.SetDimentions(1600, 900);
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    Game.g.SetDimentions(1280, 720);
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    Game.g.SetDimentions(860, 540);
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    Rectangle bo = Game.g.GetFrame().getBounds();
                    Game.g.SetDimentions(640, 360);
                    Game.g.GetFrame().setBounds(bo.x, bo.y, 640, 360);
                }
            }
        }));
        AddObject(new BlackoutButton("To Game Solo", new ButtonAbstract() {
            @Override
            public void OnClick(BlackoutButton b) {
                b.setEnabled(false);
            }
        })).setEnabled(false);
        AddObject(new Button(new Vector(0.4f, 0.1f), "fullscreen", new ButtonAbstract() {
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
