/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
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
    
    public PlatForm(){
        super();
        setPosition(new Vector(200,300));
    }
    @Override
    public void init() {
        GetSprite("/images/Cookie.png");
    }


    @Override
    public void draw(Graphics g) {
        Rad += Game.g.getDelta() * 100;
        Graphics2D g2d = (Graphics2D) g;


        //push matrix
        AffineTransform old = g2d.getTransform();

        //scale -> translate -> rotate
        
        
        g2d.translate((int) getPosition().getX(), (int) getPosition().getY() );
        g2d.rotate(Rad);

        g2d.drawImage(GetSprite("/images/Cookie.png"), -((getSpriteWidth()/2) ), -(getSpriteHeight()/2), getSpriteWidth() , getSpriteHeight(), null);

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
