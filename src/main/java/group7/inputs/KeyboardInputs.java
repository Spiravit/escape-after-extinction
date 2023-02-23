package group7.inputs;

import group7.Graphics.GraphicsPanel;
import group7.utils.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    GraphicsPanel gamePanel;
    public KeyboardInputs(GraphicsPanel gamePanel){
        this.gamePanel=gamePanel;
    }
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            //Right arrow key code
            gamePanel.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            //gamePanel arrow key code
            gamePanel.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            //Up arrow key code
            gamePanel.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            //Down arrow key code
            gamePanel.setDirection(Direction.DOWN);
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            //Right arrow key code
            gamePanel.removeDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            //gamePanel arrow key code
            gamePanel.removeDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            //Up arrow key code
            gamePanel.removeDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            //Down arrow key code
            gamePanel.removeDirection(Direction.DOWN);
        }
    }
}
