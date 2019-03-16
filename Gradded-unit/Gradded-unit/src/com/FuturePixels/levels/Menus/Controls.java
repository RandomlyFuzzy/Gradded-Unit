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
public class Controls extends ILevel {

    Vector relataiveity = Vector.Zero();

    /**
     *
     */
    public static BlackoutButton BB = new BlackoutButton("Press to get key", new HUDdelegate() {
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
            String vals = "";
            vals += "" + GamePreferences.gp.getKeyLeftP1() + "\n";
            vals += "" + GamePreferences.gp.getKeyRightP1() + "\n";
            vals += "" + GamePreferences.gp.getKeyJumpP1() + "\n";
            vals += "" + GamePreferences.gp.getKeyLeftP2() + "\n";
            vals += "" + GamePreferences.gp.getKeyRightP2() + "\n";
            vals += "" + GamePreferences.gp.getKeyJumpP2() + "\n";
            vals += "" + GamePreferences.gp.getKeyDropP1() + "\n";
            vals += "" + GamePreferences.gp.getKeyDropP2();

            ((Button) b.Level().GetObject(val)).setMessage(b.getMessage());

            FileUtils.SetFileContence("resources/data/Preferences.txt", vals);
            b.Level().GetObject(9).setEnabled(false);
            b.setMessage("Press to get key");
        }
    }
    );
    private static int val = 1;
    private static int lastKey = 1;

    /**
     *
     */
    public Controls() {
        super();
        setStopAudioOnStart(false);
        setSimpleCollison(false);

    }

    /**
     *
     * @param change
     */
    public static void ReadyForKeyChange(int change) {
        BB.setEnabled(true);
        val = change;
    }

    /**
     *
     */
    @Override
    public void init() {

        AddObject(new Button(new Vector(0.1f, 0.1f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new Settings());
            }
        }));

        //PLAYER 1
        AddObject(new Button(new Vector(0.30f, 0.3f), "LEFT = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(1);
            }
        }));
        AddObject(new Button(new Vector(0.30f, 0.425f), "RIGHT = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(2);
            }
        }));
        AddObject(new Button(new Vector(0.30f, 0.550f), "JUMP = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(3);
            }
        }));

        //PLAYER 2
        AddObject(new Button(new Vector(0.70f, 0.3f), "LEFT = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(4);
            }
        }));
        AddObject(new Button(new Vector(0.70f, 0.425f), "RIGHT = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(5);
            }

        }));
        AddObject(new Button(new Vector(0.70f, 0.550f), "JUMP = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(6);
            }

        }));

        //Player 1 Drop
        AddObject(new Button(new Vector(0.30f, 0.675f), "DROP = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(7);
            }
        }));

        //Player 2 Drop
        AddObject(new Button(new Vector(0.70f, 0.675f), "DROP = ", new HUDdelegate() {
            public void OnClick(Button b) {
                Controls.ReadyForKeyChange(8);
            }
        }));

        String[] Values = FileUtils.GetFileSplit("resources/Data/Preferences.txt", "\n");
        for (int i = 1; i < 9; i++) {
            String text = "";
            text += ((Button) GetObject(i)).getMessage();
            text += KeyEvent.getKeyText((int)Integer.parseInt(Values[i - 1].trim()));
            ((Button) GetObject(i)).setMessage(text);
        }

        BB.setEnabled(false);
        AddObject(BB);
        AddObject(new Mouse());
        setBackgroundimage(GetSprite("/Images/backgrounds/background1.png"));
    }

    /**
     *
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        relataiveity = Game.ButtonDims();
    }

    /**
     *
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {

        //Player Text
        g.setColor(Color.WHITE);
        g.setColor(new Color(55, 55, 55, 200));
        g.fillRect((int) ((0.222f) * Game.getWindowWidth()), (int) ((0.195f * Game.getWindowHeight())), (int) ((200f / 1280f) * Game.getWindowWidth()), (int) ((390f / 720f) * Game.getWindowHeight()));
        g.fillRect((int) ((0.622f) * Game.getWindowWidth()), (int) ((0.195f * Game.getWindowHeight())), (int) ((200f / 1280f) * Game.getWindowWidth()), (int) ((390f / 720f) * Game.getWindowHeight()));

        //g.fillRect((int) ((0.03f) * Game.g.getScaledWidth()), (int) ((0.235f) * Game.g.getScaledHeight()), (int) ((((times.size() / 20) * 0.13f)+0.13f) * Game.g.getScaledWidth()), (int) (((((times.size() >= 20f ? 20f : times.size())) * 0.0295f)) * Game.g.getScaledHeight()));
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Player 1 Controls", Game.getWindowWidth() * 0.30f - metrics.stringWidth("Player 1 Controls") / 2, Game.getWindowHeight() * 0.23f);
        g.drawString("Player 2 Controls", Game.getWindowWidth() * 0.70f - metrics.stringWidth("Player 2 Controls") / 2, Game.getWindowHeight() * 0.23f);
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {
        if (BB.isEnabled()) {
            String buttontext = ((Button) GetObject(val)).getMessage();
            buttontext = buttontext.substring(0, buttontext.indexOf("=") + 2);
            String text = KeyEvent.getKeyText(e.getKeyCode());
            BB.setMessage(buttontext + text);
            lastKey = e.getKeyCode();
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {

    }

}
