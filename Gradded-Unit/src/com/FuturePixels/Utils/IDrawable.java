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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
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
    private boolean Enabled = true, isColliding = false;

    public boolean isColliding() {
        return isColliding;
    }

    public void setIsColliding(boolean isColliding) {
        this.isColliding = isColliding;
    }
    private BufferedImage LastImage = null;
    private ArrayList<IComponent> Component = new ArrayList<IComponent>();
    private final Transform transform;

    float a1,
            a2,
            a3,
            a4;
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
        UpdateBounds();
        this.Rotation = Rotation;
    }

    public Vector getScale() {
        return Scale;
    }

    public void setScale(Vector Scale) {
        UpdateBounds();
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

    public Vector GetUp() {
        return transform.GetUp();
    }

    public Vector GetRight() {
        return transform.GetRight();
    }

    public void UpdateBounds() {
        float hy = (float) Math.sqrt((getSpriteWidth() / 2 * getSpriteWidth() / 2) + (getSpriteHeight() / 2 * getSpriteHeight() / 2));
        a1 = (float) Math.atan2(getSpriteHeight() / 2, getSpriteWidth() / 2);
        a2 = (float) Math.atan2(-getSpriteHeight() / 2, getSpriteWidth() / 2);
        a3 = (float) Math.atan2(-getSpriteHeight() / 2, -getSpriteWidth() / 2);
        a4 = (float) Math.atan2(getSpriteHeight() / 2, -getSpriteWidth() / 2);
        v1 = new Vector((int) (getPosition().getX() + (float) Math.cos(a1 - getRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a1 - getRotation()) * hy * Scale.getY()));
        v2 = new Vector((int) (getPosition().getX() + (float) Math.cos(a2 - getRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a2 - getRotation()) * hy * Scale.getY()));
        v3 = new Vector((int) (getPosition().getX() + (float) Math.cos(a3 - getRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a3 - getRotation()) * hy * Scale.getY()));
        v4 = new Vector((int) (getPosition().getX() + (float) Math.cos(a4 - getRotation()) * hy * Scale.getX()), (int) (getPosition().getY() + (float) -Math.sin(a4 - getRotation()) * hy * Scale.getY()));
    }

    public Polygon getBounds() {
//        Rectangle objectRect = new Rectangle(
//                        (int) (getPosition().getX() - (((spriteWidth) / 2f) * Scale.getX())),
//                        (int) (getPosition().getY() - ((spriteHeight / 2f) * Scale.getY())),
//                        (int) ((spriteWidth) * Scale.getX()),
//                        (int) ((spriteHeight) * Scale.getY()));

        Polygon g = new Polygon();

        if (v1 == null) {
            UpdateBounds();
        }

        g.addPoint((int) (v1.getX()), (int) (v1.getY()));
        g.addPoint((int) (v2.getX()), (int) (v2.getY()));
        g.addPoint((int) (v3.getX()), (int) (v3.getY()));
        g.addPoint((int) (v4.getX()), (int) (v4.getY()));
        g.addPoint((int) (v1.getX()), (int) (v1.getY()));

        return g;
    }

    public boolean checkForIntersections(Polygon g) {
        return g.contains(v1.getX(), v1.getY()) || g.contains(v2.getX(), v2.getY()) || g.contains(v3.getX(), v3.getY()) || g.contains(v4.getX(), v4.getY());
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
            UpdateBounds();
        }
        return LastImage;
    }

    //used a memory snap shot and found that this is a big proble for memory usage in this program
    public void setPosition(float X, float Y) {
        this.position.setX(X);
        this.position.setY(Y);
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

    public boolean IsVisible() {
        return Enabled;
    }

    public void SetVisible(boolean vis) {
        Enabled = vis;
    }

    public boolean CheckCollions(IDrawable t) {
        if (checkForIntersections(t.getBounds())) {
            System.out.println("com.FuturePixels.Utils.IDrawable.CheckCollions()");
            if (t.IsVisible() == true) {
                onCollison(t);
                return true;
            }
        }
        return false;
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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);

        g2d.drawLine((int) getPosition().getX(),
                (int) getPosition().getY(),
                (int) (getPosition().getX() + (GetUp().getX()) * 20),
                (int) (getPosition().getY() + (GetUp().getY()) * 20));
        g2d.drawLine((int) getPosition().getX(),
                (int) getPosition().getY(),
                (int) (getPosition().getX() + (GetRight().getX() * 20)),
                (int) ((getPosition().getY() + (GetRight().getY() * 20))));

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

    public void dispose() {
        LastImage = null;
        Component.forEach((a) -> {
            a.dispose();
        });
        for (int i = Component.size() - 1; i > 0; i--) {
            Component.remove(i);
        }
    }

    public abstract void init();

    public abstract void doMove();

    public abstract void Update(Graphics2D g);

    public abstract void onCollison(IDrawable im);
}
