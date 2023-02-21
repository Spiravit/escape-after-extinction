package group7.main;

import group7.entities.Player;
import group7.inputs.*;
import group7.utils.Direction;

import javax.swing.*;
import java.awt.*;

import static group7.main.Game.*;

/**
 * is responsible for ???
 * @author Salman Ayaz, Karmen Yung, Mohammad Parsaei, Chen Min
 */
public class GamePanel extends JPanel {
    Player player;
    public GamePanel(Player player){
        changePanelSize();
        super.addKeyListener(new KeyboardInputs(this));
        super.addMouseListener(new MouseInputs());
        this.player=player;
    }

    private void changePanelSize() {
        int gameWidth = (int) GAME_SIZE_SCALE * NUMBER_OF_TILES_IN_HEIGHT * TILES_SIZE;;
        int gameHeight= (int) GAME_SIZE_SCALE * NUMBER_OF_TILES_IN_HEIGHT * TILES_SIZE;
        Dimension size = new Dimension(gameWidth,gameHeight);
        setPreferredSize(size);
    }

    public void setDirection(Direction direction) {

        player.setDirection(direction);
    }

    public void removeDirection(Direction direction) {
        player.removeDirection(direction);
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        // redraw the player
        player.renderEntity(g);
    }
}
