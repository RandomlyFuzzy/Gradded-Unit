/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
import com.sun.javafx.geom.Vec2d;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Cookie {

    private Vector position;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    private boolean Stop = false;
    private BufferedImage sprite;
    private int score = 10;
    private long timedelay = 0;
    private long timeadded = 800;
    private Vector endpoint;
    private Vector acceration;
    private float speed = 3;

    public Cookie() {
        acceration = new Vector(0, 0);
        score = 10;
        isVisible = true;
        try {
            sprite = ImageIO.read(getClass().getResource("/Images/Cookie.png"));
            spriteWidth = sprite.getWidth()/2;
            spriteHeight = sprite.getHeight()/2;
            SetRandomPos();
        } catch (Exception ex) {
            System.err.println("Error loading treasure sprite");
        }
    }

    public void draw(Graphics g) {
        if (isVisible == true) {
            g.drawImage(sprite, (int) position.getX() - spriteWidth / 2, (int) position.getY() - spriteHeight / 2, spriteWidth, spriteHeight, null);
//            g.drawRect((int) position.getX()-spriteWidth/2, (int) position.getY()-spriteHeight/2, spriteWidth, spriteHeight);
        }

    }

    public void doMove() {
        if (System.nanoTime() / 1000000 > timedelay + timeadded) {
            reCaculateendpos();
        }
        addPosition(acceration);
    }

    public void reCaculateendpos() {
        if (Stop) {
            acceration = new Vector(0, 0);
            return;
        }

        endpoint = new Vector((float)Math.random() * Game.g.getWindowWidth() - spriteWidth, (float)(Math.random() * Game.g.getWindowWidth() - spriteHeight * 1.75f) + spriteHeight / 2);
        float x   = endpoint.getX() - position.getX(),
              y   = endpoint.getY() - position.getY(),
              C   = (float)Math.sqrt(x * x + y * y);
        acceration = new Vector(((x) / C) * speed, ((y) / C) * speed);
        timedelay = (System.nanoTime() / 1000000);
    }

    public void SetRandomPos() {
        position = new Vector((float)(Math.random() * (Game.g.getWindowHeight() - spriteWidth)), (float)(Math.random() * (Game.g.getWindowHeight()- spriteHeight * 1.75f)) + spriteHeight / 2);
    }

    public boolean IsVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        if (isVisible == false) {
            SetRandomPos();
            setVisible(true);
        }

    }

    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle((int) position.getX() - spriteWidth / 2, (int) position.getY() - spriteHeight / 2, (int) (spriteWidth * 1.5f), (int) (spriteHeight * 2.5f));
        return objectRect;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void addPosition(Vector acc) {

        if ((this.position.getX() - spriteWidth / 2 + acc.getX()) <= 0) {
            this.reCaculateendpos();
            acc.setX(0);
            this.position.setX(spriteWidth/2);
        } else if ((this.position.getX() + spriteWidth + acc.getX()) >= (Game.g.getWindowWidth())) {
            this.reCaculateendpos();
            acc.setX(0);
            this.position.setX((Game.g.getWindowWidth())- spriteWidth);
        } else if ((this.position.getY() - spriteHeight / 2 + acc.getY()) <= 0) {
            this.reCaculateendpos();
            acc.setY(0);
            this.position.setY(spriteHeight/2);
        } else if ((this.position.getY() + spriteHeight * 1.75f + acc.getY()) >= (Game.g.getWindowHeight())) {

            this.reCaculateendpos();
            acc.setY(0);
            this.position.setY(Game.g.getWindowHeight() - (spriteHeight * 1.75f));
        }

        this.position = new Vector(this.position.getX() + acc.getX(), this.position.getY() + acc.getY());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
