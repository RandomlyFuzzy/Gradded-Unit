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
 *
 * @author Liam Woolley 1748910
 */
public class MovingPlatoform extends IDrawable {

    private Vector[] Cycle;
    private float Speed;
    private int ind = 0;
    private Vector Add = Vector.Zero();

    /**
     *
     */
    public MovingPlatoform() {
        super();
    }

    /**
     *
     * @param position
     * @param RadianRotation
     * @param cycle
     * @param Speed
     */
    public MovingPlatoform(Vector position, double RadianRotation, Vector[] cycle, float Speed) {
        super();
//        GetSprite("/images/platform/Platform.png");
        setPosition(position);
        setRotation(RadianRotation);
        this.Cycle = cycle;
        this.Speed = Speed;
    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/images/Platform/rock_platform_moss_01.png");
    }

    /**
     *
     */
    @Override
    public void doMove() {
        Vector dist = getPosition().add(new Vector(Cycle[ind]).mult(-1));

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
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }

    /**
     *
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
        if(im instanceof Player){
            im.addPosition(new Vector(Add).mult(2));
        }
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }
}
