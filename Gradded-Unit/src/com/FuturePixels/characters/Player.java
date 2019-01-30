/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.characters;

import com.FuturePixels.game.Game;
import com.FuturePixels.game.Vector;
import com.FuturePixels.levels.LeaderBoard;
import com.FuturePixels.levels.SetClasses.ILevel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.imageio.ImageIO;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author Liam Woolley 1748910
 */
public class Player {

    //Vectors to represent the character's current position 
    //and movement from the current position
    private Vector position;
    private Vector veclocity;
    private Vector displacement;
    //This image represents the character
    private BufferedImage[] sprites = new BufferedImage[7];
    private float ind = 0;
    private boolean Stop = true;
    //This variable stores the width of the image  
    private int spriteWidth, Scale = 1;

    //This variable stores the height of the image 
    private int spriteHeight;
    //A score value – this could be a health or other appropriate value

    private long score;
    private long now;

    ILevel from;

    public Player(ILevel From) {
        from = From;
        position = new Vector(100, 100);
        veclocity = new Vector(100, 100);
        displacement = new Vector(0, 0);
        score = 0;
        init();

    }

    private void init() {
        try {
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = ImageIO.read(getClass().getResource("/Images/Player/sprite_" + i + ".png"));
                spriteWidth = sprites[i].getWidth();
                spriteHeight = sprites[i].getHeight();
            }
            now = System.nanoTime() / 1000000000;
        } catch (Exception ex) {
            System.err.println("Error loading player sprite");
        }

    }

    public void move(int dir) {
        boolean one = true, two = true;
        if ((dir & 1) == 1) {
            displacement.setY(-1);
        } else if ((dir & 2) == 2) {
            displacement.setY(1);
        } else {
            displacement.setY(0);
            one = false;
        }
        if ((dir & 4) == 4) {
            Scale = -1;
            displacement.setX(-1);
        } else if ((dir & 8) == 8) {
            Scale = 1;
            displacement.setX(1);
        } else {
            displacement.setX(0);
            two = false;
        }
        Stop = !one && !two;
    }

    public void stop() {
        Stop = true;
        displacement.setX(0);
        displacement.setY(0);
    }

    public void draw(Graphics g) {

//        addGravity();
        ind += Stop ? -ind : 0.1f;
        ind = ind % sprites.length;
        g.drawImage(getSprite(), (int) position.getX() - (spriteWidth / 2 * (int) Scale), (int) position.getY(), spriteWidth * (int) Scale, spriteHeight, null);
        g.drawString("Score:" + score, 0, 20);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void addGravity() {
        //check Colisions
        if (this.position.getY() > Game.g.getWindowHeight() / 2) {
            this.displacement = new Vector(0, 0);
            return;
        }

        System.out.println("com.FuturePixels.characters.Player.addGravity() " + Game.g.getDelta());
        this.displacement.add(new Vector(0, 9.81f * (float) Game.g.getDelta()));
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public BufferedImage getSprite() {
        return sprites[(int) Math.floor(ind)];
    }

    public void doMove() {
        position.add(veclocity.add(displacement));
        veclocity.mult(new Vector(0.44f, 0.44f));
    }

    public Rectangle getBounds() {
        Rectangle objectRect = new Rectangle((int) position.getX(), (int) position.getY(), spriteWidth, spriteHeight);
        return objectRect;
    }

    public boolean checkCollision(Cookie t) {
        if (t.getBounds().intersects(getBounds())) {
            if (t.IsVisible() == true) {
                score += t.getScore();
                t.setVisible(false);
                from.play(getClass().getResourceAsStream("/sounds/music.wav"));
            }
            LeaderBoard.AddTime(System.nanoTime());
            return true;
        } else {
            return false;
        }
    }
}
