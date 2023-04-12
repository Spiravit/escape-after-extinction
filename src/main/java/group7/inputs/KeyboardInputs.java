package group7.inputs;

import group7.gameStates.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/** 
* The class KeyboardInputs implements @see <a href=”https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html”>label</a>.
* Allows the usage of keys pressed on keyboard to be taken as an input for the game. 
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class KeyboardInputs implements KeyListener {
    private State gameCurrentStates;

    /**
     * Constructor: Initializes KeyListener
     * @param gameCurrentStates
     */
    public KeyboardInputs(State gameCurrentStates) {
        this.gameCurrentStates = gameCurrentStates;
    }

    /**
     * Sets the key functions allowed at each state of the game.
     * @param gameCurrentStates
     */
    public void setKeyboardGameStates(State gameCurrentStates) {
        this.gameCurrentStates = gameCurrentStates;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not currently used    
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // call the keyPressed method of current state of game
        gameCurrentStates.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // call the keyReleased method of current state of game
        gameCurrentStates.keyReleased(e);
    }
}
