/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

import com.FuturePixels.game.Vector;
import java.util.ArrayList;

/**
 *
 * @author RandomlyFuzzy
 */
public class CollisonUtils {

    static ArrayList<IDrawable> PossableCols = new ArrayList<IDrawable>();

    public static void Release() {
        PossableCols = new ArrayList<IDrawable>();
        System.gc();
    }

    /**
     * check for line intersections using raycasting or linecasting theory
     */
    public static Collison CheckForLineHits(Vector a, Vector b, Vector c, Vector d) {

        Vector r = new Vector(b.getX() - a.getX(), b.getY() - a.getY());
        Vector s = new Vector(d.getX() - c.getX(), d.getY() - c.getY());

        float g = r.getX() * s.getY() - r.getY() * s.getX();

        float u = (((c.getX() - a.getX()) * r.getY()) - ((c.getY() - a.getY()) * r.getX())) / g;
        float t = (((c.getX() - a.getX()) * s.getY()) - ((c.getY() - a.getY()) * s.getX())) / g;

        if (0 <= u && u <= 1 && t >= 0 && t <= 1) {
            return new Collison(new Vector((a.getX() + (t * r.getX())), (a.getY() + (t * r.getY()))));
        }

        return new Collison();
    }

    public static void main(String args[]) {
        System.out.println(CheckForLineHits(new Vector(0, 0), new Vector(30, 30), new Vector(30, 0), new Vector(0, 30)).hitLocation.toString());

    }
}
