/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.Components.Transform;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.imageUtils;
import java.awt.Graphics2D;

/**
 *
 * @author Liam Woolley 1748910
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
            Level().play("/Sounds/LevelFail.wav");
            Level().play("/Sounds/Scream1.wav");
        }
    }
}
