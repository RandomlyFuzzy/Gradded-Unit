/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import java.awt.Graphics2D;

/**
 *
 * @author RandomlyFuzzy
 */
public class Lava extends IDrawable {

    @Override
    public void init() {
    }

    @Override
    public void doMove() {
    }

    @Override
    public void Update(Graphics2D g) {
    }

    @Override
    public void onCollison(IDrawable im) {
        if(im instanceof Player){
            System.out.println("Playercollider");
        
        }
    }
}
