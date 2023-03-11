package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import userInterface.UiParallelBackground;

import static group7.Graphics.GraphicsPanel.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenuState extends State {
    protected GraphicsButtons[] mainMenuButtons = new GraphicsButtons[4]; // 3 since there are 3 buttons in Main menu
    private BufferedImage mainMenuBackground;
    private BufferedImage[] loadingMainPage = new BufferedImage[3];
    protected UiParallelBackground mainPageParallelBG;
    private int loadingIndex=0;
    private int loadingSpeed=20;
    private  int loadingTick = 0;


    // The Heigh of main Menu background
    private static final int MAIN_MENU_BACKGROUND_HEIGH=80+4*GraphicsGrid.getScaleY()+20;
    private static final int MAIN_MENU_BACKGROUND_WIDTH=4*GraphicsGrid.getScaleX(); // The width of main Menu is 4 Grids

    public MainMenuState(Game game) {
        super(game);
        mainPageParallelBG= new UiParallelBackground(8,"menu/parallexBG/mainMenu",2,14);
        loadingMainPage[0] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_1);
        loadingMainPage[1] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_2);
        loadingMainPage[2] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_3);

        mainMenuBackground = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BACKGROUND);
        mainMenuButtons[0] = new GraphicsButtons(game,panelWidth / 2, 160, 0, gameStates.PLAYER_SELECTION_SUB_MENU);
        //mainMenuButtons[1] = new GraphicsButtons(game,panelWidth / 2, 160 + 15 + GraphicsGrid.getScaleY(), 4, gameStates.IN_MENU);
        mainMenuButtons[1] = new GraphicsButtons(game,panelWidth / 2, 160 + 15 + GraphicsGrid.getScaleY(), 4, gameStates.LEVEL_SELECTION_SUB_MENU); //TEST
        mainMenuButtons[2] = new GraphicsButtons(game,panelWidth / 2, 160 + 35 + 2*GraphicsGrid.getScaleY(), 2, gameStates.CREDIT_SUB_MENU);
        mainMenuButtons[3] = new GraphicsButtons(game,panelWidth / 2, 160 + 45 + 3 * GraphicsGrid.getScaleY(), 3, gameStates.QUIT);
    }

    @Override
    public void update() {
        for (GraphicsButtons button : mainMenuButtons)
            button.update();
    }

    @Override
    public void render(Graphics g) {

        mainPageParallelBG.renderParallelBackground(g);
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
            // Draw Main menu background
            g.drawImage(mainMenuBackground,
                    panelWidth / 2 - 2*GraphicsGrid.getScaleX(),
                    140,
                    MAIN_MENU_BACKGROUND_WIDTH,
                    MAIN_MENU_BACKGROUND_HEIGH,
                    null);

            for (GraphicsButtons buttons : mainMenuButtons) {
                buttons.render(g);
            }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            game.changeGameStates(gameStates.QUIT);
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
    public int incrementIndexCharacterDemo() {
       // DO NOTHING HERE !!!!
        return 0;
    }
    public int decrementIndexCharacterDemo() {
        // DO NOTHING HERE !!!!
        return 0;
    }
    public int decrementIndexLevelNumber() {
        // DO NOTHING HERE !!!!
        return 0;
    }
}
