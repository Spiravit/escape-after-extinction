package group7.entitiesTest.inanimateTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group7.entities.inanimate.Key;
import group7.entities.animate.Player;
import group7.levels.Pathfinding;

import static org.junit.Assert.assertEquals;

public class KeyTest {
    @Test
    public void spawnKey() {
        Key key = new Key(0, 0);

        assertTrue(key.getPosX() == 0.5);
        assertTrue(key.getPosY() == 0.5);
    }

    @Test
    public void playerCollectsKey() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Player player = new Player(0, 0, pathfinding, 0);
        Key key = new Key(0, 0);

        // Player should not have any keys
        assertEquals(0, player.getKeysCollected());

        // Player should collect the key
        key.onInteraction(player);

        // Player should have 1 key
        assertEquals(1, player.getKeysCollected());
    }
}
