package com.FuturePixels.levels.Menus;

import com.FuturePixels.Drawables.Menus.Button;
import com.FuturePixels.Drawables.Menus.HUDdelegate;
import com.FuturePixels.Drawables.Menus.Mouse;
import com.Liamengine.Engine.AbstractClasses.ILevel;
import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.Components.Vector;
import com.Liamengine.Engine.Utils.FileUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Leaderboard menu shows the top 10 times for each levels completion.
 * @author Liam Woolley 1748910
 */
public class LeaderBoard extends ILevel {

    private String previousind = "";
    //Sets the default string that will be shown on the HUD (Level 1 times are loaded first)
    private static String Currentind = "Level1";
    //Creates an array list that will be used to store the times later
    private ArrayList<String> times = new ArrayList<String>();

    /**
     * @return
     */
    public static String getCurrentind() {
        return Currentind;
    }

    /**
     * @param Currentind
     */
    public static void setCurrentind(String Currentind) {
        LeaderBoard.Currentind = Currentind;
    }
    
    public LeaderBoard() {
        super();
        //Stops any audio that is already playing
        setStopAudioOnStart(false);
    }

    @Override
    public void init() {
        //Creates a button that links back to the main menu
        AddObject(new Button(new Vector(0.93f, 0.9f), "Back", new HUDdelegate() {
            @Override
            public void OnClick(Button b) {
                Game.SetLevelActive(new MainMenu(Vector.Zero()));
            }
        })).GetSprite("/images/button_0.png");
        
        //Creates 5 buttons using a for loop for loading the level times
        //When clicked they load the times for the appropriate level
        for (int i = 0; i < 5; i++) {
            AddObject(new Button(new Vector(((0.175f)), ((0.115f * (i % 6)) + 0.399f)), ("Level " + ((i % 5) + 1)) , new HUDdelegate() {
                public void OnClick(Button b) {
                    LeaderBoard.setCurrentind(b.getMessage().replace(" ",""));
                }
            })).GetSprite("/images/button_2.png");
        }

        //Loads the background image to the leaderboard background
        setBackgroundimage(GetSprite("/images/backgrounds/leaderboard.png"));
        
        //Adds the mouse object to the level so the user can see what they are pointing at
        AddObject(new Mouse());
    }

    /**
     * Updates the times shown on the leaderboard when a button is clicked to show the new times
     * @param ae
     */
    @Override
    public void Update(ActionEvent ae) {
        if (previousind != Currentind) {
            times = new ArrayList<String>();
            times.addAll(
                    FileUtils.GetFileSplit("resources/savedata/" + Currentind + "Solo.txt", "\n", true)
            );

            
            if (times.size() > 0) {
                times.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (o2.equals(new String())) {
                            return -1;
                        }
                        if (o1.equals(new String())) {
                            return 1;
                        }
                        return Double.parseDouble(o1) > Double.parseDouble(o2) ? 1 : -1;
                    }
                });
                if (times.size() > 10) {
                    String set = "";
                    for (int i = 0; i < 10; i++) {
                        set += times.get(i) + "\n";
                    }
                    FileUtils.SetFileContence("resources/savedata/" + Currentind + "Solo.txt", set);
                }
            }
            previousind = Currentind;
        }
    }

    /**
     * @param g
     */
    @Override
    public void Draw(Graphics2D g) {
        float y = 0.3f;
        //Loads the font 
        Font f = g.getFont();
        Font f2 = f.deriveFont(1, Game.WorldScale().getY() * 50);
        g.setFont(f2);
        //Sets a colour for the background rectangle that is transparent
        g.setColor(new Color(55, 55, 55, 150));
        //Creates the background rectangle for the times. The rectangle will scale vertically depending on how many times are currently stored.
        //The rectangle will end just after the last time.
        g.fillRect(
                    (int) ((Game.getWindowWidth() / 18)),
                    (int) ((0.24f) * Game.getWindowHeight()),
                    (int) (Game.getWindowWidth()*0.885f),
                    (int) ((0.615f * Game.getWindowHeight()) * ((float) (10 > times.size() ? times.size() : 10f) / 10f)));
        g.setColor(Color.white);
        //Creates the
        int w = (int) (g.getFontMetrics().stringWidth(Currentind.replace("solo","")) * 1.05f);
        g.drawString(Currentind.replace("solo",""), (int) ((Game.getWindowWidth() / 5.5) - w / 2),
                (int) ((0.325f) * Game.getWindowHeight()));
        f2 = f.deriveFont(1, Game.WorldScale().getY() * 10);
        g.setFont(f2);
        if ((times.size() == 1 && !times.get(0).equals(new String()) && !Double.isNaN(Double.parseDouble(times.get(0)))) || times.size() > 1) {

            g.setFont(f2.deriveFont(1, f2.getSize() + ((int) Math.log(0.5f) / 10)));
            String s = times.get(0);
            String str = "No " + (0 + 1) + " Place with " + s + " secs";
            w = (int) (g.getFontMetrics().stringWidth(str) * 1.05f);
            
            w = (int) (g.getFontMetrics().stringWidth(Currentind) * 1.05f);
            g.drawString(Currentind, (Game.getWindowWidth() / 2 - (w / 2)), y);

            g.setColor(Color.WHITE);
            int inc = 0;
            for (int i = 0; i < (10 > times.size() ? times.size() : 10); i++) {
                g.setFont(f2.deriveFont(1, (int)( f2.getSize() +(Math.log10(1.5f+(10f-i))*20f*Game.WorldScale().getY()) )));
                s = times.get(i);
                str = "#" + (i + 1) + ": " + s + " secs";
                w = g.getFontMetrics().stringWidth(str);
                g.drawString(str,
                        (Game.getWindowWidth() *0.6f) - (w/2),
                        (((i % 20) * 0.06f) + 0.31f) * Game.getWindowHeight());
                y += 0.03f;
            }
        }
        g.setFont(f);
    }

    /**
     * @param e
     */
    @Override
    public void keyPress(KeyEvent e) {

    }

    /**
     * @param e
     */
    @Override
    public void keyRelease(KeyEvent e) {

    }

    /**
     *
     */
    public void dispose() {
        super.dispose();
        //Resets the variables
        Currentind = "Level2";
        previousind = "Level1";
        times = new ArrayList<String>();
    }
}
