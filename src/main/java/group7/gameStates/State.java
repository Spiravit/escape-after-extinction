package group7.gameStates;


import group7.Game;
import group7.utils.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {
    public Game game;
    public State(Game game){
        this.game=game;
    }
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);

    public abstract void mouseClicked(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
}