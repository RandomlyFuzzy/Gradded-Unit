/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Components.RigidBody;
import com.FuturePixels.MainClasses.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.Collison;
import com.FuturePixels.MainClasses.CollisonUtils;
import com.FuturePixels.MainClasses.Vector;
import java.awt.Graphics2D;

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
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
    }
    private DebugObject dComp;

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
            setRotation((getRotation() * 0.98f));
            canJump = false;
        }
        movePlayer();
        Velocity.add(Acc);
        addPosition(Vector.Zero().add(GetRight().mult(Velocity.getX())).add(GetUp().mult(Velocity.getY())));
        Velocity.mult(new Vector(0.985f, 0.995f));
        Acc.mult(0);
    }

    private void movePlayer() {
        boolean one = true, two = true;

        if (up && canJump) {
            Acc.setY(0.01f);
            if (isColliding()) {
                Velocity.setY(5f);
            }
            canJump = false;
        } else if (down) {
            Acc.setY(-0.01f);
            canJump = true;
        } else {
//            Acc.setY(0);

            one = false;
        }
        if (left && canJump) {
            Scale = -1;
            Acc.addX(-0.2f);
        } else if (right && canJump) {
            Scale = 1;
            Acc.addX(0.2f);
        } else if (canJump) {
            two = false;
//            Acc.setX(0);
        }

        Acc.setX(Acc.getX() > 0.3f ? 0.3f : Acc.getX() < -0.3f ? 0.3f : Acc.getX());
        if (!isColliding()) {
            Acc.setY(Acc.getY() + (-9.81f * (float) Game.g.getDelta()));
        }
        Stop = !one && !two;
    }
    private Vector bottom, top, _hit;
    private Vector[] _Top, _bottom;

    @Override
    public void onCollison(IDrawable im) {
        if (im == null || im instanceof Player) {
            return;
        }

        if (im instanceof PlatForm) {
            setRotation(im.getRotation());
            //get platfor top line

            bottom = new Vector(getPosition()).add(GetUp().mult(getSpriteHeight() * -0.55f));
            top = new Vector(bottom).mult(-1).add(getPosition()).add(getPosition());
            _Top = im.sideUp();

            Collison col = CollisonUtils.CheckForLineHits(getPosition(), bottom, _Top[0], _Top[1]);
            _bottom = im.sideDown();

            if (col.IsHit) {
                canJump = true;
                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_Top[0], _Top[1]);
                _hit = col.hitLocation;

                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * 0.5f)).getX(),
                        y = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * 0.5f)).getY();
                DebugObject.AddCirles(new Vector(col.hitLocation.getX(), col.hitLocation.getY()));
                setPosition(col.hitLocation.getX() + x, col.hitLocation.getY() + y);
            }
            Collison col2 = CollisonUtils.CheckForLineHits(top, getPosition(), _bottom[0], _bottom[1]);

            if (col2.IsHit) {
                canJump = false;
                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getX(),
                        y = new Vector(bottom).mult(-0.00f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getY();

                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_bottom[0], _bottom[1]);
                setPosition(col2.hitLocation.getX() + x, col2.hitLocation.getY() + y);

                DebugObject.AddCirles(new Vector(col2.hitLocation.getX(), col2.hitLocation.getY()));
                Velocity.mult(new Vector(1, -1f));
                Acc.mult(new Vector(1, -1f));
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        Velocity = Vector.Zero();
        Acc = Vector.Zero();
    }

}
