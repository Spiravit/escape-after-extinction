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
     * @param graphicsPanel
     */
    public GraphicsWindow(GraphicsPanel graphicsPanel){
        jframe = new JFrame();
        // changing attributes of window by calling changeWindowAttributes field
        changeWindowAttributes(graphicsPanel);
    }
    
    private void changeWindowAttributes(GraphicsPanel graphicsPanel){
        // Terminate the program when player closes the window
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Connecting the graphics panel to window
        jframe.add(graphicsPanel);
        // The window can not be resized by user
        jframe.setResizable(false);
        // set the window size such that it packs the size of graphics panel
        jframe.pack();
        graphicsPanel.setFocusable(true);
        // Spawn the window in center of screen
        jframe.setLocationRelativeTo(null);
        // Make window visible
        jframe.setVisible(true);
    }
}
