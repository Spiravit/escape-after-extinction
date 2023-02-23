package group7.Graphics;

import group7.entities.Player;
import group7.inputs.*;
import group7.levels.LevelManager;
import group7.utils.Direction;

import javax.swing.*;

import static group7.Game.*;

import java.awt.*;

/**
 * is responsible for ???
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GraphicsPanel extends JPanel {
    Player player;
    LevelManager levelManager;
    int panelWidth = 1280;
    int panelHeight = 720;

    public GraphicsPanel(Player player, LevelManager levelManager){
        super.addKeyListener(new KeyboardInputs(this));
        super.addMouseListener(new MouseInputs());
        this.player = player;
        this.levelManager = levelManager;
        changePanelSize();
    }

    private void changePanelSize() {
        Dimension size = new Dimension(this.panelWidth, this.panelHeight);
        setPreferredSize(size);
    }

    public void setDirection(Direction direction) {
        System.out.println("setting direction");
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    public void paintComponent(Graphics g) {
        //System.out.println("painting 1");
        super.paintComponent(g);
        //System.out.println("painting 2");
        //levelManager.render(g);
        //System.out.println("painting 3");
        player.render(g);
        //System.out.println("painting done");
    }
}
