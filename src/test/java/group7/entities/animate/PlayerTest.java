package group7.entities.animate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import group7.helperClasses.Direction;
import group7.levels.Pathfinding;


public class PlayerTest  {
    Player player;
    Pathfinding pathfinding;

    @BeforeEach
    public void setup() {
        pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        player = new Player(0, 0, pathfinding, 0);
    }
    
    @Test
    public void spawnPlayer() {
        // Player x and y should be at the center of the tile
        assertEquals(0.5, player.getPosX(), 0.1);
        assertEquals(0.5, player.getPosY(), 0.1);
    }

    @Test
    public void movePlayer() {
        // player should not move if canMove is false
        player.setCanMove(false);
        player.setDirection(Direction.DOWN);
        player.update();

        // Player should not move down
        assertTrue(player.getPosY() == 0.5);

        setup();
        player.setCanMove(false);
        player.setDirection(Direction.UP);
        player.update();

        // Player should not move up
        assertTrue(player.getPosY() == 0.5);

        setup();
        player.setCanMove(false);
        player.setDirection(Direction.LEFT);
        player.update();

        // Player should not move left
        assertTrue(player.getPosX() == 0.5);

        setup();
        player.setCanMove(false);
        player.setDirection(Direction.RIGHT);
        player.update();

        // Player should not move right
        assertTrue(player.getPosX() == 0.5);

        // player should move if canMove is true
        setup();
        player.setDirection(Direction.DOWN);
        player.setCanMove(true);
        player.update();

        // Player should move down
        assertTrue(player.getPosY() > 0.5);

        setup();
        player.setCanMove(true);
        player.update();
        player.setDirection(Direction.UP);
        player.update();

        // Player should move up
        assertTrue(player.getPosY() < 0.5);

        setup();
        player.setCanMove(true);
        player.setDirection(Direction.LEFT);
        player.update();

        // Player should move left
        assertTrue(player.getPosX() < 0.5);

        setup();
        player.setCanMove(true);
        player.setDirection(Direction.RIGHT);
        player.update();

        // Player should move right
        assertTrue(player.getPosX() > 0.5);
    }

    @Test
    public void isMovingTest() {
        Player player = new Player(0, 0, new Pathfinding(1, 1), 0);
        player.setDirection(Direction.DOWN);

        // Player should be moving
        assertTrue(player.isMoving());

        player.setDirection(Direction.UP);

        // Player should not be moving
        assertFalse(player.isMoving());

        player.setDirection(Direction.LEFT);

        // Player should be moving
        assertTrue(player.isMoving());
    }

    @Test
    public void directionTest() {
        player.setDirection(Direction.DOWN);

        // Player should be moving down
        assertTrue(player.getDirectionState(Direction.DOWN));

        player.setDirection(Direction.UP);

        // Player should be moving up
        assertTrue(player.getDirectionState(Direction.UP));

        player.setDirection(Direction.LEFT);

        // Player should be moving left
        assertTrue(player.getDirectionState(Direction.LEFT));

        player.setDirection(Direction.RIGHT);

        // Player should be moving right
        assertTrue(player.getDirectionState(Direction.RIGHT));
    }

}