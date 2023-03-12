package group7.Graphics;

import group7.gameStates.State;
import group7.inputs.*;

import javax.swing.*;

import java.awt.*;

/**
 * is responsible for ???
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GraphicsPanel extends JPanel {
    public static final int panelWidth = 1280;
    public static final int panelHeight = 720;
    private State gameCurrentStates;
    KeyboardInputs keyboardInputs;
    MouseInputs mouseInputs;
    public GraphicsPanel(State gameCurrentStates){
        keyboardInputs = new KeyboardInputs(gameCurrentStates);
        mouseInputs = new MouseInputs(gameCurrentStates);
        super.addKeyListener(keyboardInputs);
        super.addMouseListener(mouseInputs);
        this.gameCurrentStates = gameCurrentStates;
        changePanelSize();
    }

    private void changePanelSize() {
        Dimension size = new Dimension(panelWidth, panelHeight);
        setPreferredSize(size);
    }

    // If the state of game is changed in game class, we need this following method to also update the gameCurrentStates
    // in GraphicPanel so that we will render the current state of game.
    public void changeGameStates(State gameCurrentStates){

        this.gameCurrentStates = gameCurrentStates;
        // Also have to change the gameCurrentStates in KeyboardInput
        // so it calls the methods corresponding to current state
        keyboardInputs.setKeyboardGameStates(gameCurrentStates);
        // Also changing for mouseInput due to the same reason
        mouseInputs.setMouseGameStates(gameCurrentStates);
    }
    public State getGameCurrentStates(){
        return this.gameCurrentStates;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // render the corresponding current running state of the game
        gameCurrentStates.render(g);

    }
}
