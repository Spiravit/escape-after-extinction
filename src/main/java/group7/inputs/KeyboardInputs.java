package group7.inputs;

import group7.Graphics.GraphicsPanel;
import group7.gameStates.State;
import group7.utils.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private State gameCurrentStates;
    public KeyboardInputs(State gameCurrentStates){
        this.gameCurrentStates=gameCurrentStates;
    }
    public void setKeyboardGameStates(State gameCurrentStates) {
        this.gameCurrentStates = gameCurrentStates;
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
