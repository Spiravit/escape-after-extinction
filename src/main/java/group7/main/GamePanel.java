package group7.main;

import group7.entities.Entity;
import group7.inputs.*;

import javax.swing.*;
import java.awt.*;

/**
 * is responsible for ???
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GamePanel extends JPanel {
    Entity player;
    public GamePanel(Entity player){
        super.addKeyListener(new KeyboardInputs(this));
        super.addMouseListener(new MouseInputs());
        this.player=player;
    }

    // Those two methods will be removed later, only for testing !
    public void changePositionX(double differenceX) {
        player.setPositionX(player.getPositionX()+differenceX);
        repaint();
    }
    public void changePositionY(double differenceY) {
        player.setPositionY(player.getPositionY()+differenceY);
        repaint();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        player.renderEntity(g);
    }
}
