/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Utils.imageUtils;
import java.awt.Graphics2D;

/**
 *
 * @author RandomlyFuzzy
 */
public class Lava extends IDrawable {

    private boolean hasCollided = false;

    @Override
    public void init() {
//        UseTransforms(false);
        GetSprite("/images/platform/Platform.png");
    }

    @Override

    public void doMove() {
        setSpriteWidth(Game.getScaledWidth());
        setSpriteHeight(Game.getScaledHeight() / 4);
        DebugObject.AddCirles(Transform.getOffsetTranslation());
        setPosition(-Transform.getOffsetTranslation().getX() + Game.getScaledWidth() / 2, -Transform.getOffsetTranslation().getY() + Game.getScaledHeight());
    }

    @Override
    public void Update(Graphics2D g) {
        DrawLastLoadedImage(g);
    }

    @Override
    public void onCollison(IDrawable im) {
        if (im instanceof Player) {
            System.out.println("Playercollider");
            //play you loss ui
            hasCollided = true;
            if (Player.isHasLost()) {
                im.setIsCollidable(false);
            }
            Player.setHasLost(true);
            //add overLay
            Level().AddObject(new DeathOverlay());
        }
    }
}
