package group7.entities.inanimate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import group7.entities.animate.Player;
import group7.entities.inanimate.Trap;
import group7.levels.Pathfinding;

public class TrapTest {
    @Test
    public void spawnTrap() {
        Trap trap = new Trap(0, 0);
        
        assertTrue(trap.getPosX() == 0.5);
        assertTrue(trap.getPosY() == 0.5);
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
    public void trapDisappearsAfterInteraction() {
        Trap trap = new Trap(0, 0);
        Player player = new Player(0, 0, new Pathfinding(1, 1), 0);


        trap.onInteraction(player);
        trap.update();
        assertTrue(!trap.isInteractable());
    }

    @Test
    public void callUpdateTest() {
        try {
            // original animation
            Trap trap = new Trap(0, 0);
            for (int i = 0; i < 100; i++) {
                trap.update();
            }

            Player player = new Player(0, 0, new Pathfinding(1, 1), 0);
            trap.onInteraction(player);

            // plays after interaction animation
            for (int i = 0; i < 100; i++) {
                trap.update();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
        
    }
}
