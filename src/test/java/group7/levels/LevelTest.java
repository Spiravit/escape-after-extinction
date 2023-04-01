package group7.levels;

import group7.entities.animate.Player;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class LevelTest {

    Level level;

    @BeforeEach
    void setup() {
        int dinoNumber = 3;
        level = new Level3(dinoNumber);
    }

    @Test
    public void levelStateLostHealthTest() {
        // Testing levelState method in Level class
        // when player health is less or equal to 0
        Player player = level.getPlayer();
        player.takeDamage(100);
        assertEquals(level.checkLevelState(),LevelState.LOST);
    }

    @Test
    public void levelStateHealthyPlayerTest(){
        // Testing levelState method in Level class
        // when player health is still above 100
        Player player = level.getPlayer();
        assertEquals(level.checkLevelState(),LevelState.PLAYING);
        player.takeDamage(1);
        assertEquals(level.checkLevelState(),LevelState.PLAYING);
        player.takeDamage(98);
        // In total health of player - 99 where 99 is a off point
        assertEquals(level.checkLevelState(),LevelState.PLAYING);
    }

    @Test 
    public void setLevelDataTestNonValidLevel() {
        // Checking setLevelData method to set up a level and its sprites but
        // giving non valid number like 4 , since we don't have level 4
        // this method should throw exception
   
        // since we don't have a level 5, setLevelData should throw a NullPointer Exception
        try {
            System.out.println("test setting invalid level 5");
            level.setLevelData(5);
            fail();
        } catch (NullPointerException ex) {
            assertTrue(ex instanceof NullPointerException);
        } 
    }
}