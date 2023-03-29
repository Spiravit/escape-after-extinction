package group7.entitiesTest.inanimateTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import group7.entities.animate.Player;
import group7.entities.inanimate.Egg;
import group7.levels.Pathfinding;

public class EggTest {
    @Test
    public void spawnEgg() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Egg egg = new Egg(0, 0, 100);

        // Player x and y should be at the center of the tile
        assertEquals(0.5, egg.getPosX(), 0.1);
        assertEquals(0.5, egg.getPosY(), 0.1);
    }

    @Test
    public void timeoutEgg() {
        Egg egg = new Egg(0, 0, 100);

        // Update egg
        egg.update();

        // Egg should not be timed out
        assertTrue(egg.isInteractable() && egg.isVisible());

        egg = new Egg(0, 0, 0);

        // Update egg
        egg.update();

        // Egg should be timed out
        assertTrue(!egg.isInteractable() && !egg.isVisible());
    }

    @Test
    public void playerCollectsEgg() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Player player = new Player(0, 0, pathfinding, 0);
        Egg egg = new Egg(0, 0, 100);

        // Player should not have any eggs
        assertEquals(0, player.getEggsCollected());

        // Player should collect the egg
        egg.onInteraction(player);

        // Player should have 1 egg
        assertEquals(1, player.getEggsCollected());
    }
}
