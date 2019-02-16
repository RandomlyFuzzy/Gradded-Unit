/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Components;

import com.FuturePixels.Utils.IComponent;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Utils.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author RandomlyFuzzy
 */
public class DebugComponent extends IDrawable {

    private static ArrayList<Vector> Lines = new ArrayList<Vector>();
    private static ArrayList<Vector> Cirles = new ArrayList<Vector>();

    public static void AddLine(Vector v1, Vector v2) {
        Lines.add(v1);
        Lines.add(v2);
    }

    public static void AddCirles(Vector v1) {
        Cirles.add(v1);
    }

    public DebugComponent() {
        super();
    }

    void Reset() {
        Lines = new ArrayList<Vector>();
        Cirles = new ArrayList<Vector>();
    }

    @Override
    public void Update(Graphics2D g) {
        for (Vector v : Cirles) {
            g.fillOval((int) v.getX() - 5, (int) v.getX() - 5, 10, 10);
        }

        for (int i = 0; i < Lines.size(); i += 2) {
            g.drawLine((int) Lines.get(i).getX(),
                     (int) Lines.get(i).getY(),
                     (int) Lines.get(i + 1).getX(),
                     (int) Lines.get(i + 1).getY());
        }
        Reset();
    }

    @Override
    public void init() {
    }

    @Override
    public void doMove() {
    }

    @Override
    public void onCollison(IDrawable im) {
    }

}
