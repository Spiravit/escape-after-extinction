package group7.gameStates;

import group7.Game;
import group7.entities.Player;
import group7.levels.LevelManager;
import group7.utils.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class InLevelState extends State {
        public Player player; // TODO: this will be removed !!
        private LevelManager levelManager;
        public InLevelState(Game game) {
            super(game);
            this.levelManager = new LevelManager();
            this.levelManager.loadLevel(1);
            this.player = new Player(1, 1, this.levelManager.getLevelData());
        }
        public void update() {
            player.update();
        }
        public void render(Graphics g) {
            levelManager.render(g);
            //System.out.println("painting 3");
            player.render(g);
            //System.out.println("painting done");
        }
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                //Right arrow key code
                player.setDirection(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                //gamePanel arrow key code
                player.setDirection(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
                //Up arrow key code
                player.setDirection(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                //Down arrow key code
                player.setDirection(Direction.DOWN);
            }
        }
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                //Right arrow key code
                player.removeDirection(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                //gamePanel arrow key code
                player.removeDirection(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
                //Up arrow key code
                player.removeDirection(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                //Down arrow key code
                player.removeDirection(Direction.DOWN);
            }
        }
        public Player getPlayer() {
            return player;
        }

}
