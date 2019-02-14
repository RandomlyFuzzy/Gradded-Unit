/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.Utils.ILevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private int spriteWidth = 0;
    private int spriteHeight = 0;
    private float Rotation = 0;
    private Vector Scale = Vector.One;
    private boolean Enabled = true;
    private BufferedImage LastImage = null;
    private ArrayList<IComponent> Component = new ArrayList<IComponent>();
    private final Transform transform;

    //to do
    //1. add component based logic
    //2. make collisons work
    //3. make a prototype of the game 
    //4. extend and polish that game prototype
    //5. release everything
    public IDrawable() {
        position = new Vector(0, 0);
        CollisonUtils.PossableCols.add(this);
        transform = new Transform(this);
        AddComponent(transform);
    }

    public float getRotation() {
        return Rotation;
    }

    public void setRotation(float Rotation) {
        this.Rotation = Rotation;
    }

    public Vector getScale() {
        return Scale;
    }

    public void setScale(Vector Scale) {
        this.Scale = Scale;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }

    public void AddComponent(IComponent comp) {
        Component.add(comp);
        comp.Init();
    }

    public ILevel Level() {
        return Game.GetLevel();
    }

    public Rectangle getBounds() {
        Rectangle objectRect
                = new Rectangle(
                        (int) (getPosition().getX() - (((spriteWidth) / 2f) * Scale.getX())),
                        (int) (getPosition().getY() - ((spriteHeight / 2f) * Scale.getY())),
                        (int) ((spriteWidth) * Scale.getX()),
                        (int) ((spriteHeight) * Scale.getY()));
        return objectRect;
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector addPosition(Vector v) {
        return this.position.add(v);
    }

    public BufferedImage GetSprite(String URI) {
        LastImage = imageUtils.T.GetImage(URI);
        if (LastImage != null) {
            this.spriteWidth = LastImage.getWidth();
            this.spriteHeight = LastImage.getHeight();
        }
        return LastImage;
    }

    //used a memory snap shot and found that this is a big proble for memory usage in this program
    public void setPosition(float X,float Y) {
        this.position.setX(X);
        this.position.setY(Y);
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    @Override
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

    void CoreInit() {
        init();
        initComponents();
    }

    void CoreUpdate(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        doMove();
        transform.Update(g);
        transform.PushTransforms(g);
        Update(g);
        UpdateComponents(g);
        transform.PopTransforms(g);
    }

    void initComponents() {
        if (Component.isEmpty()) {
            return;
        }
        Component.forEach((a) -> {
            a.Init();
        });
    }

    void UpdateComponents(Graphics2D g) {
        if (Component.isEmpty()) {
            return;
        }
        Component.forEach((a) -> {
            a.Update(g);
        });
    }

    public void DrawLastLoadedImage(Graphics2D g) {
        if (LastImage == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.err.println("error Drawing last image as their was not last image in " + e.getStackTrace()[1]);
            }
        }
        g.drawImage(LastImage, -getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight(), null);
    }

    public abstract void init();

    public abstract void doMove();

    public abstract void Update(Graphics2D g);

    public abstract void onCollison(IDrawable im);

    public void dispose() {
        LastImage = null;
        Component.forEach((a) -> {
            a.dispose();
        });
        for (int i = Component.size() - 1; i > 0; i--) {
            Component.remove(i);
        }
    }
}
