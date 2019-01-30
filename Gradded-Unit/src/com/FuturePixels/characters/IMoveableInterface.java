/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

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

    public void move(int dir) ;

    public void draw(Graphics g) ;

    public void doMove() ;

    public Rectangle getBounds() ;

    public Vector getPosition() ;

    public void setPosition(Vector position) ;

    public void setSpriteWidth(int spriteWidth) ;

    public int getSpriteHeight() ;

    public void setSpriteHeight(int spriteHeight) ;

    public int getSpriteWidth() ;
}
