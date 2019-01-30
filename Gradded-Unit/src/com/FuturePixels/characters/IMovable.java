/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.game.Vector;
import com.FuturePixels.levels.SetClasses.ILevel;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author RandomlyFuzzy
 */
public class IMovable {

    private Vector position;
    private ILevel from;
    private int spriteWidth;
    private int spriteHeight;

    public IMovable(ILevel From) {
        from = From;
        position = new Vector(0,0);
    }

    public ILevel From() throws Exception{
        if (from == null) {
            System.err.print("you must Super the constructor");
            throw new Exception();
        }
        return from;
    }

    public void init() {
    }

    public void move(int dir) {
    }

    public void draw(Graphics g) {
    }

    public void doMove() {
    }

    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle((int) position.getX(), (int) position.getY(), spriteWidth, spriteHeight);
        return objectRect;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

}
