package group7.Graphics;

import javax.swing.*;

/**
 * Sets up the window frame for Game.
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GraphicsWindow {
    private JFrame jframe;

    /**
     * Initializes jframe field and changes jframe's attributes
     * @param gamePanel
     */
    public GraphicsWindow(GraphicsPanel gamePanel){
        jframe = new JFrame();
        // changing attributes of window by calling changeWindowAttributes field
        changeWindowAttributes(gamePanel);
    }
    
    private void changeWindowAttributes(GraphicsPanel gamePanel){
        // Terminate the program when player closes the window
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // window size set to be 1280x720
        jframe.setSize(1280,720);
        // Connecting the gamepanel to window
        jframe.add(gamePanel);
        // Spawn the window in center of screen
        jframe.setLocationRelativeTo(null);
        // The window can not be resized by user
        jframe.setResizable(false);
        // set the window size such that it packs the size of game panel
        jframe.pack();
        gamePanel.setFocusable(true);
        // Make window visible
        jframe.setVisible(true);
    }
}
