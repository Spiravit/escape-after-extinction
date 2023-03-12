package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiButtons;
import group7.userInterface.UiMenu;
import group7.userInterface.UiParallelBackground;

import static group7.Graphics.GraphicsPanel.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenuState extends State {

    // TODO Write comment for feild below
    private BufferedImage[] loadingMainPage = new BufferedImage[3];

    // TODO Write comment for these three bellow
    private int loadingIndex=0;
    private int loadingSpeed=20;
    private  int loadingTick = 0;

    UiMenu mainMenuBox;

    public MainMenuState(Game game) {
        super(game);
        mainMenuBox = new UiMenu(game);
        loadingMainPage[0] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_1);
        loadingMainPage[1] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_2);
        loadingMainPage[2] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_3);

    }

    @Override
    public void update() {
       // TODO
    }

    @Override
    public void render(Graphics g) {

        // render the animated background
        super.render(g);

        mainMenuBox.render(g);

    }

    /**
     *
     * @param g     Graphic object passed as parameter in order
     *              to draw the loading game name
     */
    private void renderLoadingGameName (Graphics g){
        // Show the original main menu
        loadingTick++;
        if (loadingTick >= loadingSpeed){
            loadingTick= 0;
            loadingIndex = loadingIndex + 1;
            if (loadingIndex >= 3){
                loadingIndex = 0;
            }
        }
        g.drawImage(loadingMainPage[loadingIndex],0,(int)(0.125*panelHeight), (int) panelWidth,(int) (0.75*panelHeight),null);
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
    @Override
    public void mouseMoved(MouseEvent e) {
        mainMenuBox.mouseMoved(e);
    }
}
