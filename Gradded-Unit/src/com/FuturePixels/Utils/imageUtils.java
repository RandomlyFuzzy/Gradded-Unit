/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author RandomlyFuzzy
 */
public class imageUtils {

    public static imageUtils T;

    public imageUtils() {
        T = this;
    }

    private HashMap<String, BufferedImage> Images = new HashMap<String, BufferedImage>();

    public BufferedImage GetImage(String URI) {
        if (Images.containsKey(URI)) {
            return Images.get(URI);
        } else {
            try {
                BufferedImage g = ImageIO.read(getClass().getResource(URI));

                Images.put(URI, g);
                return g;
            } catch (Exception e) {
                System.err.println("error loading " + URI + " in " + e.getStackTrace()[e.getStackTrace().length - 1].getClassName());
            }
        }
        return null;
    }

}
