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
import java.awt.Graphics2D;

/**
 * Adds white to screen upon pressing particular buttons
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
     * Initialise button image
     */
    @Override
    public void init() {
        GetSprite("/images/button_0.png");
    }

    /**
     * Set attributes of button
     */
    @Override
    public void doMove() {
        setPosition(Game.getScaledWidth() / 2, Game.getScaledHeight() / 2);
        setSpriteWidth(Game.getScaledWidth());
        setSpriteHeight(Game.getScaledHeight());
    }

    /**
     *Update button image
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        
        
        g.setColor(new Color(200,200,200,100));
        g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
        g.setColor(Color.red);
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        g.drawString(Message, -metrics.stringWidth(Message) / 2, 0);
    }

    /**
     * On click, play sound and perform action, print error msg if problem
     */
    public void DoAction() {
        if (buttonDelegate != null) {
            Level().play("/sounds/uiclick.wav");
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
