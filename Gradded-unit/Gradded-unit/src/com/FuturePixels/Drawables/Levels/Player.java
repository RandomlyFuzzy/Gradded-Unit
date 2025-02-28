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
import java.awt.Graphics2D;
import java.util.Random;

/**
 * this does not implement movement relative to the time so any lag spike will
 * cause the players movements to be less than expected I updated this but it 
 * made the player seem different so the group decided to not implement that
 * functionality
 *
 * Everything relevant to the player is included here such as the position, movement, 
 * rotation, acceleration, camera position, if the player can jump, footstep sounds.
 * @author Liam Woolley 1748910
 */
public class Player extends IDrawable {

    private float ind = 0, Scale = 1;
    private boolean left = false, right = false, up = false, down = false, Stop = false, canJump = true, Isplayer = false;
    private boolean once = true;
    private boolean once2 = true;
    private Vector Velocity = new Vector(0, 0);
    private float firstX = 0;

    private Vector Acc = new Vector(0, 0);
    private Vector Cameraopos = Vector.Zero();
    private Random forsounds = new Random();
    private SpriteSheet she = new SpriteSheet(0, 0, 60, 90);

    private static int playerCount = 0;
    private static boolean hasupdated = true;
    private static boolean Lock = false;
    private static boolean hasLost = false;
    private int playerind = 0;

    // Player speed and acceleration
    /**
     *
     */
    public Player() {
        super();
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
    }

    // Initialises player properties
    /**
     *
     */
    public void init() {
        Player.setLock(false);
        hasLost = false;

        setPosition(100, 100);
        Velocity = new Vector(0, 0);
        Acc = new Vector(0, 0);
        playerind = playerCount++;

    }

    // Checks movement direction
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

    // Updates character images
    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        doMove();
        ind += !canJump ? 0.3f : Stop ? -ind : 0.3f;
        setScale(new Vector(Scale, 1));
       
        if (hasLost) {
            // Displays death sprite upon death
            GetSprite("/images/player/reggie DEATH_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if (isLock()) {
            //Dipplays cheer sprite upon win
            GetSprite("/images/player/reggie WIN_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if ((canJump)) {
            // Displays running character sprite when running
            ind = ind % 7f;
            GetSprite("/images/player/player_0" + playerind + ".png");
            she.inputImage(getLastImage());
            if (!(left || right)) {
                she.setMaxX(1);
            }
            she.IncrementX(0.5f);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if (Velocity.getY() < 0) {
            // Displays falling character sprite when mid-air
            ind = ind % 3f;
            GetSprite("/images/player/reggie FALL_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        } else if (Velocity.getY() > 0) {
            // Displays jumping character sprite upon jump
            ind = ind % 3f;
            GetSprite("/images/player/reggie JUMP_0" + playerind + ".png");
            she.inputImage(getLastImage());
            she.IncrementX(1);
            setSpriteWidth(60);
            setSpriteHeight(90);
        }

        DrawLastLoadedImageAsSpriteSheet(g, she);

    }

    /**
     *
     */
    public void doMove() {
        // Rotate player on tilted platform
        if (!isColliding() || Isplayer) {
            setRotation((getRotation() * 0.98f));
            canJump = false;
            once = true;
            Velocity.mult(new Vector(0.985f, 0.995f));
        }
        // Make player slide
        if (!Lock && !hasLost) {
            moveplayer();
        }
        if (!isColliding() || Isplayer) {
 
            Acc.addY((-3.711f * (float) Game.getDelta() * 2));
        }
        Velocity.add(Acc);
        // adds the relative "right" vector and "up" vector 
        addPosition(Vector.Zero().add(GetRight().mult(Velocity.getX())).add(GetUp().mult(Velocity.getY())).add(GetRight().mult((float) getRotation() * 2f)));

        if (isColliding() && !Isplayer) {
            Velocity.mult(new Vector(0.8f, 0.995f));
        }
        Acc.mult(0);

        // Play slide sound when character is rotated
        if ((float) getRotation() != 0 && canJump) {
            Level().play("/sounds/slide.wav");
        }

        // Screen scroller
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

        if (Level().getClass().getName().indexOf("Coop") == -1) {
            if (-getPosition().getX() != Transform.getOffsetTranslation().getX() - Game.getScaledWidth() / 2) {
                Cameraopos.setX((-getPosition().getX() + Game.getScaledWidth() / 2) / 5f + firstX);
                hasupdated = true;
            }
        } else {
            Cameraopos.setX(Transform.getOffsetTranslation().getX());
        }

        if (-getPosition().getY() > Transform.getOffsetTranslation().getY() - getScaledSpriteHeight() * 2) {
            Cameraopos.setY(-getPosition().getY() + getScaledSpriteHeight() * 2);
            hasupdated = true;
        }
        // Centers on the player 
        // Cameraopos = new Vector(getPosition()).mult(-1).add(new Vector(Game.g.getScaledWidth() / 2, Game.g.getScaledHeight() / 2));
        if (hasupdated && !once2) {
            Transform.setOffsetTranslation(Cameraopos);
            hasupdated = false;
        }
        // This was used for debugging
        DebugObject.AddLine(
                new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2),
                new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2)
        );
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX(), -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
        DebugObject.AddCirles(new Vector(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth() - 5, -Transform.getOffsetTranslation().getY() + Game.getScaledHeight() / 2));
        once2 = false;
    }

    private void moveplayer() {
        boolean one = true, two = true;

        if (up && canJump) {
        // Adds vertical acceleration of character on jump and plays random jump sound
            if (isColliding() && !Isplayer) {
                Acc.setY(8f);
                int r = forsounds.nextInt(3);
                int r2 = forsounds.nextInt(4) + 1;
                Level().play("/sounds/high" + "jump" + r2 + ".wav");
            }
            canJump = false;
        } else if (down) {
            Acc.setY(-0.01f);
        } else {
            one = false;
        }
        //Moves player left if pressed and plays footsteps
        if (left) {
            Scale = -1;
            Acc.addX(-100);
            if (canJump) {
                Level().play("/sounds/footsteps.wav");
            }
        //Moves player right if pressed and plays footsteps
        } else if (right) {
            Scale = 1;
            Acc.addX(100);
            if (canJump) {
                Level().play("/sounds/footsteps.wav");
            }
        } else if (canJump) {
            two = false;
            Acc.setX(0);
        }
        float Clamp = canJump ? 1f : 0.1f;
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
            Isplayer = true;
            return;
        } else {
            Isplayer = false;
        }

        //Sets movement of character when level is won
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
            //Updates character position based upon collosion with platform
            bottom = new Vector(getPosition()).add(GetUp().mult(getScaledSpriteHeight() * -0.5f));
            top = new Vector(bottom).mult(-1f).add(getPosition()).add(getPosition());
            left = new Vector(getPosition()).add(new Vector(-getSpriteWidth() / 2, 0));
            right = new Vector(getPosition()).add(new Vector(getSpriteWidth() / 2, 0));
            _Top = im.sideUp();
            _bottom = im.sideDown();
            _left = im.sideLeft();
            _right = im.sideRight();
            //This was used for debugging
            DebugObject.AddLine(left, right);

            //Checks for collision with platforms
            Collison col = CollisonUtils.CheckForLineHits(getPosition(), bottom, _Top[0], _Top[1]);
            Collison col2 = CollisonUtils.CheckForLineHits(getPosition(), top, _bottom[0], _bottom[1]);
            Collison col3 = CollisonUtils.CheckForLineHits(getPosition(), right, _left[0], _left[1]);
            Collison col4 = CollisonUtils.CheckForLineHits(getPosition(), left, _right[0], _right[1]);

            //Checks if bottom of player collides with platform
            if (col.ISHIT && !down) {
                canJump = true;
                //Used for debugging
                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_Top[0], _Top[1]);
                //Checks where player collides with platform
                _hit = col.HITLOCATION;
                float x = (GetUp().mult(getSpriteHeight() * 0.5f)).getX(),
                        y = (GetUp().mult(getSpriteHeight() * 0.5f)).getY();
                DebugObject.AddCirles(new Vector(col.HITLOCATION.getX(), col.HITLOCATION.getY()));
                if (once) {
                    Velocity.setY(0);
                    once = false;
                }
                //Set position of player after collision with plaftform
                setPosition(col.HITLOCATION.getX() + x, col.HITLOCATION.getY() + y);
                col = null;
                col2 = null;
                return;
            }
            //Checks if players head/top of player collides with platform
            if (col2.ISHIT && Velocity.getY() > 0) {
                canJump = false;
                float x = new Vector(bottom).mult(0f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getX(),
                        y = new Vector(bottom).mult(-0.00f).add(GetUp().mult(getSpriteHeight() * -0.7f)).getY();
                
                //Set position of player after collision with plaftform
                setPosition(col2.HITLOCATION.getX() + x, col2.HITLOCATION.getY() + y);

                //Used for debugging
                DebugObject.AddLine(bottom, top);
                DebugObject.AddLine(_bottom[0], _bottom[1]);
                DebugObject.AddCirles(new Vector(col2.HITLOCATION.getX(), col2.HITLOCATION.getY()));
                //Move player upon collision
                if (once) {
                    Velocity.mult(new Vector(1, -.40f));
                    Acc.mult(new Vector(1, -.50f));
                    once = false;
                int rHit = forsounds.nextInt(7);
                int rOuch = forsounds.nextInt(2) + 1;
                //Play sound upon collision
                Level().play("/sounds/hit" + rHit + ".wav");
                Level().play("/sounds/ouch" + rOuch + ".wav");
                }
                col2 = null;
            }

            
            if (col3.ISHIT) {

                //Update character position on collision
                setPosition(new Vector(col3.HITLOCATION).add(new Vector(left).add(new Vector(getPosition()).mult(-1)).mult(-1)));
                //Used for debugging
                DebugObject.AddLine(_left[0], _left[1]);
            }
            
            if (col4.ISHIT) {
                
                //Used for debugging
                DebugObject.AddLine(left, right);
                DebugObject.AddLine(_right[0], _right[1]);
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
     *Initialises speed, acceleration, movement allowed, player has lost etc attributes
     */
    @Override
    public void dispose() {
        super.dispose();
        Velocity = Vector.Zero();
        Acc = Vector.Zero();
        hasupdated = true;
        Lock = false;
        hasLost = false;
        playerCount = 0;
    }

    /**
     *
     * @return
     */
    public Vector getVelocity() {
        return Velocity;
    }

    /**
     *
     * @param Velocity
     */
    public void setVelocity(Vector Velocity) {
        this.Velocity = Velocity;
    }

    /**
     *
     * @return
     */
    public Vector getAcc() {
        return Acc;
    }

    /**
     *
     * @param Acc
     */
    public void setAcc(Vector Acc) {
        this.Acc = Acc;
    }

}
