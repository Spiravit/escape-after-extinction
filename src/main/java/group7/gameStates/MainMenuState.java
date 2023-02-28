package group7.gameStates;

import group7.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuState extends State{
    public MainMenuState(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString("MENU", 100, 200);
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            game.changeGameStates(gameStates.IN_LEVEL);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
