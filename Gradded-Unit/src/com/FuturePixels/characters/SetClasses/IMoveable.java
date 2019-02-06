/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters.SetClasses;

import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.game.Vector;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.levels.SetClasses.ILevel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class IMoveable {

    private Vector position;
    private ILevel from;
    private HashMap<String, Image> Sprites = new HashMap<String, Image>();
    private int spriteWidth;
    private int spriteHeight;
    private boolean Enabled = true;

    public IMoveable(ILevel From) {
        from = From;
        position = new Vector(0, 0);
    }

    public ILevel From() throws Exception {
        if (from == null) {
            System.err.print("you must Super the constructor");
            throw new Exception();
        }
        return from;
    }

    public abstract void init();

    public abstract void doMove();

    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle((int) position.getX(), (int) position.getY(), spriteWidth, spriteHeight);
        return objectRect;
    }

    public Vector getPosition() {
        return position;
    }

    public BufferedImage GetSprite(String URI) {
        BufferedImage g = imageUtils.T.GetImage(URI);
        if (g != null) {
            this.spriteWidth = g.getWidth();
            this.spriteHeight = g.getHeight();
        }
        return g;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public String toString() {
        return getClass().toString() + " at " + getPosition().getX() + " ," + getPosition().getY();
    }

    public boolean IsVisible() {
        return Enabled;
    }

    public void SetVisible(boolean vis) {
        Enabled = vis;
    }

    
    
    
    
    
    public boolean CheckCollions(IMoveable t) {
        if (t.getBounds().intersects(getBounds())) {
            if (t.IsVisible() == true) {
                onCollison(t);
            }
            return true;
        } else {
            return false;
        }
    }

    public abstract void onCollison(IMoveable im);
}
