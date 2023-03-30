package group7.levelsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import group7.helperClasses.Direction;
import group7.levels.Pathfinding;

public class PathfindingTest {
    @Test
    public void checkisValid() {
        Pathfinding pathfinding = new Pathfinding(1, 2);
        pathfinding.set(0, 0, true);

        // tile manually set to valid, so it should return true
        assertEquals(pathfinding.isValidTile(0, 0), true);

        // default tile state is invalid
        assertEquals(pathfinding.isValidTile(0, 1), false);

        pathfinding.set(0, 1, false);

        // manually setting tile to false should still return false
        assertEquals(pathfinding.isValidTile(0, 1), false);
    }

    @Test
    public void checkIsValidBounds() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        assertFalse(pathfinding.isValidTile(1, 0));
        assertFalse(pathfinding.isValidTile(0, 1));
        assertFalse(pathfinding.isValidTile(-1, 0));
        assertFalse(pathfinding.isValidTile(0, -1));
    }

    @Test
    public void settingOutOfBoundsTileAsValid() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        
        pathfinding.set(1, 0, true);
        assertFalse(pathfinding.isValidTile(1, 0));
        
        pathfinding.set(-1, 0, true);
        assertFalse(pathfinding.isValidTile(-1, 0));

        pathfinding.set(0, 1, true); 
        assertFalse(pathfinding.isValidTile(0, 1));

        pathfinding.set(0, -1, true);
        assertFalse(pathfinding.isValidTile(0, -1));
    }

    @Test 
    public void testSetPlayer() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        
        pathfinding.setPlayer(1, 0);
        assertFalse(pathfinding.isValidTile(1, 0));
        
        pathfinding.setPlayer(-1, 0);
        assertFalse(pathfinding.isValidTile(-1, 0));

        pathfinding.setPlayer(0, 1); 
        assertFalse(pathfinding.isValidTile(0, 1));

        pathfinding.setPlayer(0, -1);
        assertFalse(pathfinding.isValidTile(0, -1));
    }

    @Test
    public void testFindPlayer() {
        Pathfinding pathfinding = new Pathfinding(2, 2);
        pathfinding.set(0, 0, true);
        pathfinding.set(0, 1, true);
        pathfinding.set(1, 0, true);
        pathfinding.set(1, 1, true);
        pathfinding.setPlayer(0, 0);

        assertEquals(Direction.NONE, pathfinding.findPlayer(1, 1, 0));

        assertEquals(Direction.NONE, pathfinding.findPlayer(1, 1, 1));

        // set player to be in the top left corner, so it should return either UP or LEFT
        Direction direction = pathfinding.findPlayer(1, 1, 2);
        assertTrue(direction != Direction.NONE);
        assertTrue(direction != Direction.DOWN);
        assertTrue(direction != Direction.RIGHT);

        direction = pathfinding.findPlayer(1, 1, 3);
        assertTrue(direction != Direction.NONE);
        assertTrue(direction != Direction.DOWN);
        assertTrue(direction != Direction.RIGHT);
    }

    
    @Test
    public void testFindPlayerOutOfBounds() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        
        pathfinding.setPlayer(-1, 0);
        assertEquals(Direction.NONE, pathfinding.findPlayer(0, 0, 4));

        pathfinding.setPlayer(0, -1);
        assertEquals(Direction.NONE, pathfinding.findPlayer(0, 0, 4));

        pathfinding.setPlayer(1, 0);
        assertEquals(Direction.NONE, pathfinding.findPlayer(0, 0, 4));

        pathfinding.setPlayer(0, 1);
        assertEquals(Direction.NONE, pathfinding.findPlayer(0, 0, 4));
    }
}