package group7.main;

import javax.swing.*;

/**
 * Sets up the window frame for Game.
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GameWindow {

    private JFrame jframe;

    /**
     * Initializes jframe field and changes jframe's attributes
     * @param gamePanel
     */
    public GameWindow(GamePanel gamePanel){
        jframe= new JFrame();
        // changing attributes of window by calling changeWindowAttributes field
        changeWindowAttributes(gamePanel);
    }
    private void changeWindowAttributes(GamePanel gamePanel){
        // Terminate the program when player closes the window
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // window size set to be 1280x720
        jframe.setSize(1280,720);
        // Connecting the gamepanel to window
        jframe.add(gamePanel);
        // Spawn the window in center of screen
        jframe.setLocationRelativeTo(null);
        // Make window visible
        jframe.setVisible(true);
        // The window can not be resized by user
        jframe.setResizable(false);
    }
}
