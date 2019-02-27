/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.Engine.Entry.Game;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author Liam Rickman 1748905
 */
public class Controls extends ILevel {

    Vector relataiveity = Vector.Zero();

    @Override
    public void init() {
        setStopAudioOnStart(false);
        AddObject(new Mouse());
        AddObject(new HUD());
        AddObject(new Button(new Vector(0.1f, 0.1f), "Back", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }));
        //PLAYER 1
        AddObject(new Button(new Vector(0.15f, 0.3f), "LEFT = A", new HUDAbstract() {
        }));
        AddObject(new Button(new Vector(0.15f, 0.4f), "RIGHT = D", new HUDAbstract() {
        }));
        AddObject(new Button(new Vector(0.15f, 0.5f), "JUMP = SPACE", new HUDAbstract() {
        }));

        //PLAYER 2
        AddObject(new Button(new Vector(0.5f, 0.3f), "LEFT = Left Arrow", new HUDAbstract() {
        }));
        AddObject(new Button(new Vector(0.5f, 0.4f), "RIGHT = Right Arrow", new HUDAbstract() {
        }));
        AddObject(new Button(new Vector(0.5f, 0.5f), "JUMP = Up Arrow", new HUDAbstract() {
        }));
    }

    @Override
    public void Update(ActionEvent ae) {
        relataiveity = GamePreferences.ButtonDims();
    }

    @Override
    public void Draw(Graphics2D g) {
        //g.drawImage(GetSprite("/Images/Start.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);

        //Player Text
        g.setColor(Color.red);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Player 1 Controls", Game.g.getWindowWidth() * 0.15f-metrics.stringWidth("Player 1 Controls") / 2, Game.g.getWindowHeight() * 0.23f);
        g.drawString("Player 2 Controls", Game.g.getWindowWidth() * 0.5f-metrics.stringWidth("Player 2 Controls") / 2, Game.g.getWindowHeight() * 0.23f);
    }

    @Override
    public void keyPress(KeyEvent e) {

    }

    @Override
    public void keyRelease(KeyEvent e) {

    }

}
