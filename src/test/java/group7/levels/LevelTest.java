package group7.levels;

import group7.entities.animate.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void checkLevelState() {
        int dinoNumber = 3;
        Level level = new Level3(3);
        Player player = level.getPlayer();
        player.takeDamage(100);
        level.checkLevelState()
    }

    @Test(expected = NullPointerException.class)
    public void setLevelDataTestNonValidLevel(){
        // Checking setLevelData method to set up a level and its sprites but
        // giving non valid number like 4 , since we don't have level 4
        // this method should throw exception
        int dinoNumber = 3;
        Level level = new Level3(3);
        // since we don't have a level 5, setLevelData should throw a NullPointer Exception
        level.setLevelData(5);
    }

    /*
    @Test(expected = NullPointerException.class)
    public void nonValidDinoNumberInLevel(){
        int nonValidDinoNumberOFFPoint = 6; // we only have [1,5] as dino numbers
        // Since we don't have dino number as 6 , making a level with this dino number
        // should throw a null pointer exception
        Level level = new Level2(nonValidDinoNumberOFFPoint);
    }*/

    @Test
    public void update() {
    }
}