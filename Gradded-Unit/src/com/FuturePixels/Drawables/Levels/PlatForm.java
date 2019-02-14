/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
import com.FuturePixels.Utils.ILevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class PlatForm extends IDrawable {

    private float Rad = 0f;

    public PlatForm() {
        super();
        setPosition(200, 300);
    }

    @Override
    public void init() {
        GetSprite("/images/Cookie.png");
    }

    @Override
    public void Update(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g;
        Rad += Game.g.getDelta() * 100;

        //push matrix
        AffineTransform old = g2d.getTransform();

        //scale -> translate -> rotate
        g2d.translate((int) getPosition().getX(), (int) getPosition().getY());
        g2d.rotate(Rad);

        g2d.drawImage(GetSprite("/images/Platform.png"), -((getSpriteWidth() / 2)), -(getSpriteHeight() / 2), getSpriteWidth(), getSpriteHeight(), null);

        //pop matrix
        g2d.setTransform(old);
    }

    @Override
    public void doMove() {

    }

    @Override
    public void onCollison(IDrawable im) {
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }

}
