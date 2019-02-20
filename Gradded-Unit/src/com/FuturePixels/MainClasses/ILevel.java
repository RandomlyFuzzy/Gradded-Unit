/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses;

import com.FuturePixels.Components.*;
import com.FuturePixels.Drawables.Levels.HUD;
import com.FuturePixels.Entry.Game;
import com.FuturePixels.levels.MainMenu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class ILevel extends JPanel implements ActionListener {

    public final Game game;
    private Timer timer;
    private ArrayList<IDrawable> gameObjs = new ArrayList<IDrawable>();
    private Vector MousePos = new Vector(Vector.Zero());
    private boolean IsDragging = false, IsInside = true, IsClicking = false;
    private KeyEvent LastKeyPress = null;

    public KeyEvent getLastKeyPress() {
        if (LastKeyPress == null) {
            System.err.println("their was no last key pressed");
        }
        return LastKeyPress;
    }

    public void setLastKeyPress(KeyEvent LastKeyPress) {
        this.LastKeyPress = LastKeyPress;
    }
    private TAdapter InputAdapter = null;

    void resetParams() {
        IsDragging = false;
        IsInside = true;
        IsClicking = false;
        InputAdapter = null;
        MousePos = new Vector(Vector.Zero());
        gameObjs = new ArrayList<IDrawable>();
        timer = null;
    }

    public Vector getMousePos() {
        return MousePos;
    }

    public boolean isDragging() {
        return IsDragging;
    }

    public boolean isInside() {
        return IsInside;
    }

    public boolean isClicking() {
        return IsClicking;
    }
    private HashMap<Integer, Boolean> MouseButtonPressed = new HashMap<Integer, Boolean>();

    public boolean GetMouseButtonDown(int ind) {
        if (!MouseButtonPressed.containsKey(ind)) {
            return false;
        }
        return MouseButtonPressed.get(ind);
    }
    private int temp1;

    public ILevel() {
        timer = new Timer(15, this);
        game = Game.g;
    }

    private ILevel get() {
        return this;
    }

    public void OnStart() {
        setFocusable(true);
        setDoubleBuffered(true);
        init();
        temp1 = HUD.AddText("", new Vector(0, 20));

    }

    public IDrawable AddObject(IDrawable Drawable) {
        gameObjs.add(Drawable);
        Drawable.CoreInit();
        return Drawable;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        checkCollionsions();
        game.SetDelta();
        Update(ae);
        movement();
        this.repaint();

    }

    public void movement() {
        for (int i = 0; i < gameObjs.size(); i++) {
            gameObjs.get(i).doMove();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Draw(g2d);
        if (DebugCollisons) {
            try {
                HUD.EditText(temp1, ("" + (1 / Game.g.getDelta())).substring(0, ("" + (1 / Game.g.getDelta())).indexOf(".") + 3) + "fps on update");
            } catch (Exception e) {

            }

//            int CWH = 6;
//            for (int j = (int) -Transform.getOffsetTranslation().getX() - (int) Game.g.getWindowWidth() / 2; j < (int) -Transform.getOffsetTranslation().getX() + (int) Game.g.getWindowWidth(); j += CWH) {
//                for (int k = (int) -Transform.getOffsetTranslation().getY() - (int) Game.g.getWindowHeight() / 2; k < (int) -Transform.getOffsetTranslation().getY() + (int) Game.g.getWindowHeight(); k += CWH) {
//                    boolean draw = false;
//                    for (int i = 0; i < gameObjs.size(); i++) {
//                        if (gameObjs.get(i).getBounds().contains(j, k)) {
//                            draw = true;
//                            break;
//                        }
//                    }
//                    if (draw) {
//                        g.fillRect((int) Transform.getOffsetTranslation().getX() + j, (int) Transform.getOffsetTranslation().getY() + k, CWH, CWH);
//                    }
//                }
//            }
        } else {
            HUD.EditText(temp1, "");
        }
        PostUpdate(g2d);
    }

    public IDrawable GetObject(int index) {
        return gameObjs.get(index);
    }

    public int GetObjectCount() {
        return gameObjs.size();
    }

    public void PostUpdate(Graphics2D g) {
        for (int i = gameObjs.size() - 1; i >= 0; i--) {
            if (gameObjs.get(i).isEnabled()) {
                gameObjs.get(i).CoreUpdate(g);
                gameObjs.get(i).setIsColliding(false);
            }
        }
        g.dispose();

    }

    public void start() {
        if (InputAdapter == null) {
            InputAdapter = new TAdapter();
        }
        if (Game.GetTotal() != 0) {
            Game.PrintAllTotal();
        }
        timer.start();

        if (getKeyListeners().length == 0) {
            addKeyListener(InputAdapter);
        } else {
            System.err.println("com.FuturePixels.Utils.ILevel.start() their was a problem disposing of the KeyListener");
        }
        if (getMouseListeners().length == 0) {
            addMouseListener(InputAdapter);
        } else {
            System.err.println("com.FuturePixels.Utils.ILevel.start() their was a problem disposing of the MouseListeners");
        }
        if (getMouseMotionListeners().length == 0) {
            addMouseMotionListener(InputAdapter);
        } else {
            System.err.println("com.FuturePixels.Utils.ILevel.start() their was a problem disposing of the MouseMotionListeners");
        }
        Game.Add(this.getClass());
    }

    public void stop() throws Exception {
        if (InputAdapter == null) {
            throw new Exception("tried to stop it before it even ran");
        } else {
            timer.stop();

        }

        if (getKeyListeners().length != 0) {
            removeKeyListener(InputAdapter);
        }
        if (getMouseListeners().length != 0) {
            removeMouseListener(InputAdapter);
        }
        if (getMouseMotionListeners().length != 0) {
            removeMouseMotionListener(InputAdapter);
        }
        Game.Remove(this.getClass());
    }

    public synchronized void play(String soundResource) {
        System.out.println("com.FuturePixels.MainClasses.ILevel.play()");
        MusicUtils.play(soundResource);
    }

    protected synchronized BufferedImage GetSprite(String URI) {
        BufferedImage g = imageUtils.T.GetImage(URI);
        return g;
    }

    //this is using java swing native functionality but their are exameples or raycasting  in use in the player.onCollison function 
    public void checkCollionsions() {
        if (gameObjs.size() <= 1) {
            return;
        }
        for (int i = 0; i < gameObjs.size(); i++) {
            IDrawable a = gameObjs.get(i);
            for (int j = 0; j < gameObjs.size(); j++) {
                IDrawable b = gameObjs.get(j);
                if (i == j || !(a.isEnabled() && b.isEnabled()) || !(a.IsCollidable() && b.IsCollidable())) {
                    continue;
                }
                if (a != b) {
                    if (a.CheckCollions(b)) {
                        a.onCollison(b);
                        a.setIsColliding(true);
                        b.setIsColliding(true);
                    }
                    if (b.CheckCollions(a)) {
                        b.onCollison(a);
                        a.setIsColliding(true);
                        b.setIsColliding(true);
                    }
                }
            }

        }

//        thePlayer.checkCollision(theTreasure);
    }

    public boolean DebugCollisons = false;

    private class TAdapter extends InputAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (get() != Game.GetLevel()) {
                return;
            }

            keyRelease(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (get() != Game.GetLevel()) {
                return;
            }
            if (e.getKeyCode() == 120) {
                DebugCollisons = !DebugCollisons;
            }
            if (e.getKeyCode() == KeyEvent.VK_F10) {
                Game.SetLevelActive(new MainMenu());
            }
            if (e.getKeyCode() == 10 && e.isAltDown()) {
                Game.FullScreen();
            } else if (e.getKeyCode() == 10 && !e.isAltDown()) {
                Game.toggleCursor();
            }
            LastKeyPress = e;
            keyPress(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            IsClicking = true;
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
                    isactiveOne = isactiveOne != MouseButtonPressed.get(a) || isactiveOne;

                }

                IsClicking = isactiveOne;
            } else {
                IsClicking = false;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            start();
            IsInside = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            stop();
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

    public void dispose() throws Exception {
        stop();
        ArrayList<IDrawable> drawable = gameObjs;
        gameObjs = new ArrayList<IDrawable>();
        drawable.forEach((a) -> {
            a.dispose();
        });
        for (int i = drawable.size() - 1; i > 0; i--) {
            drawable.remove(i);
        }

        resetParams();
        Transform.setOffsetTranslation(Vector.Zero());
    }

    public abstract void init();

    public abstract void Update(ActionEvent ae);

    public abstract void Draw(Graphics2D g);

    public abstract void keyPress(KeyEvent e);

    public abstract void keyRelease(KeyEvent e);

}
