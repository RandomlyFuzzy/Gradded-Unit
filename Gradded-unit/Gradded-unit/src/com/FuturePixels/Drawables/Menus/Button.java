/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Button extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    private HUDdelegate buttonDelegate;
    private boolean Offset = true;

    /**
     *
     */
    public Button() {
        super();
        UseTransforms(false);
    }

    /**
     *
     * @param relpos
     * @param Message
     * @param Logic
     */
    public Button(Vector relpos, String Message, HUDdelegate Logic) {
        super();
        this.Message = Message;
        this.buttonDelegate = Logic;
        this.relpos = relpos;
    }

    public Button(Vector relpos, String Message, HUDdelegate Logic, boolean usetras) {
        this(relpos, Message, Logic);
        Offset = (usetras);
    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/Images/Button_1.png");
    }

    /**
     *
     */
    @Override
    public void doMove() {
        if (Offset) {
            setPosition(new Vector(((Game.getScaledWidth())) * relpos.getX(), ((Game.getScaledHeight())) * relpos.getY())
                    .add(new Vector(Transform.getOffsetTranslation()).mult(1.5f/Game.WorldScale().getY()).mult(-1)));
        } else {
            setPosition(new Vector(((Game.getScaledWidth())) * relpos.getX(), ((Game.getScaledHeight())) * relpos.getY()));
        }
        setScale(Game.ButtonDims());
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        if (Level().getTime() < (1f / 50f)) {
            return;
        }
        DrawLastLoadedImage(g);

        Font f = g.getFont();
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(Color.white);
        if (getSpriteWidth() < metrics.stringWidth(Message)) {
            setSpriteWidth((int) (metrics.stringWidth(Message) * 1.05f));
        }
        g.drawString(Message, -metrics.stringWidth(Message) / 2, +g.getFont().getSize() / 2);

        g.setFont(f);
        if (isColliding()) {
            Color c = g.getColor();
            g.setColor(new Color(200, 200, 200, 100));
            g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
            g.setColor(c);
        }
    }

    /**
     *
     */
    public void DoAction() {
        if (buttonDelegate != null) {
            Level().play("/Sounds/UiClick.wav");
            buttonDelegate.OnClick(this);
        } else {
            System.err.println("error no delegate in this button");
        }
    }

    /**
     *
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Button) {
            setIsColliding(false);
        }
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return this.Message;
    }

    /**
     *
     * @param Message
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

}
