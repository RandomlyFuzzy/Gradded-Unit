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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 *
 * @author Liam Woolley 1748910
 */
public class BlackoutButton extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    private HUDdelegate buttonDelegate;

    /**
     *
     */
    public BlackoutButton() {
        super();
    }

    /**
     *
     * @param Message
     * @param Logic
     */
    public BlackoutButton(String Message, HUDdelegate Logic) {
        super();
        this.Message = Message;
        buttonDelegate = Logic;
    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/Images/Button_0.png");
    }

    /**
     *
     */
    @Override
    public void doMove() {
        setPosition(Game.getScaledWidth() / 2, Game.getScaledHeight() / 2);
        setSpriteWidth(Game.getScaledWidth());
        setSpriteHeight(Game.getScaledHeight());
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
//        DrawLastLoadedImage(g);
        g.setColor(new Color(200,200,200,100));
        g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
        g.setColor(Color.red);
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        g.drawString(Message, -metrics.stringWidth(Message) / 2, 0);
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

    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return Message;
    }

    /**
     *
     * @param Message
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

}
