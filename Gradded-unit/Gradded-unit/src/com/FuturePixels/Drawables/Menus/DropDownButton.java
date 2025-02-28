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
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Liam Woolley 1748910
 */
public class DropDownButton extends IDrawable {

    private String Message = "";
    private Vector relpos = Vector.One();
    private HUDdelegate buttonDelegate;

    /**
     *
     */
    public final int[] INDEXOFSUBUTONS;
    private String[] SubMessage;
    private HUDdelegate[] LogicForSubButtons;
    private Vector AddVector;

    /**
     *
     */
    public DropDownButton() {
        super();
        INDEXOFSUBUTONS = new int[0];
    }

    /**
     *
     * @param relpos
     * @param Message
     * @param AddPerButton
     * @param SubMessage
     * @param LogicForSubButtons
     */
    public DropDownButton(Vector relpos, String Message, Vector AddPerButton, String[] SubMessage, HUDdelegate[] LogicForSubButtons) {
        super();
        this.Message = Message;
        this.relpos = relpos;
        this.AddVector = AddPerButton;
        this.SubMessage = SubMessage;
        this.LogicForSubButtons = LogicForSubButtons;
        INDEXOFSUBUTONS = new int[this.SubMessage.length];
        for (int i = 0; i < SubMessage.length; i++) {
            Level().AddObject(new Button(new Vector(relpos).add(new Vector(AddVector).mult(i + 1)), SubMessage[i], i < LogicForSubButtons.length ? LogicForSubButtons[i] : new HUDdelegate() {
                @Override
                public void OnClick(Button b) {
                    b.setMessage("Button missing input");
                }

            })).GetSprite("/images/button_1.png");
            INDEXOFSUBUTONS[i] = Level().GetObjectCount() - 1;
            
        }
        buttonDelegate = new HUDdelegate() {
            @Override
            public void OnClick(DropDownButton b) {
                
                for (int i = 0; i < b.INDEXOFSUBUTONS.length; i++) {
                    Button b2 = (Button) b.Level().GetObject(b.INDEXOFSUBUTONS[i]);
                    b2.setEnabled(!b2.isEnabled());
                }
            }
        };

    }

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/images/dropbuttonlong.png");
        DoAction();

    }

    /**
     *
     */
    @Override
    public void doMove() {
        setPosition(new Vector(((Game.getScaledWidth())) * relpos.getX(), ((Game.getScaledHeight())) * relpos.getY()).add(new Vector(Transform.getOffsetTranslation()).mult(-1)));
        setScale(new Vector(0.9f,0.9f));
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
//        
        FontMetrics metrics = g.getFontMetrics();
        DrawLastLoadedImage(g);
        g.setColor(Color.white);
        g.drawString(Message, -metrics.stringWidth(Message) / 2, +g.getFont().getSize() / 3);

        if (isColliding()) {
            Color c = g.getColor();
            g.setColor(new Color(200, 200, 200, 100));
            g.fillRect(-getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
            g.setColor(c);
        }
        g.setColor(Color.white);
    }

    /**
     *
     */
    public void DoAction() {
        try {
            if (buttonDelegate != null) {
                buttonDelegate.OnClick(this);
//      Level().play("/sounds/uiclick.wav");
            } else {
                System.err.println("error no delegate in this button");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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
