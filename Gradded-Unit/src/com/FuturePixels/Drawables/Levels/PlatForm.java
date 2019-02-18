/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.MainClasses.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Vector;
import com.FuturePixels.MainClasses.ILevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class PlatForm extends IDrawable {


    public PlatForm() {
        super();
    }
    
    public PlatForm(Vector position,float RadianRotation){
        super();
        setPosition(position.getX(), position.getY());
        setRotation(RadianRotation);
    }

    @Override
    public void init() {
//        GetSprite("/images/Cookie.png");
        setOffset((float) -Math.PI);
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
//        g.drawImage(GetSprite("/images/Platform.png"), -((getSpriteWidth() / 2)), -(getSpriteHeight() / 2), getSpriteWidth(), getSpriteHeight(), null);
    }

    @Override
    public void doMove() {

    }

    @Override
    public void onCollison(IDrawable im) {
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }
}
