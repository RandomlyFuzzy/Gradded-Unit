/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.Utils.ILevel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.imageio.ImageIO;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Player extends IDrawable {

    private Vector veclocity;
    private Vector displacement;
    private float ind = 0;
    private boolean Stop = true, canJump = false, hasJumped;
    private int Scale = 1;
    private long score;
    private long now;

    public Player() {
        super();
        setPosition(new Vector(100, 100));
        veclocity = new Vector(0, 0);
        displacement = new Vector(0, 0);
        score = 0;
        init();

    }

    public void init() {

        for (int i = 0; i < 7; i++) {
            GetSprite("/Images/Player/sprite_" + i + ".png");
        }
        now = System.nanoTime() / 1000000000;
    }
    private int moveDir = 0;

    public void move(int dir) {
        moveDir = dir;
    }

    private void movePlayer(int dir) {
        boolean one = true, two = true;
        if ((dir & 1) == 1) {
            displacement.addY(-1);
//        } else if ((dir & 2) == 2) {
//            displacement.setY(1);
        } else {
//            displacement.setY(0);
//            veclocity.mult(new Vector(1, 0.3f));
            one = false;
        }
        displacement.setY((float) Math.max(-0.3, Math.min(displacement.getY(), 0.5f)));
        if ((dir & 4) == 4) {
            Scale = -1;
            displacement.addX(-1);
        } else if ((dir & 8) == 8) {
            Scale = 1;
            displacement.addX(1);
        } else {
            displacement.setX(0);
            veclocity.mult(new Vector(0.93f, 1));
            two = false;
        }
        displacement.setX((float) Math.max(-0.1, Math.min(displacement.getX(), 0.1)));

        veclocity.add(displacement);

        Stop = !one && !two;
    }

    public void stop() {
        Stop = true;
        displacement.setX(0);
        displacement.setY(0);
    }
    float deg = 0;

    @Override
    public void draw(Graphics g) {
        deg += Game.g.getDelta() * 100;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Score:" + score, 0, 20);

        movePlayer(moveDir);
        addGravity(true);
        ind += Stop ? -ind : 0.1f;
        ind = ind % 7;

//        for(int i = 0;i<Game.g.getWindowHeight();i++){
//            for(int j = 0;j<Game.g.getWindowHeight();j++){
//                if(getBounds().contains(i, j)){
//                    g2d.drawRect(i, j, 1,1);
//                }
//            }
//        }
        //push matrix
        AffineTransform old = g2d.getTransform();

//scale -> translate -> rotate
        g2d.translate((int) getPosition().getX(), (int) getPosition().getY());
//        g2d.rotate(deg);

        g2d.drawImage(GetSprite("/Images/Player/sprite_" + ((int) ind) + ".png"), -((getSpriteWidth() / 2) * (int) Scale), -(getSpriteHeight()) / 2, getSpriteWidth() * (int) Scale, getSpriteHeight(), null);

        //pop matrix
        g2d.setTransform(old);
    }

    public void addGravity(boolean reset) {
        //todo check Colisions
        if (this.getPosition().getY() > Game.g.getWindowHeight() / 2) {
            if (reset) {
                this.veclocity = new Vector(this.veclocity.getX(), 0);
                this.setPosition(new Vector(this.getPosition().getX(), Game.g.getWindowHeight() / 2));
            }
            return;
        } else {
            this.displacement.add(new Vector(0, 9.81f * (float) Game.g.getDelta()));
        }

    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void doMove() {
        getPosition().add(veclocity.add(displacement));
        veclocity.mult(new Vector(0.96f, 0.96f));
    }

    @Override
    public void onCollison(IDrawable im) {
        if (im == null) {
            return;
        }

        if (im instanceof Cookie) {
            score += ((Cookie) im).getScore();
            im.SetVisible(false);
            try {
                From().play(getClass().getResourceAsStream("/sounds/music.wav"));
            } catch (Exception e) {
                System.err.println(" error playing sound");
            }
        }
    }
}
