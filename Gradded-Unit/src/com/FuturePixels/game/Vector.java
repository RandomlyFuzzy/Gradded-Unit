/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.game;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Vector {

    private float x, y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector v) {
        x = v.getX();
        y = v.getY();
    }

    public Vector add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
        return this;
    }

    public Vector mult(Vector v) {
        this.x *= v.getX();
        this.y *= v.getY();
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

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
