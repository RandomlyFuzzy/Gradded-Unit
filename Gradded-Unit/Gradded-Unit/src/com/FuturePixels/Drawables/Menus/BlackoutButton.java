/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

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
public class BlackoutButton extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    private HUDAbstract buttonDelegate;

    public BlackoutButton() {
        super();
    }

    public BlackoutButton(String Message, HUDAbstract Logic) {
        super();
        this.Message = Message;
        buttonDelegate = Logic;
    }

    @Override
    public void init() {
        GetSprite("/Images/Button_0.png");
    }

    @Override
    public void doMove() {
        setPosition(Game.g.getWindowWidth() / 2, Game.g.getWindowHeight() / 2);
        setSpriteWidth(Game.g.getWindowWidth());
        setSpriteHeight(Game.g.getWindowHeight());
    }

    @Override
    public void Update(Graphics2D g) {
//        DrawLastLoadedImage(g);
        g.setColor(new Color(200,200,200,100));
        g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
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
