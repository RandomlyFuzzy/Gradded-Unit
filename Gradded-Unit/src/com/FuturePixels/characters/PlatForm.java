/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.characters.SetClasses.IMoveable;
import com.FuturePixels.characters.SetClasses.IMoveableInterface;
import com.FuturePixels.game.Game;
import com.FuturePixels.levels.SetClasses.ILevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class PlatForm extends IMoveable implements IMoveableInterface{
        
    private float Rad = 0f;
    
    public PlatForm(ILevel From){
        super(From);
    }
    @Override
    public void init() {
        GetSprite("/resources/images/Cookie.png");
    }


    @Override
    public void draw(Graphics g) {
//        deg += Game.g.getDelta() * 100;
        Graphics2D g2d = (Graphics2D) g;


        //push matrix
        AffineTransform old = g2d.getTransform();

        //scale -> translate -> rotate
        
        
        g2d.translate((int) getPosition().getX(), (int) getPosition().getY() + (getSpriteHeight() * 1.5));
        g2d.rotate(Rad);

        g2d.drawImage(GetSprite("/resources/images/Cookie.png"), -((getSpriteWidth() / 2) ), -(getSpriteHeight()) / 3, getSpriteWidth() , getSpriteHeight(), null);

        //pop matrix
        g2d.setTransform(old);
    }

    @Override
    public void doMove() {
    }

    @Override
    public void onCollison(IMoveable im) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
