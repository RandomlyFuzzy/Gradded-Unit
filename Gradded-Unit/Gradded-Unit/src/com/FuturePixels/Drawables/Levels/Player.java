/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Components.*;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Collison;
import com.FuturePixels.Engine.Utils.CollisonUtils;
import com.FuturePixels.Engine.Components.Vector;
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
    private Vector Velocity = new Vector(0, 0), Acc = new Vector(0, 0);

    private static boolean Lock = false;

    public static boolean isLock() {
        return Lock;
    }

    public static void setLock(boolean Lock) {
        Player.Lock = Lock;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public Player() {
        super();
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
    }

    public void init() {
        Player.setLock(false);
        System.out.println("com.FuturePixels.Drawables.Levels.Player.init()");
        setPosition(100, 100);
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
        score = 0;
        for (int i = 0; i < 7; i++) {
            GetSprite("/Images/Player/sprite_" + i + ".png");
        }
        ScoreInd = HUD.AddText("Score:" + score, new Vector(0, 40));
//        AddComponent(new RigidBody(this));
//        AddComponent(new BackgroundDrawer(this));
    }

    public void move(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    @Override
    public void Update(Graphics2D g) {
        ind += !canJump ? 0.3f : Stop ? -ind : 0.3f;
        setScale(new Vector(Scale, 1));
        if (isLock()) {
            GetSprite("/Images/Player/reggie WIN.png");
        } else if ((canJump)) {
            ind = ind % 7f;
            GetSprite("/Images/Player/sprite_" + ((int) ind) + ".png");
        } else if (Velocity.getY() < 0) {
            ind = ind % 3f;
            if (ind < 1) {
                GetSprite("/Images/Player/reggie FALL.png");
            } else {
                GetSprite("/Images/Player/sprite_6.png");
            }
        } else if (Velocity.getY() > 0) {
            ind = ind % 3f;
            if (ind < 1) {
                GetSprite("/Images/Player/reggie JUMP.png");
            } else {
                GetSprite("/Images/Player/sprite_1.png");
            }
        }

        DrawLastLoadedImage(g);
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
            once = true;
            Velocity.mult(new Vector(0.985f, 0.995f));
        }
        if (!Lock) {
            movePlayer();
        }
        if (!isColliding()) {
            //gravity is a bit too much for this so im going to make it less than gravity (maybe mars gravity*2)
//            Acc.setY(Acc.getY() + (-9.81f * (float) Game.g.getDelta()));
            //mars gravity*2  
            Acc.setY(Acc.getY() + (-3.711f * (float) Game.g.getDelta() * 2));
        }
        Velocity.add(Acc);
        addPosition(Vector.Zero().add(GetRight().mult(Velocity.getX())).add(GetUp().mult(Velocity.getY())));

        if (isColliding()) {
            Velocity.mult(new Vector(0.8f, 0.995f));
        }
        Acc.mult(0);

    }

    private float distFromhit = 0;

    private void movePlayer() {
        boolean one = true, two = true;

        if (up && canJump) {
//            Acc.setY(0.01f);
            if (isColliding()) {
                Acc.setY( 8f);
//                Level().play("/Sounds/Jump.wav");
            }
            canJump = false;
        } else if (down) {
            Acc.setY(-0.01f);
            canJump = true;
        } else {
//            Acc.setY(0);

            one = false;
        }
        if (left) {
            Scale = -1;
            Acc.addX(-100);
        } else if (right) {
            Scale = 1;
            Acc.addX(100);
        } else if (canJump) {
            two = false;
            Acc.setX(0);
        }
        float Clamp = canJump ? 1f : 0.1f;
        Acc.setX(Acc.getX() > Clamp ? Clamp : Acc.getX() < -Clamp ? -Clamp : Acc.getX());

        Stop = !one && !two;
    }

    boolean once = true;

    @Override
    public void onCollison(IDrawable im) {
        if (im == null) {
            return;
        }
        System.out.println("com.FuturePixels.Drawables.Levels.Player.onCollison() "+(im instanceof MovingPlatoform));
        
        if (im instanceof Player) {
            setIsColliding(false);
//            Velocity.add(new Vector(getPosition()).add(new Vector(im.getPosition()).mult(-1)).mult(3f));
            return;
        }

        if (isLock()) {
            Velocity.addY(0.1f);
        }
        if (im instanceof PlatForm||im instanceof MovingPlatoform) {
            setRotation(im.getRotation());
            Vector bottom, top, _hit;
            Vector[] _Top, _bottom;
            //get platfor top line
            bottom = new Vector(getPosition()).add(GetUp().mult(getScaledSpriteHeight() * -0.5f));
            top = new Vector(bottom).mult(-1f).add(getPosition()).add(getPosition());
            _Top = im.sideUp();
            _bottom = im.sideDown();

            Collison col = CollisonUtils.CheckForLineHits(getPosition(), bottom, _Top[0], _Top[1]);
            Collison col2 = CollisonUtils.CheckForLineHits(getPosition(), top, _bottom[0], _bottom[1]);

            if (col.IsHit) {
                canJump = true;
                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_Top[0], _Top[1]);
                _hit = col.hitLocation;

                float x = (GetUp().mult(getSpriteHeight() * 0.5f)).getX(),
                        y = (GetUp().mult(getSpriteHeight() * 0.5f)).getY();
                DebugObject.AddCirles(new Vector(col.hitLocation.getX(), col.hitLocation.getY()));
//                Acc.addY((-3.711f * (float) Game.g.getDelta() * -2));
                Vector dif = new Vector(col.hitLocation).add(new Vector(bottom).mult(-1));
                dif.mult(dif);
                distFromhit = (float) Math.sqrt(dif.getX() + dif.getY());
                dif = null;
                if (once) {
                    Velocity.setY(0);
                    once = false;
                }
                setPosition(col.hitLocation.getX() + x, col.hitLocation.getY() + y);
                col = null;
            }

            if (col2.IsHit) {
                canJump = false;
                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getX(),
                        y = new Vector(bottom).mult(-0.00f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getY();

                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_bottom[0], _bottom[1]);
                setPosition(col2.hitLocation.getX() + x, col2.hitLocation.getY() + y);

                DebugObject.AddCirles(new Vector(col2.hitLocation.getX(), col2.hitLocation.getY()));
                if (once) {
                    Velocity.mult(new Vector(1, -1f));
                    Acc.mult(new Vector(1, -1f));
                    once = false;
                }
                col2 = null;
            }
            col = null;
            col2 = null;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        Velocity = Vector.Zero();
        Acc = Vector.Zero();
    }

}
