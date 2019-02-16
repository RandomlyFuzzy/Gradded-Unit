/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FuturePixels.Utils;

/**
 *
 * @author RandomlyFuzzy
 */
public class UtilManager {
    
    
    public UtilManager(){
        new MusicUtils();
        new imageUtils();
        new CalcUtils();
    }
    
    
    static void FindUseClass(int depth){
        try{
            throw new Exception();
        }catch(Exception e){
            System.err.println("FindUseClass() "+e.getStackTrace()[depth]);
        }
    }
    
}
