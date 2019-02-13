/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
import com.FuturePixels.Utils.ILevel;
import com.sun.javafx.geom.Vec2d;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Cookie extends IDrawable {

    private boolean isVisible;
    private boolean Stop = false;
    private BufferedImage sprite;
    private int score = 10;
    private Vector endpoint;
    private Vector acceration;
    private float speed = 3;

    public Cookie() {
        super();
        acceration = new Vector(0, 0);
        score = 10;
        isVisible = true;
    }

    @Override
    public void init() {
        this.setPosition(new Vector(Game.g.getWindowWidth()/2,Game.g.getWindowHeight()/2));
    }

    @Override
    public void Update(Graphics g) {
        if (isVisible == true) {
            g.drawImage(GetSprite("/Images/Cookie.png"), (int) getPosition().getX() - getSpriteWidth() / 2, (int) getPosition().getY() - getSpriteWidth() / 2, getSpriteWidth(), getSpriteHeight(), null);
            g.drawRect((int) getPosition().getX() - getSpriteWidth() / 2, (int) getPosition().getY() - getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight());
        }

    }

    public void doMove() {
        addvel(acceration);
    }

    public boolean IsVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        if (isVisible == false) {
            setVisible(true);
        }
    }

    public void addvel(Vector acc) {

        if ((this.getPosition().getX() - getSpriteWidth() / 2 + acc.getX()) <= 0) {
            acc.setX(0);
            this.getPosition().setX(getSpriteWidth() / 2);
        } else if ((this.getPosition().getX() + getSpriteWidth() + acc.getX()) >= (Game.g.getWindowWidth())) {
            acc.setX(0);
            this.getPosition().setX((Game.g.getWindowWidth()) - getSpriteWidth());
        } else if ((this.getPosition().getY() - getSpriteHeight() / 2 + acc.getY()) <= 0) {
            acc.setY(0);
            this.getPosition().setY(getSpriteHeight() / 2);
        } else if ((this.getPosition().getY() + getSpriteHeight() * 1.75f + acc.getY()) >= (Game.g.getWindowHeight())) {
            acc.setY(0);
            this.getPosition().setY(Game.g.getWindowHeight() - (getSpriteHeight() * 1.75f));
        }

        this.setPosition(new Vector(this.getPosition().getX() + acc.getX(), this.getPosition().getY() + acc.getY()));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void onCollison(IDrawable im) {
        
    }

}
