
import com.FuturePixels.Engine.AbstractClasses.ILevel;
import com.FuturePixels.Engine.Entry.Game;
import com.FuturePixels.Engine.Utils.LevelLoader;
import com.FuturePixels.levels.Menus.*;
import com.FuturePixels.levels.OtherLevels.*;
import com.FuturePixels.levels.SoloLevels.*;
import java.util.ArrayList;

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

    public static void main(String args[]) {
        Game.init(new MainMenu());
        ArrayList<ILevel> Levels = new ArrayList<ILevel>();
        Levels.add(new MainMenu());
        Levels.add(new Settings());
        Levels.add(new LeaderBoard());
        Levels.add(new Level1Solo());
        Levels.add(new Level2Solo());
        Levels.add(new Level3Solo());
        Levels.add(new LevelSelect());

        com.FuturePixels.Engine.Utils.LevelLoader.setLEVELS(Levels);

    }
}
