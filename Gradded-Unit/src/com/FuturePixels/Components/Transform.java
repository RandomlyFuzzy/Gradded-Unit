/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Components;

import com.FuturePixels.MainClasses.IComponent;
import com.FuturePixels.MainClasses.IDrawable;
import com.FuturePixels.MainClasses.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class Transform extends IComponent {

    private AffineTransform old;

    public Vector Translation = Vector.Zero();
    public Vector Scale = Vector.One();
    public double RotationZ = 0;
    private static Vector offsetTranslation = Vector.Zero();
    private static Vector WorldScale = new Vector(1, 1);

    public static Vector getOffsetTranslation() {
        return offsetTranslation;
    }

    public static void setOffsetTranslation(Vector offsetTranslation) {
        Transform.offsetTranslation = offsetTranslation;
        offsetTranslation = null;
    }

    public Transform(IDrawable parent) {
        super(parent);
    }

    @Override
    public void Init() {
        Scale = getParent().getScale();
        Translation = getParent().getPosition();
        RotationZ = getParent().getRotation();
    }

    @Override
    public void Update(Graphics2D g) {

    }

    public void PushTransforms(Graphics2D g) {
        Scale = getParent().getScale();
        Translation = getParent().getPosition();
        RotationZ = getParent().getRotation();

        old = g.getTransform();
        g.translate((((int) Translation.getX())) + offsetTranslation.getX(), (((int) Translation.getY())) + offsetTranslation.getY());
        g.rotate((RotationZ) + getParent().getOffset());
        g.scale(Scale.getX() * WorldScale.getX(), Scale.getY() * WorldScale.getY());
    }

    public static float GetWorldScaleX() {
        return WorldScale.getX();
    }

    public static float GetWorldScaleY() {
        return WorldScale.getY();
    }

    public void PopTransforms(Graphics2D g) {
        g.setTransform(old);
    }

    public Vector GetUp() {
        return new Vector((float) Math.sin(RotationZ), (float) -Math.cos(RotationZ));
    }

    public Vector GetRight() {
        return new Vector((float) Math.sin(RotationZ + Math.PI / 2), (float) -Math.cos(RotationZ + Math.PI / 2));
    }

}
