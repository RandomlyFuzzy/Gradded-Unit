/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Liam Woolley 1748910
 */
public class DestroyingPlatForm extends IDrawable {

    boolean haslanded = false;
    private Random forsounds = new Random();

    public DestroyingPlatForm() {
        super();
    }

    public DestroyingPlatForm(Vector position, double RadianRotation) {
        super();
        GetSprite("/images/platform/Platform.png");
        setPosition(position);
        setRotation(RadianRotation);
    }

    @Override
    public void init() {
        GetSprite("/images/platform/Platform.png");
        
    }

    
                int r2 = forsounds.nextInt(2) + 1;
                
                
                           
    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
        if (haslanded && !isColliding()) {
            Level().play("/Sounds/Break" + r2 + ".wav");
            setEnabled(false);
        }
    }

    @Override
    public void doMove() {

    }

    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Player&&(((Player)im).Velocity.getY()==0)) {
            haslanded = true;
        }
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }
}
