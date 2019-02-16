/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Components.DebugComponent;
import com.FuturePixels.Components.RigidBody;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.Utils.Collison;
import com.FuturePixels.Utils.CollisonUtils;
import com.FuturePixels.Utils.Vector;
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
    private DebugComponent dComp;

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
                Velocity.setY(1.5f);
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
            Acc.setY(Acc.getY() + (-4.81f * (float) Game.g.getDelta()));
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

            _left = new Vector(-getSpriteHeight() + 20, (float) -getSpriteHeight() + 20).mult(GetUp()).add(getPosition());
            _Top = im.sideLeft();

            Collison col = CollisonUtils.CheckForLineHits(new Vector(getPosition()).add(GetUp().mult(getSpriteHeight())), _left, _Top[0], _Top[1]);

            if (col.IsHit) {
                DebugComponent.AddLine(_left, new Vector(getPosition()).add(GetUp().mult(getSpriteHeight())));
                DebugComponent.AddLine(_Top[0], _Top[1]);
                _hit = col.hitLocation;

                float x = new Vector(_left).mult(0f).add(GetUp().mult(getSpriteHeight() * 0.550f)).getX(),
                        y = new Vector(_left).mult(-0.001f).add(GetUp().mult(getSpriteHeight() * 0.550f)).getY();
                DebugComponent.AddCirles(new Vector(col.hitLocation.getX() + x, col.hitLocation.getY() + y));
                setPosition(col.hitLocation.getX() + x, col.hitLocation.getY() + y);
//                Velocity.mult(0);
                Velocity.mult(new Vector(1, 0));
                Acc.mult(new Vector(1, 0.5f));
            }
        }
    }

    public void dispose() {
        super.dispose();
        Velocity = Vector.Zero;
        Acc = Vector.Zero;
        

    }

}
