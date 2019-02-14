/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Vector {

    public static final Vector Zero = new Vector(0, 0), One = new Vector(1, 1);

    private float x, y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector v) {
        x = v.getX();
        y = v.getY();
        v = null;
    }

    public Vector add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
        v = null;
        return this;
    }

    public Vector addto(Vector v) {
        Vector v2 = new Vector(this);
        v2.x += v.getX();
        v2.y += v.getY();
        v = null;
        return v2;
    }

    public Vector mult(Vector v) {
        this.x *= v.getX();
        this.y *= v.getY();
        v =null;
        return this;
    }

    public void setToVector(Vector v) {
        x = v.getX();
        y = v.getY();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void addY(float y) {
        this.y += y;
    }

    public void addX(float x) {
        this.x += x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString() {
        return "" + this.x + "," + this.y;
    }
}
