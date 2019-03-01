/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Drawables.Menus.BlackoutButton;
import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.GamePreferences;
import com.FuturePixels.Drawables.Menus.HUDAbstract;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Components.Vector;
import com.FuturePixels.Engine.Entry.Game;
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
public class Controls extends ILevel {

    Vector relataiveity = Vector.Zero();
    public static BlackoutButton BB = new BlackoutButton("Press to get key", new HUDAbstract() {
        public void OnClick(BlackoutButton b) {
            if (Controls.val == 1) {
                GamePreferences.gp.setKeyLeftP1(Controls.lastKey);
            } else if (Controls.val == 2) {
                GamePreferences.gp.setKeyRightP1(Controls.lastKey);

            } else if (Controls.val == 3) {
                GamePreferences.gp.setKeyJumpP1(Controls.lastKey);

            } else if (Controls.val == 4) {
                GamePreferences.gp.setKeyLeftP2(Controls.lastKey);

            } else if (Controls.val == 5) {
                GamePreferences.gp.setKeyRightP2(Controls.lastKey);

            } else if (Controls.val == 6) {
                GamePreferences.gp.setKeyJumpP2(Controls.lastKey);

            } else if (Controls.val == 7) {
                GamePreferences.gp.setKeyDropP1(Controls.lastKey);

            } else if (Controls.val == 8) {
                GamePreferences.gp.setKeyDropP2(Controls.lastKey);

            }
            ((Button) b.Level().GetObject(val)).setMessage(b.getMessage());

            b.Level().GetObject(10).setEnabled(false);
        }
    }
    );
    private static int val = -1;
    private static int lastKey = -1;

    public Controls() {
        super();
        setStopAudioOnStart(false);
        setSimpleCollison(false);

    }

    public static void ReadyForKeyChange(int change) {
        BB.setEnabled(true);
        val = change;
    }

    @Override
    public void init() {

        AddObject(new Button(new Vector(0.1f, 0.1f), "Back", new HUDAbstract() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }));
        
        
        //PLAYER 1
        AddObject(new Button(new Vector(0.30f, 0.3f), "LEFT = A", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(1);
            }
        }));
        AddObject(new Button(new Vector(0.30f, 0.425f), "RIGHT = D", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(2);
            }
        }));
        AddObject(new Button(new Vector(0.30f, 0.550f), "JUMP = SPACE", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(3);
            }
        }));

        //PLAYER 2
        AddObject(new Button(new Vector(0.70f, 0.3f), "LEFT = LEFT", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(4);
            }
        }));
        AddObject(new Button(new Vector(0.70f, 0.425f), "RIGHT = RIGHT", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(5);
            }
        
        }));
        AddObject(new Button(new Vector(0.70f, 0.550f), "JUMP = NUMPAD 0", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(6);
            }
        
        }));
        
        //Player 1 Drop
        AddObject(new Button(new Vector(0.30f, 0.675f), "DROP = S", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(7);
            }
        }));
        
        //Player 2 Drop
        AddObject(new Button(new Vector(0.70f, 0.675f), "DROP = DOWN", new HUDAbstract() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(8);
            }
        }));
    
        AddObject(new HUD());
        BB.setEnabled(false);
        AddObject(BB);
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/Images/WIP Background.png"));
    }

    @Override
    public void Update(ActionEvent ae) {
        relataiveity = GamePreferences.ButtonDims();
    }

    @Override
    public void Draw(Graphics2D g) {

        //Player Text
        g.setColor(Color.WHITE);
        g.setColor(new Color(55,55,55,200));
        g.fillRect((int) ((0.222f) * Game.g.getWindowWidth()), (int) ((0.195f * Game.g.getWindowHeight())), (int)((200f/1280f)* Game.g.getWindowWidth()), (int)((390f/720f) * Game.g.getWindowHeight()));
        g.fillRect((int) ((0.622f) * Game.g.getWindowWidth()), (int) ((0.195f * Game.g.getWindowHeight())), (int)((200f/1280f)* Game.g.getWindowWidth()), (int)((390f/720f) * Game.g.getWindowHeight()));
        
        //g.fillRect((int) ((0.03f) * Game.g.getScaledWidth()), (int) ((0.235f) * Game.g.getScaledHeight()), (int) ((((times.size() / 20) * 0.13f)+0.13f) * Game.g.getScaledWidth()), (int) (((((times.size() >= 20f ? 20f : times.size())) * 0.0295f)) * Game.g.getScaledHeight()));
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Player 1 Controls", Game.g.getWindowWidth() * 0.30f - metrics.stringWidth("Player 1 Controls") / 2, Game.g.getWindowHeight() * 0.23f);
        g.drawString("Player 2 Controls", Game.g.getWindowWidth() * 0.70f - metrics.stringWidth("Player 2 Controls") / 2, Game.g.getWindowHeight() * 0.23f);
    }

    @Override
    public void keyPress(KeyEvent e) {
        if (BB.isEnabled()) {
            String buttontext = ((Button) GetObject(val)).getMessage();
            buttontext = buttontext.substring(0, buttontext.indexOf("=") + 2);
            String text = e.paramString();
            System.out.println("com.FuturePixels.levels.Menus.Controls.keyPress() " + text);
            text = text.substring(text.indexOf("keyText") + 8);
            System.out.println("com.FuturePixels.levels.Menus.Controls.keyPress() " + text);
            text = text.substring(0, text.indexOf(","));
            System.out.println("com.FuturePixels.levels.Menus.Controls.keyPress() " + text);

            BB.setMessage(buttontext + text);
            lastKey = e.getKeyCode();
//            B.setMessage(B.getMessage().substring(0,B.getMessage().length()-2)+e.getKeyChar());
        }
    }

    @Override
    public void keyRelease(KeyEvent e) {

    }

}
