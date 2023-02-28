package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.utils.AssetLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenuState extends State {
    private GraphicsButtons[] mainMenuButtons = new GraphicsButtons[3]; // 3 since there are 3 buttons in Main menu
    private BufferedImage mainMenuBackground;

    public MainMenuState(Game game) {
        super(game);
        mainMenuBackground = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BACKGROUND);
        mainMenuButtons[0] = new GraphicsButtons(game,1280 / 2, 180, 0, gameStates.IN_LEVEL);
        mainMenuButtons[1] = new GraphicsButtons(game,1280 / 2, 180 + 40 + GraphicsGrid.getScaleY(), 1, gameStates.IN_LEVEL);
        mainMenuButtons[2] = new GraphicsButtons(game,1280 / 2, 180 + 80 + 2 * GraphicsGrid.getScaleY(), 2, gameStates.QUIT);
    }

    @Override
    public void update() {
        for (GraphicsButtons button : mainMenuButtons)
            button.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 1280, 720);
        g.drawImage(mainMenuBackground,1280 / 2 - 2*GraphicsGrid.getScaleX(),140,4*GraphicsGrid.getScaleX(),80+4*GraphicsGrid.getScaleY()+20,null);

        for (GraphicsButtons buttons : mainMenuButtons) {
            buttons.render(g);
        }
        //g.setColor(Color.black);
        //g.drawString("MENU", 100, 200);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            game.changeGameStates(gameStates.IN_LEVEL);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (GraphicsButtons button : mainMenuButtons) {
            if (button.isMouseInButton(e)) {
                button.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GraphicsButtons button : mainMenuButtons) {
            if (button.isMouseInButton(e)) {
                if (button.isMousePressed())
                    button.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (GraphicsButtons button : mainMenuButtons) {
            button.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GraphicsButtons button : mainMenuButtons)
            button.setMouseOver(false);

        for (GraphicsButtons button : mainMenuButtons) {
            if (button.isMouseInButton(e)) {
                button.setMouseOver(true);
                break;
            }

        }
    }
}
