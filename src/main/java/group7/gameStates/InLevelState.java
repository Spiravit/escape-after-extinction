package group7.gameStates;

import group7.Game;
import group7.entities.Player;
import group7.levels.LevelManager;
import group7.utils.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


// This class is for when the game is in Playing state
// it initializes a player, enemies, level
// render and update them
// the class is extending the abstract State class, a super class for all states
public class InLevelState extends State {
        public Player player;
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
            player.render(g);
        }


        @Override
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
        @Override
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
