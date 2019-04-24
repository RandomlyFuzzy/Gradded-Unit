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
import java.util.Random;

/**
 *
 * @author Liam Woolley 1748910
 */
public class DestroyingPlatForm extends IDrawable {

    private boolean haslanded = false;
    private Random forsounds = new Random();
    private int r2 = forsounds.nextInt(2) + 1;

    /**
     *
     */
    public DestroyingPlatForm() {
        super();
    }

    /**
     *Loads platform position and calls method to set position
     */
    public DestroyingPlatForm(Vector position, double RadianRotation) {
        super();
        GetSprite("/images/platform/platform.png");
        setPosition(position);
        setRotation(RadianRotation);
    }

    /**
     *Initialises platform
     */
    @Override
    public void init() {
        GetSprite("/images/platform/platform.png");

    }


    @Override
    public void Update(Graphics2D g) {
        if (((-Transform.getOffsetTranslation().getX() - (Game.getScaledWidth()) < getPosition().getX()
                && (-Transform.getOffsetTranslation().getX() + (Game.getScaledWidth())) > getPosition().getX()
                && (-Transform.getOffsetTranslation().getY() - (Game.getScaledHeight())) < getPosition().getY()
                && (-Transform.getOffsetTranslation().getY() + (Game.getScaledHeight())) > getPosition().getY()))) {
            DrawLastLoadedImage(g);
            if (haslanded && !isColliding()) {
                Level().play("/sounds/break" + r2 + ".wav");
                setEnabled(false);
            }
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
        if (im instanceof Player && (((Player) im).getVelocity().getY() == 0)) {
            haslanded = true;
        }
//        
    }
}
