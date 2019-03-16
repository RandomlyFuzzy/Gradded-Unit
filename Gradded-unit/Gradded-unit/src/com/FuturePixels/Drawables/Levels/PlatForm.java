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

/**
 *drawddraw
 * @author Liam Woolley 1748910
 */
public class PlatForm extends IDrawable {

    /**
     *
     */
    public PlatForm() {
        super();
    }

    /**
     *
     * @param position
     * @param RadianRotation
     */
    public PlatForm(Vector position, double RadianRotation) {
        super();
        GetSprite("/images/platform/Platform.png");
        setPosition(position);
        setRotation(RadianRotation);
        
    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/images/platform/Platform.png");
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }

    /**
     *
     */
    @Override
    public void doMove() {

    }

    /**
     *
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }
}
