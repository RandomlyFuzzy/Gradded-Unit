
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.levels.Menus.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RandomlyFuzzy
 */
public class Entry {
    public static void main(String args[]){
        Game.init(new MainMenu());
    }
}
