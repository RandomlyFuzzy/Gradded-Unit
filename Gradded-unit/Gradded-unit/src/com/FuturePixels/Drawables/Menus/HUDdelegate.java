/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Drawables.Menus;

/**
 *
 * @author Liam Woolley 1748910
 */
public abstract class HUDdelegate {

    
    //i had problems getting generics working and inheritence doesnt work with button object(makes everything defored) so this is the next best thing I could think of

    /**
     *
     * @param b
     */
    public void OnClick (BlackoutButton b){}

    /**
     *
     * @param b
     */
    public void OnClick (DropDownButton b){}

    /**
     *
     * @param b
     */
    public void OnClick (Button b){}

    /**
     *
     * @param b
     * @param Value
     */
    public void OnChange(Slider b,float Value){}

}
