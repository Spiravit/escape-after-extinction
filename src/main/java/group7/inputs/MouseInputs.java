package group7.inputs;

import group7.Graphics.GraphicsPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputs implements MouseListener {
    GraphicsPanel gamePanel;
    public MouseInputs(GraphicsPanel gamePanel){
        this.gamePanel=gamePanel;
    }
    public void mouseMoved(MouseEvent e) {
        gamePanel.getGameCurrentStates().mouseMoved(e);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gamePanel.getGameCurrentStates().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.getGameCurrentStates().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gamePanel.getGameCurrentStates().mouseReleased(e);
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
