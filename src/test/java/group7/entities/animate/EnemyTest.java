package group7.entities.animate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import group7.helperClasses.Direction;
import group7.levels.Pathfinding;


public class EnemyTest {
    @Test
    public void spawnEnemy() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Enemy enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);

        // Player x and y should be at the center of the tile
        assertEquals(0.5, enemy.getPosX(), 0.1);
        assertEquals(0.5, enemy.getPosY(), 0.1);
    }

    @Test
    public void enemyDetectsPlayer() {
        Pathfinding pathfinding = new Pathfinding(5, 5);
        pathfinding.set(0, 0, true);
        pathfinding.set(0, 1, true);
        pathfinding.set(0, 2, true);
        pathfinding.set(0, 3, true);
        pathfinding.set(0, 4, true);

        Enemy enemy = new Enemy(0, 0, pathfinding, 2, 3, 0);
        Player player = new Player(0, 3, pathfinding, 0);

        // Enemy should not detect player because of default state
        assertEquals(false, enemy.getTrackingPlayer());

        // update player and enemy
        player.update();
        enemy.update();

        // Enemy should not detect player
        assertEquals(false, enemy.getTrackingPlayer());

        // move player closer to enemy
        player = new Player(0, 2, pathfinding, 0);

        // update player and enemy
        player.update();
        enemy.update();

        // Enemy should detect player
        assertEquals(true, enemy.getTrackingPlayer());

        // move player away from enemy
        player = new Player(0, 3, pathfinding, 0);

        // update player and enemy
        player.update();
        enemy.update();

        // Enemy should detect player
        assertEquals(true, enemy.getTrackingPlayer());
    }

    @Test
    public void moveEnemy() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);

        Enemy enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);
        enemy.setDirection(Direction.DOWN);

        enemy.update();

        // Enemy should move down
        assertTrue(enemy.getPosY() > 0.5);

        enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);
        enemy.setDirection(Direction.UP);
        enemy.update();

        // Enemy should move up
        assertTrue(enemy.getPosY() < 0.5);


        enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);
        enemy.setDirection(Direction.LEFT);
        enemy.update();

        // Enemy should move left
        assertTrue(enemy.getPosX() < 0.5);

        enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);
        enemy.setDirection(Direction.RIGHT);
        enemy.update();

        // Enemy should move right
        assertTrue(enemy.getPosX() > 0.5);
    }

    @Test
    public void EnemyInteractsWithPlayer() {
        Pathfinding pathfinding = new Pathfinding(1, 1);
        pathfinding.set(0, 0, true);
        Player player = new Player(0, 0, pathfinding, 0);
        Enemy enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);

        // Player should not be dead
        assertEquals(true, player.getHealth() != 0);

        // Player should be dead
        enemy.onInteraction(player);
        assertEquals(true, player.getHealth() == 0);
    }

    @Test
    public void callUpdateTest() {
        try {
            // insures that enemy sprites are loaded correctly
            Pathfinding pathfinding = new Pathfinding(1, 1);
            Enemy enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);
            for (int i = 0; i < 100; i++) {
                enemy.update();
            }

            enemy.setDirection(Direction.DOWN);
            for (int i = 0; i < 100; i++) {
                enemy.update();
            }

            // plays player chasing animation
            pathfinding = new Pathfinding(2, 2);
            enemy = new Enemy(0, 0, pathfinding, 0, 0, 0);
            pathfinding.set(0, 0, true);
            pathfinding.set(0, 1, true);
            pathfinding.set(1, 0, true);
            pathfinding.set(1, 1, true);

            pathfinding.setPlayer(1, 1);

            for (int i = 0; i < 100; i++) {
                enemy.update();
            }

        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
