/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Levels;

import com.Liamengine.Engine.Entry.Game;
import com.Liamengine.Engine.AbstractClasses.IDrawable;
import com.Liamengine.Engine.Components.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Liam Woolley 1748910
 */
public class HUD extends IDrawable {

    private static ArrayList<String> texts = new ArrayList<String>();
    private static ArrayList<Color> Colours = new ArrayList<Color>();
    private static ArrayList<Double> Scales = new ArrayList<Double>();
    private static ArrayList<Vector> textsPos = new ArrayList<Vector>();

    /**
     *
     */
    public HUD() {
        super();
    }

    /**
     *
     * @param text
     * @param position
     * @return
     */
    public static int AddText(String text, Vector position) {
        texts.add(text);
        textsPos.add(position);
        Scales.add(20.0);
        Colours.add(Color.white);
        return texts.size() - 1;
    }

    /**
     *
     * @param ind
     * @param Text
     */
    public static void EditText(int ind, String Text) {
        if (texts.size() <= ind || ind < 0) {
            return;
        }
        texts.set(ind, Text);
    }

    /**
     *
     * @param ind
     * @param pos
     */
    public static void EditText(int ind, Vector pos) {
        textsPos.set(ind, pos);
    }

    /**
     *
     * @param ind
     * @param colour
     */
    public static void EditText(int ind, Color colour) {
        Colours.set(ind, colour);
    }

    /**
     *
     * @param ind
     * @param val
     */
    public static void EditText(int ind, Double val) {
        Scales.set(ind, val);
    }
    
    
    
    /**
     *
     */
    @Override
    public void init() {
        setIsCollidable(false);
        UseTransforms(false);
    }

    /**
     *
     */
    @Override
    public void doMove() {
    }

    /**
     *
     * @param g
     */
    @Override
    public void Update(Graphics2D g) {
        Font f = g.getFont();
        for (int i = 0; i < texts.size(); i++) {
            g.setColor(Colours.get(i));
            g.setFont(g.getFont().deriveFont((float)(Game.WorldScale().getY() * Scales.get(i))));
            g.drawString(this.texts.get(i), this.textsPos.get(i).getX(), this.textsPos.get(i).getY());
        }
        g.setFont(f);

    }

    /**
     *
     * @param im
     */
    @Override
    public void onCollison(IDrawable im) {

    }

    /**
     *
     */
    @Override
    public void dispose() {
        super.dispose();
        texts = new ArrayList<String>();
        textsPos = new ArrayList<Vector>();
    }
}
