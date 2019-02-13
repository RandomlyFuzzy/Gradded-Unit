/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.Utils.ILevel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class IDrawable {

    private Vector position;
    private int spriteWidth;
    private int spriteHeight;
    private boolean Enabled = true;

    public ArrayList<IComponent> Component = new ArrayList<IComponent>();

    //to do
    //1. add component based logic
    //2. make collisons work
    //3. make a prototype of the game 
    //4. extend and polish that game prototype
    //5. release everything
    public IDrawable() {
        position = new Vector(0, 0);
        CollisonUtils.PossableCols.add(this);
    }

    public ILevel Level() {
        return Game.GetLevel();
    }

    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle((int) getPosition().getX() - spriteWidth / 2, (int) getPosition().getY() - spriteHeight / 2, spriteWidth, spriteHeight);
        return objectRect;
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector addPosition(Vector v) {
        return this.position.add(v);
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

    public boolean CheckCollions(IDrawable t) {
        if (t.getBounds().intersects(getBounds())) {
            if (t.IsVisible() == true) {
                onCollison(t);
            }
            return true;
        } else {
            return false;
        }
    }

    void initComponents() {
        if (Component.size() == 0) {
            return;
        }
        Component.forEach((a) -> {
            a.Init();
        });
    }

    void UpdateComponents() {
        if (Component.size() == 0) {
            return;
        }
        Component.forEach((a) -> {
            a.Update();
        });
    }

    public abstract void init();

    public abstract void doMove();

    public abstract void Update(Graphics g);

    public abstract void onCollison(IDrawable im);
}
