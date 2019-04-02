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
import com.Liamengine.Engine.Components.SpriteSheet;
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
    private boolean once = true;
    private boolean once2 = true;
    private Vector Velocity = new Vector(0, 0);
    private float firstX = 0;

    private Vector Acc = new Vector(0, 0);
    private Vector Cameraopos = Vector.Zero();
    private Random forsounds = new Random();
    private SpriteSheet she = new SpriteSheet(0, 0, 60, 90);

    private static int PlayerCount = 0;
    private static boolean hasupdated = true;
    private static boolean Lock = false;
    private static boolean hasLost = false;
    private int playerind = 0;

    public Player() {
        super();
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
    }

    public void init() {
        Player.setLock(false);
        hasLost = false;

        setPosition(100, 100);
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
        playerind = PlayerCount++;

    }

    public void move(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    @Override
    public void Update(Graphics2D g) {
        doMove();
        ind += !canJump ? 0.3f : Stop ? -ind : 0.3f;
        setScale(new Vector(Scale, 1));
//       
        if (hasLost) {
            //insert death sprite here
            GetSprite("/Images/Player/reggie DEATH_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if (isLock()) {
            GetSprite("/Images/Player/reggie WIN_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if ((canJump)) {
            ind = ind % 7f;
            GetSprite("/Images/Player/player_0" + playerind + ".png");
            she.inputImage(getLastImage());
            if (!(left || right)) {
                she.setMaxX(1);
            }
            she.IncrementX(0.5f);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if (Velocity.getY() < 0) {
            ind = ind % 3f;
            GetSprite("/Images/Player/reggie FALL_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if (Velocity.getY() > 0) {
            ind = ind % 3f;
            GetSprite("/Images/Player/reggie JUMP_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        }

//        if (canJump) {
        DrawLastLoadedImageAsSpriteSheet(g, she);
//        } else {
//            DrawLastLoadedImage(g);
//        }
//        setRotation(getRotation()+(float)(Math.PI/180));
    }

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
        //adds the relative "right" vector and "up" vector 
        addPosition(Vector.Zero().add(GetRight().mult(Velocity.getX())).add(GetUp().mult(Velocity.getY())).add(GetRight().mult((float) getRotation() * 2f)));

        if (isColliding() && !IsPlayer) {
            Velocity.mult(new Vector(0.8f, 0.995f));
        }
        Acc.mult(0);

        if ((float) getRotation() != 0 && canJump) {
            Level().play("/Sounds/Slide.wav");
        }

        //screen scroller
        if (!isLock() && !hasLost) {
            Cameraopos.setY(Cameraopos.getY() + Game.getDelta() * 30f);
        }

        if (getPosition().getX() <= -Transform.getOffsetTranslation().getX()) {
            addPosition(new Vector(Game.getScaledWidth(), 0));

        }
        if (getPosition().getX() >= -Transform.getOffsetTranslation().getX() + Game.getScaledWidth()) {
            setPosition(new Vector(-Transform.getOffsetTranslation().getX(), getPosition().getY()));

        }
        if (firstX == 0) {
            firstX = Transform.getOffsetTranslation().getX();
        }

//        if (Level().getClass().getName().indexOf("Coop") == -1) {
            if (-getPosition().getX() != Transform.getOffsetTranslation().getX() - Game.getScaledWidth() / 2) {
            Cameraopos.setX((-getPosition().getX() + Game.getScaledWidth() / 2) / 5f + firstX);
            hasupdated = true;
            }
//        } else {
//            Cameraopos.setX(Transform.getOffsetTranslation().getX());
//        }

        if (-getPosition().getY() > Transform.getOffsetTranslation().getY() - getScaledSpriteHeight() * 2) {
            Cameraopos.setY(-getPosition().getY() + getScaledSpriteHeight() * 2);
            hasupdated = true;
        }
        // centers on the player 
//        Cameraopos = new Vector(getPosition()).mult(-1).add(new Vector(Game.g.getScaledWidth() / 2, Game.g.getScaledHeight() / 2));
        if (hasupdated&&!once2) {
            Transform.setOffsetTranslation(Cameraopos);
            hasupdated = false;
        }
        DebugObject.AddLine(
                new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2),
                new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2)
        );
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth() - 5, -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
        once2 = false;
    }

    private void movePlayer() {
        boolean one = true, two = true;

        if (up && canJump) {
//            Acc.setY(0.01f);
            if (isColliding() && !IsPlayer) {
                Acc.setY(8f);
                int r = forsounds.nextInt(3);
                int r2 = forsounds.nextInt(3) + 1;
                String Prefix = r == 0 ? "High" : "High";
                Level().play("/Sounds/" + Prefix + "Jump" + r2 + ".wav");
            }
            canJump = false;
        } else if (down) {
            Acc.setY(-0.01f);
        } else {
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

    @Override
    public void onCollison(IDrawable im) {
        if (im == null) {
            return;
        }

        if (im instanceof Player) {
            IsPlayer = true;
            return;
        } else {
            IsPlayer = false;
        }

        if (isLock()) {
            Velocity.addY(0.1f);
            return;
        } else if (hasLost) {
            Acc.setY(6.5f);
            for (int i = 0; i < Level().AmountOfObjects() - 3; i++) {
                try {
                    Level().GetObject(i).setIsCollidable(false);
                } catch (Exception e) {

                }
            }
        } else if (im instanceof PlatForm || im instanceof MovingPlatform || im instanceof DestroyingPlatForm) {
            setRotation(im.getRotation());
            Vector bottom, top, left, right, _hit;
            Vector[] _Top, _bottom, _left, _right;
            //get platfor top line
            bottom = new Vector(getPosition()).add(GetUp().mult(getScaledSpriteHeight() * -0.5f));
            top = new Vector(bottom).mult(-1f).add(getPosition()).add(getPosition());
            left = new Vector(getPosition()).add(new Vector(-getSpriteWidth() / 2, 0));
            right = new Vector(getPosition()).add(new Vector(getSpriteWidth() / 2, 0));
            _Top = im.sideUp();
            _bottom = im.sideDown();
            _left = im.sideLeft();
            _right = im.sideRight();
            DebugObject.AddLine(left, right);

            Collison col = CollisonUtils.CheckForLineHits(getPosition(), bottom, _Top[0], _Top[1]);
            Collison col2 = CollisonUtils.CheckForLineHits(getPosition(), top, _bottom[0], _bottom[1]);
            Collison col3 = CollisonUtils.CheckForLineHits(getPosition(), right, _left[0], _left[1]);
            Collison col4 = CollisonUtils.CheckForLineHits(getPosition(), left, _right[0], _right[1]);

            //up
            if (col.ISHIT && !down) {
                canJump = true;
                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_Top[0], _Top[1]);
                _hit = col.HITLOCATION;
                float x = (GetUp().mult(getSpriteHeight() * 0.5f)).getX(),
                        y = (GetUp().mult(getSpriteHeight() * 0.5f)).getY();
                DebugObject.AddCirles(new Vector(col.HITLOCATION.getX(), col.HITLOCATION.getY()));
//                Acc.addY((-3.711f * (float) Game.g.getDelta() * -2));
                if (once) {
                    Velocity.setY(0);
                    once = false;
                }
                setPosition(col.HITLOCATION.getX() + x, col.HITLOCATION.getY() + y);
                col = null;
                col2 = null;
                return;
            }
            //down
            if (col2.ISHIT && Velocity.getY() > 0) {
                canJump = false;
                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getX(),
                        y = new Vector(bottom).mult(-0.00f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getY();

                setPosition(col2.HITLOCATION.getX() + x, col2.HITLOCATION.getY() + y);

                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_bottom[0], _bottom[1]);
                DebugObject.AddCirles(new Vector(col2.HITLOCATION.getX(), col2.HITLOCATION.getY()));
                if (once) {
                    Velocity.mult(new Vector(1, -.40f));
                    Acc.mult(new Vector(1, -.50f));
                    once = false;
                }
                col2 = null;
            }

            //left
            if (col3.ISHIT) {

//                if (Acc.getX() > 0 || Velocity.getX() > 0) {
//                    this.right = false;
//                    Acc.setX(0);
//                    Velocity.setX(0);
//                }
                setPosition(new Vector(col3.HITLOCATION).add(new Vector(left).add(new Vector(getPosition()).mult(-1)).mult(-1)));
                DebugObject.AddLine(_left[0], _left[1]);
            }
            //right
            if (col4.ISHIT) {

//                if (Acc.getX() < 0 || Velocity.getX() < 0) {
//                    this.left = false;
//                    Acc.setX(0);
//                    Velocity.setX(0);
//                }
//                addPosition(col4.HITLOCATION.add(new Vector(right).mult(1)));
                DebugObject.AddLine(left, right);
                DebugObject.AddLine(_right[0], _right[1]);
            }
            col = null;
        }
    }

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

    @Override
    public void dispose() {
        super.dispose();
        Velocity = Vector.Zero();
        Acc = Vector.Zero();
        hasupdated = true;
        Lock = false;
        hasLost = false;
        PlayerCount = 0;
    }

    public Vector getVelocity() {
        return Velocity;
    }

    public void setVelocity(Vector Velocity) {
        this.Velocity = Velocity;
    }

    public Vector getAcc() {
        return Acc;
    }

    public void setAcc(Vector Acc) {
        this.Acc = Acc;
    }

}
