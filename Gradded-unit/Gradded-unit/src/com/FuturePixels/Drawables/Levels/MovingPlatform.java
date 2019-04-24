/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.SpriteSheet;
import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *This platform moves side to side and has a different image from the static platform
 * @author Liam Woolley 1748910
 */
public class MovingPlatform extends IDrawable {

    private Vector[] Cycle;
    private float Speed;
    private int ind = 0;
    private Vector Add = Vector.Zero();
    private SpriteSheet she;
    private boolean AsSpriteSheet = false;
    private SpriteRenderer sr;

    /**
     *
     */
    public MovingPlatform() {
        super();
    }

    /**
     *Sets position, rotation and movement speed of the platform
     * @param position
     * @param RadianRotation
     * @param cycle
     * @param Speed
     */
    public MovingPlatform(Vector position, double RadianRotation, Vector[] cycle, float Speed) {
        super();
        setPosition(position);
        setRotation(RadianRotation);
        this.Cycle = cycle;
        this.Speed = Speed*2;
    }

    /**
     *
     * @param position
     * @param RadianRotation
     * @param cycle
     * @param Speed
     * @param asSpriteSheet
     */
    public MovingPlatform(Vector position, double RadianRotation, Vector[] cycle, float Speed, boolean asSpriteSheet) {
        this(position, RadianRotation, cycle, Speed);
        AsSpriteSheet = asSpriteSheet;
    }

    /**
     *Initialises image
     */
    @Override
    public void init() {
        GetSprite("/images/platform/rock_platform_moss_01.png");
    }

    /**
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    public IDrawable GetSprite(String path, int width, int height) {
        if (AsSpriteSheet) {
            BufferedImage bi = imageUtils.T.GetImage(path);
            she = new SpriteSheet(0, 0, width, height);
            she.inputImage(bi);
            sr = new SpriteRenderer(this, 0.3f);
            sr.inputImage(bi, she);
            setSpriteWidth(width);
            AddComponents(sr);
        } else {
            GetSprite(path);
        }
        return this;
    }

    /**
     *Updates the movement speed and position of the platform
     */
    @Override
    public void doMove() {
        Vector dist = getPosition().add(new Vector(Cycle[ind]).mult(-1));

        if (dist.Length() <= Speed * 2) {
            ind++;
            ind = ind % Cycle.length;
        }
        Add = dist.Normalized().mult(-Speed);
        Vector newpos = getPosition().add(Add);
        DebugObject.AddCirles(Cycle[ind]);
        setPosition(newpos);
        UpdateBounds();
    }

    /**
     *Updates the image every frame
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        if (((-Transform.getOffsetTranslation().getX() - (Game.getScaledWidth()) < getPosition().getX()
                && (-Transform.getOffsetTranslation().getX() + (Game.getScaledWidth())) > getPosition().getX()
                && (-Transform.getOffsetTranslation().getY() - (Game.getScaledHeight())) < getPosition().getY()
                && (-Transform.getOffsetTranslation().getY() + (Game.getScaledHeight())) > getPosition().getY()))) {
            if (!AsSpriteSheet) {
                DrawLastLoadedImage(g);
            }
        }
    }

    /**
     *Update image on collision
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Player) {
            im.addPosition(new Vector(Add).mult(1));
        }
    }
}
