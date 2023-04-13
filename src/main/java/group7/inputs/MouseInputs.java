package group7.inputs;

import group7.gameStates.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/** 
* The class KeyboardInputs implements @see <a href=”https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html”>label</a>.
* Allows the usage of mouse clicks or other mouse movements to be taken as an input for the game. 
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class MouseInputs implements MouseListener {
    private State gameCurrentStates;

    /**
     * Constructor: Initializes MouseListener
     * @param gameCurrentStates
     */
    public MouseInputs(State gameCurrentStates){
        // call the MouseInputs method of current state of game
        this.gameCurrentStates=gameCurrentStates;
    }

    /**
     * Sets the mouse functions allowed at each state of the game.
     * @param gameCurrentStates
     */
    public void setMouseGameStates(State gameCurrentStates) {
        this.gameCurrentStates = gameCurrentStates;
    }

    /**
     * Wrapper Method: calls the mouseMoved method,
     * on this current state of the game.
     * @param e
     */
    public void mouseMoved(MouseEvent e) {
        gameCurrentStates.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // call the mouseClicked method of current state of game

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // call the mousePressed method of current state of game
        gameCurrentStates.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // call the mouseReleased method of current state of game
        gameCurrentStates.mouseReleased(e);
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
