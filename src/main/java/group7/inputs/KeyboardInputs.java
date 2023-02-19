package group7.inputs;

import group7.entities.Entity;
import group7.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
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
            gamePanel.changePositionX(+5);
            System.out.println("right key pressed ");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            //gamePanel arrow key code
            gamePanel.changePositionX(-5);
            System.out.println("left key pressed ");
        } else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            //Up arrow key code
            System.out.println("up key pressed ");
            gamePanel.changePositionY(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            //Down arrow key code
            System.out.println("down key pressed ");
            gamePanel.changePositionY(5);
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

    }
}
