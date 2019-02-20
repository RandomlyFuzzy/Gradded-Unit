/*
 * Copyright (C) 2019 RandomlyFuzzy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.FuturePixels.Drawables.Levels;

import com.FuturePixels.Entry.Game;
import com.FuturePixels.MainClasses.IDrawable;
import com.FuturePixels.MainClasses.Vector;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 *
 * @author RandomlyFuzzy
 */
public class ScrollingBackground extends IDrawable {

    private BufferedImage Scrollingimg;
    private String imgURI = "/images/cookie.png";

    public ScrollingBackground() {
        super();
    }

    public ScrollingBackground(String imageURI) {
        super();
    }

    @Override
    public void init() {
        setIsCollidable(false);
        Scrollingimg = GetSprite(imgURI);
    }

    @Override
    public void doMove() {
        Offset(new Vector(32, 32));
//        setScale(new Vector(Game.g.getWindowWidth()/getSpriteWidth(),Game.g.getWindowHeight()/getSpriteHeight()));
//        SetImage(imgURI, Scrollingimg);
    }

    public void Offset(Vector v) {
        v.setX(v.getX() % getSpriteWidth());
        v.setY(v.getY() % getSpriteHeight());
        Raster raster = Scrollingimg.getData();
        WritableRaster NEW = Raster.createWritableRaster(raster.getSampleModel(), new Point(raster.getSampleModelTranslateX(), raster.getSampleModelTranslateY()));

        Raster one, two, three, four;

//        NEW.setDataElements(0, 0, Scrollingimg.getData(new Rectangle(0,0,(int)v.getX(),(int)v.getY())));
        one = Scrollingimg.getData(new Rectangle((int) getSpriteWidth() - (int) v.getX()-1, (int) getSpriteHeight() - (int) v.getY()-1, (int) v.getX(), (int) v.getY()));
        NEW.setDataElements(( (int) getSpriteWidth() - (int) v.getX()),((int) getSpriteHeight() - (int) v.getY()), one);
//        NEW.setDataElements(0, 0, one);
//        NEW.setDataElements(0, 0, one);
//        NEW.setDataElements(0, 0, one);

        Scrollingimg.setData(NEW);
        NEW = null;
    }

    @Override
    public void Update(Graphics2D g) {
//        DrawLastLoadedImage(g);
        g.drawImage(Scrollingimg, -getSpriteWidth() / 2, -getSpriteHeight() / 2, getSpriteWidth(), getSpriteHeight(), null);
    }

    @Override
    public void onCollison(IDrawable im) {
    }
}
