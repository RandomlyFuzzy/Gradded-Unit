/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Vector;
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
    private int ScoreInd = 0;
    private boolean left = false, right = false, up = false, down = false;

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        System.out.println("com.FuturePixels.Drawables.Levels.Player.setLeft()");
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        System.out.println("com.FuturePixels.Drawables.Levels.Player.setRight()");
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        System.out.println("com.FuturePixels.Drawables.Levels.Player.setUp()");
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        System.out.println("com.FuturePixels.Drawables.Levels.Player.setDown()");
        this.down = down;
    }

    public Player() {
        super();
        setPosition(100, 100);
        veclocity = new Vector(0, 0);
        displacement = new Vector(0, 0);
        score = 0;
    }

    public void init() {

        for (int i = 0; i < 7; i++) {
            GetSprite("/Images/Player/sprite_" + i + ".png");
        }
        now = System.nanoTime() / 1000000000;
        ScoreInd = HUD.AddText("Score:" + score, new Vector(0, 40));
    }

    public void move(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    private void movePlayer() {
        boolean one = true, two = true;
        if (up) {
            displacement.addY(-1);
//        } else if (down) {
//            displacement.setY(1);
        } else {
//            displacement.setY(0);
//            veclocity.mult(new Vector(1, 0.3f));
            one = false;
        }
        displacement.setY((float) Math.max(-0.3, Math.min(displacement.getY(), 0.5f)));
        if (left) {
            Scale = -1;
            displacement.addX(-1);
        } else if (right) {
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
    public void Update(Graphics2D g) {
        deg += Game.g.getDelta() * 100;
        Graphics g2d = (Graphics) g;
//        g2d.drawString("Score:" + score, 0, 20);
        movePlayer();
        addGravity(true);
        ind += Stop ? -ind : 0.1f;
        ind = ind % 7;
        g.drawImage(GetSprite("/Images/Player/sprite_" + ((int) ind) + ".png"), -((getSpriteWidth() / 2) * (int) Scale), -(getSpriteHeight()) / 2, getSpriteWidth() * (int) Scale, getSpriteHeight(), null);

    }

    public void addPoints(int amt) {
        System.out.println("com.FuturePixels.Drawables.Levels.Player.addPoints()");
        setScore(getScore() + amt);
    }

    public void addGravity(boolean reset) {
        //todo check Colisions
        if (this.getPosition().getY() > Game.g.getWindowHeight() / 2) {
            if (reset) {
                this.veclocity = new Vector(this.veclocity.getX(), 0);
                this.setPosition(this.getPosition().getX(), Game.g.getWindowHeight() / 2);
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
        try{
            HUD.EditText(ScoreInd, "Score:" + this.score);
        }catch(Exception e){
            System.err.println("error");
        }
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
                Level().play(getClass().getResourceAsStream("/sounds/music.wav"));
            } catch (Exception e) {
                System.err.println(" error playing sound");
            }
        }
    }
}
