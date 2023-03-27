package group7.entitiesTest.inanimateTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
    public void trapDisappearsAfterInteraction() {
        Trap trap = new Trap(0, 0);
        Player player = new Player(0, 0, new Pathfinding(1, 1), 0);


        trap.onInteraction(player);
        trap.update();
        assertTrue(!trap.isInteractable());
    }
}
