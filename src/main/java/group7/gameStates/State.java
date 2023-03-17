package group7.gameStates;


import group7.Game;
import group7.userInterface.UiButtons;
import group7.userInterface.UiParallelBackground;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * An abstract class defining blueprint for objectives for each stage of game.
 *
 * @author Salaman Ayaz
 * @author Karmeny Yung
 * @author Mohammad Parsaei
 */
public abstract class State {
    public Game game;
    public boolean isPaused=false;
    public UiParallelBackground animatedDawnBackground;
    public UiButtons[] stateButton;
    public State(Game game){
        this.game=game;
        animatedDawnBackground = new UiParallelBackground(
            8,
            "menu/parallexBG/mainMenu",
            2,
            14
        );
    }

    /**
     * update must occur in current stage after an interaction such as pressing buttons.
     * default update method is empty.
     */
    public void update() {

    }

    /**
     * renders current stage on the game's window.
     * @param g
     * graphic object passed as parameter in order to draw the current stage.
     */
    public void render(Graphics g){
        animatedDawnBackground.renderParallelBackground(g);
    }

    /**
     * called when a key is pressed.
     * method is empty by default.
     * @param e
     */
    public void keyPressed(KeyEvent e){

    }

    /**
     * called when a key is released.
     * method is empty by default.
     * @param e
     */
    public void keyReleased(KeyEvent e){

    }

    /**
     * called when a mouse is clicked.
     * sets the mousePressed boolean to true.
     * @param e
     */
    public void mousePressed(MouseEvent e){
        for (UiButtons button : stateButton) {
            if (button.isMouseInButton(e)) {
                button.setIsMousePressedButton(true);
            }
        }
    }

    /**
     * called when a mouse button is released.
     * @param e
     * sets the mouseReleased boolean to true.
     */
    public void mouseReleased(MouseEvent e){
        for (UiButtons button : stateButton) {
            if (button.isMouseInButton(e)) {
                if (button.getIsMousePressed())
                    button.applyGameState();
                break;
            }
        }
        for (UiButtons button : stateButton) {
            button.resetMouseBooleans();
        }
    }

    /**
     * called when a mouse is moved.
     * @param e
     * sets the mouseOver boolean to true if the mouse is over a button.
     */
    public void mouseMoved(MouseEvent e){
        for (UiButtons button : stateButton)
            button.setIsMouseOverButton(false);

        for (UiButtons button : stateButton) {
            if (button.isMouseInButton(e)) {
                button.setIsMouseOverButton(true);
                break;
            }
        }
    }
    /**
     * increments the index of the sprite array
     * default method does nothing
     * @return
     * default method returns -1
     */
    public int incrementSpriteArrayIndex() {
        return -1;
    }

    /**
     * decrements the index of the sprite array
     * default method does nothing
     * @return
     * default method returns -1
     */
    public  int decrementSpriteArrayIndex() {
        return -1;

    }
}