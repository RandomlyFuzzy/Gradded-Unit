/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.SpriteSheet;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Entry.Game;
import java.awt.Graphics2D;

/**
 *
 * @author RandomlyFuzzy
 */
public class testObject extends IDrawable {

    SpriteSheet she = new SpriteSheet(0, 0, 60, 90);

    /**
     *
     */
    @Override
    public void init() {
        GetSprite("/Images/player/player_00.png");
        setPosition(new Vector(Game.getScaledWidth() / 2, Game.getWindowHeight() / 2));
        she.inputImage(getLastImage());
        setScale(new Vector(2, 2));
    }

    /**
     *
     */
    @Override
    public void doMove() {
        she.IncrementX(0.1f);
    }

    /**
     *
     * @param gd
     */
    @Override
    public void Update(Graphics2D gd) {
        DrawLastLoadedImageAsSpriteSheet(gd, she);
    }

    /**
     *
     * @param id
     */
    @Override
    public void onCollison(IDrawable id) {
    }

}
