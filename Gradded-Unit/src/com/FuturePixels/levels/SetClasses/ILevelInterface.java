/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.levels.SetClasses;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

/**
 *
 * @author RandomlyFuzzy
 */
public interface ILevelInterface {
    public void actionPerformed(ActionEvent ae);
    public void paintComponent(Graphics g);
    public void start();
    public void stop();
}
