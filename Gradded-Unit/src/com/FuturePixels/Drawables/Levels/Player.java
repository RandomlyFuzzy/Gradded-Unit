/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Components.RigidBody;
import com.FuturePixels.Components.Transform;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Collison;
import com.FuturePixels.Utils.CollisonUtils;
import com.FuturePixels.Utils.Vector;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.Utils.ILevel;
import java.awt.Color;
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
    private boolean left = false, right = false, up = false, down = false, Stop = false, canJump = true;

    public Vector Velocity = new Vector(0, 0), Acc = new Vector(0, 0);

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

    }

    public void init() {
        System.out.println("com.FuturePixels.Drawables.Levels.Player.init()");
        setPosition(100, 100);
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
        score = 0;
        for (int i = 0; i < 7; i++) {
            GetSprite("/Images/Player/sprite_" + i + ".png");
        }
        ScoreInd = HUD.AddText("Score:" + score, new Vector(0, 40));
        AddComponent(new RigidBody(this));
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

//        setRotation(getRotation()+(float)(Math.PI/180));
        getComponent(Transform.class).PopTransforms(g);
        g.setColor(Color.GREEN);
        Vector v2 = new Vector(-getSpriteHeight() - 20, (float) -getSpriteHeight() - 20).mult(GetUp()).add(getPosition());
        g.drawLine(
                (int)new Vector(getPosition()).add(GetUp().mult(getSpriteHeight())).getX()
                ,(int)new Vector(getPosition()).add(GetUp().mult(getSpriteHeight())).getY()
                , (int) v2.getX(), (int) v2.getY());
        if (_hit != null) {
            g.fillOval((int) (_hit.getX() - getPosition().getX()) - 5, (int) (_hit.getY() - getPosition().getX()) - 5, 10, 10);
        }
        getComponent(Transform.class).PushTransforms(g);
    }
    Vector v;

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
            setRotation((getRotation() * 0.98f));
        }
        movePlayer();
        Velocity.add(Acc);
        addPosition(Vector.Zero.add(GetRight().mult(Velocity.getX())).add(GetUp().mult(Velocity.getY())));
        Velocity.mult(new Vector(0.80f, 0.83f));
        Acc.mult(0);
    }

    private void movePlayer() {
        boolean one = true, two = true;

        if (up && canJump) {
            Acc.setY(0.01f);
            if (isColliding()) {
                Velocity.setY(0.1f);
            }
//            canJump = false;
        } else if (down) {
            Acc.setY(-0.01f);
            canJump = true;
        } else {
//            Acc.setY(0);

            one = false;
        }
        if (left && canJump) {
            Scale = -1;
            Acc.addX(-0.01f);
        } else if (right && canJump) {
            Scale = 1;
            Acc.addX(0.01f);
        } else if (canJump) {
            two = false;
//            Acc.setX(0);
        }

        Acc.setX(Acc.getX() > 0.3f ? 0.3f : Acc.getX() < -0.3f ? 0.3f : Acc.getX());
        if (!isColliding()) {
//            Acc.setY(-9.81f * (float) Game.g.getDelta());
        }
        Stop = !one && !two;
    }
    private Vector _left, _hit;
    private Vector[] _Top = new Vector[2];

    @Override
    public void onCollison(IDrawable im) {
        if (im == null) {
            return;
        }

        if (im instanceof PlatForm) {
            canJump = true;
            setRotation(im.getRotation());
            //get platfor top line

            _left = new Vector(-getSpriteHeight() - 20, (float) -getSpriteHeight() - 20).mult(GetUp()).add(getPosition());
            _Top = im.sideRight();

            Collison col = CollisonUtils.CheckForLineHits(new Vector(getPosition()).add(GetUp().mult(getSpriteHeight())), _left, _Top[0], _Top[1]);

            if (col.IsHit) {
                _hit = col.hitLocation;
                
                float x,y;
                setPosition(col.hitLocation.getX(), col.hitLocation.getY());
                Velocity.mult(0);
            }
        }
    }

}
