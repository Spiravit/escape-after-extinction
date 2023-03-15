package group7.Graphics;

import group7.gameStates.State;
import group7.inputs.*;

import javax.swing.*;
import java.awt.*;

/** 
* The class GraphicPanel extends @see <a href=”https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html”>label</a>.
* The class generates the graphics that will appear in the game window.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class GraphicsPanel extends JPanel {
    public static final int panelWidth = 1280;
    public static final int panelHeight = 720;
    private State gameCurrentStates;
    KeyboardInputs keyboardInputs;
    MouseInputs mouseInputs;

    /**
     * Constructor: Initializes JPanel to suit the game needs.
     * creates a panel that can take inputs and outputs something to the user.
     * @param gameCurrentStates 
     */
    public GraphicsPanel(State gameCurrentStates){
        keyboardInputs = new KeyboardInputs(gameCurrentStates);
        mouseInputs = new MouseInputs(gameCurrentStates);
        super.addKeyListener(keyboardInputs);
        super.addMouseListener(mouseInputs);
        this.gameCurrentStates = gameCurrentStates;
        changePanelSize();
    }

    /**
     * Changes the size of the panel that is displayed.
     */
    private void changePanelSize() {
        Dimension size = new Dimension(panelWidth, panelHeight);
        setPreferredSize(size);
    }

    /** 
     * If the state of game is changed in game class, we need this following method to also update the gameCurrentStates
     * in GraphicPanel so that we will render the current state of game.
     * @param gameCurrentStates {@link State}
     */
    public void changeGameStates(State gameCurrentStates) {
        this.gameCurrentStates = gameCurrentStates;
        // Also have to change the gameCurrentStates in KeyboardInput
        // so it calls the methods corresponding to current state
        keyboardInputs.setKeyboardGameStates(gameCurrentStates);
        // Also changing for mouseInput due to the same reason
        mouseInputs.setMouseGameStates(gameCurrentStates);
    }

    /**
     * Returns this current state of the game.
     * @return State gameCurrentStates {@link State}
     */
    public State getGameCurrentStates() {
        return this.gameCurrentStates;
    }

    /**
     * Render the graphics for the corresponding current running state of the game {@link gameStates}.
     * @param g {@link Graphics}
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameCurrentStates.render(g);
    }
}
