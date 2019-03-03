/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

/**
 *
 * @author RandomlyFuzzy
 */
public abstract class HUDdelegate {

    
    //i had problems getting generics working and inheritence doesnt work with button object(makes everything defored) so this is the next best thing I could think of
    public void OnClick (BlackoutButton b){}
    public void OnClick (DropDownButton b){}
    public void OnClick (Button b){}
    public void OnChange(Slider b,float Value){}

}
