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
        animatedDawnBackground = new UiParallelBackground(8,
                "menu/parallexBG/mainMenu",2,
                14);

    }

    /**
     * An abstract class defining update that must occur in current stage after an interaction such as pressing buttons.
     *
     */
    public void update(){}

    /**
     * renders current stage on the game's window.
     *
     * @param g
     */
    public void render(Graphics g){
        animatedDawnBackground.renderParallelBackground(g);
    }

    /**
     *
     * @param e
     */
    public void keyPressed(KeyEvent e){

    }

    /**
     *
     * @param e
     */
    public void keyReleased(KeyEvent e){

    }

    /**
     *
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
     *
     * @param e
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
     *
     * @param e
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

    public  int incrementSpriteArrayIndex(){return -1;}
    public  int decrementSpriteArrayIndex(){return -1;}
}