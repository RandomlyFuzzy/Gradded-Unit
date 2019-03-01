/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Vector;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 *
 * @author RandomlyFuzzy
 */
public class Button extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    private HUDAbstract buttonDelegate;

    public Button() {
        super();
        UseTransforms(false);

    }

    public Button(String Message, HUDAbstract Logic) {
        super();
        this.Message = Message;
        buttonDelegate = Logic;
    }

    public Button(Vector relpos, String Message, HUDAbstract Logic) {
        super();
        this.Message = Message;
        this.buttonDelegate = Logic;
        this.relpos = relpos;
    }

    @Override
    public void init() {
        GetSprite("/Images/Button_0.png");
    }

    @Override
    public void doMove() {
        setPosition(new Vector(((Game.g.getScaledWidth() ))* relpos.getX(), ((Game.g.getScaledHeight() ))* relpos.getY()).add(new Vector(Transform.getOffsetTranslation()).mult(-1)));
        setScale(GamePreferences.ButtonDims());
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
        g.setColor(new Color(128,0,128));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(Message, -metrics.stringWidth(Message) / 2, 0);
        if (isColliding()) {
            Color c = g.getColor();
            g.setColor(new Color(200, 200, 200, 100));
            g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
            g.setColor(c);
        }
    }

    public void DoAction() {
        if (buttonDelegate != null) {
            buttonDelegate.OnClick(this);
        } else {
            System.err.println("error no delegate in this button");
        }
    }

    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Button) {
            setIsColliding(false);
        }
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

}
