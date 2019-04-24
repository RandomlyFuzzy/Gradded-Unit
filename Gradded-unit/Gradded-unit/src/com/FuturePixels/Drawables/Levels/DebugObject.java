/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Liam Woolley 1748910
 */
public class DebugObject extends IDrawable {

    private static ArrayList<Vector> Lines = new ArrayList<Vector>();
    private static ArrayList<Vector> Cirles = new ArrayList<Vector>();

    /**
     * @param v1
     */
    public static void AddLine(Vector v1, Vector v2) {
        Lines.add(v1);
        Lines.add(v2);
    }

    /**
     *
     */
    public static void AddCirles(Vector v1) {
        Cirles.add(v1);
    }

    /**
     *
     */
    public DebugObject() {
        super();
        Lines = new ArrayList<Vector>();
        Cirles = new ArrayList<Vector>();
    }

    void Reset() {
        Lines = new ArrayList<Vector>();
        Cirles = new ArrayList<Vector>();
    }

    /**
     *
     */
    @Override
    public void Update(Graphics2D g) {
        g.setColor(new Color(255, 255, 150, 155));
        for (Vector v : Cirles) {
            g.fillOval((int) v.getX() - 5, (int) v.getY() - 5, 10, 10);
        }

        for (int i = 0; i < Lines.size(); i += 2) {
            g.drawLine((int) Lines.get(i).getX(),
                       (int) Lines.get(i).getY(),
                       (int) Lines.get(i + 1).getX(),
                       (int) Lines.get(i + 1).getY());
        }
        Reset();
    }

    /**
     *
     */
    @Override
    public void init() {
    }

    /**
     *
     */
    @Override
    public void doMove() {
    }

    /**
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
    }

}
