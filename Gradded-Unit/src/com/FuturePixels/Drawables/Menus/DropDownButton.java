/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Engine.extraComponents.Transform;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.extraComponents.Vector;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 *
 * @author RandomlyFuzzy
 */
public class DropDownButton extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    private HUDdelegate buttonDelegate;
    public final int[] indexOfSubbuttons;
    private String[] SubMessage;
    private HUDdelegate[] LogicForSubButtons;
    private Vector AddVector;

    public DropDownButton() {
        super();
        indexOfSubbuttons = new int[0];
    }

    public DropDownButton(Vector relpos, String Message, Vector AddPerButton, String[] SubMessage, HUDdelegate[] LogicForSubButtons) {
        super();
        this.Message = Message;
        this.relpos = relpos;
        this.AddVector = AddPerButton;
        this.SubMessage = SubMessage;
        this.LogicForSubButtons = LogicForSubButtons;
        indexOfSubbuttons = new int[this.SubMessage.length];
        for (int i = 0; i < SubMessage.length; i++) {
            Level().AddObject(new Button(new Vector(relpos).add(new Vector(AddVector).mult(i + 1)), SubMessage[i], i < LogicForSubButtons.length ? LogicForSubButtons[i] : new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    b.setMessage("Button missing input");
                }

            }));
            indexOfSubbuttons[i] = Level().GetObjectCount() - 1;
        }
        buttonDelegate = new HUDdelegate() {
            @Override
            public void OnClick(DropDownButton b) {
                for (int i : b.indexOfSubbuttons) {
                    b.Level().GetObject(i).setEnabled(!b.Level().GetObject(i).isEnabled());
                }
            }
        };
    }

    @Override
    public void init() {
        GetSprite("/Images/DropButton.png");
        DoAction();

    }

    @Override
    public void doMove() {
        setPosition(new Vector(((Game.getScaledWidth())) * relpos.getX(), ((Game.getScaledHeight())) * relpos.getY()).add(new Vector(Transform.getOffsetTranslation()).mult(-1)));

        setScale(Game.ButtonDims());

    }

    @Override
    public void Update(Graphics2D g) {
//        System.out.println("com.FuturePixels.Drawables.Menus.DropDownButton.Update() "+getPosition().toString());
        DrawLastLoadedImage(g);
        g.setColor(new Color(128, 0, 128));
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

    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

}
