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

    public Vector Scale = Vector.One;
    public Vector Translation= Vector.Zero;
    public float RotationZ = 0;

    public Transform(IDrawable parent) {
        super(parent);
    }

    @Override
    public void Init() {
    }

    @Override
    public void Update(Graphics2D g) {
        Scale = getParent().getScale();
        Translation = getParent().getPosition();
        RotationZ = getParent().getRotation();
    }

    public void PushTransforms(Graphics2D g) {
        old = g.getTransform();
        g.scale(Scale.getX(), Scale.getY());
        g.translate((int) Translation.getX(), (int) Translation.getY());
        g.rotate(RotationZ);
    }

    public void PopTransforms(Graphics2D g) {
        g.setTransform(old);
    }

}
