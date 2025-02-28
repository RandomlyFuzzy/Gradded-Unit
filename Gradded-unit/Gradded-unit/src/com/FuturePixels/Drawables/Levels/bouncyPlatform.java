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
 * This makes the player bounce up vertically upon collision
 * @author Liam Woolley 1748910
 */
public class bouncyPlatform extends IDrawable {

    private Vector[] Cycle;
    private float Speed;
    private int ind = 0;
    private Vector Add = Vector.Zero();

    /**
     *
     */
    public bouncyPlatform() {
        super();
    }

    /**
     * 
     * @param position
     * @param RadianRotation
     * @param cycle
     * @param Speed
     */
    public bouncyPlatform(Vector position, double RadianRotation, Vector[] cycle, float Speed) {
        super();

        setPosition(position);
        setRotation(RadianRotation);
        this.Cycle = cycle;
        this.Speed = Speed;
    }

    /**
     *Initialises platform image
     */
    @Override
    public void init() {
        GetSprite("/images/platform/rock_platform_moss_01.png");
    }

    /**
     * 
     */
    @Override
    public void doMove() {
        Vector dist = getPosition().add(new Vector(Cycle[ind]).mult(-1));

        //Sets position, rotation and bounce speed and updates
        if (dist.Length() <= Speed * 2) {
            ind++;
            ind = ind % Cycle.length;
        }
        Add = dist.Normalized().mult(-Speed);
        Vector newpos = getPosition().add(Add);
        DebugObject.AddCirles(Cycle[ind]);
        setPosition(newpos);
        UpdateBounds();
    }

    /**
     * Updates platform image every frame
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
     * Sets movement when player bounces and plays sound
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
        if(im instanceof Player){
            Level().play("/sounds/bounce.wav");
            Player p = ((Player) im);
            if(p.getVelocity().getY()<=0){
                p.setVelocity(GetUp().mult(-10));
            }
        }
//        
    }
}
