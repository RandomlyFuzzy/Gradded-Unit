/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.Engine.Entry.Game;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author Liam Rickman 1748905
 */
public class Controls extends ILevel{

    @Override
    public void init() {
        setStopAudioOnStart(false);
        AddObject(new Mouse());
        AddObject(new HUD());
        AddObject(new Button(new Vector(0.5f, 0.3f), "To Game Solo", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new LevelSelect());
            }
        }));
    }

    @Override
    public void Update(ActionEvent ae) {
        
    }

    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/Start.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
    }

    @Override
    public void keyPress(KeyEvent e) {
        
    }

    @Override
    public void keyRelease(KeyEvent e) {
        
    }
    
}
