/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Components;

import com.FuturePixels.MainClasses.AbstractClasses.IComponent;
import com.FuturePixels.MainClasses.AbstractClasses.IDrawable;
import com.FuturePixels.MainClasses.Components.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author RandomlyFuzzy
 */
public class BackgroundDrawer extends IComponent {

    private Vector Startpos;
    private String[] Images;
    private float[] per;

    public BackgroundDrawer(IDrawable parent, String[] img, float[] percentMoving) {
        super(parent);
        Startpos = parent.getPosition();
        Images = img;
        per = percentMoving;
    }

    @Override
    public void Init() {
    }

    @Override
    public void Update(Graphics2D g) {
        String img = getParent().getLastimageAddress();

        for (int i = 0; i < Images.length; i++) {
            getParent().GetSprite(Images[i]);
            float AddX = getParent().getPosition().add(new Vector(Startpos).mult(-1)).mult(per[i]).getX();
            float AddY = getParent().getPosition().add(new Vector(Startpos).mult(-1)).mult(per[i]).getY();
            g.drawImage(getParent().getLastImage(), (int) -(getParent().getSpriteWidth()) / 2+(int)AddX, (int) -(getParent().getSpriteHeight()) / 2+((int)AddY), (int) (getParent().getSpriteWidth()), (int) (getParent().getSpriteHeight()), null);
        }
        getParent().GetSprite(img);
    }

}
