/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.ButtonAbstract;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.ILevel;
import com.FuturePixels.MainClasses.LevelLoader;
import com.FuturePixels.MainClasses.Vector;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author RandomlyFuzzy
 */
public class LevelSelect extends ILevel {

    public LevelSelect() {
        super();
    }

    @Override
    public void init() {
        for (int i = 0; i < 2; i++) {
            AddObject(new Button(new Vector(((0.15f * (i % 6)) + 0.1f), ((0.1f * (i / 6)) + 0.1f)), ("Level" + (i + 1)), new ButtonAbstract() {
                public void OnClick(Button b) {
                    new LevelLoader(b.getMessage());
                }
            }));
        }
        AddObject(new Mouse());
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
