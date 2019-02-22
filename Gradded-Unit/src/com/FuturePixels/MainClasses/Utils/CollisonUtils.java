/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses.Utils;

import com.FuturePixels.MainClasses.Components.Collison;
import com.FuturePixels.MainClasses.AbstractClasses.IDrawable;
import com.FuturePixels.MainClasses.Components.Vector;
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
     * check for line intersections using ray-casting or linecasting theory
     * Some things are still needed to be done to make this usable 
     */
    public static Collison CheckForLineHits(Vector a, Vector b, Vector c, Vector d) {

        Vector r = new Vector(b.getX() - a.getX(), b.getY() - a.getY());
        Vector s = new Vector(d.getX() - c.getX(), d.getY() - c.getY());

        double g = r.getX() * s.getY() - r.getY() * s.getX();

        double u = (((c.getX() - a.getX()) * r.getY()) - ((c.getY() - a.getY()) * r.getX())) / g;
        double t = (((c.getX() - a.getX()) * s.getY()) - ((c.getY() - a.getY()) * s.getX())) / g;

        if (0 <= u && u <= 1 && t >= 0 && t <= 1) {
            return new Collison(
                    new Vector((a.getX() + ((float)t * r.getX()))
                    , (a.getY() + ((float)t * r.getY()))));
        }

        return new Collison();
    }

    /*
        this is just for testing purposes with that in regards to this script 
    
    */
    public static void main(String args[]) {
        System.out.println(
                CheckForLineHits(new Vector(0, 0), new Vector(30, 30)
                , new Vector(30, 0), new Vector(0, 30)).IsHit);

    }
}
