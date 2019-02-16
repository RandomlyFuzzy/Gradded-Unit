/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Utils.*;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.logging.Logger;

/**
 *
 * @author RandomlyFuzzy
 */
public class DropDownButton extends Button {

    private String Message = "";
    private Vector relpos = Vector.One;
    ButtonAbstract buttonDelegate;

    private int[] indexOfSubbuttons;
    private String[] SubMessage;
    private ButtonAbstract[] LogicForSubButtons;
    private Vector AddVector;

    public DropDownButton(Vector relpos, String Message, Vector AddPerButton, String[] SubMessage, ButtonAbstract[] LogicForSubButtons) {
//        super();
        super(relpos, Message, new ButtonAbstract() {
            @Override
            public void OnClick(DropDownButton b) {
                for (int i : b.indexOfSubbuttons) {
                    b.Level().GetObject(i).setEnabled(!b.Level().GetObject(i).isEnabled());
                }
            }
        });
        AddVector = AddPerButton;
        this.SubMessage = SubMessage;
        this.LogicForSubButtons = LogicForSubButtons;
    }

    @Override
    public void init() {
        GetSprite("/Images/Button_1.png");
        indexOfSubbuttons = new int[SubMessage.length];

        for (int i = 0; i < SubMessage.length; i++) {

            Level().AddObject(new Button(relpos.add(new Vector(AddVector).mult(i)), SubMessage[i], i + 1 < LogicForSubButtons.length ? LogicForSubButtons[i]:new ButtonAbstract() {
                 @Override
                 public void OnClick(Button b){
                     b.setMessage("Button missing input with message");
                 }
            
            }));
            indexOfSubbuttons[i] = Level().GetObjectCount() - 1;
        }
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

    public void OnCollison(IDrawable im) {
        if(Level().isClicking()){
            DoAction();
        }
    }

}
