package group7.gameStates;

import group7.Game;
import group7.Graphics.GraphicsButtons;
import group7.Graphics.GraphicsGrid;
import group7.helperClasses.AssetLoader;
import group7.userInterface.UiParallelBackground;

import static group7.Graphics.GraphicsPanel.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenuState extends State {

    //dedicated for four buttons in main menu page
    protected GraphicsButtons[] mainMenuButtons = new GraphicsButtons[4];

    //mainMenuBoxBackground: dedicated for blue box image behind main menu buttons
    private BufferedImage mainMenuBoxBackground;

    // TODO Write comment for feild below
    private BufferedImage[] loadingMainPage = new BufferedImage[3];

    // mainPageParallelBG : for animated background on main menu page
    protected UiParallelBackground mainPageParallelBG;

    // TODO Write comment for these three bellow
    private int loadingIndex=0;
    private int loadingSpeed=20;
    private  int loadingTick = 0;


    //MAIN_MENU_BOX_BACKGROUND_HEIGHT: The Height of blue box behind main menu buttons
    private static final int MAIN_MENU_BOX_BACKGROUND_HEIGHT =5*GraphicsGrid.scaleY;

    //MAIN_MENU_BOX_BACKGROUND_WIDTH: The Width of blue box behind main menu buttons
    private static final int MAIN_MENU_BOX_BACKGROUND_WIDTH =4*GraphicsGrid.scaleX;

    public MainMenuState(Game game) {
        super(game);
        mainPageParallelBG= new UiParallelBackground(8,"menu/parallexBG/mainMenu",2,14);
        loadingMainPage[0] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_1);
        loadingMainPage[1] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_2);
        loadingMainPage[2] = AssetLoader.getSpriteAtlas(AssetLoader.LOADING_3);

        mainMenuBoxBackground = AssetLoader.getSpriteAtlas(AssetLoader.MAIN_MENU_BACKGROUND);
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

        // render the animated background
        mainPageParallelBG.renderParallelBackground(g);

        // Draw blue box behind buttons background
        renderMainMenuBox(g);

        // Render each main menu buttons
        for (GraphicsButtons buttons : mainMenuButtons) {
            buttons.render(g);
        }

    }

    /**
     * renders the blue box background behind main menu buttons. It is rendered at center of the window.
     *
     * @param g     Graphic object passed as parameter in order
     *              to draw the blue box behind buttons
     */
    private void renderMainMenuBox(Graphics g){
        // Draw image at x position of (width of window - width of box )
        // In order to make sure that it is centered.
        g.drawImage(mainMenuBoxBackground,
                (int)(panelWidth / 2 - MAIN_MENU_BOX_BACKGROUND_WIDTH /2),
                4*GraphicsGrid.scaleY,
                MAIN_MENU_BOX_BACKGROUND_WIDTH,
                MAIN_MENU_BOX_BACKGROUND_HEIGHT,
                null);
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
