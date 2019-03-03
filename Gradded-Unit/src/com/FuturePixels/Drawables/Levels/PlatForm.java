/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.extraComponents.Vector;
import java.awt.Graphics2D;

/**
 *
 * @author RandomlyFuzzy
 */
public class PlatForm extends IDrawable {

    public PlatForm() {
        super();
    }

    public PlatForm(Vector position, double RadianRotation) {
        super();
        GetSprite("/images/platform/Platform.png");
        setPosition(position);
        setRotation(RadianRotation);
    }

    @Override
    public void init() {
        GetSprite("/images/platform/Platform.png");
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }

    @Override
    public void doMove() {

    }

    @Override
    public void onCollison(IDrawable im) {
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }
}
