package group7.main;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){
        jframe= new JFrame();
        // Terminate the program when player closes the window
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // window size set to be 1280x720
        jframe.setSize(1280,720);
        // Connecting the gamepanel to window
        jframe.add(gamePanel);
        // making window to appear center of display
        jframe.setLocationRelativeTo(null);
        // Make window visible
        jframe.setVisible(true);
    }
}
