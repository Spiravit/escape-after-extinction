package group7.entitiesTest.animateTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import group7.entities.animate.Enemy;
import group7.entities.animate.Player;
import group7.entities.inanimate.Egg;
import group7.entities.inanimate.Key;
import group7.entities.inanimate.Trap;
import group7.helperClasses.Direction;
import group7.levels.Pathfinding;


public class PlayerTest  {
    @Test
    public void spawnPlayer() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Player player = new Player(0, 0, pathfinding, 0);

        // Player x and y should be at the center of the tile
        assertEquals(0.5, player.getPosX(), 0.1);
        assertEquals(0.5, player.getPosY(), 0.1);
    }

    @Test
    public void playerInteractsWithTrap() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Player player = new Player(0, 0, pathfinding, 0);
        Trap trap = new Trap(0, 0);

        int originalHealth = player.getHealth();

        // Player should have less health
        trap.onInteraction(player);
        assertEquals(true, player.getHealth() < originalHealth);
    }

    @Test
    public void movePlayer() {
        // player should not move if canMove is false
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        
        

        Player player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(false);
        player.setDirection(Direction.DOWN);
        player.update();

        // Player should not move down
        assertTrue(player.getPosY() == 0.5);

        player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(false);
        player.setDirection(Direction.UP);
        player.update();

        // Player should not move up
        assertTrue(player.getPosY() == 0.5);

        player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(false);
        player.setDirection(Direction.LEFT);
        player.update();

        // Player should not move left
        assertTrue(player.getPosX() == 0.5);

        player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(false);
        player.setDirection(Direction.RIGHT);
        player.update();

        // Player should not move right
        assertTrue(player.getPosX() == 0.5);

        // player should move if canMove is true
        // no idea why this is failing
        
        player = new Player(0, 0, pathfinding, 0);
        player.setDirection(Direction.DOWN);
        player.setCanMove(true);
        player.update();

        // Player should move down
        assertTrue(player.getPosY() > 0.5);

        player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(true);
        player.update();
        player.setDirection(Direction.UP);
        player.update();

        // Player should move up
        assertTrue(player.getPosY() < 0.5);

        player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(true);
        player.setDirection(Direction.LEFT);
        player.update();

        // Player should move left
        assertTrue(player.getPosX() < 0.5);

        player = new Player(0, 0, pathfinding, 0);
        player.setCanMove(true);
        player.setDirection(Direction.RIGHT);
        player.update();

        // Player should move right
        assertTrue(player.getPosX() > 0.5);
    }
}