/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
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
    ButtonAbstract buttonDelegate;

    public Button() {
        super();
    }

    public Button(String Message, ButtonAbstract Logic) {
        super();
        this.Message = Message;
        buttonDelegate = Logic;
    }

    public Button(Vector relpos, String Message, ButtonAbstract Logic) {
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
        setPosition(Game.g.getWindowWidth() * relpos.getX(), Game.g.getWindowHeight() * relpos.getY());
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
        g.setColor(Color.red);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString(Message, -metrics.stringWidth(Message) / 2, 0);
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
      
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

}
