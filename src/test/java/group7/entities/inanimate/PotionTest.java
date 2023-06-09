package group7.entities.inanimate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import group7.entities.animate.Player;
import group7.levels.Pathfinding;


public class PotionTest {

    @Test
    public void spawnPotion() {
        Potion potion = new Potion(0, 0, 0);

        assertTrue(potion.getPosX() == 0.5);
        assertTrue(potion.getPosY() == 0.5);
    }

    @Test
    public void potionDisappearsAfterInteraction() {
        Potion potion = new Potion(0, 0, 0);
        Pathfinding pathfinding = new Pathfinding(1, 1);
        Player player = new Player(0, 0, pathfinding, 0);

        potion.onInteraction(player);
        potion.update();
        assertTrue(!potion.isInteractable());
    }

    @Test
    public void potionIncreasesPlayerHealth() {
        Potion potion = new Potion(0, 0, Potion.GREEN_HEALTH_POTION);
        Pathfinding pathfinding = new Pathfinding(1, 1);
        Player player = new Player(0, 0, pathfinding, 0);

        // player should not go over max health
        int originalHealth = player.getHealth();
        potion.onInteraction(player);
        assertTrue(player.getHealth() == originalHealth);

        // player should heal when health is less than max health
        player.takeDamage(player.getHealth() + 1);
        potion.onInteraction(player);
        
        assertTrue(player.getHealth() < originalHealth);
    }

    @Test
    public void potionIncreasesPlayerSpeed() {
        Potion potion = new Potion(0, 0, Potion.PURPLE_SPEED_POTION);
        Pathfinding pathfinding = new Pathfinding(1, 1);
        Player player = new Player(0, 0, pathfinding, 0);

        float originalSpeed = player.getSpeed();

        potion.onInteraction(player);
        assertTrue(player.getSpeed() > originalSpeed);
    }

    @Test
    public void callUpdateTest() {
        try {
            Potion potion = new Potion(0, 0, Potion.PURPLE_SPEED_POTION);
            for (int i = 0; i < 100; i++) {
                potion.update();
            }

            potion = new Potion(0, 0, Potion.GREEN_HEALTH_POTION);
            for (int i = 0; i < 100; i++) {
                potion.update();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
        
    }
}
