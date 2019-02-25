/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class End extends IDrawable {

    public End() {
        super();
    }

    public End(Vector position) {
        super();
        setPosition(position);
    }

    @Override
    public void init() {
        if (getLastImage() == null) {
            GetSprite("/images/Platform.png");
        }
        setOffset((float) -Math.PI);
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
        g.drawImage(getLastImage(), -((getSpriteWidth() / 2)), -(getSpriteHeight() / 2), getSpriteWidth(), getSpriteHeight(), null);
    }

    @Override
    public void doMove() {

    }

    @Override
    public void onCollison(IDrawable im) {
        if(im == null){
            return;
        }
        
        if(im instanceof Player){
            
        }
    }
}
