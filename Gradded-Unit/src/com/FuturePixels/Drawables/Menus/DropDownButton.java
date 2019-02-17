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
public class DropDownButton extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    ButtonAbstract buttonDelegate;
    public final int[] indexOfSubbuttons;
    private String[] SubMessage;
    private ButtonAbstract[] LogicForSubButtons;
    private Vector AddVector;

    public DropDownButton() {
        super();
        indexOfSubbuttons = new int[0];
    }

    public DropDownButton(Vector relpos, String Message, Vector AddPerButton, String[] SubMessage, ButtonAbstract[] LogicForSubButtons) {
        super();
        this.Message = Message;
        this.relpos = relpos;
        this.AddVector = AddPerButton;
        this.SubMessage = SubMessage;
        this.LogicForSubButtons = LogicForSubButtons;
        indexOfSubbuttons = new int[this.SubMessage.length];
        for (int i = 0; i < SubMessage.length; i++) {
            Level().AddObject(new Button(new Vector(relpos).add(new Vector(AddVector).mult(i+1)), SubMessage[i], i < LogicForSubButtons.length ? LogicForSubButtons[i] : new ButtonAbstract() {
                @Override
                public void OnClick(Button b) {
                    b.setMessage("Button missing input");
                }

            }));
            indexOfSubbuttons[i] = Level().GetObjectCount() - 1;
        }
        buttonDelegate = new ButtonAbstract() {
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
        GetSprite("/Images/Button_1.png");
        DoAction();

    }

    @Override
    public void doMove() {
        setPosition(Game.g.getWindowWidth() * relpos.getX(), Game.g.getWindowHeight() * relpos.getY());
    }

    @Override
    public void Update(Graphics2D g) {
//        System.out.println("com.FuturePixels.Drawables.Menus.DropDownButton.Update() "+getPosition().toString());
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
