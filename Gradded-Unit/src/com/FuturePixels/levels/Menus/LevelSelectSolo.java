/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Utils.LevelLoader;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;

/**
 *
 * @author Liam Woolley 1748910
 */
public class LevelSelectSolo extends ILevel {


    public LevelSelectSolo() {
        super();
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.2f * (i % 5)) + 0.1f), ((0.1f * (i / 6)) + 0.1f)), ("Level" + (i + 1)) + "Solo", new HUDdelegate() {
                public void OnClick(Button b) {
                    LevelLoader.LoadLevel(b.getMessage());
                }
            }));
        }
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu());
            }
        }));
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
