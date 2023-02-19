package group7.main;

import group7.inputs.*;

import javax.swing.*;
import java.awt.*;

/**
 * is responsible for ???
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GamePanel extends JPanel {
    public GamePanel(){
        super.addKeyListener(new KeyboardInputs());
        super.addMouseListener(new MouseInputs());
    }
    public void paint(Graphics g){
        g.drawRect(100,100,50,50);
    }
}
