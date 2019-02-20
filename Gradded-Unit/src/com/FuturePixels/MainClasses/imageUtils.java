/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.MainClasses;

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
        return GetImage(URI, false);
    }

    private BufferedImage GetImage(String URI, boolean isDefault) {

        URI = "" + URI;
        BufferedImage g = null;
        if (Images.containsKey(URI)) {
            try {
                throw new Exception();
            } catch (Exception e) {
//                System.out.println("Image loaded " + URI + " in " + e.getStackTrace()[isDefault ? 2 : 3].getClassName());
            }
            return Images.get(URI);
        } else {
            try {
                g = ImageIO.read(getClass().getResourceAsStream(URI));
                Images.put(URI, g);
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Image loaded " + URI + " in " + e.getStackTrace()[isDefault ? 2 : 3].getClassName());
                    return g;
                }
            } catch (Exception e) {
                System.err.println("error loading " + URI + " in " + e.getStackTrace()[isDefault ? 3 : 4].getClassName());
            }
        }

        return GetImage("/images/defualt.png", true);
    }

    public void setImage(String Name,BufferedImage img){
        Images.put(Name, img);
    }
    
}
