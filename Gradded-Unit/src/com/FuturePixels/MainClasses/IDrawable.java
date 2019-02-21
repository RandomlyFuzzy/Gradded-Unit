/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.MainClasses.imageUtils;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.MainClasses.ILevel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class IDrawable {

    private Vector position;
    private int spriteWidth = 0;
    private int spriteHeight = 0;

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }
    private double Rotation = 0, offset = 0;

    private Vector Scale = Vector.One();
    private boolean Enabled = true, isColliding = false, isCollidable = true, useTransforms = true;

    public boolean IsCollidable() {
        return isCollidable;
    }

    public void setIsCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
    }

    public boolean getUseTransforms() {
        return useTransforms;
    }

    public void setUseTransforms(boolean useTransforms) {
        this.useTransforms = useTransforms;
    }

    private BufferedImage LastImage = null;
    private ArrayList<IComponent> Component = new ArrayList<IComponent>();
    private Transform transform;

    private Vector v1,
            v2,
            v3,
            v4;

    //to do
    //1. add component based logic
    //2. make collisons work
    //3. make a prototype of the game 
    //4. extend and polish that game prototype
    //5. release everything
    private boolean hasSupered = false;

    public IDrawable() {
        transform = new Transform(this);
        position = new Vector(0, 0);
//        AddComponent(transform);
        hasSupered = true;
    }

    public double getRotation() {
        return Rotation;
    }

    public Vector getScale() {
        return new Vector(Scale);
    }

    public void setRotation(double Rotation) {
        UpdateBounds();
        this.Rotation = Rotation;
    }

    public void setScale(Vector Scale) {
        UpdateBounds();
        this.Scale = Scale;
        Scale = null;
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

    public Vector GetUp() {
        return transform.GetUp();
    }

    public Vector GetRight() {
        return transform.GetRight();
    }

    public double getTotalRotation() {
        return getRotation() + getOffset();
    }

    public void UpdateBounds() {

        float hy = (float) Math.sqrt((getSpriteWidth() / 2 * getSpriteWidth() / 2) + (getSpriteHeight() / 2 * getSpriteHeight() / 2)),
                a1 = (float) Math.atan2(getSpriteHeight() / 2, getSpriteWidth() / 2),
                a2 = (float) Math.atan2(-getSpriteHeight() / 2, getSpriteWidth() / 2),
                a3 = (float) Math.atan2(-getSpriteHeight() / 2, -getSpriteWidth() / 2),
                a4 = (float) Math.atan2(getSpriteHeight() / 2, -getSpriteWidth() / 2);
        v1 = new Vector((int) (getPosition().getX() + (float) Math.cos(a1 - getTotalRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a1 - getTotalRotation()) * hy * Scale.getY()));
        v2 = new Vector((int) (getPosition().getX() + (float) Math.cos(a2 - getTotalRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a2 - getTotalRotation()) * hy * Scale.getY()));
        v3 = new Vector((int) (getPosition().getX() + (float) Math.cos(a3 - getTotalRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a3 - getTotalRotation()) * hy * Scale.getY()));
        v4 = new Vector((int) (getPosition().getX() + (float) Math.cos(a4 - getTotalRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a4 - getTotalRotation()) * hy * Scale.getY()));
    }

    public Polygon getBounds() {
//        Rectangle objectRect = new Rectangle(
//                        (int) (getPosition().getX() - (((spriteWidth) / 2f) * Scale.getX())),
//                        (int) (getPosition().getY() - ((spriteHeight / 2f) * Scale.getY())),
//                        (int) ((spriteWidth) * Scale.getX()),
//                        (int) ((spriteHeight) * Scale.getY()));

        Polygon g = new Polygon();

        if (v1 == null || v2 == null || v3 == null || v4 == null) {
            UpdateBounds();
        }

        g.addPoint((int) (v1.getX()), (int) (v1.getY()));
        g.addPoint((int) (v2.getX()), (int) (v2.getY()));
        g.addPoint((int) (v3.getX()), (int) (v3.getY()));
        g.addPoint((int) (v4.getX()), (int) (v4.getY()));
        g.addPoint((int) (v1.getX()), (int) (v1.getY()));

        return g;
    }

    BufferedImage GetImage(String URI) {
        return imageUtils.T.GetImage(URI);
    }

    public void SetImage(String name, BufferedImage img) {
        imageUtils.T.setImage(name, img);
    }

    public boolean checkForIntersections(Polygon g) {
        if (v1 == null || v2 == null || v3 == null || v4 == null) {
            UpdateBounds();
            g = getBounds();
        }
        return g.contains(v1.getX(), v1.getY()) || g.contains(v2.getX(), v2.getY()) || g.contains(v3.getX(), v3.getY()) || g.contains(v4.getX(), v4.getY());
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector addPosition(Vector v) {
        return this.position.add(v);
    }

    public BufferedImage GetSprite(String URI) { 
        LastImage = GetImage(URI);
        this.spriteWidth = LastImage.getWidth();
        this.spriteHeight = LastImage.getHeight();
        UpdateBounds();
        return LastImage;
    }

    public void setPosition(float X, float Y) {
        this.position.setX(X);
        this.position.setY(Y);
        UpdateBounds();
    }

    public void setPosition(Vector v) {
        this.position.setX(v.getX());
        this.position.setY(v.getY());
        UpdateBounds();
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

    public <T extends IComponent> T getComponent(T g) {
        T ret = null;
        System.out.println("com.FuturePixels.Utils.IDrawable.getComponent() " + g.getClass().toString());
        for (IComponent t : Component) {
            if (t.getClass().toString().equals(g.getClass().toString())) {
                System.out.println("com.FuturePixels.Utils.IDrawable.getComponent()");
                return (T) t;
            }
        }
        return ret;
    }

    public boolean CheckCollions(IDrawable t) {
        if (checkForIntersections(t.getBounds())) {
//            System.out.println("com.FuturePixels.Utils.IDrawable.CheckCollions()");
            if (t.isEnabled() == true) {
                onCollison(t);
                return true;
            }
        }
        return false;
    }

    void CoreInit() {
        if (!hasSupered) {
            UtilManager.FindUseClass(3);
            System.err.println("you must super this " + (transform != null) + position.toString());
        }
//        GetSprite("/images/default.png");
        init();

    }

    void CoreUpdate(Graphics g2) {
        if (!isEnabled()) {
            return;
        }

        Graphics2D g = (Graphics2D) g2;
        doMove();
        if (getUseTransforms()) {
            transform.Update(g);
            transform.PushTransforms(g);
        }
        Update(g);
        if (getUseTransforms()) {
            transform.PopTransforms(g);
        }
        if (!Level().isDebugCollisons()) {//) || (getSpriteWidth() + getSpriteHeight() == 0)) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);

        g2d.drawLine((int) Transform.getOffsetTranslation().getX() + (int) getPosition().getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) getPosition().getY(),
                (int) Transform.getOffsetTranslation().getX() + (int) (getPosition().getX() + (GetUp().getX()) * 20),
                (int) Transform.getOffsetTranslation().getY() + (int) (getPosition().getY() + (GetUp().getY()) * 20));
        g2d.drawLine((int) Transform.getOffsetTranslation().getX() + (int) getPosition().getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) getPosition().getY(),
                (int) Transform.getOffsetTranslation().getX() + (int) (getPosition().getX() + (GetRight().getX() * 20)),
                (int) Transform.getOffsetTranslation().getY() + (int) ((getPosition().getY() + (GetRight().getY() * 20))));

        Vector[] _left = sideLeft();
        Vector[] _right = sideRight();
        Vector[] _Top = sideUp();
        Vector[] _down = sideDown();
        g.drawLine((int) Transform.getOffsetTranslation().getX() + (int) _left[0].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _left[0].getY(),
                (int) Transform.getOffsetTranslation().getX() + (int) _left[1].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _left[1].getY());
        g.drawLine((int) Transform.getOffsetTranslation().getX() + (int) _right[0].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _right[0].getY(),
                (int) Transform.getOffsetTranslation().getX() + (int) _right[1].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _right[1].getY());
        g.drawLine((int) Transform.getOffsetTranslation().getX() + (int) _Top[0].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _Top[0].getY(),
                (int) Transform.getOffsetTranslation().getX() + (int) _Top[1].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _Top[1].getY());
        g.drawLine((int) Transform.getOffsetTranslation().getX() + (int) _down[0].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _down[0].getY(),
                (int) Transform.getOffsetTranslation().getX() + (int) _down[1].getX(),
                (int) Transform.getOffsetTranslation().getY() + (int) _down[1].getY());

    }

    public void DrawLastLoadedImage(Graphics2D g) {
        if (LastImage == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.err.println("error Drawing last image as their was not last image in " + e.getStackTrace()[1] + " try pre loading it in init() to get rid of this warning");
            }
        } else {
            g.drawImage(LastImage, -getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight(), null);
        }
    }

    public BufferedImage getLastImage() { 
        MediaTracker mediaTracker = new MediaTracker(Level());
        mediaTracker.addImage(LastImage, 0);
        try {
            mediaTracker.waitForAll();
        } catch (InterruptedException interuptedException) {
            interuptedException.printStackTrace();
        }
        return LastImage;
    }

    public void dispose() {
        LastImage = null;
        ArrayList<IComponent> c = Component;
        Component = new ArrayList<IComponent>();
        for (int i = Component.size() - 1; i > 0; i--) {
            Component.remove(i);
        }
        c.forEach((a) -> {
            a.dispose();
        });
    }

    public Vector[] sideUp() {
        if (v3 == null || v2 == null) {
            UpdateBounds();
        }
        return new Vector[]{v3, v2};
    }

    public Vector[] sideLeft() {
        if (v3 == null || v4 == null) {
            UpdateBounds();
        }
        return new Vector[]{v3, v4};
    }

    public Vector[] sideDown() {
        if (v4 == null || v1 == null) {
            UpdateBounds();
        }
        return new Vector[]{v4, v1};
    }

    public Vector[] sideRight() {
        if (v2 == null || v1 == null) {
            UpdateBounds();
        }
        return new Vector[]{v1, v2};
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setIsColliding(boolean isColliding) {
        this.isColliding = isColliding;
    }

    public abstract void init();

    public abstract void doMove();

    public abstract void Update(Graphics2D g);

    public abstract void onCollison(IDrawable im);
}
