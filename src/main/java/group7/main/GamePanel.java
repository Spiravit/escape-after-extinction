package group7.main;

import group7.entities.Player;
import group7.inputs.*;
import group7.utils.Direction;

import javax.swing.*;
import java.awt.*;

/**
 * is responsible for ???
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GamePanel extends JPanel {
    Player player;
    public GamePanel(Player player){
        super.addKeyListener(new KeyboardInputs(this));
        super.addMouseListener(new MouseInputs());
        this.player=player;
    }

    public void setDirection(Direction direction) {

        player.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        player.renderEntity(g);
    }
}
