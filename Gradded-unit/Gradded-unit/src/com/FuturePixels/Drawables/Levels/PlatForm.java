/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.AbstractClasses.IDrawable; 
import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Entry.Game;
import java.awt.Graphics2D;

/**
 *The platform is what the character walks on
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
     *Sets platform properties
     * @param position
     * @param RadianRotation
     */
    public PlatForm(Vector position, double RadianRotation) {
        super();
        setPosition(position);
        setRotation(RadianRotation);
        
    }

    /**
     *Initialises platform images
     */
    @Override
    public void init() {
        GetSprite("/images/platform/platform.png");
    }

    /**
     *Updates the image every frame
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        if (((-Transform.getOffsetTranslation().getX() - (Game.getScaledWidth()) < getPosition().getX()
                && (-Transform.getOffsetTranslation().getX() + (Game.getScaledWidth())) > getPosition().getX()
                && (-Transform.getOffsetTranslation().getY() - (Game.getScaledHeight())) < getPosition().getY()
                && (-Transform.getOffsetTranslation().getY() + (Game.getScaledHeight())) > getPosition().getY()))) {
            DrawLastLoadedImage(g);
            
        }
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
    }
}
