/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Components;

import com.FuturePixels.Drawables.Levels.Player;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.IComponent;
import com.FuturePixels.MainClasses.IDrawable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class RigidBody extends IComponent {

    public RigidBody(IDrawable parent) {
        super(parent);
    }

    @Override
    public void Init() {
        System.out.println("com.FuturePixels.Components.RigidBody.Init()");
    }

    @Override
    public void Update(Graphics2D g) {
        Player p = (Player) getParent();
        float offset = getParent().GetRight().getY();
        if (!getParent().isColliding()) {
        } else {
        }
//        p.Velocity.add(p.GetRight().mult(1 - offset).mult(0.981f).mult((float) Game.g.getDelta()));
//        p.Acc.add(p.GetUp().mult(-1).mult(0.981f).mult((float) Game.g.getDelta()));
    }

}
