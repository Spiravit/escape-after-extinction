package group7.levels;

import group7.entities.animate.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void levelStateLostHealthTest() {
        // Testing levelState method in Level class
        // when player health is less or equal to 0
        int dinoNumber = 3;
        Level level = new Level3(3);
        Player player = level.getPlayer();
        player.takeDamage(100);
        assertEquals(level.checkLevelState(),LevelState.LOST);
    }

    @Test
    public void levelStateHealthyPlayerTest(){
        // Testing levelState method in Level class
        // when player health is still above 100
        int dinoNumber = 3;
        Level level = new Level3(3);
        Player player = level.getPlayer();
        assertEquals(level.checkLevelState(),LevelState.PLAYING);
        player.takeDamage(1);
        assertEquals(level.checkLevelState(),LevelState.PLAYING);
        player.takeDamage(98);
        // In total health of player - 99 where 99 is a off point
        assertEquals(level.checkLevelState(),LevelState.PLAYING);
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
}