/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.GameClasses;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class Mouse extends IDrawable {

    public Mouse() {
        super();

    }

    @Override
    public void init() {
    }

    @Override
    public void doMove() {

    }

    public float Rad = 0;

    @Override
    public void Update(Graphics g) {
        Rad += Game.g.getDelta() * 100;
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform old = g2d.getTransform();

//scale -> translate -> rotate
        g2d.rotate(Rad);
        g2d.translate((int) Level().getMousePos().getX(), (int) Level().getMousePos().getY());
        g2d.drawImage(GetSprite("/Images/Cookie.png"), getSpriteWidth(), getSpriteHeight(), null);
        g2d.setTransform(old);

    }

    @Override
    public void onCollison(IDrawable im) {
    }

}
