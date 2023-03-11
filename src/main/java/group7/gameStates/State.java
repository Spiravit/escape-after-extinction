package group7.gameStates;


import group7.Game;

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
    public State(Game game){
        this.game=game;
    }

    /**
     * An abstract class defining update that must occur in current stage after an interaction such as pressing buttons.
     *
     */
    public abstract void update();

    /**
     * renders current stage on the game's window.
     *
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     *
     * @param e
     */
    public abstract void keyPressed(KeyEvent e);

    /**
     *
     * @param e
     */
    public abstract void keyReleased(KeyEvent e);

    /**
     *
     * @param e
     */
    public abstract void mousePressed(MouseEvent e);

    /**
     *
     * @param e
     */
    public abstract void mouseReleased(MouseEvent e);

    /**
     *
     * @param e
     */
    public abstract void mouseMoved(MouseEvent e);

    public abstract int incrementSpriteArrayIndex();
    public abstract int decrementSpriteArrayIndex();
}