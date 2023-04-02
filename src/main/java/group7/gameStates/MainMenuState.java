package group7.gameStates;

import group7.Game;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The class creates an object of type MainMenuState, that renders a page
 * where the player can selec to play the game, select a level, or exit the game.
 * @author  Salman Ayaz
 * @author  Karmen Yung
 * @author  Mohammad Parsaei
 * @author  Chen Min
 * @version 1.0
 * @since 2023-03-13
 */
public class MainMenuState extends State {

    // stores the loading screen for the main page
    private BufferedImage[] loadingMainPage = new BufferedImage[3];

    // stores the main menu box
    UiMenu mainMenuBox;

    public MainMenuState(Game game) {
        super(game);
        mainMenuBox = new UiMenu(game);
        loadingMainPage[0] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_1);
        loadingMainPage[1] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_2);
        loadingMainPage[2] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_3);
    }

    @Override
    public void render(Graphics g) {
        // render the animated background
        super.render(g);
        mainMenuBox.render(g);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            game.changeGameStates(gameStates.QUIT);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mainMenuBox.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mainMenuBox.mouseReleased(e);
    }

    /**
     * gets a mouse movement and passes it to the main menu box
     * @param e 
     * mouse movement event 
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mainMenuBox.mouseMoved(e);
    }
}
