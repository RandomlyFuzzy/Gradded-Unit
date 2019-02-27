package com.FuturePixels.levels.SoloLevels;

import com.FuturePixels.Components.Transform;
import com.FuturePixels.Drawables.Levels.DebugObject;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Drawables.Levels.*;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Engine.AbstractClasses.IDrawable;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Components.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Level1coop extends ILevel {
    
    private Player player1;
    private Player player2;
    
    public Level1coop() {
        super();
    }
    
    @Override
    public void init() {
        setSimpleCollison(false);
        // (float) Math.PI * -0.25f / 3f));
        //        AddObject(new DebugObject());
        //Adding Platforms
        System.out.println("com.game.levels.level1.<init>()");
        AddObject(new HUD());
        AddObject(new PlatForm(new Vector(0, 0), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(200, -150), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(100, -350), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(400, -500), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(200, -650), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(375, -850), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(385, -1000), 0)).GetSprite("/images/platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(250, -1150), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(150, -1325), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(350, -1475), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(200, -1650), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(500, -1650), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(350, -1850), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(450, -2000), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(325, -2200), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(450, -2400), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(200, -2600), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(250, -2800), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(300, -3000), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(500, -3200), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(200, -3400), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(450, -3600), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(200, -3800), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(300, -4000), 0)).GetSprite("/images/platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(400, -4200), 0)).GetSprite("/images/platform/rock_platform_clean_00.png");
        AddObject(new PlatForm(new Vector(200, -4400), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(300, -4600), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(400, -4800), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(500, -5000), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(50, -5200), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(550, -5400), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        AddObject(new PlatForm(new Vector(700, -5600), 0)).GetSprite("/images/platform/rock_platform_clean_01.png");
        
        player1 = new Player();
        player2 = new Player();
//        player1.setScale(new Vector(0.3f,0.5f));
        AddObject(player1).setPosition(0, -50);
        AddObject(player2).setPosition(50, -50);
        AddObject(new Flag(new Level2Solo())).setPosition(new Vector(800, -5650));
//        AddObject(new ScrollingBackground());

//        LeaderBoard.AddTime(System.nanoTime());
//        Cameraopos = new Vector(player1.getPosition()).mult(-1).add(new Vector(Game.g.getWindowWidth() / 2, Game.g.getWindowHeight() / 2));
        Transform.setOffsetTranslation(new Vector((Game.g.getWindowWidth() * .6f) / 2, 0));
    }
    
    @Override
    public void Update(ActionEvent ae) {
        
        if (player1 == null) {
            return;
        }
        
        if (player2 == null) {
            return;
        }
        
    }
    
    @Override
    public void Draw(Graphics2D g) {
        g.drawImage(GetSprite("/Images/background.png"), 0, 0, (Game.g.getWindowWidth()), (Game.g.getWindowHeight()), null);
//        System.out.println("com.game.levels.Level1Solo.paintComponent()");
    }
    
    @Override
    public void keyPress(KeyEvent e) {
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(true);
                
            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(true);
                
            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(true);
            }

            //switches are good for definate cases not for what we need though
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_SPACE:
//                    Pressed = true;
//                    player1.setUp(true);
//                    break;
//                case KeyEvent.VK_DOWN:
//                    player1.setDown(true);
//                    break;
//                case KeyEvent.VK_LEFT:
//                    player1.setLeft(true);
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    player1.setRight(true);
//                    break;
//            }
        } catch (Exception ex) {
            
        }
        
        //PLAYER 2
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP2()) {
                player2.setRight(true);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(true);
                
            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(true);
                
            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(true);
            }
        } catch (Exception ex) {
            
        }
    }
    
    @Override
    public void keyRelease(KeyEvent e) {
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP1()) {
                player1.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP1()) {
                player1.setLeft(false);
                
            } else if (code == GamePreferences.gp.getKeyJumpP1()) {
                player1.setUp(false);
                
            } else if (code == GamePreferences.gp.getKeyDropP1()) {
                player1.setDown(false);
            }
        } catch (Exception ex) {
            
        }
        
        try {
            int code = e.getKeyCode();
            if (code == GamePreferences.gp.getKeyRightP2()) {
                player2.setRight(false);
            } else if (code == GamePreferences.gp.getKeyLeftP2()) {
                player2.setLeft(false);
                
            } else if (code == GamePreferences.gp.getKeyJumpP2()) {
                player2.setUp(false);
                
            } else if (code == GamePreferences.gp.getKeyDropP2()) {
                player2.setDown(false);
            }

//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_SPACE:
//                    Pressed = true;
//                    player1.setUp(false);
//                    break;
//                case KeyEvent.VK_DOWN:
//                    player1.setDown(false);
//                    break;
//                case KeyEvent.VK_LEFT:
//                    player1.setLeft(false);
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    player1.setRight(false);
//                    break;
//            }
        } catch (Exception ex) {
            
        }
        
    }
    
}
