/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels;

import com.FuturePixels.Utils.ILevel;
import com.FuturePixels.Utils.Vector;
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
        AddObject(new Button(new Vector(0.2f, 0.1f), "Back", new ButtonAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
        AddObject(new DropDownButton(new Vector(0.4f, 0.0f), "To Leaderboard", new Vector(0.0f, 0.1f), new String[]{"1", "2", "3", "4", "5"}, new ButtonAbstract[]{
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    System.out.println(b.getMessage());
                }
            }
                ,
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    System.out.println(b.getMessage());
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    System.out.println(b.getMessage());
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    System.out.println(b.getMessage());
                }
            },
            new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    System.out.println(b.getMessage());
                }
            }

        }));

        AddObject(m);

//        GetObject(0).setScale(new Vector(0.5f, 0.7f));

    }

    @Override
    public void Update(ActionEvent ae) {
        this.repaint();
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
