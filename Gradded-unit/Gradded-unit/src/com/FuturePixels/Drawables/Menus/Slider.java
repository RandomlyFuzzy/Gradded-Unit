/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Slider extends IDrawable {

    private float length = 1, value = 0,lastval=0;
    private Vector relpos = Vector.One();
    private HUDdelegate Delegate;
    private Vector MouseWhenPressed = new Vector(0, 0);
    private Vector MoveAmt = new Vector(0, 0);
    private boolean inside = false;
    /**
     *
     */
    public Slider() {
//        super();
    }

    /**
     *
     * @param relpos
     * @param Length
     * @param Logic
     */
    public Slider(Vector relpos, float Length, HUDdelegate Logic) {
        super();
        this.length = Game.getWindowWidth()*Length;
        this.Delegate = Logic;
        this.relpos = relpos;
    }

    /**
     *
     */
    @Override
    public void init() {

    }

    /**
     *
     */
    @Override
    public void doMove() {
        setPosition(Game.getScaledWidth()* relpos.getX(), Game.getScaledHeight()* relpos.getY());
        setScale(Game.ButtonDims());

        if (Level().isClicking() && inside) {
            MouseWhenPressed = Level().getMousePos();
        } else if (Level().isDragging() && inside) {
            MoveAmt = new Vector(MouseWhenPressed).add(new Vector(Level().getMousePos()).mult(-1));
        } else {
//            lastval = value;
//            value =0;
            inside = false;
        }
        if ((MoveAmt.getX() > length ? length : MoveAmt.getX() < -length ? -length : MoveAmt.getX()) != value) {
            value = (MoveAmt.getX() > length ? length : MoveAmt.getX() < -length ? -length : MoveAmt.getX());
            Delegate.OnChange(this, GetValue());
        }
        setPosition((Game.getScaledWidth() * relpos.getX()) - (lastval+value), (Game.getScaledHeight() * relpos.getY()));
    }

    /**
     *
     * @return
     */
    public float GetValue() {
        return 1 - ((value + length) / length) / 2;
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
//        GetSprite("/images/Slider_Back.png");
//        g.drawImage(getLastImage(), (int) (-(getSpriteWidth()) / 2) , (int) (-(getSpriteHeight()) / 2) , (int) (getSpriteWidth()), (int) (getSpriteHeight()), null);
        GetSprite("/images/slider_knob.png");
        DrawLastLoadedImage(g);
        g.setColor(Color.red);
        if (isColliding()) {
            Color c = g.getColor();
            g.setColor(new Color(200, 200, 200, 100));
            g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
            g.setColor(c);
        }
    }


    /**
     *
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Mouse) {
            inside = true;
        }
    }

}
