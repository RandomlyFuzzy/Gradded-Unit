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
import java.util.Timer;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Player extends IDrawable {

    private float ind = 0, Scale = 1;
    private boolean left = false, right = false, up = false, down = false, Stop = false, canJump = true, IsPlayer = false;
    private boolean once = true;

    /**
     *
     */
    public Vector Velocity = new Vector(0, 0);

    /**
     *
     */
    public Vector Acc = new Vector(0, 0);

    /**
     *
     */
    public Vector Cameraopos = Vector.Zero();
    private Random forsounds = new Random();
    private SpriteSheet she = new SpriteSheet(0, 0, 60, 90);

    private static int PlayerCount = 0;
    private static boolean hasupdated = true;
    private static boolean Lock = false;
    private static boolean hasLost = false;
    private int playerind = 0;

    /**
     *
     */
    public Player() {
        super();
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
    }

    /**
     *
     */
    public void init() {
        Player.setLock(false);
        hasLost = false;
        System.out.println("com.FuturePixels.Drawables.Levels.Player.init()");
        setPosition(100, 100);
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
        playerind = PlayerCount++;
    }

    /**
     *
     * @param left
     * @param right
     * @param up
     * @param down
     */
    public void move(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        ind += !canJump ? 0.3f : Stop ? -ind : 0.3f;
        setScale(new Vector(Scale, 1));
//       
        if (hasLost) {
            //insert death sprite here
            GetSprite("/Images/defualt.png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
        } else if (isLock()) {
            GetSprite("/Images/Player/reggie WIN_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
        } else if ((canJump)) {
            ind = ind % 7f;
            GetSprite("/Images/Player/player_0" + playerind + ".png");
            she.inputImage(getLastImage());
            if (!(left || right)) {
                she.setMaxX(1);
            }
            she.IncrementX(0.5f);
        } else if (Velocity.getY() < 0) {
            ind = ind % 3f;
            GetSprite("/Images/Player/reggie FALL_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
        } else if (Velocity.getY() > 0) {
            ind = ind % 3f;
            GetSprite("/Images/Player/reggie JUMP_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
        }

//        if (canJump) {
        DrawLastLoadedImageAsSpriteSheet(g, she);
//        } else {
//            DrawLastLoadedImage(g);
//        }
//        setRotation(getRotation()+(float)(Math.PI/180));
    }

    /**
     *
     */
    public void doMove() {
        if (!isColliding() || IsPlayer) {
            setRotation((getRotation() * 0.98f));
//            canJump = false;
            once = true;
            Velocity.mult(new Vector(0.99f, 0.99f));
        }
        if (!Lock && !hasLost) {
            movePlayer();
        }
        if (!isColliding() || IsPlayer) {
            //gravity is a bit too much for this so im going to make it less than gravity (maybe mars gravity*2)
//            Acc.setY(Acc.getY() + (-9.81f * (float) Game.g.getDelta()));
            //mars gravity*2  
            Acc.addY((-3.711f * (float) Game.getDelta() * 4));
        }
        Velocity.add(Acc);
        //adds the relative "right" vector and "up" vector 
        addPosition(Vector.Zero()
                .add(GetRight().mult(Velocity.getX()))
                .add(GetUp().mult(Velocity.getY() * 2))
                .add(GetRight().mult((float) getRotation() * 2f)));

        if (!(left || right) && canJump) {
            Velocity.mult(new Vector(0.8f * 0.8f, 0.995f));
        } else {
            Velocity.mult(new Vector(0.985f * 0.985f, 1f));

        }
        if ((float) getRotation() < -0.3 && canJump) {
            Level().play("/Sounds/Slide.wav");
        }

        if ((float) getRotation() > 0.3 && canJump) {
            Level().play("/Sounds/Slide.wav");
        }

        if (isColliding() && !IsPlayer) {
            Velocity.mult(new Vector(0.8f, 0.995f));
        }
        Acc.mult(0);

        //screen scroller
        if (!isLock() && !hasLost) {
            Cameraopos.setY(Cameraopos.getY() + Game.getDelta() * 30f);
        }

        if (getPosition().getX() <= -Transform.getOffsetTranslation().getX()) {
            addPosition(new Vector(Game.getScaledWidth(), 0));
            System.out.println("com.FuturePixels.Drawables.Levels.Player.doMove()");
        }
        if (getPosition().getX() >= -Transform.getOffsetTranslation().getX() + Game.getScaledWidth()) {
            setPosition(new Vector(-Transform.getOffsetTranslation().getX(), getPosition().getY()));
            System.out.println("com.FuturePixels.Drawables.Levels.Player.doMove()");
        }

//        if (-getPosition().getX() != Transform.getOffsetTranslation().getX() - Game.g.getScaledWidth() / 2) {
//            Cameraopos.setX(-getPosition().getX() + Game.g.getScaledWidth() / 2);
//        }
        if (-getPosition().getY() > Transform.getOffsetTranslation().getY() - getScaledSpriteHeight() * 2) {
            Cameraopos.setY(-getPosition().getY() + getScaledSpriteHeight() * 2);
            hasupdated = true;
        }
        // centers on the player 
//        Cameraopos = new Vector(getPosition()).mult(-1).add(new Vector(Game.g.getScaledWidth() / 2, Game.g.getScaledHeight() / 2));
        Cameraopos.setX(Transform.getOffsetTranslation().getX());
        if (hasupdated) {
            Transform.setOffsetTranslation(Cameraopos);
            hasupdated = false;
        }
        DebugObject.AddLine(
                new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2),
                new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2)
        );
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth() - 5, -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));

    }

    private void movePlayer() {
        boolean one = true, two = true;

        if (up && canJump) {
//            Acc.setY(0.01f);
            Acc.setY(8f);
            if (isColliding() && !IsPlayer) {
                int r = forsounds.nextInt(3);
                int r2 = forsounds.nextInt(3) + 1;
                String Prefix = r == 0 ? "High" : "High";
                Level().play("/Sounds/" + Prefix + "Jump" + r2 + ".wav");
            }
            canJump = false;
        } else if (down) {
            Acc.setY(-0.01f);
            canJump = true;
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
        float Clamp = canJump ? 1f : 0.2f;
        Acc.setX(Acc.getX() > Clamp ? Clamp : Acc.getX() < -Clamp ? -Clamp : Acc.getX());

        Stop = !one && !two;
    }

    /**
     *
     * @param im
     */
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
            Velocity.addY(6.5f);
            return;
        } else if (hasLost) {
            Velocity.setY(6.5f);
            for (int i = 0; i < Level().AmountOfObjects() - 3; i++) {
                try {
                    Level().GetObject(i)
                            .setIsCollidable(false);
                } catch (Exception ex) {

                }
            }
        } else if (im instanceof PlatForm || im instanceof MovingPlatoform || im instanceof DestroyingPlatForm) {
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
                col2 = null;
                return;
            }

            if (col2.IsHit && Velocity.getY() > 0) {
                Level().play("/sounds/Hit" + forsounds.nextInt(7) + ".wav");
                Level().play("/sounds/HighOuch1.wav");
                canJump = false;
                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getX(),
                        y = new Vector(bottom).mult(-0.00f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getY();

                setPosition(col2.hitLocation.getX() + x, col2.hitLocation.getY() + y);

                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_bottom[0], _bottom[1]);
                DebugObject.AddCirles(new Vector(col2.hitLocation.getX(), col2.hitLocation.getY()));
                if (once) {
                    Velocity.mult(new Vector(1, -.75f));
                    Acc.mult(new Vector(1, -.75f));
                    once = false;
                }
                col2 = null;
            }
            col = null;
        }
    }

    /**
     *
     * @return
     */
    public static boolean isHasLost() {
        return hasLost;
    }

    /**
     *
     * @param hasLost
     */
    public static void setHasLost(boolean hasLost) {
        Player.hasLost = hasLost;
    }

    /**
     *
     * @return
     */
    public static boolean isLock() {
        return Lock;
    }

    /**
     *
     * @param Lock
     */
    public static void setLock(boolean Lock) {
        Player.Lock = Lock;
    }

    /**
     *
     * @return
     */
    public boolean isLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     *
     * @return
     */
    public boolean isRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     *
     * @return
     */
    public boolean isUp() {
        return up;
    }

    /**
     *
     * @param up
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     *
     * @return
     */
    public boolean isDown() {
        return down;
    }

    /**
     *
     * @param down
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     *
     */
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

}
