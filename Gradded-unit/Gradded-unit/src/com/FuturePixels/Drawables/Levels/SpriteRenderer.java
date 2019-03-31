/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.AbstractClasses.IComponent;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.SpriteSheet;
import com.Liamengine.Engine.Entry.Game;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author RandomlyFuzzy
 */
public class SpriteRenderer extends IComponent {

    private BufferedImage bi = null;
    private SpriteSheet she;
    private float inc = 0.3f;

    public SpriteRenderer(IDrawable parent,float inc) {
        super(parent);
        this.inc = inc;
    }

    public void inputImage(BufferedImage im, SpriteSheet she) {
        bi = im;
        this.she = she;
        System.out.println(she.GetSegHeight() + " " + she.GetSegWidth());
        System.out.println(getParent().getSpriteWidth() + " " + getParent().getSpriteHeight());
    }

    @Override
    public void Init() {
        System.out.println("com.FuturePixels.Drawables.Levels.SpriteRenderer.Init()");
    }

    @Override
    public void Update(Graphics2D gd) {
        IDrawable id = getParent();
        
        float w = id.getSpriteWidth();
        float h = id.getSpriteHeight();
//        float w2 = she.GetSegWidth()/id.getSpriteWidth();
//        float h2 = she.GetSegHeight()/id.getSpriteHeight();
        she.IncrementX(inc);
        she.DrawFromGraphic(gd, bi, (int)(-w/2), (int) (- h / 2),(int)(w/2),(int)(she.GetSegHeight()));
    }

}
