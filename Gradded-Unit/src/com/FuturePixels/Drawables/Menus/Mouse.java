/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author RandomlyFuzzy
 */
public class Mouse extends IDrawable {

    public Mouse() {
        super();

    }
    int pos = 0;

    @Override
    public void init() {
        GetSprite("/Images/cookie.png");
        pos = HUD.AddText(""+isColliding(), new Vector(0,10));
        setScale(new Vector(0.2f,0.2f));
    }

    @Override
    public void doMove() {
//        Rad += Game.g.getDelta() * 100;
        setPosition(Level().getMousePos().getX(), Level().getMousePos().getY());
//        setRotation(Rad);
        HUD.EditText(pos,""+isColliding());
    }


    @Override
    public void Update(Graphics2D g) {
        g.drawImage(GetSprite("/Images/Cursor.png"), (int) -getSpriteWidth()/2 , (int) -getSpriteHeight()/2 , getSpriteWidth(), getSpriteHeight(), null);
    }

    @Override
    public void onCollison(IDrawable im) {
        if(im == null){
            return;
        }
        
        if(im instanceof Button&&Level().isClicking()){
            ((Button) im).DoAction();
        }
        
//        System.out.println("com.FuturePixels.GameClasses.Mouse.onCollison() with " + im.getClass().toString());
    }

}
