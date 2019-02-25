/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.MainClasses.AbstractClasses.IDrawable;
import com.FuturePixels.MainClasses.Components.Vector;
import java.awt.Graphics2D;

/**
 *
 * @author RandomlyFuzzy
 */
public class MovingPlatoform extends IDrawable {

    private Vector[] Cycle;
    private float Speed;
    private int ind = 0;

    public MovingPlatoform() {
        super();
    }

    public MovingPlatoform(Vector position, double RadianRotation, Vector[] cycle, float Speed) {
        super();
//        GetSprite("/images/platform/Platform.png");
        setPosition(position);
        setRotation(RadianRotation);
        this.Cycle = cycle;
        this.Speed = Speed;
    }

    @Override
    public void init() {
        GetSprite("/images/Platform/rock_platform_moss_01.png");
    }

    @Override
    public void doMove() {
        Vector dist = getPosition().add(new Vector(Cycle[ind]).mult(-1));

        if (dist.Length() <= Speed * 2) {
            ind++;
            ind = ind % Cycle.length;
        }
        Vector newpos = getPosition().add(dist.Normalized().mult(-Speed));
        DebugObject.AddCirles(Cycle[ind]);
        setPosition(newpos);
        UpdateBounds();
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }

    @Override
    public void onCollison(IDrawable im) {
//        System.out.println("com.FuturePixels.characters.PlatForm.onCollison()");
    }
}
