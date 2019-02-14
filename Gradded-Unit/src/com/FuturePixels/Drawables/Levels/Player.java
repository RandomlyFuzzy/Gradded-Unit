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

    private float ind = 0, Scale = 1;
    private long score;
    private int ScoreInd = 0;
    private boolean left = false, right = false, up = false, down = false, Stop = false;

    Vector Velocity = new Vector(0, 0), Acc = new Vector(0, 0);

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
        score = 0;
    }

    public void init() {

        for (int i = 0; i < 7; i++) {
            GetSprite("/Images/Player/sprite_" + i + ".png");
        }
        ScoreInd = HUD.AddText("Score:" + score, new Vector(0, 40));
    }

    public void move(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    @Override
    public void Update(Graphics2D g) {
        ind += Stop ? -ind : 0.3f;
        ind = ind % 8;
        g.drawImage(GetSprite("/Images/Player/sprite_" + ((int) ind) + ".png"), -((getSpriteWidth() / 2) * (int) Scale), -(getSpriteHeight()) / 2, getSpriteWidth() * (int) Scale, getSpriteHeight(), null);

    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
        try {
            HUD.EditText(ScoreInd, "Score:" + this.score);
        } catch (Exception e) {
            System.err.println("error");
        }
    }

    public void doMove() {
        if (!isColliding()) {
            setRotation(0);
        }
        movePlayer();
        Velocity.add(Acc);
        addPosition(new Vector(0, 0).add(GetUp().mult(Velocity.getX())).add(GetRight().mult(Velocity.getY())));
        Velocity.mult(0.93f);
    }

    private void movePlayer() {
        boolean one = true, two = true;

        if (up) {
            Acc.setY(1);
        } else if (down) {
            Acc.setY(-1);

        } else {
            Acc.setY(0);

            one = false;
        }
        if (left) {
            Scale = -1;
            Acc.setX(-1);
        } else if (right) {
            Scale = 1;
            Acc.setX(1);
        } else {
            two = false;
            Acc.setX(0);
        }

        Stop = !one && !two;
    }

    @Override
    public void onCollison(IDrawable im) {
        if (im == null) {
            return;
        }

        if (im instanceof PlatForm) {
            System.out.println("com.FuturePixels.Drawables.Levels.Player.onCollison()");
            setRotation(im.getRotation());
        }
    }
}
