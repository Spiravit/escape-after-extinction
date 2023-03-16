package group7.Graphics;

import javax.swing.*;

/**
* The class GraphicWindow extends @see <a href=”https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html”>label</a>.
* The class generates the window frame for game window.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class GraphicsWindow {
    private JFrame jframe;

    /**
     * Constructor: Initializes jframe field and changes jframe's attributes.
     * @param graphicsPanel
     */
    public GraphicsWindow(GraphicsPanel graphicsPanel){
        jframe = new JFrame();
        // changing attributes of window by calling changeWindowAttributes field
        changeWindowAttributes(graphicsPanel);
    }
    
    /**
     * Sets the new Window attributes to suit this game's needs.
     * @param graphicsPanel
     */
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
