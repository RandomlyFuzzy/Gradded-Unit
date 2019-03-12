/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Collison;
import com.Liamengine.Engine.Utils.CollisonUtils;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Player extends IDrawable {

    private float ind = 0, Scale = 1;
    private boolean left = false, right = false, up = false, down = false, Stop = false, canJump = true, IsPlayer = false;
    public Vector Velocity = new Vector(0, 0), Acc = new Vector(0, 0);
    public Vector Cameraopos = Vector.Zero();
    private Random forsounds = new Random();

    private static boolean Lock = false;
    private static boolean hasLost = false;

    public static boolean isHasLost() {
        return hasLost;
    }

    public static void setHasLost(boolean hasLost) {
        Player.hasLost = hasLost;
    }

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
        hasLost = false;
        System.out.println("com.FuturePixels.Drawables.Levels.Player.init()");
        setPosition(100, 100);
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
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
//       
        if (hasLost) {
            //insert death sprite here
            GetSprite("/Images/defualt.png");
        } else if (isLock()) {
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

    boolean hasupdated = true;

    public void doMove() {
        if (!isColliding() || IsPlayer) {
            setRotation((getRotation() * 0.98f));
            canJump = false;
            once = true;
            Velocity.mult(new Vector(0.985f, 0.995f));
        }
        if (!Lock && !hasLost) {
            movePlayer();
        }
        if (!isColliding() || IsPlayer) {
            //gravity is a bit too much for this so im going to make it less than gravity (maybe mars gravity*2)
//            Acc.setY(Acc.getY() + (-9.81f * (float) Game.g.getDelta()));
            //mars gravity*2  
            Acc.addY((-3.711f * (float) Game.getDelta() * 2));
        }
        Velocity.add(Acc);
        addPosition(Vector.Zero().add(GetRight().mult(Velocity.getX())).add(GetUp().mult(Velocity.getY())));

        if (isColliding() && !IsPlayer) {
            Velocity.mult(new Vector(0.8f, 0.995f));
        }
        Acc.mult(0);
//        if (-getPosition().getX() != Cameraopos.getX() - Game.g.getScaledWidth() / 2) {
//            Cameraopos.setX(-getPosition().getX() + Game.g.getScaledWidth() / 2);
//        }
        if (-getPosition().getY() > Cameraopos.getY() - getScaledSpriteHeight() * 2) {
            Cameraopos.setY(-getPosition().getY() + getScaledSpriteHeight() * 2);
        }
        //screen scroller
        if (!isLock() && !hasLost) {
            Cameraopos.setY(Cameraopos.getY() + Game.getDelta() * 30f);
        }

        if (getPosition().getX() <= -Transform.getOffsetTranslation().getX()) {
            addPosition(new Vector(Game.getScaledWidth(), 0));
            System.out.println("com.FuturePixels.Drawables.Levels.Player.doMove()");
            hasupdated = true;
        }
        if (getPosition().getX() >= -Transform.getOffsetTranslation().getX() + Game.getScaledWidth()) {
            setPosition(new Vector(-Transform.getOffsetTranslation().getX(), getPosition().getY()));
            System.out.println("com.FuturePixels.Drawables.Levels.Player.doMove()");
            hasupdated = true;
        }
        // centers on the player 
//        Cameraopos = new Vector(getPosition()).mult(-1).add(new Vector(Game.g.getScaledWidth() / 2, Game.g.getScaledHeight() / 2));
        Cameraopos.setX(Transform.getOffsetTranslation().getX());
        if (hasupdated) {
            Transform.setOffsetTranslation(Cameraopos);
            hasupdated = false;
        }
        DebugObject.AddLine(new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2),
                new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2)
        );
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth() - 5, -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
    }

    private void movePlayer() {
        boolean one = true, two = true;

        if (up && canJump) {
//            Acc.setY(0.01f);
            if (isColliding() && !IsPlayer) {
                Acc.setY(8f);
                int r = forsounds.nextInt(3);
                int r2 = forsounds.nextInt(2) + 1;
                String Prefix = r == 0 ? "Low" : r == 1 ? "" : "High";
                Level().play("/Sounds/" + Prefix + "Jump" + r2 + ".wav");
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
            if (canJump) {
                Level().play("/Sounds/Footsteps.wav");
            }
        } else if (right) {
            Scale = 1;
            Acc.addX(100);
            if (canJump) {
                Level().play("/Sounds/Footsteps.wav");
            }
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

        if (im instanceof Player) {
            IsPlayer = true;
//            Velocity.add(new Vector(getPosition()).add(new Vector(im.getPosition()).mult(-1)).mult(3f));
            return;
        } else {
            IsPlayer = false;
        }

        if (isLock()) {
            Velocity.addY(0.1f);
        }
        if (hasLost) {
            Velocity.setY(6.5f);
            for (int i = 0; i < Level().AmountOfObjects() - 3; i++) {
                Level().GetObject(i).setIsCollidable(false);
            }
        }
        if (im instanceof PlatForm || im instanceof MovingPlatoform || im instanceof DestroyingPlatForm) {
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

            if (col.IsHit && !down) {
                canJump = true;
                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_Top[0], _Top[1]);
                _hit = col.hitLocation;
                float x = (GetUp().mult(getSpriteHeight() * 0.5f)).getX(),
                        y = (GetUp().mult(getSpriteHeight() * 0.5f)).getY();
                DebugObject.AddCirles(new Vector(col.hitLocation.getX(), col.hitLocation.getY()));
//                Acc.addY((-3.711f * (float) Game.g.getDelta() * -2));
                if (once) {
                    Velocity.setY(0);
                    once = false;
                }
                setPosition(col.hitLocation.getX() + x, col.hitLocation.getY() + y);
                col = null;
            }

            if (col2.IsHit && Velocity.getY() > 0) {
                Level().play("/sounds/Hit" + forsounds.nextInt(7) + ".wav");
                canJump = false;
                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getX(),
                        y = new Vector(bottom).mult(-0.00f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getY();

                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_bottom[0], _bottom[1]);
                setPosition(col2.hitLocation.getX() + x, col2.hitLocation.getY() + y);

                DebugObject.AddCirles(new Vector(col2.hitLocation.getX(), col2.hitLocation.getY()));
                if (once) {
                    Velocity.mult(new Vector(1, -.75f));
                    Acc.mult(new Vector(1, -.75f));
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
