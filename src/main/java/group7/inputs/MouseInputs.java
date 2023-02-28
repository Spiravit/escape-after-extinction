package group7.inputs;

import group7.Graphics.GraphicsPanel;
import group7.gameStates.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputs implements MouseListener {

    private State gameCurrentStates;
    public MouseInputs(State gameCurrentStates){
        // call the MouseInputs method of current state of game
        this.gameCurrentStates=gameCurrentStates;
    }
    public void setMouseGameStates(State gameCurrentStates) {
        this.gameCurrentStates = gameCurrentStates;
    }
    public void mouseMoved(MouseEvent e) {
        // call the mouseMoved method of current state of game
        gameCurrentStates.mouseMoved(e);

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // call the mouseClicked method of current state of game
        gameCurrentStates.mouseClicked(e);
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
