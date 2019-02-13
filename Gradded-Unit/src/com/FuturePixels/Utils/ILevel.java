/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

import com.FuturePixels.Utils.MusicUtils;
import com.FuturePixels.Utils.imageUtils;
import com.FuturePixels.Utils.IDrawable;
import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class ILevel extends JPanel implements ActionListener {

    public final Game game;
    public final Timer timer;
    private ArrayList<IDrawable> gameObjs = new ArrayList<IDrawable>();
    private Vector MousePos = new Vector(Vector.Zero);
    private boolean IsDragging = false, IsInside = true, IsClicking = false;
    private TAdapter inputAdapter;

    public Vector getMousePos() {
        return MousePos;
    }

    public boolean isIsDragging() {
        return IsDragging;
    }

    public boolean isIsInside() {
        return IsInside;
    }

    public boolean isIsClicking() {
        return IsClicking;
    }
    private HashMap<Integer, Boolean> MouseButtonPressed = new HashMap<Integer, Boolean>();

    public boolean GetMouseButtonDown(int ind) {
        if (!MouseButtonPressed.containsKey(ind)) {
            return false;
        }
        return MouseButtonPressed.get(ind);
    }

    public ILevel() {
        timer = new Timer(16, this);
        game = Game.g;
    }

    public void OnStart() {
        setFocusable(true);
        setDoubleBuffered(true);
        inputAdapter = new TAdapter();
        addKeyListener(inputAdapter);
        addMouseListener(inputAdapter);
        init();
    }

    public void AddObject(IDrawable Drawable) {
        gameObjs.add(Drawable);
        Drawable.init();
        Drawable.initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Update(ae);
    }

    public void movement() {
        gameObjs.forEach((obj) -> {
            obj.doMove();
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
        PostUpdate(g);
    }

    public void PostUpdate(Graphics g) {
        game.SetDelta();
        gameObjs.forEach((a) -> {
            a.Update(g);
            a.UpdateComponents();
        });
        g.dispose();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void Add(IDrawable im) {
        gameObjs.add(im);
    }

    public synchronized void play(InputStream soundResource) {
        MusicUtils.play(soundResource);
    }

    protected synchronized BufferedImage GetSprite(String URI) {
        BufferedImage g = imageUtils.T.GetImage(URI);
        return g;
    }

    @SuppressWarnings("this will be amended soon :P")
    public void checkCollionsions() {
        gameObjs.forEach((a) -> {
            gameObjs.forEach((b) -> {
                if (a != b) {
                    a.CheckCollions(b);
                }
            });
        });

//        thePlayer.checkCollision(theTreasure);
    }

    public abstract void keyPress(KeyEvent e);

    public abstract void keyRelease(KeyEvent e);

   
    private class TAdapter extends InputAdapter {

        public TAdapter() {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyRelease(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPress(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            IsClicking = true;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            MouseButtonPressed.put(e.getButton(), true);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            MouseButtonPressed.put(e.getButton(), false);
            boolean isactiveOne = false;

            IsDragging = false;

            if (MouseButtonPressed.keySet().toArray().length > 0) {
                Integer[] arr = new Integer[MouseButtonPressed.size()];
                MouseButtonPressed.keySet().toArray(arr);
                for (Integer a : arr) {
                    isactiveOne = isactiveOne != !MouseButtonPressed.get(a) || isactiveOne;
                }

                IsClicking = isactiveOne;
            } else {
                IsClicking = false;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            start();
            IsInside = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            stop();
            IsInside = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            IsDragging = true;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            MousePos = new Vector(e.getX(), e.getY());
        }
    }

    
    public abstract void init();

    public abstract void Update(ActionEvent ae);

    public abstract void Draw(Graphics g);
}
