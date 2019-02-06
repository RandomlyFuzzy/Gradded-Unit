/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters.SetClasses;

import com.FuturePixels.game.Vector;
import com.FuturePixels.levels.SetClasses.ILevel;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author RandomlyFuzzy
 */
public interface IMoveableInterface {

    public void init();

    public void draw(Graphics g);

    public Rectangle getBounds();

    public Vector getPosition();

    public void setPosition(Vector position);

    public int getSpriteHeight();

    public int getSpriteWidth();
}
