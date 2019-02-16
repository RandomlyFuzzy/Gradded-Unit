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
public class DebugComponent extends IComponent {

    private AffineTransform old;

    public Vector Scale = Vector.One;
    public Vector Translation = Vector.Zero;
    public float RotationZ = 0;

    public DebugComponent(IDrawable parent) {
        super(parent);
    }

    @Override
    public void Init() {
    }

    @Override
    public void Update(Graphics2D g) {
        Vector[] left = getParent().sideLeft(),right = getParent().sideRight(), up = getParent().sideUp(),down = getParent().sideDown();
        g.drawLine(0, 0, 0, 0);
        
        
        
    }

  

}
