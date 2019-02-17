/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Components;

import com.FuturePixels.Utils.IComponent;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Utils.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class Transform extends IComponent {
 
    
    private AffineTransform old;

    public Vector Scale = Vector.One();
    public Vector Translation = Vector.Zero();
    public Vector offsetTranslation = Vector.Zero();
    public float RotationZ = 0;

    public Transform(IDrawable parent) {
        super(parent);
    }

    @Override
    public void Init() {
    }

    @Override
    public void Update(Graphics2D g) {

    }

    public void PushTransforms(Graphics2D g) {
        Scale = getParent().getScale();
        Translation = getParent().getPosition();
        RotationZ = getParent().getRotation();
        
        old = g.getTransform();
        g.translate((int) Translation.getX()+offsetTranslation.getX(), (int) Translation.getY()+offsetTranslation.getY());
        g.scale(Scale.getX(), Scale.getY());
        g.rotate((RotationZ) + getParent().getOffset());
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
