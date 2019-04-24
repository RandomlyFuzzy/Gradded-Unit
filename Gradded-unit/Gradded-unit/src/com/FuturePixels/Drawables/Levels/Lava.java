/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.SpriteSheet;
import com.Liamengine.Engine.Entry.Game;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *Lava is displayed at the bottom of the screen and when the player collides with
 *it, they lose and the death overlay window is called.
 * @author Liam Woolley 1748910
 */
public class Lava extends IDrawable {

    private boolean hasCollided = false;
    private Random forsounds = new Random();
    private SpriteSheet she = new SpriteSheet(0, 0, 200, 100);

    /**
     *Initialise lava image
     */
    @Override
    public void init() {
//        UseTransforms(false);
        GetSprite("/images/lava.png");
        she.inputImage(getLastImage());

    }

    /**
     *Sets lava size and position
     */
    @Override

    public void doMove() {
        setSpriteWidth(Game.getScaledWidth()-2);
        setSpriteHeight(Game.getScaledHeight() / 2);
        setPosition(
               -Transform.getOffsetTranslation().getX() + Game.getScaledWidth() / 2,
               -Transform.getOffsetTranslation().getY() + Game.getScaledHeight()-(getSpriteHeight()*-0.125f));
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        //stops the lava from jumping across the screen
        if(Level().getTime()<=0.05f){
            return;
        }
        she.IncrementX(0.4f);
        DrawLastLoadedImageAsSpriteSheet(g, she);
    }

    /**
     *Displays death overlay upon player colliding with lava and plays sounds
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Player && !Player.isLock()) {
            //play you loss ui
            hasCollided = true;
            if (Player.isHasLost()) {
                im.setIsCollidable(false);
            }
            Player.setHasLost(true);
            //add overLay

            int r = forsounds.nextInt(4) + 1;
            
            
            Level().AddObject(new DeathOverlay());
            Level().play("/sounds/levelfail.wav");
            Level().play("/sounds/scream" + r + ".wav");
        }
    }
}
