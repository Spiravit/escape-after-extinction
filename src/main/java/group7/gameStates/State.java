package group7.gameStates;


import group7.Game;
import group7.utils.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class State {
    public Game game;
    public State(Game game){
        this.game=game;
    }
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
}