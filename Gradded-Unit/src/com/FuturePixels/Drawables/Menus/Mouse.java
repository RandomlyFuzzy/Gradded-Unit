/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.MainClasses.AbstractClasses.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Components.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class Mouse extends IDrawable {
    
    private boolean clicked = false;

    public Mouse() {
        super();
    }

    @Override
    public void init() {
        GetSprite("/Images/Cursor.png");
        setScale(new Vector(0.2f, 0.2f));
    }

    @Override
    public void doMove() {
//        Rad += Game.g.getDelta() * 100;
        setPosition(Level().getMousePos());
//        setRotation(Rad);
        clicked = clicked != !Level().isClicking() && clicked;
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }

    @Override
    public void onCollison(IDrawable im) {
        if (im == null) {
            return;
        }

        if (im instanceof Button && Level().isClicking() && !clicked) {
            clicked = true;
            ((Button) im).DoAction();
        }
        if (im instanceof DropDownButton && Level().isClicking() && !clicked) {
            clicked = true;
            ((DropDownButton) im).DoAction();
        }
        if (im instanceof BlackoutButton && Level().isClicking() && !clicked) {
            clicked = true;
            ((BlackoutButton) im).DoAction();
        }
    }

}
