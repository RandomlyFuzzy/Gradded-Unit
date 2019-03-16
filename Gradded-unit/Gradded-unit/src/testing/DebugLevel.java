/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Utils.FileUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;

/**
 *
 * @author Liam Rickman 1748905
 */
public class DebugLevel extends ILevel {

    /**
     *
     */
    @Override
    public void init() {
        AddObject(new testObject());
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
    }

    /**
     *
     * @param gd
     */
    @Override
    public void Draw(Graphics2D gd) {
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyPress(KeyEvent ke) {
        
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyRelease(KeyEvent ke) {
    }

}
