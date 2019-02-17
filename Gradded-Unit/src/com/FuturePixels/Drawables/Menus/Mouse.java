/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class Mouse extends IDrawable {

    public Mouse() {
        super();
        GetSprite("/Images/Cursor.png");

    }
    int pos = 0;

    @Override
    public void init() {
        GetSprite("/Images/Cursor.png");
        System.out.println("com.FuturePixels.Drawables.Menus.Mouse.init()");
        pos = HUD.AddText("" + isColliding(), new Vector(0, 10));
        setScale(new Vector(0.2f, 0.2f));
    }

    @Override
    public void doMove() {
//        Rad += Game.g.getDelta() * 100;
        setPosition(Level().getMousePos().getX(), Level().getMousePos().getY());
//        setRotation(Rad);
        HUD.EditText(pos, "" + isColliding());
        clicked = clicked != !Level().isClicking() && clicked;
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }
    boolean clicked = false;

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
//        System.out.println("com.FuturePixels.GameClasses.Mouse.onCollison() with " + im.getClass().toString());
    }

}
